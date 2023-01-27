class Lege{

  String navn;

  public Lege(String navn){
    this.navn = navn;
  }
  public String toString(){
    return "Navn: " + navn;
  }
  public String hentNavn(){
    return navn;
  }
  public String hentKontrollID(){
    return "Har ingen kontrollID";
  }
}
