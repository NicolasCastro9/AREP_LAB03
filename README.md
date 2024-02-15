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
2. Abrir en un IDE el proyecto descargado y ejecutar el archivo java HttpServer.java
