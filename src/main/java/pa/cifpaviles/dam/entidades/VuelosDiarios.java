/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pa.cifpaviles.dam.entidades;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author hulke
 */
public class VuelosDiarios {

    private String cod;
    private LocalDate fecha;
    private LocalTime horaSalidaReal;
    private LocalTime horaLlegadaReal;
    private int numeroPlazasOcupadas;
    private int precioVuelo;
    private Vuelos vueloAsociado;

    public VuelosDiarios() {
    }
    //constructor sin objeto vuelo asociado
     public VuelosDiarios(String cod, LocalDate fecha, LocalTime horaSalidaReal, LocalTime horaLlegadaReal,
            int numeroPlazasOcupadas, int precioVuelo) {
         
        if (cod.matches("([A-Z]{2}|[A-Z]\\d)")) {
            this.cod = cod;
        } else {
            throw new IllegalArgumentException("El código de vuelo no cumple con el formato esperado.");
        }
        this.fecha = fecha;
        this.horaSalidaReal = horaSalidaReal;
        this.horaLlegadaReal = horaLlegadaReal;
        this.numeroPlazasOcupadas = numeroPlazasOcupadas;
        this.precioVuelo = precioVuelo;
    }

    //constructor para hacer un vuelo diario asociado a un vuelo
    public VuelosDiarios(Vuelos vueloAsociado, LocalDate fecha, LocalTime horaSalidaReal, LocalTime horaLlegadaReal,
            int numeroPlazasOcupadas, int precioVuelo) {
        this.vueloAsociado = vueloAsociado;
        this.fecha = fecha;
        this.horaSalidaReal = horaSalidaReal;
        this.horaLlegadaReal = horaLlegadaReal;
        this.numeroPlazasOcupadas = numeroPlazasOcupadas;
        this.precioVuelo = precioVuelo;
        
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHoraSalidaReal() {
        return horaSalidaReal;
    }

    public void setHoraSalidaReal(LocalTime horaSalidaReal) {
        this.horaSalidaReal = horaSalidaReal;
    }

    public LocalTime getHoraLlegadaReal() {
        return horaLlegadaReal;
    }

    public void setHoraLlegadaReal(LocalTime horaLlegadaReal) {
        this.horaLlegadaReal = horaLlegadaReal;
    }

    public int getNumeroPlazasOcupadas() {
        return numeroPlazasOcupadas;
    }

    public void setNumeroPlazasOcupadas(int numeroPlazasOcupadas) {
        this.numeroPlazasOcupadas = numeroPlazasOcupadas;
    }

    public int getPrecioVuelo() {
        return precioVuelo;
    }

    public void setPrecioVuelo(int precioVuelo) {
        this.precioVuelo = precioVuelo;
    }

    public Vuelos getVueloAsociado() {
        return vueloAsociado;
    }

    public void setVueloAsociado(Vuelos vueloAsociado) {
        this.vueloAsociado = vueloAsociado;
    }
    
    @Override
    public String toString() {
        return "VuelosDiarios{" + ", cod=" + cod 
                + ", fecha=" + fecha
                + ", horaSalidaReal=" + horaSalidaReal
                + ", horaLlegadaReal=" + horaLlegadaReal
                + ", numeroPlazasOcupadas=" + numeroPlazasOcupadas
                + ", precioVuelo=" + precioVuelo + '}';
    }

}
