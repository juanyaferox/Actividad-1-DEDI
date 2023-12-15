package Temperaturas;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import Temperaturas.ResultadoTemperaturas;

public class AemetTemperatureComponent {

    private final String apiKey;
    private final String codigoMunicipio;

    public AemetTemperatureComponent(String apiKey, String codigoMunicipio) {
        this.apiKey = apiKey;
        this.codigoMunicipio = codigoMunicipio;
    }

    public ResultadoTemperaturas obtenerTemperaturas() {
        HttpURLConnection connection = null;
        try {
            // Construir la URL de la API de la AEMET
            String url = "https://opendata.aemet.es/opendata/api/prediccion/especifica/municipio/diaria/" + codigoMunicipio + "/?api_key=" + apiKey;

            // Realizar la conexión HTTP
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");

            // Obtener la respuesta
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Leer la respuesta
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }

                    String responseText = response.toString();
                    System.out.println(responseText);

                    // Supongamos que las temperaturas están en el primer elemento del array porque no sé donde está
                    String temperaturaMaxima = extraerValor(responseText, "temperaturaMax");
                    String temperaturaMinima = extraerValor(responseText, "temperaturaMin");

                    // Devolver el valor de la temperatura
                    return new ResultadoTemperaturas(temperaturaMaxima, temperaturaMinima);
                }
            } else {
                System.out.println("Error en la conexión a la API. Código de respuesta: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Cerrar la conexión
            if (connection != null) {
                connection.disconnect();
            }
        }
        return null; // En caso de error, se devuelve null
    }

    private String extraerValor(String responseText, String clave) {
        // Supongamos que el formato es algo así: temperaturaMax:30,temperaturaMin:20
        String[] partes = responseText.split(",");
        for (String parte : partes) {
            String[] keyValue = parte.split(":");
            if (keyValue.length == 2 && keyValue[0].trim().equals(clave)) {
                return keyValue[1].trim();
            }
        }
        return null;
    }
}
