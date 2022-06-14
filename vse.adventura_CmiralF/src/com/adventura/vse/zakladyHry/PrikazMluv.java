package com.adventura.vse.zakladyHry;


/**
 * Třída PrikazMluv implementuje příkaz mluv
 * Třída vychází z InterfacePrikaz
 *
 * Hráč může mluvit s různými postavami pomocí příkazu
 * Postavy moho hráči nabídnout radu nebo mu říci, co chtějí měnit
 *
 * @author Filip Cmíral
 */
public class PrikazMluv implements InterfacePrikaz {
    private static final String NAZEV = "mluv";
    private HerniPlan herniPlan;

    /**
     * Konstruktor třídy
     *
     * @param herniPlan herní plán, podle kterého se třída řídí
     */
    public PrikazMluv(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }


    /**
     * Rozhovor hráče s osobou
     *
     *
     * @param parametry osoba, se kterou má hráč mluvit
     * @return rozhovor s osobou
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Luke: Nevím s kým mám mluvit.";
        } else if (parametry.length != 1) {
            return "Luke: Jsem sice silný Jedi, ale s tolika lidmi najednou mluvit nedokážu.";
        }
        String jmenoOsoby = parametry[0];

        Prostor aktualniProstor = herniPlan.getAktualniProstor();
        Osoba osoba = aktualniProstor.getOsoba(jmenoOsoby);



        if (osoba == null) {
            return "Luke: Nikoho takového tu nevidím.";
        }
        if ("urednik".equals(osoba.getJmeno())) {
            aktualniProstor.odemknoutVedlejsi("centrum");
            aktualniProstor.odemknoutVedlejsi("trh");
        }


        return osoba.getRozhovor();
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
