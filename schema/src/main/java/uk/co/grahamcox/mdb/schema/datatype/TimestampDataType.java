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
import javax.validation.constraints.NotNull;

/**
 * Representation of a Date or Time data type
 * The default if not changed is TIMESTAMP(6) WITH TIME ZONE
 * @author graham
 */
public class TimestampDataType {
    /**
     * Enumeration of the type of date/time stored
     */
    public enum Type {
        /** Store the date only */
        DATE, 
        /** Store the time only */
        TIME,
        /** Store both the date and time */
        DATE_AND_TIME
    }
    
    /** The type of date/time stored */
    @NotNull
    private Type type = Type.DATE_AND_TIME;
    
    /** Store the timezone as well, if a time is stored */
    private boolean withTimezone = true;
    
    /** The precision of the value stored */
    @Min(0)
    @Max(6)
    private int precision = 6;

    /**
     * Get the precision
     * @return the precision
     */
    public int getPrecision() {
        return precision;
    }

    /**
     * Set the precision
     * @param precision the precision 
     */
    public void setPrecision(int precision) {
        this.precision = precision;
    }

    /**
     * Get the type
     * @return the type
     */
    public Type getType() {
        return type;
    }

    /**
     * Set the type
     * @param type the type 
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * Check if the value stored includes timezone
     * @return True if the value stored includes timezone. False if not
     */
    public boolean isWithTimezone() {
        return withTimezone;
    }

    /**
     * Change if the value stored includes timezone
     * @param withTimezone True if the value stored includes timezone. False if not
     */
    public void setWithTimezone(boolean withTimezone) {
        this.withTimezone = withTimezone;
    }

    /**
     * Compare to another object for equality
     * @param obj the object to compare to
     * @return True if equal, False if not
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TimestampDataType other = (TimestampDataType) obj;
        if (this.type != other.type) {
            return false;
        }
        if (this.withTimezone != other.withTimezone) {
            return false;
        }
        if (this.precision != other.precision) {
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
        int hash = 3;
        hash = 73 * hash + (this.type != null ? this.type.hashCode() : 0);
        hash = 73 * hash + (this.withTimezone ? 1 : 0);
        hash = 73 * hash + this.precision;
        return hash;
    }

    /**
     * Generate a string for the object
     * @return the string
     */
    @Override
    public String toString() {
        return "Timestamp{" + "type=" + type + ", withTimezone=" + withTimezone + ", precision=" + precision + '}';
    }
    
    
}
