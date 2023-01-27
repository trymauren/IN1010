import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class HvitRute extends Rute{
  int counter = 0;


  public HvitRute(char c, int rad, int kolonne, Labyrint labyrint){
    super(c,rad,kolonne,labyrint);
    setFarge();
  }
  protected void setFarge(){
    setBackground(Color.WHITE);
    setOpaque(true);
  }
  @Override
  public char tilTegn(){
    return tegn;
  }
  @Override
  public void finnUtvei(){
    /*
    Lager ny ArrayList for en mulig vei fra denne ruta
    */
    ArrayList<Tuppel> nySti = new ArrayList<Tuppel>();
    /*
    Legger til denne rutas indekser til ArrayLista
    */
    nySti.add(new Tuppel(rad,kolonne));
    /*
    Forsøker å gå i retning alle naboer. Sender med foreløpig
    vei, nySti, og denne ruta som parametere
    */
    for(String s:naboer.keySet()){
      Rute rute = naboer.get(s);
      rute.gaa(nySti, this);
    }
  }
  @Override
  protected void gaa(ArrayList<Tuppel> sti, Rute forrige){

    // System.out.println("["+rad+"]"+""+","+""+"["+kolonne+"]");
    /*
    Lager kopi av sti. Kjører gaa() for hver nabo, unntatt
    forrige, som er parameter til metoden
    */
    ArrayList<Tuppel> nySti = new ArrayList<>(sti);
    besokt = true;
    nySti.add(new Tuppel(rad,kolonne));
    for(String string:naboer.keySet()){
      if(!naboer.get(string).equals(forrige) && !naboer.get(string).besokt){
        naboer.get(string).gaa(nySti,this);
      }
    }
    besokt = false;
  }
}
