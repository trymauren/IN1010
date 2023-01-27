import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

public class Legesystem {

    private static SortertLenkeliste<Lege> alleLeger = new SortertLenkeliste<Lege>();
    private static Lenkeliste<Pasient> allePasienter = new Lenkeliste<Pasient>();
    private static Lenkeliste<Legemiddel> alleLegemidler = new Lenkeliste<Legemiddel>();
    private static Lenkeliste<Resepter> alleResepter = new Lenkeliste<Resepter>();
    public static Scanner scanner = new Scanner(System.in);
    public static int antFeil = 0;
    public static void main(String [] args){
        menyValg();
    }

    public static void menyValg(){
        String valg = "";

        while(!valg.equals("7")){
            System.out.println("\nMeny \n" + "Velg et nummer for aa gjennomfoere handlingen:");
            System.out.println("1. Opprett \n" + "2. Skriv ut alt \n" + "3. Skriv ut noe \n"
            + "4. Se resepter \n" + "5. Les inn fra fil \n" + "6. Skriv til fil \n" + "7. Avslutt\n");
            
            
            System.out.print("Velg hva vil du gjøre:\n");
            valg = scanner.nextLine();

            switch(valg){
                
                case "1":
                    opprett();
                    break;

                case "2":
                    skrivutAlt();
                    break;

                case "3":
                    skrivutNoe();
                    break;

                case "4":
                    seResept();
                    break;

                case "5":
                    lesfrafil();
                    break;

                case "6":
                    skrivTilFil();
                    break;

                case "7":
                    System.exit(1);
                    break;

                default:
                System.out.println("Ugyldig input");
                break;
                
            }
        }
    }


    public static void lesfrafil(){
        /* Skal lese inn fra ei fil med formatet ... 
        - 1
        -2 */ 
        try{
            System.out.println("Skriv inn navnet på filen du vil lese inn: ");
            String filnavn = scanner.nextLine();
            File fil = new File(filnavn);
            Scanner lesFil = new Scanner(fil);
            lesFil.nextLine();
                try{
                    // Denne metoden leser pasienter fra fil
                    // Hvis linja ikke inneholder #:
                    String linje = lesFil.nextLine();
                    while(!(linje.contains("#"))){
                        // Først kommer info om pasienter. Vi legger dette inn i allePasienter:
                        String pasient = linje;
                        String[] pasientInfo = pasient.split(",");
                        String pasientNavn = pasientInfo[0];
                        String pasientFNR = pasientInfo[1];
                        Pasient nyPasient = new Pasient(pasientNavn, pasientFNR);
                        //Legger pasienten til i lenkelisten allePasienter
                        allePasienter.leggTil(nyPasient);
                        linje = lesFil.nextLine();
                    }
                }
                catch(Exception InvalidParameterException){
                    System.out.println(InvalidParameterException);
                }

                //Leser linjen med # i
                try{
                    //Denne loopen leser legemidler fra fil
                    //Så lenge det ikke er # i neste linje
                    String linje = lesFil.nextLine();
                    while(!(linje.contains("#"))){
                        String legemiddel = linje;
                        int elementIndeks = 0;

                        // Legg hvert element inn i et array:
                        String[] legemiddelInfo = legemiddel.split(",");

                        // Sjekk hvilket element som er type legemiddel, lagre posisjonen:
                        int typePosisjon = 0;
                        for (int i = 0; i < legemiddelInfo.length; i++) {
                            if (legemiddelInfo[i].equalsIgnoreCase("vanlig") 
                                || legemiddelInfo[i].equalsIgnoreCase("vanedannende")
                                || legemiddelInfo[i].equalsIgnoreCase("narkotisk")) {
                                    typePosisjon = i;
                            }
                        }

                        // Legg første element inn i navn:
                        String navn = legemiddelInfo[elementIndeks++];

                        // Hvis type ikke kommer på posisjon 1, legg sammen elementene 
                        // før typePosisjon som ett nytt element:
                        if (typePosisjon > 1) {
                            for (int i = 1; i < typePosisjon; i++) {
                                navn += "," + legemiddelInfo[elementIndeks++];
                            }    
                        }

                        // Fortsett med å legge til resten av elementene:
                        String legemiddelType = legemiddelInfo[elementIndeks++];

                        // Cast alle verdiene som ikke er string over til deres rette type
                        String legemiddelPris = legemiddelInfo[elementIndeks++];
                        float pris = Float.parseFloat(legemiddelPris);
                        String legemiddelVStoff = legemiddelInfo[elementIndeks++];
                        double virkestoff = Double.parseDouble(legemiddelVStoff);

                        //Finner ut hvilken type legemiddel det er snakk om, lager et objekt av riktig type
                        //Legger det deretter til i lenkelisten alleLegemidler
                        if(legemiddelType.toLowerCase().equals("narkotisk")){
                            String legemiddelStyrke = legemiddelInfo[elementIndeks++];
                            int styrke = Integer.parseInt(legemiddelStyrke);
                            Narkotisk nyttLegemiddel = new Narkotisk(navn, pris, virkestoff, styrke);
                            //System.out.println(nyttLegemiddel + "Narkotisk");
                            alleLegemidler.leggTil(nyttLegemiddel);
                            //System.out.println(nyttLegemiddel);
                            linje = lesFil.nextLine();
                        }
                        else if(legemiddelType.toLowerCase().equals("vanedannende")){
                            String legemiddelStyrke = legemiddelInfo[elementIndeks++];
                            int styrke = Integer.parseInt(legemiddelStyrke);
                            Vanedannende nyttLegemiddel = new Vanedannende(navn, pris, virkestoff, styrke);
                            //System.out.println(nyttLegemiddel + "Vanedannende");
                            alleLegemidler.leggTil(nyttLegemiddel);
                            //System.out.println(nyttLegemiddel);
                            linje = lesFil.nextLine();
                        }
                        else if(legemiddelType.toLowerCase().equals("vanlig")){
                            Vanlig nyttLegemiddel = new Vanlig(navn, pris, virkestoff);
                            //System.out.println(nyttLegemiddel + "Vanlig");
                            alleLegemidler.leggTil(nyttLegemiddel);
                            //System.out.println(nyttLegemiddel);
                            linje = lesFil.nextLine();
                        }                              
                        else{
                            lesFil.nextLine();
                        }
                    }
                }
                catch(Exception InvalidParameterException){
                    System.out.println(InvalidParameterException);
                }
                
                try{
                    //Denne loopen leser leger fra fil
                    //Så lenge det ikke er # i neste linje
                    String linje = lesFil.nextLine();
                    while(!(linje.contains("#"))){
                        String lege = linje;
                        String[] legeInfo = lege.split(",");
                        String navn = legeInfo[0];
                        String kontrollId = legeInfo[1];
                        //Sjekker om kontrollID == 0, da skal det lages vanlig lege
                        //Legger deretter legen til den sorterte lenkelisten alleLeger
                        if(kontrollId.equals("0")){
                            Lege nyLege = new Lege(navn);
                            alleLeger.leggTil(nyLege);
                            linje = lesFil.nextLine();
                        }
                        //Hvis kontrollID er noe annet enn 0, vil det lages spesialist
                        //Legger deretter legen til den sorterte lenkelisten alleLeger
                        else{
                            Spesialist nySpesialist = new Spesialist(navn, kontrollId);
                            alleLeger.leggTil(nySpesialist);
                            linje = lesFil.nextLine();
                        }
                    }
                }
                catch(Exception InvalidParameterException){
                    System.out.println(InvalidParameterException);
                }

                try{
                    //Denne loopen leser resepter fra fil
                    //Så lenge det ikke er # i neste linje
                    String linje = lesFil.nextLine();
                    while(linje != null){
                        String resept = linje;
                        String[] reseptInfo = resept.split(",");
                        /*Plukker ut og caster verdiene over til riktig type, slik at de kan
                        brukes som parametere i reseptobjekter*/
                        String legemiddelNummer = reseptInfo[0];
                        int legemiddelNr = Integer.parseInt(legemiddelNummer);
                        String legeNavn = reseptInfo[1];
                        String pasID = reseptInfo[2];
                        int pasientID = Integer.parseInt(pasID);
                        String reseptType = reseptInfo[3];

                        //Finner og plukker ut "riktig" legeobjekt fra legelista
                        //, for å opprette reseptobjekt på legeobjekt (som allerede ligger i lista)
                        Lege riktigLege = new Lege("");
                        for(Lege lege:alleLeger){
                            if(legeNavn.equals(lege.hentNavn())){
                                riktigLege = lege;
                            }
                        }
                        //Finner og plukker ut riktig legemiddelobjekt fra legemiddellista
                        //, for å opprette reseptobjekt på legemiddelobjekt (som allerede ligger i lista)
                        Legemiddel riktigLegemiddel = new Vanlig("", 0, 0);
                        for(Legemiddel lm:alleLegemidler){
                            if(legemiddelNr == lm.hentID()){
                                riktigLegemiddel = lm;
                            }
                        }
                        //Finner og plukker ut riktig pasientobjekt fra pasientlista
                        //, for å opprette reseptobjekt på legemiddelobjekt (som allerede ligger i lista)
                        Pasient riktigPasient = new Pasient("", "");
                        for(Pasient pasient:allePasienter){
                            if(pasientID == pasient.hentID()){
                                riktigPasient = pasient;
                            }
                        }

                        //Finner ut hvilken type resept som skal lages, og
                        // oppretter nytt objekt med riktig type
                        if(reseptType.toLowerCase().equals("blaa")){
                            //System.out.println(riktigLege + "blaa" + riktigLegemiddel);
                            int reit = Integer.parseInt(reseptInfo[4]);
                            Blaa nyBlaa = null;
                            try{
                                nyBlaa = riktigLege.skrivBlaaResept(riktigLegemiddel, riktigPasient, reit);
                                alleResepter.leggTil(nyBlaa);
                            }
                            catch(UlovligUtskrift e){
                                System.out.println(e);
                                antFeil++;
                            }
                        }
                        else if(reseptType.toLowerCase().equals("hvit")){
                            int reit = Integer.parseInt(reseptInfo[4]);
                            Hvit nyHvit = null;
                            try{
                                nyHvit = riktigLege.skrivHvitResept(riktigLegemiddel, riktigPasient, reit);
                                alleResepter.leggTil(nyHvit);
                            }
                            catch(UlovligUtskrift e){
                                System.out.println(e);
                                antFeil++;
                            }
                        }
                        else if(reseptType.toLowerCase().equals("militaer")){
                            int reit = Integer.parseInt(reseptInfo[4]);
                            MResept nyMilitaer = null;
                            try{
                                nyMilitaer = riktigLege.skrivMillitaerResept(riktigLegemiddel, riktigPasient, reit);
                                alleResepter.leggTil(nyMilitaer);
                            }
                            catch(UlovligUtskrift e){
                                System.out.println(e);
                                antFeil++;
                            }
                        }
                        else if(reseptType.toLowerCase().equals("p")){
                            try{
                                PResept nyPResept = riktigLege.skrivPResept(riktigLegemiddel, riktigPasient);
                                alleResepter.leggTil(nyPResept);
                            }
                            catch(UlovligUtskrift e){
                                System.out.println(e);
                                antFeil++;
                            }
                        }
                        else{
                            lesFil.nextLine();
                        }
                        linje = lesFil.nextLine();
                    }
                }
                catch(Exception InvalidParameterException){}
            lesFil.close();
            }
            catch(FileNotFoundException e){
                System.out.println(e);
                antFeil++;
            }
        System.out.println("Antall ulovlige resepter i innlesning: " + antFeil);
    }

    private static void skrivutAlt(){
        /*
        Skriver ut en oversikt over hele legesystemet. 
        
        Pasient: Id, navn, fødselsnr
        Legemiddel: Id, navn, type, pris, virkestoff
        Lege: navn, kontrollId
        Resept: id, type, legemiddel, lege, reit
        */

        // Lag overskrifter til hver kategori:
        String pasientUtskrift = System.lineSeparator() + "-------------------------------------- Pasienter ---------------------------------------";
        pasientUtskrift += System.lineSeparator() + System.lineSeparator() + "Id \tnavn, fødselsnummer:";
        pasientUtskrift += System.lineSeparator();

        String legemiddelUtskrift = System.lineSeparator() + "-------------------------------------- Legemidler --------------------------------------";
        legemiddelUtskrift += System.lineSeparator() + System.lineSeparator() + "Id \tnavn, type, pris, virkestoff:";
        legemiddelUtskrift += System.lineSeparator();

        String legeUtskrift = System.lineSeparator() + "-------------------------------------- Leger -------------------------------------------";
        legeUtskrift += System.lineSeparator() + System.lineSeparator() + "Navn, kontrollid:";
        legeUtskrift += System.lineSeparator();

        String reseptUtskrift = System.lineSeparator() + "-------------------------------------- Resepter ----------------------------------------";
        reseptUtskrift += System.lineSeparator() + System.lineSeparator() + "Id \ttype, pasient, legemiddel, utskrivende lege, reit:";
        reseptUtskrift += System.lineSeparator();

        // Fyll inn informasjonen for hver kategori:
        for (Pasient pasient : allePasienter) {
            pasientUtskrift += System.lineSeparator() + pasient.toString();
        }
        pasientUtskrift +=  System.lineSeparator();

        for (Legemiddel legemiddel : alleLegemidler) {
            legemiddelUtskrift += System.lineSeparator() + legemiddel.toString();
        }
        legemiddelUtskrift +=  System.lineSeparator();

        for (Lege lege : alleLeger) {
            legeUtskrift += System.lineSeparator() + lege.toString();
        }
        legeUtskrift +=  System.lineSeparator();

        for (Resepter resept : alleResepter) {
             reseptUtskrift += System.lineSeparator() + resept.toString();
        }
        reseptUtskrift +=  System.lineSeparator();

        // Sett sammen alle kategoriene til en string:
        String utskrift = "";
        utskrift += pasientUtskrift;
        utskrift += legemiddelUtskrift;
        utskrift += legeUtskrift;
        utskrift += reseptUtskrift;

        // Skriv ut:
        System.out.println(utskrift);
    }

    private static void opprett(){
        System.out.println("Skriv inn nummeret til hvilken type objekt du vil lage:");
        System.out.println("1.Lege \n2.Pasient \n3.Legemiddel \n4.Resept");
           
            String valgInn = scanner.nextLine();
            int valg = Integer.parseInt(valgInn);

            if(valg == 1){
                System.out.println("Skriv inn navn på lege:");
                String navn = scanner.nextLine();
                
                System.out.println("Er legen spesialist? Ja/ Nei:");
                String valgSpesialist = scanner.nextLine();
                //Og eventuelt om bruker vil lage en lege som er spesialist
                if(valgSpesialist.toLowerCase().equals("ja")){
                    System.out.println("Skriv inn kontrollID:");
                    String kontrollID = scanner.nextLine();
                    Spesialist nySpesialist = new Spesialist(navn,kontrollID);
                        alleLeger.leggTil(nySpesialist);
                }
                else if(valgSpesialist.toLowerCase().equals("nei")){
                    Lege nyLege = new Lege(navn);
                        alleLeger.leggTil(nyLege);
                }
            }

            else if(valg == 2){
                //Henter navn
                System.out.println("Skriv inn navn på pasient:");
                String navn = scanner.nextLine();
                //Henter fodselsnummer
                System.out.println("Skriv inn fødselsnummer på pasient:");
                String fodselsNr = scanner.nextLine();
                //Oppretter pasientobjekt og legger dette inn i lista
                Pasient nyPasient = new Pasient(navn,fodselsNr);
                allePasienter.leggTil(nyPasient);
            }

            else if(valg == 3){
                //Henter navn
                System.out.println("Skriv inn navn på legemiddel:");
                String navn = scanner.nextLine();
                //Henter pris
                System.out.println("Skriv inn pris på legemiddel:");
                float pris = Float.parseFloat(scanner.nextLine());
                //Henter virkestoff
                System.out.println("Skriv inn mengde virkestoff på legemiddel:");
                double virkestoff = Double.parseDouble(scanner.nextLine());
                //Sjekker om legemiddelet er av type vanedannende eller narkotisk,
                //i dette tilfellet skal det leses inn styrke


                System.out.println("Hvilken type legemiddel vil du opprette?:" +
                "Vanlig, vanedannende, eller narkotisk?");
                String type = scanner.nextLine().toLowerCase();
                if(type.equals("vanlig")){
                    Vanlig nyVanlig = new Vanlig(navn,pris,virkestoff);
                    alleLegemidler.leggTil(nyVanlig);
                }

                else if(type.equals("vanedannende")){
                    System.out.println("Skriv inn styrke (heltall):");
                    int styrke = Integer.parseInt(scanner.nextLine());
                    Vanedannende nyVanedannende = new Vanedannende(navn,pris,virkestoff,styrke);
                    alleLegemidler.leggTil(nyVanedannende);
                    

                }
                else if(type.equals("narkotisk")){
                    System.out.println("Skriv inn styrke (heltall):");
                    int styrke = Integer.parseInt(scanner.nextLine());
                    Narkotisk nyNarkotisk = new Narkotisk(navn,pris,virkestoff,styrke);
                    alleLegemidler.leggTil(nyNarkotisk);
                }
                else{
                    System.out.println("Du må velge enten vanlig, vanedannende eller narkotisk."+
                    " går tilbake til hovedmeny");
                    return;
                }
            }

            else if (valg == 4){
                //Hvis bruker forsøker å opprette resept når det ikke eksisterer
                //kastes den tilbake til menyValg()
                if(allePasienter.stoerrelse() == 0){
                    System.out.println("Du forsøkte å opprette resept, men det eksisterer ingen pasienter i systemet.");
                    return;
                }
                else if(alleLeger.stoerrelse() == 0){
                    System.out.println("Du forsøkte å opprette resept, men det eksisterer ingen leger i systemet.");
                    return;
                }
                else if(alleLegemidler.stoerrelse() == 0){
                    System.out.println("Du forsøkte å opprette resept, men det eksisterer ingen legemidler i systemet.");
                    return;
                }
                else{
                    System.out.println("Skriv inn type resept: (blaa, hvit, mresept eller presept");
                    String type = scanner.nextLine();
                    if(!(type.equals("blaa")) && !(type.equals("hvit")) && !(type.equals("mresept")) && !(type.equals("presept"))){
                        System.out.println("Du forsøkte å opprette en type resept som ikke eksisterer. "+
                        " Går tilbake til hovedmeny.");
                        return;
                    }
                    else{
                            lagNyResept(type);
                    }
                }
            }
    }

    private static void lagNyResept(String type){
        //Hjelpemetode til opprettelse av ny resept
        //Leser inn legemiddel, finner dette i lista og setter legeMiddel til å peke på dette
        System.out.println("Skriv inn legemiddel:");
        String legemiddelNavn = scanner.nextLine();
        Legemiddel legemiddel = null;
        for(Legemiddel lm:alleLegemidler){
            if(lm.hentNavn().equals(legemiddelNavn)){
                legemiddel = lm;
            }
        }
        //Hvis
        if(legemiddel == null){
            System.out.println("Dette legemiddelet finnes ikke i systemet. Starter meny på nytt.");  
            return;
        }

        //Leser inn lege, finner dette i lista og setter legeNavn til å peke på dette  
        System.out.println("Skriv inn navn på lege");
        String legeNavn = scanner.nextLine();
        Lege lege = null;
        for(Lege l:alleLeger){
            if(l.hentNavn().equals(legeNavn)){
                lege = l;
            } 
        }
        if(lege == null){
            System.out.println("Denne legen finnes ikke i systemet. Starter meny på nytt.");  
            return;
        }
        


        //Leser inn pasient, finner dette i lista og setter pasientID til å peke på dette  
        System.out.println("Skriv inn ID (heltall) på pasient:");
        int pasientID = Integer.parseInt(scanner.nextLine());
        Pasient pasient = null;
        for(Pasient p:allePasienter){
            if(p.hentID() == pasientID){
                pasient = p;
            }
        }
        if(pasient == null){
            System.out.println("Denne pasienten finnes ikke i systemet. Starter meny på nytt.");  
            return;
        }
        
        
        
        // if(legemiddel == null || lege == null || pasient == null){
        //     menyValg();
        // }
        if(type.equals("blaa")){
        System.out.println("Skriv inn reit (heltall) :");
        int reit = Integer.parseInt(scanner.nextLine());
        try {
            lege.skrivBlaaResept(legemiddel, pasient, reit);
        }
        catch (UlovligUtskrift e){
            System.out.println(e);
            antFeil++;
        //     // System.out.println("Legen har ikke rett til å skrive ut denne resepten.");
        }
        }
        else if(type.equals("hvit")){
            System.out.println("Skriv inn reit (heltall) :");
            int reit = Integer.parseInt(scanner.nextLine());
            try {
                lege.skrivHvitResept(legemiddel, pasient, reit);                    
            } 
            catch (UlovligUtskrift e) {
                System.out.println(e);
                antFeil++;
                // System.out.println("Legen har ikke rett til å skrive ut denne resepten.");
            }
        }
        else if(type.equals("militaer")){
            System.out.println("Skriv inn reit (heltall) :");
            int reit = Integer.parseInt(scanner.nextLine());
            try {
                lege.skrivMillitaerResept(legemiddel, pasient, reit);
            } 
            catch (UlovligUtskrift e) {
                System.out.println(e);
                antFeil++;
                // System.out.println("Legen har ikke rett til å skrive ut denne resepten.");
            }
        }
        else if(type.equals("p")){
            try {
                lege.skrivPResept(legemiddel, pasient);
            } 
            catch (UlovligUtskrift e) {
                System.out.println(e);
                antFeil++;
            }
        } 
    }
    
    private static void skrivutNoe(){
        /* Undermeny for å skrive ut ulik informasjon/statistikk fra systemet. 
        Kaller metodene skrivVanedannende(), skrivNarkotiske(), skrivNarkoLeger()
        og skrivNarkoPasienter(). */
        System.out.println("Hva vil du se data om?");
        System.out.println("1. Vanedannende resepter \n2. Narkotiske resepter \n" 
        + "3. Leger som har skrevet ut narkotiske resepter \n4. Pasienter med narkotiske resepter");
        
        String valg = scanner.nextLine();

        switch(valg){

            case "1":
                skrivVanedannende();
                break;

            case "2":
                skrivNarkotiske();
                break;

            case "3":
                skrivNarkoLeger();
                break;

            case "4":
                skrivNarkoPasienter();
                break;
        }
    }
    
    private static void skrivVanedannende() {
        /* Skriver ut totalt antall resepter på vanedannende legemidler.
        Kaller utskrevneResepterVane() som returnerer det totalt antallet. */

        String utskrift = System.lineSeparator();
        utskrift += "Antall utskrevne resepter på vanedannende legemidler er ";
        utskrift += utskrevneResepterVane();
        System.out.println(utskrift);
    }

    private static void skrivNarkotiske() {
        /* Skriver ut totalt antall resepter på narkotiske legemidler 
        Kaller utskrevneResepterNarko() som returnerer det totalt antallet. */

        String utskrift = System.lineSeparator();
        utskrift += "Antall utskrevne resepter på narkotiske legemidler er ";
        utskrift += utskrevneResepterNarko();
        System.out.println(utskrift);
    }

    private static void skrivNarkoLeger() {
        /* Skriver ut ei liste over leger som har skrevet ut resepter på narkotiske
        legemidler, sammen med antallet slike resepter per lege. */

        // Hent liste over alle leger med narko-resepter: 
        // Lag Lenkeliste-kopi av alleLeger (ikke SortertLenkeliste):
        Lenkeliste<Person> nyLegeListe = new Lenkeliste<Person>();
        for (Person lege : alleLeger){
            nyLegeListe.leggTil(lege);
        }
        Lenkeliste<Person> narkoLeger = narkoPersoner(nyLegeListe);

        String utskrift = System.lineSeparator();
        utskrift += System.lineSeparator() + "Leger som har skrevet ut resepter på narkotiske legemidler.";
        utskrift += System.lineSeparator() + "Navn, antall resepter:" + System.lineSeparator();

        // lagNarkoListe returnerer string med liste over leger og antall resepter:
        utskrift += lagNarkoListe(narkoLeger);
        System.out.println(utskrift);
    }

    private static void skrivNarkoPasienter() {
        // Hent liste over alle pasienter med narko-resepter:
        Lenkeliste<Person> nyPasientListe = new Lenkeliste<Person>();
        for (Person pasient : allePasienter) {
            nyPasientListe.leggTil(pasient);
        }
        Lenkeliste<Person> narkoPasienter = narkoPersoner(nyPasientListe);
        
        String utskrift = System.lineSeparator();
        utskrift += System.lineSeparator() + "Pasienter som har gyldige resepter på narkotiske legemidler: ";
        utskrift += System.lineSeparator() + "Navn, antall resepter:" + System.lineSeparator();

        // lagNarkoListe() returnerer string med liste over pasienter og antall resepter:
        utskrift += lagNarkoListe(narkoPasienter);
        System.out.println(utskrift);
    }

    private static int utskrevneResepterVane(){
        /* Teller gjennom alle resepter, og inkrementerer counter for hver resept
        på et vanedannende legemiddel. Returnerer counter. */

        int counter = 0;
        for(Resepter r : alleResepter){
            if(r.hentLegemiddel() instanceof Vanedannende){
                counter ++;
            }
        }
        return counter;
    }
    
    private static int utskrevneResepterNarko(){
        /* Teller gjennom alle resepter, og inkrementerer counter for hver resept
        på et narkotisk legemiddel. Returnerer counter. */
        
        int counter = 0;
        for(Resepter r : alleResepter){
            if(r.hentLegemiddel() instanceof Narkotisk){
                counter ++;
            }
        }
        return counter;
    }

    private static Lenkeliste<Person> narkoPersoner(Lenkeliste<Person> innListe){
        /* Metode som returnerer ei lenkeliste over alle personer som er 
        knyttet til minst en resept på narkotiske legemidler. Tar ei lenke-
        liste over personer (leger/pasienter) som parameter, looper gjennom
        denne lista, og returnerer ei ny liste hvor bare de som er knytta til
        narkotiske resepter er med.*/

        // Liste som skal returneres:
        Lenkeliste<Person> narkoListe = new Lenkeliste<Person>();

        // Gå gjennom alle resepter på alle personer. Hvis personen er knyttet til
        // en resept på et narkotisk legemiddel, sett harSkrevetUt = true for denne personen.
        boolean harNarkoResept = false;
        for(Person person : innListe){
            harNarkoResept = false;
            for(Resepter resept : person.hentReseptListe()){
                if(resept.hentLegemiddel() instanceof Narkotisk){
                    // Sett harNarkoResept = true dersom en resept er på narkotisk:
                    harNarkoResept = true;
                }
            }

            // Hvis personen har narkotisk resept, legg til personen i narkoListe:
            if(harNarkoResept == true){
                System.out.println(person);
            }
        }
        return narkoListe;
    }

    private static int antallNarko(Person person){
        /* Tar en pasient eller lege som parameter, og returnerer antallet
        resepter på narkotiske legemidler som er knyttet til den personen. 
        Bruker interfacet Person, som implementeres av Lege og Pasient. */

        int antallNarkoResepter = 0;
        
        // Gå gjennom alle resepter på denne personen:
        for(Resepter resept : person.hentReseptListe()){
            if(resept.hentLegemiddel() instanceof Narkotisk){
                antallNarkoResepter ++;
            }
        }
        return antallNarkoResepter;
    }

    private static String lagNarkoListe(Lenkeliste<Person> personer) {
        /* Tar ei lenkeliste med personer som parameter. Returnerer en string, 
        med navn på disse personene og antallet resepter på narkotiske
        legemidler knyttet til hver person, formatert for utskrift. */

        String utskrift = "";
        for (Person person : personer) {
            utskrift += System.lineSeparator() + person.hentNavn() + ": " + antallNarko(person);
            utskrift += " resepter.";
        }
        return utskrift;
    }

    private static void seResept(){
        /* Metode for å velge resept for en pasient. Skriver først ut
        ei liste over alle pasienter, nummerert. Brukeren skriver inn
        nummeret og velger dermed pasienten. Da får brukeren opp ei liste
        over alle resepter på den valgte pasienten. Kaller så brukResept()
        for å velge hvilken resept man skal bruke.
        */

        // Lag utskrifts-liste over alle pasienter:
        String pUtskrift = "";
        String pasientNavn = "";
        int pasientNummer = 1;
        for(Pasient p : allePasienter){
            pasientNavn = p.hentNavn();
            pUtskrift += System.lineSeparator() + pasientNummer + " : "+ pasientNavn;
            pasientNummer++;
        }

        // Be brukeren velge pasient etter indeks:
        System.out.println("Hvilken pasient vil du se resepter for? Tast inn nummer.");
        System.out.println(pUtskrift);
        // Lagre valgt indeks i "valg":
        int pasientValg = Integer.parseInt(scanner.nextLine());

        // Sjekk om nummeret er gyldig:
        if(pasientValg > allePasienter.stoerrelse() || pasientValg < 1){
            System.out.println("Ugyldig nummer");
            return;
        }

        // Hent pasienten på posisjon (pasientValg-1) i allePasienter:
        Pasient valgtPasient = allePasienter.hent(pasientValg-1);
        
        // Hent reseptene til valgt pasient:
        Stabel<Resepter> gyldigeResepter = valgtPasient.hentResepter();

        // Sjekk om pasienten har resepter:
        if(gyldigeResepter.stoerrelse() == 0){
            System.out.println("Ingen gyldige resepter på denne pasienten.");
            return;
        }

        // Lag utskrifts-liste over pasientens resepter:
        int rNummer = 1;
        String rUtskrift = "";
        for(Resepter r : gyldigeResepter){
            Legemiddel rLegemiddel = r.hentLegemiddel();
            rUtskrift += System.lineSeparator() + rNummer + " : "+ rLegemiddel.hentNavn() +", "+ r.hentReit() + "reit";
            rNummer++;
        }

        // Skriv ut spørsmål, hent input og kall metode brukResept:
        System.out.println("Hvilken resept skal brukes? Tast ID.");
        System.out.println(rUtskrift);
        int reseptValg = Integer.parseInt(scanner.nextLine());
        brukResept(valgtPasient,reseptValg-1);
    }

    private static void brukResept(Pasient pasient,int reseptPos){
        /* Metode som bruker en resept. Tar inn en pasient og indeksen til en
        resept som parametre. Reduserer reseptens reit med 1. 
        */

        // Hent resepten:
        Stabel<Resepter> reseptStabel = pasient.hentResepter();
        Resepter resept = reseptStabel.hent(reseptPos);

        // Bruk resept, og sjekk om den har reit igjen:
        if(resept.bruk()){
            String navn = resept.hentLegemiddel().hentNavn();
            System.out.println("Du brukte resepten for " + navn + ", du har " + resept.hentReit() + " reit igjen");
        }
        else{
            System.out.println("Denne resepten har ikke flere reit igjen.");
            return;
        }
    }

    private static void skrivTilFil(){ 
        try{
        
        File tekstFil = new File("utskrift.txt");
        FileWriter filskriver = new FileWriter(tekstFil,true);
        BufferedWriter skriver = new BufferedWriter(filskriver);

        skriver.write("# Pasienter (navn, fnr)" + System.lineSeparator());
        for(Pasient p : allePasienter){
            skriver.write(p.txtVennlig() + System.lineSeparator());
        }

        skriver.write("# Legemidler (navn,type,pris,virkestoff,[styrke])" + System.lineSeparator());
        for(Legemiddel lm : alleLegemidler){
            skriver.write(lm.txtVennlig() + System.lineSeparator());
        }

        skriver.write("# Leger (navn,kontrollid / 0 hvis vanlig lege)" + System.lineSeparator());
        for(Lege l : alleLeger){
            skriver.write(l.txtVennlig() + System.lineSeparator());
        }

        skriver.write("# Resepter (legemiddelNummer,legeNavn,pasientID,type,[reit])" + System.lineSeparator());
        for(Resepter r : alleResepter){
            skriver.write(r.txtVennlig() + System.lineSeparator());
        }

        skriver.close();

        }
        catch(IOException e){
            e.printStackTrace();
            antFeil++;
        }
    }
}
