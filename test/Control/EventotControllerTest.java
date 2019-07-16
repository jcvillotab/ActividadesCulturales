/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Entidad.Admint;
import Entidad.Artistat;
import Entidad.Eventot;
import Entidad.Lugart;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Fernando
 */
public class EventotControllerTest {
    
    private static EventotController eventoC = new EventotController();
    private static LugartController lugarC = new LugartController();
    private static ArtistatController artC = new ArtistatController();
    private String TODO_CORRECTO_REGISTRO = "Registro exitoso";
    private String EVENTO_EXISTENTE = "El evento ya existe";
    private String TODO_CORRECTO_MODIFICAR = "Modificacion exitosa";
    
    public EventotControllerTest() {
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
    public void testCrear() {
        
        Eventot event = new Eventot();
        event.setNombreEvento("Concierto");
        //event.setFechaEvento();
        Lugart place = new Lugart();
        place.setNombreLugar("Piscina");
        Artistat artist = new Artistat();
        artist.setIdArtista(10);
        artist.setNombreArtista("Shakira");
        artist.setOcupacionArtista("Cantante");
        ArrayList<Artistat> artists = null;
        artists.add(artist);
        int capacity = 50;
        Admint adminS = null;
       assertEquals(eventoC.crear(event, place, artists, capacity, adminS), TODO_CORRECTO_REGISTRO); 
        
    }

    @Test
    public void testEditEvent() {
        Eventot event = new Eventot();
        event.setNombreEvento("Concierto");
        //event.setFechaEvento();
        Lugart place = new Lugart();
        place.setNombreLugar("Piscina");
        Artistat artist = new Artistat();
        artist.setIdArtista(10);
        artist.setNombreArtista("Juanes");
        artist.setOcupacionArtista("Cantante");
        ArrayList<Artistat> artists = null;
        artists.add(artist);
        int capacity = 50;
        Admint adminS = null;
       assertEquals(eventoC.editEvent(event, place, artists, capacity),TODO_CORRECTO_MODIFICAR);
    }
    
    @Test
    public void testMismoEvento() {
        
        Eventot event = new Eventot();
        event.setNombreEvento("Concierto");
        //event.setFechaEvento();
        Lugart place = new Lugart();
        place.setNombreLugar("Piscina");
        Artistat artist = new Artistat();
        artist.setIdArtista(10);
        artist.setNombreArtista("Shakira");
        artist.setOcupacionArtista("Cantante");
        ArrayList<Artistat> artists = null;
        artists.add(artist);
        int capacity = 50;
        Admint adminS = null;
       assertEquals(eventoC.crear(event, place, artists, capacity, adminS), EVENTO_EXISTENTE); 
        
    }
}
