/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hmue.integra.modelo.repositorio;

import br.com.hmue.integra.factory.ConnectionFactory;
import br.com.hmue.integra.modelo.AvaliacaoOS;
import br.com.hmue.integra.modelo.Producao;
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
public class ProducaoDAO implements Serializable {

    public List<Producao> getServicosConcluidos() {
        Connection connection = null;
        List<Producao> lista = new ArrayList<Producao>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.createConnectionToOracle();
            statement = connection.prepareStatement("SELECT * FROM PRODUCAO_INTEGRA P ORDER BY (NAO_AVALIADAS + AVALIADAS) DESC");
            rs = statement.executeQuery();

            while (rs.next()) {
                Producao producao = new Producao();

                producao.setFuncionario(rs.getString("funcionario"));
                producao.setAvaliadas(rs.getInt("avaliadas"));
                producao.setNaoAvaliadas(rs.getInt("nao_avaliadas"));

                lista.add(producao);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProducaoDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(ProducaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }

    public List<Producao> getTempoPorTecnico() {
        Connection connection = null;
        List<Producao> lista = new ArrayList<Producao>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.createConnectionToOracle();
            statement = connection.prepareStatement("SELECT * FROM PRODUCAO_TEMPORAL_INTEGRA");
            rs = statement.executeQuery();

            while (rs.next()) {
                Producao producao = new Producao();

                producao.setFuncionario(rs.getString("funcionario"));
                producao.setTempo(rs.getFloat("tempo"));
               
                lista.add(producao);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProducaoDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(ProducaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }

}
