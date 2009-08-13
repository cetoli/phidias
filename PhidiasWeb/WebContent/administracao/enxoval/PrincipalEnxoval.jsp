
<!doctype html public "-//w3c//dtd html 4.0 transitional//en">
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<html>
<head>
	<title>Enxoval</title>
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
					<h:outputText value="Nome do Enxoval.....: " />
				</rich:column>
				<rich:column>
					<h:inputText value="#{enxovalBean.pieceCollection.pieceCollectionName}" />
				</rich:column>
				<rich:column>
				  	<h:outputText value="Descrição do Enxoval: " />
				</rich:column>
				<rich:column>
					<h:inputText value="#{enxovalBean.pieceCollection.pieceCollectionDesc}" />
				</rich:column>
				<rich:column>
					<h:outputText value="Tipo do Enxoval.....: " />
						<h:selectOneMenu id="tipoenxoval" value="#{enxovalBean.pieceCollection.pieceCollectionType}"> 
							<f:selectItem id="item1" itemLabel="Pessoas" itemValue="1" /> 
							<f:selectItem id="item2" itemLabel="Animais" itemValue="2" /> 
							<f:selectItem id="item3" itemLabel="Matemática" itemValue="3" /> 
						</h:selectOneMenu>
				</rich:column>
				<rich:column>
					<h:outputText value="Nível de Representação Simbólica: " />
						<h:selectOneMenu id="nivrepsimb" value="#{enxovalBean.pieceCollection.simbType}"> 
							<f:selectItem id="item4" itemLabel="Grande" itemValue="1" /> 
							<f:selectItem id="item5" itemLabel="Médio" itemValue="2" /> 
							<f:selectItem id="item6" itemLabel="Pouco Icônico" itemValue="3" /> 
						</h:selectOneMenu>
				</rich:column>
			</h:panelGrid>
					<h:panelGrid columns="1">
						<rich:column>	
							<h:commandButton value="cadastrar" action="#{enxovalBean.cadastrar}" />
						</rich:column>
					</h:panelGrid>
									
				</h:form>
		
			</rich:tab>
		
			<rich:tab label="Consultar">								 
				 <h:form>
					 <rich:dataTable var="enxoval" value="#{enxovalBean.consultarTodos}" width="750" binding="#{enxovalBean.tabela}" >
					 	<rich:column>
	                        <f:facet name="header">
	                            <h:outputText value="Código"/>
	                        </f:facet>
	                        <h:outputText value="#{enxoval.pieceCollectionId}"/>
	                    </rich:column>
	                    <rich:column>
	                        <f:facet name="header">
	                            <h:outputText value="Nome"/>
	                        </f:facet>
	                        <h:outputText value="#{enxoval.pieceCollectionName}"/>
	                    </rich:column>	                    
	                    <rich:column>
	                        <f:facet name="header">
	                            <h:outputText value="Descrição"/>
	                        </f:facet>
	                        <h:outputText value="#{enxoval.pieceCollectionDesc}"/>
	                    </rich:column>	                    
	                    <rich:column>
	                        <f:facet name="header">
	                            <h:outputText value="Tipo de Enxoval"/>
	                        </f:facet>
	                        <h:outputText value="#{enxoval.pieceCollectionType}"/>
	                    </rich:column>	                    
	                    <rich:column>
	                        <f:facet name="header">
	                            <h:outputText value="Nível de Representação Simbólica"/>
	                        </f:facet>
	                        <h:outputText value="#{enxoval.simbType}"/>
	                    </rich:column>	                    
	                     <rich:column>	                        
	                      	<h:commandButton value="Editar" action="#{enxovalBean.consultaedicao}" />  
	                    </rich:column>
	                     <rich:column>	                        
	                      	<h:commandButton value="Excluir" action="#{enxovalBean.excluir}" />  
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