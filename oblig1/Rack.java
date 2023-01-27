import java.util.ArrayList;

public class Rack{
  int maxNodes;
  public ArrayList <Node> nodeList = new ArrayList<Node>();

  public Rack(int m){
    this.maxNodes = m;
  }
  //Sjekker om det er plass til flere noder i racket
  public boolean checkSpace(int maxNodes){
    if(nodeList.size() < maxNodes){
      return true;
    }
    else{
      return false;
    }
  }

  public void addNode(int mem, int pro){
    Node newNode = new Node(mem,pro);
    nodeList.add(newNode);
  }

  public int getProcessors(){
    int proce = 0;
    for (Node node : nodeList){
      proce += node.getProcessors();
    }
    return proce;
  }

  public int getMemory(int atLeast){
    int counter = 0;
    for (Node node : nodeList){
      if (node.getMemory(atLeast)){
      counter += 1;
      }
    }
    return counter;
  }
}
