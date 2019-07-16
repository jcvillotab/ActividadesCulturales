/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Entidad.Admint;
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
public class AdminTest {
    
    private static AdmintController adminC = new AdmintController();
    private String LONGITUD_NOMBRE_ERROR = "Error en el tamaño del nombre de usuario";
    private String LONGITUD_CONTRASENIA_ERROR = "Error en el tamaño de la contraseña";
    private String CONTRASENIAS_NO_COINCIDEN = "Las contraseñas no coinciden";
    private String TODO_CORRECTO_REGISTRO = "Registro exitoso";
    private String USUARIO_NO_EXISTE = "El nombre de usuario no existe";
    private String CONTRASENIA_INCORRECTA = "Contraseña incorrecta";
    private String TODO_CORRECTO_LOGIN = "Datos correctos";
    
    public AdminTest() {
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
    public void TestLongitudNombre(){
        String vPass;
        Admint newAdmin = new Admint();
        
        newAdmin.setNombreAdmin("a");
        newAdmin.setContraseniaAdmin("12345678");
        vPass = "12345678";
        assertEquals(adminC.create(newAdmin, vPass), LONGITUD_NOMBRE_ERROR);
        
        
        newAdmin.setNombreAdmin("abcdefghijklmnlop");
        newAdmin.setContraseniaAdmin("12345678");
        vPass = "12345678";
        assertEquals(adminC.create(newAdmin, vPass), LONGITUD_NOMBRE_ERROR);
    }
    
    
    @Test
    public void TestLongitudContrasenia(){
        String vPass;
        Admint newAdmin = new Admint();
        
        newAdmin.setNombreAdmin("dacperezce");
        newAdmin.setContraseniaAdmin("1234");
        vPass = "1234";
        assertEquals(adminC.create(newAdmin, vPass), LONGITUD_CONTRASENIA_ERROR);
        
        
        newAdmin.setNombreAdmin("dacperezce");
        newAdmin.setContraseniaAdmin("12345678912345678");
        vPass = "12345678912345678";
        assertEquals(adminC.create(newAdmin, vPass), LONGITUD_CONTRASENIA_ERROR);
    }
    
    
    @Test
    public void TestContraniasNoCoinciden(){
        String vPass;
        Admint newAdmin = new Admint();
        
        newAdmin.setNombreAdmin("dacperezce");
        newAdmin.setContraseniaAdmin("12345678");
        vPass = "12345687";
        assertEquals(adminC.create(newAdmin, vPass), CONTRASENIAS_NO_COINCIDEN);
    }
    
    @Test
    public void TestTodoCorrectoRegistro(){
        String vPass;
        Admint newAdmin = new Admint();
        
        newAdmin.setNombreAdmin("dacperezce");
        newAdmin.setContraseniaAdmin("12345678");
        vPass = "12345678";
        assertEquals(adminC.create(newAdmin, vPass), TODO_CORRECTO_REGISTRO);
    }
    
    @Test
    public void TestUsuarioNoExiste(){
        Admint newAdmin = new Admint();
        String user = "aaaaaa";
        String pass = "12345678";
        newAdmin.setNombreAdmin(user);
        newAdmin.setContraseniaAdmin(pass);
        assertEquals(adminC.LoginAdmint(newAdmin),USUARIO_NO_EXISTE);
    }
    
    @Test
    public void TestContraseniaIncorrecta(){
        Admint newAdmin = new Admint();
        String user = "JoanGo2";
        String pass = "87654321";
        newAdmin.setNombreAdmin(user);
        newAdmin.setContraseniaAdmin(pass);
        assertEquals(adminC.LoginAdmint(newAdmin),CONTRASENIA_INCORRECTA);
    }
    
    @Test
    public void TestTodoCorrectoLogin(){
        Admint newAdmin = new Admint();
        String user = "JoanGo2";
        String pass = "12345678";
        newAdmin.setNombreAdmin(user);
        newAdmin.setContraseniaAdmin(pass);
        assertEquals(adminC.LoginAdmint(newAdmin),TODO_CORRECTO_LOGIN);
    }
}