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
        <title>Cadastro de usuário</title>
    </head>
    <body>
        <div class="content" >
            <h1>Área de ADM</h1>
            <h2>Cadastro de usuário</h2>

            <%
                String msg = (String) request.getAttribute("msg");
                if (msg != null) {
            %>
            <font color="blue"><%=msg%></font>
            <%}%>

            <form action="../controler/ControleUsuario" method="POST">
                <p><label for="nome_cad">Nome: </label><input type="text" name="txtNome" placeholder="nome"/></p>
                <p><label for="nome_cad">Data nascimento: </label>
                    <input id="nome_cad" name="txtDataNascimento" type="date" placeholder="dd/mm/aaaaa" /></p>
                <p><label for="nome_cad">Sexo</label><br>
                    <input type="radio" name="txtSexo" value="O" checked><label>Outros</label> <br>
                    <input type="radio" name="txtSexo" value="F"><label>Feminino</label> <br>
                    <input type="radio" name="txtSexo" value="M"><label>Masculino</label> <br>
                </p>
                <p><label for="nome_cad">Email: </label><input type="text" name="txtEmail" placeholder="email@gmail.com"/></p>
                <p><label for="nome_cad">CPF: </label><input type="text" name="txtCPF" placeholder="000.000.000-00"/></p>
                <label for="nome_cad">Telefone: </label> <label>DDD:</label>
                	<input width="5px" type="text" name="txtDDD" placeholder="(00)" maxlength="2"/>
                	<input type="text" name="txtTelefone" placeholder="999999999" maxlength="9"/>
                <p><label for="nome_cad">Login: </label><input type="text" name="txtLogin" placeholder="login"/></p>
                <p><label for="nome_cad">Senha: </label><input type="password" name="txtSenha" placeholder="senha"/></p>
                <p><label for="nome_cad">Perfil: </label><select name="optPerfil">
                        <option>COMUM</option>
                        <option>ADMINISTRADOR</option>
                    </select></p>
                <input type="submit" value="Cadastrar" name="acao">
            </form>
            <form action="../../Arquimedes/controler/ControleFormula" method="POST">
                <button type="submit" value="Home" name="acao">Voltar</button>
            </form>
        </div>
    </body>
</html>
