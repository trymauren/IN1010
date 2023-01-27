import java.util.*;
import java.util.concurrent.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

class TestProgram{
  private static int subSekvensLengde = 3;
  private static String filnavn;
  private static int antTraader;
  public static void main(String [] args) throws InterruptedException{
    Monitor lesInnMonitor = new Monitor();
    filnavn = args[0];
    antTraader = Integer.parseInt(args[1]);

    try{
      /*
      Teller antall linjer i fil for å finne ut hvor mange tråder barrieren
      skal vente på
      */
      BufferedReader reader = new BufferedReader(new FileReader(filnavn));
      int counter = 0;
      reader.readLine();
      while (reader.readLine() != null){
        counter++;
      }
      System.out.println(counter);
      reader.close();
      CountDownLatch barriere = new CountDownLatch(counter);


      /*
      Setter i gang 1 tråd per linje i fil
      */
      BufferedReader br = new BufferedReader(new FileReader(filnavn));
      String linje;
      br.readLine();
      Runnable runnable;
      Thread thread;
      while((linje = br.readLine()) != null){
        runnable = new FilLeser(barriere,lesInnMonitor,linje,subSekvensLengde);
        thread = new Thread(runnable);
        thread.start();
      }
      /*
      Trådene venter til alle er ferdige før programmet fortsetter til flette- steget
      */
      barriere.await();
    }

    catch(IOException e){
      e.printStackTrace();
    }


    /*
    Fletter sammen hashmaps i beholder for friske personer.
    Dette gjøres med et antall tråder gitt som argument til programmet,
    fordelt på de to beholderne.
    */

    /*
    De neste linjene fordeler tråder
    */
    Beholder fBeholder = lesInnMonitor.hentFBeholder();
    int antTraaderF = (int) Math.ceil(antTraader/2);
    if(antTraaderF > (int) (Math.floor(fBeholder.hentSize()/2)-1)){
      antTraaderF = (int) (Math.floor(fBeholder.hentSize()/2)-1);
    }
    Beholder sBeholder = lesInnMonitor.hentSBeholder();
    int antTraaderS = (int) Math.ceil(antTraader/2);
    if(antTraaderS > (int) (Math.floor(sBeholder.hentSize()/2)-1)){
      antTraaderS = (int) (Math.floor(sBeholder.hentSize()/2)-1);
    }

    CountDownLatch barriere = new CountDownLatch(antTraaderF+antTraaderS);

    /*
    Oppretter like mange tråder som fordelt over og starter disse på beholder for friske
    */
    for(int i = 0; i < antTraaderF; i++){
      Runnable runnable2 = new FilFletter(barriere,lesInnMonitor,"frisk");
      Thread thread2 = new Thread(runnable2);
      thread2.start();

    }
    /*
    Oppretter like mange tråder som fordelt over og starter disse på beholder for syke
    */
    for(int i = 0; i < antTraaderS; i++){
      Runnable runnable3 = new FilFletter(barriere,lesInnMonitor,"syk");
      Thread thread3 = new Thread(runnable3);
      thread3.start();

    }
    /*
    Venter på ferdigstilling av fletting før programmet går videre til finnData();
    */
    barriere.await();

    finnData(lesInnMonitor);
  }

  private static void finnData(Monitor lesInnMonitor){
    int terskel = 5;
    System.out.println("--------------------------------");
    String returString = "\n\nEnkel test:\n\nSubsekvenser hvor syke-friske >= "+terskel+":\n";
		HashMap<String, SubSekvens> HMSyk = lesInnMonitor.hentSBeholder().hentHM();
		HashMap<String, SubSekvens> HMFrisk = lesInnMonitor.hentFBeholder().hentHM();

		for (String posSekvens : HMSyk.keySet()) {
			int sykVerdi = HMSyk.get(posSekvens).hentForekomster();
			int friskVerdi = 0;
			if (HMFrisk.containsKey(posSekvens)) {
				friskVerdi = HMFrisk.get(posSekvens).hentForekomster();
			}

			if (sykVerdi-friskVerdi >= terskel) {
				returString += "\nSubsekvens: " + posSekvens
				+ ", syk: " + sykVerdi
				+ ", frisk: " + friskVerdi;
			}
		}
    System.out.println(returString);
  }
}
