package com.management.product.controller;

import com.management.product.entity.Product;
import com.management.product.entity.User;
import com.management.product.enums.UserRole;
import com.management.product.service.ProductService;
import com.management.product.service.UserService;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Class for testing of {@link MainController}
 *
 * @author Perevoznyk Pavel
 * @version 1.0
 */
public class MainControllerTest {

    private ProductService productService;

    private UserService userService;

    private MainController mainController;

    public MainControllerTest() {
        this.productService = Mockito.mock(ProductService.class);
        this.userService = Mockito.mock(UserService.class);
        this.mainController = new MainController(productService, userService);
        Mockito.when(productService.getAll()).thenReturn(getProductCollection());
        Mockito.when(userService.getAll()).thenReturn(getUserCollection());
        Mockito.when(productService.get(1L)).thenReturn(new Product("Test Product 1", "Test Manufacturer 1", "Test Description 1", 100));

    }

    @Test
    public void getIndexPageTest() {
        Mockito.when(userService.isAuthenticatedAdmin()).thenReturn(false);
        ModelAndView modelAndView = mainController.getIndexPage();
        Map<String, Object> models = modelAndView.getModel();

        assertEquals((Collection<Product>) models.get("products"), productService.getAll());
        assertFalse((Boolean) models.get("is_admin"));

        Mockito.when(userService.isAuthenticatedAdmin()).thenReturn(true);
        modelAndView = mainController.getIndexPage();
        models = modelAndView.getModel();

        assertTrue((Boolean) models.get("is_admin"));

        assertEquals(modelAndView.getViewName(), "index");
    }

    @Test
    public void getUsersPageTest() {
        Mockito.when(userService.isAuthenticatedAdmin()).thenReturn(false);
        ModelAndView modelAndView = mainController.getUsersPage();
        Map<String, Object> models = modelAndView.getModel();

        assertEquals((Collection<User>) models.get("users"), getUserCollection());
        assertFalse((Boolean) models.get("is_admin"));

        Mockito.when(userService.isAuthenticatedAdmin()).thenReturn(true);
        modelAndView = mainController.getUsersPage();
        models = modelAndView.getModel();

        assertTrue((Boolean) models.get("is_admin"));
        assertEquals(modelAndView.getViewName(), "users");
    }

    @Test
    public void getProductPageTest() {
        Mockito.when(userService.isAuthenticatedAdmin()).thenReturn(false);
        ModelAndView modelAndView = mainController.getProductPage(1L);
        Map<String, Object> models = modelAndView.getModel();

        assertEquals((Product) models.get("product"), productService.get(1L));
        assertFalse((Boolean) models.get("is_admin"));

        Mockito.when(userService.isAuthenticatedAdmin()).thenReturn(true);
        modelAndView = mainController.getProductPage(1L);
        models = modelAndView.getModel();

        assertTrue((Boolean) models.get("is_admin"));
        assertEquals(modelAndView.getViewName(), "product");
    }

    private Collection<Product> getProductCollection(){
        Collection<Product> products = new HashSet<>();
        products.add(new Product("Test Product 1", "Test Manufacturer 1", "Test Description 1", 100));
        products.add(new Product("Test Product 2", "Test Manufacturer 2", "Test Description 2", 200));
        products.add(new Product("Test Product 3", "Test Manufacturer 3", "Test Description 3", 300));
        return products;
    }
    private Collection<User> getUserCollection(){
        Collection<User> users = new HashSet<>();
        users.add(new User("Test User 1", "Test Password 1", UserRole.USER));
        users.add(new User("Test User 2", "Test Password 2", UserRole.ADMIN));
        users.add(new User("Test User 3", "Test Password 3", UserRole.USER));
        return users;
    }
}
