import java.util.Scanner;

class BilBruk3{
  public static void main(String [] Args){
    Scanner skiltInput = new Scanner(System.in);
    System.out.print("Skriv inn skiltnummer: ");
    String skiltNummer = skiltInput.nextLine();
    Bil3 b3 = new Bil3(skiltNummer);

    Scanner navnInput = new Scanner(System.in);
    System.out.print("Skriv inn navn: ");
    String navn = navnInput.nextLine();
    Person p1 = new Person(b3,navn);
    p1.skrivUt();
  }
}
