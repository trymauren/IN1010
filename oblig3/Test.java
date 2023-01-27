class Test{
  public static void main(String [] args){
    Testobjekt t1 = new Testobjekt("a");
    Lenkeliste<Testobjekt> l1 = new Lenkeliste<>();
    for (int i = 0; i < 10; i++){
      l1.leggTil(t1);
    }
    System.out.println(l1.utskriftMetode());
    System.out.println(l1.stoerrelse());
    Testobjekt t2 = new Testobjekt("b");
    l1.leggTil(3,t2);
    System.out.println(l1.utskriftMetode());
    System.out.println(l1.stoerrelse());
    l1.fjern();
    System.out.println(l1.utskriftMetode());
    System.out.println(l1.stoerrelse());
    l1.fjern(2);
    System.out.println(l1.utskriftMetode());
    System.out.println(l1.stoerrelse());
    l1.sett(0,t2);
    System.out.println(l1.utskriftMetode());
    System.out.println(l1.stoerrelse());
  }
}
