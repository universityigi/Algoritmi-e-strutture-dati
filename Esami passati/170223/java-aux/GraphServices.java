import java.util.*;


public class GraphServices {


	public static <V> void kdist(Graph<V> g, Graph.GraphNode<V> source, int k) {
		g.resetStatus();
		Queue<Graph.GraphNode<V>> queue = new LinkedList<Graph.GraphNode<V>>();
		source.timestamp = 0;
		queue.add(source);
		source.state = Graph.GraphNode.Status.EXPLORED;
		while(!queue.isEmpty()){
			Graph.GraphNode<V> n = queue.remove();
			if(n.timestamp > k)
				break;
			System.out.print(n.value + " ");
			for(Graph.GraphNode<V> nodeOut : n.outEdges){
				if (nodeOut.state == Graph.GraphNode.Status.UNEXPLORED){
					queue.add(nodeOut);
					nodeOut.timestamp = n.timestamp + 1;
					nodeOut.state = Graph.GraphNode.Status.EXPLORED;
				} 
			}
		}
		System.out.println("\n");	
	}

}
