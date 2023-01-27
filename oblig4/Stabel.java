// Lager en klasse Stabel<T> som arver fra Lenkeliste<T>
class Stabel<T> extends Lenkeliste<T>{

  //Oppretter en metode 'leggPaa (T x)', som skal legge til et elemnet på slutten av listen
  public void leggPaa (T x){
    leggTil(x);}


  //Oppretter en metode 'taAv()', som skal fjerne og returnere siste element på listen
  public T taAv(){
    int sisteElement = stoerrelse() - 1;
    return fjern(sisteElement);}
}
