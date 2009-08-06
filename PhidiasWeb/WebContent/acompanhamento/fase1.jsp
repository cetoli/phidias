<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<rich:panel header="Itera��o com a crian�a">
	<h:panelGrid border="0" columns="1" style="tabelaac" cellpadding="0" width="1000" >
		<a4j:form ajaxSubmit="true">
				<h:commandButton value="Mudar de Fase"></h:commandButton>
		</a4j:form>
	</h:panelGrid>
	<h:panelGrid border="0" columns="1" style="tabelaac" cellpadding="0"
		cellspacing="10" bgcolor="#99CCCC" width="1000">
		<a4j:form ajaxSubmit="true"
			oncomplete="alert('Est�mulo enviado com sucesso!');">
			<h:panelGrid border="1" columns="6" style="tabelaac" cellpadding="0" width="100%">
				<h:panelGrid border="0" columns="1" style="tabelaac" cellpadding="0">
					<h:outputText style="texto" value="Pergunta est�mulo:"></h:outputText>
					<h:inputTextarea cols="40" rows="5" value="#{aplicadorBean.fase1.estimuloNPC}"></h:inputTextarea>
					<h:commandButton value="Enviar est�mulo" actionListener="#{aplicadorBean.registrarEstimulo}" />
				</h:panelGrid>
				<h:panelGrid border="0" columns="1" style="tabelaac" cellpadding="0">
					<h:outputText style="texto" value="Resposta de Est�mulo:"></h:outputText>
					<h:inputTextarea cols="40" rows="5"
						value="#{aplicadorBean.fase1.estimuloNPC}"></h:inputTextarea>
					<h:commandButton value="Registrar resposta"
						actionListener="#{aplicadorBean.registrarEstimulo}" />
				</h:panelGrid>
				<h:panelGrid border="0" columns="1" style="tabelaac" cellpadding="0">
					<h:outputText value="Coment�rio:"></h:outputText>
					<h:inputTextarea cols="40" rows="5"
						value="#{aplicadorBean.fase1.respostaJogador}"></h:inputTextarea>
					<h:commandButton value="Registrar"
						action="#{aplicadorBean.registrarRespostaJogador}"></h:commandButton>
				</h:panelGrid>
			</h:panelGrid>
		</a4j:form>
	</h:panelGrid>
</rich:panel>
<br></br>
<rich:panel header="Crivo" style="background-color: white">
	<h:panelGrid border="0" columns="2" style="tabelaac" cellpadding="0"
		cellspacing="0" bgcolor="#99CCCC" width="1000"
		rowClasses="odd-row,even-row">
		<h:outputText style="texto"
			value="Coloca os personagens na posi��o de sujeito."></h:outputText>
		<h:selectOneRadio id="subscription1">
			<f:selectItem itemLabel="Sim" itemValue="S" />
			<f:selectItem itemLabel="N�o" itemValue="N" />
		</h:selectOneRadio>

		<h:outputText style="texto"
			value="Utiliza mais de um complemento para o verbo."></h:outputText>
		<h:selectOneRadio id="subscription2">
			<f:selectItem itemLabel="Sim" itemValue="S" />
			<f:selectItem itemLabel="N�o" itemValue="N" />
		</h:selectOneRadio>
	</h:panelGrid>
</rich:panel>
