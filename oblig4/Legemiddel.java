abstract class Legemiddel {
    static int gittId = 0;
    protected int Id;
    protected String navn;
    protected float pris;
    protected double virkestoff;

    public Legemiddel(String navn, float pris, double virkestoff){
        this.navn = navn;
        this.pris = pris;
        this.virkestoff = virkestoff;
        gittId++;
        Id = gittId;
    }

    public Legemiddel(String navn, float pris, double virkestoff, int styrke){
        this.navn = navn;
        this.pris = pris;
        this.virkestoff = virkestoff;
        gittId++;
        Id = gittId;
    }

    public int hentID(){
        return Id;
    }

    public String hentNavn(){
        return navn; 
    }

    public float hentPris(){
        return pris;
    }

    public double hentVirkestoff(){
        return virkestoff;
    }

    public float settNyPris(float nyPris){
        pris = nyPris;
        return pris;
    }

    public String toString() {
        // Sjekk hvilken type legemidlet er, og lag string:
        String middelType = "";
        if (this instanceof Narkotisk) {middelType = "Narkotisk";}
        else if (this instanceof Vanedannende) {middelType = "Vanedannende";}
        else if (this instanceof Vanlig) {middelType = "Vanlig";}
        // %.2f %n
        // "%.2f", value
        return Id + "\t" + navn + ": " + middelType + ", "
            // + ", pris: "String.valueOf(pris) + ", virkestoff: " + String.valueOf(virkestoff);
             + String.format("%.0f", pris) + "kr" + ", " + String.format("%.0f", virkestoff) + "mg";
        // return navn + "\t" + pris + "\t" + virkestoff + "\t" + styrke + "/10";            
    }
    abstract public String txtVennlig();
}