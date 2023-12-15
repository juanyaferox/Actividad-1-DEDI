/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Temperaturas;

/**
 *
 * @author hulke
 */
public class ResultadoTemperaturas {
    private String temperaturaMaxima;
    private String temperaturaMinima;

    public ResultadoTemperaturas(String temperaturaMaxima, String temperaturaMinima) {
        this.temperaturaMaxima = temperaturaMaxima;
        this.temperaturaMinima = temperaturaMinima;
    }

    public String getTemperaturaMaxima() {
        return temperaturaMaxima;
    }

    public String getTemperaturaMinima() {
        return temperaturaMinima;
    }
}
