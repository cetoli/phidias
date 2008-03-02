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
<%@include file="cabecalho.jsp" %>
	<div id="main">
		<div id="welcome" class="post">
			<h2 class="title">Cadastro do Aplicador</h2>
			
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

<form action="aplicador.do" method="post">
<table>
	<tr>
		<td>Login:</td>
		<td><input type="text" name="login" maxlength="10"/></td>
	</tr>
	<tr>
		<td>Nome:</td>
		<td><input type="text" name="name"  maxlength="40"/></td>
	</tr>
	<tr>
		<td>Profissão:</td>
		<td><input type="text" name="profession"  maxlength="20"/></td>
	</tr>
	<tr>
		<td>Instituição:</td>
		<td><input type="text" name="institution"  maxlength="20"/></td>
	</tr>
		<tr>
		<td colspan="2"><input type="submit" value="Cadastrar"/></td>
	</tr>
	
</table>
		</div>
		
	</div>
	<div id="extra" style="clear: both;"/>


<%@include file="rodape.jsp"%>
</body>
</html>
