<%-- 
    Document   : header
    Created on : 07/11/2018, 21:08:26
    Author     : Rafael
--%>
<%@page import="java.util.List"%>
<%@page import="br.arquimedes.model.Formula"%>
<%@page import="br.arquimedes.model.Modulo"%>
<%@page import="br.arquimedes.model.Usuario"%>
<%@page import="br.arquimedes.model.PerfilAcesso"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<%=request.getContextPath()%>/css/header.css" rel="stylesheet" type="text/css"/>
        <title>Arquimedes</title>
    </head>
    <body>
        <%
            List<Modulo> listaM = (List<Modulo>) request.getAttribute("listaM");
            Usuario usuario = (Usuario) session.getAttribute("usuarioAutenticado");
            String pagex;
            String hd;
            String hd2;
            if (usuario.getPerfil().equals(PerfilAcesso.ADMINISTRADOR)) {
                hd = "";
                hd2 = "hidden";
                pagex = "Admin/controleUsuario.jsp";
            } else {
                pagex = "atualizarDados.jsp";
                hd = "hidden";
                hd2 = "";
            }
        %>
        <header>
            <div class="flex-container">    
                <ul class="menu">
                        <li><img src="imgs/Arqumides.png" class="img container container_img" alt=""></li>
                        <li><a href="#">Home</a></li>
                        <li><a href="#">Cadastro</a>
                            <ul class="submenu-1"> 
                                <li <%=hd%>><a href="<%=request.getContextPath()%>/Admin/CadastroUsuario.jsp">Novo usuário</a></li>
                                <li <%=hd%>><a> <button type="submit" value="Listar" name="acao">Listar usuários</button></a></li>
                                <li <%=hd2%>><a type="submit" value="Buscar"  name="acao">Alterar dados</a></li>
                                <li <%=hd%>><a href="<%=request.getContextPath()%>/Admin/CadastroUsuario.jsp">Alterar dados Usuário</a></li>
                            </ul>
                        </li>
                    <li><a href="#">Modulos</a>
                        <ul class="submenu-1"> 
                            <li><a href="CadastrarModulo.jsp">Novo modulo</a></li>
                            <li><a href="ListarModulo.jsp">Listar modulos</a></li>
                            <li><a href="EditarModulo.jsp">Editar</a></li>

                        </ul>
                    </li>
                    <li><a href="Login.jsp">Sair</a></li>
                </ul>
            </div> 
        </header>
    </body>
</html>
