/*
 *  Copyright (C) 2011 gcox
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package uk.co.grahamcox.mdb.schema;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Representation of the database
 * @author gcox
 */
public class Database {
    /** The schemas in the database */
    private Map<String, Schema> schemas = new HashMap<String, Schema>();

    /**
     * Get the schemas in the database
     * @return the schemas
     */
    public Collection<Schema> getSchemas() {
        return Collections.unmodifiableCollection(schemas.values());
    }

    /**
     * Get the given schema by name
     * @param name the name of the schema
     * @return the schema
     */
    public Schema getSchema(String name) {
        return schemas.get(name);
    }
    /**
     * Add a table to the database
     * @param schema the table to add
     */
    public void addSchema(Schema schema) {
        schemas.put(schema.getName(), schema);
    }

    /**
     * Compare to another object for equality
     * @param obj the object to compare to
     * @return True if equal. False if not
     */
    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Database other = (Database) obj;
        if (this.schemas != other.schemas && (this.schemas == null || !this.schemas.equals(other.schemas)))
        {
            return false;
        }
        return true;
    }

    /**
     * Generate a hashcode for the object
     * @return the hashcode
     */
    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 71 * hash + (this.schemas != null ? this.schemas.hashCode() : 0);
        return hash;
    }

    
    /**
     * Generate a string for the object
     * @return the string
     */
    @Override
    public String toString()
    {
        return "Database{" + "schemas=" + schemas + '}';
    }


}
