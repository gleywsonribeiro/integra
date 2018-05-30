/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hmue.integra.modelo.repositorio;

import br.com.hmue.integra.factory.ConnectionFactory;
import br.com.hmue.integra.modelo.ItemOs;
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
public class ItemOsDAO {

    private Connection connection;

    public ItemOsDAO() {
        this.connection = ConnectionFactory.createConnectionToOracle();
    }

    public List<ItemOs> getItens() throws SQLException {
        List<ItemOs> itens = new ArrayList<ItemOs>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = this.connection.prepareStatement("SELECT * FROM ITSOLICITACAO_OS IT WHERE IT.CD_ITSOLICITACAO_OS IN (9024, 9023)");
            rs = statement.executeQuery();
            while (rs.next()) {
                ItemOs item = new ItemOs();
                item.setId(rs.getInt("CD_ITSOLICITACAO_OS"));
                item.setHoraInicial(rs.getTimestamp("HR_INICIO"));
                item.setHoraInicial(rs.getTimestamp("HR_FINAL"));
                itens.add(item);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ItemOsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            rs.close();
            statement.close();
            this.connection.close();
        }
        return itens;
    }

}
