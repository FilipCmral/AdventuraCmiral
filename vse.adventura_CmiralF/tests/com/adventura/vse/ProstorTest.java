package com.adventura.vse;

import com.adventura.vse.zakladyHry.Hra;
import com.adventura.vse.zakladyHry.Prostor;
import com.adventura.vse.zakladyHry.Vec;
import com.adventura.vse.zakladyHry.Osoba;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída ProstorTest slouží k otestování
 * třídy Prostor
 *
 * @author    Jarmila Pavlíčková,Filip Cmíral
 * @version   pro skolní rok 2016/2017
 */
public class ProstorTest {
    private Hra hra1;
    private Prostor prostor1;
    private Prostor prostor2;

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody.
     * Příprava objektů, se kterými bude třída pracovat
     *
     */
    @Before
    public void setUp() {
        hra1 = new Hra();
         prostor1 = new Prostor("pole", "Pole vedle brány");
         prostor2 = new Prostor("brana", "Brána vedle pole");
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }



    /**
     * Testuje, zda jsou správně nastaveny průchody mezi prostory hry. Prostory
     * neodpovídají vlastní hře
     */
    @Test
    public  void testLzeProjit() {

        prostor1.setVychod(prostor2.getNazev(), prostor2);
        prostor2.setVychod(prostor1.getNazev(), prostor1);
        assertEquals(prostor2, prostor1.vratSousedniProstor("brana"));
        assertEquals(null, prostor2.vratSousedniProstor("brana"));
    }

    /**
     * Testuje, jestli se místnost opravdu zamkne
     */
    @Test
    public void testZamknute() {
        prostor1.setVychod(prostor2.getNazev(), prostor2);
        prostor2.setVychod(prostor1.getNazev(), prostor1);
        prostor2.zamknout(true, "nelze");

        assertEquals("pole", hra1.getHerniPlan().getAktualniProstor().getNazev());
       assertTrue(prostor2.isNepristupna());
    }

    /**
     * Testuje, jestli se správně přidají do prostoru věci
     */
    @Test
    public void testPridaneVeci() {
        Vec vec = new Vec("vec", true);
        prostor1.addVec(vec);
        assertEquals(vec, prostor1.getVec("vec"));

       prostor1.removeVec("vec");
       assertEquals(null, prostor1.getVec("vec"));
    }

    /**
     * Testuje, jestli se správně přidají do prostoru osoby
     *
     */
    @Test
    public void testPridaneOsoby() {
        Osoba osoba = new Osoba("osoba", "ahoj", "nechci");
        prostor1.addOsoba(osoba);
        assertEquals(osoba, prostor1.getOsoba("osoba"));

        prostor1.removeOsoba("osoba");
        assertEquals(null, prostor1.getOsoba("osoba"));
    }

}
