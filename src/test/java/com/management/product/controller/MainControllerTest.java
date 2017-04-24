package com.management.product.controller;

import com.management.product.entity.Product;
import com.management.product.entity.User;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Class for testing of {@link MainController}
 *
 * @author Perevoznyk Pavel
 * @version 1.0
 */
public class MainControllerTest {

    private ProductServiceMock productServiceMock = new ProductServiceMock();
    private UserServiceMock userServiceMock = new UserServiceMock();

    private MainController mainController = new MainController(productServiceMock, userServiceMock);

    @Test
    public void getIndexPageTest() {
        userServiceMock.setAuth(false);
        ModelAndView modelAndView = mainController.getIndexPage();
        Map<String, Object> models = modelAndView.getModel();

        assertEquals((Collection<Product>) models.get("products"), productServiceMock.getAll());
        assertFalse((Boolean) models.get("is_admin"));

        userServiceMock.setAuth(true);
        modelAndView = mainController.getIndexPage();
        models = modelAndView.getModel();

        assertTrue((Boolean) models.get("is_admin"));

        assertEquals(modelAndView.getViewName(), "index");
    }

    @Test
    public void getUsersPageTest(){
        userServiceMock.setAuth(false);
        ModelAndView modelAndView = mainController.getUsersPage();
        Map<String, Object> models = modelAndView.getModel();

        assertEquals((Collection<User>) models.get("users"), userServiceMock.getAll());
        assertFalse((Boolean) models.get("is_admin"));

        userServiceMock.setAuth(true);
        modelAndView = mainController.getUsersPage();
        models = modelAndView.getModel();

        assertTrue((Boolean) models.get("is_admin"));
        assertEquals(modelAndView.getViewName(), "users");
    }

    @Test
    public void getProductPageTest(){
        userServiceMock.setAuth(false);
        ModelAndView modelAndView = mainController.getProductPage(1L);
        Map<String, Object> models = modelAndView.getModel();

        assertEquals((Product) models.get("product"), productServiceMock.get(1L));
        assertFalse((Boolean) models.get("is_admin"));

        userServiceMock.setAuth(true);
        modelAndView = mainController.getProductPage(1L);
        models = modelAndView.getModel();

        assertTrue((Boolean) models.get("is_admin"));
        assertEquals(modelAndView.getViewName(), "product");
    }
}
