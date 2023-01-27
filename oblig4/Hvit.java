class Hvit extends Resepter {
    public Hvit(Legemiddel legemiddel,Lege utskrivendeLege, Pasient pasient, int reit){
        super(legemiddel, utskrivendeLege, pasient, reit);
    }
    
    @Override
    public String farge(){
        return "Hvit";
    }

    @Override
    public float prisAaBetale(){
        return legemiddel.hentPris();
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
        return legemiddel.hentID() + "," + utskrivendeLege.hentNavn() + "," + pasient.hentID() + "," + "hvit" + "," + reit;
    }
}

                    