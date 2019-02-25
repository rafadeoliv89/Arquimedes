/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.arquimedes.controler.command;

import br.arquimedes.controler.Command;
import br.arquimedes.model.UserForm;
import br.arquimedes.model.dao.UserFormDAO;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Usuario
 */
public class ControleFormulaCalcular implements Command {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("------Dentro da Command: Formula Calcular-------");
        String caminho = "../calculadora.jsp";
        
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine rhino = manager.getEngineByName("JavaScript");
        UserForm form = new UserForm();
        UserFormDAO userformDao = new UserFormDAO();
        List<UserForm> formula = null;
        form.getFormula().setIdFormula(Integer.parseInt(request.getParameter("idFormula")));
        
        try {
            
            formula = userformDao.listarFormulasId(form);
            request.setAttribute("formulas", formula);
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ControleFormulaCalcular.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        form.getFormula().setIdFormula(Integer.parseInt(request.getParameter("idFormula")));
        
        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
            String name = entry.getKey();
            String value = entry.getValue()[0];

            try {
                
                rhino.put(name, Double.parseDouble(value));
                
            } catch (Exception err) {
            }
        }
        Double result;
        Object result2 = null;
        
        
        try {
            System.out.println(request.getParameter("formula"));
            result2 = rhino.eval(request.getParameter("formula"));
            System.out.println(result2);

        } catch (ScriptException ex) {
            Logger.getLogger(ControleFormulaCalcular.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception erro) {
            System.out.println("Erro na Controle formula calcular: "+erro.getMessage());
            request.setAttribute("erro", erro);
            caminho = "/erro/erro.jsp";
        }
        request.setAttribute("resultado", result2);
        
        return caminho;
    }
}