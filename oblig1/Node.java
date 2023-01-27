public class Node{
  int memory;
  int proc;

  public Node(int m, int p){
    this.memory = m;
    this.proc = p;
  }

  public int getProcessors(){
    return this.proc;
  }

  public boolean getMemory(int atLeast){
    return (this.memory >= atLeast);
  }
}
