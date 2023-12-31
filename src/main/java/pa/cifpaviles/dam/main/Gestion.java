/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pa.cifpaviles.dam.main;

import Temperaturas.AemetTemperatureComponent;
import Temperaturas.ResultadoTemperaturas;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import pa.cifpaviles.dam.entidades.*;

/**
 *
 * @author hulke
 */
public class Gestion {

    private List<CompaniaAerea> listCA;
    private List<Vuelos> listV;
    private List<VuelosDiarios> listVD;
    private List<Aeropuerto> listAe;

    public Gestion() {
        this.listCA = new ArrayList<>();
        this.listV = new ArrayList<>();
        this.listVD = new ArrayList<>();
        this.listAe = new ArrayList<>();
    }

    //métodos agregar-a-archivo.csv
    public void agregarCompania(CompaniaAerea compania) {
        this.listCA.add(compania);

        if (prefijoExiste(compania.getPrefijo())) {
            JOptionPane.showMessageDialog(null, "El prefijo ya existe en el archivo", "Error", 0);
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("csv/companias_aereas.csv", true))) {
            writer.write(compania.getPrefijo() + ";" + compania.getCodigo() + ";" + compania.getNombre()
                    + ";" + compania.getDireccion() + ";" + compania.getMunicipio() + ";" + compania.getTelefonoAeropuerto()
                    + ";" + compania.getTelefonoPasajero() + "\n");
            JOptionPane.showMessageDialog(null, "La compañia ha sido dada de alta con éxito", "SUCESSFULL", 1);
        } catch (IOException e) {
            e.getMessage();
        }
    }

    private boolean prefijoExiste(int prefijo) {
        try (BufferedReader reader = new BufferedReader(new FileReader("csv/companias_aereas.csv"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] campos = linea.split(";");
                if (campos.length > 0 && campos[0].equals(String.valueOf(prefijo))) {
                    return true; // El prefijo ya existe
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void agregarVuelo(Vuelos vuelo) {

        if (codigoExiste(vuelo.getCodigo())) {
            JOptionPane.showMessageDialog(null, "Ya existe un vuelo con ese mismo código", "ERROR", 0);
            return;
        }
        this.listV.add(vuelo);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("csv/vuelos.csv", true))) {
            writer.write(vuelo.getCodigo() + ";" + vuelo.getIATAOrigen() + ";" + vuelo.getIATADestino()
                    + ";" + vuelo.getNumeroPlazas() + ";" + vuelo.getHoraSalidaOficial() + ";" + vuelo.getHoraSalidaOficial()
                    + ";" + vuelo.getDiasQueOpera() + "\n");
            JOptionPane.showMessageDialog(null, "El vuelo ha sido dada de alta con éxito", "SUCESSFULL", 1);
        } catch (IOException e) {
            e.getMessage();
        }

    }

    private boolean codigoExiste(String codigo) {
        try (BufferedReader reader = new BufferedReader(new FileReader("csv/vuelos.csv"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] campos = linea.split(";");
                if (campos.length > 0 && campos[0].equals(codigo)) {
                    return true; // El código ya existe
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // El código no existe
    }

    public void agregarVueloDiario(VuelosDiarios vueloD) {
        this.listVD.add(vueloD);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("csv/vuelos_diarios.csv", true))) {
            writer.write(vueloD.getCod() + ";" + vueloD.getFecha() + ";" + vueloD.getHoraSalidaReal() + ";" + vueloD.getHoraLlegadaReal()
                    + ";" + vueloD.getNumeroPlazasOcupadas() + ";" + vueloD.getPrecioVuelo() + "\n");
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public void agregarAeropuerto(Aeropuerto aeropuerto) {
        this.listAe.add(aeropuerto);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("csv/aeropuertos.csv", true))) {
            writer.write(aeropuerto.getIATA() + ";" + aeropuerto.getNombre() + ";" + String.format("%05d", aeropuerto.getCodMunicipio()) + "\n");
            JOptionPane.showMessageDialog(null, "El aeropuerto ha sido dado de alta con éxito", "SUCESSFULL", 1);
        } catch (IOException e) {
            e.getMessage();
        }
    }

    //métodos para gestion de companias aereas
    public void altaCA(int prefijo, String codigo, String nombre, String direccion, String municipio, String telefonoPasajero, String telefonoAeropuerto) {
        CompaniaAerea CA = new CompaniaAerea(prefijo, codigo, nombre, direccion, municipio, telefonoPasajero, telefonoAeropuerto);
        agregarCompania(CA);
    }

    public static void bajaCA(String idABorrar) {
        String rutaArchivo = "csv/companias_aereas.csv";

        try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo)); BufferedWriter escritor = new BufferedWriter(new FileWriter("csv/temp.csv"))) {

            String linea;
            boolean idEncontrado = false;
            while ((linea = lector.readLine()) != null) {
                String[] campos = linea.split(";");
                if (!campos[0].equals(idABorrar)) {
                    escritor.write(linea);
                    escritor.newLine();
                } else {
                    idEncontrado = true;
                }
            }
            if (idEncontrado) {
                JOptionPane.showMessageDialog(null, "La compañia ha sido dada de baja con éxito", "SUCESSFULL", 1);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró ninguna compañia con ese prefijo", "ERROR", 0);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Reemplaza el archivo original con el nuevo archivo modificado
        reemplazarArchivo("csv/temp.csv", rutaArchivo);
    }

    private static void reemplazarArchivo(String archivoNuevo, String archivoOriginal) {
        try {
            Files.move(Path.of(archivoNuevo), Path.of(archivoOriginal), StandardCopyOption.REPLACE_EXISTING);
            JOptionPane.showMessageDialog(null, "Éxito en la operación", "SUCESSFULL", 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void modificarCA(String idAModificar, String nuevoCodigo, String nuevoNombre,
            String nuevaDireccion, String nuevoMunicipio, String nuevoTlfPasajero,
            String nuevoTlfAero) {
        String rutaArchivo = "csv/companias_aereas.csv";
        String rutaArchivoTemporal = "csv/temp.csv";

        try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo)); BufferedWriter escritor = new BufferedWriter(new FileWriter(rutaArchivoTemporal))) {

            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] campos = linea.split(";");
                if (campos[0].equals(idAModificar)) {
                    // Llama al método para realizar modificaciones en la línea actual
                    String lineaModificada = modificarCampos(campos, nuevoCodigo, nuevoNombre,
                            nuevaDireccion, nuevoMunicipio, nuevoTlfPasajero, nuevoTlfAero);
                    escritor.write(lineaModificada);
                } else {
                    escritor.write(linea);
                }
                escritor.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Reemplaza el archivo original con el nuevo archivo modificado
        reemplazarArchivo(rutaArchivoTemporal, rutaArchivo);
    }

    private static String modificarCampos(String[] campos, String nuevoCodigo, String nuevoNombre,
            String nuevaDireccion, String nuevoMunicipio, String nuevoTlfPasajero,
            String nuevoTlfAero) {
        // Modifica los campos solo si los nuevos valores no son nulos
        if (nuevoCodigo != null) {
            campos[1] = nuevoCodigo;
        }
        if (nuevoNombre != null) {
            campos[2] = nuevoNombre;
        }
        if (nuevaDireccion != null) {
            campos[3] = nuevaDireccion;
        }
        if (nuevoMunicipio != null) {
            campos[4] = nuevoMunicipio;
        }
        if (nuevoTlfPasajero != null) {
            campos[5] = nuevoTlfPasajero;
        }
        if (nuevoTlfAero != null) {
            campos[6] = nuevoTlfAero;
        }

        // Une los campos modificados para formar la línea actualizada
        return String.join(";", campos);
    }

    public static void consultarCA(String id) {
        String rutaArchivo = "csv/companias_aereas.csv";
        try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo));) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] campos = linea.split(";");
                if (campos.length > 0 && campos[0].equals(id)) {
                    String mensaje = String.format("Código: %s\nNombre: %s\nDirección: %s\nMunicipio: %s\nTeléfono Pasajero: %s\nTeléfono Aeropuerto: %s",
                            campos[1], campos[2], campos[3], campos[4], campos[5], campos[6]);
                    JOptionPane.showMessageDialog(null, mensaje, "Consulta de la compañía id " + id, 1);
                    return; // Termina la búsqueda después de encontrar la primera coincidencia
                } else {
                    JOptionPane.showMessageDialog(null, "No se obtuvo ningún resultado", "Consulta de la compañía id " + id, 0);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //métodos para gestion de vuelos
    public void altaV(String codigo, String iataOr, String iataDest, int numplazas, LocalTime horaSO, LocalTime horaLO, String diasOperativos) {
        Vuelos V = new Vuelos(codigo, iataOr, iataDest, numplazas, horaSO, horaLO, diasOperativos);
        agregarVuelo(V);
    }

    public static void bajaV(String idABorrar) {
        String rutaArchivo = "csv/vuelos.csv";

        try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo)); BufferedWriter escritor = new BufferedWriter(new FileWriter("csv/temp.csv"))) {

            String linea;
            boolean idEncontrado = false;
            while ((linea = lector.readLine()) != null) {
                String[] campos = linea.split(";");
                if (!campos[0].equals(idABorrar)) {
                    escritor.write(linea);
                    escritor.newLine();
                } else {
                    idEncontrado = true;
                }
            }
            if (idEncontrado) {
                JOptionPane.showMessageDialog(null, "El vuelo ha sido dada de baja con éxito", "SUCESSFULL", 1);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró ningún vuelo con ese código", "ERROR", 0);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Reemplaza el archivo original con el nuevo archivo modificado
        reemplazarArchivo("csv/temp.csv", rutaArchivo);
    }

    public static void modificarV(String idAModificar, String nuevoIATADest, String nuevoIATAOr,
            String nuevoNumPlazas, String nuevoHoraSO, String nuevoHoraLO,
            String nuevoDiasSOp) {
        String rutaArchivo = "csv/vuelos.csv";
        String rutaArchivoTemporal = "csv/temp.csv";
        String rutaArchivoDiarios = "csv/vuelos_diarios.csv";

        boolean idExiste = false;

        try (BufferedReader lectorDiarios = new BufferedReader(new FileReader(rutaArchivoDiarios))) {
            String lineaDiarios;
            while ((lineaDiarios = lectorDiarios.readLine()) != null) {
                String[] camposDiarios = lineaDiarios.split(";");
                if (camposDiarios[0].equals(idAModificar)) {
                    idExiste = true;
                    break;  // No es necesario seguir leyendo el archivo
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!idExiste) {
            try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo)); BufferedWriter escritor = new BufferedWriter(new FileWriter(rutaArchivoTemporal))) {

                String linea;
                while ((linea = lector.readLine()) != null) {
                    String[] campos = linea.split(";");
                    if (campos[0].equals(idAModificar)) {
                        // Llama al método para realizar modificaciones en la línea actual
                        String lineaModificada = modificarCamposVuelo(campos, nuevoIATADest, nuevoIATAOr,
                                nuevoNumPlazas, nuevoHoraSO, nuevoHoraLO, nuevoDiasSOp);
                        escritor.write(lineaModificada);
                    } else {
                        escritor.write(linea);
                    }
                    escritor.newLine();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            reemplazarArchivo(rutaArchivoTemporal, rutaArchivo);
        } else {
            JOptionPane.showMessageDialog(null, "El código ya se encuentra en vuelos diarios, porfavor intento con otro", "ERROR", 0);
        }
    }

    private static String modificarCamposVuelo(String[] campos, String nuevoIATADest, String nuevoIATAOr,
            String nuevoNumPlazas, String nuevoHoraSO, String nuevoHoraLO,
            String nuevoDiasSOp) {
        // Modifica los campos solo si los nuevos valores no son nulos
        if (nuevoIATADest != null) {
            campos[1] = nuevoIATADest;
        }
        if (nuevoIATAOr != null) {
            campos[2] = nuevoIATAOr;
        }
        if (nuevoNumPlazas != null) {
            campos[3] = nuevoNumPlazas;
        }
        if (nuevoHoraSO != null) {
            campos[4] = nuevoHoraSO;
        }
        if (nuevoHoraLO != null) {
            campos[5] = nuevoHoraLO;
        }
        if (nuevoDiasSOp != null) {
            campos[6] = nuevoDiasSOp;
        }

        // Une los campos modificados para formar la línea actualizada
        return String.join(";", campos);
    }

    public static void consultarV(String id) {
        String rutaArchivoVuelos = "csv/vuelos.csv";
        String rutaArchivoAeropuertos = "csv/aeropuertos.csv";
        try (BufferedReader lectorVuelos = new BufferedReader(new FileReader(rutaArchivoVuelos)); BufferedReader lectorAeropuertos = new BufferedReader(new FileReader(rutaArchivoAeropuertos))) {
            String lineaVuelos;
            while ((lineaVuelos = lectorVuelos.readLine()) != null) {
                String[] camposVuelos = lineaVuelos.split(";");
                if (camposVuelos.length > 0 && camposVuelos[0].equals(id)) {
                    String iataOrigen = camposVuelos[1];
                    String iataDestino = camposVuelos[2];
                    String infoAeropuertoOrigen = "";
                    String infoAeropuertoDestino = "";

                    // Buscar información en el archivo aeropuertos.csv
                    String lineaAeropuertos;
                    while ((lineaAeropuertos = lectorAeropuertos.readLine()) != null) {
                        String[] camposAeropuertos = lineaAeropuertos.split(";");

                        if (camposAeropuertos[0].equals(iataOrigen)) {
                            infoAeropuertoOrigen = camposAeropuertos[2];
                        }
                        if (camposAeropuertos[0].equals(iataDestino)) {
                            infoAeropuertoDestino = camposAeropuertos[2];
                        }
                        if (!infoAeropuertoOrigen.isEmpty() && !infoAeropuertoDestino.isEmpty()) {
                            break;
                        }
                    }//JA8475
                    ResultadoTemperaturas temperaturaOrigen = AemetTemperature(infoAeropuertoOrigen);
                    String tempMaxOrigen = temperaturaOrigen.getTemperaturaMaxima();
                    String tempMinOrigen = temperaturaOrigen.getTemperaturaMinima();
                    ResultadoTemperaturas temperaturasDestino = AemetTemperature(infoAeropuertoDestino);
                    String tempMaxDest = temperaturasDestino.getTemperaturaMaxima();
                    String tempMinDest = temperaturasDestino.getTemperaturaMinima();
                    String mensaje = String.format(
                            "IATA Aeropuerto Origen: %s\nTemperatura Máxima Origen: %s\nTemperatura Mínima Origen: %s\nIATA Aeropuerto Destino: %s\nTemperatura Máxima Destino: %s\nTemperatura Mínima Destino: %s\nNúmero de plazas: %s\nHora de salida oficial: %s\nHora de llegada oficial: %s\nDías operativos: %s",
                            iataOrigen, tempMaxOrigen, tempMinOrigen, iataDestino, tempMaxDest, tempMinDest, camposVuelos[3], camposVuelos[4], camposVuelos[5], camposVuelos[6]);

                    JOptionPane.showMessageDialog(null, mensaje, "Consulta del vuelo de codigo " + id, 1);

                    //implementar aqui API para las temperaturas usando infoAeropuertoOrigen y infoAeropuertoDestino
                } else {
                    JOptionPane.showMessageDialog(null, "No se obtuvo ningún resultado", "Consulta del vuelo de codigo " + id, 0);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Persistencia Datos Métodos/Lógica
    //
    //Panel de Salida
    private static ArrayList<String> valoresEncontrados = new ArrayList<>();

    public static ArrayList<String> getValoresEncontrados() {
        return valoresEncontrados;
    }

    public static void panelSalidas() {
        panelSalidas(LocalDate.now());
    }

    public static void panelSalidas(LocalDate dia) {
        if (dia == null) {
            dia = LocalDate.now();
        }

        String codigoVueloOperativo = verificarOperatividad(dia);
        if (codigoVueloOperativo == null) {
            return; // Terminar método
        }
        valoresEncontrados.clear();
        if (obtenerValorSegundaColumna(codigoVueloOperativo).equals("OVD")) {

            try (BufferedReader lector = new BufferedReader(new FileReader("csv/vuelos.csv"))) {
                String linea;
                while ((linea = lector.readLine()) != null) {
                    String[] campos = linea.split(";");
                    if (campos.length > 1 && campos[0].equals(codigoVueloOperativo)) {
                        valoresEncontrados.add(campos[0]);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String obtenerLetrasDia(LocalDate dia) {
        switch (dia.getDayOfWeek()) {
            case MONDAY:
                return "L";
            case TUESDAY:
                return "M";
            case WEDNESDAY:
                return "X";
            case THURSDAY:
                return "J";
            case FRIDAY:
                return "V";
            case SATURDAY:
                return "S";
            case SUNDAY:
                return "D";
            default:
                return " ";
        }
    }

    public static String verificarOperatividad(LocalDate dia) {

        try (BufferedReader lector = new BufferedReader(new FileReader("csv/vuelos_diarios.csv"))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] campos = linea.split(";");
                if (operaEnEsteDia(campos, dia)) {
                    return campos[0]; //Devuelve el código del vuelo
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null; // No se encontró un vuelo en ese dia
    }

    public static boolean operaEnEsteDia(String[] campos, LocalDate dia) {
        if (campos.length >= 6 && contieneAlMenosUnDia(campos[5], obtenerLetrasDia(dia))) {
            return true; // El vuelo opera en este día
        }
        return false;
    }

    private static boolean contieneAlMenosUnDia(String cadena, String dias) {
        for (char dia : dias.toCharArray()) {
            if (cadena.indexOf(dia) != -1) {
                return true;
            }
        }
        return false;
    }

    public static String obtenerValorSegundaColumna(String valorPrimeraColumna) {
        String rutaArchivo = "csv/vuelos.csv";

        try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] campos = linea.split(";");
                if (campos.length > 1 && campos[0].equals(valorPrimeraColumna)) {
                    return campos[1]; // Devuelve el valor de la segunda columna
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null; // No se encontró una coincidencia en la primera columna
    }

    //Panel de Llegadas
    public static String obtenerValorTerceraColumna(String valorPrimeraColumna) {
        String rutaArchivo = "csv/vuelos.csv";

        try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] campos = linea.split(";");
                if (campos.length > 1 && campos[0].equals(valorPrimeraColumna)) {
                    return campos[2]; // Devuelve el valor de la segunda columna
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null; // No se encontró una coincidencia en la primera columna
    }

    public static void panelEntradas(LocalDate dia) {
        if (dia == null) {
            dia = LocalDate.now();
        }

        String codigoVueloOperativo = verificarOperatividad(dia);
        if (codigoVueloOperativo == null) {
            return; //Terminar método
        }
        valoresEncontrados.clear();
        if (obtenerValorTerceraColumna(codigoVueloOperativo).equals("OVD")) {

            try (BufferedReader lector = new BufferedReader(new FileReader("csv/vuelos.csv"))) {
                String linea;
                while ((linea = lector.readLine()) != null) {
                    String[] campos = linea.split(";");
                    if (campos.length > 1 && campos[0].equals(codigoVueloOperativo)) {
                        valoresEncontrados.add(campos[0]);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //Recaudación
    public static double calcularMultiplicacion(String fechaTexto) {
        // Verificar si fechaTexto es nulo o está vacío
        if (fechaTexto == null) {
            fechaTexto = LocalDate.now().toString();
        }

        try (BufferedReader lector = new BufferedReader(new FileReader("csv/vuelos_diarios.csv"))) {
            String linea;
            double sumaMultiplicaciones = 0.0;

            while ((linea = lector.readLine()) != null) {
                String[] campos = linea.split(";");
                if (campos.length >= 6 && campos[1].equals(fechaTexto)) {
                    // Obtener valores de la 5a y 6a columna
                    double valor5aColumna = Double.parseDouble(campos[4]);
                    double valor6aColumna = Double.parseDouble(campos[5]);

                    // Multiplicar y sumar al resultado total
                    sumaMultiplicaciones += (valor5aColumna * valor6aColumna);
                }
            }

            return sumaMultiplicaciones;

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            return 0.0; // Manejar otros errores y devolver un valor predeterminado
        } catch (DateTimeParseException e) {
            e.printStackTrace();

            // Mostrar un JOptionPane con un mensaje de error
            JOptionPane.showMessageDialog(null, "Error: Formato de fecha incorrecto.", "Error", JOptionPane.ERROR_MESSAGE);

            return 0.0; // Devolver un valor predeterminado
        }
    }

    //Vuelos operativos
    public static void panelVuelosporCompañia(LocalDate dia) {
        if (dia == null) {
            dia = LocalDate.now();
        }

        String codigoVueloOperativo = verificarOperatividad(dia);
        if (codigoVueloOperativo == null) {
            return; //Terminar método
        }
        valoresEncontrados.clear();
        if (obtenerValorPrimeraColumna(codigoVueloOperativo).equals(codigoVueloOperativo)) {

            try (BufferedReader lector = new BufferedReader(new FileReader("csv/vuelos.csv"))) {
                String linea;
                while ((linea = lector.readLine()) != null) {
                    String[] campos = linea.split(";");
                    if (campos.length > 1 && campos[0].equals(codigoVueloOperativo)) {
                        valoresEncontrados.add(campos[0]);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String obtenerValorPrimeraColumna(String valorPrimeraColumna) {
        String rutaArchivo = "csv/vuelos.csv";

        try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] campos = linea.split(";");
                if (campos.length > 1 && campos[0].equals(valorPrimeraColumna)) {
                    return campos[0]; // Devuelve el valor de la segunda columna
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null; // No se encontró una coincidencia en la primera columna
    }

    //API 
    public static ResultadoTemperaturas AemetTemperature(String codMunicipio) {
        String apiKey = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqdWFuaWFnbzIwMDFAZ21haWwuY29tIiwianRpIjoiNmM1NDRlNmUtMjhiYS00NWQ0LTgyZWYtZmE0N2ZmYWViYWJlIiwiaXNzIjoiQUVNRVQiLCJpYXQiOjE3MDI0MTgzNjQsInVzZXJJZCI6IjZjNTQ0ZTZlLTI4YmEtNDVkNC04MmVmLWZhNDdmZmFlYmFiZSIsInJvbGUiOiIifQ.1nQue_YdNEHLDDNrVGUMZjy2hmCELb7FZ5WaAacCF4s";
        AemetTemperatureComponent APITemperatura = new AemetTemperatureComponent(apiKey, codMunicipio);
        return APITemperatura.obtenerTemperaturas();
    }
}
