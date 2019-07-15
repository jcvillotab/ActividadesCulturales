/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author manuel
 */
public class ClientTest {
    private static ClientetController clientC = new ClientetController();
    private String CLIENTE_NO_EXISTE = "Error, el cliente no existe";
    private String TODO_CORRECTO_RESERVA = "Reserva correcta";
    
    public ClientTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void TestClienteNoExiste(){
        int idCliente = 12345678;
        int idEvento = 1;
        assertEquals(clientC.reservar(idCliente,idEvento), CLIENTE_NO_EXISTE);
    }
    /*
    @Test
    public void TestTodoCorrectoReserva(){
        int idCliente = 48330359;
        int idEvento = 1;
        assertEquals(clientC.reservar(idCliente,idEvento), TODO_CORRECTO_RESERVA);
    }*/
}
