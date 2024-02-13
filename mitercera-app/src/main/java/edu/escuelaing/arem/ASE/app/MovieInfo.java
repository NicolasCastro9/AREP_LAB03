package edu.escuelaing.arem.ASE.app;

import java.util.List;

/**
 * Clase que representa la información de una pelicula obtenida de la API de OMDB.
 */
public class MovieInfo {
    private String Title;
    private String Year;
    private String Rated;
    private String Released;
    private String Runtime;
    private String Genre;
    private String Director;
    private String Writer;
    private String Actors;
    private String Plot;
    private String Language;
    private String Country;
    private String Poster;
    private String Awards;
    private String BoxOffice;
    private List<Rating> Ratings;


    /**
     * Obtiene el titulo de la pelicula.
     * @return Título de la pelicula.
     */
    public String getTitle() {
        return Title;
    }
    /**
     * Obtiene el año de la pelicula.
     * @return año de la pelicula.
     */
    public String getYear() {
        return Year;
    }
    /**
     * Obtiene calificacion de la pelicula.
     * @return calificacion de la pelicula.
     */
    public String getRated() {
        return Rated;
    }
    /**
     * Obtiene el lanzamiento de la pelicula.
     * @return lanzamiento de la pelicula.
     */
    public String getReleased() {
        return Released;
    }
    /**
     * Obtiene duración de la pelicula.
     * @return duración de la pelicula.
     */
    public String getRuntime() {
        return Runtime;
    }
    /**
     * Obtiene el genero de la pelicula.
     * @return genero de la pelicula.
     */
    public String getGenre() {
        return Genre;
    }
    /**
     * Obtiene el director de la pelicula.
     * @return director de la pelicula.
     */
    public String getDirector() {
        return Director;
    }
    /**
     * Obtiene el escritor de la pelicula.
     * @return escritor de la pelicula.
     */
    public String getWriter() {
        return Writer;
    }
    /**
     * Obtiene los actores de la pelicula.
     * @return actores de la pelicula.
     */
    public String getActors() {
        return Actors;
    }
    /**
     * Obtiene el argumento de la pelicula.
     * @return argumento de la pelicula.
     */
    public String getPlot() {
        return Plot;
    }
    /**
     * Obtiene el idioma de la pelicula.
     * @return idioma de la pelicula.
     */

    public String getLanguage() {
        return Language;
    }
    /**
     * Obtiene el pais de origen de la pelicula.
     * @return pais de origen de la pelicula.
     */
    public String getCountry() {
        return Country;
    }
    /**
     * Obtiene el poster de la pelicula.
     * @return poster de la pelicula.
     */
    public String getPoster() {
        return Poster;
    }
    /**
     * Obtiene los premios de la pelicula.
     * @return premios de la pelicula.
     */
    public String getAwards() {
        return Awards;
    }
    /**
     * Obtiene el dinero recaudado de la pelicula.
     * @return dinero recaudado de la pelicula.
     */
    public String getBoxOffice() {
        return BoxOffice;
    }
    /**
     * Obtiene la lista de clasificaciones de la pelicula.
     * @return Lista de clasificaciones.
     */
    public List<Rating> getRatings() {
        return Ratings;
    }

    /**
     * Clase interna que representa una clasificación de la pelicula.
     */
    public static class Rating {
        private String Source;
        private String Value;
        /**
         * Obtiene la fuente de la clasificación.
         * @return Fuente de la clasificación.
         */
        public String getSource() {
            return Source;
        }
        /**
         * Obtiene el valor de la clasificación.
         * @return Valor de la clasificación.
         */
        public String getValue() {
            return Value;
        }
    }
}


