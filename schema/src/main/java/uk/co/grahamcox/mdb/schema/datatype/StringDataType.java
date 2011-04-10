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

package uk.co.grahamcox.mdb.schema.datatype;

import uk.co.grahamcox.mdb.schema.DataType;
import javax.validation.constraints.Min;

/**
 * Datatype Implementation for a String data type
 * The default if not changed is TEXT
 * @author graham
 */
public class StringDataType implements DataType {
    /** The size of the string. Null implies unlimited */
    @Min(1)
    private Integer size = null;
    
    /** Whether the string is of a fixed or variable size */
    private boolean variableSize = true;

    /**
     * Get the size of the string
     * @return the size of the string
     */
    public Integer getSize() {
        return size;
    }

    /**
     * Set the size of the string
     * @param size the size of the string
     */
    public void setSize(Integer size) {
        this.size = size;
    }

    /**
     * Check if the string is of a fixed or variable size
     * @return True if the string is of Variable size. False if the string is
     * of Fixed size
     */
    public boolean isVariableSize() {
        return variableSize;
    }

    /**
     * Set if the string is of a fixed or variable size
     * @param variableSize True if the string is of Variable size. False if the 
     * string is of Fixed size
     */
    public void setVariableSize(boolean variableSize) {
        this.variableSize = variableSize;
    }

    /**
     * Compare to another object for equality
     * @param obj the object to compare to
     * @return True if equals. False if not
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StringDataType other = (StringDataType) obj;
        if (this.size != other.size && (this.size == null || !this.size.equals(other.size))) {
            return false;
        }
        if (this.variableSize != other.variableSize) {
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
        hash = 59 * hash + (this.size != null ? this.size.hashCode() : 0);
        hash = 59 * hash + (this.variableSize ? 1 : 0);
        return hash;
    }

    /**
     * Generate a string for the object
     * @return the string
     */
    @Override
    public String toString() {
        return "String{" + "size=" + size + ", variableSize=" + variableSize + '}';
    }
    
    
}
