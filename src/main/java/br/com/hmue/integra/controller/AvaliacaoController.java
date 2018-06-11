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
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.inject.Inject;

/**
 *
 * @author Gleywson
 */
@Named(value = "avaliacaoController2")
@RequestScoped
public class AvaliacaoController implements Serializable {

    @Inject
    private AvaliacaoOsDAO dao;
    
    private List<AvaliacaoOS> lista;
    /**
     * Creates a new instance of AvaliacaoController
     */
    public AvaliacaoController() {
        lista = new ArrayList<AvaliacaoOS>();
    }

    public List<AvaliacaoOS> getLista() {
        lista = dao.getAvaliacoesPendentes();
        return lista;
    }
    
    
}
