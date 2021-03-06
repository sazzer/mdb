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

package uk.co.grahamcox.mdb.loader;

/**
 * Exception thrown when loading a configuration
 * @author graham
 */
public class LoadException extends Exception {

    /**
     * Create the exception
     * @param string the message
     * @param thrwbl the root cause
     */
    public LoadException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    /**
     * Create the exception
     * @param string the message
     */
    public LoadException(String string) {
        super(string);
    }

}
