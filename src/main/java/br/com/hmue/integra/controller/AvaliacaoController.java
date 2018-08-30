/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hmue.integra.controller;

import br.com.hmue.integra.modelo.AvaliacaoOS;
import br.com.hmue.integra.modelo.repositorio.AvaliacaoOsDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.inject.Inject;

/**
 *
 * @author Gleywson
 */
@Named(value = "avaliacaoController2")
@ViewScoped
public class AvaliacaoController implements Serializable {

    @Inject
    private AvaliacaoOsDAO dao;
    
    @PostConstruct
    public void init() {
        lista = dao.getAvaliacoesPendentes();
    }
    
    private List<AvaliacaoOS> lista;
    /**
     * Creates a new instance of AvaliacaoController
     */
    
    public List<AvaliacaoOS> getLista() {
//        lista = dao.getAvaliacoesPendentes();
        return lista;
    }
    
    public void atualizaConsulta() {
        init();
    }
}
