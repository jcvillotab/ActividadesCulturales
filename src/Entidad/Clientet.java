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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "clientet")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clientet.findAll", query = "SELECT c FROM Clientet c")
    , @NamedQuery(name = "Clientet.findByIdCliente", query = "SELECT c FROM Clientet c WHERE c.idCliente = :idCliente")
    , @NamedQuery(name = "Clientet.findByNombreCliente", query = "SELECT c FROM Clientet c WHERE c.nombreCliente = :nombreCliente")
    , @NamedQuery(name = "Clientet.findByCodigoCliente", query = "SELECT c FROM Clientet c WHERE c.codigoCliente = :codigoCliente")})
public class Clientet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_cliente")
    private Integer idCliente;
    @Basic(optional = false)
    @Column(name = "nombre_cliente")
    private String nombreCliente;
    @Basic(optional = false)
    @Column(name = "codigo_cliente")
    @Temporal(TemporalType.DATE)
    private Date codigoCliente;
    @JoinTable(name = "eventoxclientet", joinColumns = {
        @JoinColumn(name = "id_fk_id_cliente", referencedColumnName = "id_cliente")}, inverseJoinColumns = {
        @JoinColumn(name = "id_fk_id_evento", referencedColumnName = "id_evento")})
    @ManyToMany
    private Collection<Eventot> eventotCollection;

    public Clientet() {
    }

    public Clientet(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Clientet(Integer idCliente, String nombreCliente, Date codigoCliente) {
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.codigoCliente = codigoCliente;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Date getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(Date codigoCliente) {
        this.codigoCliente = codigoCliente;
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
        hash += (idCliente != null ? idCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clientet)) {
            return false;
        }
        Clientet other = (Clientet) object;
        if ((this.idCliente == null && other.idCliente != null) || (this.idCliente != null && !this.idCliente.equals(other.idCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidad.Clientet[ idCliente=" + idCliente + " ]";
    }
    
}
