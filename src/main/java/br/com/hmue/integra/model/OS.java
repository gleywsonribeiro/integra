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
public class OS {
    private Long codigo; //cd_os
    private String descricao; //ds_servico
    private String principal; //sn_principal
    private Date dataPedido; //dt_pedido e hr_pedido
    private Date dataTermino; //dt_termino e hr_termino
    private String classificacao; // tp_classifocacao padrão = P
    private Long especialidade; // cd_especialidade padrão = 30
    private String situacao; //tp_situacao
    private Long tipoOS; //tp_os
    private Long motivo; //cd_mot_serv
    private Long setor; //cd_setor
    private Long localidade; //cd_localidade
    private Long oficina; //cd_oficina
    private String solicitante; //nm_solicitante
    private String ramal; // ds_ramal
    private String prioridade; //tp_prioridade
    private String responsavel; //cd_responsavel
    private String observacao; //ds_observacao

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

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Date getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public Long getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Long especialidade) {
        this.especialidade = especialidade;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Long getTipoOS() {
        return tipoOS;
    }

    public void setTipoOS(Long tipoOS) {
        this.tipoOS = tipoOS;
    }

    public Long getMotivo() {
        return motivo;
    }

    public void setMotivo(Long motivo) {
        this.motivo = motivo;
    }

    public Long getSetor() {
        return setor;
    }

    public void setSetor(Long setor) {
        this.setor = setor;
    }

    public Long getLocalidade() {
        return localidade;
    }

    public void setLocalidade(Long localidade) {
        this.localidade = localidade;
    }

    public Long getOficina() {
        return oficina;
    }

    public void setOficina(Long oficina) {
        this.oficina = oficina;
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

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

   

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (this.codigo != null ? this.codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OS other = (OS) obj;
        if (this.codigo != other.codigo && (this.codigo == null || !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }
    
    
    
}
