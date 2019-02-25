/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.arquimedes.model;

import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Formula {

    private int idFormula;
    private Usuario usuario = new Usuario();
    private String nomeFormula;
    private String mascaraFormula;
    private String calculoFormula;
    private String descricao;
    private String[] variaveis;
    private String variaveis2;
    private ArrayList<String> variaveis3 = new ArrayList<String>();
    private int idFormulaDependente;
    private boolean Status;

    public ArrayList<String> getVariaveis3() {
        return variaveis3;
    }

    public void setVariaveis3(ArrayList<String> variaveis3) {
        this.variaveis3 = variaveis3;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public int getIdFormula() {
        return idFormula;
    }

    public void setIdFormula(int idFormula) {
        this.idFormula = idFormula;
    }

    public String getNomeFormula() {
        return nomeFormula;
    }

    public void setNomeFormula(String nomeDaFormula) {
        this.nomeFormula = nomeDaFormula;
    }

    public String getMascaraFormula() {
        return mascaraFormula;
    }

    public void setMascaraFormula(String mascaraFormula) {
        this.mascaraFormula = mascaraFormula;
    }

    public String getCalculoFormula() {
        return calculoFormula;
    }

    public void setCalculoFormula(String calculoFormula) {
        this.calculoFormula = calculoFormula;
    }

    public String[] getVariaveis() {
        return variaveis;
    }

    public void setVariaveis(String[] variaveis) {
        this.variaveis = variaveis;
    }

    public String getVariaveis2() {
        return variaveis2;
    }

    public void setVariaveis2(String variaveis2) {
        this.variaveis2 = variaveis2;
    }

    public int getIdFormulaDependente() {
        return idFormulaDependente;
    }

    public void setIdFormulaDependente(int idFormulaDependente) {
        this.idFormulaDependente = idFormulaDependente;
    }

    public boolean getStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }



}
