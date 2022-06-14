package com.adventura.vse.zakladyHry;


/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 *
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory a věci nebo osoby v nich
 *  prostory vzájemně propojuje pomocí východů
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Filip Cmíral
 *@version    pro školní rok 2016/2017
 */

public class HerniPlan {
    private Prostor aktualniProstor;
    private Inventar inventar;



    /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Přidá inventář o velikosti 2.
     */
    public HerniPlan() {
        zalozProstorHry();
        inventar = new Inventar(2);
    }


    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví pole.
     */
    private void zalozProstorHry() {
        //zakládání prostorů, každý má název a jednoduchý popis
        //některé prostory jsou zamčené a odemknou se až po provední nějaké akce
        Prostor pole = new Prostor( "pole", "Pole vedle města Kothal, nejlepší bude se vydat k bráně do města...");
        Prostor brana = new Prostor("brana", "Brána do Kotharu, u vztupu je nějaký úředník."
                + "\nAsi bude potřeba si s ním promluvit než tě pustí...");



        Prostor centrum = new Prostor("centrum", "Centrum je křižovatkou celého města, dá se odsud dostat kamkoliv."
        + "\nTaké je tu spousta lidí, skus se s nimi pobavit, třeba ti někdo z nich poradí.");
        centrum.zamknout(true, "Úředník: Kam si myslíš, že jdeš, prvně pojď sem a já si tě zapíšu.");

        Prostor bar = new Prostor("bar", "Kotharský bar, člověk zde najde řadu zvláštních existencí, ale třeba i nějakou pomoc.");
        Prostor zadni_mistnost = new Prostor("zadni_mistnost", "Jsi v zadní místnosti, doufej, že tě tu nikdo nenanjde, neměl bys tu být...");
        zadni_mistnost.zamknout(true, "Aby ses dostal do zadní místnosti, potřebuješ klíč.");



        Prostor trh = new Prostor("trh", "Na trhu člověk najde spoustu zajímavých věcí, když se budeš koukat pořádně, narazíš i na ilegální část trhu...");
        trh.zamknout(true, "Úředník: Kam si myslíš, že jdeš, prvně pojď sem a já si tě zapíšu.");
        Prostor ferruv_dum = new Prostor("ferruv_dum", "Dům obávaného Ferry, dávej pozor, aby Ferra nezjistil, že jsi tu byl...");
        ferruv_dum.zamknout(true, "Luke: Dům je zamčený, bez nějakého klíče se tam nedostanu.");
        Prostor puda = new Prostor("puda", "Půda Ferrova domu, schválně ,co si tu Ferra schovává...");

        Prostor chram = new Prostor("chram", "Bývalý Jedijský chrám, od té doby, co planetu převzalo imperium tu nikdo dlouho nebyl...");
        Prostor sklep = new Prostor("sklep", "V tomto sklepě už dlouho nikdo nebyl, třeba zde bude něco zajímavého...");


        Prostor dlouha_ulice = new Prostor("dlouha_ulice", "Dlouhá ulice vede směrem k imperiální čtvrti, dále bych raději bez zbraně nechodil...");

        Prostor imperialni_ctvrt = new Prostor("imperialni_ctvrt", "V imperiální čtvrti se to hemží Stormtroopery, modli se, aby tě nepoznali...");
        imperialni_ctvrt.zamknout(true, "Stormtroopeři: Ještě krok a bude z tebe jen hromádka prachu. Dále mohou jen stormtroopeři.");

        Prostor tunel = new Prostor("tunel", "Tunel vede směrem k hangáru a zbrojírně, dávej pozor, ať na tebe něco nevyskočí.");
        Prostor hangar = new Prostor("hangar", "Imperiální hangár, třeba tu najdeš něco, čím můžeš odletět pryč.");
        hangar.zamknout(true, "Luke: Teďka se do hangáru nedostanu, vypadá to, že potřebuji nějakou přístupovou kartu.");

        Prostor tajna_mistnost = new Prostor("tajna_mistnost", "Tajná místnost imperiálů, najdeš zde obrovské množství kreditů.");
        tajna_mistnost.zamknout(true, "Luke: Místnost je zamčená, vypadá to, že je pořeba nějaká id karta.");
        Prostor zbrojirna = new Prostor("zbrojirna", "Imperiální zbrojírna, nachází se tu různé druhy zbraní a výbušnin.");
        zbrojirna.zamknout(true, "Stormtroopeři: Jestli se chceš dostat do zbrojírny budeš muset přez nás.");
        Prostor pruchod = new Prostor("pruchod", "Průchod do kontrolní místnosti, dávej pozor, na Deathtroopery."
                                        + "\nBudeš se s nimi muset vypořádat, než tě pustí dál."
                                        + "\nDoporučuji detonátory z imperiální zbrojírny.");
        Prostor kontrolni_mistnost = new Prostor("kontrolni_mistnost", "Kontolní místnost imperiálů, někde tu najít kartu od hangáru a můžeš pryč.");
        kontrolni_mistnost.zamknout(true, "Deathtroopeři: Nás bys musel snad odbouchnout, kdyby ses tam chtěl dostat.");

        Prostor nakladni_lod = new Prostor("nakladni_lod", "Luke odletěl z této prašivé planety.");


        //průchody mezi prostory
        pole.setVychod("brana",brana);
        brana.setVychod("centrum", centrum);
        brana.setVychod("trh", trh);

        trh.setVychod("centrum" ,centrum);
        trh.setVychod("ferruv_dum", ferruv_dum);
        ferruv_dum.setVychod("trh", trh);
        ferruv_dum.setVychod("puda", puda);
        puda.setVychod("ferruv_dum", ferruv_dum);
        trh.setVychod("chram",chram);
        chram.setVychod("trh", trh);
        chram.setVychod("sklep", sklep);
        sklep.setVychod("chram", chram);




        centrum.setVychod("trh", trh);
        centrum.setVychod("dlouha_ulice", dlouha_ulice);
        centrum.setVychod("bar", bar);
        bar.setVychod("centrum", centrum);
        bar.setVychod("zadni_mistnost", zadni_mistnost);
        zadni_mistnost.setVychod("bar",bar);

        dlouha_ulice.setVychod("centrum", centrum);
        dlouha_ulice.setVychod("imperialni_ctvrt", imperialni_ctvrt);


        imperialni_ctvrt.setVychod("tunel",tunel);
        tunel.setVychod("imperialni_ctvrt", imperialni_ctvrt);
        tunel.setVychod("zbrojirna", zbrojirna);
        zbrojirna.setVychod("tunel",tunel);
        tunel.setVychod("hangar",hangar);
        hangar.setVychod("tunel",tunel);
        hangar.setVychod("nakladni_lod", nakladni_lod);
        nakladni_lod.setVychod("hangar", hangar);

        imperialni_ctvrt.setVychod("tajna_mistnost", tajna_mistnost);
        tajna_mistnost.setVychod("imperialni_ctvrt", imperialni_ctvrt);

        imperialni_ctvrt.setVychod("pruchod",pruchod);
        pruchod.setVychod("imperialni_ctvrt", imperialni_ctvrt);
        pruchod.setVychod("kontrolni_mistnost",kontrolni_mistnost);
        kontrolni_mistnost.setVychod("pruchod", pruchod);

        //hra začíná na poli
        aktualniProstor = pole;


        //zakládání věcí (předmětů) - každá má název a jestli je sebratelná
        // a kreditů (herní měna) - kredity mají ještě hodnotu
        Kredity kredity_pole = new Kredity("ztracene_kredity", true, 500);
        Kredity kredity_centrum = new Kredity("penezenka", true, 1000);
        Kredity kredity_mistnost = new Kredity("hromadka_kreditu", true, 3000);
        Kredity kredity_ulice = new Kredity("kredity_na_zemi", true, 750);
        Kredity kredity_imperial = new Kredity("hora_kreditu", true, 3000);
        Kredity kredity_imperial2 = new Kredity("zabavene_kredity", true, 1500);
        Kredity kredity_imperial3 = new Kredity("par_kreditu", true, 400);


        int cenaBarman = 200;
        Kredity kredity_barman = new Kredity("kredity_barman", true, cenaBarman);
        int ziskaneOpilec = 300;
        Kredity kredity_opilec = new Kredity("kredity_opilec", true, ziskaneOpilec);
        int cenaDealer = 500;
        Kredity kredity_dealer = new Kredity("kredity_dealer", true, cenaDealer);
        int ziskaneObchodnik = 3000;
        Kredity kredity_obchodnik = new Kredity("kredity_obchodnik", true, ziskaneObchodnik);




        Vec sklenka_prow = new Vec("sklenka_prow", true);
        Vec deathsticky = new Vec("deathsticky", true);
        Vec batoh = new Vec("batoh", true);
        Vec kyber_krystal = new Vec("kyber_krystal", true);
        Vec detonatory = new Vec("detonatory", true);
        Vec svetelny_mec = new Vec("svetelny_mec", true);


        Vec id_karta = new Vec("id_karta", true);
        Vec klic_bar = new Vec("klic_bar", true);
        Vec klic_ferra = new Vec("klic_ferra", true);
        Vec pristupova_karta = new Vec("pristupova_karta", true);


        //přidání věcí do prostoru
        pole.addVec(kredity_pole);

        centrum.addVec(kredity_centrum);
        zadni_mistnost.addVec(kredity_mistnost);

        ferruv_dum.addVec(kredity_centrum);
        puda.addVec(klic_bar);
        puda.addVec(detonatory);

        sklep.addVec(batoh);
        sklep.addVec(kyber_krystal);


        zadni_mistnost.addVec(id_karta);


        dlouha_ulice.addVec(kredity_ulice);

        tajna_mistnost.addVec(kredity_imperial);
        tajna_mistnost.addVec(kredity_imperial2);
        tajna_mistnost.addVec(kredity_imperial3);

        zbrojirna.addVec(detonatory);
        zbrojirna.addVec(kredity_ulice);
        kontrolni_mistnost.addVec(pristupova_karta);
        kontrolni_mistnost.addVec(kredity_imperial3);


        //zakládání osob, každá má jméno a rozhovor, při příkazu mluv osoba
        //některé osoby mohou vyměňovat věci či kredity při zadání příkazu dej
        Osoba farmar = new Osoba("farmar", "Farmář: Hochu já ti s ničím nepomůžu, zkus se zeptat ve městě.", "Farmář: Já toto nechci.");
        Osoba urednik = new Osoba("urednik", "Úředník: Jak se jmenuješ? \nLuke? \nA dál? \nSkywalker. \nDobrá tedy, můžeš jít.",
                "Úředník: Nesnaž se mne uplatit.");

        Osoba pilot = new Osoba("pilot", "Pilot: Promiň, já už nelétám, loď mi vzali imperiálové, teď rezne v imperiálním hangáru."
        , "Pilot: Toto si nech, já nic nechci");
        Osoba dealer = new Osoba("dealer", "Dealer: Dám ti deathsticky, jeden šup za " + cenaDealer + " kreditů,bereš?",
                "Dealer: Beru jen kredity.");
        dealer.addVymena(deathsticky, kredity_dealer, "Dealer: Dám ti deathsticky, jeden šup za "+ cenaDealer +  " kreditů, bereš?",
                "Dealer: Přeji skvělou zábavu...", "Dealer: Sorry, ale už došlo zboží.", false);
        dealer.setCenaVeci(cenaDealer);
        Osoba blazen = new Osoba("blazen", "Blazen: Hahhah...Oni staví další hvězdu smrti, a ješteee většíiíií...",
                "Blazen: Jdi pryčččs, jsii s nimy, zráaadce...hahahahaaa");
        Osoba obchodnik = new Osoba("obchodnik", "Obchodník: Kupuji krystaly, jsem ochotný dát až " + ziskaneObchodnik +
                " imperiálních kreditů.", "Obchodník: Děkuji, ale o toto zboží nemám zájem.");
        obchodnik.addVymena(kredity_obchodnik, kyber_krystal, "Obchodník: Omlouvám se, dnes už mám zavřeno, můžete jít třeba do baru."
        , "Obchodnik: Ooo...výtečně, nádherný krystal, tady vemte si " + ziskaneObchodnik +" kredtů.", "Obchodník: Omlouvám se, " +
                        "dnes už mám zavřeno, můžete jít třeba do baru.", false);
        Osoba trendosian = new Osoba("trendosian", "Trendosian: Nevíš, kde tu je bar, hledám Bosska.", "Trendosian: sssrrrs.");

        Osoba obiwanuv_duch = new Osoba("obi-wanuv_duch", "Obi-Wan: Luku, najdi svůj světelný meč a dostaň se do imperiální čtvrti."
        + "\nTam najdeš způsob, jak z této planety uniknout.", "Obi-Wan: Ať tě provází síla.");

        Osoba bossk = new Osoba("bossk", "Bossk: Luke Skywalker...tebe bych tady nečekal." +
                "\nNormálně bych tě udal, ale teď udělám výjimku.\nNáhodou jsem našel tvůj světelný meč, když mi seženeš nějaké detonátory," +
                "tak ti ho vrátím.", "Bossk: Tohle si strč někam.");
        bossk.addVymena(svetelny_mec, detonatory, "Bossk: Dám ti radu, není to tu bezpečné, zmiz.", "Bossk: Výborně, tady máš ten svůj mečík.",
                "Bossk: Díky, ale to co mám už mi stačí.", false);


        Osoba opilec = new Osoba("opilec", "Opilec: Tyjo co bych teďka dal za sklenku prow!!", "Opilec: S tímhletím si jdi za jinými.");
        opilec.addVymena(kredity_opilec, sklenka_prow, "Opilec: Mám pro tebe radu, jestli se chceš někam dostat, musíš být Stormtrooper, " +
                        "v imperiální čtvti tě zaškolí.", "Opilec: Moc děkuju, tady máš " + kredity_opilec + " kreditů.",
                "Opilec: Díky, ale já už měl dost.",  false);

        Osoba barman = new Osoba("barman", "Barman: Mohu nabídnout prow za " + cenaBarman + " kreditů.", "Barman: Omlouvám se, ale bereme jenom imperiální kredity.");
        barman.addVymena(sklenka_prow, kredity_barman, "Barman: Mohu nabídnout prow za " + cenaBarman + " kreditů.",
                "Barman: Tak tady to je.", "Barman: Omlovám se, ale prow už došla.", false);
        barman.setCenaVeci(cenaBarman);



        Osoba ferra = new Osoba("ferra", "Ferra: Jdi o de mě pryč nebo si skočím domů a odpálím tě detonátorem.", "Jdi mi z očí.");
        Osoba zavisly = new Osoba("zavisly", "Závislý: Potřebuju deathstickyyy...\nDám ti za ně tenhle klíč, rychle sežeň mi je.",
                "Závislý: To nejsou deathsticky, já chichchi...chci deathstickyyy.");
        zavisly.addVymena(klic_ferra, deathsticky, "Závislý: Ty klíče jsou od jednoho domu u trrrrhu, jdii tam",
                "Závislý: Joo, tohlee jsem potřebovaal, tady je klííččs.", "Závislý: Jdii pryč, už nechci.", false);
        Osoba cestovatel = new Osoba("cestovatel", "Cestovatel: Tady je to hrozné, všude divní lidé, je tu jen nějaký opuštěný chrám" +
                "\na pak ten hloupý bar, kde mají jen praw, vůbec jsem sem neměl jezdit.", "Cestovatel: Jdi pryč, já to tu přežiju sám.");
        Osoba veteran = new Osoba("veteran", "Veterán: Viděl jsi to nové brnění pro Stormtroopery, vypadá to, že ho prorazí jen světelný meč.",
                "Veterán: Nic mi nedávej, já už nic nepotřebuji.");

        Osoba stormtrooperi1 = new Osoba("stormtrooperi", "Stormtroopeři: Radši bys měl jít pryč, než se ti něco stane.",
                "Stormtroopeři: Nesnaž se nás uplatit nebo skončíš tady v příkopu.");
        Osoba stormtrooperi2 = new Osoba("stormtrooperi", "Stormtroopeři: Buď ticho a dej ruce za hlavu.",
                "Stormtroopeři: Teď už je na domlouvání pozdě.");
        Osoba deathtrooperi = new Osoba("deathtrooperi", "Deathtroopeři: Na výmluvy je pozdě, teď tě vezmeme k Vaderovi.",
                                        "Deathtroopeři: Nás neuplatíš ty špíno.");
        Osoba imperial = new Osoba("imperial", "Imperiál: Ne, prosím nech mne na pokoji vem si tu přístupovou kartu a jdi pryč."
        , "Imperiál: Já nic nechci, tu kartu si vem a neubližuj mi prosím.");

        //přidání osob do herního plánu
        pole.addOsoba(farmar);
        brana.addOsoba(urednik);

        trh.addOsoba(pilot);
        trh.addOsoba(dealer);
        trh.addOsoba(blazen);
        trh.addOsoba(trendosian);
        trh.addOsoba(obchodnik);

        chram.addOsoba(obiwanuv_duch);

        centrum.addOsoba(ferra);
        centrum.addOsoba(zavisly);
        centrum.addOsoba(cestovatel);
        centrum.addOsoba(veteran);

        bar.addOsoba(bossk);
        bar.addOsoba(opilec);
        bar.addOsoba(barman);


        dlouha_ulice.addOsoba(stormtrooperi1);
        tunel.addOsoba(stormtrooperi2);
        pruchod.addOsoba(deathtrooperi);
        kontrolni_mistnost.addOsoba(imperial);
    }
    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */
    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }

    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
        aktualniProstor = prostor;
    }

    /**
     *  Metoda vrací odkaz na inventář, který hráč má u sebe.
     *
     *@return     inventar
     */
    public Inventar getInventar() {
        return inventar;
    }


}
