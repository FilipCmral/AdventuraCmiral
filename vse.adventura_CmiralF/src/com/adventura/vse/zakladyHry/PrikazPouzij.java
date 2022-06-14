package com.adventura.vse.zakladyHry;


/**
 *  Třída PrikazPouzij implementuje pro hru příkaz použij.
 *  Tato třída vychází z InterfacePříkaz
 *
 *@author     Filip Cmíral
 *
 */
public class PrikazPouzij implements InterfacePrikaz {
    private static final String NAZEV = "pouzij";
    private final HerniPlan herniPlan;

    /**
     * Konstruktor třídy
     *
     * @param herniPlan herní plán, ze kterého příkaz vychází
     */
    public PrikazPouzij(HerniPlan herniPlan) {this.herniPlan = herniPlan;}


    /**
     * Použije nebo nepoužije předmět, vzhledem k tomu, jestli je
     * přemět v daném prostoru použitelný
     * a vrátí příslušnou zprávu
     *
     *
     * @param parametry předmět, který má být použit
     * @return zpráva po ne/použití
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Luke: Nevím, co mám použít.";
        }
        else if (parametry.length != 1) {
            return "Luke: Jsem sice silný Jedi, ale nedokážu použít tolik věcí najednou.";
        }

        String nazevVeci = parametry[0];
        Prostor aktualniProstor = herniPlan.getAktualniProstor();



        Inventar inventar = herniPlan.getInventar();
        Vec pouzita = inventar.getVec(nazevVeci);

        if (pouzita instanceof Kredity) {
            return "Luke: Přece nebudu takhle plýtvat penězi.";
        }
        if (pouzita == null) {
            return "Luke: Toto u sebe nemám.";
        }

        switch (nazevVeci) {
            case "svetelny_mec":
                if (aktualniProstor.getNazev().equals("dlouha_ulice")) {
                    aktualniProstor.odemknoutVedlejsi("imperialni_ctvrt");
                    aktualniProstor.removeOsoba("stormtrooperi");
                    return "[Luke použil svůj světelný meč a porazil stromtroopery, teď může pokračovat]" +
                            "\nLuke: Pracovat pro Impérium se nevyplácí!";
                }
                else if (aktualniProstor.getNazev().equals("tunel")) {
                    aktualniProstor.odemknoutVedlejsi("zbrojirna");
                    aktualniProstor.removeOsoba("stormtrooperi");
                    return "[Luke použil svůj světelný meč a porazil stromtroopery, teď může pokračovat]" +
                            "\nLuke: Pracovat pro Impérium se nevyplácí!";
                } else if (aktualniProstor.getNazev().equals("pruchod")) {
                    return "[Luke nemůže projít brněním těch deathtrooperů]" +
                            "\nDeathtroopeři: Na nás tohle neplatí, zahoď ten meč a klekni na zem.";
                } else {
                    return "Luke: Tady bych se mečem radši ohánět neměl, ať se nikomu nic nestane...";
                }
            case "detonatory":
                if (aktualniProstor.getNazev().equals("dlouha_ulice")) {
                    return "Luke: Radši těmi detonátory nebudu takhle plýtvat, přes ty Stormtroopery se přece dá dostat jinak!" +
                            "\nŠkoda, že u sebe nemám svůj meč.";
                } else if (aktualniProstor.getNazev().equals("pruchod")) {
                    aktualniProstor.odemknoutVedlejsi("kontrolni_mistnost");
                    aktualniProstor.removeOsoba("deathtrooperi");
                    inventar.smazVec("detonatory");
                    return "[Luke hodil po deathtrooperech detonátor a zbyl z nich pouze prach]"
                            + "\nLuke: Páni, ty detonátory jsou nějaké silné";
                }
            case "sklenka_prow":
                return "Luke: Přeci nejsem takový opilec, to bych radši měl to prow někomu dát...";
            case "deathsticky":
                return "Luke: Přeci nejsem blázen, aybch musel tahkle mámit svou mysl...";
            case "kyber_krystal":
                return "Luke: Pěkný krystal, ale tady ho asi nevyužiju...";
            case "id_karta":
                if (aktualniProstor.getNazev().equals("imperialni_ctvrt")) {
                    aktualniProstor.odemknoutVedlejsi("tajna_mistnost");
                    return "[Luke otevřel tajnou místnost]" + "\nLuke: Super, konečně něco zajímavého...";
            } else if (aktualniProstor.getNazev().equals("tunel")) {
                    return "Luke: Ne, špatná karta, na hangár asi používají jinou.";
                } else {
                    return "Luke: Tady tu kartu nemám jak využít, kdybych jen našel nějaký terminál...";
                }
            case "klic_bar":
                if (aktualniProstor.getNazev().equals("bar")) {
                    aktualniProstor.odemknoutVedlejsi("zadni_mistnost");
                    inventar.smazVec("klic_bar");
                    return "[Luke tajně otevřel zadní místnost]" + "\nLuke: Schválně, co v té místnosti bude...";
                } else if (aktualniProstor.getNazev().equals("trh")) {
                    return "Luke: Od tohoto domu potřebuju jiné klíče.";
                } else {
                    return "Luke: Tady ty klíče nemám jak využít...";
                }
            case "klic_ferra":
                if (aktualniProstor.getNazev().equals("trh")) {
                    aktualniProstor.odemknoutVedlejsi("ferruv_dum");
                    inventar.smazVec("klic_ferra");
                    return "[Luke tajně odevřel dveře od Ferrova domu]" + "\nLuke: Schválně, co si ten zločinec doma schovává.";
                } else if (aktualniProstor.getNazev().equals("bar")) {
                    return "Luke: Tento klíč asi patří k jiným dveřím.";
                } else {
                    return "Luke: Tady ty klíče nemám jak využít...";
                }
            case "pristupova_karta":
                if (aktualniProstor.getNazev().equals("tunel")) {
                    aktualniProstor.odemknoutVedlejsi("hangar");
                    return "[Luke otevřel hangár]" + "\nLuke: Konečně se mohu dostat domů...";
                } else if (aktualniProstor.getNazev().equals("imperialni_ctvrt")) {
                    return "Luke: Ne, špatná karta, na tuto místnost asi používají jinou.";
                } else {
                    return "Luke: Tady tu kartu nemám jak využít, kdybych jen našel nějaký terminál...";
                }
        }



        return "Luke: Toto teď nedokážu použít.";
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
