import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

abstract class Rute extends JButton{
  char tegn;
  int rad;
  int kolonne;
  Labyrint labyrint;
  HashMap<String,Rute> naboer;
  public boolean besokt = false;

  public Rute(char c, int rad, int kolonne, Labyrint labyrint){
    this.tegn = c;
    this.rad = rad;
    this.kolonne = kolonne;
    this.labyrint = labyrint;
    this.naboer = new HashMap<String,Rute>();
  }
  public void initGUI (){
    /*
    Lager Rute visuelt og legger til knapp
    */
    setBorder(BorderFactory.createLineBorder(Color.black));
    setFont(new Font("Monospaced", Font.BOLD, 10));
    setPreferredSize(new Dimension(30, 30));
    Rute denneRuten = this;
    class Rutevelger implements ActionListener {
      @Override
      public void actionPerformed (ActionEvent e) {
        rydd();
        finnUtvei();
        labyrint.visLosning();
      }
    }
    addActionListener(new Rutevelger());
  }
  private void rydd(){
    labyrint.rydd();
  }
  protected abstract void setFarge();

  public abstract char tilTegn();

  public abstract void finnUtvei();

  protected abstract void gaa(ArrayList<Tuppel> sti, Rute rute);

  public void settNabo(String s, Rute r){
    naboer.put(s,r);
  }

  // /*
  // Hjelpemetoder
  // */

  // public int rad(){
  //   return rad;
  // }
  // public int kolonne(){
  //   return kolonne;
  // }
  //
  // public Rute harVenstre(){
  //   if(naboer.containsKey("venstre")){
  //     return naboer.get("venstre");
  //   }
  //   return null;
  // }
  // public Rute harHoyre(){
  //   if(naboer.containsKey("hoyre")){
  //     return naboer.get("hoyre");
  //   }
  //   return null;
  // }
  // public Rute harOver(){
  //   if(naboer.containsKey("over")){
  //     return naboer.get("over");
  //   }
  //   return null;
  // }
  // public Rute harUnder(){
  //   if(naboer.containsKey("under")){
  //     return naboer.get("under");
  //   }
  //   return null;
  // }
}
