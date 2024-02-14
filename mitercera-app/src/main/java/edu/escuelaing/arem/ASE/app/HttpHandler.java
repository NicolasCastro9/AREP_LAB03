package edu.escuelaing.arem.ASE.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.function.BiConsumer;

public class HttpHandler {
    public static BiConsumer<Socket, String> helloGetHandler = (clientSocket, requestData) -> {
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println("HTTP/1.1 200 OK");
            out.println("Content-Type: text/html");
            out.println();
            out.println("<h1>Hello, world!</h1>");
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    };

    public static BiConsumer<Socket, String> echoPostHandler = (clientSocket, requestData) -> {
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println("HTTP/1.1 200 OK");
            out.println("Content-Type: text/plain");
            out.println();
            out.println("Echoing your POST data:");
            out.println(requestData);
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    };
}