/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hmue.integra.model.dao;

import br.com.hmue.integra.factory.SingleConnection;
import br.com.hmue.integra.model.MotivoServico;
import br.com.hmue.integra.model.OS;
import br.com.hmue.integra.model.TipoOS;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gleywson
 */
public class OSDao implements Serializable {

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
                os.setSolicitante(rs.getString("nm_solicitante"));
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
                    + "tp_prioridade=?," //prioridade
                    + "cd_responsavel=?," //
                    + "ds_servico=?" //
                    + " where cd_os = " + o.getCodigo();

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setLong(1, 30L); //especialidade
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

    public List<TipoOS> getTiposOS() {
        List<TipoOS> lista = new ArrayList<TipoOS>();

        String sql = "select tp.cd_tipo_os, tp.ds_tipo_os from tipo_os tp";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                TipoOS tipoOS = new TipoOS();

                tipoOS.setCodigo(resultSet.getLong("cd_tipo_os"));
                tipoOS.setDescricao(resultSet.getString("ds_tipo_os"));

                lista.add(tipoOS);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public List<MotivoServico> getMotivos(Long codigoTipoOs) {
        List<MotivoServico> lista = new ArrayList<MotivoServico>();

        String sql = "select s.cd_mot_serv, s.ds_mot_serv, s.cd_tipo_os from mot_serv s where s.cd_tipo_os = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, codigoTipoOs);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                MotivoServico motivo = new MotivoServico();

                motivo.setCodigo(resultSet.getLong("cd_mot_serv"));
                motivo.setDescricao(resultSet.getString("ds_mot_serv"));
                motivo.setCodigoTipoOs(resultSet.getLong("cd_tipo_os"));

                lista.add(motivo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public String getSetor(Long id) {
        String sql = "select nm_setor from setor where cd_setor = ?";
        String setor = "";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                setor = resultSet.getString("nm_setor");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return setor;
    }

    public String getLocalidade(Long id) {
        String sql = "select ds_localidade from localidade where cd_localidade = ?";
        String localidade = "";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                localidade = resultSet.getString("ds_localidade");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return localidade;
    }
}
