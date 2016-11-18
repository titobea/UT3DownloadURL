Ejercicio 1
-----------

Se trata de hacer un programa en Java que descargue un archivo a partir de una URL.

Para ello deberás seguir los siguientes pasos (más o menos):

1. pedimos una URL por línea de comandos

2. Comprobamos que es una URL correcta.		

3. creamos el objeto URL
		
4. Obtenemos un objeto HttpURLConnection. openConnection 
		
5. configuramos la conexión al método GET. setRequestMethod
		
6. Nos conectamos. connect
		
7. Obtenemos y imprimimos el código de respuesta. getResponseCode
		
8. Obtenemos y imprimimos el tamaño del recurso. ContentLength
		
9. Guardamos el stream a un fichero con el nombre del recurso en caso de que el código sea correcto.
		
10. Damos un error en caso de que el código sea incorrecto (BufferedInputStream -> FileOutputStream)
		