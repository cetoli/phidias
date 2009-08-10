<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Documento sem t&iacute;tulo</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="css/rodalinguagem.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="">
<f:view>

<h:form>
<table width="100%" height="320" border=0>
	<tr>
		<td height="117"><img src="imagens/img_roda_ling.gif" width="200"
			height="100"></td>
	</tr>
	<tr bgcolor="#99CCCC">
		<td height="22" bgcolor="#99CCCC">
		<div align="center">
		<p align="center"><b><font color="#000033" size="3"
			face="Geneva, Arial, Helvetica, sans-serif">Tela de Entrada</font></b></p>
		</div>
		</td>
	</tr>
	<tr">
		<td height="173" align="center" bgcolor="#99CCCC">
		<div align="center">
		<p>&nbsp;</p>
		<table  border=0 align="center"
			cellpadding="0" cellspacing="0">
			<tr>
				<td bgcolor="#99CCCC">
				<p align="right"><font color="#000033" size="2"
					face="Geneva, Arial, Helvetica, sans-serif"> Usuário </font></p>
				</td>
				<td bgcolor="#99CCCC">
				<p><h:inputText maxlength="10" size="10" value="#{usuarioLogin.usuario}"></h:inputText></p>
				</td>
			</tr>
			<tr>
				<td bgcolor="#99CCCC">
				<div align="right"><font color="#000033" size="2"
					face="Geneva, Arial, Helvetica, sans-serif">Senha</font></div>
				</td>
				<td bgcolor="#99CCCC"><h:inputSecret maxlength="10" size="10" value="#{usuarioLogin.senha}"></h:inputSecret></td>
			</tr>
			<tr>
				<td colspan="2">
					<h:commandButton value="Entrar" action="#{usuarioLogin.autenticarUsuario}"></h:commandButton>
				</td>
			
		</table>
		</div>
		</td>
	</tr>
</table>
</h:form>
</f:view>
</body>
</html>
