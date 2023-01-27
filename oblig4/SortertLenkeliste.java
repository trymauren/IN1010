// Lager en klasse SortertLenkeliste<T> som arver fra Lenkeliste<T>
class SortertLenkeliste<T extends Comparable<T>> extends Lenkeliste<T>{

  //Oppretter en moetoden 'leggTil(T x)', somn skal sotere elementene i listen etter rekkefølge, fra sørst til minst
  @Override
  public void leggTil(T x){
    if(stoerrelse() == 0){                        // Hvis den Lenkedelisten er tom
      super.leggTil(x);}

    else if(stoerrelse() == 1){                   // Hvis den lenkedlisten inneholder ett element
      if(hent(0).compareTo(x) < 0){                // Hvis x er større en første elemnet i den lenkedlisten blir compare mindre enn 0
        super.leggTil(x);}
      else{                                       // Hvis x er mindre en første elemnet i den lenkedlisten
        super.leggTil(0, x);}}

    else{                                         // Hvis den lenkedlisten inneholder flere enn 2 element
      for(int i = 0; i < stoerrelse(); i++){      // Loop som går gjennom alle elemntene i listen
        if(hent(i).compareTo(x) > 0){
          super.leggTil(i,x);
          return;}}                                // Stopper loopen fordi vi ikke ønsker å legge til samme elemnt flere ganger
      super.leggTil(x);}}                          // Hvis ingen ting ble lagt til, betyr det at ny verdi er sørst


  //Oppretter metoden 'fjern()', som skal fjerne siste elemnet på lenkedlisten
  @Override
  public T fjern(){
    int sisteElement = stoerrelse() - 1;
    return fjern(sisteElement);}


  //Oppretter metoden 'sett(int pos, T x)', som skal skal nå i stedet kun kaste et unntak
  @Override
  public void sett(int pos, T x) throws UnsupportedOperationException{
    throw new UnsupportedOperationException();}

  //Oppretter metoden 'leggTil(int pos, T x)', som skal nå i stedet kun kaste et unntak
  @Override
  public void leggTil(int pos, T x) throws UnsupportedOperationException{
    throw new UnsupportedOperationException();}


}
