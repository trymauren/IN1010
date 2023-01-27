class Vanedannende extends Legemiddel {
    int styrke;
    public Vanedannende(String navn, float pris, double virkestoff, int styrke){
        super(navn, pris, virkestoff, styrke);
        this.styrke = styrke;
    }

    public int hentVanedannendeStyrke(){
        return styrke;
    }
    
    // public String toString(){
    //     // return "Navn: " + navn + "\t\tPris: " + pris + "\t\tVirkestoff: " + virkestoff + "\t\tmG Styrke: " + styrke + "/10"; 
    //     return navn + "\t" + pris + "\t" + virkestoff + "\t" + styrke + "/10";
    // }
    public String txtVennlig(){
        return navn + "," + "vanedannende" + pris + "," + virkestoff + "," + styrke;
    }
}