class Lenkeliste<T> implements Liste<T>{

  class Node{
    Node neste;
    T data;
    Node (T x){
      data = x;
    }
    public T hentData(){
      return data;
    }
    public Node hentNeste(){
      return neste;
    }
  }

  protected Node start = null;
  protected Node slutt = null;
  protected int iBruk = 0;


  public int stoerrelse(){
    return iBruk;
  }

  public void leggTil(int pos, T x) throws UgyldigListeIndeks{
    if (pos < 0 || pos > iBruk){
      throw new UgyldigListeIndeks(pos);
    }
    Node ny = new Node(x);
    Node denne = start;
    if (start != null && slutt != null){
      if(pos == (iBruk)){
        slutt.neste = ny;
        slutt = slutt.neste;
      }
      else if(pos == 0){
        ny.neste = start;
        start = ny;
      }
      else{
        for(int i = 0; i < pos -1 ; i++){
          denne = denne.neste;
        }
        ny.neste = denne.neste;
        denne.neste = ny;
      }
    }
    else{
      start = ny;
      slutt = ny;
    }
    iBruk++;
  }

  public void leggTil(T x){
    Node ny = new Node(x);
    if (start != null && slutt != null){
      slutt.neste = ny;
      slutt = slutt.neste;
    }
    else{
      start = ny;
      slutt = ny;
    }
    iBruk++;
  }

  public void sett(int pos, T x) throws UgyldigListeIndeks{
    if (pos < 0 || pos >= iBruk || start == null){
      throw new UgyldigListeIndeks(pos);
    }
    Node plukk = start;
    for (int i = 0; i<pos;i++){
      plukk = plukk.neste;
    }
    plukk.data = x;
  }

  public T hent(int pos) throws UgyldigListeIndeks{
    if (pos < 0 || pos >= iBruk || start == null){
      throw new UgyldigListeIndeks(pos);
    }
    Node plukk = start;
    for (int i = 0; i < pos;i++){
      plukk = plukk.neste;
    }
    return plukk.data;
  }

  public T fjern(int pos) throws UgyldigListeIndeks{
    if (pos < 0 || pos >= iBruk || start == null){
      throw new UgyldigListeIndeks(pos);
    }
    Node denne = start;
    T fjernetData = null;
    if(pos == 0){
      fjernetData = start.data;
      start = start.neste;
    }
    else{
      for(int i = 0; /*denne != null && */ i < pos-1; i++){
        denne = denne.neste;
      }
      if(denne.neste == slutt){
        slutt = denne;
      }
      fjernetData = denne.neste.data;
      denne.neste = denne.neste.neste;
    }
    iBruk--;
    return fjernetData;
  }

  public T fjern() throws UgyldigListeIndeks{
    if(start == null){
      throw new UgyldigListeIndeks(0);
    }
    Node fjernet = null;
    if(start != slutt){
      fjernet = start;
      start = start.neste;
      }
    else{
      fjernet = start;
      start = slutt = null;
      }
    iBruk--;
    return fjernet.data;
  }
  public String toString(){
    String streng = "";
    Node denne = start;
    if(start == null){
      return "Tom liste.";
    }
    while(denne != null){
      streng += (denne.data + " ");
      denne = denne.neste;
    }
    return streng;
  }
}
