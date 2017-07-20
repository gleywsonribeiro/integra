/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hmue.integra.controller;

import br.com.hmue.integra.modelo.DAO;
import br.com.hmue.integra.modelo.OrdemServico;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Gleywson
 */
@Named(value = "osController")
@SessionScoped
public class OrdemServicoController implements Serializable{

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
    
    public void abrirView() {
        Map<String, Object> opcoes = new HashMap<String, Object>();
        opcoes.put("modal", true);
        opcoes.put("resizable", false);
        opcoes.put("contentHeight", 300);
        opcoes.put("contentWidth", 600);

        RequestContext.getCurrentInstance().openDialog("/telas/os/cadastro", opcoes, null);
    }
    
    public void fecharDialogo() {
        RequestContext.getCurrentInstance().closeDialog(null);
    }
}
