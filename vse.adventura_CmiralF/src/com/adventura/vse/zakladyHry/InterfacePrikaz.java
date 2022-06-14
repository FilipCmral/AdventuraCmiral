package com.adventura.vse.zakladyHry;


/**
 *  Třída implementující toto rozhraní bude ve hře zpracovávat jeden konkrétní příkaz.
 *
 *@author     Jarmila Pavlickova, Filip Cmíral
 *@version    pro školní rok 2016/2017
 *
 */
public interface InterfacePrikaz {


    /**
     *  Metoda pro provedení příkazu ve hře.
     *  Počet parametrů je závislý na konkrétním příkazu,
     *  např. příkazy konec, inventar nemají parametry
     *  příkazy jdi, seber, mluv mají jeden parametr
     *  příkaz napoveda může mít nule nebo jeden parametr
     *
     *  @param parametry počet parametrů závisí na konkrétním příkazu.
     *
     */
    public String provedPrikaz(String... parametry);


    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     *  @return nazev prikazu
     */
    public String getNazev();
}
