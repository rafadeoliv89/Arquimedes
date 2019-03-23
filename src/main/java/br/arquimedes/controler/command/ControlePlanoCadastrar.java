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
public class ControlePlanoCadastrar implements Command {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("------Dentro da Command: Plano Cadastro-------");

        String msg;
        String caminho = "../Admin/CadastroPlano.jsp";

        try {
            PlanoDAO planoDao = new PlanoDAO();
            Plano plano = new Plano();

            plano.setNomePlano(request.getParameter("txtNome"));
            plano.setValor(Double.parseDouble(request.getParameter("txtValor")));
            plano.setTempoPlano(Integer.parseInt(request.getParameter("txtDuracao")));
            
            if (planoDao.validaCadastro(plano) == true) {
                planoDao.cadastrar(plano);
                msg = "Cadastrado com sucesso";
            } else {
                msg = "Plano já cadastrado";
            }
            request.setAttribute("msg", msg);
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ControlePlanoCadastrar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception erro) {
            request.setAttribute("erro", erro);
            caminho = "/erro/erro.jsp";
        }
        return caminho;
    }

}
