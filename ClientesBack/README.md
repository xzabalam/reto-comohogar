# Prueba de BackEnd

Creamos esta prueba para conocer sobre sus conocimientos y habilidades en lenguaje Java, programación orientada a objetos, micro servicios, spring boot, arquitectura hexagonal y buenas prácticas de programación. 

La prueba consiste en construir una aplicación Java capaz de recuperar información de un archivo XML y un archivo JSON, conservar un registro en una base de datos en memoria o de archivo, y listar los registros en una interfaz simple. 

## Propuesta 

¡El "nuevo" sistema de registro de clientes de la empresa XYZ_CH desde la plataforma web necesita una nueva propuesta!  

El departamento de marketing, ha decidido que por motivos del lanzamiento de la nueva plataforma web, premiará a los primeros clientes en registrarse y participar de beneficios durante todo el año.  En el momento de inscribirse, el cliente deberá escoger a que grupo quiere pertenecer para participar de los beneficios. Por esta razón, se ha creado dos listas separadas diferenciadas por sus formatos: “sk_formato” y “th_formato” (ver carpeta referencias)

El reto es desarrollar un sistema Java capaz de: 

Permitir el registro de clientes escogiendo al grupo que quiere pertenecer para participar en los beneficios de los contenidos en los enlaces de referencia sk_formato.json y th_formato.xml 

Enviar un registro que contenga el nombre, el correo electrónico y el número de teléfono del cliente (los campos obligatorios son el nombre y correo electrónico) 

Conservar la información registrada en una base de datos en memoria, como HSQLDB o un archivo 

Obtener, en cualquier momento, la lista de todos los clientes registrados con su respectivo grupo y el beneficio que se le otorgó. 

Validar el uso del mismo nombre como clave para diferentes usuarios (a menos que el nombre sea para listas de formatos diferentes) 

Debe leer la información de los beneficios de los archivos compartidos  

 

## Arquitectura de referencia 

![](https://github.com/SistemasComoHogar/ClientesBack/blob/main/Referencias/Arquitectura.png)

## Enlaces a archivos de referencia 

https://github.com/SistemasComoHogar/ClientesBack/blob/main/Referencias/sk_formato.json  

https://github.com/SistemasComoHogar/ClientesBack/blob/main/Referencias/th_formato.xml 

## Casos de uso 

- Registro exitoso: 

  * El usuario “José” registra su nombre, correo electrónico y número de teléfono, y elige una opción de la lista sk_formato.json cargada previamente. 

  * El sistema recibe el registro y comprueba los nombres de código disponibles de la lista sk_formato.json cargada previmente. 

  * El sistema encuentra un beneficio libre y lo elige 

  * El sistema conserva el nombre, el e-mail, el teléfono, beneficio y el archivo de referencia en una base de datos. 

  * El sistema informa que el usuario se ha registrado correctamente y muestra una advertencia de éxito. 

- Lista escogida no tiene beneficios disponibles 

  * El usuario “Gabriel” registra su nombre, correo electrónico y número de teléfono, y elige una opción de la lista lista th_formato.xml cargada previamente.  

  * El sistema recibe el registro y comprueba los beneficios disponibles de la lista th_formato.xml cargada previmente.

  * El sistema no encuentra un beneficio libre 

  * El sistema le informa que esa lista ya no tiene beneficios disponibles 

- Informe de usuarios registrados: 

  * El usuario “Monica” lista los clientes en “Listar Clientes” 

  * El sistema consulta la base de datos en memoria o en archivo 

  * El sistema presenta a todos los usuarios registrados. Cada línea tiene la información: nombre, correo electrónico, teléfono, beneficio y archivo de referencia 

## Instrucciones 

 

El código generado no está ni bien ni mal. Solo queremos saber más sobre sus conocimientos en el lenguaje Java, Spring boot, arquitectura hexagonal como el uso de librerías públicas, y también su cuidado por el código fuente, teniendo en cuenta la claridad de las ideas, la organización del código, la documentación y las pruebas. 

**Clone este proyecto, cree un nuevo proyecto en su propio GitHub y siga los siguientes pasos:**

1. Clonar el  proyecto https://github.com/SistemasComoHogar/ClientesBack.git 

2. Crea un nuevo proyecto dentro de tu GitHub (https://github.com) 

3. Desarrollar un sistema que cumpla con los casos de uso presentados 

4. Para montar el sistema, tenga en cuenta la arquitectura de referencia dentro de la carpeta de referencia 

5. Realizar un llamado a una API que contenga un formulario para recibir el nombre, el correo electrónico y el teléfono (No es necesario crear la pantalla)

6. Realizar un llamado a una API que enumere a los clientes registrados por nombre, correo electrónico, teléfono, beneficio y lista de referencias (No es necesario crear la pantalla) 

7. Cree una o más clases que realice la carga mediante una solicitud HTTP para el archivo de referencia "th_formato" en: https://github.com/SistemasComoHogar/ClientesBack/blob/main/Referencias/th_formato.xml

8. Cree una o más clases que realice la carga mediante una solicitud HTTP para el archivo de referencia "sk_formato" en: https://github.com/SistemasComoHogar/ClientesBack/blob/main/Referencias/sk_formato.json 

9. Crear una o más clases que contengan las reglas para conservar y recuperar los registros de los clientes 

10. Documente cómo se debe iniciar el proyecto para que podamos ejecutar su aplicación 

11. Envía tu propuesta para el proyecto que creaste en GitHub. Ejemplo: https://github.com/nombre_apellido 

12. Envíanos el enlace de GitHub de tu proyecto para que podamos descargarlo. Ejemplo: https://github.com/nombre_apellido/registro-backEnd-java.git 

13. Espere a que RRHH se ponga en contacto con usted. 

## Reglas 

1. Puede utilizar Java en cualquier versión y el IDE de su preferencia :) 

2. Puede utilizar frameworks de lenguaje Java, de preferencia Spring y hibernate :) 

3. Para conservar la información, puede utilizar una base de datos en memoria que administre o utilizar una base de datos, como HSQLDB. 

4. También puede elegir escribir en un archivo. 

5. (Opcional) Detalle de la creación de pruebas unitarias, la ordenación de la lista registrada o el filtrado de la lista. ¡Pero si lo haces te lo agradeceremos! =) 

## Lo que apreciamos 

* Organización 

* Simplicidad 

* Objetividad 

* Reutilización del código 

* Pruebas unitarias 

* Estandarización del código 

* Patrones de diseño 

## A quién buscamos 

Queremos una persona a la que le guste lo que hace, trabaje en equipo y quiera innovar, buscando siempre soluciones actualizadas e innovadoras. 

Si te has identificado, ¡forma parte de nuestro equipo! 

 
