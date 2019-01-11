/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hmue.integra.model.dao;

import br.com.hmue.integra.factory.SingleConnection;
import br.com.hmue.integra.model.OS;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gleywson
 */
public class OSDao {

    private Connection connection;

    public OSDao() {
        connection = SingleConnection.getConnection();
    }

    public OS buscar(Long codigo) {
        OS os = new OS();
        try {
            String sql = "select * from solicitacao_os where cd_os = " + codigo;

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                os.setCodigo(rs.getLong("cd_os"));
                os.setDescricao(rs.getString("ds_servico"));
                //os.setPrincipal(Boolean.TRUE);
                
//                Date d = rs.getDate("dt_pedido");
//                Timestamp t = rs.getTimestamp("hr_pedido");
//                DateFormat format = DateFormat.getDateInstance();
//
//                os.setDataPedido(format.parse(d.toString() + t.toString()));
                
//                d = rs.getDate("dt_termino");
//                t = rs.getTimestamp("hr_termino");
//                os.setDataTermino(format.parse(d.toString() + t.toString()));
                
                
//                os.setClassificacao(rs.getString("tp_classificacao"));
//                os.setEspecialidade(rs.getLong("cd_especialidade"));
//                os.setSituacao(rs.getString("tp_situacao"));
                 os.setTipoOS(rs.getLong("cd_tipo_os"));
                os.setMotivo(rs.getLong("cd_mot_serv"));
                os.setSetor(rs.getLong("cd_setor"));
                os.setLocalidade(rs.getLong("cd_localidade"));
//                os.setOficina(rs.getLong("cd_oficina"));
//                os.setSolicitante(rs.getString("nm_solicitante"));
//                os.setRamal(rs.getString("ds_ramal"));
                os.setPrioridade(rs.getString("tp_prioridade"));
                os.setResponsavel(rs.getString("cd_responsavel"));
                os.setObservacao(rs.getString("ds_observacao"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return os;
    }
    
    public void atualizar(OS o) {
        try {

            String sql = "update solicitacao_os set "
                    + "cd_espec=?," //especialidade
                    + "cd_tipo_os=?," //tipo de os
                    + "cd_mot_serv=?," //motivo do servico
                    + "cd_setor=?," //setor 
                    + "cd_localidade=?," //localidade
                    + "tp_prioridade=?,"  //prioridade
                    + "cd_responsavel=?," //
                    + "ds_servico=?" //
                    + " where cd_os = " + o.getCodigo();

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setLong(1, 30);
            statement.setLong(2, o.getTipoOS());
            statement.setLong(3, o.getMotivo());
            statement.setLong(4, o.getSetor());
            statement.setLong(5, o.getLocalidade());
            statement.setString(6, o.getPrioridade());
            statement.setString(7, o.getResponsavel());
            statement.setString(8, o.getDescricao());
            
            statement.execute(); 
            connection.commit(); 

        } catch (SQLException exp) {
            try {
                exp.printStackTrace();
                connection.rollback();// Reverte caso dê algum erro
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            exp.printStackTrace();
        }
    }

}
