<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<f:view>
	<h:form>
		<rich:panel header="Iteração com a criança">
			<h:panelGrid border="0" columns="2" style="tabelaac" cellpadding="0"
				cellspacing="0" bgcolor="#99CCCC" width="1000">

				<h:panelGrid border="0" columns="3" style="tabelaac" cellpadding="0"
					cellspacing="0" bgcolor="#99CCCC">

					<h:outputText style="texto" value="NPC:"></h:outputText>

					<h:inputText value=""></h:inputText>
					<h:commandButton value="Enviar"></h:commandButton>

					<h:outputText value="Registrar resposta jogador:"></h:outputText>
					<h:inputText value=""></h:inputText>
					<h:commandButton value="Registrar"></h:commandButton>
				</h:panelGrid>
				<h:commandButton value="Mudar de Fase"></h:commandButton>

			</h:panelGrid>
		</rich:panel>

		<rich:panel header="Crivo">
			<h:panelGrid border="0" columns="2" style="tabelaac" cellpadding="0"
				cellspacing="0" bgcolor="#99CCCC" width="1000"
				rowClasses="odd-row,even-row">
				<h:outputText style="texto"
					value="Coloca os personagens na posição de sujeito."></h:outputText>
				<h:selectOneRadio id="subscription1">
					<f:selectItem itemLabel="Sim" itemValue="S" />
					<f:selectItem itemLabel="Não" itemValue="N" />
				</h:selectOneRadio>

				<h:outputText style="texto"
					value="Utiliza mais de um complemento para o verbo."></h:outputText>
				<h:selectOneRadio id="subscription2">
					<f:selectItem itemLabel="Sim" itemValue="S" />
					<f:selectItem itemLabel="Não" itemValue="N" />
				</h:selectOneRadio>
			</h:panelGrid>
		</rich:panel>
	</h:form>
</f:view>