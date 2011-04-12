/*
 * Copyright (C) 2011 gcox
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
import uk.co.grahamcox.mdb.schema.Database;
import uk.co.grahamcox.mdb.schema.datatype.BooleanDataType;
import uk.co.grahamcox.mdb.schema.datatype.NumberDataType;
import uk.co.grahamcox.mdb.schema.datatype.StringDataType;

/**
 *
 * @author gcox
 */
public class TestLoadTables {
    private JsonLoader loader;
    
    @BeforeTest
    public void setUp() {
        loader = new JsonLoader();
    }

    @Test
    public void load() throws Exception {
        InputStream stream = getClass().getResourceAsStream("/tables.json");
        Database database = new Database();
        loader.load(database, stream);
        
        Assert.assertEquals(1, database.getSchemas().size());
        Assert.assertNotNull(database.getSchema("users"));
        Assert.assertEquals(1, database.getSchema("users").getTables().size());
        Assert.assertNotNull(database.getSchema("users").getTable("user"));
        Assert.assertEquals(1, database.getSchema("users").getTable("user").getKeyColumns().size());
        Assert.assertEquals(4, database.getSchema("users").getTable("user").getStandardColumns().size());
        Assert.assertEquals(5, database.getSchema("users").getTable("user").getAllColumns().size());
        
        Assert.assertNotNull(database.getSchema("users").getTable("user").getColumn("id"));
        Assert.assertNotNull(database.getSchema("users").getTable("user").getColumn("name"));
        Assert.assertNotNull(database.getSchema("users").getTable("user").getColumn("email"));
        Assert.assertNotNull(database.getSchema("users").getTable("user").getColumn("enabled"));
        Assert.assertNotNull(database.getSchema("users").getTable("user").getColumn("age"));
        
        Assert.assertFalse(database.getSchema("users").getTable("user").getColumn("id").isNullable());
        Assert.assertFalse(database.getSchema("users").getTable("user").getColumn("name").isNullable());
        Assert.assertFalse(database.getSchema("users").getTable("user").getColumn("email").isNullable());
        Assert.assertTrue(database.getSchema("users").getTable("user").getColumn("enabled").isNullable());
        Assert.assertFalse(database.getSchema("users").getTable("user").getColumn("age").isNullable());
        
        Assert.assertTrue(database.getSchema("users").getTable("user").getColumn("id").getDataType() instanceof StringDataType);
        Assert.assertTrue(database.getSchema("users").getTable("user").getColumn("name").getDataType() instanceof StringDataType);
        Assert.assertTrue(database.getSchema("users").getTable("user").getColumn("email").getDataType() instanceof StringDataType);
        Assert.assertTrue(database.getSchema("users").getTable("user").getColumn("enabled").getDataType() instanceof BooleanDataType);
        Assert.assertTrue(database.getSchema("users").getTable("user").getColumn("age").getDataType() instanceof NumberDataType);
    }
}
