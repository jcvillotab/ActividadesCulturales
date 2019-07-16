/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Entidad.Lugart;
import java.util.ArrayList;
import java.util.List;
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
public class LugartControllerTest {
    
    private static LugartController lugarC = new LugartController();
    private String TODO_CORRECTO_REGISTRO = "Registro exitoso";
    private String LONG_NOMBRE_INCORRECTA = "Longitud nombre incorrecta";
    private String CAPACIDAD_INCORRECTA = "Capacidad incorrecta";
    private String TODO_CORRECTO_MODIFICAR = "Modificacion exitosa";
    
    public LugartControllerTest() {
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
    public void TestTodoCorrecto() {
        Lugart newPlace = new Lugart();
        
        newPlace.setNombreLugar("Piscina");
        newPlace.setCubiertaLugar("Principal");
        newPlace.setSeccionLugar("Dos");
        newPlace.setCapacidadLugar(50);
        
        assertEquals(lugarC.create(newPlace), TODO_CORRECTO_REGISTRO);   
    }

    @Test
    public void TestLongitudNombre() {
        Lugart newPlace = new Lugart();
        
        newPlace.setNombreLugar("Bar");
        newPlace.setCubiertaLugar("Principal");
        newPlace.setSeccionLugar("Dos");
        newPlace.setCapacidadLugar(21);
        
        assertEquals(lugarC.create(newPlace), LONG_NOMBRE_INCORRECTA);   
        
        newPlace.setNombreLugar("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        newPlace.setCubiertaLugar("Principal");
        newPlace.setSeccionLugar("Dos");
        newPlace.setCapacidadLugar(21);
        
        assertEquals(lugarC.create(newPlace), LONG_NOMBRE_INCORRECTA);  
    }
    
     @Test
    public void TestCapacidad() {
        Lugart newPlace = new Lugart();
        
        newPlace.setNombreLugar("Piscina");
        newPlace.setCubiertaLugar("Principal");
        newPlace.setSeccionLugar("Dos");
        newPlace.setCapacidadLugar(7000);
        
        assertEquals(lugarC.create(newPlace), CAPACIDAD_INCORRECTA);     
    }
   
    @Test
    public void testEdit() {
        Lugart newPlace = new Lugart();
        
        newPlace.setNombreLugar("Piscina");
        newPlace.setCubiertaLugar("Principal");
        newPlace.setSeccionLugar("Dos");
        newPlace.setCapacidadLugar(500);
        
        assertEquals(lugarC.edit(newPlace,1), TODO_CORRECTO_MODIFICAR);
    }
    
}
