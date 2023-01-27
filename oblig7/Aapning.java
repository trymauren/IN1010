import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Aapning extends HvitRute{
  public Aapning(char c, int rad, int kolonne, Labyrint labyrint){
    super(c,rad,kolonne,labyrint);
    setFarge();
  }

  protected void setFarge(){
    setBackground(Color.YELLOW);
    setOpaque(true);
  }
  public void settNabo(String s, Rute r){
    naboer.put(s,r);
  }
  public void gaa(ArrayList<Tuppel> sti, Rute rute){
    try{
      //System.out.println("["+rad+"]"+""+","+""+"["+kolonne+"]");
      ArrayList<Tuppel> nySti = new ArrayList<>(sti);
      besokt = true;
      nySti.add(new Tuppel(rad,kolonne));
      labyrint.alleVeier.add(nySti);
    }
    catch(NullPointerException e){
      System.out.println("feil");
    }
    besokt = false;
  }
}
