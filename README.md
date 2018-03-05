# AMusic
AMusic es una bilioteca musica, donde podras seleccionar un directorio para leer las canciones que ahí se encuentra

Este proyecto utliza conexion con base de datos, por lo que en la carpeta "Script" se puede encontrar lo necesario para crearlo
Se asume que la contraseña de postgresql es "root", y el propietario de la base es "postgres". Si lo anterior no es tu caso, puede cambiar
dentro del código, en el paquete "database", archivo DBConnection.java, en las lineas 19 y 20 respectivamente. 

Si al trabajar presentas problemas, como que Netbeans te informa que necesitas resolver problemas con unas librerias o archivos jar, los puedes encontrar en la carpera srs/Jar utlizado

Si luego de eso sigue dando problemas con librerias, te recomendamos usar el siguiente comando para liberar la caché. Luego cierra y vuelve a abrir netbeans

del /s /q %USERPROFILE%\AppData\Local\NetBeans\Cachede
