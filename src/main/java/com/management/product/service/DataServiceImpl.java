package com.management.product.service;

import com.management.product.entity.Model;
import com.management.product.repository.DataRepository;

import java.util.Collection;

/**
 * provides a set of methods for operations with entities.
 *
 * @author Slava
 */
public abstract class DataServiceImpl<T extends Model> implements DataService<T> {

    /**
     * An instance of class that implements DataRepository interface for working with T entity
     */
    protected DataRepository<T> repository;

    /**
     * Constructor
     *
     * @param repository An instance of class that implements DataRepository interface for working with T entity
     */
    public DataServiceImpl(DataRepository<T> repository) {
        this.repository = repository;
    }


    @Override
    public T add(T t) {

        return null;
    }

    @Override
    public Collection<T> addAll(Collection<T> collection) {
        return null;
    }

    @Override
    public T update(T t) {
        return null;
    }

    @Override
    public Collection<T> updateAll(Collection<T> collection) {
        return null;
    }

    @Override
    public T get(Long id) {
        return null;
    }

    @Override
    public Collection<T> getAll() {
        return null;
    }

    @Override
    public void remove(long id) {

    }

    @Override
    public void remove(T t) {

    }

    @Override
    public void remove(Collection<T> collection) {

    }

    @Override
    public void removeAll() {

    }

    @Override
    public boolean exist(long id) {
        return false;
    }

    @Override
    public boolean exist(T t) {
        return false;
    }
}
