/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.arquimedes.controler.command;

import br.arquimedes.controler.Command;
import br.arquimedes.model.Plano;
import br.arquimedes.model.dao.PlanoDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Usuario
 */
public class ControlePlanoExcluir implements Command {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("------Dentro da Command: Plano Excluir-------");

        String msg = null;
        String caminho = "../Admin/controlePlano.jsp";

        try {
            PlanoDAO planoDao = new PlanoDAO();
            Plano plano = new Plano();

            plano.setIdPlano(Integer.parseInt(request.getParameter("txtIdPlano")));

            planoDao.excluir(plano);
            
            msg = "Dados do plano apagados";

            request.setAttribute("msg", msg);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ControlePlanoExcluir.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception erro) {
            request.setAttribute("erro", erro);
            caminho = "/erro/erro.jsp";
        }
        return caminho;
    }
}
