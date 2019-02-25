/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.arquimedes.controler.command;

import br.arquimedes.controler.Command;
import br.arquimedes.model.Modulo;
import br.arquimedes.model.PerfilAcesso;
import br.arquimedes.model.Usuario;
import br.arquimedes.model.dao.ModuloDAO;
import br.arquimedes.model.dao.PessoaDAO;
import br.arquimedes.model.dao.UserModDAO;
import br.arquimedes.model.dao.UsuarioDAO;
import br.arquimedes.util.ConversorData;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Usuario
 */
public class ControleUsuarioCadastrar implements Command {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("text/html");
        
        System.out.println("------Dentro da Command: Usuario Cadastrar-------");

        String msg;
        String caminho;

        try {
            PessoaDAO pessoaDao = new PessoaDAO();
            UsuarioDAO usuarioDao = new UsuarioDAO();
            UserModDAO userModDao = new UserModDAO();
            ModuloDAO moduloDao = new ModuloDAO();
            Usuario usuario = new Usuario();
            ConversorData conv = new ConversorData();
            
            usuario.setNomePessoa(request.getParameter("txtNome"));
            usuario.setDataNascimento(conv.conversorData(request.getParameter("txtDataNascimento")));
            System.out.println("Data: "+usuario.getDataNascimento());
            usuario.setSexo(request.getParameter("txtSexo"));
            usuario.setEmail(request.getParameter("txtEmail"));
            usuario.setCPF(request.getParameter("txtCPF"));
            usuario.setNomeUsuario(request.getParameter("txtLogin"));
            usuario.setSenha(request.getParameter("txtSenha"));
            
            HttpSession sessaoUsuario = ((HttpServletRequest) request).getSession();
            Usuario validaUsuario = (Usuario) sessaoUsuario.getAttribute("usuarioAutenticado");
            
            if (validaUsuario != null && validaUsuario.getPerfil().equals(PerfilAcesso.ADMINISTRADOR)) {
                
                caminho = "../Admin/CadastroUsuario.jsp";
                String perfil = request.getParameter("optPerfil");
                
                if (perfil.equalsIgnoreCase("administrador")) {
                    usuario.setPerfil(PerfilAcesso.ADMINISTRADOR);
                } else {
                    usuario.setPerfil(PerfilAcesso.COMUM);
                }
            } else {
                caminho = "../novoUsuario.jsp";
                usuario.setPerfil(PerfilAcesso.COMUM);
            }

            if (usuarioDao.validaCadastro(usuario) == true) {
                
                usuario = pessoaDao.cadastrar(usuario);
                usuario = usuarioDao.cadastrar(usuario);
                
                if (usuario.getPerfil().equals(PerfilAcesso.ADMINISTRADOR)) {
                    
                    List<Modulo> listaM = moduloDao.listar();
                    for (Modulo mod : listaM) {
                        new UserModDAO().associarTodosModulos(usuario, mod);
                    }
                             
                } else {
                    userModDao.associaModuloBasico(usuario);
                    userModDao.associaMeuModulo(usuario);
                }
                    
                msg = "Cadastrado com sucesso";

            } else {
                msg = "Login já cadastrado";
            }

        } catch (Exception erro) {
            msg = erro.getMessage();
            System.out.println("Erro na cadastrar novo usuario: "+erro.getMessage());
            caminho = "../erro/erro.jsp";
        }

        request.setAttribute("msg", msg);
        return caminho;
    }
}
