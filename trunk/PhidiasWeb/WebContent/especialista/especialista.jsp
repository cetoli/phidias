<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd" "http://java.sun.com/dtd/facelet-taglib_1_0.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="css/phidias.css"
	title="Default">

</head>
<body>
<f:view>
	<f:subview id="menu">
  		<jsp:include page="/common/cabecalho.jsp"></jsp:include>
	</f:subview>
	
	<h:dataTable border="1" style="width: 1000px" bgcolor="Silver">
		<h:column id="column1">
			<f:facet name="header">
				<center><h:outputText value="Phidias"
					style="font-size: 24px"></h:outputText></center>
			</f:facet>
		</h:column>

	</h:dataTable>
	<table>
	</table>
	<h:panelGrid columns="3" rowClasses="linha1"
		columnClasses="coluna1,coluna1,coluna1">
		<h:panelGrid columns="2" rules="all" border="2">
			<f:facet name="header">
				<h:outputText value="Dados Jogador"></h:outputText>
			</f:facet>

			<h:outputText value="Nome do Jogador:" />
			<h:outputText value="André" />
			<h:outputText value="Idade:" />
			<h:outputText value="10" />
			<h:outputText value="Série Escolar:" />
			<h:outputText value="3" />
			<h:outputText value="Escola:" />
			<h:outputText value="Pedro II" />
		</h:panelGrid>
		<h:panelGrid columns="1" rules="rows" border="1">

			<f:facet name="header">
				<h:outputText value="Estatística"></h:outputText>
			</f:facet>
			<iframe> Teste </iframe>

		</h:panelGrid>

		<h:panelGrid columns="1" rules="rows" border="1">
			<f:facet name="header">
				<h:outputText value="Eventos"></h:outputText>
			</f:facet>

			<a4j:region>
				<h:form>
					<a4j:poll id="poll" interval="500" reRender="poll,grid" />
				</h:form>
			</a4j:region>
			<h:form>
				<h:panelGrid columns="1" width="80%" id="grid">
					<h:inputTextarea id="serverDate" style="font-size:16px"
						value="Server Date: #{estatisticaBean.data}" />
				</h:panelGrid>
			</h:form>
		</h:panelGrid>
	</h:panelGrid>


	<!--//Set of Tabs inside-->
	<rich:tabPanel width="40%" headerAlignment="left">
		<rich:tab label="Fase 1">
		<h:panelGrid columns="1" id="fase1grid">
				<rich:panel styleClass="width:400px;" id="panelgridfase1">
					<f:facet name="header">
						<h:outputText value="Envio estímulo NPC" />
					</f:facet>
					<h:form>
						<h:inputText value=""/><h:commandButton value="enviar"></h:commandButton>
					</h:form>
				</rich:panel>
		</h:panelGrid>
		
		<h:panelGrid columns="1" id="fase2grid">
				<rich:panel styleClass="width:400px;" id="panelgridfase2">
					<f:facet name="header">
						<h:outputText value="Perguntas" />
					</f:facet>
					<h:form>
						<h:inputText value=""/><h:commandButton value="enviar"></h:commandButton>
					</h:form>
				</rich:panel>
		</h:panelGrid>
		</rich:tab>
		<rich:tab label="Fase 2">
			...
			</rich:tab>
		<rich:tab label="Fase 3">
			...
			</rich:tab>
		<rich:tab label="Fase 4">
			...
			</rich:tab>
		<rich:tab label="Fase 5" disabled="true">
			...
		</rich:tab>
	</rich:tabPanel>
</f:view>
</body>
</html>