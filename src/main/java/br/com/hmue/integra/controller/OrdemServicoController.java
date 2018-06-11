/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hmue.integra.controller;

import br.com.hmue.integra.modelo.repositorio.DAO;
import br.com.hmue.integra.modelo.OrdemServico;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
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
    private List<OrdemServico> outrosServicos;

    public OrdemServicoController() {
        this.os = new OrdemServico();
    }

    public OrdemServico getOs() {
        return os;
    }

    public void setOs(OrdemServico os) {
        this.os = os;
    }

    public List<OrdemServico> getOutrosServicos() {
        outrosServicos = dao.aguardandoAtencao();
        return outrosServicos;
    }

    public List<OrdemServico> getServicos() {
        servicos = dao.getOSsPendentes();
        return servicos;
    }

    public List<OrdemServico> getServidosDoDia() {
        servicos = dao.getServicosDoDia();
        return servicos;
    }

    public List<OrdemServico> getServicosPreventivos() {
        servicos = dao.getServicosPreventivos();
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

    public void postProcessXLS(Object document) {
        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow header = sheet.getRow(0);

        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        for (int i = 0; i < header.getPhysicalNumberOfCells(); i++) {
            HSSFCell cell = header.getCell(i);

            cell.setCellStyle(cellStyle);
        }
    }

    public void imprimirOS() {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("cd_os", this.os.getOs());
    }

    public void listener() {
//        System.out.println("ok");
    }
}
