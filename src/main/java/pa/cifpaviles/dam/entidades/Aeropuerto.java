







/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pa.cifpaviles.dam.entidades;
/**
 *
 * @author juanya
 */
public class Aeropuerto {
    private String IATA;
    private String nombre;
    private int codMunicipio;

    public Aeropuerto() {
    }

    public Aeropuerto(String IATA, String nombre, int codMunicipio) {
        this.IATA = IATA;
        this.nombre = nombre;
        this.codMunicipio = codMunicipio;
    }
    

    public String getIATA() {
        return IATA;
    }

    public void setIATA(String IATA) {
        this.IATA = IATA;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCodMunicipio() {
        return codMunicipio;
    }

    public void setCodMunicipio(int codMunicipio) {
        this.codMunicipio = codMunicipio;
    }
    
    
}
