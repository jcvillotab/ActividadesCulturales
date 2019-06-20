/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Control.exceptions.IllegalOrphanException;
import Control.exceptions.NonexistentEntityException;
import Entidad.Admint;
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
import javax.persistence.TypedQuery;

/**
 *
 * @author Armageddon132
 */
public class AdmintJpaController implements Serializable {
    private Admint adminS;

    public AdmintJpaController() {
        
        this.emf = Persistence.createEntityManagerFactory("ActividadesCulturalesPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public String create(Admint admint, String vPass) {

        if(admint.getNombreAdmin().length()<6 || admint.getNombreAdmin().length()>16){
            return "Error en el tamaño del nombre de usuario";
        }
        
        if(!String.valueOf(admint.getContraseniaAdmin()).equals(String.valueOf(vPass))){
            return "Las contraseñas no coinciden";
        }
         
        if(admint.getContraseniaAdmin().length()<8 || admint.getContraseniaAdmin().length()>16){
            return "Error en el tamaño de la contraseña";
        }
        
        if (admint.getEventotCollection() == null) {
            admint.setEventotCollection(new ArrayList<Eventot>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Eventot> attachedEventotCollection = new ArrayList<Eventot>();
            for (Eventot eventotCollectionEventotToAttach : admint.getEventotCollection()) {
                eventotCollectionEventotToAttach = em.getReference(eventotCollectionEventotToAttach.getClass(), eventotCollectionEventotToAttach.getIdEvento());
                attachedEventotCollection.add(eventotCollectionEventotToAttach);
            }
            admint.setEventotCollection(attachedEventotCollection);
            
            em.persist(admint);
            for (Eventot eventotCollectionEventot : admint.getEventotCollection()) {
                Admint oldFkIdAdminOfEventotCollectionEventot = eventotCollectionEventot.getFkIdAdmin();
                eventotCollectionEventot.setFkIdAdmin(admint);
                eventotCollectionEventot = em.merge(eventotCollectionEventot);
                if (oldFkIdAdminOfEventotCollectionEventot != null) {
                    oldFkIdAdminOfEventotCollectionEventot.getEventotCollection().remove(eventotCollectionEventot);
                    oldFkIdAdminOfEventotCollectionEventot = em.merge(oldFkIdAdminOfEventotCollectionEventot);
                }
            }
            em.getTransaction().commit();
        }catch(Exception e){
            return "Error al registrar :"+e;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return "registro exitoso";
    }

    public void edit(Admint admint) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Admint persistentAdmint = em.find(Admint.class, admint.getIdAdmin());
            Collection<Eventot> eventotCollectionOld = persistentAdmint.getEventotCollection();
            Collection<Eventot> eventotCollectionNew = admint.getEventotCollection();
            List<String> illegalOrphanMessages = null;
            for (Eventot eventotCollectionOldEventot : eventotCollectionOld) {
                if (!eventotCollectionNew.contains(eventotCollectionOldEventot)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Eventot " + eventotCollectionOldEventot + " since its fkIdAdmin field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Eventot> attachedEventotCollectionNew = new ArrayList<Eventot>();
            for (Eventot eventotCollectionNewEventotToAttach : eventotCollectionNew) {
                eventotCollectionNewEventotToAttach = em.getReference(eventotCollectionNewEventotToAttach.getClass(), eventotCollectionNewEventotToAttach.getIdEvento());
                attachedEventotCollectionNew.add(eventotCollectionNewEventotToAttach);
            }
            eventotCollectionNew = attachedEventotCollectionNew;
            admint.setEventotCollection(eventotCollectionNew);
            admint = em.merge(admint);
            for (Eventot eventotCollectionNewEventot : eventotCollectionNew) {
                if (!eventotCollectionOld.contains(eventotCollectionNewEventot)) {
                    Admint oldFkIdAdminOfEventotCollectionNewEventot = eventotCollectionNewEventot.getFkIdAdmin();
                    eventotCollectionNewEventot.setFkIdAdmin(admint);
                    eventotCollectionNewEventot = em.merge(eventotCollectionNewEventot);
                    if (oldFkIdAdminOfEventotCollectionNewEventot != null && !oldFkIdAdminOfEventotCollectionNewEventot.equals(admint)) {
                        oldFkIdAdminOfEventotCollectionNewEventot.getEventotCollection().remove(eventotCollectionNewEventot);
                        oldFkIdAdminOfEventotCollectionNewEventot = em.merge(oldFkIdAdminOfEventotCollectionNewEventot);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = admint.getIdAdmin();
                if (findAdmint(id) == null) {
                    throw new NonexistentEntityException("The admint with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Admint admint;
            try {
                admint = em.getReference(Admint.class, id);
                admint.getIdAdmin();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The admint with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Eventot> eventotCollectionOrphanCheck = admint.getEventotCollection();
            for (Eventot eventotCollectionOrphanCheckEventot : eventotCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Admint (" + admint + ") cannot be destroyed since the Eventot " + eventotCollectionOrphanCheckEventot + " in its eventotCollection field has a non-nullable fkIdAdmin field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(admint);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Admint> findAdmintEntities() {
        return findAdmintEntities(true, -1, -1);
    }

    public List<Admint> findAdmintEntities(int maxResults, int firstResult) {
        return findAdmintEntities(false, maxResults, firstResult);
    }

    private List<Admint> findAdmintEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Admint.class));
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

    public Admint findAdmint(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Admint.class, id);
        } finally {
            em.close();
        }
    }
    
    public String LoginAdmint (String user, String pass){
        boolean ty = false;
        Admint usuario;
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        TypedQuery<Admint> query = em.createNamedQuery("Admint.findByNombreAdmin", Admint.class);
        query.setParameter("nombreAdmin", user);
        try {
            
            if(query.getResultList().isEmpty()){
                return "El nombre de usuario no existe";
            }else{
                usuario = query.getSingleResult();
                if(usuario.getContraseniaAdmin().equals(pass)){
                    ty = true;
                }else{
                    return "Contraseña incorrecta";
                }
            }
        }catch(Exception e){
            return "Error bd codigo : "+e;
        }finally {
            em.close();
        }
        if(ty == true){
                setAdminS(usuario);
                return "Datos correctos";
            }else{
                return "Error innesperado";
            }
    } 

    public Admint getAdminS() {
        return adminS;
    }

    public void setAdminS(Admint adminS) {
        this.adminS = adminS;
    }

    
    
    public int getAdmintCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Admint> rt = cq.from(Admint.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
