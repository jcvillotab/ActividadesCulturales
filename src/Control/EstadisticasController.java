/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.util.ArrayList;

/**
 *
 * @author Armageddon132
 */
public class EstadisticasController {
    
    public String porcentajeOcupacionEvento(String[] evento){// [ nombre_evento, capacidad_evento, cantidad_reserva]
        String estadisticas = "";
        estadisticas+=  "Evento: "+evento[0]+"\n";
        estadisticas+=  "Capacidad del evento: "+evento[1]+"\n";
        estadisticas+=  "Capacidad del lugar: "+evento[2]+"\n";
        estadisticas+=  "Cantidad de reservas: "+evento[3]+"\n";
        estadisticas+=  "Porcentaje de reservas: "+((Float.parseFloat(evento[3])*100)/(Float.parseFloat(evento[1])))+"%\n";
        estadisticas+=  "Porcentaje de ocupacion: "+((Float.parseFloat(evento[3])*100)/(Float.parseFloat(evento[2])))+"%\n\n";
        return estadisticas;
    }
    
    public String porcentajeOcupacionEventos(ArrayList<String[]> eventos){// [nombre_eventos,  capacidad_eventos, cantidad_reservas]
        String estadisticas = "";
        for(int i=0;i<eventos.size();i++){
            estadisticas+=  ""+eventos.get(i)[0]+"\n";
            estadisticas+=  "capacidad del evento: "+eventos.get(i)[1]+"\n";
            estadisticas+=  "cantidad de reservas: "+eventos.get(i)[2]+"\n";
            estadisticas+=  "porcentaje de reservas: "+((Float.parseFloat(eventos.get(i)[2])*100)/(Float.parseFloat(eventos.get(i)[1])))+"%\n\n";
        }        
        return estadisticas;
    }
}
