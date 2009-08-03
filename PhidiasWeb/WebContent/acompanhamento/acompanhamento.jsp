<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<style type="text/css">
.rich-tabpanel{
background-color: #99CCCC;
}
.rich-tabpanel-content{
background-color: #99CCCC;
}
.rich-panel-body{
background-color: #99CCCC;
}
.odd-row{
background-color: #FFFFCC;
}
.even-row{
background-color: #99CCCC;
}

</style>
</head>
<body bgcolor="#99CCCC">
<f:view>

	<f:subview id="menu">
		<jsp:include page="/common/cabecalho.jsp"></jsp:include>
	</f:subview>

	<h2>Módulo de Acompanhamento</h2>
	<rich:tabPanel width="40%" headerAlignment="left" >
		<rich:tab label="Fase 1">
			<jsp:include page="fase1.jsp"></jsp:include>
		</rich:tab>
		<rich:tab label="Fase 2">
			<jsp:include page="fase2.jsp"></jsp:include>
		</rich:tab>
		<rich:tab label="Fase 3">
			<jsp:include page="fase3.jsp"></jsp:include>
		</rich:tab>
		<rich:tab label="Fase 4">
			<jsp:include page="fase4.jsp"></jsp:include>
		</rich:tab>
		<rich:tab label="Fase 5">
			<jsp:include page="fase5.jsp"></jsp:include>
		</rich:tab>

	</rich:tabPanel>
	
	
</f:view>
</body>
</html>