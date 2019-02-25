/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.arquimedes.controler.command;

import br.arquimedes.controler.Command;
import br.arquimedes.model.Plano;
import br.arquimedes.model.dao.PlanoDAO;
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
public class ControlePlanoSelecionar implements Command {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("------Dentro da Command: Seleconar-------");

        String msg = null;
        String caminho = "../Admin/controlePlano.jsp";

        try {
            PlanoDAO planoDao = new PlanoDAO();
                
            List<Plano> lista = planoDao.listar();
            request.setAttribute("lista", lista);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ControlePlanoSelecionar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception erro) {
            request.setAttribute("erro", erro);
            caminho = "/erro/erro.jsp";
        }
        request.setAttribute("msg", msg);
        return caminho;
    }   
}
