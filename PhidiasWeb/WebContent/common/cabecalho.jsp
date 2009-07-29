<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<style>
.pic {
	margin-bottom: -4px;
	margin-right: 2px;
}
</style>


	<table width="100%" height="120" border=0>
		<tr>
			<td height="117"><img src="imagens/img_roda_ling.gif"
				width="200" height="100"></td>
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
		<rich:toolBar contentClass="background-color:'#FFFFCC'"
			styleClass="rich-toolbar" itemSeparator="line">
			<rich:dropDownMenu>
				<f:facet name="label">
					<h:panelGrid cellpadding="0" cellspacing="0" columns="2"
						style="vertical-align:middle" width="200">
						<h:outputText value="Módulo Administração" />
					</h:panelGrid>
				</f:facet>

				<rich:menuGroup value="Cadastro" direction="rigth-down">
					<rich:menuItem submitMode="ajax" value="Formulários" />
					<rich:menuItem submitMode="ajax" value="Aplicadores" />
					<rich:menuItem submitMode="ajax" value="Criança" />
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

				<rich:menuItem submitMode="ajax" value="Buscar sessões"
					action="#{sessaoBean.listarSessoes}" />

			</rich:dropDownMenu>
		</rich:toolBar>
	</h:form>
