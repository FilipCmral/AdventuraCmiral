package com.adventura.vse.zakladyHry;


/**
 *  Třída PrikazKonec implementuje pro hru příkaz konec.
 *  Tato třída vychází z InterfacePříkaz.
 *
 *@author     Jarmila Pavlickova, Filip Cmíral
 *@version    pro školní rok 2016/2017
 *
 */
public class PrikazKonec implements InterfacePrikaz {
    private static final String NAZEV = "konec";

    private Hra hra;

    /**
     *  Konstruktor třídy
     *
     *  @param hra odkaz na hru, která má být příkazem konec ukončena
     */
    public PrikazKonec(Hra hra) {
        this.hra = hra;
    }




    /**
     * Hra by měla být ukončena pouze zadáním příkazu konec
     *
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length > 0) {
            return "Pokud chceš hru ukončit, zadej jednoduše 'konec'.";
        }
        else {
            hra.setKonecHry(true);
            return "\n\n\tHra byla ukončena příkazem konec.";
        }
    }


    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
