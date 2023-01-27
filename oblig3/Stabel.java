class Stabel<T> extends Lenkeliste<T>{

  public void leggPaa(T x){
    super.leggTil(x);
  }

  public T taAv(){
    T fjernetElement = super.fjern(iBruk-1);
    return fjernetElement;
  }
}
