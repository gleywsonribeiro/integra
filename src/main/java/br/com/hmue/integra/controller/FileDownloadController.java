/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hmue.integra.controller;

import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Gleywson
 */
@Named(value = "fileDownloadController")
@RequestScoped
public class FileDownloadController {

    private final StreamedContent file;
    
    public FileDownloadController() {
        InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/relatorios/blank.pdf");
        file = new DefaultStreamedContent(stream, "application/pdf", "OS_branco.pdf");
    }

    public StreamedContent getFile() {
        return file;
    }
    
    
}
