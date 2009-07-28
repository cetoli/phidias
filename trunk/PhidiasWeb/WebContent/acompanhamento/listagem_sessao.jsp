<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<f:view>

	<h:form>
		<rich:dataTable value="#{sessaoBean.sessoes}" var="sessao"
			width="750">

			<f:facet name="header">
				<h:outputText value="Sessões Disponíveis"></h:outputText>
			</f:facet>
			<rich:column>
				<f:facet name="header"><h:outputText value="Sessão"/></f:facet>
				<h:outputText value="#{sessao.id}" />
			</rich:column>
			<rich:column>
				<f:facet name="header"><h:outputText value="Jogo"/></f:facet>
				<h:outputText value="#{sessao.jogo.name}" />
			</rich:column>
         	<rich:column>
				<f:facet name="header"><h:outputText value="Fase"/></f:facet>
				<h:outputText value="-" />
			</rich:column>   
			<rich:column>
				<f:facet name="header"><h:outputText value="Aluno"/></f:facet>
				<h:outputText value="#{sessao.patient.name}" />
			</rich:column>   
         	<rich:column>
				<f:facet name="header"><h:outputText value="Aplicador"/></f:facet>
				<h:outputText value="#{sessao.attendant.name}" />
			</rich:column>   
         	<rich:column>
				<f:facet name="header"><h:outputText value="Especialista"/></f:facet>
				<h:outputText value="-" />
			</rich:column>   
         	<rich:column>
				<f:facet name="header"><h:outputText value="Operações"/></f:facet>
				<h:outputText value="-" />
			</rich:column>   
		</rich:dataTable>
	</h:form>

</f:view>
