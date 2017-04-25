package com.management.product.controller;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.data.mapping.model.IllegalMappingException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Class for testing of {@link AdviceController}
 *
 * @author Perevoznyk Pavel
 * @version 1.0
 */
public class AdviceControllerTest {
    private AdviceController adviceController;
    private HttpServletRequest request;

    public AdviceControllerTest() {
        this.adviceController = new AdviceController();
        this.request = Mockito.mock(HttpServletRequest.class);
    }

    @Test
    public void NoHandlerFoundExceptionTest() {
        ModelAndView modelAndView = adviceController.NoHandlerFoundException(new NoHandlerFoundException("theMethod()", "test URL", new HttpHeaders()), request);
        Map<String, Object> models = modelAndView.getModel();

        assertEquals(models.get("message"), "NoHandlerFoundException : No handler found for theMethod() test URL");
        assertEquals(models.get("status"), HttpStatus.NOT_FOUND.value());
        assertEquals(modelAndView.getViewName(), "error");
    }

    @Test
    public void NullPointerExceptionTest() {
        ModelAndView modelAndView = adviceController.NullPointerException(new NullPointerException("test"), request);
        Map<String, Object> models = modelAndView.getModel();

        assertEquals(models.get("message"), "NullPointerException : test");
        assertEquals(models.get("status"), HttpStatus.BAD_REQUEST.value());
        assertEquals(modelAndView.getViewName(), "error");
    }

    @Test
    public void IllegalArgumentExceptionTest() {
        ModelAndView modelAndView = adviceController.IllegalArgumentException(new IllegalArgumentException("test"), request);
        Map<String, Object> models = modelAndView.getModel();

        assertEquals(models.get("message"), "IllegalArgumentException : test");
        assertEquals(models.get("status"), HttpStatus.NOT_ACCEPTABLE.value());
        assertEquals(modelAndView.getViewName(), "error");
    }

    @Test
    public void HttpRequestMethodNotSupportedExceptionTest() {
        ModelAndView modelAndView = adviceController.HttpRequestMethodNotSupportedException(new HttpRequestMethodNotSupportedException("test"), request);
        Map<String, Object> models = modelAndView.getModel();

        assertEquals(models.get("message"), "HttpRequestMethodNotSupportedException : Request method 'test' not supported");
        assertEquals(models.get("status"), HttpStatus.UNAUTHORIZED.value());
        assertEquals(modelAndView.getViewName(), "error");
    }

    @Test
    public void IllegalAccessExceptionTest() {
        ModelAndView modelAndView = adviceController.IllegalAccessException(new IllegalAccessException("test"), request);
        Map<String, Object> models = modelAndView.getModel();

        assertEquals(models.get("message"), "IllegalAccessException : test");
        assertEquals(models.get("status"), HttpStatus.FORBIDDEN.value());
        assertEquals(modelAndView.getViewName(), "error");
    }

    @Test
    public void IllegalMappingExceptionTest() {
        ModelAndView modelAndView = adviceController.IllegalMappingException(new IllegalMappingException("test"), request);
        Map<String, Object> models = modelAndView.getModel();

        assertEquals(models.get("message"), "IllegalMappingException : test");
        assertEquals(models.get("status"), HttpStatus.METHOD_NOT_ALLOWED.value());
        assertEquals(modelAndView.getViewName(), "error");
    }

    @Test
    public void ExceptionTest() {
        ModelAndView modelAndView = adviceController.Exception(new Exception("test"), request);
        Map<String, Object> models = modelAndView.getModel();

        assertEquals(models.get("message"), "Exception : test");
        assertEquals(models.get("status"), HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertEquals(modelAndView.getViewName(), "error");
    }
}
