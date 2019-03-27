<%-- 
    Document   : atualizarDados
    Created on : 20/05/2018, 16:45:45
    Author     : Usuario
--%>
<%@page import="br.arquimedes.model.Usuario"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<%=request.getContextPath()%>/css/cadastro.css" rel="stylesheet" type="text/css"/>
        <title>Atualizar dados</title>
    </head>
    <body>
        <div class="content">
            <h2>Atualizar dados</h2>

            <%
                String msg = (String) request.getAttribute("msg");
                if (msg != null) {
            %>
            <font color="blue"><%=msg%></font>
            <%}%>
            <br>
            <form action="../../Arquimedes/controler/ControleUsuario" method="POST" accept-charset="iso-8859-1,utf-8">
                <p><label>Nome: </label><input type="text" name="txtNome" value="${usuarioBusca.getNomePessoa()}"></p>
                <p><label>Nascimento: </label><input type="date" name="txtDataNascimento" value="${usuarioBusca.getDataNascimento()}"></p>
                <p><label>Email: </label><input type="text" name="txtEmail" value="${usuarioBusca.getEmail()}"></p>
                <p><label>CPF: </label><input type="text" name="txtCPF" value="${usuarioBusca.getCPF()}"></p>
                <p><label>Telefone: </label><label>(DDD) </label><input maxlength="2" type="text" name="txtDDD" value=""/> <input maxlength="9" type="text" name="txtTelefone" value="${usuarioBusca.getTelefone()}"></p>

                <input type="hidden" value="${usuarioBusca.getIdUsuario()}" name="txtIdUsuario"/>
                <input type="submit" value="Atualizar" name="acao">
            </form>
            <form action="../../Arquimedes/controler/ControleFormula" method="POST">
                <button class=botao1" type="submit" value="Home" name="acao"><span>Voltar</span></button>
            </form>
        </div>
    </body>

</html>
