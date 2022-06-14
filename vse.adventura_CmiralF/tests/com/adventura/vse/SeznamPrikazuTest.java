package com.adventura.vse;



import com.adventura.vse.zakladyHry.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída SeznamPrikazuTest slouží k otestování třídy
 * SeznamPrikazu
 *
 * @author    Luboš Pavlíček
 * @version   pro školní rok 2016/2017
 */
public class SeznamPrikazuTest {
    private Hra hra;
    private PrikazKonec prKonec;
    private PrikazJdi prJdi;
    private PrikazNapoveda prNapoveda;

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody.
     * Příprava objektů, se kterými bude třída pracovat
     */
    @Before
    public void setUp() {
        hra = new Hra();
        prKonec = new PrikazKonec(hra);
        prJdi = new PrikazJdi(hra.getHerniPlan(), hra);
        prNapoveda = new PrikazNapoveda(hra.getPlatnePrikazy());
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }


    /**
     * Testuje, jestli jsou správně vloženy příkazy
     */
    @Test
    public void testVlozeniVybrani() {
        SeznamPrikazu seznPrikazu = new SeznamPrikazu();
        seznPrikazu.vlozPrikaz(prKonec);
        seznPrikazu.vlozPrikaz(prJdi);
        seznPrikazu.vlozPrikaz(prNapoveda);
        assertEquals(prKonec, seznPrikazu.vratPrikaz("konec"));
        assertEquals(prJdi, seznPrikazu.vratPrikaz("jdi"));
        assertEquals(prNapoveda, seznPrikazu.vratPrikaz("napoveda"));
    }

    /**
     * Testuje, jestli je správně kontrolována správnost příkazů
     */
    @Test
    public void testJePlatnyPrikaz() {
        SeznamPrikazu seznPrikazu = new SeznamPrikazu();
        seznPrikazu.vlozPrikaz(prKonec);
        seznPrikazu.vlozPrikaz(prJdi);
        seznPrikazu.vlozPrikaz(prNapoveda);
        assertTrue(seznPrikazu.jePlatnyPrikaz("konec"));
        assertTrue(seznPrikazu.jePlatnyPrikaz("jdi"));
        assertTrue(seznPrikazu.jePlatnyPrikaz("napoveda"));
        assertFalse(seznPrikazu.jePlatnyPrikaz("Konec"));
        assertFalse(seznPrikazu.jePlatnyPrikaz("znic"));
    }


    /**
     * Testuje, jestli jsou správně zapsány názvy příkazů
     */
    @Test
    public void testNazvyPrikazu() {
        SeznamPrikazu seznPrikazu = new SeznamPrikazu();
        seznPrikazu.vlozPrikaz(prKonec);
        seznPrikazu.vlozPrikaz(prJdi);
        seznPrikazu.vlozPrikaz(prNapoveda);
        String nazvy = seznPrikazu.vratNazvyPrikazu();
        assertTrue(nazvy.contains("konec"));
        assertTrue(nazvy.contains("jdi"));
        assertTrue(nazvy.contains("napoveda"));
        assertFalse(nazvy.contains("Konec"));
    }
}
