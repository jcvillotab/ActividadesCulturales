/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Entidad.Artistat;
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
public class ArtistTest {
    
    private static ArtistatController artistC = new ArtistatController();
    private String TODO_CORRECTO_REGISTRO = "Registro exitoso";
    private String MISMO_ID = "Ya existe un artista con ese id";
    private String MISMO_NOMBRE = "Ya existe un artista con ese nombre";
    private String TODO_CORRECTO_MODIFICAR = "Modificacion exitosa";
    public ArtistTest() {
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
    public void TestTodoCorrectoRegistro(){
        Artistat newArtist = new Artistat();
        
        newArtist.setIdArtista(20);
        newArtist.setNombreArtista("Juanes");
        newArtist.setOcupacionArtista("Cantante");
        
        assertEquals(artistC.create(newArtist), TODO_CORRECTO_REGISTRO);
    }
    
    @Test
    public void TestMismoId(){
        Artistat newArtist = new Artistat();
        
        newArtist.setIdArtista(21);
        newArtist.setNombreArtista("Adam Sandler");
        newArtist.setOcupacionArtista("Actor");
        
        assertEquals(artistC.create(newArtist), MISMO_ID);
    }
    
    @Test
    public void TestMismoNombre(){
        Artistat newArtist = new Artistat();
        
        newArtist.setIdArtista(40);
        newArtist.setNombreArtista("Shakira");
        newArtist.setOcupacionArtista("Cantante");
        
        assertEquals(artistC.create(newArtist), MISMO_NOMBRE);
    }
    
    @Test
    public void TestTodoCorrectoModificar(){
        Artistat newArtist = new Artistat();
        
        newArtist.setIdArtista(21);
        newArtist.setNombreArtista("Carlos Vives");
        newArtist.setOcupacionArtista("Cantante");
        
        assertEquals(artistC.edit(newArtist), TODO_CORRECTO_MODIFICAR);
    }
}
