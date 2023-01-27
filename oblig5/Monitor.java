import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.CountDownLatch;
import java.util.HashMap;

class Monitor{
  @SuppressWarnings("unchecked")
  public static Beholder beholderFrisk = new Beholder("friske");
  public static Beholder beholderSyk = new Beholder("syke");

  private Lock laas = new ReentrantLock();

  public void leggTil(HashMap<String,SubSekvens> hashmap, String type){
    laas.lock();
    try{
      if(type.equals("True")){
        beholderSyk.leggTilHM(hashmap);
        System.out.println(" Lagt til person i SYK beholder");
      }
      else if(type.equals("False")){
        beholderFrisk.leggTilHM(hashmap);
        System.out.println(" Lagt til person i FRISK beholder");
      }
      else{
        System.out.println(" FEIL: Hverken true eller false!");
      }
    }
    finally{
    laas.unlock();
    }
  }

  public HashMap<String,SubSekvens> fjernHMFrisk(){
    laas.lock();
    try{
      return beholderFrisk.fjernHM();
    }
    finally{
      laas.unlock();
    }
  }
  public HashMap<String,SubSekvens> fjernHMSyk(){
    laas.lock();
    try{
      return beholderSyk.fjernHM();
    }
    finally{
      laas.unlock();
    }
  }

  public void leggTilHMFrisk(HashMap<String,SubSekvens> hm){
    laas.lock();
    try{
      beholderFrisk.leggTilHM(hm);
    }
    finally{
      laas.unlock();
    }
  }
  public void leggTilHMSyk(HashMap<String,SubSekvens> hm){
    laas.lock();
    try{
      beholderSyk.leggTilHM(hm);
    }
    finally{
      laas.unlock();
    }
  }

  public int hentFBeholderSize(){
    return beholderFrisk.hentSize();

  }
  public int hentSBeholderSize(){
    return beholderSyk.hentSize();
  }

  public Beholder hentFBeholder(){
    return beholderFrisk;
  }
  public Beholder hentSBeholder(){
    return beholderSyk;
  }
}
