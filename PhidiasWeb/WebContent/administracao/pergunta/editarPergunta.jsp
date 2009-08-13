<!doctype html public "-//w3c//dtd html 4.0 transitional//en">
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<html>
<head>
	<title>Jogo</title>
	<link href="css/rodalinguagem.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="#FFFFCC">
<f:view>
	<f:subview id="menu">
		<jsp:include page="/common/cabecalho2.jsp"></jsp:include>
	</f:subview>		
	<h:form>
		<rich:panel>
			<h:inputHidden value="#{perguntaBean.question.pk.questionID}" />
			<h:inputHidden value="#{perguntaBean.question.pk.questionnaireId}" />
			<h:panelGrid columns="2">
				<rich:column>
							<h:outputText value="Questionário" />	
						</rich:column>
						<rich:column>
							<h:selectOneMenu id="selecioneQuestionario" value="#{perguntaBean.question.pk.questionnaireId}" onchange="this.form.submit()" >
							  <f:selectItems value="#{perguntaBean.questionarios}" />
							</h:selectOneMenu>							
						</rich:column>
						<rich:column>
							<h:outputText value="Pergunta" />	
						</rich:column>
						<rich:column>
							<h:inputText value="#{perguntaBean.question.question}" />
						</rich:column>
						<rich:column>
							<h:outputText value="Tipo de Pergunta" />	
						</rich:column>
						<rich:column>
							<h:inputText value="#{perguntaBean.question.questionType}" />
						</rich:column>
						<rich:column>
							<h:outputText value="Tipo Eval" />	
						</rich:column>
						<rich:column>
							<h:inputText value="#{perguntaBean.question.evalType}" />
						</rich:column>
						<rich:column>
							<h:outputText value="Comentários" />	
						</rich:column>
						<rich:column>
							<h:inputText value="#{perguntaBean.question.comments}" />
						</rich:column>
			</h:panelGrid>
			<h:panelGrid columns="1">
				<rich:column>	
					<h:commandButton value="alterar" action="#{perguntaBean.alterar}" />	
				</rich:column>
			</h:panelGrid>
		</rich:panel>
		<br />
		<h:commandButton  value="Inicial" action="inicialADM"  />
	</h:form>
</f:view>
</body>
</html>