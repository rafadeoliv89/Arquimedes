<%-- 
    Document   : CadastroUsuario
    Created on : 24/04/2018, 19:08:59
    Author     : Usuario
--%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/cadastro.css" rel="stylesheet" type="text/css"/>
        <title>Cadastro de Móulos</title>
    </head>
    <body>
        <div class="flex-container">
        <div class="content">
        <h1>Área de ADM</h1>
        <label><h2 style="text-align: center">Cadastro de Módulos</h2></label>

        <%
            String msg = (String) request.getAttribute("msg");
            if (msg != null) {
        %>
        <font color="blue"><%=msg%></font>
        <%}%>

        <form action="../controler/ControleModulo" method="POST">
            Nome: <input type="text" name="txtNome"><br/>
            Descricao: <input type="text" name="txtDescricao"><br/>

            <input type="submit" value="Cadastrar" name="acao">
        </form>
        <form action="../../Arquimedes/controler/ControleFormula" method="POST">
            <button type="submit" value="Home" name="acao">Voltar</button>
        </form>
        </div>  
        </div>
    </body>
</html>
