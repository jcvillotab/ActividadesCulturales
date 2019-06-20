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
import java.text.SimpleDateFormat;
import java.util.List;
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

    public int returnLastInsertedId(){
        List<Eventot> list = findEventotEntities();
        
        if(list.isEmpty()){
            return 0;
        }else{
            return list.get(list.size()-1).getIdEvento();
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
    
    
    public void editEvent(Eventot event, String name, String date, Lugart place, int capacity) {
        EntityManager em = getEntityManager();
        Query aux;
        Query update;
        Eventot original;

        em.getTransaction().begin();
        TypedQuery<Eventot> qryEvent = em.createNamedQuery("Eventot.findByNombreFechaEvento", Eventot.class);
        qryEvent.setParameter(1, name);
        qryEvent.setParameter(2, date);

        try {
            if (!qryEvent.getResultList().isEmpty()) {
                original = qryEvent.getSingleResult();
                aux = em.createNativeQuery("SELECT * FROM eventoxlugarT WHERE id_fk_id_evento =" + original.getIdEvento() + " AND id_fk_id_lugar =" + place.getIdLugar() +" ");
                if(!aux.getResultList().isEmpty()){
                    update = em.createNativeQuery("UPDATE eventoT SET nombre_evento=" + event.getNombreEvento()+ " fecha_evento=" + event.getFechaEvento().toString() + " WHERE id_evento=" + String.valueOf(original.getIdEvento()) + ")");
                    update.executeUpdate();
                    update = em.createNativeQuery("UPDATE eventoxlugarT SET capacidad_ocupada_exl=" + String.valueOf(capacity) + "WHERE id_fk_id_evento=" + original.getIdEvento() + "AND id_fk_id_lugar=" + place.getIdLugar()+ " )");
                    update.executeUpdate();
                    em.getTransaction().commit();
                }
            }
        } catch (Exception e) {
            
        }finally{
            em.close();
        }

    }

}


