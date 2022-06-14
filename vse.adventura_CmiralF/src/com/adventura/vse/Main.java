package com.adventura.vse;

import com.adventura.vse.zakladyHry.Hra;

/*******************************************************************************
 * Třída  Start je hlavní třídou projektu,
 * který představuje jednoduchou textovou adventuru určenou k dalším úpravám a rozšiřování
 *
 * @author    Jarmila Pavlíčková, Filip Cmíral
 * @version   ZS 2016/2017
 */

public class Main {

    /***************************************************************************
     * Metoda, prostřednictvím níž se spouští celá aplikace.
     *
     * @param args Parametry příkazového řádku
     */
    public static void main(String[] args) {

        Hra nova_hra = new Hra();
        if (args.length == 0) {
            nova_hra.hraj();
        }
        else {
            String argument = args[0];
            if (argument.equals("testFunkci.txt")) {
                nova_hra.spustZeSouboru(argument);
            } else if (argument.equals("skore.txt")) {
                nova_hra.prectiSkore(argument);
            } else {
                System.out.println("V args byl podán neplatný argument.");
            }

        }
    }
}
