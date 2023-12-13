/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pa.cifpaviles.dam.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author hulke
 */
public class AemetTemperatureComponent {

    private final String apiKey;
    private final String codigoMunicipio;

    public AemetTemperatureComponent(String apiKey, String codigoMunicipio) {
        this.apiKey = apiKey;
        this.codigoMunicipio = codigoMunicipio;
    }

    public void mostrarTemperaturas() {
        try {
            // Construir la URL de la API de la AEMET
            String url = "https://opendata.aemet.es/opendata/api/prediccion/especifica/municipio/diaria/" + codigoMunicipio + "/?api_key=" + apiKey;

            // Realizar la conexión HTTP
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");

            // Obtener la respuesta
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Leer la respuesta
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Procesar la respuesta y mostrar las temperaturas (aquí deberías adaptar la lógica según la respuesta de la API)
                String jsonResponse = response.toString();
                System.out.println("Respuesta de la API de la AEMET: " + jsonResponse);

                // Aquí deberías parsear el JSON de la respuesta y extraer las temperaturas
                // Mostrar las temperaturas en tu interfaz de usuario o hacer lo que desees con la información.
            } else {
                System.out.println("Error en la conexión a la API. Código de respuesta: " + responseCode);
            }

            // Cerrar la conexión
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
