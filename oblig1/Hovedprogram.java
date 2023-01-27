class Hovedprogram{
  public static void main(String [] Args){
    Dataklynge abel = new Dataklynge("dataklynge.txt");

    System.out.println("Antall noder med minst 32 gb: " +
    abel.getMemory(32) + ".");

    System.out.println("Antall noder med minst 64 gb: " +
    abel.getMemory(64) + ".");

    System.out.println("Antall noder med minst 128 gb: " +
    abel.getMemory(128) + ".");

    System.out.println("Antall prosessorer: " +
    abel.getProcessors() + ".");

    System.out.println("Antall rack: " +
    abel.getRackNum() + ".");

  }
}
