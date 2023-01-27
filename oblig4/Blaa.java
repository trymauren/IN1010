class Blaa extends Resepter {
    public Blaa(Legemiddel legemiddel,Lege utskrivendeLege, Pasient pasient, int reit){
        super(legemiddel, utskrivendeLege, pasient, reit);
    }
    
    @Override
    public String farge(){
        return "Blaa";
    }

    @Override
    public float prisAaBetale(){
        float nyPris = (float) Math.round(legemiddel.hentPris()*0.25);
        return nyPris;
    }
    String newLine = System.getProperty("line.separator");

    // @Override
    // public String toString(){
    //     return "------------" + farge() + "------------" + newLine + "Legemiddel: " + legemiddel + newLine +
    //     "Lege: " + utskrivendeLege + newLine + "Pasient: " + pasient + " Reit: " + reit + newLine;
    // }
    // @Override
    // public String toString() {
    //     //  return Id +"\t"+ pasient +", "+ legemiddel +", "+ utskrivendeLege +", "+ reit;
    //     return legemiddel;
    // }
    @Override
    public String txtVennlig(){
        return legemiddel.hentID() + "," + utskrivendeLege.hentNavn() + "," + pasient.hentID() + "," + "blaa" + "," + reit;
    }
}
