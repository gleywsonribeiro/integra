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
public class AvaliacaoOS implements Serializable {
    private int codigo;
    private Date dataPedido;
    private String servico;
    private String solicitante;
    private String ramal;
    private String setor;
    private String status;
    private String tecnico;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    public String getRamal() {
        return ramal;
    }

    public void setRamal(String ramal) {
        this.ramal = ramal;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }

    @Override
    public String toString() {
        return "AvaliacaoOS{" + "codigo=" + codigo + ", dataPedido=" + dataPedido + ", servico=" + servico + ", solicitante=" + solicitante + ", ramal=" + ramal + ", setor=" + setor + ", status=" + status + ", tecnico=" + tecnico + '}';
    }

    
    
    
  
    
    
    
}
