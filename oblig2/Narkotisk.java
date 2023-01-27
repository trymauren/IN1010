class Narkotisk extends Legemiddel{
  public Narkotisk(String navn,int pris, double virkestoff, int styrke){
    super(navn, pris, virkestoff, styrke);
  }
  public int hentNarkotiskStyrke(){
    return styrke;
  }
  public String toString(){
    return super.toString() + ", styrke: " + styrke;
  }
}
