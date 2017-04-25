package com.management.product.entity;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Slava Makhinich
 */
public class ProductTest extends ModelTest {

    @Test
    public void toStringTest() throws Exception {
        Product product = new Product("testTitle", "testManufactured", "testDescript", 123456789);
        String productString = product.toString();
        assertTrue(productString.contains("testTitle")
                && productString.contains("testManufactured")
                && productString.contains("testDescript")
                && productString.contains("123456789"));
    }

    @Test
    public void equals() throws Exception {
        Product product = new Product("testTitle", "testManufactured", "testDescript", 123456789);
        Product product1 = new Product("testTitle", "testManufactured", "testDescript", 123456789);
        Product product2 = new Product("testTitle", "testManufactured", "testDescript", 123456789);
        assertTrue(product.equals(product));
        assertTrue(product.equals(product1));
        assertTrue(product1.equals(product));
        assertTrue(product1.equals(product2));
        assertTrue(product.equals(product2));
        product1.setCost(555);
        assertFalse(product.equals(product1));
        product2.setTitle("NewTitle");
        assertFalse(product.equals(product2));
        product.setTitle("NewTitle");
        product2.setManufacturer("newManufactured");
        assertFalse(product.equals(product2));
        product.setManufacturer("newManufactured");
        product2.setDescription("newDescript");
        assertFalse(product.equals(product2));
        assertFalse(product.equals(null));
    }

    @Test
    public void hashCodeTest() throws Exception {
        Product product = new Product("testTitle", "testManufactured", "testDescript", 123456789);
        Product product1 = new Product("testTitle", "testManufactured", "testDescript", 123456789);
        assertTrue(product.hashCode() == product.hashCode());
        assertTrue(product.hashCode() == product1.hashCode());
        product.setTitle("newTitle");
        assertFalse(product.hashCode() == product1.hashCode());
        product1.setTitle("newTitle");
        product.setManufacturer("newMan");
        assertFalse(product.hashCode() == product1.hashCode());
        product1.setManufacturer("newMan");
        product.setDescription("newDesc");
        assertFalse(product.hashCode() == product1.hashCode());
        product1.setDescription("newDesc");
        product.setCost(987654321);
        assertFalse(product.hashCode() == product1.hashCode());
    }

    @Test
    public void getTitleAndSatTitle() throws Exception {
        Product product = new Product();
        product.setTitle("newTitle");
        assertEquals(product.getTitle(), "newTitle");
        product.setTitle("    ");
        assertEquals(product.getTitle(), "");
    }

    @Test
    public void getAndSatManufacturer() throws Exception {
        Product product = new Product();
        product.setManufacturer("newMan.");
        assertEquals(product.getManufacturer(), "newMan.");
        product.setManufacturer("         ");
        assertEquals(product.getManufacturer(), "");
    }

    @Test
    public void getAndSatDescription() throws Exception {
        Product product = new Product();
        product.setDescription("newDesc.");
        assertEquals(product.getDescription(), "newDesc.");
        product.setDescription("        ");
        assertEquals(product.getDescription(), "");
    }

    @Test
    public void getAndSetCost() throws Exception {
        Product product = new Product();
        product.setCost(7777777);
        assertEquals(product.getCost(), 7777777);
        product.setCost(-89);
        assertEquals(product.getCost(), 0);
    }
}
