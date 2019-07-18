/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import DAO.EventoDao;
import java.io.Serializable;
import javax.persistence.Query;
import Entidad.Admint;
import java.util.ArrayList;
import Entidad.Artistat;
import Entidad.Eventot;
import Entidad.Lugart;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 *
 * @author Armageddon132
 */
public class EventotController implements Serializable {

    private static ArrayList<Integer> ids;
    private final EventoDao conDB = new EventoDao();

    public EventotController() {
        this.emf = Persistence.createEntityManagerFactory("ActividadesCulturalesPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static ArrayList<Integer> getIds() {
        return ids;
    }

    public static void setIds(ArrayList<Integer> ids) {
        EventotController.ids = ids;
    }

    public int buscarCapacidad (int id_evento){
        return conDB.capacidad(id_evento);
    }
    
    public ArrayList buscar_ids_artistas(int id_evento) {
        ArrayList<Integer> ids = new ArrayList();
        ids = conDB.buscar_ids_artistas(id_evento);
        return ids;
    }

    public Eventot findEventot(Integer id) {
        System.out.println(id);
        EntityManager em = getEntityManager();
        try {
            return em.find(Eventot.class, id);
        } finally {
            em.close();
        }
    }
    
    public ArrayList listarEventos(){
        ArrayList<Eventot> eventosT = conDB.listar();
        ArrayList<Eventot> eventos = new ArrayList();
        for (Eventot evento : eventosT) {
            if(evento.getEstadoEvento()==0){
                eventos.add(evento);
            }
        }
        return eventos;
    }

    public String[] listarNombres() {
        ArrayList<Eventot> list = listarEventos();
        List<String> names = new ArrayList<>();
        ids = new ArrayList<>();
        for (Eventot list1 : list) {
            names.add(list1.getNombreEvento());
            ids.add(list1.getIdEvento());
        }
        return names.toArray(new String[0]);
    }

    public int[] returnIdsEvento(int x) {
        int[] idsEvento = new int[3];
        Query qr;
        EntityManager em = getEntityManager();
        int idT = ids.get(x);
        return idsEvento;
    }
    
    public int returnCapacidadEvento(int idEvento) {
        return conDB.capacidad_evento(idEvento);
    }

    public int returnReservasEvento(int idEvento) {
        return conDB.reservas_hechas(idEvento);
    }
    public String crear(Eventot event, Lugart place, ArrayList<Artistat> artist, int capacity, Admint adminS) {
        int[] artistas = new int[4];
        int count = 0;
        for (Artistat artis : artist) {
            artistas[count]= artis.getIdArtista();
            count++;
        }
        if(count<3){
            for(int i = count; i<3;i++){
                artistas[i]=0;
            }
        }
        String res = conDB.crear_evento(event, artistas, place.getIdLugar(), capacity, adminS.getIdAdmin());
        return res;
    }

    public String editEvent(Eventot event, Lugart lugar, List<Artistat> artists, int capacity) {
        int[] ids_artist = new int[4];
        
        int count = 0;
        for (int i = 0; i < artists.size(); i++) {
            ids_artist[i]=artists.get(i).getIdArtista();
            count++;
        }
        if(count<3){
        int aux = 3-count;
            for (int i = 0; i < aux; i++) {
                ids_artist[count+i+1]=0;
            }
        }
        String res = conDB.editar_evento(event, ids_artist, lugar, capacity);
        return res;
    }
    
    public String close_event(int id_evento){
        String res = conDB.close_evento(id_evento);
        return res;
    }
    
    public int buscarIdLugar(int idEvento){
        return conDB.buscarIdLugar(idEvento);
    }
    
    public ArrayList<Eventot> retornar_evento_fecha(Date fechaInicio, Date fechaFinal){
        return conDB.retornar_evento_fecha(fechaInicio, fechaFinal);
    }
}
