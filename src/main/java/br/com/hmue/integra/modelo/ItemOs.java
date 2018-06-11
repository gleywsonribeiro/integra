/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hmue.integra.modelo;

import java.util.Date;

/**
 *
 * @author Gleywson
 */
public class ItemOs {
    private int id;
    private Date horaInicial;
    private Date horaFinal;
    //private OrdemServico os;
    //funcionario
    //Servico

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getHoraInicial() {
        return horaInicial;
    }

    public void setHoraInicial(Date horaInicial) {
        this.horaInicial = horaInicial;
    }

    public Date getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(Date horaFinal) {
        this.horaFinal = horaFinal;
    }

    @Override
    public String toString() {
        return "ItemOs{" + "id=" + id + ", horaInicial=" + horaInicial + ", horaFinal=" + horaFinal + '}';
    }
}
