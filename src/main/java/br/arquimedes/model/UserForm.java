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
public class UserForm {
    
    private int idUserForm;
    private Modulo modulo = new Modulo();
    private Usuario usuario = new Usuario();
    private Formula formula = new Formula();
    private boolean status;

    public int getIdUserForm() {
        return idUserForm;
    }

    public void setIdUserForm(int idUserForm) {
        this.idUserForm = idUserForm;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Formula getFormula() {
        return formula;
    }

    public void setFormula(Formula formula) {
        this.formula = formula;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
    
}
