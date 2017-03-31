package com.management.product.service;

import com.management.product.entity.Product;

/**
 * Interface provides a set of methods for the operation with products.
 *
 * @author Slava
 */
public interface ProductService extends DataService<Product> {


    /**
     * The method founds product in database by title
     *
     * @param title a title of product
     * @return founded product
     */
    Product getByTitle(String title);

    /**
     * The method founds product in database by title and removes it
     *
     * @param title a title of product
     */
    void removeByTitle(String title);
}
