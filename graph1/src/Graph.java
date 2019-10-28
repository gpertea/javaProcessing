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
	
	
	public void processVertex(Vertex<T> v) {
		System.out.println("Visiting ["+v.data+"]");
		v.visited=true;
	}
	
	public void rtraverseDFS(Vertex<T> v) {
		if (v==null) return;
		processVertex(v);
		for (Edge e : v.adjList) {
			if (!e.to.visited) {
				rtraverseDFS(e.to);
			}
		}
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
			System.out.println("Error: recoverPath failed for dest="+dest);
			System.exit(1);
		}
		return path;
	}
	
	public void traverseDFS(Vertex<T> v) {
		HashMap<Vertex<T>, Vertex<T>> pred=new HashMap<Vertex<T>, Vertex<T>>();
		Stack<Vertex<T>> vstack=new Stack<>();
		vstack.push(v);
		while (!vstack.isEmpty()) {
			System.out.println("\tpop and visit top of stack: "+vstack);
			Vertex<T> top=vstack.pop();
			if (!top.visited) {
				processVertex(top);
				//path.push(top);
				int nump=0;
				//! adjList should be processed in reverse direction to fully emulate the recursive DFS!
				for (int i=top.adjList.size()-1; i>=0; i--) {
					Vertex<T> vto=top.adjList.get(i).to;
					if (!vto.visited) {
						System.out.println("\tpushing "+vto+ " onto stack: "+vstack);
						nump++;
						vstack.push(vto);
						pred.put(vto, top);
					} //if connected vertex not visited
				} //for each connected vertex
				if (nump==0) {
					//end of a path, print current path stack!
					DLinkedList<Vertex<T>> path=recoverPath(v, top, pred);
					System.out.println(">>> Path: "+path);
					//path.pop();
				}
			} //top of stack not visited
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
	
	public void rtraverseDFS(T vdata) {
		int vi = getVertexIndex(vdata);
		if (vi<0) {
			System.out.println("Error: vertex ["+vdata+"] not found!");
			System.exit(1);
		}
		rtraverseDFS(vertices.get(vi));
	}

}
