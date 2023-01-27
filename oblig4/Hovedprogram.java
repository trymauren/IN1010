public class Hovedprogram {
    public static void main(String[] args) throws UlovligUtskrift {
        Narkotisk Heroin = new Narkotisk("Heroin", 950, 127.5, 8);
        Vanedannende Ibux = new Vanedannende("Ibux", 100, 125.5, 4);
        Vanlig Ppiller = new Vanlig("Prevensjonspiller", 200, 40.5);

        Lege legeOle = new Lege("Ole");
        Spesialist legeSara = new Spesialist("Sara", "Ma-1243");
        Pasient mons = new Pasient("Mons", "92892824949");
        Pasient oystein = new Pasient("Øystein", "12345678987");
        Pasient sondre = new Pasient("Mons", "000000000");
        Pasient trym = new Pasient("Mons", "111111111");

        // try {
        legeOle.skrivPResept(Ppiller,sondre);
        // }
        // catch (Exception e) {
        //     System.out.println("feil");
        // }

        legeSara.skrivBlaaResept(Heroin, mons ,1234);
        legeOle.skrivHvitResept(Ibux, oystein, 3);
        legeSara.skrivMillitaerResept(Ibux, trym, 1234);
        //Denne skal gi feil
        //legeOle.skrivHvitResept(Heroin,trym,32);

        String newLine = System.getProperty("line.separator");
        Stabel<String> nyStabel = new Stabel<String>();
        nyStabel.leggTil("A");
        nyStabel.leggTil("B");
        nyStabel.leggTil("C");
        nyStabel.leggTil("D");
        nyStabel.leggTil("E");
        int i = 0;
        for (String s:nyStabel){
            i++;
        }
        System.out.println("Antall elementer i stabel: " + i);

        System.out.println("Legemidler som blir skrevet ut i dag:");
        System.out.println(Heroin);
        System.out.println(Ibux);
        System.out.println(Ppiller);

        System.out.println(newLine + "Leger som jobber i dag: ");
        System.out.println(legeOle);
        System.out.println(legeSara);

        System.out.println(newLine + "Resepter skrevet ut av legene i dag, og gjeldende Legemidler:");

    }
}
