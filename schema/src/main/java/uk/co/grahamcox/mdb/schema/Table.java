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

package uk.co.grahamcox.mdb.schema;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Representation of an actual table
 * @author graham
 */
public class Table {
    /** The name of the table */
    @NotNull
    private String name = null;
    /** The comment on the table */
    private String comment = null;
    /** The set of columns that make up the tables key */
    @Size(min=1)
    private Set<Column> keyColumns = new HashSet<Column>();
    /** The set of columns that make up the rest of the table */
    private Set<Column> columns = new HashSet<Column>();
    
    /**
     * Get the key columns
     * @return the key columns
     */
    public Set<Column> getKeyColumns() {
        return Collections.unmodifiableSet(keyColumns);
    }
    /**
     * Get the standard columns - i.e. those that are not part of the key
     * @return the standard columns
     */
    public Set<Column> getStandardColumns() {
        return Collections.unmodifiableSet(keyColumns);
    }
    /**
     * Get all of the columns
     * @return the columns
     */
    public Set<Column> getAllColumns() {
        Set<Column> allColumns = new HashSet<Column>(this.columns);
        allColumns.addAll(keyColumns);
        return Collections.unmodifiableSet(allColumns);
    }

    /**
     * Add a column to the table. There must not be any columns with the same name
     * already registered
     * @param c the column to add
     */
    public void addColumn(Column c) {
        confirmUniqueName(c.getName());
        columns.add(c);
    }
    
    /**
     * Add a key column to the table. There must not be any columns with the same name
     * already registered
     * @param c the column to add
     */
    public void addKeyColumn(Column c) {
        confirmUniqueName(c.getName());
        keyColumns.add(c);
    }
    
    /**
     * Confirm that there are no columns with the given name already registered
     * @param name the name to check for
     */
    private void confirmUniqueName(final String name) {
        for (Column c : getAllColumns()) {
            if (name.equals(c.getName())) {
                throw new IllegalArgumentException("Column with name " + name + " already exists in table");
            }
        }
    }
    /**
     * Get the name of the table
     * @return the name of the table
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the table
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the comment on the table
     * @return the comment
     */
    public String getComment()
    {
        return comment;
    }

    /**
     * Set the comment on the table
     * @param comment the comment
     */
    public void setComment(String comment)
    {
        this.comment = comment;
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
        final Table other = (Table) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name))
        {
            return false;
        }
        if ((this.comment == null) ? (other.comment != null) : !this.comment.equals(other.comment))
        {
            return false;
        }
        if (this.keyColumns != other.keyColumns && (this.keyColumns == null || !this.keyColumns.equals(other.keyColumns)))
        {
            return false;
        }
        if (this.columns != other.columns && (this.columns == null || !this.columns.equals(other.columns)))
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
        int hash = 7;
        hash = 29 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 29 * hash + (this.comment != null ? this.comment.hashCode() : 0);
        hash = 29 * hash + (this.keyColumns != null ? this.keyColumns.hashCode() : 0);
        hash = 29 * hash + (this.columns != null ? this.columns.hashCode() : 0);
        return hash;
    }

    /**
     * Generate a string for the object
     * @return the string
     */
    @Override
    public String toString()
    {
        return "Table{" + "name=" + name + "comment=" + comment + "keyColumns=" + keyColumns + "columns=" + columns + '}';
    }


    
}
