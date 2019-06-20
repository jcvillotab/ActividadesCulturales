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
    
    
    public EventotJpaController() {
        this.emf = Persistence.createEntityManagerFactory("ActividadesCulturalesPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Eventot eventot) {
        if (eventot.getClientetCollection() == null) {
            eventot.setClientetCollection(new ArrayList<Clientet>());
        }
        if (eventot.getArtistatCollection() == null) {
            eventot.setArtistatCollection(new ArrayList<Artistat>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Admint fkIdAdmin = eventot.getFkIdAdmin();
            if (fkIdAdmin != null) {
                fkIdAdmin = em.getReference(fkIdAdmin.getClass(), fkIdAdmin.getIdAdmin());
                eventot.setFkIdAdmin(fkIdAdmin);
            }
            Collection<Clientet> attachedClientetCollection = new ArrayList<Clientet>();
            for (Clientet clientetCollectionClientetToAttach : eventot.getClientetCollection()) {
                clientetCollectionClientetToAttach = em.getReference(clientetCollectionClientetToAttach.getClass(), clientetCollectionClientetToAttach.getIdCliente());
                attachedClientetCollection.add(clientetCollectionClientetToAttach);
            }
            eventot.setClientetCollection(attachedClientetCollection);
            Collection<Artistat> attachedArtistatCollection = new ArrayList<Artistat>();
            for (Artistat artistatCollectionArtistatToAttach : eventot.getArtistatCollection()) {
                artistatCollectionArtistatToAttach = em.getReference(artistatCollectionArtistatToAttach.getClass(), artistatCollectionArtistatToAttach.getIdArtista());
                attachedArtistatCollection.add(artistatCollectionArtistatToAttach);
            }
            eventot.setArtistatCollection(attachedArtistatCollection);
            em.persist(eventot);
            if (fkIdAdmin != null) {
                fkIdAdmin.getEventotCollection().add(eventot);
                fkIdAdmin = em.merge(fkIdAdmin);
            }
            for (Clientet clientetCollectionClientet : eventot.getClientetCollection()) {
                clientetCollectionClientet.getEventotCollection().add(eventot);
                clientetCollectionClientet = em.merge(clientetCollectionClientet);
            }
            for (Artistat artistatCollectionArtistat : eventot.getArtistatCollection()) {
                artistatCollectionArtistat.getEventotCollection().add(eventot);
                artistatCollectionArtistat = em.merge(artistatCollectionArtistat);
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
            Collection<Clientet> clientetCollectionOld = persistentEventot.getClientetCollection();
            Collection<Clientet> clientetCollectionNew = eventot.getClientetCollection();
            Collection<Artistat> artistatCollectionOld = persistentEventot.getArtistatCollection();
            Collection<Artistat> artistatCollectionNew = eventot.getArtistatCollection();
            if (fkIdAdminNew != null) {
                fkIdAdminNew = em.getReference(fkIdAdminNew.getClass(), fkIdAdminNew.getIdAdmin());
                eventot.setFkIdAdmin(fkIdAdminNew);
            }
            Collection<Clientet> attachedClientetCollectionNew = new ArrayList<Clientet>();
            for (Clientet clientetCollectionNewClientetToAttach : clientetCollectionNew) {
                clientetCollectionNewClientetToAttach = em.getReference(clientetCollectionNewClientetToAttach.getClass(), clientetCollectionNewClientetToAttach.getIdCliente());
                attachedClientetCollectionNew.add(clientetCollectionNewClientetToAttach);
            }
            clientetCollectionNew = attachedClientetCollectionNew;
            eventot.setClientetCollection(clientetCollectionNew);
            Collection<Artistat> attachedArtistatCollectionNew = new ArrayList<Artistat>();
            for (Artistat artistatCollectionNewArtistatToAttach : artistatCollectionNew) {
                artistatCollectionNewArtistatToAttach = em.getReference(artistatCollectionNewArtistatToAttach.getClass(), artistatCollectionNewArtistatToAttach.getIdArtista());
                attachedArtistatCollectionNew.add(artistatCollectionNewArtistatToAttach);
            }
            artistatCollectionNew = attachedArtistatCollectionNew;
            eventot.setArtistatCollection(artistatCollectionNew);
            eventot = em.merge(eventot);
            if (fkIdAdminOld != null && !fkIdAdminOld.equals(fkIdAdminNew)) {
                fkIdAdminOld.getEventotCollection().remove(eventot);
                fkIdAdminOld = em.merge(fkIdAdminOld);
            }
            if (fkIdAdminNew != null && !fkIdAdminNew.equals(fkIdAdminOld)) {
                fkIdAdminNew.getEventotCollection().add(eventot);
                fkIdAdminNew = em.merge(fkIdAdminNew);
            }
            for (Clientet clientetCollectionOldClientet : clientetCollectionOld) {
                if (!clientetCollectionNew.contains(clientetCollectionOldClientet)) {
                    clientetCollectionOldClientet.getEventotCollection().remove(eventot);
                    clientetCollectionOldClientet = em.merge(clientetCollectionOldClientet);
                }
            }
            for (Clientet clientetCollectionNewClientet : clientetCollectionNew) {
                if (!clientetCollectionOld.contains(clientetCollectionNewClientet)) {
                    clientetCollectionNewClientet.getEventotCollection().add(eventot);
                    clientetCollectionNewClientet = em.merge(clientetCollectionNewClientet);
                }
            }
            for (Artistat artistatCollectionOldArtistat : artistatCollectionOld) {
                if (!artistatCollectionNew.contains(artistatCollectionOldArtistat)) {
                    artistatCollectionOldArtistat.getEventotCollection().remove(eventot);
                    artistatCollectionOldArtistat = em.merge(artistatCollectionOldArtistat);
                }
            }
            for (Artistat artistatCollectionNewArtistat : artistatCollectionNew) {
                if (!artistatCollectionOld.contains(artistatCollectionNewArtistat)) {
                    artistatCollectionNewArtistat.getEventotCollection().add(eventot);
                    artistatCollectionNewArtistat = em.merge(artistatCollectionNewArtistat);
                }
            }
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
            Collection<Clientet> clientetCollection = eventot.getClientetCollection();
            for (Clientet clientetCollectionClientet : clientetCollection) {
                clientetCollectionClientet.getEventotCollection().remove(eventot);
                clientetCollectionClientet = em.merge(clientetCollectionClientet);
            }
            Collection<Artistat> artistatCollection = eventot.getArtistatCollection();
            for (Artistat artistatCollectionArtistat : artistatCollection) {
                artistatCollectionArtistat.getEventotCollection().remove(eventot);
                artistatCollectionArtistat = em.merge(artistatCollectionArtistat);
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
        for (Eventot list1 : list) {
            names.add(list1.getNombreEvento());
        }
        return names.toArray(new String[0]);
    }

    public String registerEvent(Eventot event, Lugart place, ArrayList<Artistat> artist, int capacity, Admint adminS) {
        EntityManager em = getEntityManager();
        Query aux;
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy"); 
        
        em.getTransaction().begin();
        aux = em.createNativeQuery("INSERT INTO eventoT (nombre_evento, fecha_evento) VALUES ('" + event.getNombreEvento()+ "' ," + format1.format(event.getFechaEvento()) + ","+adminS.getIdAdmin()+" )");
        aux.executeUpdate();
        
        for (Artistat artista : artist) {

            em.getTransaction().begin();
            TypedQuery<Artistat> qryArtist = em.createNamedQuery("Artistat.findByIdArtista", Artistat.class);
            qryArtist.setParameter("idArtista", artista.getIdArtista());
            try {
                if (!qryArtist.getResultList().isEmpty()) {
                    aux = em.createNativeQuery("INSERT INTO eventoxartistaT (id_fk_id_evento, id_fk_id_artista) VALUES (" + event.getIdEvento() + " ," + artista.getIdArtista() + " )");
                    aux.executeUpdate();
                }

            } catch (Exception e) {
                return "Error:" + e;
            } finally {
                em.close();
            }
        }

        em.getTransaction().begin();
        TypedQuery<Lugart> qryPlace = em.createNamedQuery("Lugart.findByIdLugar", Lugart.class);
        qryPlace.setParameter("idLugar", String.valueOf(place.getIdLugar()));

        try {
            if (!qryPlace.getResultList().isEmpty()) {
                aux = em.createNativeQuery("INSERT INTO eventoxlugarT (id_fk_id_evento, id_fk_id_lugar, capacidad_ocupada_exl) VALUES (" + event.getIdEvento() + " ," + place.getIdLugar() + " ," + capacity + " )");
                aux.executeUpdate();
            }

        } catch (Exception e) {
            return "Error:" + e;
        } finally {
            em.close();

        }
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
                }
            }
        } catch (Exception e) {
            
        }finally{
            em.close();
        }

    }

}


