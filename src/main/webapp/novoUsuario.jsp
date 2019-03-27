<%-- 
    Document   : novoUsuario
    Created on : 29/04/2018, 10:42:00
    Author     : Usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<%=request.getContextPath()%>/css/cadastro.css" rel="stylesheet" type="text/css"/>
        <title>Cadastro de usuário</title>
    </head>
    <body>
        <div class="container" >
            <a class="links" id="paracadastro"></a>
            <a class="links" id="paralogin"></a>
            <div id="cadastro">
                <h1>Cadastro</h1>
                <form action="../../Arquimedes/controler/ControleUsuario" method="POST">

                    <%
                        String msg = (String) request.getAttribute("msg");
                        if (msg != null) {
                    %>
                    <font color="blue"><%=msg%></font>
                    <%}%>



                    <input id="nome_cad" name="txtNome" type="text"
                           placeholder="nome" />
                    <p><label for="nome_cad">Nome</label></p>

                    <input id="nome_cad" name="txtDataNascimento" type="date" placeholder="Data nascimento" /><br>
                    <p><label for="nome_cad">Data nascimento</label></p>

                    <p><label for="nome_cad">Sexo</label><br>
                        <input type="radio" name="txtSexo" value="O" checked><label>Outros</label> <br>
                        <input type="radio" name="txtSexo" value="F"><label>Feminino</label> <br>
                        <input type="radio" name="txtSexo" value="M"><label>Masculino</label> <br>
                    </p>

                    <input id="email_cad" name="txtEmail" type="email"
                           placeholder="contato@exemplo.com"/><br>
                    <p><label for="email_cad">Seu e-mail</label></p>

                    <input id="nome_cad" name="txtCPF" type="text"
                           placeholder="CPF" /><br>
                    <p><label for="nome_cad">CPF</label></p> 
					<p>	<input maxlength="2" type="text" name="txtDDD" value=""/>
						<label>(DDD) </label>
						<input maxlength="9" type="text" name="txtTelefone">
						<label>Telefone: </label></p>
                    <input id="nome_cad" name="txtLogin" type="text"
                           placeholder="Login" /><br>                   
                    <p><label for="nome_cad">Login</label></p>

                    <input id="senha_cad" name="txtSenha" type="password" placeholder="ex. 1234"/><br>
                    <p><label for="senha_cad">Sua senha</label></p>

                    <input type="submit" value="Cadastrar" name="acao">

                    <p class="link">
                        <a href="../../Arquimedes/Login.jsp">Efetuar login</a>
                    </p>
                </form>
            </div> 
        </div>
    </body>
</html>
