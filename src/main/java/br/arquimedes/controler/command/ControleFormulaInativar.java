/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.arquimedes.controler.command;

import br.arquimedes.controler.Command;
import br.arquimedes.model.PerfilAcesso;
import br.arquimedes.model.UserForm;
import br.arquimedes.model.UserMod;
import br.arquimedes.model.Usuario;
import br.arquimedes.model.dao.UserFormDAO;
import br.arquimedes.model.dao.UserModDAO;
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
public class ControleFormulaInativar implements Command {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("------Dentro da Command: Formula Inativar-------");

        String msg = null;
        String caminho = "../minhasFormulas.jsp";

        try {
            Usuario usuario = new Usuario();
            UsuarioDAO usuarioDao = new UsuarioDAO();
            UserFormDAO userformDao = new UserFormDAO();
            UserForm userform = new UserForm();
            UserModDAO usermodDao = new UserModDAO();
            UserMod usermod = new UserMod();

            userform.getFormula().setIdFormula(Integer.parseInt(request.getParameter("txtIdFormula")));
            usuario.setIdUsuario(Integer.parseInt(request.getParameter("txtIdUsuario")));
            userform.getUsuario().setIdUsuario(usuario.getIdUsuario());

            userformDao.inativar(userform);

            msg = "Formula Inativa com sucesso";
            request.setAttribute("msg", msg);

            usuario = usuarioDao.buscarId(usuario);
            request.setAttribute("usuario", usuario);

            usermod.getUsuario().setIdUsuario(usuario.getIdUsuario());

            if (usuario.getPerfil().equals(PerfilAcesso.ADMINISTRADOR)) {
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

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ControleFormulaInativar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception erro) {
            System.out.println("Erro SVT Formula inativar: " + erro.getMessage());
            request.setAttribute("erro", erro);
            caminho = "/erro/erro.jsp";
        }
        return caminho;
    }
}
