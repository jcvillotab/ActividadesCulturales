/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import DAO.ClienteDao;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Armageddon132
 */
public class ClientetController implements Serializable {
    
    private final ClienteDao conDB = new ClienteDao();
    
    
    
    public String cliente_existe(int idCliente){
        return conDB.client_exist(idCliente);
    }
    
    public String reservar(int codigoCliente, int idEvento){
        if(cliente_existe(codigoCliente).equals("No existe")){
            return "Error, el cliente no existe";
        }
        int idCliente = returnIdCliente(codigoCliente);
        
        
        return conDB.reservar(idCliente, idEvento);
    }
    
    public String eliminarReserva(int codigoCliente, int idEvento){
        if(cliente_existe(codigoCliente).equals("No existe")){
            return "Error, el cliente no existe";
        }
        int idCliente = returnIdCliente(codigoCliente);
        
        
        return conDB.eliminar_reserva(idCliente, idEvento);
    }
    
    public ArrayList<Integer> listar_id_evento_reservas(int codigoCliente){
        int idCliente = returnIdCliente(codigoCliente);
        return conDB.listar_id_evento_reservas(idCliente);
    }
    
    public int returnIdCliente(int codCliente){
        return conDB.returnClientId(codCliente);
    }
}
