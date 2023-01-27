class Bil3{
  String skiltNummer;
  Bil3(String str){
    skiltNummer = str;
  }
  public void skrivUt(){
    System.out.println(skiltNummer);
  }
  public String hentNummer(){
    return skiltNummer;
  }
}
