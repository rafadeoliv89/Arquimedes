/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.arquimedes.controler;


import br.arquimedes.model.Formula;
import br.arquimedes.model.PerfilAcesso;
import br.arquimedes.model.UserForm;
import br.arquimedes.model.UserMod;
import br.arquimedes.model.Usuario;
import br.arquimedes.model.dao.PessoaDAO;
import br.arquimedes.model.dao.UserFormDAO;
import br.arquimedes.model.dao.UserModDAO;
import br.arquimedes.model.dao.UsuarioDAO;
import java.io.IOException;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Usuario
 */
@WebServlet(name = "ControleAcesso", urlPatterns = {"/ControleAcesso"})
public class ControleAcesso extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        System.out.println("---Dentro da servlet: Controle de acesso----");

        try {
            String acao = request.getParameter("acao");
            if (acao.equals("Entrar")) {

                UsuarioDAO usuarioDao = new UsuarioDAO();
                Usuario usuario = new Usuario();
                UserModDAO usermodDao = new UserModDAO();
                UserMod usermod = new UserMod();
                PessoaDAO pessoaDao = new PessoaDAO();
                UserFormDAO userformDao = new UserFormDAO();
                UserForm userform = new UserForm();

                usuario.setNomeUsuario(request.getParameter("txtLogin"));
                usuario.setSenha(request.getParameter("txtSenha"));

                usuario = usuarioDao.autenticaUsuario(usuario);
                Usuario usuarioAutenticado = pessoaDao.buscar(usuario);
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
                if (usuarioAutenticado.getStatus() == true) {
                    HttpSession sessaoUsuario = request.getSession();
                    sessaoUsuario.setAttribute("usuarioAutenticado", usuarioAutenticado);
                    request.getRequestDispatcher("home.jsp").forward(request, response);

                } else if (usuarioAutenticado.getStatus() != true) {
                    request.setAttribute("msg", "Usuario inativo, favor entrar em contato com o administrador");
                    request.getRequestDispatcher("Login.jsp").forward(request, response);
                    
                } else {
                    request.setAttribute("msg", "Login ou senha incorretos");
                    request.getRequestDispatcher("Login.jsp").forward(request, response);
                }
            }
        } catch (Exception erro) {
            System.out.println("Erro na servlet Controle Acesso: " + erro.getMessage());
            request.setAttribute("msg", "Login ou senha incorretos");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        System.out.println("---Dentro da servlet: Sair----");

        try {
            String acao = request.getParameter("acao");
            System.out.println("--Parametro: " + acao);

            if (acao.equals("Sair")) {
                System.out.println("------Dentro do sair-------");

                HttpSession sessaoUsuario = request.getSession();
                System.out.println("Sessão: " + request.getSession());

                sessaoUsuario.removeAttribute("usuarioAutenticado");
                response.sendRedirect("logout.jsp");
            }

        } catch (Exception erro) {
            System.out.println("Erro: " + erro.getMessage());
            request.setAttribute("erro", erro);
            request.getRequestDispatcher("/erro/erro.jsp").forward(request, response);
        }
    }
}
