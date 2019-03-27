/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.arquimedes.controler.command;

import br.arquimedes.controler.Command;
import br.arquimedes.controler.ControleAcesso;
import br.arquimedes.model.Plano;
import br.arquimedes.model.dao.PlanoDAO;
import br.arquimedes.model.dao.UserModDAO;
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
public class ControlePlanoListar implements Command {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("------Dentro da Command: Listar-------");

        String msg = null;
        String caminho = "../Admin/listagemDePlano.jsp";               

        try {
            PlanoDAO planoDao = new PlanoDAO();
            UserModDAO usermodDao = new UserModDAO();    
            
            List<Plano> lista = planoDao.listar();            
            lista.forEach(plano -> {
                    try {
                        usermodDao.contUsuariosPlano(plano);

                    } catch (ClassNotFoundException | SQLException ex) {
                        Logger.getLogger(ControleAcesso.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            
            request.setAttribute("lista", lista);
            request.setAttribute("msg", msg);
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ControlePlanoListar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception erro) {
            request.setAttribute("erro", erro);
            caminho = "/erro/erro.jsp";
        }
        return caminho;
    }   
}
