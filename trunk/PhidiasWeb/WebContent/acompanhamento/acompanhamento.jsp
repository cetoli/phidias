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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<style type="text/css">
.rich-tabpanel {
	background-color: white;
}

.rich-tabpanel-content {
	background-color: #99CCCC;
}

.rich-panel-body {
	background-color: #99CCCC;
}

.odd-row {
	background-color: #FFFFCC;
}

.even-row {
	background-color: #99CCCC;
}
</style>
</head>
<body bgcolor="">
<f:view>

	<f:subview id="menu">
		<%@ include file="/common/cabecalho.jsp"%>
	</f:subview>

	<h2>Módulo de Acompanhamento</h2>
	<rich:panel header="Dados Gerais">
		<h:panelGrid border="0" columns="2" style="tabelaac" cellpadding="0"
			width="1000">
			<rich:panel header="Aplicação">
				<h:panelGrid border="0" columns="4" style="tabelaac" cellpadding="0" cellspacing="0">
					<h:outputText style="texto" value="Nome Aplicação:"></h:outputText>
					<h:outputText style="texto" value="Roda da Linguagem"></h:outputText>

					<h:outputText style="texto" value="Objetivo:"></h:outputText>
					<h:outputText style="texto" value="Trabalhar a capacidade da criança de formação de frases."></h:outputText>
				</h:panelGrid>
			</rich:panel>

			<rich:panel header="Jogador">
				<h:panelGrid border="0" columns="4" style="tabelaac" cellpadding="0" >
					<h:outputText style="texto" value="Nome criança:"></h:outputText>
					<h:outputText style="texto" value="Andre"></h:outputText>

					<h:outputText style="texto" value="Idade da criança:"></h:outputText>
					<h:outputText style="texto" value="7"></h:outputText>

					<h:outputText style="texto" value="Instituição:"></h:outputText>
					<h:outputText style="texto" value="UFRJ"></h:outputText>

					<h:outputText style="texto" value="Série:"></h:outputText>
					<h:outputText style="texto" value="7"></h:outputText>
				</h:panelGrid>
			</rich:panel>

		</h:panelGrid>

		<h:panelGrid border="0" columns="1" style="tabelaac" cellpadding="0">
		<rich:panel header="Eventos">
				<h:inputTextarea style="texto" value="7" cols="150" rows="5"></h:inputTextarea>
		</rich:panel>
		</h:panelGrid>


	</rich:panel>
	<br></br>

	<rich:tabPanel width="40%" headerAlignment="left" switchType="ajax">
		<rich:tab label="Fase 1" switchType="ajax">
			<f:subview id="fase1">
				<%@ include file="fase1.jsp"%>
			</f:subview>
		</rich:tab>
		<rich:tab label="Fase 2" disabled="true">
			<f:subview id="fase2">
				<%@ include file="fase1.jsp"%>
			</f:subview>
		</rich:tab>
		<rich:tab label="Fase 3" disabled="true">
			<f:subview id="fase3">
				<%@ include file="fase1.jsp"%>
			</f:subview>
		</rich:tab>
		<rich:tab label="Fase 4" disabled="true">
			<f:subview id="fase4">
				<%@ include file="fase1.jsp"%>
			</f:subview>
		</rich:tab>
		<rich:tab label="Fase 5" disabled="true">
			<f:subview id="fase5">
				<%@ include file="fase1.jsp"%>
			</f:subview>
		</rich:tab>

	</rich:tabPanel>
</f:view>
</body>
</html>