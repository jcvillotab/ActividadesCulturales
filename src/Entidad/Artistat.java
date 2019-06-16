/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidad;

import java.io.Serializable;
import java.util.Collection;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author JoanGomez
 */
@Entity
@Table(name = "artistat")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Artistat.findAll", query = "SELECT a FROM Artistat a")
    , @NamedQuery(name = "Artistat.findByIdArtista", query = "SELECT a FROM Artistat a WHERE a.idArtista = :idArtista")
    , @NamedQuery(name = "Artistat.findByNombreArtista", query = "SELECT a FROM Artistat a WHERE a.nombreArtista = :nombreArtista")
    , @NamedQuery(name = "Artistat.findByOcupacionArtista", query = "SELECT a FROM Artistat a WHERE a.ocupacionArtista = :ocupacionArtista")})
public class Artistat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_artista")
    private Integer idArtista;
    @Basic(optional = false)
    @Column(name = "nombre_artista")
    private String nombreArtista;
    @Basic(optional = false)
    @Column(name = "ocupacion_artista")
    private String ocupacionArtista;
    @JoinTable(name = "eventoxartistat", joinColumns = {
        @JoinColumn(name = "id_fk_id_artista", referencedColumnName = "id_artista")}, inverseJoinColumns = {
        @JoinColumn(name = "id_fk_id_evento", referencedColumnName = "id_evento")})
    @ManyToMany
    private Collection<Eventot> eventotCollection;

    public Artistat() {
    }

    public Artistat(Integer idArtista) {
        this.idArtista = idArtista;
    }

    public Artistat(Integer idArtista, String nombreArtista, String ocupacionArtista) {
        this.idArtista = idArtista;
        this.nombreArtista = nombreArtista;
        this.ocupacionArtista = ocupacionArtista;
    }

    public Integer getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(Integer idArtista) {
        this.idArtista = idArtista;
    }

    public String getNombreArtista() {
        return nombreArtista;
    }

    public void setNombreArtista(String nombreArtista) {
        this.nombreArtista = nombreArtista;
    }

    public String getOcupacionArtista() {
        return ocupacionArtista;
    }

    public void setOcupacionArtista(String ocupacionArtista) {
        this.ocupacionArtista = ocupacionArtista;
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
        hash += (idArtista != null ? idArtista.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Artistat)) {
            return false;
        }
        Artistat other = (Artistat) object;
        if ((this.idArtista == null && other.idArtista != null) || (this.idArtista != null && !this.idArtista.equals(other.idArtista))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidad.Artistat[ idArtista=" + idArtista + " ]";
    }
    
}
