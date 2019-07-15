/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import com.mysql.cj.jdbc.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author Armageddon132
 */
public class ClienteDao {
    
    public Connection con;
    
    public ClienteDao() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/actividadesculturalesdb?zeroDateTimeBehavior=convertToNull","root","mfbejaranob");
        } catch (Exception e) {
            System.out.println("error en la conexion: "+e);
        }
    }
    
    public String reservar(int idCliente, int idEvento){
        try {
            String sentencia_crear = "{call RESERVE(" +idCliente+ "," +idEvento+ ")}";
            CallableStatement ms = (CallableStatement) con.prepareCall(sentencia_crear);
            ms.executeQuery();
            return "Reserva correcta";
        } catch (Exception e) {
            return "Error al reservar"+e;
        }
    }
    
    public String client_exist(int idCliente){
        int result;
        try {
            String sentencia_crear = "{? = call CLIENT_EXIST(?)}";
            CallableStatement ms = (CallableStatement) con.prepareCall(sentencia_crear);
            ms.registerOutParameter(1, java.sql.Types.INTEGER);
            ms.setInt(2, idCliente);
            ms.execute();
            result = ms.getInt(1);
            if(result == 0){
                return "No existe";
            }
            return "Existe";
        } catch (Exception e) {
            return "Error al reservar"+e;
        }
    }
    
    public int returnClientId(int codCliente){
        int result = 0;
        try {
            String sentencia_crear = "{? = call RETURN_ID_CLIENT(?)}";
            CallableStatement ms = (CallableStatement) con.prepareCall(sentencia_crear);
            ms.registerOutParameter(1, java.sql.Types.INTEGER);
            ms.setInt(2, codCliente);
            ms.execute();
            result = ms.getInt(1);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
        
    }
    
    public ArrayList<Integer> listar_id_evento_reservas(int idCliente){
        ArrayList<Integer> list = new ArrayList<>();
        int temp;
        try {
            String sentencia_crear = "{call LIST_RESERVAS(?)}";
            CallableStatement ms = (CallableStatement) con.prepareCall(sentencia_crear);
            ms.setInt(1, idCliente);
            ResultSet rs = ms.executeQuery();
            while(rs.next()){
                temp = rs.getInt(1);
                list.add(temp);
            }
            return list;
        } catch (Exception e) {
            System.out.println("ERROR EN PROCEDIMIENTO DE LISTAR: "+e);
            return null;
        }
    }
    
    public String eliminar_reserva(int idCliente, int idEvento){
        try {
            String sentencia_crear = "{call DELETE_RESERVE(" +idCliente+ "," +idEvento+ ")}";
            CallableStatement ms = (CallableStatement) con.prepareCall(sentencia_crear);
            ms.executeQuery();
            return "Reserva eliminada correctamente";
        } catch (Exception e) {
            return "Error al eliminar reserva: "+e;
        }
    }
    
}
