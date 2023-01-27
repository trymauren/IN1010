import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class SpillVindu extends JPanel{
  JButton sluttknapp;
  JButton nesteknapp;
  JLabel overskrift;
  JLabel antLosninger;
  Labyrint labyrint;
  int count = 0;
  int antall = 0;

  SpillVindu(File fil) throws FileNotFoundException {
    try{
      labyrint = new Labyrint(fil, this);
    }
    catch(FileNotFoundException e){
      e.printStackTrace();
    }
  }
  public void initGUI(){

    /*
    Legger til overskrift
    */
    overskrift = new JLabel("Trykk på en rute for å finne veien ut");
    add(overskrift);


    /*
    Legger til antall løsninger og hvilken løsning som vises
    */
    antLosninger = new JLabel("                             ");
    add(antLosninger);

    /*
    Legger til knapp for å avslutte spillet
    */
    sluttknapp = new JButton("Avslutt");
    class Slutter implements ActionListener{
      @Override
      public void actionPerformed (ActionEvent e){
        System.exit(0);
      }
    }
    sluttknapp.addActionListener(new Slutter());
    add(sluttknapp);
    /*
    Legger til knapp for å vise flere løsninger (neste)
    */
    JButton neste = new JButton("Neste");
    class Neste implements ActionListener{
      @Override
      public void actionPerformed (ActionEvent e){
        labyrint.visNeste();
      }
    }
    neste.addActionListener(new Neste());
    add(neste);

    /*
    Lager GUI av labyrint og legger labyrint- panelet til dette panelet
    */
    labyrint.initGUI();
    add(labyrint);
  }
  public void settAntLosninger(String str){
    antLosninger.setText(str);
  }
  public void settOverskrift(String str){
    overskrift.setText(str);
  }
}
