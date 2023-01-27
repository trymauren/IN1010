class MilitResept extends Hvit{
  public MilitResept(
  Legemiddel legemiddel, Lege utskrivendeLege, int pasientID, int reit){
    super(legemiddel, utskrivendeLege, pasientID, reit);
  }
  public String toString(){
    return "Resept: " + legemiddel + ", utskrivende lege: " + utskrivendeLege +
    ", pasientID: " + pasientID + ", antall resepter igjen: " + reit + ".";
  }
  public int prisAaBetale(){
    return 0;
  }
}
