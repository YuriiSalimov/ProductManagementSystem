package com.management.product.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * A Class intercepts exceptions that throw out during
 * the work of methods of other classes.
 *
 * @author Вадим
 */
@Aspect
@Component
public class ExceptionAspectController {

    /**
     * An instance of Logger for logging information
     */
    private static final Logger LOGGER = Logger.getLogger(ExceptionAspectController.class);

    /**
     * Method register information about the exception:
     *
     * @param ex a exception, which will be register in method
     */
    @AfterThrowing(
            pointcut = "execution(* com.management.product..controller..*(..))",
            throwing = "ex"
    )
    public void afterThrowingAdvice(Exception ex) {
        LOGGER.error("EXCEPTION IN METHOD " + ex.getClass());
    }
}
