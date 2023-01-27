class PResept extends Hvit{
  public PResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientID){
    super(legemiddel, utskrivendeLege, pasientID,3);
  }
  public String toString(){
    return "Resept: " + legemiddel + ", utskrivende lege: " + utskrivendeLege +
    ", pasientID: " + pasientID + ".";
  }

  public int prisAaBetale(){
    int rabatertpris = (legemiddel.hentPris() - 108);
    if(rabatertpris <0){
      rabatertpris = 0;
    }
    return rabatertpris;
  }
}
