class Person{

  private Bil3 bil;
  private String navn;

  Person(Bil3 b,String n){
  bil = b;
  navn = n;
  }
  
  public void skrivUt(){
    String nummer = bil.hentNummer();
    System.out.println("Bil med skiltnummer " + nummer +
    " tilhører " + navn + ".");
  }
}
