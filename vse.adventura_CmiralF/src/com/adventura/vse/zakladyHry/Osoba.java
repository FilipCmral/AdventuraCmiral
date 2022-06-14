package com.adventura.vse.zakladyHry;


/**
 * Třída Osoba popisuje jednotlivé osoby ve hře
 *
 * S osobami může hráč mluvit pomocí příkazu mluv.
 * Některé osoby mají nastavenou výměnu, tzn. když hráč napíše příkaz dej Osoba Věc,
 * tak osoba vymění předmět s hráčem. Některé osoby chtějí nebo vrací kredity.
 *
 *
 * @author Filip Cmíral
 */
public class Osoba {
    private final String jmeno;
    private String rozhovor;

    private Vec vecVlastni;
    private Vec vecChtena;
    private int cenaVeci;

    private String rozhovorPoVymene;
    private String rozhovorNechceVec;
    private String rozhovorChceVec;
    private String rozhovorUzVymenil;
    private boolean probehlaVymena;

    /**
     * Konstrkuktor třídy, osoba má jméno
     * Základní rozhovor a rozhovor, když je jí dána věc
     * Vzhledem k tomu, že normální osoba nemá zájem o obchod, tak věc odmítne
     *
     *
     * @param jmeno jméno Osoby
     * @param rozhovor rozhovor, který řekne při zadání příkazu mluv Osoba
     * @param rozhovorNechceVec rozhovor, který řekne, když je jí dána věc, o kterou nemá zájem
     */
    public Osoba(String jmeno, String rozhovor, String rozhovorNechceVec) {
        this.jmeno = jmeno;
        this.rozhovor = rozhovor;
        this.rozhovorNechceVec = rozhovorNechceVec;
    }


    /**
     *
     * @return jméno osoby
     */
    public String getJmeno() {
        return jmeno;
    }

    /**
     * Vrátí rozhovor, podle toho, jestli
     *
     * @return
     */
    public String getRozhovor() {
        if (!this.probehlaVymena) {
            return rozhovor;
        } else {
            return rozhovorPoVymene;
        }


    }

    /**
     * Vrátí cenu věci, kterou osoba nabízí, používá se, když osoba obchoduje s kredity
     *
     * @return cena věci
     */
    public int getCenaVeci() {
        return cenaVeci;
    }

    /**
     *
     * @param cenaVeci cena, kterou osoba žádá při výměně
     */
    public void setCenaVeci(int cenaVeci) {
        this.cenaVeci = cenaVeci;
    }


    /**
     * Nastaví osobě výměnu
     *
     *
     * @param vecVlastni věc, kterou osoba vlastní a po výměně dá hráči
     * @param vecChtena věc, kterou osoba požaduje
     * @param rozhovorPoVymene rozhovor, který bude osoba říkat vždy po výměně
     * @param rozhovorChceVec rozhovor, který bude osoba říkat, když úspěšně vymění
     * @param rozhovorUzVymenil rozhovor, který bude osoba říkat, když už nechce vyměňovat
     * @param probehlaVymena t/f, jestli už s osobou hráč měnil
     */
    public void addVymena(Vec vecVlastni, Vec vecChtena, String rozhovorPoVymene,  String rozhovorChceVec, String rozhovorUzVymenil, boolean probehlaVymena) {
        this.vecVlastni = vecVlastni;
        this.vecChtena = vecChtena;
        this.rozhovorPoVymene = rozhovorPoVymene;
        this.rozhovorChceVec = rozhovorChceVec;
        this.rozhovorUzVymenil = rozhovorUzVymenil;
        this.probehlaVymena = probehlaVymena;

    }

    /**
     *
     * @return věc, kterou osoba chce
     */
    public Vec getVecChtena() {
        return vecChtena;
    }

    /**
     *
     * @return t/f, jestli hráč s osobou již měnil
     */
    public boolean isProbehlaVymena() {
        return probehlaVymena;
    }

    /**
     *
     * @return rozhovor, který bude osoba říkat, když už nechce vyměňovat
     */
    public String getRozhovorUzVymenil() {
        return rozhovorUzVymenil;
    }

    /**
     *
     * @return rozhovor, který řekne, když je jí dána věc, o kterou nemá zájem
     */
    public String getRozhovorNechceVec() {
        return rozhovorNechceVec;
    }


    /**
     * Udělá výměnu osoby s hráčem
     *
     *
     * @param nabizena věc, kterou hráč nabízí osobě
     * @return věc, kterou osoba hráči vrací (pokud výměna proběhla úspěšně)
     */
    public Vec vymena(Vec nabizena) {
        if (nabizena.equals(this.vecChtena)) {
            this.probehlaVymena = true;
            return this.vecVlastni;
        }
        else {
            return null;
        }
    }

    /**
     * Vrátí řeč, kterou osoba bude říkat po výměně
     * Záleží, jestli výměna proběhla úspěšně nebo ne
     *
     * @param vec věc, kterou hráč osobě nabízel
     * @return řeč, kterou osoba bude říkat
     */
    public String getRecVymena(Vec vec) {
        if (vec == null) {
            return rozhovorNechceVec;
        }
        if (vec.equals(this.vecChtena)) {
            return this.rozhovorChceVec;
        } else {
            return rozhovorNechceVec;

        }
    }





}
