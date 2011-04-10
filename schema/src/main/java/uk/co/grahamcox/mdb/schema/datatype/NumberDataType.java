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

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Representation of an Exact Numeric data type
 * The default if not changed is NUMERIC(38, 0)
 * @author graham
 */
public class NumberDataType {
    /** The precision of the number */
    @Min(1)
    @Max(38)
    private int precision = 38;
    /** The scale of the number */
    @Min(0)
    @Max(38)
    private int scale = 0;

    /**
     * Get the precision of the number
     * @return the precision
     */
    public int getPrecision() {
        return precision;
    }

    /**
     * Set the precision of the number
     * @param precision the precision
     */
    public void setPrecision(int precision) {
        this.precision = precision;
    }

    /**
     * Get the scale of the number
     * @return the scale
     */
    public int getScale() {
        return scale;
    }

    /**
     * Set the scale of the number
     * @param scale the scale
     */
    public void setScale(int scale) {
        this.scale = scale;
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
        final NumberDataType other = (NumberDataType) obj;
        if (this.precision != other.precision) {
            return false;
        }
        if (this.scale != other.scale) {
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
        int hash = 5;
        hash = 53 * hash + this.precision;
        hash = 53 * hash + this.scale;
        return hash;
    }

    /**
     * Generate a string for the object
     * @return the string
     */
    @Override
    public String toString() {
        return "Number{" + "precision=" + precision + ", scale=" + scale + '}';
    }
    
    
    
}
