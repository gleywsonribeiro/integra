/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hmue.integra.controller;

import br.com.hmue.integra.modelo.OrdemServico;
import java.io.Serializable;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Gleywson
 */
@Named(value = "exibeOsController")
@ViewScoped
public class ExibeOsController implements Serializable {
    private OrdemServico servico;
    
    public ExibeOsController() {
        this.servico = new OrdemServico();
    }

    public OrdemServico getServico() {
        return servico;
    }

    public void setServico(OrdemServico servico) {
        this.servico = servico;
    }
    
    public void fecharDialogo() {
        RequestContext.getCurrentInstance().closeDialog(null);
    }
}
