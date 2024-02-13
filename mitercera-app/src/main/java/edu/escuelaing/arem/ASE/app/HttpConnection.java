package edu.escuelaing.arem.ASE.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Clase que realiza la conexión HTTP con la API de OMDB para obtener informacion de películas.
 */
public class HttpConnection {
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String API_URL = "http://www.omdbapi.com/";
    private static final String API_KEY = "a8496578";

    /**
     * Metodo que realiza una solicitud GET a la API de OMDB para obtener informacion de una pelicula solicitada.
     *
     * @param title Titulo de la película.
     * @return Respuesta de la API en formato JSON.
     * @throws IOException Si ocurre un error de entrada/salida durante la conexion.
     */
    public static String getMovie(String title) throws IOException {
        // Construye la URL de la API de OMDB con el titulo de la pelicula y la clave API
        String apiUrl = API_URL + "?t=" + title + "&apikey=" + API_KEY;
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try {
            // Se configura la solicitud HTTP
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = connection.getResponseCode();
            System.out.println("GET Response Code: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Lee la respuesta de la API si la solicitud fue exitosa
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                System.out.println(response.toString());
                return response.toString();
            } else {
                throw new IOException("GET request failed with response code: " + responseCode);
            }
        } finally {
            // La conexion se cierra para liberar recursos
            connection.disconnect();
        }
    }
}
