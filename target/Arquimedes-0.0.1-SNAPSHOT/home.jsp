<%-- 
    Document   : home
    Created on : 26/04/2018, 21:04:14
    Author     : Usuario
--%>
<%@page import="java.util.List"%>
<%@page import="br.arquimedes.model.Formula"%>
<%@page import="br.arquimedes.model.Modulo"%>
<%@page import="br.arquimedes.model.Usuario"%>
<%@page import="br.arquimedes.model.PerfilAcesso"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<%=request.getContextPath()%>/css/tabelas.css" rel="stylesheet" type="text/css"/>
        <link href="<%=request.getContextPath()%>/css/botoes.css" rel="stylesheet" type="text/css"/>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="<%=request.getContextPath()%>/css/jquery-3.3.1.slim.min.js" type="text/javascript"></script>
        <title>Arquimedes</title>
    </head>
    <body>
        <div class="content">
            <%
                List<Modulo> listaM = (List<Modulo>) request.getAttribute("listaM");
                Usuario usuario = (Usuario) session.getAttribute("usuarioAutenticado");
                if (usuario != null) {
            %>
            <h2>Seja Bem vindo <%= usuario.getNomePessoa()%></h2>
            <%}%>
            <%  String pagex;
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
                }%>
            <form action="<%=request.getContextPath()%>/controler/ControleUsuario" method="POST">
                <a href="<%=request.getContextPath()%>/Admin/CadastroUsuario.jsp"><button class="botao1" type="button" <%=hd%> ><span>Novo Usuário</span></button></a>
                <button class="botao1" type="submit" value="Listar" name="acao" <%=hd%>><span>Listar usuários</span></button>
                <button class="botao1" type="submit" value="Buscar"  name="acao" <%=hd2%>><span>Alterar dados</span></button>
                <a href="<%=request.getContextPath()%>/<%=pagex%>"><button class="botao1" type="button" <%=hd%>><span>Alterar dados Usuário</span></button></a>
            </form>
            <br>
            <form action="<%=request.getContextPath()%>/controler/ControleModulo" method="POST">
                <button class="botao1" type="submit" value="Adquirir"  name="acao" <%=hd2%>><span>Meus Modulos</span></button>
                <a href="<%=request.getContextPath()%>/Admin/CadastroModulo.jsp"><button class="botao1" type="button" <%=hd%> ><span>Novo Modulo</span></button></a>
                <button class="botao1" type="submit" value="Listar" name="acao"<%=hd%>><span>Listar Modulos</span></button>
                <button class="botao1" type="submit" value="Selecionar" name="acao"<%=hd%>><span>Alterar dados Modulo</span></button>
            </form>
            <br>
            <form action="<%=request.getContextPath()%>/controler/ControlePlano" method="POST">
                <a href="<%=request.getContextPath()%>/Admin/CadastroPlano.jsp"><button class="botao1" type="button" <%=hd%> ><span>Novo Plano</span></button></a> 
                <button class="botao1" type="submit" value="Listar" name="acao"<%=hd%>><span>Listar Planos</span></button>
                <button class="botao1" type="submit" value="Selecionar" name="acao"<%=hd%>><span>Alterar dados Plano</span></button>
            </form>
            <br>
            <form action="<%=request.getContextPath()%>/controler/ControleFormula" method="POST">
                <button class="botao1" type="submit" value="NovaFormula" name="acao"><span>Nova Formula</span></button>
                <button class="botao1" type="submit" value="Listar" name="acao" hidden><span>Listar formulas</span></button>
                <button class="botao1" type="submit" value="Listar2"  name="acao" hidden><span>Alterar dados Formula</span></button>
            </form>
            <br>
            <!Form de lista módulo com busca>
            <h3>Minhas formulas</h3>
            <table id="tabelas">
                <thead>
                    <tr>
                        <th><h4>Nome</h4></th>
                        <th><h4>Formula</h4></th>
                        <th><h4>Descrição</h></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <c:forEach items="${listaM}" var="userMod">
                            <c:forEach items="${userMod.modulo.formulas}" var="formula">
                                <td>${formula.getNomeFormula()}</td>
                                <td>${formula.getMascaraFormula()}</td>
                                <td>${formula.getDescricao()}</td>
                                <td>
                                    <form action="<%=request.getContextPath()%>/controler/ControleFormula" method="POST">
                                        <input type="hidden" value="${usuario.getIdUsuario()}" name="txtIdUsuario"/>
                                        <input type="hidden" value="${formula.getIdFormula()}" name="txtIdFormula"/>
                                        <button class="botao2" type="submit" value="Calculadora"  name="acao">Calcular</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:forEach>
                </tbody>
            </table>
            <b> </b>   
            <a href="<%=request.getContextPath()%>/ControleAcesso?acao=Sair"><button type=button>Logout</button></a>
        </div>
    </body>
</html>
