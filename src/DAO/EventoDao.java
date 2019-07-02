/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entidad.Artistat;
import Entidad.Eventot;
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
            String sentencia_crear = "{call CREATE_EVENTO('"+evento.getNombreEvento()+"','"+format1.format(evento.getFechaEvento())+"',"+artistas[0]+","+artistas[1]+","+artistas[2]+","+lugar+","+capacidad+","+fk_admin+")}";
            CallableStatement ms = (CallableStatement) con.prepareCall(sentencia_crear);
            ms.executeQuery();
            return "Evento creado";
        } catch (Exception e) {
            return "error en creacion"+e;
        }
    }
    
    public String editar_evento(){
        return "";
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
    
}
