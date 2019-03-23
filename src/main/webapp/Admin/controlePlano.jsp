<%-- 
    Document   : controleUsuario
    Created on : 03/05/2018, 20:14:25
    Author     : Usuario
--%>

<%@page import="br.arquimedes.model.Plano"%>
<%@page import="java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/cadastro.css" rel="stylesheet" type="text/css"/>
        <title>Controle Plano</title>
    </head>
    <body>
        <div class="content">
            <%
                List<Plano> lista = (List<Plano>) request.getAttribute("lista");
            %>

            <h2>Busca Plano</h2>
            <form action="../../Arquimedes/controler/ControlePlano" method="POST">

                <p><label>Módulo</label>
                    <select name="txtBusca">
                        <option>Selecione um plano</option>
                        <c:forEach items="${lista}" var="lista">
                            <option value="${lista.idPlano}"> ${lista.nomePlano} </option>
                        </c:forEach>
                    </select></p>

                <button type="submit" value="Buscar" name="acao">Buscar</button>
                <br/>
                <br/>
                <h2>Alterar dados</h2>
                <%
                    String msg = (String) request.getAttribute("msg");
                    if (msg != null) {
                %>
                <font color="blue"><%=msg%></font>
                <%}%>
                <br/>
                <p><label>Nome: </label><input type="text" name="txtNome" value="${Busca.getNomePlano()}"></p>
                <p><label>Valor: </label><input type="text" name="txtValor" value="${Busca.getValor()}"></p>
                <p><label>Duração: </label><input type="text" name="txtDuracao" value="${Busca.getTempoPlano()}"></p>
                <input type="hidden" value="${Busca.getIdPlano()}" name="txtIdPlano"/>
                <br/>            
                <button type="submit" value="Alterar" name="acao">Alterar</button>
                <br/>
            </form>
            <form action="../../Arquimedes/controler/ControleFormula" method="POST">
                <button type="submit" value="Home" name="acao">Voltar</button>
            </form>
        </div>
    </body>
</html>
