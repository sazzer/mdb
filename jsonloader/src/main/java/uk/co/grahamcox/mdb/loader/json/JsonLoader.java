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
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import uk.co.grahamcox.mdb.loader.LoadException;
import uk.co.grahamcox.mdb.schema.Column;
import uk.co.grahamcox.mdb.schema.Database;
import uk.co.grahamcox.mdb.schema.Schema;
import uk.co.grahamcox.mdb.schema.Table;

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
                    database.addSchema(schema);                    
                }
                if (schemaObject.has("comment") && schema.getComment() == null) {
                    String comment = schemaObject.getString("comment");
                    if (comment != null) {
                        schema.setComment(comment);
                    }
                }
                
                // Now build the tables in the schema
                if (schemaObject.has("tables")) {
                    Iterator<String> tablesIter = schemaObject.getJSONObject("tables").keys();
                    while (tablesIter.hasNext()) {
                        String tableName = tablesIter.next();
                        JSONObject tableObject = schemaObject.getJSONObject("tables").getJSONObject(tableName);
                        parseJsonTable(schema, tableName, tableObject);
                    }
                }
            }
        }
    }
    
    /**
     * Parse the part of the JSON that represents a table in a schema
     * @param schema the schema
     * @param tableName the name of the table
     * @param object the JSONObject representing the table
     * @throws LoadException if an error occurs
     */
    private void parseJsonTable(Schema schema, String tableName, JSONObject object) throws LoadException {
        if (schema.getTable(tableName) != null) {
            throw new LoadException("Duplicate definition of table " + tableName + " in schema " + schema.getName());
        }
        LOG.debug("Adding table: " + tableName);
        Table table = new Table(tableName);
        schema.addTable(table);
        Set<String> keyNames = new HashSet<String>();
        if (object.has("key")) {
            JSONArray keys = object.getJSONArray("key");
            Iterator<String> keyIter = keys.iterator();
            while (keyIter.hasNext()) {
                String keyName = keyIter.next();
                LOG.debug("Found key name: " + keyName);
                keyNames.add(keyName);
            }
        }
        
        if (object.has("columns")) {
            Iterator<String> columnIter = object.getJSONObject("columns").keys();
            while (columnIter.hasNext()) {
                String columnName = columnIter.next();
                boolean isKey = (keyNames.contains(columnName));
                JSONObject columnObject = object.getJSONObject("columns").getJSONObject(columnName);
                parseJsonColumn(table, columnName, isKey, columnObject);
            }
        }
    }
    
    /**
     * Parse the part of the JSON that represents a column in a table
     * @param table the table
     * @param columnName the name of the column
     * @param isKey whether the column is a key or not
     * @param object the JSONObject representing the column
     * @throws LoadException if an error occurs
     */
    private void parseJsonColumn(Table table, String columnName, boolean isKey, JSONObject object) throws LoadException {
        if (table.getColumn(columnName) != null) {
            throw new LoadException("Duplicate definition of column " + columnName + " in table " + table.getName());
        }
        LOG.debug("Adding column " + columnName);
                
        Column column = new Column(columnName);

        if (object.has("nullable")) {
            column.setNullable(object.getBoolean("nullable"));
        }
        
        if (isKey) {
            table.addKeyColumn(column);
        }
        else {
            table.addColumn(column);
        }
    }
}
