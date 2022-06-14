package com.adventura.vse.zakladyHry;



/**
 *  Třída PrikazSeber implementuje pro hru příkaz seber.
 *  Tato třída vychází z InterfacePříkaz
 *
 *@author     Jarmila Pavlickova, Luboš Pavlíček, Filip Cmíral
 *@version    pro školní rok 2016/2017
 *
 */
public class PrikazSeber implements InterfacePrikaz {
    private static final String NAZEV = "seber";
    private final HerniPlan herniPlan;

    /**
     * Konstruktor třídy
     *
     * @param herniPlan herní plán, ze kterého příkaz vychází
     */
    public PrikazSeber(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }


    /**
     * Sebere/neseber daný předmět
     * Podle toho jestli je to možné a vrátí příslušnou zprávu
     *
     * @param parametry předmět, který má být sebrán
     * @return zpráva o ne/sebrání
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Luke: Nevím, co mám sebrat.";
        } else if (parametry.length != 1) {
            return "Luke: Jsem silný Jedi, ale nedokážu sebrat tolik věcí najednou.";
        }

        String nazevVeci = parametry[0];


        Prostor aktualniProstor = herniPlan.getAktualniProstor();
        Vec vec = aktualniProstor.getVec(nazevVeci);
        Inventar inventar = herniPlan.getInventar();

        if (vec instanceof Kredity) {
            Kredity kredity = (Kredity) aktualniProstor.getVec(nazevVeci);
            inventar.setPocet_kreditu(inventar.getPocet_kreditu() + kredity.getHodnota());
            aktualniProstor.removeVec(nazevVeci);
            return "Luke: Dalších " + kredity.getHodnota() + " kreditů navíc.";

        }



        if (vec == null) {
            return "Luke: Takovou věc tu nikde nevidím.";
        } else if (!vec.isSebratelna()) {
            return "Luke: Toto nedokážu sebrat.";
        } else if (nazevVeci.contains("batoh")) {
            inventar.setVelikostInventare(5);
            return "Luke: Super, větší batoh, teď mám více místa.";
        } else if (!inventar.jeMisto()) {
            return "Luke: Toto se mi už nikam nevejde.";
        }

        aktualniProstor.removeVec(nazevVeci);
        inventar.vlozVec(vec);

        return "Luke: " + sebranyPredmetZprava();

    }


    /**
     * Náhodně pomocí Math.random() vybere číslo
     * a podle toho vypíše jednu ze 3 zpráv
     *
     * @return zpráva o sebrání předmětu
     */
    public String sebranyPredmetZprava() {
        int min = 1;
        int pocetOdpovedi = 3;
        int rozsah = pocetOdpovedi - min +1;
        int nahodna = (int)(Math.random() * rozsah) + min;

        switch (nahodna) {
            case 1:
                return "Toto se mi bude hodit.";
            case 2:
                return "Další bonus do mé zbírky.";
            case 3:
                return "Naštěstí tu pořád mám nějaké místo.";
            default: return "Sebráno.";
        }

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
