package com.adventura.vse.zakladyHry;


/**
 *  Třída PrikazJdi implementuje pro hru příkaz jdi.
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Jarmila Pavlickova, Luboš Pavlíček, Filip Cmíral
 *@version    pro školní rok 2016/2017
 */
public class PrikazJdi implements InterfacePrikaz {
    private static final String NAZEV = "jdi";
    private HerniPlan plan;
    private Hra hra;


    /**
     *  Konstruktor třídy
     *
     *  @param plan herní plán, ve kterém se bude ve hře "pohybovat"
     *  @param hra hra, kterou hráč hraje, aby mohla být ukončena, když se dostane do lodi (cíl)
     *
     */
    public PrikazJdi(HerniPlan plan, Hra hra) {
        this.plan = plan;
        this.hra = hra;
    }


    /**
     *  Provádí příkaz "jdi". Zkouší se vyjít do zadaného prostoru. Pokud prostor
     *  existuje, vstoupí se do nového prostoru. Pokud zadaný sousední prostor
     *  (východ) není, vypíše se chybové hlášení.
     *
     *@param parametry - jako  parametr obsahuje jméno prostoru (východu),
     *                         do kterého se má jít.
     *@return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Luke: Kam mám jít?";
        }

        String smer = parametry[0];

        Prostor sousedniProstor = plan.getAktualniProstor().vratSousedniProstor(smer);

        //pokud sousední prostor neexistuje nebo byl zadán špatně
        if (sousedniProstor == null) {
            return "Luke: Odsud se tam asi nedostanu.";
        } else if (sousedniProstor.isNepristupna()) {
            return sousedniProstor.getNepristupnaZprava();

        }
        else {
            //pokud vejde hráč do nákladní lodi (-> Luke může letět pryč) ukončí se hra a vypíše se epilog
            if (plan.getAktualniProstor().getNazev().equals("hangar") && sousedniProstor.getNazev().equals("nakladni_lod")) {
                hra.setKonecHry(true);
                return hra.vratEpilog();
            }

            //jinak změna aktuálního prostoru a popis prostoru
            plan.setAktualniProstor(sousedniProstor);
            return sousedniProstor.kratkyPopis();
        }
    }


    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {return NAZEV;}
}
