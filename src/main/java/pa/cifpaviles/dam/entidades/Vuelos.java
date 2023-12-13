package pa.cifpaviles.dam.entidades;

import java.time.LocalTime;

/**
 *
 * @author hulke
 */
public class Vuelos {

    private String codigo;//Se compone del código de la compañía y un número entero positivo cuyo máximo es 9999.
    //No es necesario completar con ceros. Ejemplos de códigos de vuelos válidos: V73585, IB480
    private String IATAOrigen;
    private String IATADestino;
    private int numeroPlazas;
    private LocalTime horaSalidaOficial;
    private LocalTime horaLlegadaOficial;
    private String diasQueOpera;//Cadena con siete caracteres que contiene qué días de la semana opera el vuelo. 
    //Por ejemplo, si lo hace todos los días: “LMXJVSD”. Se da por hecho que no hay variaciones anuales

    public Vuelos() {
    }

    public Vuelos(String codigo, String IATAOrigen, String IATADestino,
            int numeroPlazas, LocalTime horaSalidaOficial, LocalTime horaLlegadaOficial,
            String diasQueOpera) {
        // Validación del código
        if (validarCodigo(codigo)) {
            this.codigo = codigo;
        } else {
            throw new IllegalArgumentException("El código de vuelo no cumple con el formato esperado.");
        }

        this.IATAOrigen = IATAOrigen;
        this.IATADestino = IATADestino;

        // Validación del número de plazas (positivo)
        if (numeroPlazas > 0) {
            this.numeroPlazas = numeroPlazas;
        } else {
            throw new IllegalArgumentException("El número de plazas debe ser un entero positivo.");
        }

        this.horaSalidaOficial = horaSalidaOficial;
        this.horaLlegadaOficial = horaLlegadaOficial;

        // Validación de los días que opera (cadena de 7 caracteres)
        if (diasQueOpera.length() >= 1 && diasQueOpera.length() <= 7 && diasQueOpera.matches("[LMXJVSD]+")) {
            this.diasQueOpera = diasQueOpera;
        } else {
            throw new IllegalArgumentException("La cadena de días que opera no cumple con el formato esperado.");
        }
    }

    private boolean validarCodigo(String codigo) {
        String regex = "([A-Z]\\d|[A-Z]{2})\\d{4}";

        return codigo.matches(regex);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getIATAOrigen() {
        return IATAOrigen;
    }

    public void setIATAOrigen(String IATAOrigen) {
        this.IATAOrigen = IATAOrigen;
    }

    public String getIATADestino() {
        return IATADestino;
    }

    public void setIATADestino(String IATADestino) {
        this.IATADestino = IATADestino;
    }

    public int getNumeroPlazas() {
        return numeroPlazas;
    }

    public void setNumeroPlazas(int numeroPlazas) {
        this.numeroPlazas = numeroPlazas;
    }

    public LocalTime getHoraSalidaOficial() {
        return horaSalidaOficial;
    }

    public void setHoraSalidaOficial(LocalTime horaSalidaOficial) {
        this.horaSalidaOficial = horaSalidaOficial;
    }

    public LocalTime getHoraLlegadaOficial() {
        return horaLlegadaOficial;
    }

    public void setHoraLlegadaOficial(LocalTime horaLlegadaOficial) {
        this.horaLlegadaOficial = horaLlegadaOficial;
    }

    public String getDiasQueOpera() {
        return diasQueOpera;
    }

    public void setDiasQueOpera(String diasQueOpera) {
        this.diasQueOpera = diasQueOpera;
    }
    
}
