import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Hovedprogram{
  static ArrayList<ArrayList<Tuppel>> alleVeier = null;
  static int counter = 0;
  public static void main(String[] args){
    JFrame vindu = new JFrame("Hei");
    vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    /*
    Finner fil og lager og viser labyrint
    */
    JFileChooser filVelger = new JFileChooser("/Documents/ROBOTIKK1/IN1010/oblig_6_2021");
    int resultat = filVelger.showOpenDialog(null);
    if(resultat != JFileChooser.APPROVE_OPTION){
      System.exit(1);
    }
    File fil = filVelger.getSelectedFile();
    SpillVindu spillVindu = null;
    try{
      spillVindu = new SpillVindu(fil);
    }
    catch(FileNotFoundException e){
      System.out.println("FEIL: Kunne ikke lese fra fil");
      System.exit(1);
    }
    spillVindu.initGUI();
    vindu.add(spillVindu);
    vindu.pack();
    vindu.setVisible(true);
  }
}
