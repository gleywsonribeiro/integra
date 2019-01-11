/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hmue.integra.model;

import java.util.Date;

/**
 *
 * @author Gleywson
 */
public class ItemOS {
    private Long codigo; //cd_itsolicitacao_os
    private Long funcionario; //cd_func
    private Date dataInicial; //hr_inicio
    private Date dataFinal; //hr_final
    private Long servico; //cd_servico
    private Integer tempoHora; //vl_tempo_gasto ->calculo dinamico
    private Integer tempoMinuto; //vl_tempo_gasto_min -> calculo dinamico
    private String descricao; //ds_servico

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Long getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Long funcionario) {
        this.funcionario = funcionario;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Long getServico() {
        return servico;
    }

    public void setServico(Long servico) {
        this.servico = servico;
    }

    public Integer getTempoHora() {
        return tempoHora;
    }

    public void setTempoHora(Integer tempoHora) {
        this.tempoHora = tempoHora;
    }

    public Integer getTempoMinuto() {
        return tempoMinuto;
    }

    public void setTempoMinuto(Integer tempoMinuto) {
        this.tempoMinuto = tempoMinuto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
}
