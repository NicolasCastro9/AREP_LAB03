# ARQUITECTURA MICROFRAMEWORK
En este taller exploraremos la arquitectura básica de un microframework web similar a Spark utilizando solo el API básico de Java. El objetivo es comprender cómo funcionan los microframeworks web y cómo podemos construir uno desde cero sin depender de frameworks como Spark o Spring.

## Clases
HttpServer: Esta clase principal es el punto de entrada del servidor HTTP. Se encarga de iniciar el servidor, manejar las solicitudes entrantes y enrutarlas a los manejadores correspondientes.

Cache: Esta clase implementa un cache que actúa como una memoria para almacenar información de películas.

HttpConnection: clase que se utiliza para realizar conexiones HTTP con la API de OMDB y obtener información sobre películas.

MovieInfo: clase que representa la información de una película obtenida de la API de OMDB.

HttpHandler: Esta clase define los manejadores para las solicitudes GET y POST. Contiene funciones lambda que procesan estas solicitudes y devuelven respuestas adecuadas.

## Pre-Requisitos

Asegúrate de tener instalado Java y Maven en tu máquina antes de ejecutar el proyecto.

## Instrucciones de uso

1. Clona el repositorio a tu máquina local:
   ```
   git clone https://github.com/NicolasCastro9/AREP_LAB03.git
   ```
2. En la consola de comandos navegar hasta el directorio donde se encuentra el pom del proyecto
   ```
   cd mitercera-app
   ```
3. Ejecuta el siguiente comando para compilar el proyecto y descargar las dependencias definidas en el archivo
   ```
   mvn clean install
   ```
4. Ejecuta el proyecto con el siguiente comando
  ```
   mvn exec:java
   ```
5. En el navegador escribir las siguientes rutas, primero para ver los archivos estaticos
    ```
   http://localhost:35000/action/index.html
   ```
    ![image](https://github.com/NicolasCastro9/AREP_LAB03/assets/98556822/65062bcc-0a2b-4883-9b09-5a6028bcc23b)
   ```
   http://localhost:35000/action/image.jpg
   ```
   ![image](https://github.com/NicolasCastro9/AREP_LAB03/assets/98556822/2f5edf3b-82b8-442b-ae71-7fa124ddd439)
   ```
   http://localhost:35000/action/tiburon.gif
   ```
   ![image](https://github.com/NicolasCastro9/AREP_LAB03/assets/98556822/ef1c953f-e360-4a2c-87bb-def55f9da857)
   ```
   http://localhost:35000/action/script.js
   ```
   ![image](https://github.com/NicolasCastro9/AREP_LAB03/assets/98556822/be5d080d-66a1-4224-8e34-958bd90bb0bc)
   ```
   http://localhost:35000/action/styles.css
   ```   
   ![image](https://github.com/NicolasCastro9/AREP_LAB03/assets/98556822/4a389e07-94d7-415c-91d7-d58eafdb39cf)


6. Ver MANEJO DE SOLICITUDES GET Y POST con postman

   ```
   http://localhost:35000/hello
   ```  
   ![image](https://github.com/NicolasCastro9/AREP_LAB03/assets/98556822/63ddf133-f95c-4e31-b10a-9a7ec3ac7bb9)
   ![image](https://github.com/NicolasCastro9/AREP_LAB03/assets/98556822/80d99865-3a05-4614-b95d-7bbf6e6b75ac)
   ```
   http://localhost:35000/echo
   ```  
   ![image](https://github.com/NicolasCastro9/AREP_LAB03/assets/98556822/dcb8ca70-1d3f-4f8e-9df5-e93788621d60)


8. Obtener información de una pelicula como un formato JSON Ejemplo:

   ```
   http://localhost:35000/title?name=TED
   ```  
![image](https://github.com/NicolasCastro9/AREP_LAB03/assets/98556822/4c14fd5b-d65a-4631-882d-cfa05a45a5ab)


## CONSTRUIDO CON

MAVEN -  framework de gestión de proyectos de software

## Dependencias
JUnit: Framework de pruebas unitarias para Java.

Gson: Biblioteca de Google para trabajar con JSON en Java.

## Autor
### Nicolás Castro Jaramillo

## Licencia
Este proyecto está bajo la licencia MIT.
