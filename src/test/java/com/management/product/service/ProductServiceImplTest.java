package com.management.product.service;

import com.management.product.entity.Product;
import com.management.product.repository.ProductRepository;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


/**
 * Class for testing {@link com.management.product.service.ProductServiceImpl}
 *
 * @author Slava Makhinich
 * @version 1.0
 */

public class ProductServiceImplTest extends DataServiceImplTest<Product> {

    /**
     * An instance of {@link ProductRepository}
     */
    ProductRepository repository;

    /**
     * An instance of {@link Product}
     */
    Product product1;

    /**
     * An instance of {@link Product}
     */
    Product product2;

    /**
     * An instance of {@link ProductServiceImpl}
     */
    ProductServiceImpl productService;

    public ProductServiceImplTest() {
        this.product1 = new Product("first product", "Dell", "first product", 100);
        this.product1.setId(1l);
        this.product2 = new Product("second product", "Apple", "second product", 200);
        this.product2.setId(2l);
        this.repository = mock(ProductRepository.class);
        when(repository.findOne(product1.getId())).thenReturn(product1);
        when(repository.findOne(product2.getId())).thenReturn(product2);
        when(repository.findOne(-100l)).thenReturn(null);
        when(repository.save(product1)).thenReturn(product1);
        when(repository.save(product2)).thenReturn(product2);
        this.productService = new ProductServiceImpl(repository);
    }

    @Override
    public void updateTest() throws Exception {
        Product productForUpdate = new Product("For Update", 1000);
        when(repository.save(productForUpdate)).thenReturn(productForUpdate);
        productService.update(productForUpdate);
        verify(repository).save(productForUpdate);
        assertEquals(productService.update(productForUpdate), productForUpdate);
    }

    @Override
    public void updateAllTest() throws Exception {
        Product productForUpdateAllTest1 = new Product("productForUpdateAllTest1", 11111);
        productForUpdateAllTest1.setId(11111);
        Product productForUpdateAllTest2 = new Product("productForUpdateAllTest2", 22222);
        productForUpdateAllTest2.setId(22222);
        when(repository.save(productForUpdateAllTest1)).thenReturn(productForUpdateAllTest1);
        when(repository.save(productForUpdateAllTest2)).thenReturn(productForUpdateAllTest2);
        Collection<Product> collection = new ArrayList<>();
        collection.add(productForUpdateAllTest1);
        collection.add(productForUpdateAllTest2);
        Collection<Product> collectionAdded = productService.updateAll(collection);
        assertEquals(collection, collectionAdded);
        verify(repository).save(productForUpdateAllTest1);
        verify(repository).save(productForUpdateAllTest2);
    }

    @Override
    @Test(expected = IllegalArgumentException.class)
    public void removeTest() throws Exception {
        productService.remove(99999);
        verify(repository).delete(99999l);
        Product productToRemove = new Product("productToRemove", 1452);
        productService.remove(productToRemove);
        verify(repository).delete(productToRemove);
        Product productNull = null;
        productService.remove(productNull);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeCollectionTest() throws Exception {
        productService.remove(getObjects());
        verify(repository).delete(product1);
        verify(repository).delete(product2);
        Collection<Product> collection = null;
        productService.remove(collection);
    }

    @Override
    @Test(expected = IllegalArgumentException.class)
    public void existTest() throws Exception {
        when(repository.exists(753l)).thenReturn(true);
        assertTrue(productService.exist(753));
        verify(repository).exists(753l);
        Product product = new Product("for test exist", 159);
        product.setId(88888);
        when(repository.exists(88888l)).thenReturn(true);
        assertTrue(productService.exist(product));
        verify(repository).exists(88888l);
        dataService.exist(null);
    }

    @Test(expected = NullPointerException.class)
    public void getNotExist() throws Exception {
        productService.get(-100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getByTitleBlank() throws Exception {
        productService.getByTitle("   ");
    }

    @Test(expected = NullPointerException.class)
    public void getByTitle () throws Exception {
        when(repository.findByTitle(product1.getTitle())).thenReturn(product1);
        assertEquals(productService.getByTitle(product1.getTitle()), product1);
        verify(repository).findByTitle(product1.getTitle());
        when(repository.findByTitle("not exist")).thenReturn(null);
        productService.getByTitle("not exist");
    }

    @Test
    public void removeByTitle() throws Exception {
        productService.removeByTitle("for remove");
        productService.removeByTitle(" ");
        verify(repository).deleteByTitle("for remove");
        verify(repository, never()).deleteByTitle(" ");
    }

    @Test
    public void removeAllTest()throws Exception {
        productService.removeAll();
        verify(repository).deleteAll();
    }

    @Test
    public void getAllTest() throws Exception {
        when(repository.findAll()).thenReturn(getObjects());
        assertEquals(productService.getAll(), getObjects());
        verify(repository).findAll();
    }


    /**
     * Getter for field productService.
     *
     * @return an instance of {@link ProductServiceImpl} from field productService.
     */
    @Override
    protected DataService<Product> getService() {
        return this.productService ;
    }

    /**
     * Getter for field product1.
     *
     * @return an instance of {@link Product} from field product1.
     */
    @Override
    protected Product getObject() {
        return this.product1;
    }

    /**
     * Method for getting collection of {@link Product}s
     *
     * @return a collection of {@link Product}s
     */
    @Override
    protected List<Product> getObjects() {
        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        return products;
    }
}