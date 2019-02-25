/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.arquimedes.util;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class ConversorData {

    public Date conversorData(String data) {
        System.out.println("----Dentro da Conversor Data----");
        
        SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        Date dataUtil = null;
        try {
            dataUtil = df.parse(data);
            
        } catch (ParseException ex) {
            Logger.getLogger(ConversorData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataUtil;
    }
    
    public String conversorSqlToDate(Date data) {
        String txtData = null;
        Calendar cd = Calendar.getInstance();
        
            cd.setTime(data);
            cd.add(Calendar.DATE, 0);
        
            data = cd.getTime();
            
            txtData = (new SimpleDateFormat("dd/MM/yyyy")).format(data);
            
        return txtData;
    }
    
    public Date calculaDataFim(String data, int duracao) {
        SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
        
        Date dataUtil = null;
        try {
            dataUtil = dt.parse(data + duracao);
            
        } catch (ParseException ex) {
            Logger.getLogger(ConversorData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataUtil;
    }
    
    public java.sql.Date calculaDataFim2(Date data, int duracao) {
        
        Calendar cd = Calendar.getInstance();
        cd.setTime(data);
        cd.add(Calendar.DATE, duracao);
        
        data = cd.getTime();
       
        return new java.sql.Date( data.getTime() );
    }
    
    public java.sql.Date calculaDataFim3(Date data) {
        
        Calendar cd = Calendar.getInstance();
        cd.setTime(data);
        cd.add(Calendar.DATE, 2);
        
        data = cd.getTime();
       
        return new java.sql.Date( data.getTime() );
    }
}
