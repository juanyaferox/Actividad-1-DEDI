/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pa.cifpaviles.dam.entidades;

/**
 *
 * @author hulke
 */
public class CompaniaAerea {
    private int prefijo; //max 999 / id
    private String codigo; //2 caracteres, todo mayuscula, primer caracter letra siguiente numero o letra
    private String nombre;
    private String direccion;
    private String municipio;
    private String telefonoPasajero;//codigo pais 3 digitos + 12 digitos 
    private String telefonoAeropuerto;//mismo q arriba
    
    public CompaniaAerea(){}

    public CompaniaAerea(int prefijo, String codigo, String nombre, String direccion, String municipio,
                         String telefonoPasajero, String telefonoAeropuerto) {
        // Verificar los límites y formato de los campos según los comentarios
        if (prefijo <= 999 && prefijo >= 0) {
            this.prefijo = prefijo;
        } else {
            throw new IllegalArgumentException("El prefijo debe estar en el rango de 0 a 999.");
        }

        if (codigo.length() == 2 && Character.isLetter(codigo.charAt(0)) && 
            (Character.isDigit(codigo.charAt(1)) || Character.isLetter(codigo.charAt(1)))) {
            this.codigo = codigo.toUpperCase();
        } else {
            throw new IllegalArgumentException("El código debe tener 2 caracteres, el primer caracter " +
                    "debe ser una letra y el segundo un número o letra.");
        }

        // Otros campos simplemente se asignan sin validación específica en el constructor
        this.nombre = nombre;
        this.direccion = direccion;
        this.municipio = municipio;

        // Validar el formato del número de teléfono
        if (validarTelefono(telefonoPasajero)) {
            this.telefonoPasajero = telefonoPasajero;
        } else {
            throw new IllegalArgumentException("El formato del teléfono de pasajero no es válido.");
        }

        if (validarTelefono(telefonoAeropuerto)) {
            this.telefonoAeropuerto = telefonoAeropuerto;
        } else {
            throw new IllegalArgumentException("El formato del teléfono de aeropuerto no es válido.");
        }
    }

    // Método para validar el formato del número de teléfono
    private boolean validarTelefono(String telefono) {
        // Aquí solo se verifica la longitud total (12 a 15 caracteres) y que todos sean dígitos
        return telefono.length() >= 12 && telefono.length() <= 15 && telefono.chars().allMatch(Character::isDigit);
    }

    public int getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(int prefijo) {
        this.prefijo = prefijo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getTelefonoPasajero() {
        return telefonoPasajero;
    }

    public void setTelefonoPasajero(String telefonoPasajero) {
        this.telefonoPasajero = telefonoPasajero;
    }

    public String getTelefonoAeropuerto() {
        return telefonoAeropuerto;
    }

    public void setTelefonoAeropuerto(String telefonoAeropuerto) {
        this.telefonoAeropuerto = telefonoAeropuerto;
    }

    @Override
    public String toString() {
        return "CompaniaAerea{" + "prefijo=" + prefijo +
                ", codigo=" + codigo +
                ", nombre=" + nombre +
                ", direccion=" + direccion +
                ", municipio=" + municipio +
                ", telefonoPasajero=" + telefonoPasajero +
                ", telefonoAeropuerto=" + telefonoAeropuerto + '}';
    }
    
    
}
