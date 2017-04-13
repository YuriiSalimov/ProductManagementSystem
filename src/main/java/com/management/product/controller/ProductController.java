package com.management.product.controller;

import com.management.product.entity.Product;
import com.management.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * A class provides a set of methods for operations with products and handles requests from the administrator
 *
 * @author Вадим
 */
@Controller
@ComponentScan(basePackages = "com.management.product.service")
@RequestMapping(value = "/admin/product")
public class ProductController {

    /**
     * An instance of implementation ProductService interface
     */
    private ProductService productService;

    /**
     * Constructor
     *
     * @param productService an instance of implementation ProductService interface
     */
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Method creates a page to add a new product
     *
     * @return a page to add a new product
     */
    @RequestMapping(
            value = "/new",
            method = RequestMethod.GET
    )
    public ModelAndView getNewProductPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("is_admin", true);
        modelAndView.setViewName("add_product");
        return modelAndView;
    }

    /**
     * Method saves the new product and redirect to the saved product`s page
     *
     * @param title        a product name
     * @param manufacturer a product manufacturer`s name
     * @param description  a description of product
     * @param cost         a product cost
     * @return an address of saved product`s page
     */
    @RequestMapping(
            value = "/add",
            method = RequestMethod.POST
    )
    public String addNewProduct(
            @RequestParam(value = "title", defaultValue = "") String title,
            @RequestParam(value = "manufacturer", defaultValue = "") String manufacturer,
            @RequestParam(value = "description", defaultValue = "") String description,
            @RequestParam(value = "cost", defaultValue = "0") int cost
    ) {
        Product productToAdd = new Product(title, manufacturer, description, cost);
        Product productFromDb = productService.add(productToAdd);
        return "redirect:/product/" + productFromDb.getId();
    }

    /**
     * Method creates a page to edit a product
     *
     * @param id a unique identifier for product
     * @return a page to edit a product
     */
    @RequestMapping(
            value = "/edit/{id}",
            method = RequestMethod.GET
    )
    public ModelAndView getPageForUpdatingProduct(@PathVariable(name = "id") long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("product", productService.get(id));
        modelAndView.addObject("is_admin", true);
        modelAndView.setViewName("edit_product");
        return modelAndView;
    }

    /**
     * Method updates all parameters of a product and redirect to the edited product`s page
     *
     * @param id           a unique identifier for product
     * @param title        a product name
     * @param manufacturer a product manufacturer`s name
     * @param description  a description of product
     * @param cost         a product cost
     * @return an address of edited product`s page
     */
    @RequestMapping(
            value = "/update/{id}",
            method = RequestMethod.POST
    )
    public String update(
            @PathVariable(name = "id") long id,
            @RequestParam(value = "title", defaultValue = "") String title,
            @RequestParam(value = "manufacturer", defaultValue = "") String manufacturer,
            @RequestParam(value = "description", defaultValue = "") String description,
            @RequestParam(value = "cost", defaultValue = "0") int cost
    ) {
        Product productToUpdate = productService.get(id);
        productToUpdate.setTitle(title);
        productToUpdate.setManufacturer(manufacturer);
        productToUpdate.setDescription(description);
        productToUpdate.setCost(cost);
        Product productFromDb = productService.update(productToUpdate);
        return ("redirect:/product/" + productFromDb.getId());
    }

    /**
     * Method removes a product from database
     *
     * @param id a unique identifier for product
     * @return an address of home page
     */
    @RequestMapping(
            value = "/delete/{id}",
            method = RequestMethod.GET
    )
    public String deleteProduct(@PathVariable(name = "id") long id) {
        productService.remove(id);
        return "redirect:/home";
    }

    /**
     * Method removes all product from database
     *
     * @return an address of home page
     */
    @RequestMapping(
            value = "/delete/all",
            method = RequestMethod.GET
    )
    public String deleteAllProducts() {
        productService.removeAll();
        return "redirect:/home";
    }
}
