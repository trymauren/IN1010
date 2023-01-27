import java.util.Iterator;
// Lager en klasse Lenkeliste<T> som implementerer Liste<T>.
class Lenkeliste<T> implements Liste<T>{

  //Konstantvariabler
  private Node start;
  private Node slutt;

  public Lenkeliste(){
    start = null;
    slutt = null;
  }

  public Iterator<T> iterator() {
    return new LenkelisteIterator(this);
  }

  //Lager Lenkelisten Node
  class Node {
    Node neste = null;    //Variabel som peker på den neste noden
    T data;
    Node(T x){            //Konstruktør til Node
      data = x;}
  }

  class LenkelisteIterator implements Iterator<T>{
    private int teller;
    //private Lenkeliste<T> liste;

    public LenkelisteIterator(Lenkeliste<T> liste) {
      teller = 0;
    };

    @Override
    public T next() {
      return hent(teller++);
    }

    @Override
    public boolean hasNext() {
      return (teller < stoerrelse());
    }
  }

  //Oppretter en metode 'leggTil(T x)', som skal legge til et element på slutten av listen
  public void leggTil (T x){
    Node nyNode = new Node(x);
    if (start == null){                         //Hvis den Lenkelisten er tom
      start = nyNode;
    }
    else if (start != null && slutt == null){  //Hvis den Lenkelisten inneholder ett element
      slutt = nyNode;
      start.neste = slutt;
    }
    else{                                      //Hvis den Lenkelisten inneholder flere en to elementer
      Node peker = start;
      while(peker.neste != null){
        peker = peker.neste;
      }
      peker.neste = nyNode;
      slutt = nyNode;
    }
  }

  //Oppretter en metode 'fjern()', som skal fjerne og returnere det første elementet på listen
  public T fjern() throws UgyldigListeIndeks{
    if(stoerrelse() > 0){
      Node returNode = start;
      start = start.neste;
      return returNode.data;}
    throw new UgyldigListeIndeks(0);}

  //Oppretter en metode'sett(int pos, T x)', som skal sette inn elementer på angitt posisjon
  //og overskrive det som var der fra før
  public void sett(int pos, T x) throws UgyldigListeIndeks{
    if(stoerrelse() != 0 ){
      if(pos > 0 && pos < stoerrelse()){
        Node peker = start;
        for(int i = 0; i < pos;i++){
            peker = peker.neste;}
        peker.data = x;}
      else if(pos == 0){
        start.data = x;}
      else{
        throw new UgyldigListeIndeks(pos);}}
    else{
      throw new UgyldigListeIndeks(0);}}

  //Oppretter en metode 'leggTil(int pos, T x)', som skal legge inn et nytt element i listen.
  //resterende elementene kommer da ett hakk lenger bakover i listen
  public void leggTil(int pos, T x) throws UgyldigListeIndeks{
    if(pos > 0 && pos < stoerrelse()){
      Node nyNode = new Node(x);
      Node peker = start;
      Node SistBruktNode = null;
      for(int i = 0; i < pos; i++){
        SistBruktNode = peker;
        peker = peker.neste;}
      SistBruktNode.neste = nyNode;
      nyNode.neste = peker;}
    else if(pos == 0){
      Node nyNode = new Node(x);
      Node peker = start;
      nyNode.neste = peker;
      start = nyNode;
      slutt = nyNode.neste;}
    else if(pos == stoerrelse()){
      leggTil(x);}
    else{
      throw new UgyldigListeIndeks(pos);}
    }

  //Oppretter en metode 'fjern(int pos)', som skal skal fjerne elementet på gitt indeks
  public T fjern(int pos) throws UgyldigListeIndeks{
    if(pos > 0 && pos < stoerrelse()){
      if(stoerrelse() > 1){
        Node peker = start;
        for (int i = 1; i < pos; i++){
          peker = peker.neste;}
        Node neste = peker.neste;
        peker.neste = neste.neste;
        return neste.data;}
      else{
        Node neste = start;
        start = null;
        return neste.data;}}
    else if(pos == 0) {
      if(stoerrelse() != 0){
        Node peker = start;
        start = start.neste;
        return peker.data;}}
    throw new UgyldigListeIndeks(pos);
  }

  //Oppretter en metode 'hent(int pos)', som skal skal hente ut et element på oppgitt plass
  public T hent(int pos) throws UgyldigListeIndeks{
    if(pos > 0 && pos < stoerrelse()){
      Node peker = start;
      for (int i = 0; i < pos; i++){
        peker = peker.neste;
      }
      return peker.data;
    }
    else if (pos == 0){
      if(stoerrelse() != 0){
        return start.data;
      }
    }
    throw new UgyldigListeIndeks(pos);
  }


  //Oppretter en metode 'stoerrelse', som skal finne størelsen av den Lenkedelisten
  public int stoerrelse(){
    int teller = 0;
    Node peker = start;
    while(peker != null){
      peker = peker.neste;
      teller += 1;
    }
    return teller;
  }

  @Override
  public String toString(){
     if(start.neste == null){
       return "Den Lenkelisten er tom :(";
      }

    else{
      String setning = "";
      Node peker = start;
      while(peker != null){
        setning = setning + peker.data+ ", ";
        peker = peker.neste;
      }
      return setning;
    }
  }

}
