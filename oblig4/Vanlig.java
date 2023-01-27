class Vanlig extends Legemiddel {
    public Vanlig(String navn, float pris, double virkestoff){
        super(navn, pris, virkestoff);
    }
    
    // public String toString(){
    //     return "Navn: " + navn + " Pris: " + pris + " Virkestoff: " + virkestoff + "mG"; 
    // }
    public String txtVennlig(){
        return navn + "," + "vanlig" + pris + "," + virkestoff;
    }
}
