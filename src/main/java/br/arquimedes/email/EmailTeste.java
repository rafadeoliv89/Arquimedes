/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.arquimedes.email;

import br.arquimedes.model.Modulo;
import br.arquimedes.model.Plano;
import br.arquimedes.model.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Usuario
 */
@WebServlet(name = "EmailTeste", urlPatterns = {"/EmailTeste"})
public class EmailTeste extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            Usuario usuario = new Usuario();
            Modulo modulo = new Modulo();
            Plano plano = new Plano();

            usuario.setNomePessoa("douglinha");
            usuario.setEmail("cezar_accacio@hotmail.com");
            modulo.setNome("Matematica");
            plano.setNomePlano("Trimestral");
            EmailFimPlano mail = new EmailFimPlano(usuario, modulo, plano);
            mail.enviar();
    }
}
