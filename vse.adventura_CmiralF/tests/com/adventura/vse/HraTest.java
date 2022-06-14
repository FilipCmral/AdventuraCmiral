package com.adventura.vse;


import com.adventura.vse.zakladyHry.Hra;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída HraTest slouží k otestování
 * třídy Hra
 *
 * @author    Jarmila Pavlíčková, Filip Cmíral
 * @version  pro školní rok 2016/2017
 */
public class HraTest {
    private Hra hra1;



    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody.
     * Příprava objektů, se kterými bude třída pracovat
     */
    @Before
    public void setUp() {
        hra1 = new Hra();
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }



    /***************************************************************************
     * Testuje průběh hry, po zavolání každěho příkazu testuje, zda hra končí
     * a v jaké aktuální místnosti se hráč nachází.
     *
     *
     */
    @Test
    public void testPrubehHry() {
        assertEquals("pole", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi brana");
        assertEquals(false, hra1.konecHry());
        assertEquals("brana", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi trh");
        assertEquals(false, hra1.konecHry());
        assertEquals("brana", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("konec");
        assertEquals(true, hra1.konecHry());
    }


    /**
     * Testuje, jestli se hra správně ukončí
     */
    @Test
    public void testKonecHry() {
        assertEquals("pole", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi brana");
        assertEquals(false, hra1.konecHry());

        hra1.setKonecHry(false);
        assertEquals(false, hra1.konecHry());
    }
}
