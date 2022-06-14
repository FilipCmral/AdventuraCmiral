package com.adventura.vse;


import com.adventura.vse.zakladyHry.Hra;
import com.adventura.vse.zakladyHry.Prostor;
import com.adventura.vse.zakladyHry.Vec;
import com.adventura.vse.zakladyHry.Kredity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


/*******************************************************************************
 * Testovací třída VecTest slouží k otestování
 * třídy Vec
 *
 * @author    Filip Cmíral
 */
public class VecTest {
    private Hra hra1;
    private Prostor prostor;
    private Vec vec1;
    private Vec vec2;
    private Kredity kredity1;




    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody.
     * Příprava objektů, se kterými bude třída pracovat
     *
     */
    @Before
    public void setUp() {
        hra1 = new Hra();
        prostor = hra1.getHerniPlan().getAktualniProstor();
        vec1 = new Vec("vec1", true);
        vec2 = new Vec("vec2", false);
        kredity1 = new Kredity("kredity1", true, 1000);
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }


    /**
     * Metoda testuje jestli lze normálně sbírat věci
     * a jestli hráč sebere nesebratelnou věc
     *
     */
    @Test
    public void testSebratelna() {
        prostor.addVec(vec1);
        prostor.addVec(vec2);

        hra1.zpracujPrikaz("seber vec1");
        assertEquals(null, prostor.getVec("vec1"));
        hra1.zpracujPrikaz("seber vec2");
        assertEquals(vec2, prostor.getVec("vec2"));

    }

    /**
     * Testuje, jestli jsou správně sebrány kredity
     */
    @Test
    public void testSeberKredity() {
        prostor.addVec(kredity1);
        hra1.zpracujPrikaz("seber kredity1");
        assertEquals(null, prostor.getVec("kredity1"));
    }




}
