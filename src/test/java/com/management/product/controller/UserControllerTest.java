package com.management.product.controller;

import com.management.product.entity.User;
import com.management.product.enums.UserRole;
import com.management.product.service.UserService;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Class for testing {@link UserController}
 *
 * @author Slava Makhinich
 * @version 1.0
 */
public class UserControllerTest {

    private UserService userService;
    private UserController userController;

    public UserControllerTest() {
        this.userService = Mockito.mock(UserService.class);
        this.userController = new UserController(this.userService);
    }

    @Test
    public void getNewUserPage() throws Exception {
        when(userService.isAuthenticatedAdmin()).thenReturn(true);
        ModelAndView modelAndView = userController.getNewUserPage();
        assertEquals(modelAndView.getViewName(), "add_user");
        Map<String, Object> models = modelAndView.getModel();
        assertTrue((boolean)models.get("is_admin"));
        verify(userService).isAuthenticatedAdmin();
        assertEquals(Arrays.toString(UserRole.values()), Arrays.toString((UserRole[]) models.get("roles")));
    }

    @Test
    public void addNewUser() throws Exception {
        User user = new User("user for addNewUser()",  "777");
        userController.addNewUser("user for addNewUser()", "777", UserRole.USER, false);
        verify(userService).add(user);
    }

    @Test
    public void getPageForUpdatingUser() throws Exception {
        long id = -100;
        User user = new User("user for getPageForUpdatingUser()");
        when(userService.get(id)).thenReturn(user);
        ModelAndView modelAndView = userController.getPageForUpdatingUser(id);
        assertEquals(modelAndView.getViewName(), "edit_user");
        Map<String, Object> models = modelAndView.getModel();
        assertEquals(models.get("user"), user);
        verify(userService).get(id);
        assertEquals(Arrays.toString(UserRole.values()), Arrays.toString((UserRole[]) models.get("roles")));
        assertTrue((boolean)models.get("is_admin"));
    }

    @Test
    public void updateUser() throws Exception {
        long id = -50;
        String username = "user for test updateUser()";
        String password = "888";
        UserRole role = UserRole.ADMIN;
        boolean isLocked = true;
        User user = new User(username, password, role);
        user.setId(id);
        user.setLocked(isLocked);
        when(userService.get(id)).thenReturn(user);
        assertEquals("redirect:/users",
                userController.updateUser(-50, username, password, role, isLocked));
        verify(userService).get(id);
        verify(userService).update(user);
    }

    @Test
    public void deleteUser() throws Exception {
        long id = -80;
        assertEquals(userController.deleteUser(id), "redirect:/users");
        verify(userService).remove(id);
    }

    @Test
    public void deleteAllUsers() throws Exception {
        assertEquals(userController.deleteAllUsers(), "redirect:/users");
        verify(userService).removeAll();
    }

}