import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class SortRute extends Rute{
  public SortRute(char c, int rad, int kolonne, Labyrint labyrint){
    super(c,rad,kolonne,labyrint);
    setFarge();
  }
  @Override
  protected void setFarge(){
    setBackground(Color.BLACK);
    setOpaque(true);
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
  protected void gaa(ArrayList<Tuppel> sti, Rute rute){};
}
