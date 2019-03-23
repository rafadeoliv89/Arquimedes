/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.arquimedes.controler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Usuario
 */
@WebServlet(urlPatterns = {"/controler/*"})
public class ControllerFactory extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("text/html");

        try {
            String acao = request.getParameter("acao");

            String url = request.getRequestURI();
            url = url.replace(request.getContextPath() + "/controler/", "").replaceAll("/", ".");

            String nomeDaClasse = "br.arquimedes.controler.command." + url + acao;

            Class classeAction = Class.forName(nomeDaClasse);
            Command commandAction = (Command) classeAction.newInstance();

            String urlRetorno = commandAction.executar(request, response);
            request.setAttribute("msg", request.getAttribute("msg"));

            request.getRequestDispatcher(urlRetorno).forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(ControllerFactory.class.getName()).log(Level.SEVERE, null, ex);
            request.getRequestDispatcher("../erro/erro.jsp").forward(request, response);
        }
    }
}
