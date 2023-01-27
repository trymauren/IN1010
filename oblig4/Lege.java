class Lege extends Person implements Comparable<Lege>{
    protected String legeNavn;
    protected Lenkeliste<Resepter> utskrevedeResepter;


    public Lege(String navn) {
        legeNavn = navn;
        utskrevedeResepter = new Lenkeliste<Resepter>();
    }
    public Lenkeliste<Resepter> utskrevedeResepter(){
        return utskrevedeResepter;
    }

    public Lenkeliste<Resepter> hentReseptListe(){
        /* Et alias for utskrevedeResepter() med generelt navn,
        som kan kalles for både leger og pasienter. */
        return utskrevedeResepter();
    }

    public String hentNavn(){
        return legeNavn;
    }

    @Override
    public int compareTo(Lege annenLege){
        String annetNavn = annenLege.hentNavn();
        return legeNavn.compareTo(annetNavn);
    }

    public Hvit skrivHvitResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift{
        if (legemiddel instanceof Narkotisk && !(this instanceof Spesialist)) {
            throw new UlovligUtskrift(this,legemiddel);
        }
        Hvit nyHvit = new Hvit(legemiddel,this,pasient,reit);
        utskrevedeResepter.leggTil(nyHvit);
        pasient.leggTilResept(nyHvit);
        return nyHvit;
    }

    public MResept skrivMillitaerResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift{
        if (legemiddel instanceof Narkotisk && !(this instanceof Spesialist)) {
            throw new UlovligUtskrift(this,legemiddel);
        }
        MResept nyMResept = new MResept(legemiddel, this, pasient, reit);
        utskrevedeResepter.leggTil(nyMResept);
        pasient.leggTilResept(nyMResept);
        return nyMResept;
    }

    public PResept skrivPResept(Legemiddel legemiddel, Pasient pasient) throws UlovligUtskrift{
        if (legemiddel instanceof Narkotisk && !(this instanceof Spesialist)) {
            throw new UlovligUtskrift(this,legemiddel);
        }
        PResept nyPResept = new PResept(legemiddel,this,pasient);
        utskrevedeResepter.leggTil(nyPResept);
        pasient.leggTilResept(nyPResept);
        return nyPResept;
    }

    public Blaa skrivBlaaResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift{
        if (legemiddel instanceof Narkotisk && !(this instanceof Spesialist)) {
            throw new UlovligUtskrift(this,legemiddel);
        }
        Blaa nyBlaa = new Blaa(legemiddel,this,pasient,reit);
        utskrevedeResepter.leggTil(nyBlaa);
        pasient.leggTilResept(nyBlaa);
        return nyBlaa;
    }

        @Override
    public String toString(){
        return legeNavn;
    }

    public String txtVennlig(){
        return legeNavn + "," + "0";
    }
}
