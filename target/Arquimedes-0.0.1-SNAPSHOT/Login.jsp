<%-- 
    Document   : Login
    Created on : 24/04/2018, 20:58:39
    Author     : Usuario
--%>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<%=request.getContextPath()%>/css/min.css" rel="stylesheet" type="text/css"/>
        <title>Arquimedes login</title>
    </head>
    <body>
        <div class="container" >
            <a class="links" id="paracadastro"></a>
            <a class="links" id="paralogin"></a>
            <div class="content">      
                <!--FORMULÁRIO DE LOGIN-->
                <div id="login">
                    <form action="ControleAcesso" method="post">
                        <h1>Login</h1>
                        
                            <%
                                String msg = (String) request.getAttribute("msg");
                                if (msg != null) {
                            %>
                            <font color="red"> <%=msg%></font>
                            <%}%>

                        <p><label for="nome_login">Login</label></p>
                            <input id="nome_login" required="required" placeholder="Insira seu login" type="text" name="txtLogin"><br/> 
                        

                        <p><label for="email_login">Senha</label> </p>
                            <input id ="email_login" required="required" placeholder="senha" type="password" name="txtSenha"><br/>
                       

                
                            <input type="submit" value="Entrar" name="acao"value="Entrar"/>
                  

                        <p class="link">
                            Ainda não tem conta?
                            <a href="novoUsuario.jsp">Cadastrar-se</a>
                        </p>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
