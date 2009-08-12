
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

		<rich:tabPanel switchType="normal">
		
			<rich:tab label="Incluir"  >
			
				<h:form>
					<h:panelGrid columns="2">
						<rich:column>
							<h:outputText value="Nome do Jogo" />
						</rich:column>
						<rich:column>
							<h:inputText value="#{jogoBean.game.name}" />
						</rich:column>
					</h:panelGrid>
					<h:panelGrid columns="1">
						<rich:column>	
							<h:commandButton value="cadastrar" action="#{jogoBean.cadastrar}" />
						</rich:column>
					</h:panelGrid>
									
				</h:form>
		
			</rich:tab>
		
			<rich:tab label="Consultar">								 
				 <h:form>
					 <rich:dataTable var="game" value="#{jogoBean.consultarTodos}" width="750" binding="#{jogoBean.tabela}" >
					 	<rich:column>
	                        <f:facet name="header">
	                            <h:outputText value="Código"/>
	                        </f:facet>
	                        <h:outputText value="#{game.id}"/>
	                    </rich:column>
	                    <rich:column>
	                        <f:facet name="header">
	                            <h:outputText value="Nome"/>
	                        </f:facet>
	                        <h:outputText value="#{game.name}"/>
	                    </rich:column>	                    
	                     <rich:column>	                        
	                      	<h:commandButton value="Editar" action="#{jogoBean.consultaedicao}" />  
	                    </rich:column>
	                     <rich:column>	                        
	                      	<h:commandButton value="Excluir" action="#{jogoBean.excluir}" />  
	                    </rich:column>
	                 </rich:dataTable>                	
				 </h:form>
 	
			</rich:tab>           	 
		</rich:tabPanel>
		<br/>
		<h:commandButton value="Inicial" action="inicialADM" />
	</h:form>
</f:view>
</body>
</html>