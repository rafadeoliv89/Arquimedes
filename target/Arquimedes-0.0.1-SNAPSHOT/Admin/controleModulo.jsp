<%-- 
    Document   : controleUsuario
    Created on : 03/05/2018, 20:14:25
    Author     : Usuario
--%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="br.arquimedes.model.Modulo"%>
<%@page import="java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<%=request.getContextPath()%>/css/cadastro.css" rel="stylesheet" type="text/css"/>
        <title>Controle Módulo</title>
    </head>
    <body>
        <div class="content">
            <%
                List<Modulo> lista = (List<Modulo>) request.getAttribute("lista");
            %>

            <h2>Busca Módulo</h2>
            <form action="../../Arquimedes/controler/ControleModulo" method="POST">

                <p><label>Módulo</label>
                    <select name="txtBusca" onchange="verificaOpcao(this.value)">
                        <option value="1">Selecione um módulo</option>
                        <c:forEach items="${lista}" var="lista">
                            <option value="${lista.nome}"> ${lista.nome} </option>
                        </c:forEach>
                    </select></p>
                <button id="acao" type="submit" value="Buscar" name="acao">Buscar</button>
                <br/>
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
                <br/>
                <p><label>Nome: </label><input type="text" name="txtNome" value="${moduloBusca.getNome()}"></p>
                <p><label>Descrição: </label><input type="text" name="txtDescricao" value="${moduloBusca.getDescricao()}"></p>
                <input type="hidden" value="${moduloBusca.getIdModulo()}" name="txtIdModulo"/>
                <input type="submit" value="Alterar" name="acao">
                <input type="submit" name="acao" value="${status}" <%=hd%>>
                <br/>
            </form>
            <form action="../../Arquimedes/controler/ControleFormula" method="POST">
                <button type="submit" value="Home" name="acao">Voltar</button>
            </form>
        </div>
    </body>
</html>
