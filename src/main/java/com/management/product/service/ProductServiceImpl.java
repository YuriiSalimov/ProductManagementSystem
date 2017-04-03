package com.management.product.service;

import com.management.product.entity.Product;
import com.management.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * The class provides a set of methods for operations with products.
 *
 * @author Вадим
 */
@Service
public class ProductServiceImpl extends DataServiceImpl<Product> implements ProductService {

    /**
     * An instance of ProductRepository
     */
    private ProductRepository repository;

    /**
     * Constructor
     *
     * @param repository An instance of class that implements
     *                   DataRepository interface for working with product
     */
    @Autowired
    public ProductServiceImpl(ProductRepository repository) {
        super(repository);
        this.repository = repository;
    }

    /**
     * The method founds product in database by title
     *
     * @param title a title of product
     * @return founded product
     * @throws IllegalArgumentException in case if product name is NULL or whitespaces
     * @throws NullPointerException     in case if product with entered name
     *                                  is not exist in database
     */
    @Override
    @Transactional(readOnly = true)
    public Product getByTitle(String title) throws IllegalArgumentException, NullPointerException {
        if (isBlank(title)) {
            throw new IllegalArgumentException("Incorrect name of product.");
        }
        Product product = repository.findByTitle(title);
        if (product == null) {
            throw new NullPointerException("Product with name " + title + " is not exist in database.");
        }
        return product;
    }

    /**
     * The method founds product in database by title and removes it
     *
     * @param title a title of product
     */
    @Override
    @Transactional
    public void removeByTitle(String title) {
        if (isNotBlank(title)) {
            repository.deleteByTitle(title);
        }
    }
}
