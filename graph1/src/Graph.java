import java.util.ArrayList;

public class Graph<T> {
   ArrayList< Vertex<T> > vertices;   
   
   class Edge {
	  Vertex<T> to;
	  int weight;
	  public Edge(Vertex<T> v, int w) {
		  to=v;
		  weight=w;
	  }
	  public Edge(Vertex<T> v) {
		  to=v;
		  weight=0;
	  }	  

   }
   
   class Vertex<D> {
	  D data;
	  ArrayList<Edge> adjList; //list of edges to other nodes = adjacency list
	  
	  public Vertex(D d) {
		  data=d;
		  adjList=new ArrayList<Edge>();
	  }
	  public void addEdge(Vertex<T> to, int weight) {
		  adjList.add(new Edge(to, weight));
	  }
	  
	  public void addEdge(Vertex<T> to) {
		  adjList.add(new Edge(to, 1));
	  }
	  
      public String toString() {
    	  String r=data.toString();
    	  for (Edge e: adjList) {
    		  r+=" ->["+ e.to.data.toString()+ "]("+e.weight+")";
    	  }
    	  return r;
      }
   } //-- Vertex class end

   // --- Graph class methods ---
   
   public Graph() { //graph constructor
	  vertices = new ArrayList< Vertex<T> > () ;
   }

   public Vertex<T> addVertex(T data) { //add a vertex to the graph
	   Vertex<T> v=new Vertex<>(data);
	   vertices.add(v);
	   return v;
   }
   
   public Vertex<T> getV(int atIndex) {
	   if (atIndex >= vertices.size()) {
		   System.out.println("Error: getV() out of range index "+atIndex+ ", size="+vertices.size());
		   System.exit(1);
	   }
	   return vertices.get(atIndex);
   }
   public void addEdge(Vertex<T> from, Vertex<T> to, int weight) { //add an edge to the graph
	   if (from==null || to==null) {
		   System.out.println("Error: cannot add edge to/from null vertex!");
		   System.exit(1);
	   }
	   from.addEdge(to, weight);
   }
   
   public void addEdge(Vertex<T> from, Vertex<T> to) {
	   addEdge(from, to, 0);
   }
   
   public String toString() {
	   String r="Graph ("+vertices.size()+" vertices)";
	   if (vertices.size()>0) r+=":";
	   r+="\n";
	   for (Vertex<T> v : vertices) {
		   r+=v.toString()+"\n";
	   }
	   return r;
   }
   
}
