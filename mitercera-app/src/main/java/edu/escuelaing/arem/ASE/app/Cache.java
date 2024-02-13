package edu.escuelaing.arem.ASE.app;
import com.google.gson.Gson;
import java.io.*;
import java.util.HashMap;
import java.util.List;


/**
 * Clase que implementa una cache  para almacenar informacion de películas.
 */
public class Cache {
    /**
     * HashMap que almacena la informacion de las películas en memoria cache.
     */
    public static HashMap<String,String> movies = new HashMap<>();
     /**
     * Metodo que obtiene la informacion de una película desde la memoria caché o la API de OMDB cuando se busca por primera vez.
     *
     * @param titulo Título de la pelicula.
     * @return Cadena HTML con la informacion de la película.
     * @throws IOException Si ocurre un error de entrada/salida al realizar la solicitud a la API.
     */
    public static String inMemory(String titulo) throws IOException {
        String n = "";

    if (movies.containsKey(titulo)) {
        // Si la información está en la memoria cache, se obitiene la informacion almacenada
        String jsonMovie = movies.get(titulo);
        n += buildHtmlFromJson(jsonMovie);
    } else {
        // Si la informacion no esta en el cache, se realiza la solicitud a la API de OMDB
        String jsonMovie = HttpConnection.getMovie(titulo);
        movies.put(titulo, jsonMovie);
        n += buildHtmlFromJson(jsonMovie);
    }

    // Devuelve la cadena construida
    return n;
    }

    /**
     * Metodo que construye una cadena HTML a partir de la informacion de la película en formato JSON.
     *
     * @param jsonMovie Información de la película en formato JSON.
     * @return Cadena HTML con la información de la película.
     */
    static String buildHtmlFromJson(String jsonMovie) {
        Gson gson = new Gson();
        MovieInfo movieInfo = gson.fromJson(jsonMovie, MovieInfo.class);
    
        // Se construye la cadena con la informacion de la película
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<strong>Title:</strong> ").append(movieInfo.getTitle()).append("<br>");
        htmlBuilder.append("<strong>Year:</strong> ").append(movieInfo.getYear()).append("<br>");
        htmlBuilder.append("<strong>Rated:</strong> ").append(movieInfo.getRated()).append("<br>");
        htmlBuilder.append("<strong>Released:</strong> ").append(movieInfo.getReleased()).append("<br>");
        htmlBuilder.append("<strong>Runtime:</strong> ").append(movieInfo.getRuntime()).append("<br>");
        htmlBuilder.append("<strong>Genre:</strong> ").append(movieInfo.getGenre()).append("<br>");
        htmlBuilder.append("<strong>Director:</strong> ").append(movieInfo.getDirector()).append("<br>");
        htmlBuilder.append("<strong>Writer:</strong> ").append(movieInfo.getWriter()).append("<br>");
        htmlBuilder.append("<strong>Actors:</strong> ").append(movieInfo.getActors()).append("<br>");
        htmlBuilder.append("<strong>Plot:</strong> ").append(movieInfo.getPlot()).append("<br>");
        htmlBuilder.append("<strong>Language:</strong> ").append(movieInfo.getLanguage()).append("<br>");
        htmlBuilder.append("<strong>Country:</strong> ").append(movieInfo.getCountry()).append("<br>");
        htmlBuilder.append("<strong>Awards:</strong> ").append(movieInfo.getAwards()).append("<br>");
        htmlBuilder.append("<strong>BoxOffice:</strong> ").append(movieInfo.getBoxOffice()).append("<br>");
    
        String posterUrl = movieInfo.getPoster();
        if (posterUrl != null && !posterUrl.isEmpty()) {
            htmlBuilder.append("<img src=\"").append(posterUrl).append("\" alt=\"Poster\"><br>");
        }
    
        List<MovieInfo.Rating> ratings = movieInfo.getRatings();
        if (ratings != null && !ratings.isEmpty()) {
            htmlBuilder.append("<strong>Ratings:</strong><br>");
            for (MovieInfo.Rating rating : ratings) {
                htmlBuilder.append("&emsp;<strong>").append(rating.getSource()).append(":</strong> ").append(rating.getValue()).append("<br>");
            }
        }
    
        return htmlBuilder.toString();
    }
    
}