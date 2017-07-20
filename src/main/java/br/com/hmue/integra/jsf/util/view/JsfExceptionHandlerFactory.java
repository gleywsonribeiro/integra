/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hmue.integra.jsf.util.view;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

/**
 *
 * @author gleyw
 */
public class JsfExceptionHandlerFactory extends ExceptionHandlerFactory {

    private ExceptionHandlerFactory handlerFactory;

    public JsfExceptionHandlerFactory(ExceptionHandlerFactory handlerFactory) {
        this.handlerFactory = handlerFactory;
    }

    @Override
    public ExceptionHandler getExceptionHandler() {
        return new JsfExceptionHandler(handlerFactory.getExceptionHandler());
    }

}
