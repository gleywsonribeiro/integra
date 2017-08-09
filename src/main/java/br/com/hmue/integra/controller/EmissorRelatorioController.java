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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author Gleywson
 */
@Named(value = "emissorRelatorioController")
@RequestScoped
public class EmissorRelatorioController {

    private OrdemServico os;
    
    @Inject
    private FacesContext facesContext;

    @Inject
    private HttpServletResponse response;

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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmissorRelatorioController.class.getName()).log(Level.SEVERE, null, ex);
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

    
}
