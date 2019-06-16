/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Control.exceptions.NonexistentEntityException;
import Entidad.Lugart;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Armageddon132
 */
public class LugartJpaController implements Serializable {
    
    private EntityManagerFactory emf = null;

    public LugartJpaController() {
        this.emf = Persistence.createEntityManagerFactory("ActividadesCulturalesPU");
    }
    

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Lugart lugart) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(lugart);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Lugart lugart) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            lugart = em.merge(lugart);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = lugart.getIdLugar();
                if (findLugart(id) == null) {
                    throw new NonexistentEntityException("The lugart with id " + id + " no longer exists.");
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
            Lugart lugart;
            try {
                lugart = em.getReference(Lugart.class, id);
                lugart.getIdLugar();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The lugart with id " + id + " no longer exists.", enfe);
            }
            em.remove(lugart);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Lugart> findLugartEntities() {
        return findLugartEntities(true, -1, -1);
    }

    public List<Lugart> findLugartEntities(int maxResults, int firstResult) {
        return findLugartEntities(false, maxResults, firstResult);
    }

    private List<Lugart> findLugartEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Lugart.class));
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

    public Lugart findLugart(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Lugart.class, id);
        } finally {
            em.close();
        }
    }

    public int getLugartCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Lugart> rt = cq.from(Lugart.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
