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
import br.arquimedes.model.dao.PessoaDAO;
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
public class ControleFormulaHome implements Command {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("------Dentro da: voltar para home-------");

        String msg = null;
        String caminho = "../home.jsp";

        UsuarioDAO usuarioDao = new UsuarioDAO();
        Usuario usuario = new Usuario();
        UserModDAO usermodDao = new UserModDAO();
        UserMod usermod = new UserMod();
        PessoaDAO pessoaDao = new PessoaDAO();
        UserFormDAO userformDao = new UserFormDAO();
        UserForm userform = new UserForm();
        
            HttpSession sessaoUsuario = ((HttpServletRequest) request).getSession();
            Usuario validaUsuario = (Usuario) sessaoUsuario.getAttribute("usuarioAutenticado");
            
            usuario.setNomeUsuario(validaUsuario.getNomeUsuario());

        try {
             usuario = usuarioDao.buscar(usuario);
            request.setAttribute("usuario", usuario);
            usermod.getUsuario().setIdUsuario(usuario.getIdUsuario());
            userform.getUsuario().setIdUsuario(usuario.getIdUsuario());

            if (usuario.getPerfil().equals(PerfilAcesso.ADMINISTRADOR)) {
                usermod.getUsuario().setIdUsuario(1);

                List<UserMod> listaModulos = usermodDao.listarModulosADM(usermod);
                listaModulos.forEach(modulo -> {
                    try {
                        userform.getModulo().setIdModulo(modulo.getModulo().getIdModulo());
                        List<UserForm> formulasDosModulos = userformDao.listarFormulasModulo(userform);
                        List<Formula> formulas = formulasDosModulos
                                .stream()
                                .map(userForm -> userForm.getFormula())
                                .collect(Collectors.toCollection(ArrayList::new));
                        modulo.getModulo().setFormulas(formulas);

                    } catch (ClassNotFoundException | SQLException ex) {
                        Logger.getLogger(ControleAcesso.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                request.setAttribute("listaM", listaModulos);

            } else {
                List<UserMod> listaModulos = usermodDao.listarMeusModulosCalculo(usermod);
                listaModulos.forEach(modulo -> {
                    userform.getModulo().setIdModulo(modulo.getModulo().getIdModulo());
                    userform.getUsuario().setIdUsuario(modulo.getUsuario().getIdUsuario());
                    try {
                        if (modulo.getModulo().getIdModulo() != 2) {
                            List<UserForm> formulasDosModulos = userformDao.listarFormulasModulo(userform);
                            List<Formula> formulas = formulasDosModulos
                                    .stream()
                                    .map(userForm -> userForm.getFormula())
                                    .collect(Collectors.toCollection(ArrayList::new));
                            modulo.getModulo().setFormulas(formulas);
                        } else {
                            List<UserForm> formulasDosModulos = userformDao.listarMinhasFormulasCustom(userform);
                            List<Formula> formulas = formulasDosModulos
                                    .stream()
                                    .map(userForm -> userForm.getFormula())
                                    .collect(Collectors.toCollection(ArrayList::new));
                            modulo.getModulo().setFormulas(formulas);
                        }
                    } catch (ClassNotFoundException | SQLException ex) {
                        Logger.getLogger(ControleAcesso.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                request.setAttribute("listaM", listaModulos);
            }

        } catch (Exception erro) {
            System.out.println("Erro na voltar para home: " + erro.getMessage());
            request.setAttribute("erro", erro);
            caminho = "../erro.jsp";
        }
        return caminho;
    }
}
