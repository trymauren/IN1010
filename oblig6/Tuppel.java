class Tuppel{
  int rad;
  int kolonne;
  public Tuppel(int rad, int kolonne){
    this.rad = rad;
    this.kolonne = kolonne;
  }
  public String toString(){
    return "["+ rad + "] , " + "[" + kolonne + "]";
  }
}
