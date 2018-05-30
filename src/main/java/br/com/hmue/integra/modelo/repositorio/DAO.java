/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hmue.integra.modelo.repositorio;

import br.com.hmue.integra.factory.ConnectionFactory;
import br.com.hmue.integra.modelo.FuncionarioOS;
import br.com.hmue.integra.modelo.OrdemServico;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gleywson
 */
public class DAO implements Serializable {

    public List<OrdemServico> getOSsPendentes() {
        String sql = "SELECT CD_OS OS,\n"
                + "       LPAD(DIA, 2, 0) || ' dia(s) ' ||\n"
                + "       LPAD(HORA, 2, 0) || ':' ||\n"
                + "       LPAD(MINUTO, 2, 0) || ':' || \n"
                + "       LPAD(SEGUNDO, 2, 0) TEMPO_ABERTURA,\n"
                + "\n"
                + "       \n"
                + "       DT_PEDIDO,\n"
                + "       DS_SERVICO SERVICO,\n"
                + "       NOME,\n"
                + "       NM_SETOR SETOR,\n"
                + "       DS_LOCALIDADE LOCALIDADE,\n"
                + "       DS_RAMAL RAMAL,\n"
                + "       DS_TIPO_OS TP_OS,\n"
                + "       NM_USUARIO USUARIO,\n"
                + "       STATUS,\n"
                + "       RESP TEC_RESPONSAVEL\n"
                + "  FROM (\n"
                + "        \n"
                + "        SELECT TRUNC(TEMPO) DIA,\n"
                + "                TRUNC(((86400 * (TEMPO)) / 60) / 60) - 24 * (TRUNC((((86400 * (TEMPO)) / 60) / 60) / 24)) HORA,\n"
                + "                TRUNC(TRUNC((86400 * (TEMPO) / 60) - 60 * (TRUNC(((86400 * (TEMPO)) / 60) / 60)),'09')) MINUTO,\n"
                + "                TRUNC(TRUNC((86400 * TEMPO) - 60 * (TRUNC((86400 * TEMPO) / 60)))) SEGUNDO,\n"
                + "                DS_SERVICO,\n"
                + "                DS_TIPO_OS,\n"
                + "                CD_OS,\n"
                + "                DT_PEDIDO,\n"
                + "                NOME,\n"
                + "                NM_SETOR,\n"
                + "                DS_LOCALIDADE,\n"
                + "                DS_RAMAL,\n"
                + "                NM_USUARIO,\n"
                + "                RESP,\n"
                + "                DECODE(TP_SITUACAO, 'S','SOLICITADA',\n"
                + "                                    'A','ABERTA',\n"
                + "                                    'C','CONCLUÍDA',\n"
                + "                                    'N','NÃO ATENDIDA',\n"
                + "                                    'M','AGUARDANDO MATERIAL',\n"
                + "                                    'E','CONSERTO EXTERNO',\n"
                + "                                    'F','A AGENDAR',\n"
                + "                                    'L', 'AGUARDANDO LIBERAÇÃO DO SETOR'\n"
                + "                                    ) STATUS\n"
                + "          FROM (\n"
                + "                 \n"
                + "                 SELECT OS.CD_OS,\n"
                + "                         OS.DT_PEDIDO,\n"
                + "                         SYSDATE DT_ATUAL,\n"
                + "                         (SYSDATE - OS.DT_PEDIDO) TEMPO,\n"
                + "                         OS.DS_SERVICO,\n"
                + "                         SUBSTR(OS.NM_SOLICITANTE,\n"
                + "                                1,\n"
                + "                                INSTR(OS.NM_SOLICITANTE, ' ') - 1) NOME,\n"
                + "                         S.NM_SETOR,\n"
                + "                         L.DS_LOCALIDADE,\n"
                + "                         OS.DS_RAMAL,\n"
                + "                         TPOS.DS_TIPO_OS,\n"
                + "                         OS.NM_USUARIO,\n"
                + "                         U.NM_USUARIO RESP,\n"
                + "                         OS.TP_SITUACAO\n"
                + "                   FROM SOLICITACAO_OS OS, TIPO_OS TPOS, SETOR S, LOCALIDADE L, DBASGU.USUARIOS U\n"
                + "                  WHERE S.CD_SETOR = OS.CD_SETOR\n"
                + "                    AND TPOS.CD_TIPO_OS = OS.CD_TIPO_OS\n"
                + "                    AND L.CD_LOCALIDADE(+) = OS.CD_LOCALIDADE\n"
                + "                    AND U.CD_USUARIO(+) = OS.CD_RESPONSAVEL\n"
                + "                    AND TPOS.CD_TIPO_OS <> 2\n"
                + "                    AND OS.CD_OFICINA = 13\n"
                + "                    AND OS.DT_PEDIDO > '01/07/2017'\n"
                + "                    AND OS.TP_SITUACAO IN ('A','S')\n"
                + "                  ORDER BY OS.CD_OS\n"
                + "                 \n"
                + "                 )  \n"
                + "        )";

        Connection connection = null;
        PreparedStatement pstm = null;
        //Classe que vai recuperar os dados do banco de dados
        ResultSet resultSet = null;

        List<OrdemServico> lista = new ArrayList<OrdemServico>();

        try {
            //Cria uma conexão com o banco
            connection = ConnectionFactory.createConnectionToOracle();

            //Cria um PreparedStatment, classe usada para executar a query
            pstm = connection.prepareStatement(sql);
            resultSet = pstm.executeQuery();

            while (resultSet.next()) {
                OrdemServico os = new OrdemServico();

                os.setOs(resultSet.getInt("os"));
                os.setTempoAbertura(resultSet.getString("tempo_abertura"));
//                os.setDtPedido(resultSet.getString("dt_pedido"));
                os.setDtPedido(resultSet.getTimestamp("dt_pedido"));
                os.setServico(resultSet.getString("servico"));
                os.setNome(resultSet.getString("nome"));
                os.setSetor(resultSet.getString("setor"));
                os.setLocalidade(resultSet.getString("localidade"));
                os.setRamal(resultSet.getString("ramal"));
                os.setTpOs(resultSet.getString("tp_os"));
                os.setUsuario(resultSet.getString("usuario"));
                os.setStatus(resultSet.getString("status"));
                os.setTecResponsavel(resultSet.getString("tec_responsavel"));

                lista.add(os);
            }

            //Executa a sql para inserção dos dados
            //pstm.execute();
        } catch (SQLException e) {

        } finally {
            //Fecha as conexões
            try {
                if (pstm != null) {

                    pstm.close();
                }

                if (connection != null) {
                    connection.close();
                }

                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
            }
        }
        return lista;
    }

    public List<OrdemServico> getServicosPreventivos() {
        String sql = "SELECT CD_OS OS,\n"
                + "                      LPAD(DIA, 2, 0) || ' dia(s) ' ||\n"
                + "                       LPAD(HORA, 2, 0) || ':' ||\n"
                + "                      LPAD(MINUTO, 2, 0) || ':' || \n"
                + "                       LPAD(SEGUNDO, 2, 0) TEMPO_ABERTURA,\n"
                + "                \n"
                + "                       \n"
                + "                       DT_PEDIDO,\n"
                + "                       DS_SERVICO SERVICO,\n"
                + "                       NOME,\n"
                + "                       NM_SETOR SETOR,\n"
                + "                       DS_LOCALIDADE LOCALIDADE,\n"
                + "                       DS_RAMAL RAMAL,\n"
                + "                       DS_TIPO_OS TP_OS,\n"
                + "                       NM_USUARIO USUARIO,\n"
                + "                       STATUS,\n"
                + "                       RESP TEC_RESPONSAVEL\n"
                + "                  FROM (\n"
                + "                        \n"
                + "                        SELECT TRUNC(TEMPO) DIA,\n"
                + "                                TRUNC(((86400 * (TEMPO)) / 60) / 60) - 24 * (TRUNC((((86400 * (TEMPO)) / 60) / 60) / 24)) HORA,\n"
                + "                                TRUNC(TRUNC((86400 * (TEMPO) / 60) - 60 * (TRUNC(((86400 * (TEMPO)) / 60) / 60)),'09')) MINUTO,\n"
                + "                                TRUNC(TRUNC((86400 * TEMPO) - 60 * (TRUNC((86400 * TEMPO) / 60)))) SEGUNDO,\n"
                + "                                DS_SERVICO,\n"
                + "                                DS_TIPO_OS,\n"
                + "                                CD_OS,\n"
                + "                                DT_PEDIDO,\n"
                + "                                NOME,\n"
                + "                                NM_SETOR,\n"
                + "                                DS_LOCALIDADE,\n"
                + "                                DS_RAMAL,\n"
                + "                                NM_USUARIO,\n"
                + "                                RESP,\n"
                + "                                DECODE(TP_SITUACAO, 'S','SOLICITADA',\n"
                + "                                                    'A','ABERTA',\n"
                + "                                                    'C','CONCLUÍDA',\n"
                + "                                                    'N','NÃO ATENDIDA',\n"
                + "                                                    'M','AGUARDANDO MATERIAL',\n"
                + "                                                    'E','CONSERTO EXTERNO',\n"
                + "                                                    'F','A AGENDAR',\n"
                + "                                                    'L', 'AGUARDANDO LIBERAÇÃO DO SETOR'\n"
                + "                                                    ) STATUS\n"
                + "                          FROM (\n"
                + "                                 \n"
                + "                                 SELECT OS.CD_OS,\n"
                + "                                         OS.DT_PEDIDO,\n"
                + "                                         SYSDATE DT_ATUAL,\n"
                + "                                         (SYSDATE - OS.DT_PEDIDO) TEMPO,\n"
                + "                                         OS.DS_SERVICO,\n"
                + "                                         SUBSTR(OS.NM_SOLICITANTE,\n"
                + "                                                1,\n"
                + "                                                INSTR(OS.NM_SOLICITANTE, ' ') - 1) NOME,\n"
                + "                                         S.NM_SETOR,\n"
                + "                                         L.DS_LOCALIDADE,\n"
                + "                                         OS.DS_RAMAL,\n"
                + "                                         T.DS_TIPO_OS,\n"
                + "                                         OS.NM_USUARIO,\n"
                + "                                         U.NM_USUARIO RESP,\n"
                + "                                         OS.TP_SITUACAO\n"
                + "                                   FROM SOLICITACAO_OS OS, SETOR S, LOCALIDADE L, DBASGU.USUARIOS U, TIPO_OS T\n"
                + "                                  WHERE S.CD_SETOR = OS.CD_SETOR\n"
                + "                                    AND OS.CD_TIPO_OS = T.CD_TIPO_OS\n"
                + "                                    AND T.DS_TIPO_OS = 'PREVENTIVA'\n"
                + "                                    AND L.CD_LOCALIDADE(+) = OS.CD_LOCALIDADE\n"
                + "                                    AND U.CD_USUARIO(+) = OS.CD_RESPONSAVEL\n"
                + "                                    AND OS.CD_OFICINA = 13\n"
                + "                                    AND OS.DT_PEDIDO > '01/07/2017'\n"
                + "                                    AND OS.TP_SITUACAO <> 'C'\n"
                + "                                  ORDER BY OS.CD_OS\n"
                + "                                 \n"
                + "                                 )  \n"
                + "                     )";

        Connection connection = null;
        PreparedStatement pstm = null;
        //Classe que vai recuperar os dados do banco de dados
        ResultSet resultSet = null;

        List<OrdemServico> lista = new ArrayList<OrdemServico>();

        try {
            //Cria uma conexão com o banco
            connection = ConnectionFactory.createConnectionToOracle();

            //Cria um PreparedStatment, classe usada para executar a query
            pstm = connection.prepareStatement(sql);
            resultSet = pstm.executeQuery();

            while (resultSet.next()) {
                OrdemServico os = new OrdemServico();

                os.setOs(resultSet.getInt("os"));
                os.setTempoAbertura(resultSet.getString("tempo_abertura"));
                os.setDtPedido(resultSet.getTimestamp("dt_pedido"));
                os.setServico(resultSet.getString("servico"));
                os.setNome(resultSet.getString("nome"));
                os.setSetor(resultSet.getString("setor"));
                os.setLocalidade(resultSet.getString("localidade"));
                os.setRamal(resultSet.getString("ramal"));
                os.setTpOs(resultSet.getString("tp_os"));
                os.setUsuario(resultSet.getString("usuario"));
                os.setStatus(resultSet.getString("status"));
                os.setTecResponsavel(resultSet.getString("tec_responsavel"));

                lista.add(os);
            }

            //Executa a sql para inserção dos dados
            //pstm.execute();
        } catch (SQLException e) {

        } finally {
            //Fecha as conexões
            try {
                if (pstm != null) {

                    pstm.close();
                }

                if (connection != null) {
                    connection.close();
                }

                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
            }
        }
        return lista;
    }

    public List<OrdemServico> getServicosDoDia() {
        String sql = "SELECT CD_OS OS,\n"
                + "                      LPAD(DIA, 2, 0) || ' dia(s) ' ||\n"
                + "                       LPAD(HORA, 2, 0) || ':' ||\n"
                + "                      LPAD(MINUTO, 2, 0) || ':' || \n"
                + "                       LPAD(SEGUNDO, 2, 0) TEMPO_ABERTURA,\n"
                + "                \n"
                + "                       \n"
                + "                       DT_PEDIDO,\n"
                + "                       DS_SERVICO SERVICO,\n"
                + "                       NOME,\n"
                + "                       NM_SETOR SETOR,\n"
                + "                       DS_LOCALIDADE LOCALIDADE,\n"
                + "                       DS_RAMAL RAMAL,\n"
                + "                       DS_TIPO_OS TP_OS,\n"
                + "                       NM_USUARIO USUARIO,\n"
                + "                       STATUS,\n"
                + "                       RESP TEC_RESPONSAVEL\n"
                + "                  FROM (\n"
                + "                        \n"
                + "                        SELECT TRUNC(TEMPO) DIA,\n"
                + "                                TRUNC(((86400 * (TEMPO)) / 60) / 60) - 24 * (TRUNC((((86400 * (TEMPO)) / 60) / 60) / 24)) HORA,\n"
                + "                                TRUNC(TRUNC((86400 * (TEMPO) / 60) - 60 * (TRUNC(((86400 * (TEMPO)) / 60) / 60)),'09')) MINUTO,\n"
                + "                                TRUNC(TRUNC((86400 * TEMPO) - 60 * (TRUNC((86400 * TEMPO) / 60)))) SEGUNDO,\n"
                + "                                DS_SERVICO,\n"
                + "                                DS_TIPO_OS,\n"
                + "                                CD_OS,\n"
                + "                                DT_PEDIDO,\n"
                + "                                NOME,\n"
                + "                                NM_SETOR,\n"
                + "                                DS_LOCALIDADE,\n"
                + "                                DS_RAMAL,\n"
                + "                                NM_USUARIO,\n"
                + "                                RESP,\n"
                + "                                DECODE(TP_SITUACAO, 'S','SOLICITADA',\n"
                + "                                                    'A','ABERTA',\n"
                + "                                                    'C','CONCLUÍDA',\n"
                + "                                                    'N','NÃO ATENDIDA',\n"
                + "                                                    'M','AGUARDANDO MATERIAL',\n"
                + "                                                    'E','CONSERTO EXTERNO',\n"
                + "                                                    'F','A AGENDAR',\n"
                + "                                                    'L', 'AGUARDANDO LIBERAÇÃO DO SETOR'\n"
                + "                                                    ) STATUS\n"
                + "                          FROM (\n"
                + "                                 \n"
                + "                                 SELECT OS.CD_OS,\n"
                + "                                         OS.DT_PEDIDO,\n"
                + "                                         SYSDATE DT_ATUAL,\n"
                + "                                         (SYSDATE - OS.DT_PEDIDO) TEMPO,\n"
                + "                                         OS.DS_SERVICO,\n"
                + "                                         SUBSTR(OS.NM_SOLICITANTE,\n"
                + "                                                1,\n"
                + "                                                INSTR(OS.NM_SOLICITANTE, ' ') - 1) NOME,\n"
                + "                                         S.NM_SETOR,\n"
                + "                                         L.DS_LOCALIDADE,\n"
                + "                                         OS.DS_RAMAL,\n"
                + "                                         T.DS_TIPO_OS,\n"
                + "                                         OS.NM_USUARIO,\n"
                + "                                         U.NM_USUARIO RESP,\n"
                + "                                         OS.TP_SITUACAO\n"
                + "                                   FROM SOLICITACAO_OS OS, SETOR S, LOCALIDADE L, DBASGU.USUARIOS U, TIPO_OS T\n"
                + "                                  WHERE S.CD_SETOR = OS.CD_SETOR\n"
                + "                                    AND OS.CD_TIPO_OS = T.CD_TIPO_OS\n"
                + "                                    AND OS.TP_SITUACAO IN ('A','S')\n"
                + "                                    AND L.CD_LOCALIDADE(+) = OS.CD_LOCALIDADE\n"
                + "                                    AND U.CD_USUARIO(+) = OS.CD_RESPONSAVEL\n"
                + "                                    AND OS.CD_OFICINA = 13\n"
                + "                                    AND T.CD_TIPO_OS <> 2\n"
                + "                                    AND TRUNC(OS.DT_PEDIDO) = TRUNC(SYSDATE) \n"
                + "                                  ORDER BY OS.CD_OS \n"
                + "                                 \n"
                + "                                 )  \n"
                + "                     )\n"
                + "                     \n"
                + "         ";

        Connection connection = null;
        PreparedStatement pstm = null;
        //Classe que vai recuperar os dados do banco de dados
        ResultSet resultSet = null;

        List<OrdemServico> lista = new ArrayList<OrdemServico>();

        try {
            //Cria uma conexão com o banco
            connection = ConnectionFactory.createConnectionToOracle();

            //Cria um PreparedStatment, classe usada para executar a query
            pstm = connection.prepareStatement(sql);
            resultSet = pstm.executeQuery();

            while (resultSet.next()) {
                OrdemServico os = new OrdemServico();

                os.setOs(resultSet.getInt("os"));
                os.setTempoAbertura(resultSet.getString("tempo_abertura"));
                os.setDtPedido(resultSet.getTimestamp("dt_pedido"));
                os.setServico(resultSet.getString("servico"));
                os.setNome(resultSet.getString("nome"));
                os.setSetor(resultSet.getString("setor"));
                os.setLocalidade(resultSet.getString("localidade"));
                os.setRamal(resultSet.getString("ramal"));
                os.setTpOs(resultSet.getString("tp_os"));
                os.setUsuario(resultSet.getString("usuario"));
                os.setStatus(resultSet.getString("status"));
                os.setTecResponsavel(resultSet.getString("tec_responsavel"));

                lista.add(os);
            }

            //Executa a sql para inserção dos dados
            //pstm.execute();
        } catch (SQLException e) {

        } finally {
            //Fecha as conexões
            try {
                if (pstm != null) {

                    pstm.close();
                }

                if (connection != null) {
                    connection.close();
                }

                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
            }
        }
        return lista;
    }

    public List<FuncionarioOS> OSPorFuncionario() {
        String sql = "SELECT MES,\n"
                + "       CARGO,\n"
                + "       NOME,\n"
                + "       COUNT(NOME) QTD\n"
                + "  FROM (SELECT SOLICITACAO_OS.CD_OS,\n"
                + "               TO_CHAR(SOLICITACAO_OS.DT_PEDIDO, 'mm/yyyy') MES,\n"
                + "               SOLICITACAO_OS.DS_SERVICO,\n"
                + "               SOLICITACAO_OS.DS_OBSERVACAO,\n"
                + "               SOLICITACAO_OS.CD_SETOR,\n"
                + "               SETOR.NM_SETOR,\n"
                + "               ITSOLICITACAO_OS.CD_FUNC,\n"
                + "               FUNCIONARIO.NM_FUNC NOME,\n"
                + "               TIPO_CARGO_PLANO.DS_CARGO CARGO\n"
                + "          FROM DBAMV.SOLICITACAO_OS,\n"
                + "               DBAMV.ITSOLICITACAO_OS,\n"
                + "               DBAMV.FUNCIONARIO,\n"
                + "               DBAMV.SETOR,\n"
                + "               DBAMV.TIPO_CARGO_PLANO\n"
                + "         WHERE SOLICITACAO_OS.CD_OS = ITSOLICITACAO_OS.CD_OS\n"
                + "           AND FUNCIONARIO.CD_FUNC = ITSOLICITACAO_OS.CD_FUNC\n"
                + "           AND SETOR.CD_SETOR = SOLICITACAO_OS.CD_SETOR\n"
                + "           AND FUNCIONARIO.CD_TIPO_CARGO_PLANO(+) = TIPO_CARGO_PLANO.CD_TIPO_CARGO_PLANO\n"
                + "           AND TO_CHAR(SOLICITACAO_OS.DT_PEDIDO, 'mm/yyyy') = to_char(sysdate, 'mm/yyyy')\n"
                + "           AND SOLICITACAO_OS.CD_OFICINA = 13\n"
                + "           AND SOLICITACAO_OS.TP_SITUACAO = 'C'\n"
                + "          ORDER BY SOLICITACAO_OS.CD_OS)\n"
                + "GROUP BY MES,CARGO, NOME\n"
                + "ORDER BY cargo,QTD DESC\n"
                + "";

        Connection connection = null;
        PreparedStatement pstm = null;
        //Classe que vai recuperar os dados do banco de dados
        ResultSet resultSet = null;

        List<FuncionarioOS> lista = new ArrayList<FuncionarioOS>();

        try {
            //Cria uma conexão com o banco
            connection = ConnectionFactory.createConnectionToOracle();

            //Cria um PreparedStatment, classe usada para executar a query
            pstm = connection.prepareStatement(sql);
            resultSet = pstm.executeQuery();

            while (resultSet.next()) {
                FuncionarioOS funcionario = new FuncionarioOS();
                //os.setNome(resultSet.getString("nome"));
                funcionario.setNome(resultSet.getString("nome"));
                funcionario.setCargo(resultSet.getString("cargo"));
                funcionario.setMes(resultSet.getString("mes"));
                funcionario.setQuantidade(resultSet.getInt("qtd"));

                lista.add(funcionario);
            }

            //Executa a sql para inserção dos dados
            //pstm.execute();
        } catch (SQLException e) {

        } finally {
            //Fecha as conexões
            try {
                if (pstm != null) {

                    pstm.close();
                }

                if (connection != null) {
                    connection.close();
                }

                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
            }
        }
        return lista;
    }

    public List<OrdemServico> aguardandoAvaliacao() {
        String sql = "";

        Connection connection = null;
        PreparedStatement pstm = null;
        //Classe que vai recuperar os dados do banco de dados
        ResultSet resultSet = null;

        List<OrdemServico> lista = new ArrayList<OrdemServico>();

        try {
            //Cria uma conexão com o banco
            connection = ConnectionFactory.createConnectionToOracle();

            //Cria um PreparedStatment, classe usada para executar a query
            pstm = connection.prepareStatement(sql);
            resultSet = pstm.executeQuery();

            while (resultSet.next()) {
                OrdemServico os = new OrdemServico();

                os.setOs(resultSet.getInt("os"));
                os.setTempoAbertura(resultSet.getString("tempo_abertura"));
//                os.setDtPedido(resultSet.getString("dt_pedido"));
                os.setDtPedido(resultSet.getTimestamp("dt_pedido"));
                os.setServico(resultSet.getString("servico"));
                os.setNome(resultSet.getString("nome"));
                os.setSetor(resultSet.getString("setor"));
                os.setLocalidade(resultSet.getString("localidade"));
                os.setRamal(resultSet.getString("ramal"));
                os.setTpOs(resultSet.getString("tp_os"));
                os.setUsuario(resultSet.getString("usuario"));
                os.setStatus(resultSet.getString("status"));
                os.setTecResponsavel(resultSet.getString("tec_responsavel"));

                lista.add(os);
            }

            //Executa a sql para inserção dos dados
            //pstm.execute();
        } catch (SQLException e) {

        } finally {
            //Fecha as conexões
            try {
                if (pstm != null) {

                    pstm.close();
                }

                if (connection != null) {
                    connection.close();
                }

                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
            }
        }
        return lista;
    }
//    -----
    public List<OrdemServico> aguardandoAtencao() {
        String sql = "SELECT CD_OS OS,\n"
                + "       LPAD(DIA, 2, 0) || ' dia(s) ' ||\n"
                + "       LPAD(HORA, 2, 0) || ':' ||\n"
                + "       LPAD(MINUTO, 2, 0) || ':' || \n"
                + "       LPAD(SEGUNDO, 2, 0) TEMPO_ABERTURA,\n"
                + "\n"
                + "       \n"
                + "       DT_PEDIDO,\n"
                + "       DS_SERVICO SERVICO,\n"
                + "       NOME,\n"
                + "       NM_SETOR SETOR,\n"
                + "       DS_LOCALIDADE LOCALIDADE,\n"
                + "       DS_RAMAL RAMAL,\n"
                + "       DS_TIPO_OS TP_OS,\n"
                + "       NM_USUARIO USUARIO,\n"
                + "       STATUS,\n"
                + "       RESP TEC_RESPONSAVEL\n"
                + "  FROM (\n"
                + "        \n"
                + "        SELECT TRUNC(TEMPO) DIA,\n"
                + "                TRUNC(((86400 * (TEMPO)) / 60) / 60) - 24 * (TRUNC((((86400 * (TEMPO)) / 60) / 60) / 24)) HORA,\n"
                + "                TRUNC(TRUNC((86400 * (TEMPO) / 60) - 60 * (TRUNC(((86400 * (TEMPO)) / 60) / 60)),'09')) MINUTO,\n"
                + "                TRUNC(TRUNC((86400 * TEMPO) - 60 * (TRUNC((86400 * TEMPO) / 60)))) SEGUNDO,\n"
                + "                DS_SERVICO,\n"
                + "                DS_TIPO_OS,\n"
                + "                CD_OS,\n"
                + "                DT_PEDIDO,\n"
                + "                NOME,\n"
                + "                NM_SETOR,\n"
                + "                DS_LOCALIDADE,\n"
                + "                DS_RAMAL,\n"
                + "                NM_USUARIO,\n"
                + "                RESP,\n"
                + "                DECODE(TP_SITUACAO, 'S','SOLICITADA',\n"
                + "                                    'A','ABERTA',\n"
                + "                                    'C','CONCLUÍDA',\n"
                + "                                    'N','NÃO ATENDIDA',\n"
                + "                                    'M','AGUARDANDO MATERIAL',\n"
                + "                                    'E','CONSERTO EXTERNO',\n"
                + "                                    'F','A AGENDAR',\n"
                + "                                    'L', 'AGUARDANDO LIBERAÇÃO DO SETOR'\n"
                + "                                    ) STATUS\n"
                + "          FROM (\n"
                + "                 \n"
                + "                 SELECT OS.CD_OS,\n"
                + "                         OS.DT_PEDIDO,\n"
                + "                         SYSDATE DT_ATUAL,\n"
                + "                         (SYSDATE - OS.DT_PEDIDO) TEMPO,\n"
                + "                         OS.DS_SERVICO,\n"
                + "                         SUBSTR(OS.NM_SOLICITANTE,\n"
                + "                                1,\n"
                + "                                INSTR(OS.NM_SOLICITANTE, ' ') - 1) NOME,\n"
                + "                         S.NM_SETOR,\n"
                + "                         L.DS_LOCALIDADE,\n"
                + "                         OS.DS_RAMAL,\n"
                + "                         TPOS.DS_TIPO_OS,\n"
                + "                         OS.NM_USUARIO,\n"
                + "                         U.NM_USUARIO RESP,\n"
                + "                         OS.TP_SITUACAO\n"
                + "                   FROM SOLICITACAO_OS OS, TIPO_OS TPOS, SETOR S, LOCALIDADE L, DBASGU.USUARIOS U\n"
                + "                  WHERE S.CD_SETOR = OS.CD_SETOR\n"
                + "                    AND TPOS.CD_TIPO_OS = OS.CD_TIPO_OS\n"
                + "                    AND L.CD_LOCALIDADE(+) = OS.CD_LOCALIDADE\n"
                + "                    AND U.CD_USUARIO(+) = OS.CD_RESPONSAVEL\n"
                + "                    AND OS.CD_OFICINA = 13\n"
                + "                    AND OS.DT_PEDIDO > '01/07/2017'\n"
                + "                    AND OS.TP_SITUACAO IN ('M','L','F','E')\n"
                + "                  ORDER BY OS.CD_OS\n"
                + "                 \n"
                + "                 )  \n"
                + "        )";

        Connection connection = null;
        PreparedStatement pstm = null;
        //Classe que vai recuperar os dados do banco de dados
        ResultSet resultSet = null;

        List<OrdemServico> lista = new ArrayList<OrdemServico>();

        try {
            //Cria uma conexão com o banco
            connection = ConnectionFactory.createConnectionToOracle();

            //Cria um PreparedStatment, classe usada para executar a query
            pstm = connection.prepareStatement(sql);
            resultSet = pstm.executeQuery();

            while (resultSet.next()) {
                OrdemServico os = new OrdemServico();

                os.setOs(resultSet.getInt("os"));
                os.setTempoAbertura(resultSet.getString("tempo_abertura"));
//                os.setDtPedido(resultSet.getString("dt_pedido"));
                os.setDtPedido(resultSet.getTimestamp("dt_pedido"));
                os.setServico(resultSet.getString("servico"));
                os.setNome(resultSet.getString("nome"));
                os.setSetor(resultSet.getString("setor"));
                os.setLocalidade(resultSet.getString("localidade"));
                os.setRamal(resultSet.getString("ramal"));
                os.setTpOs(resultSet.getString("tp_os"));
                os.setUsuario(resultSet.getString("usuario"));
                os.setStatus(resultSet.getString("status"));
                os.setTecResponsavel(resultSet.getString("tec_responsavel"));

                lista.add(os);
            }

            //Executa a sql para inserção dos dados
            //pstm.execute();
        } catch (SQLException e) {

        } finally {
            //Fecha as conexões
            try {
                if (pstm != null) {

                    pstm.close();
                }

                if (connection != null) {
                    connection.close();
                }

                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
            }
        }
        return lista;
    }
}
