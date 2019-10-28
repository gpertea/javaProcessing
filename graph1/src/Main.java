
public class Main {

	public static void main(String[] args) {
		Graph<String> graph=new Graph<>();
		graph.addVertex("V0");
		graph.addVertex("V1");
		graph.addVertex("V2");
		graph.addVertex("V3");
		graph.addVertex("V4");
		graph.addUndirectedEdge("V0", "V2");
		graph.addUndirectedEdge("V0", "V1");
		graph.addUndirectedEdge("V0", "V3");
		graph.addUndirectedEdge("V1", "V2");
		graph.addUndirectedEdge("V2", "V4");
		System.out.println(graph);
		graph.traverseDFS("V0");
		System.out.println("Recursive DFS:");
		graph.resetVisited();
		graph.rtraverseDFS("V0");
	}

}
