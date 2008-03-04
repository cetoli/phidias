<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Kuarup</title>
<link href="./styles/principal.css" rel="stylesheet" type="text/css" />
</head>
<body>
<%@include file="cabecalho.jsp"%>
<div id="main">
<div id="welcome" class="post">
<h2 class="title">Aplicação Paciente</h2>
<div id="login" class="boxed">
<div class="content">
<table>
	<tr>
		<td><applet ARCHIVE="aCriaConto.jar" code="br.ufrj.nce.labase.criaconto.view.attendant.CriaContoAttendant.class" width=1024 height=800></td>
	</tr>
</table>

</div>
</div>
</div>
</div>
<div id="extra" style="clear: both;"></div>

</div>
<!-- fecha div corpo do cabecalho.jsp -->
<%@include file="rodape.jsp"%>
</body>
</html>

