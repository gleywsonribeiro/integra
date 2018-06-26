/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hmue.integra.modelo;

/**
 *
 * @author Gleywson
 * 
 */
//representa a producao dos 
public class Producao {
    private String funcionario;
    private int avaliadas;
    private int naoAvaliadas;
    private float tempo;

    public String getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(String funcioanrio) {
        this.funcionario = funcioanrio;
    }

    public int getAvaliadas() {
        return avaliadas;
    }

    public void setAvaliadas(int avaliadas) {
        this.avaliadas = avaliadas;
    }

    public int getNaoAvaliadas() {
        return naoAvaliadas;
    }

    public void setNaoAvaliadas(int naoAvaliadas) {
        this.naoAvaliadas = naoAvaliadas;
    }

    public float getTempo() {
        return tempo;
    }

    public void setTempo(float tempo) {
        this.tempo = tempo;
    }
   
    public String getPrimeiroNomeFuncionario() {
        return getFuncionario().split(" ")[0];
    }
    
}
