abstract class Resepter{    
    static int gittId = 0;
    protected int Id;
    protected Legemiddel legemiddel;
    protected Lege utskrivendeLege;
    protected Pasient pasient;
    protected int reit;
    
     public Resepter(Legemiddel legemiddel,Lege utskrivendeLege, Pasient pasient, int reit){
          this.legemiddel = legemiddel;
          this.utskrivendeLege = utskrivendeLege;
          this.pasient = pasient;
          this.reit = reit;
          gittId++;
          Id = gittId;
     }

     public boolean bruk(){
          if (reit >= 1){
               reit--;
               return true;
          }
          else{
               return false;
          }
     }
   
     abstract public String farge();
     abstract public float prisAaBetale();

     public int hentID(){
          return Id;
     }

     public Legemiddel hentLegemiddel(){
          return legemiddel;
     }

     public Lege hentLege(){
          return utskrivendeLege;
     }

     public Pasient hentPasient(){
          return pasient;
     }

     public int hentReit(){
          return reit;
     }

     @Override
     public String toString() {

          String reseptType = "";
          if (this instanceof Blaa) {reseptType = "Blå resept";}
          else if (this instanceof PResept) {reseptType = "P-resept";}
          else if (this instanceof MResept) {reseptType = "Militær-resept";}
          else if (this instanceof Hvit) {reseptType = "Hvit resept";}

          return Id +"\t"+ reseptType +", "+ pasient.hentNavn() +", "+ legemiddel.hentNavn() +", "+ utskrivendeLege.hentNavn() +", "+ reit;
   }
//    return Id + "\t" + navn + ": " + reseptType + ", "
//        // + ", pris: "String.valueOf(pris) + ", virkestoff: " + String.valueOf(virkestoff);
//         + String.format("%.0f", pris) + "kr" + ", " + String.format("%.0f", virkestoff) + "mg";
//    // return navn + "\t" + pris + "\t" + virkestoff + "\t" + styrke + "/10";            

   abstract public String txtVennlig();
}
