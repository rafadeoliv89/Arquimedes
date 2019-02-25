<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="../css/jquery-3.3.1.slim.min.js" type="text/javascript"></script>
        <link href="<%=request.getContextPath()%>/css/cadastro.css" rel="stylesheet" type="text/css"/>
        <title>Calcular</title>
    </head>
    <body>
        <div class="content">     
            <c:forEach var="fmS" items="${formulas}">
                <b> Calcular </b>

                <form action="../controler/ControleFormula" method="post">

                    <p><label>Formula: </label><input name= "nomeForm" disabled type="text" value="${fmS.formula.nomeFormula}"></p>
                    <p><label>Equação: </label><input type="text" disabled value="${fmS.formula.mascaraFormula}"></p>
                    <input name= "formula" type="hidden" value="${fmS.formula.calculoFormula}">
                    <input name= "idFormula" type="hidden" value="${fmS.formula.idFormula}">
                    <br>
                    <table>
                        <tr>
                            <td>
                                <c:forEach var="fmV" items="${fmS.formula.variaveis2}">
                                    <b>${fmV}</b> <input name="${fmV}">
                                    <br>
                                </c:forEach>
                            </td>
                        </tr>
                    </table>
                </c:forEach>
                resultado: <input disabled value="${resultado}">
                <button type="submit" value="Calcular" name="acao">Calcular</button>
                <button type="submit" value="Home" name="acao">Voltar</button>
            </form>
        </div>
    </body>
</html>
