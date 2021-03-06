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
import javax.validation.constraints.NotNull;

/**
 * Representation of a schema in the database
 * @author gcox
 */
public class Schema {
    /** The name of the schema */
    @NotNull
    private final String name;
    /** The comment on the schema */
    private String comment = null;
    /** The tables in the schema */
    private Map<String, Table> tables = new HashMap<String, Table>();

    /**
     * Create the schema
     * @param name the name of the schema
     */
    public Schema(String name)
    {
        this.name = name;
    }


    /**
     * Get the comment on the schema
     * @return the comment
     */
    public String getComment()
    {
        return comment;
    }

    /**
     * Set the comment on the schema
     * @param comment the comment
     */
    public void setComment(String comment)
    {
        this.comment = comment;
    }

    /**
     * Get the name of the schema
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Get the tables in the schema
     * @return the tables
     */
    public Collection<Table> getTables() {
        return Collections.unmodifiableCollection(tables.values());
    }

    /**
     * Get the requested table from the schema
     * @param name the name of the table
     * @return the table
     */
    public Table getTable(String name) {
        return tables.get(name);
    }
    /**
     * Add a table to the schema
     * @param table the table to add
     */
    public void addTable(Table table) {
        tables.put(table.getName(), table);
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
        final Schema other = (Schema) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name))
        {
            return false;
        }
        if ((this.comment == null) ? (other.comment != null) : !this.comment.equals(other.comment))
        {
            return false;
        }
        if (this.tables != other.tables && (this.tables == null || !this.tables.equals(other.tables)))
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
        hash = 71 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 71 * hash + (this.comment != null ? this.comment.hashCode() : 0);
        hash = 71 * hash + (this.tables != null ? this.tables.hashCode() : 0);
        return hash;
    }

    /**
     * Generate a string for the object
     * @return the string
     */
    @Override
    public String toString()
    {
        return "Schema{" + "name=" + name + "comment=" + comment + "tables=" + tables + '}';
    }


}
