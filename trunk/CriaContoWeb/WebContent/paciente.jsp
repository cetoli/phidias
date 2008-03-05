<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<!--
Design by Veronica Tosta
-->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Kuarup</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="./styles/principal.css" rel="stylesheet" type="text/css" />
</head>
<body>

<%@include file="cabecalho.jsp"%>

<%
	if (request.getAttribute("registrook") != null) {
%>
<script type="">
 	alert('<%=request.getAttribute("registrook")%>');
	document.forms[0].reset();
 </script>
<%
	}
%>
<div id="main">
<div id="welcome" class="post">
<h2 class="title">Cadastro do Paciente</h2>
<form action="paciente.do" method="post">
<table>
	<tr>
		<td>Login:</td>
		<td><input type="text" name="login" /></td>
	</tr>
	<tr>
		<td>Nome:</td>
		<td><input type="text" name="name" /></td>
	</tr>
	<tr>
		<td>Data de Nascimento:</td>
		<td><input type="text" name="birthdate" /></td>
	</tr>
	<tr>
		<td>Sexo:</td>
		<td><select name="gender">
			<option value="M">Masculino</option>
			<option value="F">Feminino</option>
		</select></td>
	</tr>
	<tr>
		<td colspan="2"><input type="submit" value="Cadastrar" /></td>
	</tr>
</table>
</form>
</div>
</div>
<div id="extra" style="clear: both;"></div>

</div>
<!-- fecha div corpo do cabecalho.jsp -->

<%@include file="rodape.jsp"%>
</body>
</html>
