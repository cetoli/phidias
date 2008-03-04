<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<!--
Design by Veronica Tosta
-->
<%@page import="java.util.List"%>
<%@page import="br.ufrj.nce.labase.phidias.communication.bean.PatientBean"%>
<%@page import="br.ufrj.nce.labase.phidias.persistence.model.Patient"%>
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
<h2 class="title">Listagem de Pacientes</h2>

<table border="1">

	<tr>
		<td><b>Login</b></td>
		<td><b>Nome</b></td>
	</tr>
	<%
		if (request.getAttribute("listagem") != null) {
			List listagem = (List) request.getAttribute("listagem");
			Patient patient;
			for (int i = 0; i < listagem.size(); i++) {
				patient = (Patient) listagem.get(i);
	%>

	<tr>
		<td><%= patient.getId() %></td>
		<td><%= patient.getName() %></td>
	</tr>
	<%
			}
		}
	%>

</table>
</div>
</div>
<div id="extra" style="clear: both;"></div>

</div>
<!-- fecha div corpo do cabecalho.jsp -->

<%@include file="rodape.jsp"%>
</body>
</html>
