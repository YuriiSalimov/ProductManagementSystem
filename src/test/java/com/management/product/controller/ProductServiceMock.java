package com.management.product.controller;

import com.management.product.entity.Product;
import com.management.product.service.DataServiceImpl;
import com.management.product.service.ProductService;

import java.util.Collection;
import java.util.HashSet;

/**
 * Class is a Mock of {@link ProductService} for testing of {@link MainController}
 *
 * @author Perevoznyk Pavel
 * @version 1.0
 */
public class ProductServiceMock extends DataServiceImpl<Product> implements ProductService {

    private Collection<Product> products = new HashSet<>();

    public ProductServiceMock() {
        super(null);
        this.products.add(new Product("Test Product 1", "Test Manufacturer 1", "Test Description 1", 100));
        this.products.add(new Product("Test Product 2", "Test Manufacturer 2", "Test Description 2", 200));
        this.products.add(new Product("Test Product 3", "Test Manufacturer 3", "Test Description 3", 300));
    }

    @Override
    public Collection<Product> getAll() {
        return this.products;
    }

    @Override
    public Product get(long id) {
        return new Product("Test Product 1", "Test Manufacturer 1", "Test Description 1", 100);
    }

    @Override
    public Product getByTitle(String title) {
        return null;
    }

    @Override
    public void removeByTitle(String title) {

    }

}
