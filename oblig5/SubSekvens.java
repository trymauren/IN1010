class SubSekvens{

  private int forekomster = 1;
  private String subSekvens;

  public SubSekvens(String subSekvens){
    this.subSekvens = subSekvens;
  }
  public void leggTilForekomster(int antallForekomster){
    forekomster += antallForekomster;
  }
  public void settSubSekvens(String subSekvensInn){
    subSekvens = subSekvensInn;
  }
  public int hentForekomster(){
    return forekomster;
  }
  public String hentSubSekvens(){
    return subSekvens;
  }
}
