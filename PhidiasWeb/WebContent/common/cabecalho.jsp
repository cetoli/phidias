
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<style>
.pic {
	margin-bottom: -4px;
	margin-right: 2px;
}
</style>

	<h:form>
	<table width="100%" height="120" border=0>
		<tr bgcolor="#FFCC99">
			<td width="100">
			<h:graphicImage url="/imagens/img_roda_ling.gif"></h:graphicImage></td>
			<td bgcolor="#FFCC99">
			<div align="center">
			<p align="center"><b><font color="#000033" size="3"
				face="Geneva, Arial, Helvetica, sans-serif">Phidias</font></b></p>
			</div>
			</td>
		</tr>
	</table>
	
		<rich:toolBar contentClass="background-color:'#FFFFCC'"
			styleClass="rich-toolbar" itemSeparator="line">
			<rich:dropDownMenu>
				<f:facet name="label">
					<h:panelGrid cellpadding="0" cellspacing="0" columns="2"
						style="vertical-align:middle" width="200">
						<h:outputText value="M�dulo Administra��o" />
					</h:panelGrid>
				</f:facet>

				<rich:menuGroup value="Cadastros" direction="rigth-down">
                   <rich:menuItem value="Categoria de Habilidade" />
                
                   <rich:menuItem value="Habilidade" />
                           
                   <rich:menuGroup value="Formul�rios" direction="rigth">
                     	<rich:menuItem value="Question�rio" />
                        <rich:menuItem value="Pergunta" submitMode="server" action="#{perguntaBean.goToPergunta}" />
                        <rich:menuItem value="Resposta" />
                        <rich:menuItem value="Associar Resposta a Pergunta" />
                   </rich:menuGroup>
                
                   <rich:menuItem value="Enxoval" submitMode="ajax" action="#{enxovalBean.goToEnxoval}"/>
                
                   <rich:menuItem value="Jogo" submitMode="server" action="#{jogoBean.goToJogo}"/>
                
                   <rich:menuItem value="Pe�a" />
                            
				</rich:menuGroup>

				<rich:menuItem submitMode="ajax" value="Aplicadores" />
				<rich:menuItem submitMode="ajax" value="Crian�a" />
				<rich:menuSeparator id="menuSeparator11" />
				<rich:menuItem submitMode="ajax" value="Sair" />
			</rich:dropDownMenu>

			<rich:dropDownMenu>
				<f:facet name="label">
					<h:panelGrid cellpadding="0" cellspacing="0" columns="2"
						style="vertical-align:middle" width="300">
						<h:outputText value="M�dulo de Acompanhamento de Crian�as" />
					</h:panelGrid>
				</f:facet>

				<rich:menuItem value="Buscar sess�es"
					action="#{sessaoBean.listarSessoes}" />

			</rich:dropDownMenu>
		</rich:toolBar>
	</h:form>
