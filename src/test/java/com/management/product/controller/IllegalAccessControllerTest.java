package com.management.product.controller;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Class for testing of {@link IllegalAccessController}
 *
 * @author Perevoznyk Pavel
 * @version 1.0
 */
public class IllegalAccessControllerTest {
    private IllegalAccessController illegalAccessController;

    public IllegalAccessControllerTest() {
        this.illegalAccessController = new IllegalAccessController();
    }

    @Test
    public void getIllegalAccessExceptionTest(){
        IllegalAccessException exception = new IllegalAccessException("The user does not have rights to access the request");

        try{
            illegalAccessController.getIllegalAccessException();
        }
        catch (IllegalAccessException e){
            assertTrue(e.getMessage().equals(exception.getMessage()));
        }
    }
}
