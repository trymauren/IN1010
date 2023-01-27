// public interface Person {

//     public Lenkeliste<Resepter> hentResepter();
//     public String hentNavn();
//     // public int compareTo(Person annenPerson);
//     }

public abstract class Person {

    public Lenkeliste<Resepter> hentReseptListe() {
        Lenkeliste<Resepter> resepter = new Lenkeliste<Resepter>();
        return resepter;
    }
    public String hentNavn() {
        return "";
    }
    // public int compareTo(Person annenPerson);
    }
