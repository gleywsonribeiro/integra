/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hmue.integra.controller;

import br.com.hmue.integra.factory.ConnectionFactory;
import br.com.hmue.integra.jsf.util.JsfUtil;
import br.com.hmue.integra.modelo.OrdemServico;
import br.com.hmue.integra.service.ExecutorRelatorio;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Gleywson
 */
@Named(value = "emissorRelatorioController")
@RequestScoped
public class EmissorRelatorioController {

    private OrdemServico os;
    private StreamedContent file;
    
    @Inject
    private FacesContext facesContext;

    @Inject
    private HttpServletResponse response;

    public EmissorRelatorioController() {
        try {
            InputStream stream = this.getClass().getResourceAsStream("/relatorios/blank.pdf");
        file = new DefaultStreamedContent(stream, "application/pdf","os_em_branco.pdf");
        } catch (Exception e) {
            System.err.println("Erro ao abrir o arquivo");
        }
    }
    
    

    public void emitir() {
        try {
            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("cd_os", this.os.getOs());

            ExecutorRelatorio executor = new ExecutorRelatorio("/relatorios/os_template.jasper",
                    this.response, parametros, "os.pdf");

            Connection connection = ConnectionFactory.createConnectionToOracle();

            executor.execute(connection);

            if (executor.isRelatorioGerado()) {
                facesContext.responseComplete();
            } else {
                JsfUtil.addErrorMessage("A execução do relatório não retornou dados.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmissorRelatorioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public OrdemServico getOs() {
        return os;
    }

    public void setOs(OrdemServico os) {
        this.os = os;
    }

    public StreamedContent getFile() {
        return file;
    }

    
}
