/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.arquimedes.controler.command;

import br.arquimedes.controler.Command;
import br.arquimedes.model.Formula;
import br.arquimedes.model.PerfilAcesso;
import br.arquimedes.model.UserForm;
import br.arquimedes.model.UserMod;
import br.arquimedes.model.Usuario;
import br.arquimedes.model.dao.FormulaDAO;
import br.arquimedes.model.dao.UserFormDAO;
import br.arquimedes.model.dao.UserModDAO;
import br.arquimedes.model.dao.UsuarioDAO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Usuario
 */
public class ControleFormulaCadastrar implements Command {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");

        System.out.println("-----Dentro da Formula cadastrar-----");

        String msg;
        String caminho = "../minhasFormulas.jsp";

        try {
            FormulaDAO formulaDao = new FormulaDAO();
            Formula formula = new Formula();
            Usuario usuario = new Usuario();
            UsuarioDAO usuarioDao = new UsuarioDAO();
            UserFormDAO userformDao = new UserFormDAO();
            UserForm userform = new UserForm();
            UserModDAO usermodDao = new UserModDAO();
            UserMod usermod = new UserMod();

            //coleta paramtros  
            formula.getUsuario().setIdUsuario(Integer.parseInt(request.getParameter("txtIdUsuario")));
            formula.setNomeFormula(request.getParameter("txtNome"));
            formula.setMascaraFormula(request.getParameter("txtMascara"));
            formula.setCalculoFormula(request.getParameter("txtFormula"));
            formula.setDescricao(request.getParameter("txtDescricao"));
            String b = request.getParameter("txtVariaveis");
            String[] v = b.split(",");
            for (int i = v.length - 1; i >= 0; i--) {
                formula.setVariaveis(v);
            }

            usuario.setIdUsuario(formula.getUsuario().getIdUsuario());
            usuario = usuarioDao.buscarId(usuario);

            if (usuario.getPerfil().equals(PerfilAcesso.ADMINISTRADOR)) {
                formula.getUsuario().setIdUsuario(1);
                usuario.setIdUsuario(1);
            }

            request.setAttribute("usuario", usuario);

 /*parou aqui-->*/ usermod.getUsuario().setIdUsuario(usuario.getIdUsuario());
            userform.getUsuario().setIdUsuario(usuario.getIdUsuario());

            //valida cadastro
            Formula busca = null;
            if (usuario.getPerfil().equals(PerfilAcesso.COMUM)) {
                busca = formulaDao.validarMascara(formula);                                                                                                                                                                                                                                                                                                     
            }

            if (busca != null) {
                userform.getFormula().setIdFormula(busca.getIdFormula());
                userformDao.buscarFormulasId(userform);
                msg = "Calculo já cadastrado a formula: " + busca.getNomeFormula() + ", do módulo: " + userform.getModulo().getNome();

            } else if (formulaDao.validaNome(formula) == false) {
                msg = "Nome de formula já cadastrada";

            } else {
                formula = formulaDao.cadastrar(formula);
                formula = formulaDao.buscarId(formula);

                userform.getUsuario().setIdUsuario(formula.getUsuario().getIdUsuario());
                userform.getFormula().setIdFormula(formula.getIdFormula());
                userform.getModulo().setIdModulo(Integer.parseInt(request.getParameter("txtIdModulo")));
                System.out.println("idModulo: " + Integer.parseInt(request.getParameter("txtIdModulo")));
                userformDao.cadastrar(userform);
                msg = "Cadastrado com sucesso";
            }

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

        } catch (Exception erro) {
            System.out.println("Erro SVT Formula cadastrar: " + erro.getMessage());
            msg = erro.getMessage();
            caminho = "../erro/erro.jsp";
        }

        request.setAttribute("msg", msg);
        return caminho;
    }

}
