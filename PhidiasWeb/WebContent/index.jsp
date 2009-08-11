
<!doctype html public "-//w3c//dtd html 4.0 transitional//en">
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<html>
<head>
<title>Habilidade</title>
</head>
<body>
<f:view>
<h:form>
		<h:dataTable border="1" style="width: 1000px" bgcolor="Silver">
			<h:column id="column1">
				<f:facet name="header">
					<center><h:outputText value="Phidias" style="font-size: 24px"></h:outputText></center>
				</f:facet>
			</h:column>
			
		</h:dataTable>
		
		<h:panelGrid styleClass="vertical-menu-cell" columnClasses="optionList" columns="1" cellspacing="0" cellpadding="0">
            <rich:dropDownMenu style="border:1px solid #{a4jSkin.panelBorderColor}" value="Administração"
                        submitMode="none" direction="bottom-right" jointPoint="tr">
                <rich:menuGroup value="Habilidade" direction="rigth">
                    <rich:menuItem value="Cadastro" />
                    <rich:menuItem value="Consulta" />
                </rich:menuGroup>
            
                <rich:menuGroup value="Questionários" direction="rigth">
                    <rich:menuItem value="Cadastro" />
                    <rich:menuItem value="Consulta" />
                </rich:menuGroup>
                            
            </rich:dropDownMenu>

            <rich:dropDownMenu style="border:1px solid #{a4jSkin.panelBorderColor}" value="Aplicador"
                        submitMode="none" direction="bottom-right" jointPoint="tr">
                <rich:menuItem value="Iniciar Sessão" />
                            
            </rich:dropDownMenu>

            <rich:dropDownMenu style="border:1px solid #{a4jSkin.panelBorderColor}" value="Especialista"
                        submitMode="none" direction="bottom-right" jointPoint="tr">
                <rich:menuItem value="Iniciar Sessão" submitMode="server" action="#{especialistaBean.goToEspecialista}">
                </rich:menuItem>

            </rich:dropDownMenu>
                     
            
        </h:panelGrid>
				
			
	</h:form>
	
</f:view>
</body>
</html>