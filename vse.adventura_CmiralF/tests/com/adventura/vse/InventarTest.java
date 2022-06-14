package com.adventura.vse;

import com.adventura.vse.zakladyHry.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


/*******************************************************************************
 * Testovací třída InventarTest slouží ke komplexnímu otestování
 * třídy Inventář
 *
 * @author    Filip Cmíral
 */

public class InventarTest {
    private Inventar inventar;
    private Hra hra;
    private Vec vec1;
    private Vec vec2;
    private Kredity kredity;


    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody.
     * Příprava objektů, se kterými bude třída pracovat
     *
     */
    @Before
    public void setUp() {
        inventar = new Inventar(2);
        hra = new Hra();
        vec1 = new Vec("vec1", true);
        vec2 = new Vec("vec2", true);
        kredity = new Kredity("kredity", true, 500);

        Prostor prostor = hra.getHerniPlan().getAktualniProstor();
        prostor.addVec(vec1);
        prostor.addVec(vec2);
        prostor.addVec(kredity);
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }

    /**
     * Testuje úspěšné přidání do inventáře
     *
     */
    @Test
    public void testPridaDoInventare() {


        assertEquals(vec1, inventar.vlozVec(vec1));
        assertEquals(vec2, inventar.vlozVec(vec2));

    }

    /**
     * Testuje, jestli se do inventáře nepřidá předmět, i když je plno
     */
    @Test
    public void testInventarPlny() {


        assertEquals(vec1, inventar.vlozVec(vec1));
        inventar.setVelikostInventare(1);
        assertEquals(null, inventar.vlozVec(vec2));

    }


    /**
     * Testuje úspěšné odebrání z inventáře
     */
    @Test
    public void testOdebereZInventare() {

        inventar.addVec(vec1);
        inventar.smazVec("vec1");
        assertEquals(0,inventar.getPocetVeci());
    }


    /**
     * Testuje práci s kredity
     */
    @Test
    public void testKredity() {
        int pocetKreditu = 500;

        inventar.setPocet_kreditu(pocetKreditu);
        assertEquals(pocetKreditu,inventar.getPocet_kreditu());
        hra.zpracujPrikaz("seber kredity");
        assertEquals(0, inventar.getPocetVeci());
    }


}
