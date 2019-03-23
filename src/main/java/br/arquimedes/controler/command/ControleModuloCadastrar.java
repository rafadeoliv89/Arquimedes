/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.arquimedes.controler.command;

import br.arquimedes.controler.Command;
import br.arquimedes.model.Modulo;
import br.arquimedes.model.UserMod;
import br.arquimedes.model.dao.ModuloDAO;
import br.arquimedes.model.dao.UserModDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Usuario
 */
public class ControleModuloCadastrar implements Command {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("------Dentro da Command: Cadastro Modulo-------");

        String msg;
        String caminho = "../Admin/CadastroModulo.jsp";

        try {
            ModuloDAO moduloDao = new ModuloDAO();
            Modulo modulo = new Modulo();
            UserMod usermod = new UserMod();
            UserModDAO usermodDao = new UserModDAO();
            
            modulo.setNome(request.getParameter("txtNome"));
            modulo.setDescricao(request.getParameter("txtDescricao"));
            modulo.setStatusModulo(true);

            if (moduloDao.validaCadastro(modulo) == true) {
                modulo = moduloDao.cadastrar(modulo);
                
                usermod.getModulo().setIdModulo(modulo.getIdModulo());
                
                usermodDao.cadastrarADM(usermod);
                msg = "Cadastrado com sucesso";
            } else {
                msg = "Login já cadastrado";
            }
            request.setAttribute("msg", msg);
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ControleModuloCadastrar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception erro) {
            request.setAttribute("erro", erro);
            caminho = "/erro/erro.jsp";
        } 
        return caminho;
    }
}
