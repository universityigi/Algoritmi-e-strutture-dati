import java.util.*;

public class GraphServices {


	public static <V> void bfs(Graph<V> g, Graph.GraphNode<V> source) {
		g.resetStatus();
		//NIENTE RICORSIONE!!!
		Queue<Graph.GraphNode<V>> q = new LinkedList<>();
		q.add(source);

		while(!q.isEmpty()){
			Graph.GraphNode<V> u = q.remove();
			if(u.state != Graph.GraphNode.Status.UNEXPLORED)
				continue;
			System.out.println("Livello "+u.value+": " + u.timestamp);
			u.state = Graph.GraphNode.Status.EXPLORING;
			for(Graph.GraphNode<V> v : u.outEdges){
				if(v.state == Graph.GraphNode.Status.UNEXPLORED){
					v.timestamp = u.timestamp+1;
					q.add(v);
				}
					
			}
			u.state = Graph.GraphNode.Status.EXPLORED;
		}
	}
}
