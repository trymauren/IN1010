class Spesialist extends Lege implements Godkjenningsfritak{

  String kontrollID;

  Spesialist(String navn, String kontrollID){
    super(navn);
    this.kontrollID = kontrollID;
  }
  public String toString(){
    return "Navn: " + navn + " (spesialist)" + ", kontrollID = " + kontrollID;
  }
  public String hentKontrollID(){
    return kontrollID;
  }
}
