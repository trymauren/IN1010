class Hovedprogram{
  public static void main(String []args){
    Vanlig vanlig1 = new Vanlig("Prevensjonspille",90,100);
    Vanedannende vanedannende1 = new Vanedannende("Paracet",100,500,5);
    Narkotisk narkotisk1 = new Narkotisk("Heroin",1000,1000,1);

    Lege lege1 = new Lege("Olav");
    Spesialist spesialist1 = new Spesialist("Albert","343536");
    Lege lege2 = new Lege("Bing");

    PResept presept1 = new PResept(vanlig1,lege1,005);
    MilitResept militresept1 = new MilitResept(vanedannende1,lege1,006,1);
    Blaa blaa1 = new Blaa(narkotisk1,spesialist1,007,2);
    Hvit hvit1 = new Hvit(narkotisk1,lege2,006,3);

    System.out.println(narkotisk1.hentNarkotiskStyrke() == 1);
    System.out.println(vanedannende1.hentVanedannendeStyrke() == 5);
    System.out.println(spesialist1.hentKontrollID() == "343536");
    System.out.println(presept1.prisAaBetale() == 0);
    System.out.println(militresept1.prisAaBetale() == 0);
    System.out.println(blaa1.farge() == "blaa");
    System.out.println(militresept1.farge() == "hvit");
    System.out.println(presept1.farge() == "hvit");
    System.out.println(blaa1.prisAaBetale() == 250);
    System.out.println(militresept1.hentReit() == 1);
    System.out.println(blaa1.hentReit() == 2);
    System.out.println(presept1.hentPasientID() == 005);
    System.out.println(blaa1.hentLege() == spesialist1);
    System.out.println(blaa1.bruk() == true);
    System.out.println(blaa1.hentReit() == 1);
    System.out.println(blaa1.bruk() == true);
    System.out.println(blaa1.bruk() == false);

    System.out.println(narkotisk1);
    System.out.println(vanedannende1);
    System.out.println(vanlig1);
    System.out.println(lege1);
    System.out.println(spesialist1);
    System.out.println(presept1);
    System.out.println(militresept1);
    System.out.println(blaa1);
  }
}
