package com.management.product.entity;

import com.management.product.service.DataService;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Class for testing {@link com.management.product.service.DataServiceImpl}
 *
 * @author Perevoznyk Pavel
 * @version 1.0
 */
public abstract class DataServiceImplTest<T extends Model> {

    protected DataService<T> dataService;

    @Before
    public void initialize() {
        dataService = getService();
    }

    @Test
    public void addAndGetTest() throws Exception {
        T object = getObject();

        object = dataService.add(object);
        assertEquals(object, dataService.get(object.getId()));
        assertFalse(dataService.get(1L).equals(null));
    }

    @Test
    public void updateTest() throws Exception {
        Long idOfUpdatedObject = 1L;
        T objectBeforeUpdate = dataService.get(idOfUpdatedObject);
        T changedObject = getObject();
        changedObject.setId(idOfUpdatedObject);
        dataService.update(changedObject);
        assertFalse(objectBeforeUpdate.equals(changedObject));
        assertEquals(dataService.get(idOfUpdatedObject), changedObject);
    }

    @Test
    public void addAllTest() throws Exception {
        Collection<T> collection = getObjects();
        collection = dataService.addAll(collection);

        for(T object : collection){
            assertEquals(object, dataService.get(object.getId()));
        }
    }

    @Test
    public void updateAllTest() throws Exception {
        Collection<T> collection = new HashSet<T>();
        for(int i=1; i<4; i++) {
            T object = getObject();
            object.setId(i);
            collection.add(object);
        }
        dataService.updateAll(collection);

        for (T object:collection) {
            assertEquals(object, dataService.get(object.getId()));
        }
    }

    @Test
    public void removeTest() throws Exception {
        Long id = 1L;
        T object = dataService.get(id);
        dataService.remove(id);
        assertFalse(object.equals(dataService.get(id)));

        dataService.add(object);
        assertEquals(object, dataService.get(id));

        dataService.remove(object);
        assertFalse(object.equals(dataService.get(id)));

        Collection<T> collection = dataService.getAll();
        dataService.removeAll();
        assertTrue(dataService.getAll().isEmpty());

        dataService.addAll(collection);
    }

    @Test
    public void existTest() throws Exception {
        T object = getObject();
        assertFalse(dataService.exist(object));

        object = dataService.get(1L);
        assertTrue(dataService.exist(object));

        assertFalse(dataService.exist(99999999L));
        assertTrue(dataService.exist(1L));
    }

    protected abstract DataService<T> getService();

    protected abstract T getObject();

    protected abstract Collection<T> getObjects();
}
