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

/**
 *
 * @author Usuario
 */
public class ControleUsuarioAlterar implements Command {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("------Dentro da Command: Usuario Alterar-------");

        String msg = null;
        String caminho = "../Admin/controleUsuario.jsp";

        try {
            PessoaDAO pessoaDao = new PessoaDAO();
            UsuarioDAO usuarioDao = new UsuarioDAO();
            Usuario usuario = new Usuario();
            ConversorData conv = new ConversorData();

            usuario.setNomePessoa(request.getParameter("txtNome"));
            usuario.setTelefone(Integer.parseInt(request.getParameter("txtDDD")+request.getParameter("txtTelefone")));
            usuario.setDataNascimento(conv.conversorData(request.getParameter("txtDataNascimento")));
            usuario.setEmail(request.getParameter("txtEmail"));
            usuario.setCPF(request.getParameter("txtCPF"));

            usuario.setIdUsuario(Integer.parseInt(request.getParameter("txtIdUsuario")));
                String perfil = request.getParameter("optPerfil");
                if (perfil.equalsIgnoreCase("administrador")) {
                    usuario.setPerfil(PerfilAcesso.ADMINISTRADOR);
                } else {
                    usuario.setPerfil(PerfilAcesso.COMUM);
                }
            
            usuario = usuarioDao.buscarId(usuario);
                
            usuarioDao.alterar(usuario);
            pessoaDao.alterar(usuario);
            
            usuario = usuarioDao.buscarId(usuario);
            Usuario usuarioBusca = pessoaDao.buscar(usuario);
            
            msg = "Dados do usuário alterados";

            request.setAttribute("msg", msg);
            request.setAttribute("usuarioBusca", usuarioBusca);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ControleUsuarioAlterar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception erro) {
            request.setAttribute("erro", erro);
            caminho = "/erro/erro.jsp";
        }
        return caminho;
    }
}
