package com.management.product.repository;

import com.management.product.entity.Product;

/**
 * Interface provides a set of methods for the operation of Product with a database.
 * @author Вадим
 */
public interface ProductRepository extends DataRepository<Product> {

    /**
     * The method find product by name from database
     * @param title a product`s name
     * @return product with entered name
     */
    Product findByTitle(String title);

    /**
     * The method removes product from database
     * @param title a name of product, which must be removed
     */
    void deleteByTitle (String title);

}
