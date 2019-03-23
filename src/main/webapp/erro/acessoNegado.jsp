<%-- 
    Document   : acessoNegado
    Created on : 27/04/2018, 09:50:08
    Author     : Usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Acesso negado</title>
    </head>
    <body>
        <h1>Você não tem permissão para essa pagina...</h1>
        <br/>
        <form action="../../Arquimedes/controler/ControleFormula" method="POST">
            <button type="submit" value="Home" name="acao">Voltar</button>
        </form>
    </body>
</html>
