package com.adventura.vse.zakladyHry;


/**
 *  Třída PrikazZahod implementuje pro hru příkaz zahod.
 *  Tato třída vychází z InterfacePříkaz
 *
 *@author      Filip Cmíral
 *
 */
public class PrikazZahod implements InterfacePrikaz {
    private static final String NAZEV = "zahod";
    private final HerniPlan herniPlan;

    /**
     * Konstruktor třídy
     *
     * @param herniPlan herní plán, podle kterého se příkaz řídí
     */
    public PrikazZahod(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;

    }


    /**
     * Zahodí/nezahodí vybraný předmět podle toho,
     * jestli ho je možné zahodit a vrátí do herního prostoru
     *
     *
     * @param parametry předmět, který má být zahozen
     * @return zpráva o ne/zahození předmětu
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Nevím, co mám zahodit.";
        }
        else if (parametry.length != 1) {
            return "Snažíš se zahodit příliž mnoho věcí";
        }
        String nazevVeci = parametry[0];


        Prostor aktualniProstor = herniPlan.getAktualniProstor();
        Inventar inventar = herniPlan.getInventar();
        Vec zahozena = inventar.getVec(nazevVeci);

        if (zahozena == null) {
            return "Luke: Toto u sebe nemám.";
        }
        else {// pokud je věc smazána z batohu, přesune se do aktualního prostoru
            zahozena = inventar.smazVec(nazevVeci);
            aktualniProstor.addVec(zahozena);
            inventar.smazVec(nazevVeci);
            return "Luke: Zahodil jsem " + nazevVeci + ".";
        }




    }


    /**
     *
     * @return název předmětu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
