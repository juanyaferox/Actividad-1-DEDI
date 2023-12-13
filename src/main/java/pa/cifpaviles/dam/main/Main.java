/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pa.cifpaviles.dam.main;

import pa.cifpaviles.dam.entidades.*;

/**
 *
 * @author juanya
 */
public class Main {
  
    
    public static void main(String[] args) {
       /* //instancion de la clase gestion para el uso de metodos
        Gestion gestion = new Gestion();
        //creacion de los aeropuertos
        Aeropuerto aeABC = new Aeropuerto("ABC", "Aeropuerto de Albacete", 02003);
        Aeropuerto aeEDI = new Aeropuerto("EDI", "Aeropuerto de Edimburgo", 00000);
        Aeropuerto aePNA = new Aeropuerto("PNA", "Aeropuerto de Pamplona", 31201);
        Aeropuerto aeSBZ = new Aeropuerto("SBZ", "Aeropuerto Internacional de Sibiu", 00000);
        Aeropuerto aeYOW = new Aeropuerto("YOW", "Aeropuerto Internacional de Ottawa", 00000);
        Aeropuerto aeOVD = new Aeropuerto("OVD","Aeropuerto de Asturias", 33044);
        //uso del metodo para añadir los aeropuertos a la lista
        gestion.agregarAeropuerto(aeABC);
        gestion.agregarAeropuerto(aeEDI);
        gestion.agregarAeropuerto(aePNA);
        gestion.agregarAeropuerto(aeSBZ);
        gestion.agregarAeropuerto(aeYOW);
        gestion.agregarAeropuerto(aeOVD); */
       
       java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrameMenu ventanaPrincipal = new JFrameMenu();
                ventanaPrincipal.setVisible(true);
                
            }
        });
        
        
        /*crear pantalla principal->
            gestion compañia areas
                - alta, baja, modificar, consultar companias aereas;
                - verificar que los datos ingresados sean correctos;
                - compañias creadas en un csv(pueden ser dadas de alta al crear un vuelo) -> usar metodo agregarCompania(compania);
            gestion de vuelos;
                - alta, baja, modificar, consultar vuelos;
                - registro en un archivo cvs -> usar metodos agregarVuelo y agregarVueloDiario;
                - en caso de que un vuelo ya esté asociado a un vuelo diario este primero ya no se pude modificar;
            panel de salidas -> mostrar salidas de aeropuerto el dia seleccionado, ordenado por hora salida ascendente;
            panel llegada -> mostrar llegas de aeropuerto el dia seleccionado, ordenado por hora llegada ascendente(por defecto dia de hoy);
            vuelos por compañia en dia concreto;
            recaudaciones vuelos completados en dia concreto -> numplazas*precio billete;
            lista vuelo previstos a un destino concreto los siguientes 7 dias a la fecha actual, ordenador por fecha y hora salida;
        */
        
    }
}
