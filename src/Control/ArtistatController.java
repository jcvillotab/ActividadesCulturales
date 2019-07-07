/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import DAO.ArtistaDao;
import Entidad.Artistat;
import java.io.Serializable;
import java.util.ArrayList;


/**
 *
 * @author Armageddon132
 */
public class ArtistatController implements Serializable {
    private final ArtistaDao conDB = new ArtistaDao();

    public String create(Artistat artistaNew){
        
        ArrayList<Artistat> artistas = listar_artistas();
        for (Artistat artistaTemp : artistas) {
            if(artistaTemp.getIdArtista().equals(artistaNew.getIdArtista())){
                return "Ya existe un artista con ese id";
            }
            if(artistaTemp.getNombreArtista().equals(artistaNew.getNombreArtista())){
                return "Ya existe un artista con ese nombre";
            }
        }
        String res = conDB.create_artista(artistaNew);
        return res;
    }

    public String edit(Artistat artista){
        String res = conDB.edit_artista(artista);
        return res;
    }

    public String destroy(Integer id){
        String res = conDB.delete_artista(id);
        return res;
    }
    
    public ArrayList listar_artistas(){
        ArrayList<Artistat> artistas = conDB.listar_artistas();
        return artistas;
    }

    public String[] listar_nombres(){
        ArrayList<Artistat> artistas = listar_artistas();
        String[] names = new String[artistas.size()+1];
        names[0]="N/A";
        for (int i = 0; i < artistas.size(); i++) {
            names[i+1] = artistas.get(i).getNombreArtista();
        }
        return names;
    }
    
    public Artistat buscarById(Integer id){
        return conDB.buscarById(id);
    }
    
    public ArrayList buscarByIds(ArrayList<Integer> ids){
        ArrayList<String> names = new ArrayList();
        for (Integer id : ids) {
            if(id!=0){
                names.add(buscarById(id).getNombreArtista());
            }
        }
        return names;
    }
    
    public ArrayList buscarByNames(ArrayList names){
        ArrayList<Artistat> artistas = listar_artistas();
        ArrayList<Artistat> artistasRes = new ArrayList();
        for (Artistat artista : artistas) {
            for(int i = 0; i<names.size();i++){
                if(artista.getNombreArtista().equals(names.get(i))){
                    artistasRes.add(artista);
                }
            }
        }
        return artistasRes;
    }
    
    
    /*public List<Artistat> findArtistatEntities() {
        
    }

    public Artistat findArtistat(Integer id) {
        
    }*/  
}
