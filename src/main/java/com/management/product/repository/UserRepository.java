package com.management.product.repository;

import com.management.product.entity.User;

/**
 * Interface provides a set of methods for the operation of User with a database.
 *
 * @author Slava
 */
public interface UserRepository extends DataRepository<User> {

    /**
     * The method find user in database by name
     *
     * @param username a users`s name
     * @return user with entered name
     */
    User findByUsername(String username);

    /**
     * The method removes user from database
     *
     * @param username a name of user, which must be removed
     */
    void deleteByUsername(String username);
}
