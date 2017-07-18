/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hmue.integra.factory;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Gleywson
 */
@Named(value = "osController")
@RequestScoped
public class OrdemServicoController {

    @Inject
    private DAO dao;
    
    private OrdemServico os;
    private List<OrdemServico> servicos;

    public OrdemServicoController() {
        this.os = new OrdemServico();
    }

    public OrdemServico getOs() {
        return os;
    }

    public void setOs(OrdemServico os) {
        this.os = os;
    }

    public List<OrdemServico> getServicos() {
        servicos = dao.getOSsPendentes();
        return servicos;
        
    }
    
    
    
}
