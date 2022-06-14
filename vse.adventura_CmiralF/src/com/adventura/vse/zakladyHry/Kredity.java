package com.adventura.vse.zakladyHry;


/**
 * Třída Kredity popisuje jednotlivé kredity popisující ve hře
 * Třída rozvijí třídu Vec
 *
 * Kredity mají název, mohou být sebratelné nebo nesebratelné
 * Také mají vždy nějakou číselnou hodnotu
 *
 * @author Filip Cmíral
 */
public class Kredity extends Vec{
    private int hodnota;

    /**
     * Konstruktor třídy kredity, má název, sebratelnost a hodnotu
     *
     * @param nazev název předmětu kreditů
     * @param sebratelna sebratelnost t/f
     * @param hodnota peněžní hodnota
     */
    public Kredity(String nazev, boolean sebratelna, int hodnota) {
        super(nazev, sebratelna);
        this.hodnota = hodnota;
    }

    /**
     *
     * @return hodnota kreditů
     */
    public int getHodnota() {
        return hodnota;
    }
}
