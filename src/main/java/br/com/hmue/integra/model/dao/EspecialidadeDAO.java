/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hmue.integra.model.dao;

import br.com.hmue.integra.factory.SingleConnection;
import br.com.hmue.integra.model.Especialidade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Gleywson
 */
public class EspecialidadeDAO {

    private Connection connection;

    public EspecialidadeDAO() {
        connection = SingleConnection.getConnection();
    }

    public void atualizar(Especialidade e) {
        try {

            String sql = "update manu_espec set ds_espec = ? where cd_espec = " + e.getCodigo();

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, e.getDescricao()); // Passando o
            statement.execute(); // Executando a atualização
            connection.commit(); // Comitando/Gravando no banco de dados

        } catch (SQLException exp) {
            try {
                connection.rollback();// Reverte caso dê algum erro
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            exp.printStackTrace();
        }
    }

    public Especialidade buscar(Long codigo) throws Exception {
        Especialidade retorno = new Especialidade();
        String sql = "select * from manu_espec where cd_espec = " + codigo; // Sql
        // recebendo
        // o
        // parâmetro

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultado = statement.executeQuery();
        
        while (resultado.next()) {
            retorno.setCodigo(resultado.getLong("cd_espec"));
            retorno.setDescricao(resultado.getString("ds_espec"));
        }
        return retorno;
    }

}
