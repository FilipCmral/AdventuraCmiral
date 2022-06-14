package com.adventura.vse.zakladyHry;


/**
 * Třída PrikazInventar implementuje pro hru příkaz inventář
 * Třída vychází z InterfacePrikaz
 *
 * @author Filip Cmíral
 */
public class PrikazInventar implements InterfacePrikaz {
    private static final String NAZEV = "inventar";
    private HerniPlan herniPlan;


    /**
     * kontsruktor třídy
     *
     * @param herniPlan herní plán, ze kterého příkaz vychází
     */
    public PrikazInventar(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }


    /**
     * Vypíše předměty v inventáři a počet kreditů, které uživatel má
     *
     * @param parametry tento příkaz nemá parametry
     * @return výčet předmětů v inventáři a počet kreditů
     */
    @Override
    public String provedPrikaz(String... parametry) {
        String inventarText = "";
        if (herniPlan.getInventar().getVeci().isEmpty()) {
            // pokud je batoh prázdný
            inventarText = "\nLuke: Provází mne Síla, nic jiného u sebe nemám.";
        }
        else {
            // pokud je v batohu jedna a více věcí.
            inventarText = "\nLuke: U sebe mám" + herniPlan.getInventar().getVeci() + ".";
        }
        if (herniPlan.getInventar().getPocet_kreditu() > 0) {
            inventarText += "\nLuke: Jo a mám u sebe " + herniPlan.getInventar().getPocet_kreditu() + " kreditů.";
        }

        return inventarText;
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
