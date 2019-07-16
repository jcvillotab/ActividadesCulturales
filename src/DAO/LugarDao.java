/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entidad.Lugart;
import com.mysql.cj.jdbc.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Armageddon132
 */

public class LugarDao {
    
    public Connection con;
    
    public LugarDao() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/actividadesculturalesdb?zeroDateTimeBehavior=convertToNull","root","btZ7op0gGo");
        } catch (Exception e) {
            System.out.println("error en la conexion: "+e);
        }
    }
    
    public String create_lugar(Lugart lugarNew){
        try {
            String sentencia_crear = "{call CREATE_LUGAR('"+lugarNew.getNombreLugar()+"','"+lugarNew.getCubiertaLugar()+"',"+ lugarNew.getCapacidadLugar()+",'"+lugarNew.getSeccionLugar()+"')}";
            CallableStatement ms = (CallableStatement) con.prepareCall(sentencia_crear);
            ms.executeQuery();
            return "Registro exitoso";
            
        } catch (Exception e) {
            return "Error en db: "+e;
        }
    }
    
    public String edit_lugar(Lugart lugar, int idLugar ){
        try {
            String sentencia_editar = "{call EDIT_LUGAR("+idLugar+",'"+lugar.getNombreLugar()+"','"+lugar.getCubiertaLugar()+"',"+ lugar.getCapacidadLugar()+",'"+lugar.getSeccionLugar()+"')}";
            CallableStatement ms = (CallableStatement) con.prepareCall(sentencia_editar);
            ms.executeQuery();
            return "Modificacion exitosa";
        } catch (Exception e) {
            return "Error en db: "+e;
        }
    }
    
    public ArrayList listar_lugares(){
        ArrayList<Lugart> lugares = new ArrayList();
        try {
            Lugart lugarTemp;
            String sentencia_crear = "{call LIST_LUGARES()}";
            CallableStatement ms = (CallableStatement) con.prepareCall(sentencia_crear);
            ResultSet rs = ms.executeQuery();
            while(rs.next()){
               lugarTemp = new Lugart(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5));
               lugares.add(lugarTemp);
            }
            
            return lugares;
        } catch (Exception e) {
            System.out.println("ERROR EN PROCEDIMIENTO ALMACENADO"+e);
            return null;
        }
    }
    
    public Lugart buscarById(int id){
        ArrayList<Lugart> lugares =listar_lugares();
        for (Lugart lugar : lugares) {
            if(lugar.getIdLugar().equals(id)){
                return lugar;
            }
        }
        return null;
    }
    
    public Lugart buscarByName(String name){
        ArrayList<Lugart> lugares =listar_lugares();
        for (Lugart lugar : lugares) {
            if(lugar.getNombreLugar().equals(name)){
                return lugar;
            }
        }
        return null;
    }
}
