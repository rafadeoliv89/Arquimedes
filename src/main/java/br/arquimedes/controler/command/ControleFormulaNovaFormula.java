/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.arquimedes.controler.command;

import br.arquimedes.controler.Command;
import br.arquimedes.controler.ControleAcesso;
import br.arquimedes.model.Formula;
import br.arquimedes.model.PerfilAcesso;
import br.arquimedes.model.UserForm;
import br.arquimedes.model.UserMod;
import br.arquimedes.model.Usuario;
import br.arquimedes.model.dao.UserFormDAO;
import br.arquimedes.model.dao.UserModDAO;
import br.arquimedes.model.dao.UsuarioDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Usuario
 */
public class ControleFormulaNovaFormula implements Command {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("------Dentro da Command: Nova Formula-------");

        String caminho = "../minhasFormulas.jsp";

        try {
            UsuarioDAO usuarioDao = new UsuarioDAO();
            Usuario usuario = new Usuario();
            UserModDAO usermodDao = new UserModDAO();
            UserMod usermod = new UserMod();
            UserFormDAO userformDao = new UserFormDAO();
            UserForm userform = new UserForm();

            HttpSession sessaoUsuario = ((HttpServletRequest) request).getSession();
            Usuario validaUsuario = (Usuario) sessaoUsuario.getAttribute("usuarioAutenticado");

            usuario.setNomeUsuario(validaUsuario.getNomeUsuario());

            usuario = usuarioDao.buscar(usuario);
            request.setAttribute("usuario", usuario);
            System.out.println("perfil: " + usuario.getPerfil());

            usermod.getUsuario().setIdUsuario(usuario.getIdUsuario());
            userform.getUsuario().setIdUsuario(usuario.getIdUsuario());

            if (usuario.getPerfil().equals(PerfilAcesso.ADMINISTRADOR)) {
                usermod.getUsuario().setIdUsuario(1);
                userform.getUsuario().setIdUsuario(1);
                List<UserMod> listaModulos = usermodDao.listarModulosADM(usermod);
                request.setAttribute("listaM", listaModulos);
                List<UserForm> listaFormulas = userformDao.listarMinhasFormulas(userform);
                request.setAttribute("listaF", listaFormulas);
            } else {
                List<UserForm> listaFormulas = userformDao.listarMinhasFormulas(userform);
                request.setAttribute("listaF", listaFormulas);
                
                List<UserForm> listaMod = userformDao.listarMinhasFormulas(userform);
                    for (int i = 0; i <= listaMod.size(); i++) {
                        for (int j = i; j < listaMod.size(); j++) {
                            if (listaMod.get(i).getModulo().getNome().equals(listaMod.get(j).getModulo().getNome())) {
                                listaMod.remove(j);
                            }
                        }
                    }
                    request.setAttribute("listaM", listaMod);
            }
    }catch (ClassNotFoundException ex) {
        Logger.getLogger(ControleFormulaNovaFormula.class.getName()).log(Level.SEVERE, null, ex);
    }catch (Exception erro) {
        System.out.println("Erro na ControleFormulaNovaFormula: " + erro.getMessage());
        request.setAttribute("erro", erro);
        caminho = "/erro/erro.jsp";
    }
    return caminho ;
}
}
