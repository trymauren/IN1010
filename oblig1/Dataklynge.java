import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Dataklynge{

  String fileName;
  int maxNodes;

  public ArrayList<Rack> rackList = new ArrayList<Rack>();

  public Dataklynge(String fileName){
  this.fileName = fileName;
  this.maxNodes = 0;
    try{
      File file = new File(fileName);
      Scanner readFile = new Scanner(file);
      if(readFile.hasNextLine()){ //Leser først ut max antall noder per rack
        maxNodes = Integer.parseInt(readFile.nextLine());
      }
      while(readFile.hasNextLine()){ //Looper gjennom fil og kaller placeNode()
        int num = readFile.nextInt();
        int memory = readFile.nextInt();
        int process = readFile.nextInt();
        readFile.nextLine();
        for(int i = 0; i < num; i++){
          placeNode(memory, process);
        }
      }
    }
    catch(Exception e) {
      System.out.println(e);
    }
  }
  //Lager nytt rack og legger det til i dataklyngen
  private Rack newRack(){
    Rack newR = new Rack(maxNodes);
    rackList.add(newR);
    return newR;
  }
  //Plasserer node i rack i dataklynge
  private String placeNode(int mem, int pro){
    if (rackList.size() > 0){
      for (Rack rack : rackList){
        if (rack.checkSpace(maxNodes)){
          rack.addNode(mem,pro);
          return "";
        }
      }
    }
    newRack().addNode(mem,pro);
    return "";
  }
  //Returnerer antall prosessorer i dataklyngen
  public int getProcessors(){
    int numProc = 0;
    for(Rack rack: rackList){
      numProc += rack.getProcessors();
    }
    return numProc;
  }
  //Returnerer antall noder som har over x minne i dataklyngen
  public int getMemory(int atLeast){
    int counter = 0;
    for (Rack rack : rackList){
      counter += rack.getMemory(atLeast);
    }
    return counter;
  }
  //Returnerer antall rack i dataklyngen
  public int getRackNum(){
    int counter = 0;
    for (Rack rack : rackList){
      counter += 1;
    }
    return counter;
  }
}
