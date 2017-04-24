package com.management.product.controller;

import com.management.product.entity.User;
import com.management.product.enums.UserRole;
import com.management.product.repository.DataRepository;
import com.management.product.service.DataServiceImpl;
import com.management.product.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.HashSet;

/**
 * Class is a Mock of {@link UserService} for testing of {@link MainController}
 *
 * @author Perevoznyk Pavel
 * @version 1.0
 */
public class UserServiceMock extends DataServiceImpl<User> implements UserService {

    private boolean isAuth = false;
    private Collection<User> users = new HashSet<>();

    public UserServiceMock() {
        super(null);
        this.users.add(new User("Test User 1", "Test Password 1", UserRole.USER));
        this.users.add(new User("Test User 2", "Test Password 2", UserRole.ADMIN));
        this.users.add(new User("Test User 3", "Test Password 3", UserRole.USER));
    }

    @Override
    public User getByUsername(String username) {
        return null;
    }

    @Override
    public void removeByUsername(String username) {

    }

    @Override
    public Collection<User> getByRole(UserRole userRole) {
        return null;
    }

    @Override
    public Collection<User> getAdmins() {
        return null;
    }

    @Override
    public Collection<User> getUsers() {
        return null;
    }

    @Override
    public User getAuthenticatedUser() {
        return null;
    }

    @Override
    public boolean isAuthenticatedAdmin() {
        return isAuth;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public Collection<User> getAll() {
        return this.users;
    }

    public void setAuth(boolean auth) {
        isAuth = auth;
    }
}
