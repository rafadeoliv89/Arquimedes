/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.arquimedes.model;

import java.sql.Date;

/**
 *
 * @author Usuario
 */
public class Plano {
    
    private int idPlano;
    private String nomePlano;
    private double valor;
    private int tempoPlano;
    private boolean visibilidade;    
    private int contador;

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }    

    public int getTempoPlano() {
        return tempoPlano;
    }

    public void setTempoPlano(int tempoPlano) {
        this.tempoPlano = tempoPlano;
    }

    
    public boolean getVisibilidade() {
        return visibilidade;
    }

    public void setVisibilidade(boolean visibilidade) {
        this.visibilidade = visibilidade;
    }
    
    public int getIdPlano() {
        return idPlano;
    }

    public void setIdPlano(int idPlano) {
        this.idPlano = idPlano;
    }

    public String getNomePlano() {
        return nomePlano;
    }

    public void setNomePlano(String nomePlano) {
        this.nomePlano = nomePlano;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    
}
