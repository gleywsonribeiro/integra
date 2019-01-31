/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hmue.integra.controller;

import br.com.hmue.integra.jsf.util.JsfUtil;
import br.com.hmue.integra.model.MotivoServico;
import br.com.hmue.integra.model.OS;
import br.com.hmue.integra.model.TipoOS;
import br.com.hmue.integra.model.dao.OSDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Gleywson
 */
@Named(value = "cadastroOSController")
@ViewScoped
public class CadastroOSController implements Serializable {

    private OS os;
    private OSDao dao;
    private List<TipoOS> listaTipos;
    private List<MotivoServico> motivos;

    private String codigo;

    @PostConstruct
    public void init() {
        dao = new OSDao();
        listaTipos = dao.getTiposOS();
        codigo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codigo");
        os = dao.buscar(Long.parseLong(codigo));
        motivos = dao.getMotivos(os.getTipoOS());
    }

    public OS getOs() {
        return os;
    }

    public void setOs(OS os) {
        this.os = os;
    }

    public void salvar() {
        dao.atualizar(os);
        JsfUtil.addMessage("Salvo com sucesso!");
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public List<TipoOS> getListaTipos() {
        return listaTipos;
    }

    public void setListaTipos(List<TipoOS> listaTipos) {
        this.listaTipos = listaTipos;
    }

    public List<MotivoServico> getMotivos() {
        return motivos;
    }

    public void setMotivos(List<MotivoServico> motivos) {
        this.motivos = motivos;
    }

    public void atualizarMotivos() {
        motivos = dao.getMotivos(os.getTipoOS());
    }
    
    public String getSetor() {
        return dao.getSetor(os.getSetor());
    }
    
    public String getLocalidade() {
        return dao.getLocalidade(os.getLocalidade());
    }
}
