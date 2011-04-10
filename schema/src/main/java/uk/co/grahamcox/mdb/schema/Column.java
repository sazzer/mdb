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

import javax.validation.constraints.NotNull;

/**
 * Representation of a column in a table
 * @author graham
 */
public class Column {
    /** The name of the column */
    @NotNull
    private String name;
    /** The data type of the column */
    @NotNull
    private DataType dataType;
    
    /** Whether the column is nullable or not */
    private boolean nullable = true;
    
    /** The default value of the column. Contains a snippet of SQL, so literal String values must contain ' characters */
    private String defaultValue = null;

    /**
     * Get the data type
     * @return the data type
     */
    public DataType getDataType() {
        return dataType;
    }

    /**
     * Set the data type
     * @param dataType the data type
     */
    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    /**
     * Get the default value
     * @return the default value
     */
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * Set the default value
     * @param defaultValue the default value
     */
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * Check if the column is nullable
     * @return True if the column is nullable. False if not
     */
    public boolean isNullable() {
        return nullable;
    }

    /**
     * Change if the column is nullable
     * @param nullable True if the column is nullable. False if not
     */
    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    /**
     * Get the name of the column
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the column
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    

    /**
     * Compare to another object for equality
     * @param obj the object to compare with
     * @return True if equal. False if not
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Column other = (Column) obj;
        if (this.dataType != other.dataType && (this.dataType == null || !this.dataType.equals(other.dataType))) {
            return false;
        }
        if (this.nullable != other.nullable) {
            return false;
        }
        if ((this.defaultValue == null) ? (other.defaultValue != null) : !this.defaultValue.equals(other.defaultValue)) {
            return false;
        }
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        return true;
    }

    /**
     * Generate a hashcode for the object
     * @return the hashcode
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.dataType != null ? this.dataType.hashCode() : 0);
        hash = 97 * hash + (this.nullable ? 1 : 0);
        hash = 97 * hash + (this.defaultValue != null ? this.defaultValue.hashCode() : 0);
        hash = 97 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }

    /**
     * Generate a string for the object
     * @return the string
     */
    @Override
    public String toString() {
        return "Column{" 
                + "name=" + name 
                + ", dataType=" + dataType 
                + ", nullable=" + nullable 
                + ", defaultValue=" + defaultValue 
                + '}';
    }
    
    
}
