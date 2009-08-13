<!doctype html public "-//w3c//dtd html 4.0 transitional//en">
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<html>
<head>
	<title>Pergunta</title>
	<link href="css/rodalinguagem.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="#FFFFCC">
<f:view>
	<f:subview id="menu">
		<jsp:include page="/common/cabecalho.jsp"></jsp:include>
	</f:subview>	
	
	
	<h:form>

		<rich:tabPanel switchType="normal">
		
			<rich:tab label="Incluir"  >
			
				<h:form>
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
							<h:commandButton value="cadastrar" action="#{perguntaBean.cadastrar}" />
						</rich:column>
					</h:panelGrid>				
				</h:form>
		
			</rich:tab>
		
			<rich:tab label="Consultar">								 
				 <h:form>
					 <rich:dataTable var="question" value="#{perguntaBean.consultarTodos}" width="750" binding="#{perguntaBean.tabela}"  >
	                    <rich:column>
	                        <f:facet name="header">
	                            <h:outputText value="Código Questionário"/>
	                        </f:facet>
	                        <h:outputText value="#{question.pk.questionnaireId}"/>
	                    </rich:column>
					 	<rich:column>
	                        <f:facet name="header">
	                            <h:outputText value="Código Pergunta"/>
	                        </f:facet>
	                        <h:outputText value="#{question.pk.questionID}"/>
	                    </rich:column>
	                     <rich:column>
	                        <f:facet name="header">
	                            <h:outputText value="Nome"/>
	                        </f:facet>
	                        <h:outputText value="#{question.question}"/>
	                    </rich:column>
	                    <rich:column>
	                        <f:facet name="header">
	                            <h:outputText value="Tipo de Questão"/>
	                        </f:facet>
	                        <h:outputText value="#{question.questionType}"/>
	                    </rich:column>
	                     <rich:column>
	                        <f:facet name="header">
	                            <h:outputText value="Tipo Eval"/>
	                        </f:facet>
	                        <h:outputText value="#{question.evalType}"/>
	                    </rich:column>
	                    <rich:column>
	                        <f:facet name="header">
	                            <h:outputText value="Comentários"/>
	                        </f:facet>
	                        <h:outputText value="#{question.comments}"/>
	                    </rich:column> 
	                    <rich:column>	                        
	                      	<h:commandButton value="Editar" action="#{perguntaBean.consultaedicao}" />  
	                    </rich:column>       
	                     <rich:column>	                        
	                      	<h:commandButton value="Excluir" action="#{perguntaBean.excluir}" />  
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