
public class Main {

	public static void main(String[] args) {
		Graph<String> graph=new Graph<>();
		Graph<String>.Vertex<String> v1=graph.addVertex("V1");
		Graph<String>.Vertex<String> v2=graph.addVertex("V2");
		graph.addEdge(v1, v2, 20);
		graph.addVertex("V3");
		graph.addVertex("V4");
		graph.addEdge(graph.getV(1), graph.getV(0), 10);
		graph.addEdge(graph.getV(1), graph.getV(3), 13);
		graph.addEdge(graph.getV(3), graph.getV(1), 31);
		System.out.println(graph);
	}

}
