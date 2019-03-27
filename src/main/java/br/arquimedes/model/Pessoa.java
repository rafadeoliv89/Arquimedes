/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.arquimedes.model;

import java.util.Date;

/**
 *
 * @author Usuario
 */
public abstract class Pessoa {
   
    private int idPessoa;
    private String nomePessoa;
    private int idade;
    private String sexo;
    private String email;
    private String CPF;
    private Date dataNascimento;
    private long telefone;

    public long getTelefone() {
        return telefone;
    }

    public void setTelefone(long telefone) {
        this.telefone = telefone;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getIdPessoa() {
        return idPessoa;
    }
    public void setIdPessoa(int id) {
        this.idPessoa = id;
    }
    
    public String getNomePessoa() {
        return nomePessoa;
    }
    public void setNomePessoa(String nome) {
        this.nomePessoa = nome;
    }

    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getSexo() {
        return sexo;
    }
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getCPF() {
        return CPF;
    }
    public void setCPF(String CPF) {
        this.CPF = CPF;
    }
    
    
    
}
