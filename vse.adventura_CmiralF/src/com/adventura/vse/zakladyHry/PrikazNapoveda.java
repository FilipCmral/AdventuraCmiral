package com.adventura.vse.zakladyHry;


/**
 *  Třída PrikazNapoveda implementuje pro hru příkaz napoveda.
 *  Tato třída vychází z InterfacePříkaz
 *
 *@author     Jarmila Pavlickova, Luboš Pavlíček, Filip Cmíral
 *@version    pro školní rok 2016/2017
 *
 */
public class PrikazNapoveda implements InterfacePrikaz {
    private static final String NAZEV = "napoveda";
    private SeznamPrikazu platnePrikazy;


    /**
     *  Konstruktor třídy
     *
     *  @param platnePrikazy seznam příkazů,
     *                       které je možné ve hře použít,
     *                       aby je nápověda mohla zobrazit uživateli.
     */
    public PrikazNapoveda(SeznamPrikazu platnePrikazy) {
        this.platnePrikazy = platnePrikazy;
    }



    /**
     *  Vrací základní nápovědu po zadání příkazu "napoveda"
     *  nebo vrací konkrétní nápovědu k dané instanci předmětu, osoby, příkazu
     *
     *  @return napoveda ke hre, k příkazu, k předmětu, k osobě
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Luke ztroskotal na planetě Lothal, tvým úkolem je mu pomoci zorientovat se ve městě" +
                    "\na najít způsob jak se z planety dostat."
                    + "\nCestou můžeš sbírat, používat a vyměňovat předměty nebo interagovat s osobami."
                    + "\nK dispozici máš tyto příkazy: " + platnePrikazy.vratNazvyPrikazu()
                    + "\nTaké můžeš napsat: napoveda [argument]  -argument je nějaký příkaz, věc, osoba apod."
                    + "\nNapř: [napoveda seber]; [napoveda urednik]; [napoveda klic_bar]; [napoveda kredity]"
                    + "\nPokud to uděláš, dostaneš bližší info o dané instanci, popřípadě radu, jak se přiblížit k cíli."
                    + "\nVšechny příkazy jsou bez interpunkce a malými písmeny.";
        } else {
            switch (parametry[0]) {
                case "seber":
                    return "Příkaz seber je učen na sbírání věcí, tyto věci se pak dají využít v určitých situacích příkazem pouzij."
                            + "\nVěci také můžeš měnit s některými postavami pomocí příkazu dej nebo je zahodit, pokud je nepotřebuješ."
                            + "\nPoužití: seber [jmeno_veci]";
                case "zahod":
                    return "Příkaz zahod je určen na zahazování věcí z inventáře, abys udělal prostor pro jiné."
                            + "\nPoužití: zahod [jmeno_veci]";
                case "inventar":
                    return "Příkaz inventar ti zobrazí všechny věci v inventáři, také ti vypíše, kolik kreditů máš u sebe."
                            + "\nPoužití: inventar";
                case "konec":
                    return "Tento příkaz ukončí hru." + "\nPoužití: konec";
                case "jdi":
                    return "Tento příkaz přesune postavu do sousedního prostoru, pokud je prostor přístupný."
                            + "\nNěkteré prostory je potřeba odemknout pomocí klíče, či zabití osoby, která chrání vchod."
                            + "\nPoužití: jdi [jmeno_sousedniho_prostoru]";
                case "mluv":
                    return "Příkaz mluv vypíše monolog osoby se kterou se má Luke bavit."
                            + "\nNěkteré osoby dají Lukovi radu, jak pokračovat, jiné mu zas řeknou co chtějí a můžeš s nimi směňovat pomocí příkazu dej."
                            + "\nPoužití: mluv [jmeno_osoby]";
                case "pouzij":
                    return "Příkaz použij je na používání předmětů, které má Luke v inventáři."
                            + "\nPředměty mají velmi specifické využití, tzn. jdou využít jenom v konkrétních prostorech."
                            + "\nNěkteré předměty využití nemají, ale lze s nimi obchodovat."
                            + "\nPoužití: pouzij [jmeno_veci]";
                case "prozkoumej":
                    return "Příkaz prozkoumej ti vypíše krátké info o prostoru, kde se Luke nachází."
                            + "\nTaké ti dá informace o sousedních prostorech a vypíše věci a osoby v tom aktuálním."
                            + "\nPoužití: prozkoumej";
                case "kredity":
                    return "Kredity jsou měnou tohoto světa, některé osoby přijímají kredity, a dají ti za odměnu jinou věc." +
                            "\nVyužití: dej [jmeno_osoby] kredity"
                            + "\nKredity zároveň slouží jako skóre hry.";
                case "sklenka_prow":
                    return "Sklenka prow je předmět, který můžeš dostat od barmana, když mu dáš nějaké kredity."
                            + "\nTato věc se nedá použít, jen ji můžeš vyměnit s opilcem za více kreditů.";
                case "deathsticky":
                    return "Deathsticky jsou předmět, který můžeš dostat od dealera, když mu dáš nějaké kredity."
                            + "\nTato věc se nedá použít, jen ji můžeš vyměnit se závislým za klíč od ferrova domu";
                case "batoh":
                    return "Batoh je předmět, který můžeš sebrat ve sklepě jediského chrámu, zvýší počet věcí, které můžeš nést u sebe v inventáři.";
                case "kyber_krystal":
                    return "Kyber krystal je předmět, který můžeš sebrat ve sklepě jediského chrámu."
                            + "\nKrystal můžeš vyměnit s obchodníkem na trhu za hojné množství kreditů";
                case "detonatory":
                    return "Detonatory jsou předmět, který lze nalézt u Ferry doma a v imperiální zbrojírně."
                            + "\nVyužít je můžeš, aby ses zbavil Deathtrooperů, taky je můžeš vyměnit s Bosskem za světelný meč.";
                case "svetelny_mec":
                    return "Světelný meč získáš, když dáš Bosskovi detonátory."
                            + "\nSvětelný meč se dá využít, aby ses zbavil stormtroperů.";
                case "id_karta":
                    return "Id karta se dá použít na otevření tajné místnosti..." +
                            "\nPoužij Id kartu v prostoru imperiální čtvrť a otevře se tajná místnost";
                case "pristupova_karta":
                    return "Přístupová karta se dá použít na otevření hangáru..." +
                            "\nPoužij přístupovou kartu v prostoru tunel a otevře se hangár.";
                case "klic_ferra":
                    return "Klíč Ferra se dá použít na otevření Ferrova domu." +
                            "\nPoužij klíč v prostoru trh a dům se otevře.";
                case "klic_bar":
                    return "Klíč bar se dá použít na otevření zadní místnosti." +
                            "\nPoužij klíč v prostoru bar a zadní místnost se otevře.";
                case "farmar":
                    return "Starý farmář ti moc nepomůže, když si s ním promluvíš, tak ti možná poradí cestu.";
                case "urednik":
                    return "Úředník kontroluje, kdo jde do města, prvně si s ním musíš promluvit, a pak tě pustí.";
                case "pilot":
                    return "Pilot už dlouho nelétá, moc ti nepomůže, když si s ním promluvíš, prozradí ti proč.";
                case "dealer":
                    return "Dealer ti prodá deathsticky, když mu dáš pár kreditů." +
                            "\nTobě deathsticky budou k ničemu, ale postava Závisláka ti za ně dá cokoliv.";
                case "blazen":
                    return "S bláznem se radši nebav.";
                case "obchodnik":
                    return "Obchodník sbírá krystaly, za takový kyber_krystal je ochoten dát jmění.";
                case "trendosian":
                    return "Trendosian hledá místní bar, ve kterém se nachází jeho šéf Bossk." +
                            "\nTaky bys ten bar měl najít, Bossk má pro tebe skvělý obchod.";
                case "obi-wanuv_duch":
                    return "Obi-Wan ti je ochoten pomoci, když si s ním promluvíš.";
                case "bossk":
                    return "Bossk je nájemný zabiják, který náhodou našel tvůj světelný meč." +
                            "\nKdyž mu přineseš detonátory z Ferrova domu, tak ti meč vrátí.";
                case "opilec":
                    return "Opilec ti toho moc neřekne.\nAle kdybys mu přinesl něco k pití, tak se možná rozpovídá.";
                case "barman":
                    return "Barman prodává Prow. \nDej to Prow opilcovi";
                case "ferra":
                    return "S Ferrou se radši nebav, než se naštve.";
                case "zavisly":
                    return "Závislý potřebuje deathsticky, když mu je dáš, dostaneš klíč od Ferrova domu.";
                case "cestovatel":
                    return "Cestovatel je skoro stejně zmatený jako ty, když s ním promluvíš třeba ti něco prozradí.";
                case "veteran":
                    return "Starý veterán ti už nepomůže, je už moc starý.";
                case "stormtrooperi":
                    return "Stormtroopeři chrání imperiální čtvrť a řadu objektů uvnitř." +
                            "\nJediný způsob, jak se jich zbavit je použít světelný meč.";
                case "deathtrooperi":
                    return "Deathtroopeři jsou o dost silnější než Stormtroopeři."+
                            "\nNa ně tvůj meč nestačí, musíš použít detonátor.";


                default:
                    return "Tento typ nápovědy neexistuje.";
            }
        }

    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
