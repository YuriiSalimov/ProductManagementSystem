package com.management.product.controller;

import com.management.product.entity.Product;
import com.management.product.service.ProductService;
import com.management.product.service.UserService;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.AdditionalAnswers.returnsFirstArg;

/**
 * Class for testing of {@link ProductController}
 *
 * @author Perevoznyk Pavel
 * @version 1.0
 */
public class ProductControllerTest {

    private ProductService productService;
    private UserService userService;
    private ProductController productController;

    public ProductControllerTest() {
        this.productService = Mockito.mock(ProductService.class);
        this.userService = Mockito.mock(UserService.class);
        this.productController = new ProductController(this.productService);
        Mockito.when(productService.add(getProduct())).thenReturn(getProduct());
        Mockito.when(productService.get(1L)).thenReturn(getProduct());
        Mockito.when(productService.update(Mockito.any(Product.class))).then(returnsFirstArg());
    }

    @Test
    public void getNewProductPageTest() {

        ModelAndView modelAndView = productController.getNewProductPage();
        Map<String, Object> models = modelAndView.getModel();

        assertTrue((Boolean) models.get("is_admin"));
        assertEquals(modelAndView.getViewName(), "add_product");
    }

    @Test
    public void addNewProductTest() {
        Product product = getProduct();
        String result = productController.addNewProduct(product.getTitle(), product.getManufacturer(), product.getDescription(), product.getCost());
        assertEquals(result, "redirect:/product/1");
    }

    @Test
    public void getPageForUpdatingProductTest() {
        ModelAndView modelAndView = productController.getPageForUpdatingProduct(1L);
        Map<String, Object> models = modelAndView.getModel();

        assertTrue((Boolean) models.get("is_admin"));
        assertEquals((Product) models.get("product"), getProduct());
        assertEquals(modelAndView.getViewName(), "edit_product");
    }

    @Test
    public void updateTest() {
        Product product = getProduct();
        Product newProduct = new Product("Test Product Updated", "Test Manufacturer Updated", "Test Description updated", 200);
        String result = productController.update(product.getId(), newProduct.getTitle(), newProduct.getManufacturer(), newProduct.getDescription(), newProduct.getCost());
        assertEquals(result, "redirect:/product/" + product.getId());
        Mockito.verify(productService).get(1L);
        Mockito.verify(productService).update(Mockito.any(Product.class));
    }

    @Test
    public void deleteProductTest() {
        assertEquals(productController.deleteProduct(1L), "redirect:/home");
        Mockito.verify(productService).remove(1L);
    }

    @Test
    public void deleteAllProductsTest() {
        assertEquals(productController.deleteAllProducts(), "redirect:/home");
        Mockito.verify(productService).removeAll();
    }

    private Product getProduct() {
        Product product = new Product("Test Product 1", "Test Manufacturer 1", "Test Description 1", 100);
        product.setId(1L);
        return product;
    }
}
