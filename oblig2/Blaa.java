import java.lang.Math;

class Blaa extends Resept{
  public Blaa(
  Legemiddel legemiddel, Lege utskrivendeLege, int pasientID, int reit){
    super(legemiddel, utskrivendeLege, pasientID, reit);
  }
  public String toString(){
    return "Resept: " + legemiddel + ", utskrivende lege: " + utskrivendeLege +
    ", pasientID: " + pasientID + ", antall resepter igjen: " + reit + ".";
  }
  public String farge(){
    return "blaa";
  }
  public int prisAaBetale(){
    return (int)(legemiddel.hentPris()*0.25);
  }
}
