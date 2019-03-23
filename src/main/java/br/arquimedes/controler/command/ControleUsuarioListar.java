/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.arquimedes.controler.command;

import br.arquimedes.controler.Command;
import br.arquimedes.model.Usuario;
import br.arquimedes.model.dao.PessoaDAO;
import br.arquimedes.model.dao.UsuarioDAO;
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
public class ControleUsuarioListar implements Command {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("------Dentro da Command: Listar-------");

        String msg = null;
        String caminho = "../Admin/listagemDeUsuarios.jsp";

        try {
            PessoaDAO pessoaDao = new PessoaDAO();
            UsuarioDAO usuarioDao = new UsuarioDAO();

            List<Usuario> listaUsuarios = usuarioDao.listar();
            request.setAttribute("lista", listaUsuarios);

            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ControleUsuarioListar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception erro) {
            request.setAttribute("erro", erro);
            caminho = "/erro/erro.jsp";
        }
        request.setAttribute("msg", msg);
        return caminho;
    }   
}