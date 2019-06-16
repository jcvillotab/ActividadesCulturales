/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidad;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author JoanGomez
 */
@Entity
@Table(name = "admint")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Admint.findAll", query = "SELECT a FROM Admint a")
    , @NamedQuery(name = "Admint.findByIdAdmin", query = "SELECT a FROM Admint a WHERE a.idAdmin = :idAdmin")
    , @NamedQuery(name = "Admint.findByNombreAdmin", query = "SELECT a FROM Admint a WHERE a.nombreAdmin = :nombreAdmin")
    , @NamedQuery(name = "Admint.findByContraseniaAdmin", query = "SELECT a FROM Admint a WHERE a.contraseniaAdmin = :contraseniaAdmin")})
public class Admint implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_admin")
    private Integer idAdmin;
    @Basic(optional = false)
    @Column(name = "nombre_admin")
    private String nombreAdmin;
    @Basic(optional = false)
    @Column(name = "contrasenia_admin")
    private String contraseniaAdmin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkIdAdmin")
    private Collection<Eventot> eventotCollection;

    public Admint() {
    }

    public Admint(Integer idAdmin) {
        this.idAdmin = idAdmin;
    }

    public Admint(Integer idAdmin, String nombreAdmin, String contraseniaAdmin) {
        this.idAdmin = idAdmin;
        this.nombreAdmin = nombreAdmin;
        this.contraseniaAdmin = contraseniaAdmin;
    }

    public Integer getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(Integer idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getNombreAdmin() {
        return nombreAdmin;
    }

    public void setNombreAdmin(String nombreAdmin) {
        this.nombreAdmin = nombreAdmin;
    }

    public String getContraseniaAdmin() {
        return contraseniaAdmin;
    }

    public void setContraseniaAdmin(String contraseniaAdmin) {
        this.contraseniaAdmin = contraseniaAdmin;
    }
    
    

    @XmlTransient
    public Collection<Eventot> getEventotCollection() {
        return eventotCollection;
    }

    public void setEventotCollection(Collection<Eventot> eventotCollection) {
        this.eventotCollection = eventotCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAdmin != null ? idAdmin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Admint)) {
            return false;
        }
        Admint other = (Admint) object;
        if ((this.idAdmin == null && other.idAdmin != null) || (this.idAdmin != null && !this.idAdmin.equals(other.idAdmin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidad.Admint[ idAdmin=" + idAdmin + " ]";
    }
    
}
