package com.management.product.repository;

import com.management.product.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface provides a set of methods for the operation of entities with a database.
 * @param <T> Entity type, extends {@link Model}.
 * @author Вадим
 */
public interface DataRepository<T extends Model> extends JpaRepository<T, Long> {
}
