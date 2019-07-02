/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entidad.Admint;
import com.mysql.cj.jdbc.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 *
 * @author JoanGomez
 */
public class AdminDao {
    private Admint adminS;
    public Connection con;

    public AdminDao() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/actividadesculturalesdb?zeroDateTimeBehavior=convertToNull","root","juancamilovill9");
        } catch (Exception e) {
            System.out.println("error en la conexion"+e);
        }
    }
    
    public String create_admin(Admint adminNew){
        try {
            String lista_usuarios = "{call LIST_ADMINN()}";
            String sentencia_crear = "{call CREATE_ADMIN('"+adminNew.getNombreAdmin()+"','"+adminNew.getContraseniaAdmin()+"')}";
            CallableStatement ms = (CallableStatement) con.prepareCall(lista_usuarios);
            ResultSet rs = ms.executeQuery();
            while(rs.next()){
                if(adminNew.getNombreAdmin().equals(rs.getString(1))){
                    return "el usuario ya existe";
                }
            }
            ms = (CallableStatement) con.prepareCall(sentencia_crear);
            ms.executeQuery();
            return "registro exitoso";
            
        } catch (Exception e) {
            return "Error en db: "+e;
        }
    }
    
    public String login_admin(Admint admin){
        try {
            String lista_usuarios = "{call LIST_ADMINC()}";
            CallableStatement ms = (CallableStatement) con.prepareCall(lista_usuarios);
            ResultSet rs = ms.executeQuery();
            while(rs.next()){
                if(admin.getNombreAdmin().equals(rs.getString(2))){
                    if(admin.getContraseniaAdmin().equals(rs.getString(3))){
                        Admint tempAdmin = new Admint(rs.getInt(1),rs.getString(2),rs.getString(3));
                        setAdminS(tempAdmin);
                        return "Datos correctos";
                    }else{
                        return "Contraseña incorreecta";
                    }
                }
            }
            return "Usuario no existe";
            
        } catch (Exception e) {
            return "Error en db: "+e;
        }
    }

    public Admint getAdminS() {
        return adminS;
    }

    public void setAdminS(Admint adminS) {
        this.adminS = adminS;
    }
    
}
