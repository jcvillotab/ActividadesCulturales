/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import DAO.AdminDao;
import Entidad.Admint;
import java.io.Serializable;

/**
 *
 * @author Armageddon132
 */
public class AdmintController implements Serializable {

    private Admint adminS;
    private final AdminDao conDB = new AdminDao();

    public String create(Admint admin, String vPass) {
        //AQUI SE HACEN VALIDACIONES
        if (admin.getNombreAdmin().length() < 6 || admin.getNombreAdmin().length() > 16) {
            return "Error en el tamaño del nombre de usuario";
        }
        if (!String.valueOf(admin.getContraseniaAdmin()).equals(String.valueOf(vPass))) {
            return "Las contraseñas no coinciden";
        }
        if (admin.getContraseniaAdmin().length() < 8 || admin.getContraseniaAdmin().length() > 16) {
            return "Error en el tamaño de la contraseña";
        }
        //AQUI SE LLAMA AL METODO QUE CREA AL USUARIO
        String res = conDB.create_admin(admin);
        return res;

    }

    public String LoginAdmint(Admint admin) {
        //AQUI SE HACEN VALIDACIONES
        if (admin.getNombreAdmin().length() < 6 || admin.getNombreAdmin().length() > 16) {
            return "Error en el tamaño del nombre de usuario";
        }
        if (admin.getContraseniaAdmin().length() < 8 || admin.getContraseniaAdmin().length() > 16) {
            return "Error en el tamaño de la contraseña";
        }
        //AQUI SE LLAMA EL METODO QUE HACE LOGIN
        String res = conDB.login_admin(admin);
        setAdminS(conDB.getAdminS());
        return res;
    }

    public Admint getAdminS() {
        return adminS;
    }

    public void setAdminS(Admint adminS) {
        this.adminS = adminS;
    }
    
}
