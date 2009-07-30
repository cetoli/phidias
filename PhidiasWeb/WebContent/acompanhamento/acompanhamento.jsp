<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<f:view>

<f:subview id="menu">
		<jsp:include page="/common/cabecalho.jsp"></jsp:include>
</f:subview>

<table width="98%" height="320" border=0>
  <tr bgcolor="#FFFFCC" valign="top"> 
    <td height="1382" colspan="2" align="center" bgcolor="#FFFFCC"> 
      <div align="center"> 
        <table width="100%" height="0" border=0 cellpadding="0" cellspacing="0">
          <tr> 
            <td width="534" height="20" valign="baseline" bgcolor="#FFFFCC"> 
              <div align="center"><strong><font size="2" face="Geneva, Arial, Helvetica, sans-serif"> 
                </font></strong> </div>
              <div align="center"></div>
              <div align="left"> <img name="bt_fase1" src="/imagens/bt_fase1.jpg" width="109" height="46" border="0" alt=""> 
                <b> </b></div>
            </td>
            <td width="628" bgcolor="#FFFFCC" height="20" valign="baseline"> 
              <div align="right"><strong><font size="2" face="Geneva, Arial, Helvetica, sans-serif"> 
                <input type="button" name="Submit" value="Mudar fase" onClick="Tela da fase 2.html">
                </font></strong></div>
            </td>
            <td width="49" bgcolor="#FFFFCC" height="20" valign="top">&nbsp;</td>
          </tr>
          <tr bgcolor="#FFCC99"> 
            <td width="534" height="134" valign="top"> 
              <table width="99%" border="0">
                <tr> 
                  <td> 
                    <p align="center"> 
                      <input name="pergunta" type="text" id="comentario" value="Por favor mexa e me conte o que voc&ecirc; est&aacute; vendo?" size="50">
                      <input type="submit" name="Submit2" value="Enviar">
                    </p>
                    <p align="center"><font size="2" face="Geneva, Arial, Helvetica, sans-serif"> 
                      <input name="resposta" type="text" id="comentario" size="50" value="Resposta do jogador">
                      <input type="submit" name="Submit22" value="Salvar">
                      </font> </p>
                  </td>
                </tr>
              </table>
              <p>&nbsp;</p>
              <p>&nbsp; </p>
              <p> <font size="2" face="Geneva, Arial, Helvetica, sans-serif"> 
                </font> </p>
            </td>
            <td height="134" valign="top" colspan="2"> 
              <table width="93%" border="0">
                <tr> 
                  <td> <div align="left">
                      <textarea name="comentario" cols="60" rows="5" id="comentario">Comentários do aplicador:</textarea>
                    </div></td>
                </tr>
              </table>
              <p>&nbsp; </p>
              <div id="Layer1" style="position:absolute; width:1000px; height:239px; z-index:1; left: 50px; top: 340px; background-color: #FFFFCC; layer-background-color: #FFFFCC; border: 1px none #000000"> 
                <script languagem=javascript>
				  window.open()
           		</script>
                <form name="form2" method="post" action="">
                  <table width="95%" border="0" align="center">
                    <tr valign="middle" bgcolor="#FFCC99"> 
                      <td width="44%"> <div align="center"><font size="2" color="#000066"><b><font face="Geneva, Arial, Helvetica, san-serif">Crivo</font></b></font></div></td>
                      <td width="3%"> <div align="center"><font size="2" color="#000066"><b><font face="Geneva, Arial, Helvetica, san-serif">Sim</font></b></font></div></td>
                      <td width="3%"> <div align="center"><font size="2" color="#000066"><b><font face="Geneva, Arial, Helvetica, san-serif">N&atilde;o</font></b></font></div></td>
                    </tr>
                    <tr> 
                      <td width="44%"><font face="Geneva, Arial, Helvetica, san-serif" size="2">1) 
                        Reflex&atilde;o sobre o conte&uacute;do de um enunciado 
                        em fun&ccedil;&atilde;o da coer&ecirc;ncia do conte&uacute;do 
                        pretendido?</font></td>
                      <td width="3%"> <font face="Geneva, Arial, Helvetica, san-serif" size="2"> 
                        <input type="radio" name="radiobutton" value="radiobutton">
                        </font></td>
                      <td width="3%"> <font face="Geneva, Arial, Helvetica, san-serif" size="2"> 
                        <input type="radio" name="radiobutton" value="radiobutton" checked>
                        </font></td>
                    </tr>
                    <tr> 
                      <td width="44%" bgcolor="#FFCC99"><font face="Geneva, Arial, Helvetica, san-serif" size="2">2) 
                        A crian&ccedil;a utiliza mais de um complemento para o 
                        verbo?</font></td>
                      <td width="3%" bgcolor="#FFCC99"> <font face="Geneva, Arial, Helvetica, san-serif" size="2"> 
                        <input type="radio" name="radiobutton" value="radiobutton">
                        </font></td>
                      <td width="3%" bgcolor="#FFCC99"> <font face="Geneva, Arial, Helvetica, san-serif" size="2"> 
                        <input type="radio" name="radiobutton" value="radiobutton" checked>
                        </font></td>
                    </tr>
                    <tr> 
                      <td width="44%" bgcolor="#FFFFCC"><font face="Geneva, Arial, Helvetica, san-serif" size="2">3)Constr&oacute;i 
                        narrativa utilizando outros personagens e ambientes?</font></td>
                      <td width="3%" bgcolor="#FFFFCC"> <font face="Geneva, Arial, Helvetica, san-serif" size="2"> 
                        <input type="radio" name="radiobutton" value="radiobutton">
                        </font></td>
                      <td width="3%" bgcolor="#FFFFCC"> <font face="Geneva, Arial, Helvetica, san-serif" size="2"> 
                        <input type="radio" name="radiobutton" value="radiobutton" checked>
                        </font></td>
                    </tr>
                    <tr> 
                      <td width="44%" height="7" bgcolor="#FFCC99"><font face="Geneva, Arial, Helvetica, san-serif" size="2">4)Utiliza 
                        Linguagem descritiva?<br>
                        </font></td>
                      <td width="3%" bgcolor="#FFCC99"> <font face="Geneva, Arial, Helvetica, san-serif" size="2"> 
                        <input type="radio" name="radiobutton" value="radiobutton">
                        </font></td>
                      <td width="3%" bgcolor="#FFCC99"> <font face="Geneva, Arial, Helvetica, san-serif" size="2"> 
                        <input type="radio" name="radiobutton" value="radiobutton" checked>
                        </font></td>
                    </tr>
                    <tr> 
                      <td width="44%" bgcolor="#FFFFCC"><font face="Geneva, Arial, Helvetica, san-serif" size="2">5) 
                        Mant&eacute;m os personagens?</font></td>
                      <td width="3%" bgcolor="#FFFFCC"> <font face="Geneva, Arial, Helvetica, san-serif" size="2"> 
                        <input type="radio" name="radiobutton" value="radiobutton">
                        </font></td>
                      <td width="3%" bgcolor="#FFFFCC"> <font face="Geneva, Arial, Helvetica, san-serif" size="2"> 
                        <input type="radio" name="radiobutton" value="radiobutton" checked>
                        </font></td>
                    </tr>
                    <tr> 
                      <td width="44%" bgcolor="#FFCC99"><font face="Geneva, Arial, Helvetica, san-serif" size="2">6) 
                        Relaciona hist&oacute;ria como um todo mantendo a diretividade 
                        do pensamento?</font></td>
                      <td width="3%" bgcolor="#FFCC99"> <font face="Geneva, Arial, Helvetica, san-serif" size="2"> 
                        <input type="radio" name="radiobutton" value="radiobutton">
                        </font></td>
                      <td width="3%" bgcolor="#FFCC99"> <font face="Geneva, Arial, Helvetica, san-serif" size="2"> 
                        <input type="radio" name="radiobutton" value="radiobutton" checked>
                        </font></td>
                    </tr>
                  </table>
                </form>
              </div>
            </td>
          </tr>
          <tr bgcolor="#FFCC99" valign="top"> 
            <td height="34" colspan="3"> 
              <p align="center">&nbsp;</p>
              <p align="center">&nbsp;</p>
              <p align="center">&nbsp;</p>
              <p align="center">&nbsp;</p>
              <p align="center">&nbsp;</p>
              <p align="center">&nbsp;</p>
              <p align="center">&nbsp;</p>
              <p align="center">&nbsp;</p>
              <p align="center">&nbsp;</p>
              <p align="center">&nbsp;</p>
              <p align="center">&nbsp;</p>
              <p align="center">&nbsp;</p>
              <p align="center">&nbsp;</p>
              <p align="center">&nbsp;</p>
              <p align="center">&nbsp;</p>
              <p align="center">&nbsp;</p>
              <p align="center">&nbsp;</p>
              <p align="center">&nbsp;</p>
            </td>
          </tr>
        </table>
      </div></td>
  </tr>
</table>

</f:view>
</body>
</html>