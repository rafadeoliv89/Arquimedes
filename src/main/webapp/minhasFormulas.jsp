<%-- 
    Document   : cadastrarFormulas
    Created on : 15/11/2018, 23:39:45
    Author     : Abelino
--%>

<%@page import="java.util.List"%>
<%@page import="br.arquimedes.model.Formula"%>
<%@page import="br.arquimedes.model.Usuario"%>
<%@page import="br.arquimedes.model.Modulo"%>
<%@page import="br.arquimedes.model.PerfilAcesso"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="<%=request.getContextPath()%>/css/tabelas.css" rel="stylesheet" type="text/css"/>
        <link href="<%=request.getContextPath()%>/css/botoes.css" rel="stylesheet" type="text/css"/>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="<%=request.getContextPath()%>/css/jquery-3.3.1.slim.min.js" type="text/javascript"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<%=request.getContextPath()%>/css/min.css" rel="stylesheet" type="text/css"/>
        <title>Minhas Formulas</title>
        
        <script>
            $(document).ready(function () {
                $(".btnVariaveis").click(function () {
                    $(".btnVariaveis").hide();
                    $(".btnParentesesI").hide();
                    $("#raiz").hide();
                    $(".btnSimbolos").show();
                    $(".btnSimbolosSub").show();
                    $(".btnPotencia").hide();
                    $(".btnRaizQuadrada").hide();
                    if ($("#total").val() > 0) {
                        $(".btnParentesesF").show();
                    } else {
                        $(".btnParentesesF").hide();
                    }
                    $(".btnNumeros").hide();
                    $(".btnPonto").hide();
                    if ($("#total").val() <= 0) {
                        $(".btnCadastrar").show();
                    }
                    if ($("#totalpotencia2").val() > 0) {
                        $('#totalpotencia').val(parseInt($('#totalpotencia').val()) + 1);
                    } else if ($("#totalpotencia2").val() > -1) {
                        $(".btnVirgula").hide();


                    }

                    if ($("#totalpotencia").val() > 0) {
                        $(".btnVirgula").show();
                        $('#totalpotencia').val(parseInt($('#totalpotencia').val()) - 1);

                    }




                });
                $(".btnSimbolos").click(function () {
                    $(".btnVariaveis").show();
                    $(".btnParentesesI").show();
                    $(".btnSimbolos").hide();
                    $(".btnSimbolosSub").hide();
                    $(".btnRaizQuadrada").show();
                    $(".btnParentesesF").hide();
                    $(".btnNumeros").show();
                    $(".btnPonto").show();
                    $(".btnCadastrar").hide();
                    $(".btnPotencia").show();
                    $('#totalpotencia').val(parseInt($('#totalpotencia').val()) + 1);
                    if ($("#totalpotencia2").val() > 0) {
                        $(".btnVirgula").hide();

                    } else {
                        $(".btnVirgula").show();


                    }

                });

                $(".btnSimbolosSub").click(function () {
                    $(".btnVariaveis").show();
                    $(".btnParentesesI").show();
                    $(".btnSimbolosSub").hide();
                    $("#raiz").show();
                    $(".btnParentesesF").hide();
                    $(".btnNumeros").show();
                    $(".btnPonto").show();
                    $(".btnCadastrar").hide();
                });


                $(".btnRaizQuadrada").click(function () {

                    $(".btnVariaveis").show();
                    $(".btnParentesesI").hide();
                    $(".btnSimbolos").hide();
                    $(".btnNumeros").show();
                    $(".btnPonto").show();



                });
                $(".btnParentesesF").click(function () {
                    if ($("#total").val() <= 0) {
                        $(".btnParentesesF").hide();
                        $(".btnNumeros").show();
                        $(".btnPonto").show();
                        $(".btnCadastrar").show();
                        $(".btnRaizQuadrada").hide();
                        $(".btnPotencia").hide();

                    }

                });
                $(".btnParentesesI").click(function () {
                    $(".btnParentesesF").hide();
                    if ($("#total").val() >= 1) {
                        $(".btnCadastrar").hide();
                        $(".btnNumeros").show();
                        $(".btnPonto").show();
                        $(".btnSimbolosSub").show();

                    }

                });

                $(".btnNumeros").click(function () {
                    $(".btnVariaveis").hide();
                    $(".btnSimbolos").show();
                    $(".btnSimbolosSub").show();
                    $(".btnPotencia").hide();
                    $(".btnParentesesI").hide();
                    $(".btnRaizQuadrada").hide();
                    if ($("#total").val() > 0) {
                        $(".btnParentesesF").show();
                    } else {
                        $(".btnParentesesF").hide();
                    }
                    if ($("#total").val() <= 0) {
                        $(".btnCadastrar").show();
                    }
                });

                $(".btnPonto").click(function () {
                    $(".btnVariaveis").hide();
                    $(".btnPonto").hide();
                    $(".btnSimbolos").hide();
                    $(".btnSimbolosSub").hide();

                    $(".btnParentesesI").hide();
                    $("#raiz").hide();
                });

                $(".btnVirgula").click(function () {
                    $(".btnVariaveis").show();
                    $(".btnPonto").hide();
                    $(".btnSimbolos").show();
                    $(".btnSimbolosSub").show();
                    $(".btnParentesesI").hide();
                    $(".btnNumeros").show();
                    $(".btnSimbolos").hide();
                    $(".btnVirgula").hide();
                    $("#raiz").hide();
                    $('#totalpotencia').val(parseInt($('#totalpotencia').val()) - 1);
                    $('#totalpotencia2').val(parseInt($('#totalpotencia2').val()) - 1);

                });

                $(".btnIniciar").click(function () {
                    $(".btnVariaveis").show();
                    $(".btnPonto").hide();
                    $(".btnSimbolos").hide();
                    $(".btnParentesesF").hide();
                    $("#raiz").hide();
                    $(".btnIniciar").hide();
                    $(".btnVirgula").hide();

                });

                $(".btnPotencia").click(function () {
                    if ($("#total").val() <= 0) {
                        $(".btnParentesesF").hide();
                        $(".btnNumeros").show();
                        $(".btnPonto").show();
                        $(".btnCadastrar").show();
                        $(".btnSimbolos").hide();
                        $('#total').val(parseInt($('#total').val()) + 1);
                        $('#totalpotencia').val(parseInt($('#totalpotencia').val()) + 1);
                        $('#totalpotencia2').val(parseInt($('#totalpotencia2').val()) + 1);

                    }
                });


            });
        </script>

    </head>
    <body>
        <div class="content">
            <%
                List<Modulo> listaM = (List<Modulo>) request.getAttribute("listaM");
                List<Formula> listaF = (List<Formula>) request.getAttribute("listaF");
                Usuario usuario = (Usuario) request.getAttribute("usuario");
            %>

            <h2>Minhas Formulas</h2>
            <br>
            <%
                String msg = (String) request.getAttribute("msg");
                if (msg != null) {
            %>
            <font color="blue"> <%=msg%></font>
            <%}%>

            <h3>Cadastro de Formulas</h3>
            <form action="../controler/ControleFormula" method="POST" accept-charset="iso-8859-1,utf-8">
                <p><label>Nome: </label><input name= "txtNome" type="text" value=""></p>
                <p><label>Descrição: </label><input name= "txtDescricao" type="text" value=""></p>
                <p><label>Módulo:</label>
                    <select name="txtIdModulo" onchange="dadosModulo(this.value)">
                        <option value="2" >Selecione um módulo</option>
                        <c:forEach items="${listaM}" var="listaM">
                            <option value="${listaM.modulo.idModulo}"> ${listaM.modulo.nome} </option>
                        </c:forEach>
                    </select></p>
                <p><label>Formula: </label><input id="formulaJava" name="txtMascara" type="text" value=""></p>

                <input type="hidden" value="" id="formula" name= "txtFormula" >
                <input type="hidden" value="" id= "variaveisForm" name= "txtVariaveis">
                <input type="hidden" value="${usuario.getIdUsuario()}" name="txtIdUsuario">

                <button class="botao1" type="submit" value="Cadastrar"  name="acao"><span>Cadastrar</span></button>
                <button class="botao1" type="submit" value="Home" name="acao"><span>Voltar</span></button>
            
            </form>
            <br>
            <button class="btnIniciar" name="Iniciar"  value="Iniciar"><span>Iniciar Criação</span></button>
            <br>    
            <table>
                <tr id= "numeros" >
                <button class="btnNumeros"  name="0"  value="0">0</button>
                <button class="btnNumeros"  name="1"  value="1">1</button>
                <button class="btnNumeros"  name="2"  value="2">2</button>
                <button class="btnNumeros"  name="3"  value="3">3</button>
                <button class="btnNumeros"  name="4"  value="4">4</button>
                <button class="btnNumeros"  name="5"  value="5">5</button>
                <button class="btnNumeros"  name="6"  value="6">6</button>
                <button class="btnNumeros"  name="7"  value="7">7</button>
                <button class="btnNumeros"  name="8"  value="8">8</button>
                <button class="btnNumeros"  name="9"  value="9">9</button>
                </tr>  
                <tr id= "ponto" >
                <button class="btnPonto"  name="."  value=".">.</button>
                </tr> 
                <br>
                <tr id= "variaveis" >
                <button class="btnVariaveis"  name="a"  value="a">a</button>
                <button class="btnVariaveis"  name="b"  value="b">b</button>
                <button class="btnVariaveis"  name="c"  value="c">c</button>
                <button class="btnVariaveis"  name="d"  value="d">d</button>
                <button class="btnVariaveis"  name="e"  value="e">e</button>
                <button class="btnVariaveis"  name="f"  value="f">f</button>
                <button class="btnVariaveis"  name="g"  value="g">g</button>
                <button class="btnVariaveis"  name="h"  value="h">h</button>
                <button class="btnVariaveis"  name="i"  value="i">i</button>
                <button class="btnVariaveis"  name="j"  value="j">j</button>
                <button class="btnVariaveis"  name="k"  value="k">k</button>
                <button class="btnVariaveis"  name="l"  value="l">l</button>
                <button class="btnVariaveis"  name="m"  value="m">m</button>
                <button class="btnVariaveis"  name="n"  value="n">n</button>
                <button class="btnVariaveis"  name="o"  value="o">o</button>
                <button class="btnVariaveis"  name="p"  value="p">p</button>
                <button class="btnVariaveis"  name="q"  value="q">q</button>
                <button class="btnVariaveis"  name="r"  value="r">r</button>
                <button class="btnVariaveis"  name="s"  value="s">s</button>
                <button class="btnVariaveis"  name="t"  value="t">t</button>
                <button class="btnVariaveis"  name="u"  value="u">u</button>
                <button class="btnVariaveis"  name="v"  value="v">v</button>
                <button class="btnVariaveis"  name="x"  value="x">x</button>
                <button class="btnVariaveis"  name="w"  value="w">w</button>
                <button class="btnVariaveis"  name="y"  value="y">y</button>
                <button class="btnVariaveis"  name="z"  value="z">z</button>
                </tr>  
                <br>
                <tr id= "simbolos" >
                <button class="btnSimbolos"  name="+"  value="+">+</button>
                <button class="btnSimbolosSub"  name="-"  value="-">-</button>
                <button class="btnSimbolos"  name="*"  value="*">*</button>
                <button class="btnSimbolos"  name="/"  value="/">/</button>
                <br>
                <tr id= "parentesesI" >
                <button class="btnParentesesI" onclick="$('#total').val(parseInt($('#total').val()) + 1)" name="("  value="(">(</button>
                </tr> 
                <tr id= "parentesesF" >
                <button class="btnParentesesF" onclick="$('#total').val(parseInt($('#total').val()) - 1)" name=")"  value=")">)</button>
                </tr> 
                <input type="hidden" id="total" value="0"></input>

            </table>
            <br>

            <h3>Minhas formulas</h3>
            <table id="tabelas">
                <thead>
                    <tr>
                        <th><h3>Nome</h3></th>
                        <th><h3>Formula</h3></th>
                        <th><h3>Modulo</h3></th>
                        <th><h3>Descrição</h3></th>
                        <th><h3></h3></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listaF}" var="listaF">
                        <tr>
                            <td>${listaF.formula.nomeFormula}</td>
                            <td>${listaF.formula.mascaraFormula}</td>
                            <td>${listaF.modulo.nome}</td>
                            <td>${listaF.formula.descricao}</td>
                            <td>
                                <form action="../controler/ControleFormula" method="POST">
                                    <input type="hidden" value="${usuario.getIdUsuario()}" name="txtIdUsuario"/>
                                    <input type="hidden" value="${listaF.formula.idFormula}" name="txtIdFormula"/>
                                    <button class="botao2" type="submit" value="Inativar"  name="acao">Excluir</button>
                                </form> 
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <b> </b>   

            <script>
                $(document).ready(function () {
                    $('.btnSimbolos').click(simboloSelecionado);
                    $('.btnSimbolosSub').click(simboloSelecionado);
                    $('.btnNumeros').click(simboloSelecionado);
                    $('.btnParentesesI').click(simboloSelecionado);
                    $('.btnParentesesF').click(simboloSelecionado);
                    $('.btnPonto').click(simboloSelecionado);
                    $('.btnRaizQuadrada').click(simboloSelecionado);
                    $('.btnPotencia').click(simboloSelecionado);
                    $('.btnVirgula').click(simboloSelecionado);
                    $('.btnPi').click(simboloSelecionado);



                });
                function simboloSelecionado(event) {
                    var variavel = event.target.value;
                    var variavel2 = event.target.name;
                    var inputFormula = $('#formula');
                    var inputMatematica = $('#formulaJava');
                    inputFormula.val(inputFormula.val() + variavel);
                    inputMatematica.val(inputMatematica.val() + variavel2);
                }
            </script>
            <script>
                const MY_ARRAY = [];
                $(document).ready(function () {
                    $('.btnVariaveis').click(simboloSelecionado2);
                });
                function simboloSelecionado2(event) {
                    var variavel = event.target.value;
                    var inputVariaveis = $('#variaveisForm');
                    var inputFormulaV = $('#formula');
                    var inputFormulaJava = $('#formulaJava');
                    var inputNomeDaFormula = $("#nomeForm");
                    inputFormulaV.val(inputFormulaV.val() + variavel);
                    inputFormulaJava.val(inputFormulaJava.val() + variavel);

                    if ($.inArray(variavel, MY_ARRAY) === -1) {
                        MY_ARRAY.push(variavel); //["A"]

                        if ($('#variaveisForm').val() === "") {
                            inputVariaveis.val(inputVariaveis.val() + variavel);
                        } else {
                            inputVariaveis.val(inputVariaveis.val() + "," + variavel);
                        }
                    }
                }
            </script>
        </div>
    </body>
</html>
