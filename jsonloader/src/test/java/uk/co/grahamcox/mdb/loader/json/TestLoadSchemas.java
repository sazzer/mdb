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

import java.io.InputStream;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import uk.co.grahamcox.mdb.loader.LoadException;
import uk.co.grahamcox.mdb.schema.Database;

/**
 *
 * @author graham
 */
public class TestLoadSchemas {
    private JsonLoader loader;
    
    @BeforeTest
    public void setUp() {
        loader = new JsonLoader();
    }
    
    @Test
    public void testLoadValid() throws Exception {
        InputStream stream = getClass().getResourceAsStream("/schemas.json");
        Database database = new Database();
        loader.load(database, stream);
        
        Assert.assertEquals(2, database.getSchemas().size());
        Assert.assertEquals("users", database.getSchema("users").getName());
        Assert.assertEquals("This is the Users schema", database.getSchema("users").getComment());
        Assert.assertEquals("utils", database.getSchema("utils").getName());
        Assert.assertNull(database.getSchema("utils").getComment());
        Assert.assertNull(database.getSchema("other"));
    }
    
    @Test(expectedExceptions=LoadException.class)
    public void testLoadInvalid() throws Exception {
        InputStream stream = getClass().getResourceAsStream("/invalid.json");
        Database database = new Database();
        loader.load(database, stream);
    }
}
