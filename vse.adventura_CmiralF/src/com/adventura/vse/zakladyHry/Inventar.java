package com.adventura.vse.zakladyHry;

import java.util.Vector;


/**
 * Třída Invenar - vytváří funkční inventář pro hráče
 *
 * Inventář má nějakou velikost, a může tedy nést jen nějaké maximální množství věcí
 * velikost inventáře se dá zvětšit
 *Inventář zároveň udržuje informaci o kreditech (herní měna)
 * A s kredity pracuje
 *
 * @author Filip Cmíral
 */
public class Inventar {
    private int velikostInventare;
    private Vector<Vec> veciVInventari;
    private int pocet_kreditu;

    /**
     * Vytvoření inventáře, který má nějakou velikost
     * Inventář funguje na bázi vektoru veciVInventari,
     * který ukládá, které věci se v něm nacházejí
     *
     * @param velikostInventare maximální počet věcí v inventáři
     */
    public Inventar(int velikostInventare) {
        this.velikostInventare = velikostInventare;

        this.pocet_kreditu = 0;
        veciVInventari = new Vector<>();
    }


    /**
     * Nastaví velikost inventáře
     *
     * @param velikostInventare číslo, kolik věcí může být maximálně v inventáři
     */
    public void setVelikostInventare(int velikostInventare) {
        this.velikostInventare = velikostInventare;
    }


    /**
     *
     * @return počet kreditů v inventáři
     */
    public int getPocet_kreditu() {
        return pocet_kreditu;
    }

    /**
     * Nastaví počet kredtů v inventáři
     *
     * @param pocet_kreditu kolik kreditů má být v inventáři
     */
    public void setPocet_kreditu(int pocet_kreditu) {
        this.pocet_kreditu = pocet_kreditu;
    }



    /**
     * Vloží věc do inventáře
     *
     *
     * @param pridavana instance třídy Vec, která má být přidána
     * @return instance třídy věc, pokud se přidání povedlo
     */
    public Vec vlozVec(Vec pridavana) {
        if (jeMisto()) {
            veciVInventari.add(pridavana);
            return pridavana;
        }
        return null;
    }

    /**
     * Přidá věc do inventáře (I když by na ní nebylo místo)
     *
     * @param pridavana instance třídy Vec, která má být přidána
     * @return instance třídy věc
     */
    public Vec addVec(Vec pridavana) {
        veciVInventari.add(pridavana);
        return pridavana;
    }


    /**
     * Zjistí, jestli je v inventáři místo na přidání věci
     *
     * @return t/f - jestli lze věc sebrat/přidat
     */
    public boolean jeMisto() {
        if (this.getPocetVeci() < velikostInventare) {
            return true;
        }
        return false;
    }

    /**
     * Zjistí, jestli by bylo místo v inventáři
     * - používá se při výměně, kde hráč zároveň přijímá a odevzdává věc
     *
     * @return t/f jestli by bylo místo
     */
    public boolean byloByMisto() {
        if (this.getPocetVeci() + 1 <= velikostInventare) {
            return true;
        }
        return false;
    }

    /**
     * Zjistí, jestli inventář obsahuje hledanou věc
     *
     * @param hledana název věci
     * @return t/f jestli obsahuje věc
     */
    public boolean obsahujeVec(String hledana) {
        for (Vec aktualni: veciVInventari) {
            if (aktualni.getNazev().equals(hledana)) {
                return true;
            }
        }
        return false;
    }


    /**
     *
     * @return počet věcí v inventáři
     */
    public int getPocetVeci(){
        int pocet = 0;
        for (int i = 0; i< veciVInventari.size(); i++) {
            pocet++;
        }

        return pocet;
    }


    /**
     *
     * @return seznam věcí v inventáři
     */
    public String getVeci() {
        String seznam = "";
        for (int i = 0; i< veciVInventari.size(); i++) {
            seznam += " " + veciVInventari.elementAt(i).getNazev();
        }


        return seznam;
    }

    /**
     * Vrátí instanci třídy Vec na zíkladě jména, pokud se věc v inventáři nachází
     *
     * @param nazev název věci
     * @return instance třídy věc
     */
    public Vec getVec(String nazev) {
        Vec hledana = null;
        for (int i = 0; i < veciVInventari.size(); i++) {
            if (veciVInventari.elementAt(i).getNazev().equals(nazev)) {
                hledana = veciVInventari.elementAt(i);
                break;
            }
        }


        return hledana;
    }





    /**
     * Smaže věc z inventáře na základě jména věci
     *
     *
     * @param mazana jméno mazané věci z inventáře
     * @return instanci smazané věci
     */
    public Vec smazVec (String mazana) {
        Vec smazana = null;
        for (int i = 0; i < veciVInventari.size(); i++) {
            if (veciVInventari.elementAt(i).getNazev().equals(mazana)) {
                smazana = veciVInventari.elementAt(i);
                break;
            }
        }
        this.veciVInventari.remove(smazana);
        return smazana;
    }


}
