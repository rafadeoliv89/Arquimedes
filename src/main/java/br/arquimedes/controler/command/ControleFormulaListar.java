/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.arquimedes.controler.command;

import br.arquimedes.controler.Command;
import br.arquimedes.model.Formula;
import br.arquimedes.model.dao.FormulaDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Usuario
 */
public class ControleFormulaListar implements Command {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("------Dentro da Command: Listar-------");

        String msg = null;
        String caminho = "../Admin/listagemDeFormulas.jsp";               
        
            //caminho = "../Admin/controleModulo.jsp";

        try {
            String acao = request.getParameter("acao");

            FormulaDAO formulaDao = new FormulaDAO();
                
            List<Formula> listaFormulas = formulaDao.listar();
            request.setAttribute("listaF", listaFormulas);

        } catch (SQLException erro) {
            System.out.println("Erro Controle formula: " + erro.getMessage());
            request.setAttribute("erro", erro);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControleFormulaListar.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("msg", msg);
        return caminho;
    }   
}
