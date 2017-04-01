package com.management.product.service;

import com.management.product.entity.Product;
import com.management.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * The class provides a set of methods for operations with products.
 *
 * @author Вадим
 */
@Service
public class ProductServiceImpl extends DataServiceImpl<Product> implements ProductService{

    /**
     * An instance of ProductRepository
     */
    private ProductRepository repository;

    /**
     * Constructor
     *
     * @param repository An instance of class that implements DataRepository interface for working with product
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
     */
    @Override
    @Transactional(readOnly = true)
    public Product getByTitle(String title) {
        if(isNotBlank(title)){
            Product product =  repository.findByTitle(title);
            if(product!=null){
                return product;
            } else {
                throw new NullPointerException("Product with name " + title + " is not exist in database.");
            }
        } else{
            throw new IllegalArgumentException("Incorrect name of product.");
        }
    }

    /**
     * The method founds product in database by title and removes it
     *
     * @param title a title of product
     */
    @Override
    @Transactional
    public void removeByTitle(String title) {
        if(isNotBlank(title)){
            repository.deleteByTitle(title);
        }
    }
}
