<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<style>
.coluna1 {   
    text-align: right;   
} 
</style>

<rich:panel header="Iteração com a criança">
	<h:panelGrid border="0" columns="1" style="tabelaac" cellpadding="0" width="1000" columnClasses="coluna1">
		<a4j:form ajaxSubmit="true">
				<h:commandButton value="Mudar de Fase"></h:commandButton>
		</a4j:form>
	</h:panelGrid>
	<h:panelGrid border="0" columns="1" style="tabelaac" cellpadding="0"
		cellspacing="10" bgcolor="#99CCCC" width="1000">
			<h:panelGrid border="1" columns="3" style="tabelaac" cellpadding="0" width="100%">
			<a4j:form ajaxSubmit="true" id="form_estimulo" oncomplete="document.getElementById('fase1:form_estimulo:estimulo').value='';">
				<h:panelGrid border="0" columns="1" style="tabelaac" cellpadding="0">
					<h:outputText style="texto" value="Pergunta estímulo:"></h:outputText>
					<h:inputTextarea cols="40" rows="5" value="#{aplicadorBean.fase1.estimuloNPC}" id="estimulo"></h:inputTextarea>
					<h:commandButton value="Enviar estímulo" actionListener="#{aplicadorBean.registrarEstimulo}" id="registrar_estimulo">
						<rich:componentControl for="panel" attachTo="registrar_estimulo" operation="show" event="onclick"/>
					</h:commandButton>
				</h:panelGrid>
			</a4j:form>
			<a4j:form ajaxSubmit="true" id="form_resposta_estimulo" oncomplete="document.getElementById('fase1:form_resposta_estimulo:resposta').value='';">
				<h:panelGrid border="0" columns="1" style="tabelaac" cellpadding="0">
					<h:outputText style="texto" value="Resposta de Estímulo:"></h:outputText>
					<h:inputTextarea cols="40" rows="5"
						value="#{aplicadorBean.fase1.estimuloNPC}" id="resposta"></h:inputTextarea>
					<h:commandButton value="Registrar resposta"
						actionListener="#{aplicadorBean.registrarEstimulo}" id="registrar_resposta">
						<rich:componentControl for="panel" attachTo="registrar_resposta" operation="show" event="onclick"/>	
					</h:commandButton>
				</h:panelGrid>
			</a4j:form>
			<a4j:form ajaxSubmit="true" id="form_comentario" oncomplete="document.getElementById('fase1:form_comentario:comentario').value='';">
				<h:panelGrid border="0" columns="1" style="tabelaac" cellpadding="0">
					<h:outputText value="Comentário:"></h:outputText>
					<h:inputTextarea cols="40" rows="5" value="#{aplicadorBean.fase1.comentario}" id="comentario"></h:inputTextarea>
					<h:commandButton value="Registrar" actionListener="#{aplicadorBean.registrarComentario}" id="registrar_comentario">	
					<rich:componentControl for="panel" attachTo="registrar_comentario" operation="show" event="onclick" />	
					</h:commandButton>
				</h:panelGrid>
			</a4j:form>
			</h:panelGrid>
	
		<rich:modalPanel id="panel" width="350" height="100">
	        <f:facet name="header">
	            <h:panelGroup>
	                <h:outputText value="Modal Panel"></h:outputText>
	            </h:panelGroup>
	        </f:facet>
	        <f:facet name="controls">
	            <h:panelGroup>
	                <h:graphicImage value="/imagens/close.png" styleClass="hidelink" id="hidelink"/>
	                <rich:componentControl for="panel" attachTo="hidelink" operation="hide" event="onclick"/>
	            </h:panelGroup>
	        </f:facet>
	        <h:outputText value="#{aplicadorBean.mensagem}"></h:outputText>
	    </rich:modalPanel>
	</h:panelGrid>
</rich:panel>
<br></br>
<a4j:form ajaxSubmit="true">
<rich:panel header="Crivo" style="background-color: white">
	<h:panelGrid border="0" columns="2" style="tabelaac" cellpadding="0"
		cellspacing="0" bgcolor="#99CCCC" width="1000"
		rowClasses="odd-row,even-row">
		
		<c:forEach var="questao" items="${aplicadorBean.fase1.questoes}">
			<h:outputText style="texto">${questao.question}</h:outputText>
			
			<h:selectOneRadio required="">
				<f:selectItem itemLabel="Sim" itemValue="S" />
				<f:selectItem itemLabel="Não" itemValue="N" />
			</h:selectOneRadio>			
		</c:forEach>
	</h:panelGrid>
	<br></br>
	<h:commandButton value="Registrar"></h:commandButton>
	
</rich:panel>
</a4j:form>