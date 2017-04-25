package com.management.product.service;

import com.management.product.entity.Product;
import com.management.product.entity.User;
import com.management.product.enums.UserRole;
import com.management.product.repository.UserRepository;
import org.junit.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


/**
 * Created by Вадим on 20.04.2017.
 */
public class UserServiceImplTest extends DataServiceImplTest<User> {

    UserRepository repository;
    UserServiceImpl userService;
    private User user1;
    private User user2;

    public UserServiceImplTest() {
        this.user1 = new User ("User", "user", UserRole.USER);
        this.user2 = new User ("Admin", "admin", UserRole.ADMIN);
        user1.setId(1L);
        user2.setId(2L);
        this.repository = mock(UserRepository.class);
        this.userService = new UserServiceImpl(repository);
        when(repository.findOne(user1.getId())).thenReturn(user1);
        when(repository.findOne(user2.getId())).thenReturn(user2);
        when(repository.findOne(-100L)).thenReturn(null);
        when(repository.save(user1)).thenReturn(user1);
        when(repository.save(user2)).thenReturn(user2);
    }
    

    @Test(expected = IllegalArgumentException.class)
    public void getByUsernameBlank() throws Exception {
        userService.getByUsername("   ");
    }

    @Test(expected = NullPointerException.class)
    public void getByUsername () throws Exception {
        when(repository.findByUsername(user1.getUsername())).thenReturn(user1);
        assertEquals(userService.getByUsername(user1.getUsername()), user1);
        verify(repository).findByUsername(user1.getUsername());
        when(repository.findByUsername("not exist")).thenReturn(null);
        userService.getByUsername("not exist");
    }

    @Test
    public void removeByUsername() throws Exception {
        userService.removeByUsername("remove user");
        userService.removeByUsername(" ");
        verify(repository).deleteByUsername("remove user");
        verify(repository, never()).deleteByUsername(" ");
    }



    @Test
    public void getByRole() throws Exception {
        userService.getByRole(UserRole.USER);
        userService.getByRole(UserRole.ADMIN);
        verify(repository).findAllByRole(UserRole.USER);
        verify(repository).findAllByRole(UserRole.ADMIN);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getByRoleNull() throws Exception {
        when(repository.findAllByRole(null)).thenReturn(null);
        userService.getByRole(null);
    }

    @Test
    public void getAdmins() throws Exception {
        when(repository.findAllByRole(UserRole.ADMIN)).thenReturn(getAdminsList());
        assertEquals(userService.getAdmins(),getAdminsList());
    }

    @Test
    public void getUsers() throws Exception {
        when(repository.findAllByRole(UserRole.USER)).thenReturn(getUsersList());
        assertEquals(userService.getUsers(),getUsersList());
    }

    @Test
    public void getAuthenticatedUser() throws Exception {
        securityContextHolderTest();

        User user = userService.getAuthenticatedUser();
        User userAnon = userService.getAuthenticatedUser();
        assertEquals(user2, user);
        assertTrue(userAnon.getUsername().equals("anonymousUser"));
    }


    @Test
    public void isAuthenticatedAdmin() throws Exception {
        securityContextHolderTest();
        assertTrue(userService.isAuthenticatedAdmin());
    }

    @Test
    public void loadUserByUsername() throws Exception {
        when(repository.findByUsername("User")).thenReturn(user1);
        org.springframework.security.core.userdetails.UserDetails user = userService.loadUserByUsername("User");
        assertEquals(user,user1);
    }
    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByUsernameEx() throws Exception {
        when(repository.findByUsername("Not Exist")).thenReturn(null);
        userService.loadUserByUsername("Not Exist");

    }

    @Override
    protected DataService<User> getService() {
        return this.userService;
    }

    @Override
    protected User getObject() {
        return this.user1;
    }

    @Override
    protected List<User> getObjects() {
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        return users;
    }

    @Override
    @Test
    public void updateTest() throws Exception {
        User userForUpdate = new User("For Update");
        when(repository.save(userForUpdate)).thenReturn(userForUpdate);
        userService.update(userForUpdate);
        verify(repository).save(userForUpdate);
        assertEquals(userService.update(userForUpdate), userForUpdate);
    }

    @Test
    public void getAllTest() throws Exception {
        when(repository.findAll()).thenReturn(getObjects());
        assertEquals(userService.getAll(), getObjects());
        verify(repository).findAll();
    }

    @Override
    @Test
    public void updateAllTest() throws Exception {
        User userForUpdateAllTest1 = new User("userForUpdateAllTest1");
        userForUpdateAllTest1.setId(11111);
        User userForUpdateAllTest2 = new User("productForUpdateAllTest2");
        userForUpdateAllTest2.setId(22222);
        when(repository.save(userForUpdateAllTest1)).thenReturn(userForUpdateAllTest1);
        when(repository.save(userForUpdateAllTest2)).thenReturn(userForUpdateAllTest2);
        Collection<User> collection = new ArrayList<>();
        collection.add(userForUpdateAllTest1);
        collection.add(userForUpdateAllTest2);
        Collection<User> collectionAdded = userService.updateAll(collection);
        assertEquals(collection, collectionAdded);
        verify(repository).save(userForUpdateAllTest1);
        verify(repository).save(userForUpdateAllTest2);
    }

    @Override
    @Test(expected = IllegalArgumentException.class)
    public void removeTest() throws Exception {
        userService.remove(111);
        verify(repository).delete(111L);
        User userToRemove = new User("userToRemove");
        userService.remove(userToRemove);
        verify(repository).delete(userToRemove);
        User userNull = null;
        userService.remove(userNull);
    }

    @Override
    @Test(expected = IllegalArgumentException.class)
    public void existTest() throws Exception {
        when(repository.exists(10L)).thenReturn(true);
        assertTrue(userService.exist(10));
        verify(repository).exists(10L);
        User user = new User("for test exist");
        user.setId(111);
        when(repository.exists(111L)).thenReturn(true);
        assertTrue(userService.exist(user));
        verify(repository).exists(111L);
        userService.exist(null);
    }

    @Test(expected = NullPointerException.class)
    public void getNotExist() throws Exception {
        userService.get(-100);
    }

    private List<User> getAdminsList() {
        List<User> users = new ArrayList<>();
        users.add(user2);
        return users;
    }
    private List<User> getUsersList() {
        List<User> users = new ArrayList<>();
        users.add(user1);
        return users;
    }

    private void securityContextHolderTest() {
        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(user2).thenReturn(new Product());
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
    }
}