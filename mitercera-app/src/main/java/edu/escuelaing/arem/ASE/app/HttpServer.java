package edu.escuelaing.arem.ASE.app;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;


/**
 * Clase principal que contiene el metodo main que inicia el servidor
 */
public class HttpServer {
    // Ruta al directorio de archivos estáticos
    private static final String STATIC_FILES_PATH = "/public/";
     // Mapas para almacenar los manejadores GET y POST registrados
    private static final Map<String, BiConsumer<Socket, String>> getHandlers = new HashMap<>();
    private static final Map<String, BiConsumer<Socket, String>> postHandlers = new HashMap<>();
    private static String responseType = "text/html"; // Default response type
    private static final Map<String, String> movieCache = new HashMap<>();


        /**
     * Metodo principal para iniciar el servidor HTTP en el puerto especificado.
     * 
     * @param args Argumentos de línea de comandos (no utilizados)
     * @throws IOException Si ocurre un error de E/S al intentar iniciar el servidor en el puerto especificado
     */
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = null;
        try{
            serverSocket = new ServerSocket(35000);
            System.out.println("Server listening on port 35000...");

            registerGetHandler("/hello", HttpHandler.helloGetHandler);
            registerPostHandler("/echo", HttpHandler.echoPostHandler);

        
        while (true) {
            Socket clientSocket = serverSocket.accept(); 
            new Thread(() -> handleRequest(clientSocket)).start(); 
        }
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        serverSocket.close();

        
    }
       /**
     *  Metodo que maneja la solicitud recibida en el socket proporcionado.
     * 
     * @param clientSocket El socket del cliente que realiza la solicitud
     */
    private static void handleRequest(Socket clientSocket) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            String inputLine;
            StringBuilder request = new StringBuilder();

            // Lee la solicitud del cliente
            while ((inputLine = in.readLine()) != null) {
                if (inputLine.isEmpty()) {
                    break;
                }
                request.append(inputLine).append("\r\n");
            }
            String requestString = request.toString();

            // Parsea la solicitud para obtener el método y la ruta
            String[] requestLines = requestString.split("\r\n");
            String[] requestParts = requestLines[0].split(" ");
            String requestType = requestParts[0];
            String path = requestParts[1];

            // Maneja la solicitud GET
            if (requestType.contains("GET")) {
                BiConsumer<Socket, String> getHandler = getHandlers.get(path);
                if (getHandler != null) {
                    getHandler.accept(clientSocket, "");
                } else if (path.startsWith("/action")) {
                    serveStaticFile(path.substring("/action".length()), clientSocket.getOutputStream());
                }else if(requestString.contains("GET /title?name=")){
                    String[] parts = requestString.split(" ");
                    String title = parts[1].substring("/title?name=".length());
                    try {
                        String movieInfo = Cache.inMemory(title);
                        out.println("HTTP/1.1 200 OK\r\nContent-Type: text/html\r\n\r\n" + movieInfo);
                    } catch (IOException e) {
                        out.println("HTTP/1.1 500 Internal Server Error\r\n\r\nError processing request");
                    }
                    
                }
                String[] parts = requestString.split(" ");
                String resource = parts[1].substring(1);
            try {
                serveStaticFile(resource, clientSocket.getOutputStream());
            } catch (IOException e) {
                out.println("HTTP/1.1 404 Not Found\r\n");
            }
            }
            // Maneja la solicitud POST
            else if (requestType.equals("POST")) {
                BiConsumer<Socket, String> postHandler = postHandlers.get(path);
                if (postHandler != null) {
                    postHandler.accept(clientSocket, "");
                } else {
                    out.println("HTTP/1.1 404 Not Found\r\n\r\n");
                }
            }
            

            out.close();
            in.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     *  Metodo que devuelve un flujo de entrada para el recurso especificado.
     * 
     * @param filename Nombre del archivo del recurso
     * @return InputStream Flujo de entrada para el recurso especificado
     */
    private static InputStream getResourceAsStream(String filename) {
        return HttpServer.class.getResourceAsStream(STATIC_FILES_PATH + filename);
    }
    /**
     *  Metodo que sirve un archivo estatico al cliente a través del socket proporcionado.
     * 
     * @param filename Nombre del archivo estatico a servir
     * @param outStream Flujo de salida del socket del cliente
     * @throws IOException Si ocurre un error de E/S al servir el archivo estatico
     */
    private static void serveStaticFile(String filename, OutputStream outStream) throws IOException {
        InputStream inputStream = getResourceAsStream(filename);
        if (inputStream != null) {
            String contentType = getContentType(filename);
            PrintWriter out = new PrintWriter(outStream, true);
            out.println("HTTP/1.1 200 OK");
            out.println("Content-Type: " + contentType);
            out.println();
            
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
            inputStream.close();
        } else {
            PrintWriter out = new PrintWriter(outStream, true);
            out.println("\r\n");
        }
    }
    /**
     * Metodo que obtiene el tipo de contenido MIME para el archivo especificado.
     * 
     * @param filename Nombre del archivo
     * @return String Tipo de contenido MIME
     */
    private static String getContentType(String filename) {
        if (filename.endsWith(".html")) {
            return "text/html";
        } else if (filename.endsWith(".css")) {
            return "text/css";
        } else if (filename.endsWith(".js")) {
            return "text/javascript";
        } else if (filename.endsWith(".jpg") || filename.endsWith(".jpeg")) {
            return "image/jpeg";
        } else if (filename.endsWith(".png")) {
            return "image/png";
        } else if (filename.endsWith(".gif")) {
            return "image/gif";
        } else {
            return "application/octet-stream";
        }
    }
    /**
     * Registra un manejador GET para la ruta especificada.
     * 
     * @param path Ruta para la cual se registra el manejador GET
     * @param handler Manejador GET a registrar
     */
    public static void registerGetHandler(String path, BiConsumer<Socket, String> handler) {
        getHandlers.put(path, handler);
    }
    /**
     * Registra un manejador POST para la ruta especificada.
     * 
     * @param path Ruta para la cual se registra el manejador POST
     * @param handler Manejador POST a registrar
     */
    public static void registerPostHandler(String path, BiConsumer<Socket, String> handler) {
        postHandlers.put(path, handler);
    }


        /**
     * Establece el tipo de respuesta predeterminado.
     * 
     * @param type Tipo de respuesta predeterminado
     */
    public static void setResponseType(String type) {
        responseType = type;
    }
}


