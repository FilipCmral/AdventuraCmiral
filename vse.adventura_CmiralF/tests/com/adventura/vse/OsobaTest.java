package com.adventura.vse;



import com.adventura.vse.zakladyHry.Hra;
import com.adventura.vse.zakladyHry.Osoba;
import com.adventura.vse.zakladyHry.Vec;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída OsobaTest slouží k otestování
 * třídy Osoba
 *
 * @author    Jarmila Pavlíčková, Filip Cmíral
 * @version  pro školní rok 2016/2017
 */
public class OsobaTest {
     private Hra hra1;
     private Osoba osoba1;



    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody.
     * Příprava objektů, se kterými bude třída pracovat
     */
    @Before
    public void setUp() {

        hra1 = new Hra();
        osoba1 = new Osoba("osoba1", "ahoj", "nechci");
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }

    /**
     * Testuje, jestli u osob správně fungují rozhovory
     */
    @Test
    public void testRozhovor() {
        assertEquals("ahoj", osoba1.getRozhovor());
        assertEquals("nechci", osoba1.getRozhovorNechceVec());
    }


    /**
     * Testuje, jestli osoba správně přijme výměnu
     */
    @Test
    public void testVymena() {
        Vec vecChce = new Vec("vecChce", true);
        Vec vecVlastni = new Vec("vecVlastni", true);
        osoba1.addVymena(vecVlastni, vecChce, "jsi super", "diky", "uz nechci", false);


        osoba1.vymena(vecChce);
        assertTrue(osoba1.isProbehlaVymena());
    }


    /**
     * Testuje, aby si osoba nevzala špatný předmět
     */
    @Test
    public void testNechce() {
        Vec vecChce = new Vec("vecChce", true);
        Vec vecNechce = new Vec("vecNechce", true);
        osoba1.addVymena(vecNechce, vecChce, "jsi super", "diky", "uz nechci", false);

        osoba1.vymena(vecNechce);
        assertFalse(osoba1.isProbehlaVymena());
    }

    /**
     * Testuje, jestli jsou vraceny správné rozhovory
     * po špatných i správných výměnách
     */
    @Test
    public void testRecPoVymene() {
        Vec vecChce = new Vec("vecChce", true);
        Vec vecNechce = new Vec("vecNechce", true);
        osoba1.addVymena(vecNechce, vecChce, "jsi super", "diky", "uz nechci", false);

        osoba1.vymena(vecNechce);
        assertEquals("ahoj",osoba1.getRozhovor());
        osoba1.vymena(vecChce);
        assertEquals("jsi super", osoba1.getRozhovor());

    }


    /**
     * Testuje, jestli osoba vrací správný rozhovor, když vyměňuje správnou a špatnou věc
     */
    @Test
    public void testRecVymena() {
        Vec vecChce = new Vec("vecChce", true);
        Vec vecNechce = new Vec("vecNechce", true);
        osoba1.addVymena(vecNechce, vecChce, "jsi super", "diky", "uz nechci", false);

        assertEquals("nechci" ,osoba1.getRecVymena(vecNechce));
        assertEquals("diky", osoba1.getRecVymena(vecChce));
    }
}
