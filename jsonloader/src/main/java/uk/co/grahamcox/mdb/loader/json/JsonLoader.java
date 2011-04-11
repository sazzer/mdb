/*
 * Copyright (C) 2011 graham
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package uk.co.grahamcox.mdb.loader.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import net.sf.json.JSON;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import uk.co.grahamcox.mdb.loader.LoadException;
import uk.co.grahamcox.mdb.schema.Database;
import uk.co.grahamcox.mdb.schema.Schema;

/**
 * Load a given JSON File into the Database object
 * @author graham
 */
public class JsonLoader {
    /** The logger to use */
    private final Log LOG = LogFactory.getLog(JsonLoader.class);
    /**
     * Load the configuration from the given stream
     * @param database the database object to load into
     * @param input the stream to load from
     * @throws IOException if a read error occurs
     * @throws LoadException if an error occurs loading the configuration
     */
    public void load(final Database database, final InputStream input) 
            throws IOException, LoadException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        StringBuilder jsonString = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            jsonString.append(line).append("\n");
        }
        
        try {
            LOG.debug("Processing JSON text: " + jsonString);
            JSON json = JSONSerializer.toJSON(jsonString.toString());
            if (json instanceof JSONObject) {
                LOG.debug("Loaded JSON object");
                JSONObject jsonObject = JSONObject.class.cast(json);
                parseJson(database, jsonObject);
            }
            else {
                LOG.error("Loaded unexpected type. Should have been a JSONObject but was a " + json.getClass().getName());
                throw new LoadException("Parsed JSON was not of correct form. Should have been a JSONObject but was a " + json.getClass().getName());
            }
        }
        catch (JSONException ex) {
            LOG.error("Failed to load JSON data", ex);
            throw new LoadException("Failed to load JSON data", ex);
        }
    }
    
    /**
     * Parse the outermost JSONObject from the file. 
     * @param object the JSONObject to process
     * @throws LoadException if an error occurs
     */
    private void parseJson(Database database, JSONObject object) throws LoadException {
        JSONObject schemas = object.getJSONObject("schemas");
        if (schemas != null) {
            LOG.debug("Loading schemas");
            Iterator<String> keysIter = schemas.keys();
            while (keysIter.hasNext()) {
                String schemaName = keysIter.next();
                JSONObject schemaObject = schemas.getJSONObject(schemaName);
                
                Schema schema = database.getSchema(schemaName);
                if (schema == null) {
                    LOG.debug("Creating schema: " + schemaName);
                    schema = new Schema(schemaName);
                    if (schemaObject.has("comment")) {
                        String comment = schemaObject.getString("comment");
                        if (comment != null) {
                            schema.setComment(comment);
                        }
                    }
                    database.addSchema(schema);
                    
                }
            }
        }
    }
}
