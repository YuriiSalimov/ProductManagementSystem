package com.management.product.service;

import com.management.product.entity.Model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.HashSet;

/**
 * Class for testing {@link com.management.product.service.DataServiceImpl}
 *
 * @author Perevoznyk Pavel
 * @version 1.0
 */
public abstract class DataServiceImplTest<T extends Model> {

    private DataService<T> dataService;

    @Before
    public void initialize() {
        dataService = getService();
    }

    @Test(expected = IllegalArgumentException.class)
    public void addAndGetTest() throws Exception {
        T object = getObject();
        object = dataService.add(object);
        Assert.assertEquals(object, dataService.get(object.getId()));
        Assert.assertFalse(dataService.get(1L).equals(null));
        dataService.add(null);
    }

    @Test
    public void updateTest() throws Exception {
        Long idOfUpdatedObject = 1L;
        T objectBeforeUpdate = dataService.get(idOfUpdatedObject);
        T changedObject = getObject();
        changedObject.setId(idOfUpdatedObject);
        dataService.update(changedObject);
        Assert.assertFalse(objectBeforeUpdate.equals(changedObject));
        Assert.assertEquals(dataService.get(idOfUpdatedObject), changedObject);
    }

    @Test
    public void addAllTest() throws Exception {
        Collection<T> collection = getObjects();
        collection = dataService.addAll(collection);
        for (T object : collection) {
            Assert.assertEquals(object, dataService.get(object.getId()));
        }
        Assert.assertTrue(dataService.addAll(null).size() == 0);
    }

    @Test
    public void updateAllTest() throws Exception {
        Collection<T> collection = new HashSet<T>();
        for (int i = 1; i < 3; i++) {
            T object = getObject();
            object.setId(i);
            collection.add(object);
        }
        dataService.updateAll(collection);
        for (T object : collection) {
            Assert.assertEquals(object, dataService.get(object.getId()));
        }
    }

    @Test
    public void removeTest() throws Exception {
        Long id = 1L;
        T object = dataService.get(id);
        dataService.remove(id);
        Assert.assertFalse(object.equals(dataService.get(id)));

        dataService.add(object);
        Assert.assertEquals(object, dataService.get(id));

        dataService.remove(object);
        Assert.assertFalse(object.equals(dataService.get(id)));

        Collection<T> collection = dataService.getAll();
        dataService.removeAll();
        Assert.assertTrue(dataService.getAll().isEmpty());

        dataService.addAll(collection);
    }

    @Test
    public void existTest() throws Exception {
        T object = getObject();
        Assert.assertFalse(dataService.exist(object));
        object = dataService.get(1L);
        Assert.assertTrue(dataService.exist(object));
        Assert.assertFalse(dataService.exist(99999999L));
        Assert.assertTrue(dataService.exist(1L));
    }

    protected abstract DataService<T> getService();

    protected abstract T getObject();

    protected abstract Collection<T> getObjects();
}
