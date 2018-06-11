/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hmue.integra.modelo.repositorio;

import br.com.hmue.integra.factory.ConnectionFactory;
import br.com.hmue.integra.modelo.AvaliacaoOS;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gleywson
 */
public class AvaliacaoOsDAO implements Serializable {
    
    public List<AvaliacaoOS> getAvaliacoesPendentes() {
        Connection connection = null;
        List<AvaliacaoOS> lista = new ArrayList<AvaliacaoOS>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.createConnectionToOracle();
            statement = connection.prepareStatement("select * from avaliacao_integra");
            rs = statement.executeQuery();
            
            while (rs.next()) {
                AvaliacaoOS avaliacao = new AvaliacaoOS();
                
                avaliacao.setCodigo(rs.getInt("CD_OS"));
                avaliacao.setDataPedido(rs.getTimestamp("DT_PEDIDO"));
                avaliacao.setRamal(rs.getString("DS_RAMAL"));
                avaliacao.setServico(rs.getString("DS_SERVICO"));
                avaliacao.setSetor(rs.getString("NM_SETOR"));
                avaliacao.setSolicitante(rs.getString("NM_SOLICITANTE"));
                avaliacao.setStatus(rs.getString("STATUS"));
                
                lista.add(avaliacao);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AvaliacaoOsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                 if (statement != null) {

                    statement.close();
                }

                if (connection != null) {
                    connection.close();
                }

                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(AvaliacaoOsDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }

    
    
}
