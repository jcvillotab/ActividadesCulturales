/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import Entidad.Eventot;
import Entidad.Lugart;
import com.mysql.cj.jdbc.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author JoanGomez
 */
public class EventoDao {
    
    public Connection con;

    public EventoDao() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/actividadesculturalesdb?zeroDateTimeBehavior=convertToNull","root","juancamilovill9");
        } catch (Exception e) {
            System.out.println("error en la conexion"+e);
        }
    }
    
    
    public String crear_evento(Eventot evento,int[] artistas, int lugar, int capacidad, int fk_admin){
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            System.out.println("llegue aqui");
            String sentencia_crear = "{call CREAR_EVENTO('"+evento.getNombreEvento()+"','"+format1.format(evento.getFechaEvento())+"',"+artistas[0]+","+artistas[1]+","+artistas[2]+","+lugar+","+capacidad+","+fk_admin+")}";
            CallableStatement ms = (CallableStatement) con.prepareCall(sentencia_crear);
            ms.executeQuery();
            return "Evento creado";
        } catch (Exception e) {
            return "error en creacion"+e;
        }
    }
    
    public String editar_evento(Eventot event, int[] ids_artist, Lugart lugar, int capacity){
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String sentencia = "{call EDITAR_EVENTO("+event.getIdEvento()+",'"+event.getNombreEvento()+"','"+format1.format(event.getFechaEvento())+"',"+ids_artist[0]+","+ids_artist[1]+","+ids_artist[2]+","+lugar.getIdLugar()+","+capacity+")}";
            CallableStatement ms = (CallableStatement) con.prepareCall(sentencia);
            ms.executeQuery();
            return "EVENTO EDITADO";
        } catch (Exception e) {
            return "ERROR EN PROCEDIMIENTO EDITAR_EVENTO  "+e;
        }
    }
    
    public String close_evento(int id_evento){
        try {
            String sentencia = "{call CLOSE_EVENT("+id_evento+")}";
            CallableStatement ms = (CallableStatement) con.prepareCall(sentencia);
            ms.executeQuery();
            return "EVENTO CERRADO";
        } catch (Exception e) {
            return "ERROR EN PROCEDIMIENTO CLOSE_EVENT "+e;
        }
    }
    
    public ArrayList listar(){
        ArrayList<Eventot> eventos = new ArrayList();                                           
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Eventot eventTemp;
            CallableStatement ms =  (CallableStatement) con.prepareCall("{call LIST_EVENT()}");
            ResultSet rs = ms.executeQuery();
            while (rs.next()) {                
                eventTemp = new Eventot(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getInt(4));
                eventos.add(eventTemp);
            }
            return eventos;
        } catch (Exception e) {
            System.out.println("ERROR EN PROCEDIMIENTOS DE LISTAR: "+e);
            return null;
        }
    }
    
    public int buscarIdLugar(int idEvento){
        try {
            CallableStatement ms =  (CallableStatement) con.prepareCall("{call EDIT_LUGAR("+ idEvento +")}");
            ms.execute();
            return ms.getInt(idEvento);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int capacidad (int id_evento){
        try {
            String sentencia = "{call CAPAIDAD_LUGARxEVENTO("+id_evento+")}";
            CallableStatement ms = (CallableStatement) con.prepareCall(sentencia);
            ResultSet rs = ms.executeQuery();
            int capacidad = 0;
            while(rs.next()){
                capacidad = rs.getInt(1);
            }
            return capacidad;
        } catch (Exception e) {
            System.out.println("Error en procedimiento almacenado CAPAIDAD_LUGARxEVENTO: "+e);
            return -1;
        }
    }
    
    public ArrayList buscar_ids_artistas(int id_evento){
        ArrayList<Integer> ids = new ArrayList();
        try {
            String sentencia = "{call IDS_ARTISTAS(" + id_evento + ")}";
            CallableStatement ms = (CallableStatement) con.prepareCall(sentencia);
            ResultSet rs = ms.executeQuery();
            int id;
            int count = 0;
            while (rs.next()) {
                id = rs.getInt(1);
                ids.add(id);
                count++;
            }
            if (count < 3) {
                int aux = 3 - count;
                for (int i = 0; i < aux; i++) {
                    ids.add(0);
                }
            }
            return ids;
        } catch (Exception e) {
            System.out.println("ERROR EN EL PPROCEDIMIENTO ALMACENADO: " + e);
            return null;
        }
    }
    
    
    
}
