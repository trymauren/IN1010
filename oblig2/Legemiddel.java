abstract class Legemiddel{
  private static int statiskID = 1;
  protected String navn;
  protected int pris;
  protected double virkestoff;
  protected int klasse;
  protected int styrke;
  protected int id;

  //kstr vanlig
  protected Legemiddel(String navn,int pris, double virkestoff){
    id = statiskID;
    statiskID = statiskID+1;
    this.navn = navn;
    this.pris = pris;
    this.virkestoff = virkestoff;
  }
  //kstr narko,vane
  protected Legemiddel(String navn,int pris, double virkestoff, int styrke){
    id = statiskID;
    statiskID = statiskID+1;
    this.navn = navn;
    this.pris = pris;
    this.virkestoff = virkestoff;
    this.styrke = styrke;
  }
  public int hentID(){
    return id;
  }
  public String hentNavn(){
    return navn;
  }
  public int hentPris(){
    return pris;
  }
  public double hentVirkestoff(){
    return virkestoff;
  }
  public void settNyPris(int nypris){
    pris = nypris;
  }
  public String toString(){
    return "Legemiddel: " + navn + ", pris:" + pris + ", mg virkestoff:" + virkestoff;
  }
}
