class Spesialist extends Lege implements Godkjenningsfritak {
    String kontrollId;
    public Spesialist(String navn, String kontrollId){
        super(navn);
        this.kontrollId = kontrollId;
    }

    @Override
    public String hentKontrollID() {
        return kontrollId;
    }
    
    @Override
    public String toString(){
        return legeNavn + ", " + kontrollId;
    }

    public String txtVennlig(){
        return legeNavn + "," + kontrollId;
    }
} 
