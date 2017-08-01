/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hmue.integra.modelo;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Gleywson
 */
public class OrdemServico implements Serializable {
    private String os, tempoAbertura, servico, nome, setor, localidade, ramal, tpOs, usuario, status, tecResponsavel;
    
    private Date dtPedido;

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getTempoAbertura() {
        return tempoAbertura;
    }

    public void setTempoAbertura(String tempoAbertura) {
        this.tempoAbertura = tempoAbertura;
    }

    public Date getDtPedido() {
        return dtPedido;
    }

    public void setDtPedido(Date dtPedido) {
        this.dtPedido = dtPedido;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getTpOs() {
        return tpOs;
    }

    public void setTpOs(String tpOs) {
        this.tpOs = tpOs;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTecResponsavel() {
        return tecResponsavel;
    }

    public void setTecResponsavel(String tecResponsavel) {
        this.tecResponsavel = tecResponsavel;
    }

    public String getRamal() {
        return ramal;
    }

    public void setRamal(String ramal) {
        this.ramal = ramal;
    }

    
    
     
}
