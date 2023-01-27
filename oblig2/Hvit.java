class Hvit extends Resept{
  public Hvit(Legemiddel legemiddel, Lege utskrivendeLege, int pasientID, int reit){
    super(legemiddel, utskrivendeLege, pasientID, reit);
  }
  public String farge(){
    return "hvit";
  }
  public int prisAaBetale(){
    return legemiddel.hentPris();
  }
}
