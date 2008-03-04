@echo off
echo Assinando o Applet CriaConto...
echo O script utiliza o .jar original, de nome CriaConto.jar, e gera um arquivo assinado de nome aCriaConto.jar
echo Quando solicitada a senha, informar "123456"
echo.
echo. 


keytool -genkey -dname "cn=Neurociencias, ou=NCE, o=UFRJ, c=BR" -alias key -keystore mykeystore -storepass 123456 -validity 180

jarsigner -keystore mykeystore -storepass 123456 -signedjar signedCriaConto.jar criaConto.jar key

keytool -export -keystore mykeystore -alias key -file keycert.x509 


echo.
echo Assinatura concluída. 