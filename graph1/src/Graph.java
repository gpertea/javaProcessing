import java.util.ArrayList;
import java.util.HashMap;

public class Graph<T> {

	class Edge {
		Vertex<T> to; // the vertex to connect to
		int weight;

		public Edge(Vertex<T> v, int w) { // constructor
			to = v;
			weight = w;
		}

		public Edge(Vertex<T> v) { // constructor for unweighted graphs
			to = v; // set the weight to an arbitrary default constant
			weight = 1;
		}
	}

	class Vertex<D> {
		boolean visited = false; //the vertex has been visited
		D data; // actual data for this vertex
		ArrayList<Edge> adjList;// list of edges to other nodes = adjacency list

		Vertex(D adata) {
			data = adata;
			adjList = new ArrayList<Edge>();
		}

		public void addEdge(Vertex<T> to, int weight) {
			adjList.add(new Edge(to, weight));
		}

		public String toString() {
			String r = data.toString();
			/*
			for (Edge e : adjList) {
				r += " ->[" + e.to.data.toString() + "]";
				if (isWeighted) r+="(" + e.weight + ")";
			}
			*/
			return r;
		}
	}

	ArrayList<Vertex<T>> vertices;
    boolean isWeighted;
    Stack<Vertex<T>> path = new Stack<Vertex<T>>();
    
	Graph(){
		vertices = new ArrayList<Vertex<T>>();
	}

	public void resetVisited() {
		for(int i = 0; i < vertices.size(); i++) {
			(vertices.get(i)).visited = false;
		}
	}

	public int getVertexIndex(T searchData) {
		for (int i = 0; i < vertices.size(); i++) {
			if(((vertices.get(i)).data).equals(searchData)) {
				return i;
			}
		}
		return -1;
	}

	public Vertex<T> getVertex(int atIndex){
		if(atIndex < 0 || atIndex >= vertices.size()) {
			System.out.println("wrong index");
			System.exit(1);
		}
		return vertices.get(atIndex);
	}

	public int addVertex(T data) {
		Vertex<T> v = new Vertex<T>(data);
		vertices.add(v);
		return (vertices.size()-1);
	}

	public void addEdge(int fromIdx, int toIdx, int weight) {
		getVertex(fromIdx).addEdge(getVertex(toIdx), weight);
		if (weight!=1) isWeighted=true;
	}

	public void addEdge(int fromIdx, int toIdx) {
		getVertex(fromIdx).addEdge(getVertex(toIdx), 1);
	}

	public void addUndirectedEdge(int fromIdx, int toIdx) {
		getVertex(fromIdx).addEdge(getVertex(toIdx), 1);
		getVertex(toIdx).addEdge(getVertex(fromIdx), 1);
	}

	public void addEdge(T fromData, T toData, int weight) {
		if (weight!=1) isWeighted=true;
		int f = getVertexIndex(fromData);
		int t = getVertexIndex(toData);
		if(f < 0) {
			System.out.println("error: "+fromData+" not found");
			return;
		}
		else if(t < 0) {
			System.out.println("error: "+toData+" not found");
			return;
		}
		vertices.get(f).addEdge(vertices.get(t), weight);
	}

	public void addEdge(T fromData, T toData) {
		addEdge(fromData, toData, 1);
	}


	public void addUndirectedEdge(T fromData, T toData, int weight) {
		if (weight!=1) isWeighted=true;
		int f = getVertexIndex(fromData);
		int t = getVertexIndex(toData);
		if(f < 0) {
			System.out.println("error: "+fromData+" not found");
			return;
		}
		else if(t < 0) {
			System.out.println("error: "+toData+" not found");
			return;
		}
		vertices.get(f).addEdge(vertices.get(t), weight);
		vertices.get(t).addEdge(vertices.get(f), weight);
	}

	public void addUndirectedEdge(T fromData, T toData) {
		addUndirectedEdge(fromData, toData, 1);
	}

	public String toString() {
		String r="Graph ("+vertices.size()+" vertices)";
		if (vertices.size()>0) r+=":";
		r+="\n";
		for (Vertex<T> v : vertices) {
			r+=v.toString()+"\n"; //make use of the Vertex.toString() method!
		}
		return r;
	}
	
	
	public void visitVertex(Vertex<T> v) {
		System.out.println("Visiting ["+v.data+"]");
		v.visited=true;
	}
	
	public void recursiveDFS(Vertex<T> v) {
		if (v==null) return;
		path.push(v);
		visitVertex(v);
		//System.out.println("    path to this vertex:"+path);
		boolean pathEnd=true;
		for (Edge e : v.adjList) {
			if (!e.to.visited) {
				pathEnd=false;
				recursiveDFS(e.to);
			}
		} //for each neighbor
		if (pathEnd) System.out.println("\t Path: "+path);
		path.pop();
	}
	
	public void recursiveDFS(T vdata) {
		int vi = getVertexIndex(vdata);
		if (vi<0) {
			System.out.println("Error: vertex ["+vdata+"] not found!");
			System.exit(1);
		}
		
		recursiveDFS(vertices.get(vi));
	}

	
	
	DLinkedList<Vertex<T>> recoverPath(Vertex<T> src, Vertex<T> dest, HashMap<Vertex<T>, Vertex<T>> pred) {
		DLinkedList<Vertex<T>> path=new DLinkedList<>();
		path.addFirst(dest);
		Vertex<T> v=pred.get(dest);
		while (v!=src && v!=null) {
			path.addFirst(v);
			v=pred.get(v);
		}
		if (v==src) path.addFirst(v);
		else {
			System.out.println("Error: recoverPath failed for dest="+dest+ " and src="+src);
			System.exit(1);
		}
		return path;
	}
	
	public void traverseDFS(Vertex<T> v) {
		//process (visit) nodes as they are pushed onto the stack
		HashMap<Vertex<T>, Vertex<T>> pred=new HashMap<Vertex<T>, Vertex<T>>();
		Stack<Vertex<T>> vstack=new Stack<>();
		Vertex<T> lastUnvisited=v;
		vstack.push(v);
		visitVertex(v); // "visit" the source node
		while (!vstack.isEmpty()) {
			Vertex<T> top=vstack.peek();
			System.out.println("\tpeek at the top of stack: "+vstack);
			Vertex<T> nextUnvisited=null;
			// find the first unvisited neighbor of top 
			for (int i=0;i<top.adjList.size(); i++) {
				Vertex<T> vto=top.adjList.get(i).to;
				if (!vto.visited) {
					nextUnvisited=vto;
					break; //unvisited neighbor found!
				}
			}
			if (nextUnvisited!=null) {
				vstack.push(nextUnvisited);
				pred.put(nextUnvisited, top);
				lastUnvisited=nextUnvisited;
				visitVertex(nextUnvisited);
				System.out.println("\tpushed "+nextUnvisited+ " onto stack which is now: "+vstack);
			} else { //end of traversal here
				if (top==lastUnvisited)
				    System.out.println("\t----- traversal path: "+vstack);
				vstack.pop();
				System.out.println("\tpopped the stack which is now: "+vstack);
				//end of a path, print current path stack!
				if (!vstack.isEmpty() && top==lastUnvisited) {
					DLinkedList<Vertex<T>> path=recoverPath(v, top, pred);
					System.out.println("\t>>> Path: "+path);
				}
			}
		}
	}
	
	public void traverseDFS(T vdata) {
		int vi = getVertexIndex(vdata);
		if (vi<0) {
			System.out.println("Error: vertex ["+vdata+"] not found!");
			System.exit(1);
		}
		traverseDFS(vertices.get(vi));
	}
	

}
