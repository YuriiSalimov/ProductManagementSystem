package com.management.product.entity;

import com.management.product.enums.UserRole;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by Вадим on 01.04.2017.
 */
public class UserTest {

    @Test
    public void toStringTest() throws Exception {
        User testUser = new User("name", "pass", UserRole.ADMIN);
        String toString = testUser.toString();
        assertTrue(toString.contains("name") &&
                toString.contains("pass") &&
                toString.contains("ADMIN"));
    }

    @Test
    public void equals() throws Exception {
        User testUser1 = new User("name", "pass", UserRole.ADMIN);
        User testUser2 = new User("name", "pass", UserRole.ADMIN);
        assertEquals(testUser1, testUser2);
        testUser1.setUsername("newName");
        assertNotEquals(testUser1, testUser2);
        testUser2.setUsername("newName");
        assertEquals(testUser1, testUser2);
        testUser1.setPassword("newPass");
        assertNotEquals(testUser1, testUser2);
        testUser2.setPassword("newPass");
        assertEquals(testUser1, testUser2);
        testUser1.setRole(UserRole.USER);
        assertNotEquals(testUser1, testUser2);
        assertNotEquals(testUser1, null);
        assertNotEquals(testUser1, new Integer(5));
    }

    @Test
    public void hashCodeTest() throws Exception {
        User testUser1 = new User("name", "pass", UserRole.ADMIN);
        User testUser2 = new User("name", "pass", UserRole.ADMIN);
        assertTrue(testUser1.hashCode() == testUser2.hashCode());
        testUser1.setPassword("pass1");
        assertFalse(testUser1.hashCode() == testUser2.hashCode());
    }

    @Test
    public void isAccountNonExpired() throws Exception {
        User testUser = new User("name", "pass");
        testUser.setLocked(false);
        assertTrue(testUser.isAccountNonExpired());
        testUser.setLocked(true);
        assertFalse(testUser.isAccountNonExpired());
    }

    @Test
    public void isAccountNonLocked() throws Exception {
        User testUser = new User();
        testUser.setLocked(false);
        assertTrue(testUser.isAccountNonLocked());
        testUser.setLocked(true);
        assertFalse(testUser.isAccountNonLocked());
    }

    @Test
    public void isCredentialsNonExpired() throws Exception {
        User testUser = new User("name");
        testUser.setLocked(false);
        assertTrue(testUser.isCredentialsNonExpired());
        testUser.setLocked(true);
        assertFalse(testUser.isCredentialsNonExpired());
    }

    @Test
    public void isEnabled() throws Exception {
        User testUser = new User();
        testUser.setLocked(false);
        assertTrue(testUser.isEnabled());
        testUser.setLocked(true);
        assertFalse(testUser.isEnabled());
    }

    @Test
    public void getAuthorities() throws Exception {
        User testUser = new User("name", "pass", UserRole.USER);
        Collection<GrantedAuthority> grantedAuthorities = testUser.getAuthorities();
        assertNotNull(grantedAuthorities);
        assertTrue(grantedAuthorities.size() == 1);

    }

    @Test
    public void getAndSetUsername() throws Exception {
        User testUser = new User();
        assertEquals(testUser.getUsername(), "");
        testUser.setUsername(null);
        assertEquals(testUser.getUsername(), "");
        testUser.setUsername("     ");
        assertEquals(testUser.getUsername(), "");
        testUser.setUsername("newName");
        assertEquals(testUser.getUsername(), "newName");
    }

    @Test
    public void getAndSetPassword() throws Exception {
        User testUser = new User();
        assertEquals(testUser.getPassword(), "");
        testUser.setPassword(null);
        assertEquals(testUser.getPassword(), "");
        testUser.setPassword("     ");
        assertEquals(testUser.getPassword(), "");
        testUser.setPassword("newPass");
        assertEquals(testUser.getPassword(), "newPass");
    }

    @Test
    public void getAndSetRole() throws Exception {
        User testUser = new User();
        assertEquals(testUser.getRole(), UserRole.USER);
        testUser.setRole(null);
        assertEquals(testUser.getRole(), UserRole.USER);
        testUser.setRole(UserRole.ADMIN);
        assertTrue(testUser.getRole().equals(UserRole.ADMIN));
    }

    @Test
    public void isLockedAndSetLocked() throws Exception {
        User testUser = new User();
        testUser.setLocked(true);
        assertTrue(testUser.isLocked());
        testUser.setLocked(false);
        assertFalse(testUser.isLocked());
    }
}
