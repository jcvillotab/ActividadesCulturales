/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import DAO.LugarDao;
import Entidad.Lugart;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Armageddon132
 */
public class LugartController implements Serializable {
    private LugarDao conDB = new LugarDao();
    
    public String create(Lugart lugart) {
        if(lugart.getNombreLugar().length()<6 || lugart.getNombreLugar().length()>32){
            return "Error en el tamaño del nombre del lugar";
        }
         
        if(lugart.getCapacidadLugar()>6000){
            System.out.println(lugart.getCapacidadLugar());
            return "Error en la capacidad definida";
        }
        
        if (lugart.getCubiertaLugar() == null) {
            return "Error, cubierta no definida" + lugart.getCubiertaLugar();
        }
        
        if (lugart.getSeccionLugar() == null) {
            return "Error, seccion no definida";
        }
        
        
        return conDB.create_lugar(lugart) ;
    }
    
    public String edit(Lugart lugart, int idLugar){
        return conDB.edit_lugar(lugart, idLugar);
    }
    
    public ArrayList findLugarList(){
        return conDB.listar_lugares();
    }
    
    
    public static String[] listToArrayPlace(List<Lugart> list){
        List<String> names = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            names.add(list.get(i).getNombreLugar());
        }
        return names.toArray(new String[0]);
    }
    
    public Lugart findById(int idLugar){
        return conDB.buscarById(idLugar);
    }
    
    public Lugart findByName(String nameLugar){
        return conDB.buscarByName(nameLugar);
    }
    
    public ArrayList listar_lugares(){
        ArrayList<Lugart> artistas = conDB.listar_lugares();
        return artistas;
    }
    
    public String[][] listar_datos(){
        Lugart temp;
        ArrayList<Lugart> lugares = conDB.listar_lugares();
        String[][] data = new String[lugares.size()][5];
        
        for (int i = 0; i < lugares.size(); i++) {
            temp = lugares.get(i);
            data[i][0] = String.valueOf(temp.getIdLugar());
            data[i][1] = temp.getNombreLugar();
            data[i][2] = temp.getCubiertaLugar();
            data[i][3] = temp.getSeccionLugar();
            data[i][4] = String.valueOf(temp.getCapacidadLugar());
        }
        return data;
    }
    
    public String[] listar_nombres(){
        ArrayList<Lugart> artistas = listar_lugares();
        String[] names = new String[artistas.size()+1];
        names[0]="N/A";
        for (int i = 0; i < artistas.size(); i++) {
            names[i+1] = artistas.get(i).getNombreLugar();
        }
        return names;
    }
}
