<%-- 
    Document   : listagemDeUsuarios
    Created on : 20/05/2018, 18:15:12
    Author     : Usuario
--%>

<%@page import="br.arquimedes.model.Plano"%>
<%@page import="br.arquimedes.model.Modulo"%>
<%@page import="java.util.List"%>
<%@page import="br.arquimedes.model.Usuario"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/tabelas.css" rel="stylesheet" type="text/css"/>
        <link href="../css/cadastro.css" rel="stylesheet" type="text/css"/>
        <title>Listagem de Planos</title>
    </head>
    <body>
        <div class="content">
            <h2>Listagem de modulos</h2>
            <br/>
            <%
                List<Plano> lista = (List<Plano>) request.getAttribute("lista");
            %>

            <table id="tabelas">
                <thead>
                    <tr>
                        <th><h3>Id</h3></th>
                        <th><h3>Nome</h3></th>
                        <th><h3>Valor</h3></th>
                        <th><h3>Duração (dias)</h3></th>
                        <th><h3></h3></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${lista}" var="lista">
                        <tr>
                            <td>${lista.idPlano}</td>
                            <td>${lista.nomePlano}</td>
                            <td>${lista.valor}</td>
                            <td>${lista.tempoPlano}</td>
                            <td>
                                <form action="../../Arquimedes/controler/ControlePlano" method="POST">
                                    <input type="hidden" value="${lista.getIdPlano()}" name="txtBusca"/>
                                    <button type="submit" value="Buscar"  name="acao">Editar</button>
                                </form> 
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <br/>
            <form action="../../Arquimedes/controler/ControleFormula" method="POST">
                <button type="submit" value="Home" name="acao">Voltar</button>
            </form>
        </div>
    </body>
</html>
