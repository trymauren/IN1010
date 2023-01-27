class MResept extends Hvit {
    public MResept(Legemiddel legemiddel,Lege utskrivendeLege, Pasient pasient, int reit){
        super(legemiddel, utskrivendeLege, pasient, reit);
    }
    public float prisAaBetale(){
        return 0;
    }
    
    @Override
    public String farge(){
        return "Militaer-resept";
    }
    String newLine = System.getProperty("line.separator");
    
    // @Override
    // public String toString(){
    //     return "------------" + farge() + "------------" + newLine + "Legemiddel: " + legemiddel + newLine +
    //     "Lege: " + utskrivendeLege + newLine + "Pasient: " + pasient + " Reit: " + reit + newLine;
    // }

    // @Override
    // public toString() {
    //      return Id +"\t"+ pasient +", "+ legemiddel +", "+ utskrivendeLege +", "+ reit;
    // }

    @Override
    public String txtVennlig(){
        return legemiddel.hentID() + "," + utskrivendeLege.hentNavn() + "," + pasient.hentID() + "," + "militaer" + "," + reit;
    }
}
