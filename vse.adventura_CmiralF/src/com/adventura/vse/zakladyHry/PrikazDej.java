package com.adventura.vse.zakladyHry;


/**
 * Třída PrikazDej implementuje pro hru příkaz dej
 * Tato třída vychází z InterfacePříkaz
 *
 * @author Filip Cmíral
 */
public class PrikazDej implements InterfacePrikaz{
    private static final String NAZEV = "dej";
    private final HerniPlan herniPlan;


    /**
     * Konstruktor třídy
     *
     * @param herniPlan herní plán podle krerého se třída řídí
     */
    public PrikazDej(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }


    /**
     * Provede/neprovede výměnu podle toho, co hráč postavě nabízí
     * Lze měnit a získávat i kredity
     *
     * @param parametry věc, kterou má hráč postavě dát
     * @return rozhovor, který postava po výměně vrátí
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Luke: Co mám dát, a komu?";
        } else if (parametry.length == 1) {
            return "Luke: A co bych té osobě měl dát?";
        }







        String nazevOsoby = parametry[0];
        String nazevVeci = parametry[1];
        Prostor aktualniProstor = herniPlan.getAktualniProstor();




        Inventar inventar = herniPlan.getInventar();
        Osoba osoba = aktualniProstor.getOsoba(nazevOsoby);



        if (nazevVeci.equals("kredity") ) {
            if (osoba == null) {
                return "Luke: Nikdo takový tady není.";
            }
            if (osoba.getVecChtena() == null) {
                return osoba.getRozhovorNechceVec();
            }


            if (!inventar.byloByMisto()) {
                return "Luke: Nemůžu to koupit, pak bych to neměl kam dát";
            }

            int cenaKoupe = osoba.getCenaVeci();


            if (inventar.getPocet_kreditu() < cenaKoupe) {
                return "Luke: Na to teďka nemám peníze";
            }
            Vec kredityVymena = osoba.getVecChtena();


            Vec ziskana = osoba.vymena(kredityVymena);
            inventar.addVec(ziskana);
            inventar.setPocet_kreditu(inventar.getPocet_kreditu() - cenaKoupe);

            return osoba.getRecVymena(kredityVymena);
        }




        Vec vec = inventar.getVec(nazevVeci);

        if (vec == null) {
            return "Luke: Toto u sebe nemám.";
        }
        if (osoba == null) {
            return "Luke: Nikdo takový tady není.";
        }
        if (osoba.getVecChtena() == null) {
            return osoba.getRozhovorNechceVec();
        }




        if (osoba.isProbehlaVymena()) {
            return osoba.getRozhovorUzVymenil();
        }


        Vec ziskana = osoba.vymena(vec);
        if (ziskana==null) {
            return osoba.getRecVymena(vec);
        }



        if (ziskana instanceof Kredity) {
            int pocet = ((Kredity) ziskana).getHodnota();
            inventar.setPocet_kreditu(inventar.getPocet_kreditu() + pocet);
        } else {
            inventar.addVec(ziskana);
        }



        inventar.smazVec(nazevVeci);



        return osoba.getRecVymena(vec);
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
