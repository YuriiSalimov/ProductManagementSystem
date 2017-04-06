package com.management.product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * The class describes the method when the user accesses the forbidden requests
 * @author Вадим
 */
@Controller
public class IllegalAccessController {

    /**
     * Method throws exception, when the user accesses the forbidden requests
     *
     * @throws IllegalAccessException in case when user doesn`t have rights to access the request
     */
    @RequestMapping(value = "/illegal_access_exception", method = RequestMethod.GET)
    public void getIllegalAccessException() throws IllegalAccessException {

        throw new IllegalAccessException("The user does not have rights to access the request");
    }
}
