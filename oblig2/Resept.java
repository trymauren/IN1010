abstract class Resept{

  private static int statiskID = 1;
  protected Legemiddel legemiddel;
  protected Lege utskrivendeLege;
  protected int pasientID;
  protected int reit;
  protected int id;

  Resept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientID, int reit){
    id = statiskID;
    statiskID = statiskID+1;
    this.legemiddel = legemiddel;
    this.utskrivendeLege = utskrivendeLege;
    this.pasientID = pasientID;
    this.reit = reit;
  }
  public int hentID(){
    return id;
  }
  public Legemiddel hentLegemiddel(){
    return legemiddel;
  }
  public Lege hentLege(){
    return utskrivendeLege;
  }
  public int hentPasientID(){
    return pasientID;
  }
  public int hentReit(){
    return reit;
  }
  public boolean bruk(){
    if(reit > 0){
      reit -= 1;
      return true;
    }
    else{
      return false;
      }
  }
  abstract public String farge();

  abstract public int prisAaBetale();
}
