/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hmue.integra.factory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

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
    
    public void abrirView() {
        Map<String, Object> opcoes = new HashMap<String, Object>();
        opcoes.put("modal", true);
        opcoes.put("resizable", false);
        opcoes.put("contentHeight", 300);

        RequestContext.getCurrentInstance().openDialog("/telas/os/cadastro", opcoes, null);
    }
    
    public void fecharDialogo() {
        RequestContext.getCurrentInstance().closeDialog(null);
    }
}
