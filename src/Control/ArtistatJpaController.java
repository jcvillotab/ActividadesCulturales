/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Control.exceptions.NonexistentEntityException;
import Control.exceptions.PreexistingEntityException;
import Entidad.Artistat;
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
public class ArtistatJpaController implements Serializable {

    public ArtistatJpaController() {
        this.emf = Persistence.createEntityManagerFactory("ActividadesCulturalesPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Artistat artistat) throws PreexistingEntityException, Exception {
        if (artistat.getEventotCollection() == null) {
            artistat.setEventotCollection(new ArrayList<Eventot>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findArtistat(artistat.getIdArtista()) != null) {
                throw new PreexistingEntityException("Artistat " + artistat + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Artistat artistat) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Artistat persistentArtistat = em.find(Artistat.class, artistat.getIdArtista());
            artistat = em.merge(artistat);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = artistat.getIdArtista();
                if (findArtistat(id) == null) {
                    throw new NonexistentEntityException("The artistat with id " + id + " no longer exists.");
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
            Artistat artistat;
            try {
                artistat = em.getReference(Artistat.class, id);
                artistat.getIdArtista();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The artistat with id " + id + " no longer exists.", enfe);
            }
            em.remove(artistat);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Artistat> findArtistatEntities() {
        return findArtistatEntities(true, -1, -1);
    }

    public List<Artistat> findArtistatEntities(int maxResults, int firstResult) {
        return findArtistatEntities(false, maxResults, firstResult);
    }

    private List<Artistat> findArtistatEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Artistat.class));
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

    public Artistat findArtistat(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Artistat.class, id);
        } finally {
            em.close();
        }
    }

    public int getArtistatCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Artistat> rt = cq.from(Artistat.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public static String[] listToArrayArtist(List<Artistat> list){
        List<String> names = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++) {
            names.add(list.get(i).getNombreArtista());
        }
        return names.toArray(new String[0]);
    }
    
}
