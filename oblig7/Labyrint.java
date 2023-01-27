import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Labyrint extends JPanel{
  Rute[][] labyrint;
  File fil;
  int rader;
  int kolonner;
  SpillVindu spillVindu;
  int counter = 0;

  public ArrayList<ArrayList<Tuppel>> alleVeier;

  public Labyrint(File fil, SpillVindu spillVindu) throws FileNotFoundException {
    try{
      this.fil = fil;
      this.spillVindu = spillVindu;
      Scanner leser = new Scanner(fil);
      System.out.println();
      System.out.println("Leser inn labyrint fra fil");

      /*
      Oppretter array med like mange rader og kolonner som tallene i 1. linje i fil
      */

      String[] data = leser.nextLine().trim().split(" ");
      rader = Integer.parseInt(data[0]);
      kolonner = Integer.parseInt(data[1]);

      labyrint = new Rute[rader][kolonner];
      /*
      Leser inn 2d- array fra fil til char- array
      */
      Rute naboVenstre = null;
      Rute naboHoyre = null;
      Rute naboOver = null;
      Rute naboUnder = null;
      Rute rute = null;

      while(leser.hasNextLine()){
        for(int i=0;i<rader;i++){
          char[] linje = leser.nextLine().trim().toCharArray();
          Rute[] ruter = new Rute[kolonner];
          Rute r;
          for(int k=0; k<kolonner;k++){
            if(Character.toString(linje[k]).equals(".")){
              /*
              Sjekker om hvit rute også er en åpning. Hvis ja, opprettes Aapning- objekt
              */
              if(i == 0 || k == 0 || i == rader-1 || k == kolonner-1){
                r = new Aapning(linje[k],i,k,this);
              }
              else{
                r = new HvitRute(linje[k],i,k,this);
              }
            }
            else if(Character.toString(linje[k]).equals("#")){
              r = new SortRute(linje[k],i,k,this);
            }
            else{
              System.out.println("Feil i fil.");
              return;
            }
            ruter[k] = r;

          }
          for(int j=0;j<kolonner;j++){
            labyrint[i][j] = ruter[j];
          }
        }
      }

      /*
      Setter naboer til alle ruter i labyrint
      */
      for(int i=0;i<rader;i++){
        for(int j=0;j<kolonner;j++){

          rute = labyrint[i][j];
          try{
            naboVenstre = labyrint[i][j-1];
            rute.settNabo("venstre",naboVenstre);

          }
          catch(ArrayIndexOutOfBoundsException e){
          }
          try{
            naboHoyre = labyrint[i][j+1];
            rute.settNabo("hoyre",naboHoyre);
          }
          catch(ArrayIndexOutOfBoundsException e){
          }
          try{
            naboOver = labyrint[i-1][j];
            rute.settNabo("over",naboOver);
          }
          catch(ArrayIndexOutOfBoundsException e){
          }
          try{
            naboUnder = labyrint[i+1][j];
            rute.settNabo("under",naboUnder);
          }
          catch(ArrayIndexOutOfBoundsException e){
          }
        }
      }
    }
    catch(FileNotFoundException e){
      e.printStackTrace();
    }
  }

  public Rute[][] hentLabyrint(){
    return labyrint;
  }

  public String toString(){
    String s = "";
    String d = "";
    String f = "";
    for(int i=0;i<rader*2+1;i++){
      d += "-";
    }
    for (Rute[] x : labyrint){
      for (Rute y : x){
        f += (y.tilTegn() + " ");
      }
      f += "\n";
    }
    s += "\n\n";
    s += d;
    s += "\n\n";
    s += f;
    s += "\n";
    s += d;
    s += "\n\n";
    return s;

  }
  public void setCounter(int n){
    counter = n;
  }
  public ArrayList<ArrayList<Tuppel>> initGUI(){
    alleVeier = new ArrayList<ArrayList<Tuppel>>();
    /*
    Legger til knapp for å skjule andre løsninger.
    Legges til på SpillVindu
    */
    // JButton fjern = new JButton("Fjern løsning");
    // class Fjern implements ActionListener{
    //   @Override
    //   public void actionPerformed (ActionEvent e){
    //     fjernLosning();
    //     }
    //   }
    //   fjern.addActionListener(new Fjern());
    //   spillVindu.add(fjern);

    setLayout(new GridLayout(rader,kolonner));
    for(int i=0;i<labyrint.length;i++){
      for(int j=0;j<labyrint[i].length;j++){
        Rute rute = labyrint[i][j];
        rute.initGUI();
        add(rute);
      }
    }
    return alleVeier;
  }
  /*
  Viser løsning til bruker ved å markere alle
  koordinatene som ligger i en ArrayList<Tuppel>,
  altså på én vei
  */
  public void visLosning(){

    spillVindu.settOverskrift("Viser korteste løsning");
    spillVindu.settAntLosninger("Trykk neste for å se flere løsninger");
    try{

      ArrayList<Tuppel> korteste = alleVeier.get(0);
      for (ArrayList<Tuppel> lis: alleVeier) {
        if(lis.size() < korteste.size()){
          korteste = lis;
        }
      }
      for(Tuppel t : korteste){
        int rad = t.hentRad();
        int kolonne = t.hentKolonne();
        Rute rute = labyrint[rad][kolonne];
        rute.setBackground(Color.green);
      }
    }
    catch(NullPointerException e){
      System.out.println("nullpointer");
    }
  }

  public void visNeste(){
    /*
    Viser ved å bruke knappen til å navigere
    */
    int antVeierInt = alleVeier.size();
    spillVindu.settOverskrift("Trykk neste for å se flere løsninger");
    spillVindu.settAntLosninger("Viser løsning " + (counter+1) + " / " + antVeierInt);

    rydd();
    try{
      if(counter >= antVeierInt){
        counter = 0;
      }
      ArrayList<Tuppel> enVei = alleVeier.get(counter);
      for(Tuppel t : enVei){
        int rad = t.hentRad();
        int kolonne = t.hentKolonne();
        Rute rute = labyrint[rad][kolonne];
        rute.setBackground(Color.green);
      }
    }
    catch(NullPointerException e){
      System.out.println("nullpointer");
    }
    counter++;
  }
  /*
  Fjerner fargede ruter før ny løsning skal vises
  */
  public void rydd(){
    for(int i=0;i<labyrint.length;i++){
      for(int j=0;j<labyrint[i].length;j++){
        Rute rute = labyrint[i][j];
        if(rute.tilTegn() == '.'){
          rute.setBackground(Color.white);
        }
      }
    }
  }
}
