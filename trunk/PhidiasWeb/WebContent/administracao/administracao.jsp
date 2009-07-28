<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Documento sem t&iacute;tulo</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="css/rodalinguagem.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="#FFFFCC">
<f:view>

	<style>
.pic {
	margin-bottom: -4px;
	margin-right: 2px;
}
</style>

<table width="100%" height="120" border=0>
	<tr>
		<td height="117"><img src="imagens/img_roda_ling.gif" width="200"
			height="100"></td>
	</tr>
	<tr bgcolor="#FFCC99">
		<td height="22" bgcolor="#FFCC99">
		<div align="center">
		<p align="center"><b><font color="#000033" size="3"
			face="Geneva, Arial, Helvetica, sans-serif">Phidias</font></b></p>
		</div>
		</td>
	</tr>
	</table>
	<h:form>
		<rich:toolBar contentClass="background-color:'#FFFFCC'" styleClass="rich-toolbar">
			<rich:dropDownMenu>
				<f:facet name="label">
					<h:panelGrid cellpadding="0" cellspacing="0" columns="2"
						style="vertical-align:middle" width="200">
						<h:outputText value="Módulo Administração" />
					</h:panelGrid>
				</f:facet>

				<rich:menuGroup value="Cadastro">
					<rich:menuItem submitMode="ajax" value="Formulários"/>
					<rich:menuItem submitMode="ajax" value="Aplicadores"/>
					<rich:menuItem submitMode="ajax" value="Criança"/>
				</rich:menuGroup>
				<rich:menuSeparator id="menuSeparator11" />
				<rich:menuItem submitMode="ajax" value="Sair" />
			</rich:dropDownMenu>

			<rich:dropDownMenu>
				<f:facet name="label">
					<h:panelGrid cellpadding="0" cellspacing="0" columns="2"
						style="vertical-align:middle" width="300">
						<h:outputText value="Módulo de Acompanhamento de Crianças" />
					</h:panelGrid>
				</f:facet>

				<rich:menuItem submitMode="ajax" value="Buscar sessões" target="divSessao" action="#{sessaoBean.listarSessoes}"/>
				
			</rich:dropDownMenu>
		</rich:toolBar>
	</h:form>
	
	<div id="divSessao">
	
	</div>
</f:view>
</body>
</html>
