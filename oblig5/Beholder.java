import java.util.HashMap;
import java.util.ArrayList;

class Beholder{

  ArrayList<HashMap<String,SubSekvens>> beholder;
  String navn;
  public Beholder(String navn){
    beholder = new ArrayList<HashMap<String,SubSekvens>>();
    this.navn = navn;
  }

  public void leggTilHM(HashMap<String,SubSekvens> nyHM){
    beholder.add(nyHM);
  }

  public HashMap<String,SubSekvens> fjernHM(){
    return beholder.remove(0);
  }

  public HashMap<String,SubSekvens> hentHM(){
    return beholder.get(0);
  }

  public int hentSize(){
    return beholder.size();
  }

  public static HashMap<String,SubSekvens> leggSammen(HashMap<String,SubSekvens> hm1,
    HashMap<String,SubSekvens>  hm2){
      /*
      Fletter 2 HashMap sammen til 1, ved å legge til alle elementene fra begge,
      og øke antall forekomster for SubSekvens- objektet med 1 hvis den finner
      2 like subsekvenser hos en person
      */
      SubSekvens hentaSub;
      HashMap<String,SubSekvens> nyHM = new HashMap<String,SubSekvens> ();
      for(SubSekvens sub1:hm1.values()){
          hentaSub = hm2.remove(sub1.hentSubSekvens());
          if (hentaSub == null) {
              nyHM.put(sub1.hentSubSekvens(), sub1);
          }
          else {
              int ant = hentaSub.hentForekomster();
              sub1.leggTilForekomster(ant);
              nyHM.put(sub1.hentSubSekvens(), sub1);
          }
      }
      for(SubSekvens sub2:hm2.values()) {
          nyHM.put(sub2.hentSubSekvens(),sub2);
      }
      return nyHM;
  }
  public String hentNavn(){
    return navn;
  }
}
