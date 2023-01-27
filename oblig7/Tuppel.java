import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Tuppel{
  int rad;
  int kolonne;
  public Tuppel(int rad, int kolonne){
    this.rad = rad;
    this.kolonne = kolonne;
  }
  public int hentRad(){
    return rad;
  }
  public int hentKolonne(){
    return kolonne;
  }
  public String toString(){
    return "["+ rad + "] , " + "[" + kolonne + "]";
  }
}
