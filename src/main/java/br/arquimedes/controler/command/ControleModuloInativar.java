/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.arquimedes.controler.command;

import br.arquimedes.controler.Command;
import br.arquimedes.model.Modulo;
import br.arquimedes.model.dao.ModuloDAO;
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
public class ControleModuloInativar implements Command {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("------Dentro da Command: Inativar-------");

        String msg = null;
        String caminho = "../Admin/controleModulo.jsp";

        try {
            String acao = request.getParameter("acao");
            System.out.println("------Parametro acao:" + acao + "-------");

            ModuloDAO moduloDao = new ModuloDAO();
            Modulo modulo = new Modulo();

            System.out.println("--InativarModulo--");

            modulo.setIdModulo(Integer.parseInt(request.getParameter("txtIdModulo")));

            moduloDao.inativar(modulo);

            msg = "Modulo Inativo com sucesso";

            List<Modulo> listaModulos = moduloDao.listar();

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
