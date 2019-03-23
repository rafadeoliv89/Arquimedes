<%-- 
    Document   : controleUsuario
    Created on : 03/05/2018, 20:14:25
    Author     : Usuario
--%>
<%@page import="br.arquimedes.model.UserMod"%>
<%@page import="br.arquimedes.model.Usuario"%>
<%@page import="br.arquimedes.model.Modulo"%>
<%@page import="br.arquimedes.model.Plano"%>
<%@page import="java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<%=request.getContextPath()%>/css/tabelas.css" rel="stylesheet" type="text/css"/>
        <link href="<%=request.getContextPath()%>/css/cadastro.css" rel="stylesheet" type="text/css"/>
        <title>Aquisição de Módulos</title>
    </head>
    <body>
        <div class="content">
            <%
                List<Modulo> listaM = (List<Modulo>) request.getAttribute("listaM");
                List<Plano> listaP = (List<Plano>) request.getAttribute("listaP");
                List<UserMod> listaU = (List<UserMod>) request.getAttribute("listaU");
                Usuario usuario = (Usuario) session.getAttribute("usuario");
            %>

            <h2>Selecionar Modulo e Plano</h2>
            <%
                String msg = (String) request.getAttribute("msg");
                if (msg != null) {
            %>
            <font color="blue"> <%=msg%></font>
            <%}%>
            <form action="../../Arquimedes/controler/ControleModulo" method="POST">

                <p><label>Módulo: </label>
                    <select name="txtIdModulo" onchange="dadosModulo(this.value)">
                        <option>Selecione um modulo</option>
                        <c:forEach items="${listaM}" var="listaM">
                            <option value="${listaM.idModulo}"> ${listaM.nome} </option>
                        </c:forEach>
                    </select></p>
                <p><label>Descrição: </label>
                    <input type="text" name="txtDescricao" value="" disabled>
                </p>
                <p><label>Plano: </label>
                    <select name="txtIdPlano">
                        <option>Selecione um plano</option>
                        <c:forEach items="${listaP}" var="listaP">
                            <option value="${listaP.idPlano}"> ${listaP.nomePlano} </option>
                        </c:forEach>
                    </select>
                </p>
                <p><label>Plano: </label>
                    <input type="text" name="txtValor" value="" disabled>
                </p>
                <p><label>Duração: </label>
                    <input type="text" name="txtDuracao" value="" disabled>
                </p>     
                <br/>     
                <input type="hidden" value="${usuario.getIdUsuario()}" name="txtIdUsuario"/>
                <button type="submit" value="Contratar" name="acao">Contratar</button>
                <br/>
            </form>
            <form action="../../Arquimedes/controler/ControleFormula" method="POST">
                <button type="submit" value="Home" name="acao">Voltar</button>
            </form>
            <br/>
            <br/>
            <br/>
            <h3>Meus modulos</h3>
            <br/>
            <table id="tabelas">
                <thead>
                    <tr>
                        <th><h3>Id</h3></th>
                        <th><h3>Nome</h3></th>
                        <th><h3>Descrição</h3></th>
                        <th><h3>Plano</h3></th>
                        <th><h3>Duração</h3></th>
                        <th><h3>Data Expiração</h3></th>
                        <th><h3>Encerrar</h3></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listaU}" var="listaU">
                        <tr>
                            <td>${listaU.modulo.idModulo}</td>
                            <td>${listaU.modulo.nome}</td>
                            <td>${listaU.modulo.descricao}</td>
                            <td>${listaU.plano.nomePlano}</td>
                            <td>${listaU.dataContrato}</td>
                            <td>${listaU.dataExpiracao}</td>
                            <td>
                                <form action="../../Arquimedes/controler/ControleModulo" method="POST">
                                    <input type="hidden" value="${usuario.getIdUsuario()}" name="txtIdUsuario"/>
                                    <input type="hidden" value="${listaU.modulo.idModulo}" name="txtIdModulo"/>
                                    <button type="submit" value="Encerrar"  name="acao">Encerrar</button>
                                </form> 
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
