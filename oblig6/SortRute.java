import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;

class SortRute extends Rute{
  public SortRute(char c, int rad, int kolonne, Labyrint labyrint){
    super(c,rad,kolonne,labyrint);
  }
  @Override
  public char tilTegn(){
    return tegn;
  }
  @Override
  public void finnUtvei(){
    System.out.println("\nStartet på svart rute.");
    return;
  }

  @Override
  public void gaa(ArrayList<Tuppel> sti, Rute rute){};
}
