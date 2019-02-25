/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.arquimedes.model;

import java.util.List;

/**
 *
 * @author Usuario
 */
public class Modulo {

    private int idModulo;
    private String nome;
    private String descricao;
    private boolean statusModulo;
    private boolean visibilidade;
    private List<Formula> formulas;

    public boolean getVisibilidade() {
        return visibilidade;
    }

    public void setVisibilidade(boolean visibilidade) {
        this.visibilidade = visibilidade;
    }
    
    public List<Formula> getFormulas() {
        return formulas;
    }

    public void setFormulas(List<Formula> formulas) {
        this.formulas = formulas;
    }

    public String getNome() {
        return nome;
    }

    public int getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(int idModulo) {
        this.idModulo = idModulo;
    }

    public boolean getStatusModulo() {
        return statusModulo;
    }

    public void setStatusModulo(boolean statusModulo) {
        this.statusModulo = statusModulo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.idModulo;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Modulo other = (Modulo) obj;
        if (this.idModulo != other.idModulo) {
            return false;
        }
        return true;
    }
        
    
}
