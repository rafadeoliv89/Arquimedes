/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.arquimedes.controler.command;

import br.arquimedes.controler.Command;
import br.arquimedes.model.PerfilAcesso;
import br.arquimedes.model.Usuario;
import br.arquimedes.model.dao.PessoaDAO;
import br.arquimedes.model.dao.UsuarioDAO;
import br.arquimedes.util.ConversorData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Usuario
 */
public class ControleUsuarioBuscar implements Command {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("------Dentro da Command: Usuario Buscar-------");

        PessoaDAO pessoaDao = new PessoaDAO();
        UsuarioDAO usuarioDao = new UsuarioDAO();
        Usuario usuario = new Usuario();
        ConversorData conv = new ConversorData();

        String msg = null;
        String caminho = null;
        String nomeUsuario = null;

        //Valida permissão para determinar o retorno
        HttpSession sessaoUsuario = ((HttpServletRequest) request).getSession();
        Usuario validaUsuario = (Usuario) sessaoUsuario.getAttribute("usuarioAutenticado");

        if (validaUsuario != null && validaUsuario.getPerfil().equals(PerfilAcesso.ADMINISTRADOR)) {
            caminho = "../Admin/controleUsuario.jsp";
            nomeUsuario = request.getParameter("txtBusca");

        } else {
            caminho = "../atualizarDados.jsp";
            nomeUsuario = validaUsuario.getNomeUsuario();
        }

        try {
            usuario.setNomeUsuario(nomeUsuario);

            usuario = usuarioDao.buscar(usuario);
                Usuario usuarioBusca = pessoaDao.buscar(usuario);

            String primeiroPerfil;
            String segundoPerfil;
            String status;
            String nomeStatus;

            if (usuarioBusca.getStatusUsuario() == true) {
                status = "Inativar";
                nomeStatus = "Ativo";
            } else {
                status = "Ativar";
                nomeStatus = "Inativo";
            }

            if ("ADMINISTRADOR".equals(usuarioBusca.getPerfil().toString())) {
                primeiroPerfil = "ADMINISTRADOR";
                segundoPerfil = "COMUM";

            } else {
                primeiroPerfil = "COMUM";
                segundoPerfil = "ADMINISTRADOR";
            }

            request.setAttribute("primeiroPerfil", primeiroPerfil);
            request.setAttribute("segundoPerfil", segundoPerfil);
            request.setAttribute("status", status);
            request.setAttribute("nomeStatus", nomeStatus);
            request.setAttribute("usuarioBusca", usuarioBusca);
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ControleUsuarioBuscar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception erro) {
            request.setAttribute("erro", erro);
            msg = "Usuario não encontrado";
        }
        request.setAttribute("msg", msg);
        return caminho;
    }
}
