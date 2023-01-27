import java.util.Scanner;

class BilBruk2{
  public static void main(String [] Args){
    Scanner skiltInput = new Scanner(System.in);
    System.out.print("Skriv inn skiltnummer: ");
    String skiltNummer = skiltInput.nextLine();
    Bil2 b2 = new Bil2(skiltNummer);  //Lager nytt objekt av klasse Bil2,
    b2.skrivUt();                     // med skiltnummer = input
  }
}
