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
	<f:subview id="menu">
		<jsp:include page="/common/cabecalho.jsp"></jsp:include>
	</f:subview>

	<h:form>
		<rich:dataTable value="#{sessaoBean.sessoes}" var="sessao" width="750">

			<f:facet name="header">
				<h:outputText value="Sess�es Dispon�veis"></h:outputText>
			</f:facet>
			<rich:column>
				<f:facet name="header">
					<h:outputText value="Sess�o" />
				</f:facet>
				<h:outputText value="#{sessao.id}" />
			</rich:column>
			<rich:column>
				<f:facet name="header">
					<h:outputText value="Jogo" />
				</f:facet>
				<h:outputText value="#{sessao.jogo.name}" />
			</rich:column>
			<rich:column>
				<f:facet name="header">
					<h:outputText value="Fase" />
				</f:facet>
				<h:outputText value="-" />
			</rich:column>
			<rich:column>
				<f:facet name="header">
					<h:outputText value="Aluno" />
				</f:facet>
				<h:outputText value="#{sessao.patient.name}" />
			</rich:column>
			<rich:column>
				<f:facet name="header">
					<h:outputText value="Aplicador" />
				</f:facet>
				<h:outputText value="#{sessao.attendant.name}" />
			</rich:column>
			<rich:column>
				<f:facet name="header">
					<h:outputText value="Especialista" />
				</f:facet>
				<h:outputText value="-" />
			</rich:column>
			<rich:column>
				<f:facet name="header">
					<h:outputText value="Opera��es" />
				</f:facet>
				<h:commandLink value="Iniciar Aplicador" action="#{aplicadorBean.aderirSessaoJogo}"> 
					<f:param name="paramsessaoid" value="#{sessao.id}"></f:param>
				</h:commandLink><br></br>
				<h:commandLink value="Iniciar Especialista" action="#{aplicadorBean.aderirSessaoJogo}"> 
					<f:param name="paramsessaoid" value="#{sessao.id}"></f:param>
				</h:commandLink>
			</rich:column>
		</rich:dataTable>
	</h:form>

</f:view>
</body>
</html>