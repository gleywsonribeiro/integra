/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hmue.integra.model;

/**
 *
 * @author Gleywson
 */
public class MotivoServico {
    private Long codigo; //cd_mot_serv
    private String descricao; //ds_mot_serv
    private Long codigoTipoOs;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getCodigoTipoOs() {
        return codigoTipoOs;
    }

    public void setCodigoTipoOs(Long codigoTipoOs) {
        this.codigoTipoOs = codigoTipoOs;
    }
    
    
    
}
