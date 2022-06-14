package com.adventura.vse.zakladyHry;


/**
 * Třída Vec - popisuje jednotlivé věci (předměty), které se vyskytují ve hře
 *
 * Věc má nějaký název a buď může být sebratelná nebo nesebratelná
 *
 */
public class Vec {
    private final String nazev;
    private boolean sebratelna;


    /**
     * Konstruktor věci
     *
     * @param nazev název věci
     * @param sebratelna sebratelnost věci
     */
    public Vec(String nazev, boolean sebratelna) {
        this.nazev = nazev;
        this.sebratelna = sebratelna;
    }

    /**
     *
     *
     * @return název věci
     */
    public String getNazev() {
        return nazev;
    }


    /**
     *
     * @return sebratelnost věci
     */
    public boolean isSebratelna() {
        return sebratelna;
    }


    /**
     * Nastaví sebratelnost dané věci
     *
     * @param sebratelna t/f jestli má být věc sebratelná
     */
    public void setSebratelna(boolean sebratelna) {
        this.sebratelna = sebratelna;
    }

    /**
     * Přepíše metodu toString, aby vracela věc ve správném formátu
     *
     * @return String - věc
     */
    @Override
    public String toString() {
        return "Vec{" +
                "nazev='" + nazev + '\'' +
                ", sebratelna=" + sebratelna +
                '}';
    }
}
