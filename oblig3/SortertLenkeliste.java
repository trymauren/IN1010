class SortertLenkeliste<T extends Comparable <T>> extends Lenkeliste<T>{

  public void leggTil(T x){
    if(stoerrelse() == 0){
      super.leggTil(x);
      return;
    }
    for(int i = 0; i < stoerrelse(); i++){
      if(hent(i).compareTo(x) > 0){
        super.leggTil(i,x);
        return;
      }
    }
    super.leggTil(x);
  }
  public T fjern(){
    return super.fjern(stoerrelse()-1);
  }
  public void sett(int pos, T x) throws UnsupportedOperationException{
    throw new UnsupportedOperationException("Denne metoden er ikke tilgjengelig");
  }
  public void leggTil(int pos, T x) throws UnsupportedOperationException{
    throw new UnsupportedOperationException("Denne metoden er ikke tilgjengelig");
  }

}
