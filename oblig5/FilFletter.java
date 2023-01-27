import java.util.HashMap;
import java.util.concurrent.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class FilFletter implements Runnable{
  CountDownLatch barriere;
  Monitor monitor;
  String tilstand;
  int operasjoner;

  FilFletter(CountDownLatch barriere, Monitor monitor, String tilstand){
    this.monitor = monitor;
    this.barriere = barriere;
    this.tilstand = tilstand;
    this.operasjoner = operasjoner;
  }

  public void run(){

    if(tilstand == "frisk"){
      while(monitor.hentFBeholderSize() > 1){
        HashMap<String,SubSekvens> hm1 = monitor.fjernHMFrisk();
        HashMap<String,SubSekvens> hm2 = monitor.fjernHMFrisk();
        HashMap<String,SubSekvens> sammenlagt = Beholder.leggSammen(hm1,hm2);
        monitor.leggTilHMFrisk(sammenlagt);
      }
    }

    if(tilstand == "syk"){
      while(monitor.hentSBeholderSize() > 1){
        HashMap<String,SubSekvens> hm1 = monitor.fjernHMSyk();
        HashMap<String,SubSekvens> hm2 = monitor.fjernHMSyk();
        HashMap<String,SubSekvens> sammenlagt = Beholder.leggSammen(hm1,hm2);
        monitor.leggTilHMSyk(sammenlagt);
      }
    }

    barriere.countDown();

  }
}
