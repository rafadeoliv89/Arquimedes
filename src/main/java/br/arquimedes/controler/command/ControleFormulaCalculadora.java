/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.arquimedes.controler.command;

import br.arquimedes.controler.Command;
import br.arquimedes.model.UserForm;
import br.arquimedes.model.dao.UserFormDAO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Usuario
 */
public class ControleFormulaCalculadora implements Command {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("------Dentro da Command: Calculadora-------");
        String caminho = "../calculadora.jsp";    
        
        UserFormDAO userformDao = new UserFormDAO();
        UserForm userform = new UserForm();
        List<UserForm> form = null;
        userform.getFormula().setIdFormula(Integer.parseInt(request.getParameter("txtIdFormula")));
        
        try {
            form = userformDao.listarFormulasId(userform);
            request.setAttribute("formulas", form);
            
        } catch (Exception erro) {
            System.out.println("Erro na Conrole formula calculadora: "+erro.getMessage());
            request.setAttribute("erro", erro);
            caminho = "/erro/erro.jsp";
        }
        return caminho;
    }   
}
