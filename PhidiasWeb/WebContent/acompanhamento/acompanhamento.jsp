<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript">
function goTab() {
	document.getElementById('tab_fase2_shifted').onclick();
}

</script>
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

.table_column {
	width: 30%;
}
</style>
</head>
<body bgcolor="">
<f:view>

	<f:subview id="menu">
		<%@ include file="/common/cabecalho.jsp"%>
	</f:subview>

	<h2>Módulo de Acompanhamento</h2>

	<table width="100%" border=0>
		<tr bgcolor="#99CCCC">
			<td bgcolor="#99CCCC" valign="top"><rich:panel
				header="Aplicação">
				<h:panelGrid border="0" columns="2" style="tabelaac" cellpadding="0"
					cellspacing="0" width="350" rules="cols"
					columnClasses="table_column">
					<h:outputText style="font: bold" value="Jogo:"></h:outputText>
					<h:outputText style="texto" value="#{sessaoBean.sessaoAtiva.jogo.name}"></h:outputText>

					<h:outputText style="font: bold" value="Aplicador:"></h:outputText>
					<h:outputText style="texto" value="#{sessaoBean.sessaoAtiva.attendant.name}"></h:outputText>

					<h:outputText style="font: bold" value="Início Sessão:"></h:outputText>
					<h:outputText style="texto" value="#{sessaoBean.sessaoAtiva.sessionStartDate}">
						<f:convertDateTime type="date" pattern="dd/MM/yyyy hh:mm"/>
					</h:outputText>
				</h:panelGrid>
			</rich:panel></td>
			<td bgcolor="#99CCCC" valign="top"><rich:panel header="Jogador">
				<h:panelGrid border="0" columns="2" rules="cols" style="tabelaac"
					cellpadding="0" width="350" columnClasses="table_column">
					<h:outputText style="font: bold" value="Nome:"></h:outputText>
					<h:outputText style="texto" value="#{sessaoBean.sessaoAtiva.patient.name}"></h:outputText>

					<h:outputText style="font: bold" value="Data de Nascimento:"></h:outputText>
					<h:outputText style="texto" value="#{sessaoBean.sessaoAtiva.patient.birthdayDate}">
						<f:convertDateTime type="date" pattern="dd/MM/yyyy"/>
					</h:outputText>

					<h:outputText style="font: bold" value="Sexo:"></h:outputText>
					<h:outputText style="texto" value="#{sessaoBean.sessaoAtiva.patient.gender}"></h:outputText>
				</h:panelGrid>
			</rich:panel></td>
			<td bgcolor="#99CCCC" valign="top"><rich:panel header="Eventos">
			<h:panelGrid columns="1" id="grid" width="300">
						
				<rich:scrollableDataTable rowKeyVar="rkv" frozenColCount="0"  height="200px" 
	                width="150px" rows="5"	value="#{sessaoBean.eventosJogo}" var="evento" sortMode="single">
	
	                <rich:column id="evento">
	                	<f:facet name="header">
	                		<h:outputText value="Evento"></h:outputText>
	                	</f:facet>
	                    <h:outputText value="#{evento.actionType.description}" />
	                </rich:column>
	                <rich:column id="evento">
	                	<f:facet name="header">
	                		<h:outputText value="Evento"></h:outputText>
	                	</f:facet>
	                    <h:outputText value="#{evento.actionType.description}" />
	                </rich:column>
	                <rich:column id="evento">
	                	<f:facet name="header">
	                		<h:outputText value="Peça"></h:outputText>
	                	</f:facet>
	                    <h:outputText value="#{evento.actionType.description}" />
	                </rich:column>
	            </rich:scrollableDataTable>
			</h:panelGrid>				
			</rich:panel></td>
		</tr>
	</table>

	<a4j:region>
        <h:form>
            <a4j:poll id="poll" interval="2000" enabled="true"
                reRender="poll,grid" />
        </h:form>
    </a4j:region>
	
	<br></br>

	<rich:tabPanel width="40%" headerAlignment="left" switchType="client">
		<rich:tab label="Fase 1" id="tab_fase1">
			<f:subview id="fase1">
				<%@ include file="fase1.jsp"%>
			</f:subview>
		</rich:tab>
		<rich:tab label="Fase 2" id="tab_fase2">
			<f:subview id="fase2">
				<%@ include file="fase1.jsp"%>
			</f:subview>
		</rich:tab>
		<rich:tab label="Fase 3" id="tab_fase3" disabled="true">
			<f:subview id="fase3">
				<%@ include file="fase1.jsp"%>
			</f:subview>
		</rich:tab>
		<rich:tab label="Fase 4" id="tab_fase4" disabled="true">
			<f:subview id="fase4">
				<%@ include file="fase1.jsp"%>
			</f:subview>
		</rich:tab>
		<rich:tab label="Fase 5" id="tab_fase5" disabled="true">
			<f:subview id="fase5">
				<%@ include file="fase1.jsp"%>
			</f:subview>
		</rich:tab>

	</rich:tabPanel>
</f:view>
</body>
</html>