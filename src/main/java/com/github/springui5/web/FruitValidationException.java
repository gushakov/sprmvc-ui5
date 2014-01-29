/*
 * Exception handling based on examples from
 * http://springinpractice.com/2013/10/09/generating-json-error-object-responses-with-spring-web-mvc
 */

package com.github.springui5.web;

import org.springframework.validation.BindingResult;

/**
 * Exception thrown in case of any validation errors. Used in {@linkplain HomeController#handleException(FruitValidationException)}
 * method.
 *
 * @author gushakov
 */
public class FruitValidationException extends RuntimeException {

    private BindingResult errors;

    public FruitValidationException(BindingResult errors) {
        this.errors = errors;
    }

    public String getRejectedField() {
        return errors.getFieldError().getField();
    }

    public Object getRejectedValue() {
        return errors.getFieldError().getRejectedValue();
    }

    public String getRejectedMessage() {
        return errors.getFieldError().getDefaultMessage();
    }
}
