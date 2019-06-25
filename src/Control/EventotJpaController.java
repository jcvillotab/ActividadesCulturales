/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Control.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidad.Admint;
import Entidad.Clientet;
import java.util.ArrayList;
import java.util.Collection;
import Entidad.Artistat;
import Entidad.Eventot;
import Entidad.Lugart;
import com.mysql.cj.jdbc.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.SimpleFormatter;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Armageddon132
 */
public class EventotJpaController implements Serializable {

    private static ArrayList<Integer> ids;

    public EventotJpaController() {
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
        EventotJpaController.ids = ids;
    }

    public void create(Eventot eventot) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Admint fkIdAdmin = eventot.getFkIdAdmin();
            if (fkIdAdmin != null) {
                fkIdAdmin = em.getReference(fkIdAdmin.getClass(), fkIdAdmin.getIdAdmin());
                eventot.setFkIdAdmin(fkIdAdmin);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
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

    
    public void edit(Eventot eventot) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Eventot persistentEventot = em.find(Eventot.class, eventot.getIdEvento());
            Admint fkIdAdminOld = persistentEventot.getFkIdAdmin();
            Admint fkIdAdminNew = eventot.getFkIdAdmin();
            if (fkIdAdminNew != null) {
                fkIdAdminNew = em.getReference(fkIdAdminNew.getClass(), fkIdAdminNew.getIdAdmin());
                eventot.setFkIdAdmin(fkIdAdminNew);
            }
            eventot = em.merge(eventot);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = eventot.getIdEvento();
                if (findEventot(id) == null) {
                    throw new NonexistentEntityException("The eventot with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Eventot eventot;
            try {
                eventot = em.getReference(Eventot.class, id);
                eventot.getIdEvento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The eventot with id " + id + " no longer exists.", enfe);
            }
            Admint fkIdAdmin = eventot.getFkIdAdmin();
            if (fkIdAdmin != null) {
                fkIdAdmin.getEventotCollection().remove(eventot);
                fkIdAdmin = em.merge(fkIdAdmin);
            }
            em.remove(eventot);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Eventot> findEventotEntities() {
        return findEventotEntities(true, -1, -1);
    }

    public List<Eventot> findEventotEntities(int maxResults, int firstResult) {
        return findEventotEntities(false, maxResults, firstResult);
    }

    private List<Eventot> findEventotEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Eventot.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
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

    public int returnLastInsertedId() {
        List<Eventot> list = findEventotEntities();

        if (list.isEmpty()) {
            return 0;
        } else {
            return list.get(list.size() - 1).getIdEvento();
        }

    }

    public String registerEvent(Eventot event, Lugart place, ArrayList<Artistat> artist, int capacity, Admint adminS) {
        EntityManager em = getEntityManager();
        Query aux;
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

        em.getTransaction().begin();
        aux = em.createNativeQuery("INSERT INTO eventoT (nombre_evento, fecha_evento, fk_id_admin) VALUES ('" + event.getNombreEvento() + "' , '" + format1.format(event.getFechaEvento()) + "' ," + adminS.getIdAdmin() + " )");
        aux.executeUpdate();
        em.getTransaction().commit();
        em.close();

        for (Artistat artista : artist) {

            em = getEntityManager();
            em.getTransaction().begin();

            TypedQuery<Artistat> qryArtist = em.createNamedQuery("Artistat.findByIdArtista", Artistat.class);
            qryArtist.setParameter("idArtista", artista.getIdArtista());

            try {
                if (!qryArtist.getResultList().isEmpty()) {

                    aux = em.createNativeQuery("INSERT INTO eventoxartistaT (id_fk_id_evento, id_fk_id_artista) VALUES (" + returnLastInsertedId() + " ," + artista.getIdArtista() + " )");
                    aux.executeUpdate();
                    em.getTransaction().commit();

                }
                em.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        em = getEntityManager();
        em.getTransaction().begin();

        TypedQuery<Lugart> qryPlace = em.createNamedQuery("Lugart.findByIdLugar", Lugart.class);
        qryPlace.setParameter("idLugar", place.getIdLugar());

        try {
            if (!qryPlace.getResultList().isEmpty()) {

                aux = em.createNativeQuery("INSERT INTO eventoxlugarT (id_fk_id_evento, id_fk_id_lugar, capacidad_ocupada_exl) VALUES (" + returnLastInsertedId() + " ," + place.getIdLugar() + " ," + capacity + " )");
                aux.executeUpdate();
                em.getTransaction().commit();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        em.close();
        return "Evento Registrado Correctamente";
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
