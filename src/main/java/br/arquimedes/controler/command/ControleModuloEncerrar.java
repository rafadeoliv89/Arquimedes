/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.arquimedes.controler.command;

import br.arquimedes.controler.Command;
import br.arquimedes.model.Modulo;
import br.arquimedes.model.Plano;
import br.arquimedes.model.UserMod;
import br.arquimedes.model.Usuario;
import br.arquimedes.model.dao.ModuloDAO;
import br.arquimedes.model.dao.PlanoDAO;
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

/**
 *
 * @author Usuario
 */
public class ControleModuloEncerrar implements Command {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("------Dentro da Command: Encerrar-------");

        String msg = null;
        String caminho = "../meusModulos.jsp";

        try {
            ModuloDAO moduloDao = new ModuloDAO();
            PlanoDAO planoDao = new PlanoDAO();
            UsuarioDAO usuarioDao = new UsuarioDAO();
            Usuario usuario = new Usuario();
            UserModDAO usermodDao = new UserModDAO();
            UserMod usermod = new UserMod();
            
            usermod.getModulo().setIdModulo(Integer.parseInt(request.getParameter("txtIdModulo")));
            usermod.getUsuario().setIdUsuario(Integer.parseInt(request.getParameter("txtIdUsuario")));
            
            usuario.setIdUsuario(usermod.getUsuario().getIdUsuario());
            
            usermodDao.deletar(usermod);
            msg = "Contrato encerrado com sucesso";
            usermodDao.cadastrarEncerramento(usermod);
            
            List<Modulo> filtroModulo = new ArrayList<Modulo>();
            List<Modulo> listaModulos = moduloDao.listaModuloAtivo();
            List<UserMod> listaUserModulosUsuario = usermodDao.listarPorUsuario(usermod);
            List<Modulo> listaModulosUsuario = listaUserModulosUsuario
                    .stream()
                    .map(userMod -> userMod.getModulo())
                    .collect(Collectors.toCollection(ArrayList::new));
            
            filtroModulo = listaModulos.stream()
                    .filter(moduloSistema -> !listaModulosUsuario.contains(moduloSistema))
                    .collect(Collectors.toCollection(ArrayList::new));           
            request.setAttribute("listaM", filtroModulo);
            
            List<UserMod> listarMeusModulos = usermodDao.listarMeusModulos(usermod);
            request.setAttribute("listaU", listarMeusModulos);
            
            List<Plano> listaPlanos = planoDao.listar();
            request.setAttribute("listaP", listaPlanos);     
            
            usuarioDao.buscarId(usuario);
            request.setAttribute("usuario", usuario);
            
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
