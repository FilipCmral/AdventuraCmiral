package com.adventura.vse.zakladyHry;

import java.util.HashMap;
import java.util.Map;

/**
 *  Class SeznamPrikazu - eviduje seznam přípustných příkazů hry.
 *  Používá se pro rozpoznávání příkazů
 *  a vrácení odkazu na třídu implementující konkrétní příkaz.
 *  Každý nový příkaz (instance implementující rozhraní InterfacePrikaz) se
 *  musí do seznamu přidat metodou vlozPrikaz.
 *
 *
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Filip Cmíral
 *@version    pro školní rok 2016/2017
 */
public class SeznamPrikazu {
    // mapa pro uložení přípustných příkazů
    private Map<String, InterfacePrikaz> mapaSPrikazy;



    /**
     * Konstruktor třídy, který používá mapu
     */
    public SeznamPrikazu() {
        mapaSPrikazy = new HashMap<>();
    }


    /**
     * Přidává nový příkaz
     *
     *@param  prikaz  Instance třídy příkaz
     */
    public void vlozPrikaz(InterfacePrikaz prikaz) {
        mapaSPrikazy.put(prikaz.getNazev(),prikaz);
    }


    /**
     * Vrací odkaz na instanci třídy implementující rozhraní InterfacePrikaz,
     * která provádí příkaz uvedený jako parametr.
     *
     *@param  retezec  klíčové slovo příkazu, který chce hráč zavolat
     *@return          instance třídy, která provede požadovaný příkaz
     */
    public InterfacePrikaz vratPrikaz(String retezec) {
        if (mapaSPrikazy.containsKey(retezec)) {
            return mapaSPrikazy.get(retezec);
        }
        else {
            return null;
        }
    }

    /**
     * Kontroluje, zda zadaný řetězec je přípustný příkaz.
     *
     *@param  retezec  Řetězec, který se má otestovat, zda je přípustný příkaz
     *@return          Vrací hodnotu true, pokud je zadaný
     *                 přípustný příkaz
     */
    public boolean jePlatnyPrikaz(String retezec) {
        return mapaSPrikazy.containsKey(retezec);
    }


    /**
     *  Vrací seznam přípustných příkazů, jednotlivé příkazy jsou odděleny mezerou.
     *
     *  @return     Řetězec, který obsahuje seznam přípustných příkazů
     */
    public String vratNazvyPrikazu() {
        String seznam = "";
        for (String slovoPrikazu : mapaSPrikazy.keySet()){
            if (!seznam.equals("")) {
                //pro větší přehlednost dáme čárku
                seznam += ",";
            }
            if(slovoPrikazu.equals("seber")) {
                // aby se nám příkazy vešly na řádek konzole
                seznam += "\n\t";
            }
            seznam += " " + slovoPrikazu;
        }
        return seznam;
    }

}
