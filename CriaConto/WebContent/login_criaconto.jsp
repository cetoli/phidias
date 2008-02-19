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
 <%@include file="cabecalho.jsp" %>
	<div id="main">
		<div id="welcome" class="post">
			<h2 class="title">Identificação</h2>
			<div id="login" class="boxed">
				<div class="content">
					<form id="login" method="post" action="appletCriaConto">
						<fieldset>
						<label for="inputtext1">Aplicador</label>
						<input id="inputtext1" type="text" name="aplicador" value="" /><br/>
						<label for="inputtext1">Paciente</label>
						<input id="inputtext1" type="text" name="paciente" value="" /><br/>
						<input id="inputsubmit1" type="submit" name="ok" value="Ok" />
						</fieldset>
					</form>
				</div>
			</div>
		</div>
	</div>	
  <div id="extra" style="clear: both;"></div>
	
</div> <!-- fecha div corpo do cabecalho.jsp -->
<%@include file="rodape.jsp"%>	
</body>
</html>