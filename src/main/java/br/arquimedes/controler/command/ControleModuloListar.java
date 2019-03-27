/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.arquimedes.controler.command;

import br.arquimedes.controler.Command;
import br.arquimedes.controler.ControleAcesso;
import br.arquimedes.model.Modulo;
import br.arquimedes.model.dao.ModuloDAO;
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
public class ControleModuloListar implements Command {
    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("------Dentro da Command: Listar-------");

        String msg = null;
        String caminho = "../Admin/listagemDeModulos.jsp";

        try {
            ModuloDAO moduloDao = new ModuloDAO();
            UserModDAO usermodDao = new UserModDAO();

            List<Modulo> listaModulos = moduloDao.listar();
            listaModulos.forEach(modulo -> {
                    try {
                        usermodDao.contUsuarios(modulo);

                    } catch (ClassNotFoundException | SQLException ex) {
                        Logger.getLogger(ControleAcesso.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            
            request.setAttribute("lista", listaModulos);
            request.setAttribute("msg", msg);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ControleModuloInativar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception erro) {
            request.setAttribute("erro", erro);
            caminho = "/erro/erro.jsp";
        }
        return caminho;
    }
}