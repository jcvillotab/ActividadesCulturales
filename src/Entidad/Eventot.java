/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidad;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author JoanGomez
 */
@Entity
@Table(name = "eventot")
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
    @Basic(optional = false)
    @Column(name = "estado_evento")
    private int estadoEvento;
    @JoinColumn(name = "fk_id_admin", referencedColumnName = "id_admin")
    @ManyToOne(optional = false)
    private Admint fkIdAdmin;

    public Eventot() {
    }

    public Eventot(Integer idEvento) {
        this.idEvento = idEvento;
    }

    public Eventot(Integer idEvento, String nombreEvento, Date fechaEvento, int estado) {
        this.idEvento = idEvento;
        this.nombreEvento = nombreEvento;
        this.fechaEvento = fechaEvento;
        this.estadoEvento = estado;
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

    public int getEstadoEvento() {
        return estadoEvento;
    }

    public void setEstadoEvento(int estadoEvento) {
        this.estadoEvento = estadoEvento;
    }
}
