/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.arquimedes.email;

/**
 *
 * @author Usuario
 */
public abstract class EmailConfig {
    
    static protected String FROM = "arquimedesmanager@gmail.com";
    static protected String FROMNAME = "Arquimedes";
    static protected final String SMTP_USERNAME = "8a34d920e91a0e";
    static protected final String SMTP_PASSWORD = "865ae8543d180c";
    static protected final String HOST = "smtp.mailtrap.io";
    static protected final int PORT = 2525;
    protected String TO;
    
    // The name of the Configuration Set to use for this message.
    // If you comment out or remove this variable, you will also need to
    // comment out or remove the header below.
    //static final String CONFIGSET = "ConfigSet";

    public String getTO() {
        return TO;
    }

    public void setTO(String TO) {
        this.TO = TO;
    }

    
    
}
