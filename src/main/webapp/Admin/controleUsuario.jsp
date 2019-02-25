<%-- 
    Document   : controleUsuario
    Created on : 03/05/2018, 20:14:25
    Author     : Usuario
--%>

<%@page import="br.arquimedes.model.Usuario"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/cadastro.css" rel="stylesheet" type="text/css"/>
        <title>Controle Usuário</title>
    </head>
    <body>
        <div class="content">
            <h2>Busca Usuário</h2>
            <form action="../../Arquimedes/controler/ControleUsuario" method="POST">
                <p><label>Buscar por:</label> 
                    <select>
                        <option value="login">Nome de usuário</option>
                    </select>
                    <input type="text" name="txtBusca">
                    <input type="submit" value="Buscar" name="acao">
                </p>
                <h2>Alterar dados</h2>
                <%
                    String sts = (String) request.getAttribute("status");
                    String hd;

                    if (sts == "Ativar" || sts == "Inativar") {
                        hd = "";
                    } else {
                        hd = "hidden";
                    }

                    String msg = (String) request.getAttribute("msg");
                    if (msg != null) {
                %>
                <font color="blue"><%=msg%></font>
                <%}%>
                <br>
                <p><label>Nome: </label><input type="text" name="txtNome" value="${usuarioBusca.getNomePessoa()}"></p>
                <p><label>Data Nascimento: </label>
                    <input id="nome_cad" name="txtDataNascimento" type="date" placeholder="dd/mm/aaaaa" value="${usuarioBusca.getDataNascimento()}"/></p>
                <p><label>E-mail: </label><input type="text" name="txtEmail" value="${usuarioBusca.getEmail()}"></p>
                <p><label>CPF: </label><input type="text" name="txtCPF" value="${usuarioBusca.getCPF()}"><br/>
                <p><label>Status: </label> ${nomeStatus}</p>
                <p><label>Perfil: </label><select name="optPerfil" value="${usuarioBusca.getPerfil()}" <%=hd%>>
                        <option>${primeiroPerfil}</option>
                        <option>${segundoPerfil}</option>
                    </select></p>

                <input type="hidden" value="${usuarioBusca.getIdUsuario()}" name="txtIdUsuario"/>

                <input type="submit" value="Alterar" name="acao" <%=hd%>>
                <input type="submit" name="acao" value="${status}" <%=hd%>>
            </form>
            <form action="../../Arquimedes/controler/ControleFormula" method="POST">
                <button type="submit" value="Home" name="acao">Voltar</button>
            </form>
        </div>
    </body>
</html>
