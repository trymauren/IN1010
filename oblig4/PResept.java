class PResept extends Hvit {
    public PResept(Legemiddel legemiddel,Lege utskrivendeLege, Pasient pasient){
        super(legemiddel, utskrivendeLege, pasient, 3);
    }

    public float prisAaBetale(){
        if(legemiddel.hentPris() >= 108){
            float totPris = legemiddel.hentPris() - 108;
            return totPris;
        }
        else{
            return 0;
        }
    }

    @Override
    public String farge(){
        return "P-resept";
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
        return legemiddel.hentID() + "," + utskrivendeLege.hentNavn() + "," + pasient.hentID() + "," + "p";
    }
}
