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
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author JoanGomez
 */
@Entity
@Table(name = "artistat")
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
}
