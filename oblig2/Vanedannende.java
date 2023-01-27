class Vanedannende extends Legemiddel{
  public Vanedannende(String navn,int pris, double virkestoff, int styrke){
    super(navn, pris, virkestoff, styrke);
  }
  public int hentVanedannendeStyrke(){
    return styrke;
  }
  public String toString(){
    return super.toString() + ", styrke: " + styrke;
  }
}
