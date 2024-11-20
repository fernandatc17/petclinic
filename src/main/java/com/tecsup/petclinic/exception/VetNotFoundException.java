package com.tecsup.petclinic.exception;

/**
 *
 * @author jcoronel
 *
 */
public class VetNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public VetNotFoundException(String message) {
        super(message);
    }
}
