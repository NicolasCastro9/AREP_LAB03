package edu.escuelaing.arem.ASE.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    public void testApiConnection() {
    try {
        String movieInfo = HttpConnection.getMovie("ted");
        assertNotNull(movieInfo);
        
    } catch (IOException e) {
        fail("IOException occurred during API connection");
    }
}
    /**
     * Envía una solicitud GET al servidor HTTP simulado utilizando la URI especificada.
     * 
     * @param uri la URI de la solicitud GET
     * @return la respuesta del servidor HTTP
     * @throws Exception si ocurre un error durante la solicitud
     */
    private String sendGetRequest(String uri) throws Exception {
        // Define la URL del servidor HTTP simulado
        URL url = new URL("http://localhost:35000" + uri);

        // Abre la conexión HTTP
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Lee la respuesta del servidor
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        // Cierra la conexión
        connection.disconnect();

        // Retorna la respuesta del servidor
        return response.toString();
    }


public void testCache() {
    try {
        String movieInfo1 = Cache.inMemory("Titanic");
        assertNotNull(movieInfo1);

        String movieInfo2 = Cache.inMemory("Titanic");
        assertNotNull(movieInfo2);

        
        assertEquals(movieInfo1, movieInfo2);
    } catch (IOException e) {
        fail("IOException occurred during cache test");
    }
}
public void testBuildHtmlFromJson() {
    String jsonExample = "{\"Title\":\"The Shawshank Redemption\",\"Year\":\"1994\",\"Genre\":\"Drama\",\"Director\":\"Frank Darabont\"}";
    String htmlResult = Cache.buildHtmlFromJson(jsonExample);
    
    assertTrue(htmlResult.contains("<strong>Title:</strong> The Shawshank Redemption<br>"));
    assertTrue(htmlResult.contains("<strong>Year:</strong> 1994<br>"));
    assertTrue(htmlResult.contains("<strong>Genre:</strong> Drama<br>"));
    assertTrue(htmlResult.contains("<strong>Director:</strong> Frank Darabont<br>"));
    
}
public void testBuildHtmlFromJson2() {
    String jsonExample = "{\"Title\":\"The Godfather\",\"Year\":\"1972\",\"Genre\":\"Crime, Drama\",\"Director\":\"Francis Ford Coppola\"}";
    String htmlResult = Cache.buildHtmlFromJson(jsonExample);
    
    assertTrue(htmlResult.contains("<strong>Title:</strong> The Godfather<br>"));
    assertTrue(htmlResult.contains("<strong>Year:</strong> 1972<br>"));
    assertTrue(htmlResult.contains("<strong>Genre:</strong> Crime, Drama<br>"));
    assertTrue(htmlResult.contains("<strong>Director:</strong> Francis Ford Coppola<br>"));

}
public void testBuildHtmlFromJson3() {
    String jsonExample = "{\"Title\":\"Pulp Fiction\",\"Year\":\"1994\",\"Genre\":\"Crime, Drama\",\"Director\":\"Quentin Tarantino\",\"Actors\":\"John Travolta, Uma Thurman, Samuel L. Jackson\"}";
    String htmlResult = Cache.buildHtmlFromJson(jsonExample);
    
    assertTrue(htmlResult.contains("<strong>Title:</strong> Pulp Fiction<br>"));
    assertTrue(htmlResult.contains("<strong>Year:</strong> 1994<br>"));
    assertTrue(htmlResult.contains("<strong>Genre:</strong> Crime, Drama<br>"));
    assertTrue(htmlResult.contains("<strong>Director:</strong> Quentin Tarantino<br>"));
    assertTrue(htmlResult.contains("<strong>Actors:</strong> John Travolta, Uma Thurman, Samuel L. Jackson<br>"));
  
}
    //probar con el servidor funcionando
    // public void testGetRequestToURI() {
    //     try {
    //         URL url = new URL("http://localhost:35000/hello");
    //         URLConnection connection = url.openConnection();
    //         BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    //         String response = in.readLine();
    //         assertEquals("<h1>Hello, world!</h1>", response);
    //         in.close();
    //     } catch (Exception e) {
    //         fail("Error occurred during GET request to URI");
    //     }
    // }
        //probar con el servidor funcionando
    //     public void testPostRequestToURI() {
    //     try {
    //         URL url = new URL("http://localhost:35000/echo");
    //         URLConnection connection = url.openConnection();
    //         connection.setDoOutput(true);
    //         PrintWriter out = new PrintWriter(connection.getOutputStream());
    //         out.println("This is a test POST request.");
    //         out.close();

    //         BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    //         String response = in.readLine();
    //         assertEquals("Echoing your POST data:", response);
    //         in.close();
    //     } catch (Exception e) {
    //         fail("Error occurred during POST request to URI");
    //     }
    // }
    //probar con el servidor funcionando
    // public void testMovieRequestToURI() throws Exception {
    //     try {

    //         String uri = "/title?name=The+Shawshank+Redemption";
    //         String movieInfo = sendGetRequest(uri);
    //         assertNotNull(movieInfo);
    //         assertTrue(movieInfo.contains("The Shawshank Redemption"));
            
    //     } catch (IOException e) {
    //         fail("IOException occurred during GET request to URI");
    //     }
    // }
    




}
