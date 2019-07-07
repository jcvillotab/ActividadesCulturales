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
import javax.persistence.Table;

/**
 *
 * @author JoanGomez
 */
@Entity
@Table(name = "admint")
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

    public Admint() {
    }

    public Admint(Integer idAdmin, String nombreAdmin, String contraseniaAdmin) {
        this.idAdmin = idAdmin;
        this.nombreAdmin = nombreAdmin;
        this.contraseniaAdmin = contraseniaAdmin;
    }
    
    public Admint(String nombreAdmin, String contraseniaAdmin) {
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

    
}
