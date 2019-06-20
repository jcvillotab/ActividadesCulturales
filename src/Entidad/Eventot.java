/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidad;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author JoanGomez
 */
@Entity
@Table(name = "eventot")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Eventot.findAll", query = "SELECT e FROM Eventot e")
    , @NamedQuery(name = "Eventot.findByIdEvento", query = "SELECT e FROM Eventot e WHERE e.idEvento = :idEvento")
    , @NamedQuery(name = "Eventot.findByNombreEvento", query = "SELECT e FROM Eventot e WHERE e.nombreEvento = :nombreEvento")
    , @NamedQuery(name = "Eventot.findByNombreFecha Evento", query = "SELECT e FROM Eventot e WHERE e.nombreEvento = :nombreEvento AND e.fechaEvento = :fechaEvento")
    , @NamedQuery(name = "Eventot.findByFechaEvento", query = "SELECT e FROM Eventot e WHERE e.fechaEvento = :fechaEvento")})
public class Eventot implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_evento")
    private Integer idEvento;
    @Basic(optional = false)
    @Column(name = "nombre_evento")
    private String nombreEvento;
    @Basic(optional = false)
    @Column(name = "fecha_evento")
    @Temporal(TemporalType.DATE)
    private Date fechaEvento;
    @JoinColumn(name = "fk_id_admin", referencedColumnName = "id_admin")
    @ManyToOne(optional = false)
    private Admint fkIdAdmin;

    public Eventot() {
    }

    public Eventot(Integer idEvento) {
        this.idEvento = idEvento;
    }

    public Eventot(Integer idEvento, String nombreEvento, Date fechaEvento) {
        this.idEvento = idEvento;
        this.nombreEvento = nombreEvento;
        this.fechaEvento = fechaEvento;
    }

    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public Date getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(Date fechaEvento) {
        this.fechaEvento = fechaEvento;
    }


    public Admint getFkIdAdmin() {
        return fkIdAdmin;
    }

    public void setFkIdAdmin(Admint fkIdAdmin) {
        this.fkIdAdmin = fkIdAdmin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEvento != null ? idEvento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Eventot)) {
            return false;
        }
        Eventot other = (Eventot) object;
        if ((this.idEvento == null && other.idEvento != null) || (this.idEvento != null && !this.idEvento.equals(other.idEvento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidad.Eventot[ idEvento=" + idEvento + " ]";
    }
    
}
