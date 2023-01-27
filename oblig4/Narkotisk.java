class Narkotisk extends Legemiddel {
    int styrke;
    public Narkotisk(String navn, float pris, double virkestoff, int styrke){
        super(navn, pris, virkestoff, styrke);
        this.styrke = styrke;
    }

    public int hentNarkotiskStyrke(){
        return styrke;
    }
    
    // @Override
    // public String toString(){
    //     return "Navn: " + navn + " Pris: " + pris + " Virkestoff: " + virkestoff + "mG Styrke: " + styrke + "/10"; 
    // }

    public String txtVennlig(){
        return navn + "," + "narkotisk" + pris + "," + virkestoff + "," + styrke;
    }
}
