/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.arquimedes.autorizacaoAcesso;

import br.arquimedes.model.PerfilAcesso;
import br.arquimedes.model.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Usuario
 */
@WebServlet(name = "AcessoLogado", urlPatterns = {"/AcessoLogado"})
public class AcessoLogado implements Filter {

    @Override
    public void init(FilterConfig filterconfig) throws ServletException{
                
            }
    
 @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
                                                            throws IOException, ServletException {
        
        HttpSession sessaoUsuario = ((HttpServletRequest)request).getSession();
        
        Usuario usuario = (Usuario) sessaoUsuario.getAttribute("usuarioAutenticado");
        
        if(usuario!=null){
            chain.doFilter(request, response);
        }else{
            ((HttpServletResponse)response).sendRedirect("/Arquimedes/erro/naoAutenticado.jsp");
        }
    }

    @Override
    public void destroy() {   
    }
}
