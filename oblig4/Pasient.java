class Pasient extends Person{

    protected Stabel<Resepter> reseptStabel;
    protected String navn;
    protected static int id;
    protected String fodselsnummer;
    protected int egenID;
    
    public Pasient(String navn, String fodselsnummer){
        id++;
        egenID = id;
        this.navn = navn;
        this.fodselsnummer = fodselsnummer;
        reseptStabel = new Stabel<Resepter>();
    }

    public int hentID(){
        return egenID;
    }

    public String hentNavn(){
        return navn;
    }

    public void leggTilResept(Resepter resept){
        reseptStabel.leggPaa(resept);
    }
    
    public Stabel<Resepter> hentResepter(){
        return reseptStabel;
    }

    public Lenkeliste<Resepter> hentReseptListe(){
        /* Returnerer reseptene til pasienten som Lenkeliste i stedet
        for som Stabel. */ 
        Lenkeliste<Resepter> reseptListe = new Lenkeliste<Resepter>();
        for (Resepter resept : reseptStabel) {
            reseptListe.leggTil(resept);
        }
        return reseptListe;
    }

    public String toString(){
        return String.valueOf(egenID) +"\t"+ navn +", "+ String.valueOf(fodselsnummer);
    }
    //Egen utskriftsmetode for å skrive til fil
    public String txtVennlig(){
        return navn + "," + fodselsnummer;
    }
}