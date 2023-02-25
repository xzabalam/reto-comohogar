# Para deployar el aplicativo
1. Se debe ejecutar el comando
```bash
mvn clean install
```
2. Importar el proyecto en Intellij Idea
3. Editar las configuraciones de ejecución para que quede como la siguiente imagen
![img.png](ClientesBack%2Fimg.png)
4. Abrir el archivo RetoComoHogarApplication.java
5. Presionar con el click derecho del mouse en cualquier parte de l archivo RetoComoHogarApplication y seleccionar 
   la opción "Run RetoComoHogarApplication"
6. Se está utilizando FLyway para crear la base de datos, por lo que los archivos .sql que se encuentran en la 
   carpeta "resources/db/scripts/migrations" se ejecutarán automáticamente al inciar el app.
7. Jpa realizará una validación de las entidades con las tablas de la base de datos para que no exista inconsistencias.
8. En el archivo "ClientesBack/como-hogar.postman_collection.json" se encuentra la coleccion de postman con la que 
   se puede probar el api rest.
