package com.adventura.vse.zakladyHry;


/**
 *  Třída PrikazNapoveda implementuje pro hru příkaz napoveda.
 *  Tato třída vychází z InterfacePříkaz
 *
 *@author     Filip Cmíral
 *
 */
public class PrikazProzkozkoumej implements InterfacePrikaz{
    private static final String NAZEV = "prozkoumej";
    private HerniPlan herniPlan;

    /**
     * Konstruktor třídy
     *
     * @param herniPlan herní plán, ze kterého příkaz vychází
     */
    public PrikazProzkozkoumej(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }

    /**
     * Vypíše dlouhý popis prostoru, všechny východy, předměty a osoby v něm
     *
     * @param parametry příkaz prozkoumej nepotřebuje parametry
     * @return
     */
    @Override
    public String provedPrikaz(String... parametry) {
        Prostor prostor = herniPlan.getAktualniProstor();
        return prostor.dlouhyPopis();
    }


    /**
     *
     * @return název příkazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
