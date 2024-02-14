package edu.escuelaing.arem.ASE.app;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class HttpServer {
    private static final String STATIC_FILES_PATH = "/public/";
    private static final Map<String, BiConsumer<Socket, String>> getHandlers = new HashMap<>();
    private static final Map<String, BiConsumer<Socket, String>> postHandlers = new HashMap<>();
    private static String responseType = "text/html"; // Default response type
    private static final Map<String, String> movieCache = new HashMap<>();

    public static void main(String[] args) throws IOException {
        // Inicia el servidor HTTP en el puerto 35000
        ServerSocket serverSocket = new ServerSocket(35000);
        System.out.println("Server listening on port 35000...");

        registerGetHandler("/hello", HttpHandler.helloGetHandler);
        registerPostHandler("/echo", HttpHandler.echoPostHandler);

        // Loop infinito para escuchar conexiones entrantes
        while (true) {
            Socket clientSocket = serverSocket.accept(); // Acepta una conexión entrante
            new Thread(() -> handleRequest(clientSocket)).start(); // Maneja la solicitud en un nuevo hilo
        }
    }

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
            if (requestType.equals("GET")) {
                BiConsumer<Socket, String> getHandler = getHandlers.get(path);
                if (getHandler != null) {
                    getHandler.accept(clientSocket, "");
                } else if (path.startsWith("/action")) {
                    serveStaticFile(path.substring("/action".length()), clientSocket.getOutputStream());
                }else if(requestString.contains(" /title?name=")){
                    String[] parts = requestString.split(" ");
                    String title = parts[1].substring("/title?name=".length());
                    try {
                        String movieInfo = Cache.inMemory(title);
                        out.println("HTTP/1.1 200 OK\r\nContent-Type: text/html\r\n\r\n" + movieInfo);
                    } catch (IOException e) {
                        out.println("HTTP/1.1 500 Internal Server Error\r\n\r\nError processing request");
                    }
                    
                } else {
                    out.println("HTTP/1.1 404 Not Found\r\n\r\n");
                }
                String[] parts = requestString.split(" ");
            String resource = parts[1].substring(1);
            serveStaticFile(resource, clientSocket.getOutputStream());
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

    private static InputStream getResourceAsStream(String filename) {
        return HttpServer.class.getResourceAsStream(STATIC_FILES_PATH + filename);
    }

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

    public static void registerGetHandler(String path, BiConsumer<Socket, String> handler) {
        getHandlers.put(path, handler);
    }

    public static void registerPostHandler(String path, BiConsumer<Socket, String> handler) {
        postHandlers.put(path, handler);
    }



    public static void setResponseType(String type) {
        responseType = type;
    }
}

// java -jar target/mitercera-app-1.0-SNAPSHOT.jar
