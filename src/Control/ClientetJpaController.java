/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Control.exceptions.NonexistentEntityException;
import Control.exceptions.PreexistingEntityException;
import Entidad.Clientet;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidad.Eventot;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Armageddon132
 */
public class ClientetJpaController implements Serializable {

    public ClientetJpaController() {
        this.emf = Persistence.createEntityManagerFactory("ActividadesCulturalesPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Clientet clientet) throws PreexistingEntityException, Exception {
        if (clientet.getEventotCollection() == null) {
            clientet.setEventotCollection(new ArrayList<Eventot>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(clientet);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findClientet(clientet.getIdCliente()) != null) {
                throw new PreexistingEntityException("Clientet " + clientet + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Clientet clientet) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Clientet persistentClientet = em.find(Clientet.class, clientet.getIdCliente());
            Collection<Eventot> eventotCollectionOld = persistentClientet.getEventotCollection();
            Collection<Eventot> eventotCollectionNew = clientet.getEventotCollection();
            Collection<Eventot> attachedEventotCollectionNew = new ArrayList<Eventot>();
            for (Eventot eventotCollectionNewEventotToAttach : eventotCollectionNew) {
                eventotCollectionNewEventotToAttach = em.getReference(eventotCollectionNewEventotToAttach.getClass(), eventotCollectionNewEventotToAttach.getIdEvento());
                attachedEventotCollectionNew.add(eventotCollectionNewEventotToAttach);
            }
            eventotCollectionNew = attachedEventotCollectionNew;
            clientet.setEventotCollection(eventotCollectionNew);
            clientet = em.merge(clientet);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = clientet.getIdCliente();
                if (findClientet(id) == null) {
                    throw new NonexistentEntityException("The clientet with id " + id + " no longer exists.");
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
            Clientet clientet;
            try {
                clientet = em.getReference(Clientet.class, id);
                clientet.getIdCliente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The clientet with id " + id + " no longer exists.", enfe);
            }
            em.remove(clientet);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Clientet> findClientetEntities() {
        return findClientetEntities(true, -1, -1);
    }

    public List<Clientet> findClientetEntities(int maxResults, int firstResult) {
        return findClientetEntities(false, maxResults, firstResult);
    }

    private List<Clientet> findClientetEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Clientet.class));
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

    public Clientet findClientet(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Clientet.class, id);
        } finally {
            em.close();
        }
    }

    public int getClientetCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Clientet> rt = cq.from(Clientet.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
