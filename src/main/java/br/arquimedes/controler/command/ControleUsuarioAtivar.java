/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.arquimedes.controler.command;

import br.arquimedes.controler.Command;
import br.arquimedes.model.Usuario;
import br.arquimedes.model.dao.UsuarioDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Usuario
 */
public class ControleUsuarioAtivar implements Command {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("------Dentro da Command: Usuario Ativar-------");

        String msg = null;
        String caminho = "../Admin/controleUsuario.jsp";

        try {
            UsuarioDAO usuarioDao = new UsuarioDAO();
            Usuario usuario = new Usuario();

            usuario.setIdUsuario(Integer.parseInt(request.getParameter("txtIdUsuario")));

            usuarioDao.ativar(usuario);
            
            msg = "Usuário Ativo com sucesso";

            request.setAttribute("msg", msg);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ControleUsuarioAtivar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception erro) {
            request.setAttribute("erro", erro);
            caminho = "/erro/erro.jsp";
        }
        return caminho;
    }
}