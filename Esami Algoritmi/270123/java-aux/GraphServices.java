import java.util.*;

public class GraphServices {


	public static <V> int max_dist(Graph<V> g, Graph.GraphNode<V> source) {
		g.resetStatus();
		Queue<Graph.GraphNode<V>> q = new LinkedList<>();
		int maxLvl = 0;
		q.add(source);
		while(!q.isEmpty()){
			Graph.GraphNode<V> u = q.remove();
			System.out.println("Livello "+u.value+": " + u.timestamp);
			u.state = Graph.GraphNode.Status.EXPLORING;
			for(Graph.GraphNode<V> v : u.outEdges){
				if(v.state == Graph.GraphNode.Status.UNEXPLORED){
					v.timestamp = u.timestamp+1;
					q.add(v);
					if(v.timestamp > maxLvl)
						maxLvl = v.timestamp;
				}		
			}

			u.state = Graph.GraphNode.Status.EXPLORED;
		}

		return maxLvl;
	}
}
