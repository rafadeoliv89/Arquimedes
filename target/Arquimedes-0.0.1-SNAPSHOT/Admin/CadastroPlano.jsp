<%-- 
    Document   : CadastroUsuario
    Created on : 24/04/2018, 19:08:59
    Author     : Usuario
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/cadastro.css" rel="stylesheet" type="text/css"/>
        <title>Área de cadastro</title>
    </head>
    <body>
        <div class="content">
        <h1>Área de ADM</h1>
        <label> <h2 style="text-align: center" >Cadastro de Planos</h2></label>

        <%
            String msg = (String) request.getAttribute("msg");
            if (msg != null) {
        %>
        <font color="blue"><%=msg%></font>
        <%}%>

        <form action="../controler/ControlePlano" method="POST">
            <p><label>Nome:</label> <input type="text" name="txtNome"></p>
            <p><label>Valor:</label> <input type="text" name="txtValor"></p>
            <p><label>Duração:</label> <input type="text" name="txtDuracao"></p> 

            <input type="submit" value="Cadastrar" name="acao">
        </form>
        <form action="../../Arquimedes/controler/ControleFormula" method="POST">
            <button type="submit" value="Home" name="acao">Voltar</button>
        </form>
        </div>
    </body>
</html>
