/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidad;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JoanGomez
 */
@Entity
@Table(name = "lugart")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lugart.findAll", query = "SELECT l FROM Lugart l")
    , @NamedQuery(name = "Lugart.findByIdLugar", query = "SELECT l FROM Lugart l WHERE l.idLugar = :idLugar")
    , @NamedQuery(name = "Lugart.findByNombreLugar", query = "SELECT l FROM Lugart l WHERE l.nombreLugar = :nombreLugar")
    , @NamedQuery(name = "Lugart.findByCubiertaLugar", query = "SELECT l FROM Lugart l WHERE l.cubiertaLugar = :cubiertaLugar")
    , @NamedQuery(name = "Lugart.findByCapacidadLugar", query = "SELECT l FROM Lugart l WHERE l.capacidadLugar = :capacidadLugar")
    , @NamedQuery(name = "Lugart.findBySeccionLugar", query = "SELECT l FROM Lugart l WHERE l.seccionLugar = :seccionLugar")})
public class Lugart implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_lugar")
    private Integer idLugar;
    @Basic(optional = false)
    @Column(name = "nombre_lugar")
    private String nombreLugar;
    @Basic(optional = false)
    @Column(name = "cubierta_lugar")
    private String cubiertaLugar;
    @Basic(optional = false)
    @Column(name = "capacidad_lugar")
    private int capacidadLugar;
    @Column(name = "seccion_lugar")
    private String seccionLugar;

    public Lugart() {
    }

    public Lugart(Integer idLugar) {
        this.idLugar = idLugar;
    }

    public Lugart(Integer idLugar, String nombreLugar, String cubiertaLugar, int capacidadLugar) {
        this.idLugar = idLugar;
        this.nombreLugar = nombreLugar;
        this.cubiertaLugar = cubiertaLugar;
        this.capacidadLugar = capacidadLugar;
    }

    public Integer getIdLugar() {
        return idLugar;
    }

    public void setIdLugar(Integer idLugar) {
        this.idLugar = idLugar;
    }

    public String getNombreLugar() {
        return nombreLugar;
    }

    public void setNombreLugar(String nombreLugar) {
        this.nombreLugar = nombreLugar;
    }

    public String getCubiertaLugar() {
        return cubiertaLugar;
    }

    public void setCubiertaLugar(String cubiertaLugar) {
        this.cubiertaLugar = cubiertaLugar;
    }

    public int getCapacidadLugar() {
        return capacidadLugar;
    }

    public void setCapacidadLugar(int capacidadLugar) {
        this.capacidadLugar = capacidadLugar;
    }

    public String getSeccionLugar() {
        return seccionLugar;
    }

    public void setSeccionLugar(String seccionLugar) {
        this.seccionLugar = seccionLugar;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLugar != null ? idLugar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lugart)) {
            return false;
        }
        Lugart other = (Lugart) object;
        if ((this.idLugar == null && other.idLugar != null) || (this.idLugar != null && !this.idLugar.equals(other.idLugar))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidad.Lugart[ idLugar=" + idLugar + " ]";
    }
    
}
