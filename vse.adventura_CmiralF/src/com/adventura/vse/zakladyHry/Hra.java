package com.adventura.vse.zakladyHry;


/**
 *  Třída Hra - třída představující logiku adventury.
 *
 *  Toto je hlavní třída  logiky aplikace.  Tato třída vytváří instanci třídy HerniPlan, která inicializuje mistnosti hry
 *  a vytváří seznam platných příkazů, instance tříd provádějící jednotlivé příkazy, instanci inventáře uchovávající předměty.
 *  Vypisuje uvítací text hry.
 *  Také čte a vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Filip Cmíral
 *@version    pro školní rok 2016/2017
 */

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Hra extends TemplateHra {
    private SeznamPrikazu platnePrikazy;
    private HerniPlan herniPlan;
    private Inventar inventarNeco;

    private boolean konecHry = false;


    /**
     *  Vytváří hru a inicializuje místnosti (prostřednictvím třídy HerniPlan) a seznam platných příkazů.
     */
    public Hra() {
        herniPlan = new HerniPlan();
        platnePrikazy = new SeznamPrikazu();
        inventarNeco = new Inventar(2);
        platnePrikazy.vlozPrikaz(new PrikazNapoveda(platnePrikazy));
        platnePrikazy.vlozPrikaz(new PrikazJdi(herniPlan, this));
        platnePrikazy.vlozPrikaz(new PrikazKonec(this));
        platnePrikazy.vlozPrikaz(new PrikazSeber(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazZahod(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazPouzij(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazInventar(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazMluv(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazProzkozkoumej(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazDej(herniPlan));
    }

    /**
     *  Vrátí úvodní zprávu pro hráče.
     */
    public String uvitani() {
        return "\t\t\t  ---STAR WARS---\n" +
                "Luke Skywalker byl sestřelen, když ho nečekaně\n" +
                "napadly imperiální TIE Fightery. Se svým X-Wingem\n" +
                "se zřítil vedle města Kothar na planetě Lothal.\n" +
                "Všechno, co Luke u sebe měl je pryč. V této těžké\n" +
                "chvíli mu zbývá jen Síla a odhodlání přežít.\n" +
                "Luka za žádnou cenu nesmí chytit imperiálové.\n" +
                "Pomoz mu najít způsob jak se dostat k nové stíhačce\n" +
                "a uniknout z rukou impéria.\n" +
                "Napište 'napoveda' - pokud nebudete vědět, jak dál.\n" +
                "Příkazem 'prozkoumej' zjistíš co se na daném místě nachází" +
                "\n" +
                herniPlan.getAktualniProstor().kratkyPopis();
    }

    /**
     *  Vrátí závěrečnou zprávu pro hráče.
     */
    public String vratEpilog() {return "\t\t\t  ---STAR WARS---\n" +
            "Luke Skywalker sebral imperiální loď a uletěl z planety Lothal\n"
            + "Díky, že jsi mu pomohl/a. Ať tě provází Síla.\n";}

    /**
     * Vrací true, pokud hra skončila.
     */
    public boolean konecHry() {return konecHry;}


    /**
     *  Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo příkazu a další parametry.
     *  Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     *  Pokud ano spustí samotné provádění příkazu.
     *
     *@param  radek  text, který zadal uživatel jako příkaz do hry.
     *@return          vrací se řetězec, který se má vypsat na obrazovku
     */
    public String zpracujPrikaz(String radek) {
        String[] slova = radek.split("[ \t]+");
        String slovoPrikazu = slova[0];
        String [] parametry = new String[slova.length-1];
        for (int i = 0; i<parametry.length; i++) {
            parametry[i] = slova[i+1];
        }
        String textKVypsani = " .... ";
        if (platnePrikazy.jePlatnyPrikaz(slovoPrikazu)) {
            InterfacePrikaz prikaz =platnePrikazy.vratPrikaz(slovoPrikazu);
            textKVypsani = prikaz.provedPrikaz(parametry);
        }
        else {
            textKVypsani = "Luke: Nevím co tím myslíš? Tento příkaz neznám";
        }
        return  textKVypsani;
    }


    /**
     *  Nastaví, že je konec hry, metodu využívá třída PrikazKonec
     *  a třída PrikazJdi, když se hráč dostane do lodi a odletí.
     *
     *  @param  konecHry  hodnota false= konec hry, true = hra pokračuje
     */
    public void setKonecHry(boolean konecHry)
    {
        this.konecHry = konecHry;
    }

    /**
     *
     * @return instance třídy herní plán
     */
    public HerniPlan getHerniPlan() {
        return herniPlan;
    }


    /**
     *
     * @return instance třídy s platnými příkazy
     */
    public SeznamPrikazu getPlatnePrikazy() {
        return platnePrikazy;
    }

    /**
     *  Hlavní metoda hry. Vypíše úvodní text a pak opakuje čtení a zpracování
     *  příkazu od hráče do konce hry (dokud metoda konecHry() z logiky nevrátí
     *  hodnotu true). Nakonec zkontroluje a vypíše skore.
     *  Když má hráč dostatečné skore, spustí metodu na zápis skore.
     */
    public void hraj() {
        System.out.println(this.uvitani());

        //opakuje čtení a zpracování příkazů dokud neskončí hra
        while(!this.konecHry()) {
            String radek = prectiInput();
            System.out.println(this.zpracujPrikaz(radek));
        }


        int skore = herniPlan.getInventar().getPocet_kreditu();
        if (skore == 0) {
            System.out.println("\nNesesbírali jste žádné kredity, vaše skore je 0.\t:(");
        } else {
            System.out.println("\nSesbírali jste spoustu kreditů, vaše skore je " + skore +".\t:D");
            zapisSkore(skore);

        }

    }


    /**
     *  Metoda přečte příkaz z příkazového řádku
     *
     *@return    Vrací přečtený příkaz jako instanci třídy String
     */
    private String prectiInput() {
        Scanner inputUzivatele = new Scanner(System.in);
        System.out.print("> ");
        return inputUzivatele.nextLine();

    }


    /**
     * Metoda přečte zapsaná skore ze souboru skore.txt
     * Metoda se spustí jen tehdy, když je v argumentu hlavní třídy zadáno skore.txt
     *
     * @param soubor soubor, kde jsou zapsána skore
     */
    public void prectiSkore(String soubor) {
        System.out.println("Zapsaná skore:");
        try {
            BufferedReader skore = new BufferedReader(new FileReader(soubor));
            String radek = skore.readLine();
            while (radek != null) {
                System.out.println(radek);
                radek=skore.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Metoda se zeptá uživatele, jestli chce, aby jeho skore (počet kreditů)
     * bylo zapsáno do souboru skore.txt
     * Pokud ano, zapíše se jeho skore pod jím vybraným jménem do souboru
     *
     *
     * @param skore skore  (počet kreditů), které hráč získal
     */
    public void zapisSkore(int skore) {
        Scanner scannerSkore = new Scanner(System.in);
        System.out.println("Chcete zapsat své skore do tabulky? Y/N");
        char zapsat = scannerSkore.next().charAt(0);
        if (zapsat == 'y' || zapsat == 'Y') {

            System.out.println("Zadejte jméno, pod kterým chcete skore zapsat.");
            String jmeno = (new Scanner(System.in)).nextLine();

            LocalDate dnesniDatum = LocalDate.now();
            DateTimeFormatter  zpusobFormatu= DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String dnesniDatumFormatovane = dnesniDatum.format(zpusobFormatu);

            try {PrintWriter zapisSkore = new PrintWriter(new FileWriter("skore.txt", true));
                    zapisSkore.append("\n" + skore + ", " + jmeno + ", " + dnesniDatumFormatovane);
                    zapisSkore.close();

                System.out.println("Vaše skore bylo zapsáno.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Skore nebylo zapsáno.");
        }
    }


    /**
     * Metoda spustí hru pomocí souboru testFunkci.txt
     * Příkazy nejsou zadány do řádku, nýbrž vypsány do souboru
     * Metoda slouží hlavně pro rychlou kontrolu funkcí programu a ne pro hráče
     * Metoda se spustí jen tehdy, když je v argumentu hlavní třídy zadáno testFunkci.txt
     *
     * @param soubor soubor testFunkci.txt, ze kterého se čte hra
     */
    public void spustZeSouboru(String soubor) {
        try (BufferedReader cteni = new BufferedReader(new FileReader(soubor));
             PrintWriter zapis = new PrintWriter(new FileWriter("vypisTestu.txt")))  {
            zapis.println(uvitani());
            System.out.println(uvitani());

            String radek = cteni.readLine();
            while (radek != null) {
                System.out.println("> " + radek);
                zapis.println("> " + radek);

                String odpoved = zpracujPrikaz(radek);
                System.out.println(odpoved);

                zapis.println(odpoved);
                radek = cteni.readLine();
            }



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
