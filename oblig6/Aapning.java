import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;

class Aapning extends HvitRute{
  public Aapning(char c, int rad, int kolonne, Labyrint labyrint){
    super(c,rad,kolonne,labyrint);
  }
  public void settNabo(String s, Rute r){
    naboer.put(s,r);
  }
  public void gaa(ArrayList<Tuppel> sti, Rute rute){
    try{
      //System.out.println("["+rad+"]"+""+","+""+"["+kolonne+"]");
      ArrayList<Tuppel> nySti = new ArrayList<>(sti);
      nySti.add(new Tuppel(rad,kolonne));
      labyrint.alleVeier.add(nySti);
      //System.out.println("Ferdig");
    }
    catch(NullPointerException e){
      System.out.println("feil");
    }
  }
}
