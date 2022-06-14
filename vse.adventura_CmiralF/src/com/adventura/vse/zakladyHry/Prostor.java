package com.adventura.vse.zakladyHry;

import java.util.*;


/**
 * Trida Prostor - popisuje jednotlivé prostory (místnosti) hry
 *
 *
 * "Prostor" reprezentuje jedno místo (místnost, prostor, ..) ve scénáři hry.
 * Prostor může mít sousední prostory připojené přes východy. Pro každý východ
 * si prostor ukládá odkaz na sousedící prostor. Ke všemu jsou použity mapy
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Filip Cmíral
 * @version pro školní rok 2016/2017
 */
public class Prostor {

    private final String nazev;
    private final String popis;

    private boolean nepristupna;
    private String nepristupnaZprava;

    private Map<String, Prostor> vychody;
    private Map<String, Vec> veci;
    private Map<String, Osoba> osoby;

    /**
     * Vytvoření prostoru se zadaným popisem
     *
     * @param nazev nazev prostoru - jedno slovo
     * @param popis Popis prostoru.
     */
    public Prostor(String nazev, String popis) {
        this.nazev = nazev;
        this.popis = popis;


        vychody = new HashMap<>();
        veci = new HashMap<>();
        osoby = new HashMap<>();
    }


    /**
     * Definuje východ z prostoru (sousední/vedlejsi prostor)
     *
     * @param vedlejsi prostor, který sousedi s aktualnim prostorem.
     *
     */
    public void setVychod (String vedlejsiNazev,Prostor vedlejsi) {vychody.put(vedlejsiNazev,vedlejsi);}

    /**
     * Uzamkne nějakou místnost/prostor v herním plánu, ta se
     * odemkne pouze tehdy když hráč udělá nějakou specifickou činnost
     * např. promluví s postavou, použije klíč
     *
     * @param nepristupna mistnost/prostor, který se uzamkne
     * @param nepristupnaZprava zprava, kterou metoda vrátí, pokud se hráč pokusí vejít do uzamčeného prostoru
     */
    public void zamknout(boolean nepristupna, String nepristupnaZprava) {
        this.nepristupna = nepristupna;
        this.nepristupnaZprava = nepristupnaZprava;
    }


    /**
     * Odemkne místnost v herním plánu
     *
     * @param vedlejsi, nazev vedlejší místnosti, která má být odemčena
     */
    public void odemknoutVedlejsi(String vedlejsi) {
        Prostor sousedni = vratSousedniProstor(vedlejsi);
        sousedni.zamknout(false, "odemceno");
    }

    /**
     *
     * @return nepristupnaZprava zprava která je vrácena uzamčenou místností,
     * když se hráč pokusí vejít
     */
    public String getNepristupnaZprava() {
        return nepristupnaZprava;
    }

    /**
     *
     * @return boolean nepristupna, zjistí, jestli se dá do prostoru vejít
     */
    public boolean isNepristupna() {
        return nepristupna;
    }

    /**
     * Přidá instanci věci do prostoru
     *
     * @param vec Věc, která má být do prostoru přidána
     */
    public void addVec (Vec vec) {
        veci.put(vec.getNazev(), vec);
    }

    /**
     * Vrátí instanci třídy věc na základě jména
     *
     * @param jmenoVeci jméno instance věci
     * @return Vec podle jména věci z mapy věcí
     */
    public Vec getVec(String jmenoVeci) {
        return veci.get(jmenoVeci);
    }

    /**
     * Odstraní věc z daného prostoru
     *
     * @param jmenoVeci jméno věci, která má být odstraněna
     */
    public void removeVec(String jmenoVeci) {
        veci.remove(jmenoVeci);
    }

    /**
     * Přidá instanci třídy osoba do prostoru
     *
     * @param osoba osoba, která má být přidána
     */
    public void addOsoba (Osoba osoba) {
        osoby.put(osoba.getJmeno(), osoba);
    }

    /**
     * Odebere osobu z prostoru
     *
     * @param jmenoOsoby jméno osoby, která má být odebrána
     */
    public void removeOsoba(String jmenoOsoby) {
        osoby.remove(jmenoOsoby);
    }

    /**
     * Vrátí instanci třídy osoba na základě klíče jménoOsoby z mapy osob
     *
     * @param jmenoOsoby, jméno osoby, která má být vrácena
     * @return instance třídy osoba
     */
    public Osoba getOsoba(String jmenoOsoby) {return osoby.get(jmenoOsoby);}



    /**
     * Metoda equals pro porovnání dvou prostorů. Překrývá se metoda equals ze
     * třídy Object. Dva prostory jsou shodné, pokud mají stejný název. Tato
     * metoda je důležitá z hlediska správného fungování seznamu východů (Set).
     *
     * Bližší popis metody equals je u třídy Object.
     *
     * @param o object, který se má porovnávat s aktuálním
     * @return hodnotu true, pokud má zadaný prostor stejný název, jinak false
     */
    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (!(o instanceof Prostor)) {
            return false;
        }

        Prostor druhy = (Prostor) o;

        return (java.util.Objects.equals(this.nazev, druhy.nazev));
    }


    /**
     * metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.nazev);
    }

    /**
     * Vrátí název prostoru
     *
     * @return název prostoru
     */
    public String getNazev() {
        return nazev;
    }


    /**
     * Vypíše jednoduchý popis prostoru
     *
     * @return krátký popis prostoru
     */
    public String kratkyPopis() {
        return "" + popis + "\n";
    }

    /**
     * Vrací "dlouhý" popis prostoru, který vypadá následovně: Jsi v
     * mistnosti/prostoru brána, východy: centrum trh, předměty: batoh, osoby: úředník
     *
     * @return Dlouhý popis prostoru
     */
    public String dlouhyPopis() {
        return "" + popis + "\n"
                + popisVychodu() + "\n"
                + popisVeci() + "\n"
                + popisOsob();

    }

    /**
     * Vypíše východy do sousedních prostor a jejich přístupnost
     * na základě for each loopu a mapy prostor
     *
     * @return východy z momentálního prostoru
     */
    private String popisVychodu() {
        String vracenyText = "východy:";
        for (String sousedni : vychody.keySet()) {
            vracenyText += " " + sousedni;
            if (vratSousedniProstor(sousedni).nepristupna) {
                vracenyText += "(nepřístupné)";
            }
        }
        return vracenyText;
    }

    /**
     * Vypíše věci v prostoru
     * na základě for each loopu a mapy věcí
     *
     * @return věci v momentálním prostoru
     */
    private String popisVeci() {
        String vracenyText = "věci:";
        int pocetVeci = 0;
        for (String vec : veci.keySet()) {
            vracenyText += " " + vec;
            pocetVeci++;
        }
        if (pocetVeci == 0) {
            vracenyText +=  "žádné";
        }
        return vracenyText;

    }


    /**
     * Vypíše osoby v prostoru
     * na základě for each loopu a mapy osob
     *
     * @return osoby v momentálním prostoru
     */
    private String popisOsob() {
        String vracenyText = "osoby:";
        int pocetOsob = 0;
        for (String osoba : osoby.keySet()) {
            vracenyText += " " + osoba;
            pocetOsob++;
        }
        if (pocetOsob == 0) {
            vracenyText += "žádné";
        }
        return vracenyText;
    }


    /**
     * Vrátí instanci třídy prostor sousedního prostoru na základě jména prostoru
     *
     * @param nazevSouseda název sousedního prostoru
     * @return instance třídy prostor, sousední prostor
     */
    public Prostor vratSousedniProstor(String nazevSouseda) {
        return vychody.get(nazevSouseda);
    }
}
