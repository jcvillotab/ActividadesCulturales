/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import DAO.EventoDao;
import java.io.Serializable;
import javax.persistence.Query;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidad.Admint;

import java.util.ArrayList;

import Entidad.Artistat;
import Entidad.Eventot;
import Entidad.Lugart;
import com.mysql.cj.jdbc.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
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
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/actividadesculturalesdb?zeroDateTimeBehavior=convertToNull","root","btZ7op0gGo");
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
    
    public ArrayList buscar_ids_artistas(int id_evento) {
        ArrayList<Integer> ids = new ArrayList();
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/actividadesculturalesdb?zeroDateTimeBehavior=convertToNull", "root", "btZ7op0gGo");
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
        } catch (Exception e) {
            System.out.println("ERROR EN EL PPROCEDIMIENTO ALMACENADO: " + e);
        }
        return ids;
    }

    
    

    public Eventot findEventot(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Eventot.class, id);
        } finally {
            em.close();
        }
    }

    public int getEventotCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Eventot> rt = cq.from(Eventot.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public static String[] listToArrayEvent(List<Eventot> list) {
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

    

    public String crear(Eventot event, Lugart place, ArrayList<Artistat> artist, int capacity, Admint adminS) {
        int[] artistas = new int[4];
        int count = 0;
        for (Artistat artis : artist) {
            artistas[count]= artis.getIdArtista();
            count++;
        }
        if(count<3){
            for(int i = count; i<3;count++){
                artistas[i]=0;
            }
        }
        String res = conDB.crear_evento(event, artistas, place.getIdLugar(), capacity, adminS.getIdAdmin());
        return res;
    }

    public void editEvent(Eventot event, Lugart lugar, List<Artistat> artists, int capacity) {
        int[] ids_artist = new int[4];
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
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
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/actividadesculturalesdb?zeroDateTimeBehavior=convertToNull","root","btZ7op0gGo");
            String sentencia = "{call EDITAR_EVENTO("+event.getIdEvento()+",'"+event.getNombreEvento()+"','"+format1.format(event.getFechaEvento())+"',"+ids_artist[0]+","+ids_artist[1]+","+ids_artist[2]+","+lugar.getIdLugar()+","+capacity+")}";
            CallableStatement ms = (CallableStatement) con.prepareCall(sentencia);
            ms.executeQuery();
        } catch (Exception e) {
            System.out.println("Error con procedimiento almacenado:"+e);
        }
    }

}
