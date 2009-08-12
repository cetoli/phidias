<!doctype html public "-//w3c//dtd html 4.0 transitional//en">
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<html>
<head>
<title>Enxoval</title>
</head>
<body>
<f:view>
	<h:panelGrid columns="1" id="fase1grid">

		<rich:panel styleClass="width:400px;" id="panelgridfase1">
			<f:facet name="header">
				<h:outputText value="Enxoval" />
			</f:facet>
			<h:form>
				<h:outputText value="Nome do Enxoval.....: " />
				<h:inputText
					value="#{enxovalBean.pieceCollection.pieceCollectionName}" />
				<br />
				<h:outputText value="Descrição do Enxoval: " />
				<h:inputText
					value="#{enxovalBean.pieceCollection.pieceCollectionDesc}" />
				<br />
				<h:outputText value="Tipo do Enxoval.....: " />
				<h:selectOneMenu id="tipoenxoval" value="#{enxovalBean.pieceCollection.pieceCollectionType}"> 
					<f:selectItem id="item1" itemLabel="Pessoas" itemValue="1" /> 
					<f:selectItem id="item2" itemLabel="Animais" itemValue="2" /> 
					<f:selectItem id="item3" itemLabel="Matemática" itemValue="3" /> 
				</h:selectOneMenu>>
    			<br />
				<h:outputText value="Nível de Representação Simbólica: " />
				<h:selectOneMenu id="nivrepsimb" value="#{enxovalBean.pieceCollection.simbType}"> 
					<f:selectItem id="item4" itemLabel="Grande" itemValue="1" /> 
					<f:selectItem id="item5" itemLabel="Médio" itemValue="2" /> 
					<f:selectItem id="item6" itemLabel="Pouco Icônico" itemValue="3" /> 
				</h:selectOneMenu>>
    			<br />
        		<h:commandButton value="Inicial"   action="inicialadm" />
				<h:commandButton value="cadastrar" action="#{enxovalBean.cadastrar}" />
			</h:form>
		</rich:panel>

	</h:panelGrid>

</f:view>
</body>
</html>