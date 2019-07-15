/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entidad.Artistat;
import com.mysql.cj.jdbc.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author JoanGomez
 */
public class ArtistaDao {
    
    public Connection con;
    
    public ArtistaDao() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/actividadesculturalesdb?zeroDateTimeBehavior=convertToNull","root","mfbejaranob");
        } catch (Exception e) {
            System.out.println("error en la conexion: "+e);
        }
    }
    
    
    
    public String create_artista(Artistat artistaNew){
        try {
            String sentencia_crear = "{call CREATE_ARTISTA("+artistaNew.getIdArtista()+",'"+artistaNew.getNombreArtista()+"','"+artistaNew.getOcupacionArtista()+"')}";
            CallableStatement ms = (CallableStatement) con.prepareCall(sentencia_crear);
            ms.executeQuery();
            return "Registro exitoso";
            
        } catch (Exception e) {
            return "Error en db: "+e;
        }
    }
    
    public String edit_artista(Artistat artista){
        try {
            String sentencia_editar = "{call EDIT_ARTISTA("+artista.getIdArtista()+",'"+artista.getNombreArtista()+"','"+artista.getOcupacionArtista()+"')}";
            CallableStatement ms = (CallableStatement) con.prepareCall(sentencia_editar);
            ms.executeQuery();
            return "Modificacion exitosa";
        } catch (Exception e) {
            return "Error en db: "+e;
        }
    }
    
    public String delete_artista(Integer id){
        try {
            String sentencia_delete = "{call DELETE_ARTISTA("+id+")}";
            CallableStatement ms = (CallableStatement) con.prepareCall(sentencia_delete);
            ms.executeQuery();
            return "Artista eliminado";
        } catch (Exception e) {
            return "Error en db: "+e;
        }
    }
    
    public Artistat buscarById(Integer id){
        ArrayList<Artistat> artistas =listar_artistas();
        for (Artistat artista : artistas) {
            if(artista.getIdArtista().equals(id)){
                return artista;
            }
        }
        return null;
    }
    
    public ArrayList listar_artistas(){
        ArrayList<Artistat> artistas = new ArrayList();
        try {
            Artistat artistaTemp;
            String sentencia_crear = "{call LIST_ARTISTAS()}";
            CallableStatement ms = (CallableStatement) con.prepareCall(sentencia_crear);
            ResultSet rs = ms.executeQuery();
            while(rs.next()){
               artistaTemp = new Artistat(rs.getInt(1),rs.getString(2),rs.getString(3));
               artistas.add(artistaTemp);
            }
            return artistas;
        } catch (Exception e) {
            return null;
        }
    }
}
