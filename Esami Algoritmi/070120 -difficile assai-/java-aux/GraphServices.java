import java.util.*;

public class GraphServices {


	public static <V> void bfs(Graph<V> g, Node<V> source) {
		resetAll(g);
		int lvl = 0;
		Queue<Node<V>> q = new LinkedList<>();
		q.add(source);
		source.timestamp = lvl;
		while(!q.isEmpty()){
			Node<V> u = q.remove();
			if(u.stato != Node.Stato.UNEXPLORED )
				continue;
			System.out.println("Livello "+ u.getElement()+": "+ u.timestamp);
			u.stato = Node.Stato.EXPLORING;
			for(Edge<V> e : g.getOutEdges(u)){
				Node<V> v = e.getTarget();
				if(v.stato == Node.Stato.UNEXPLORED){
					q.add(v);
					v.timestamp = lvl + 1;
				}
			}
			lvl++;
			u.stato = Node.Stato.EXPLORED;
		}
	}

	private static <V> void resetAll(Graph<V> g) {
		for(Node<V> node : g.getNodes()){
			node.stato = Node.Stato.UNEXPLORED;
			node.timestamp = 0;
		}
	}

	public static <V> String sssp(Graph<V> g, Node<V> source) {
		resetAll(g);

		for(Node<V> n : g.getNodes())
			n.timestamp = 100000;
		source.timestamp = 0;

		StringBuilder sb = new StringBuilder();
		sb.append("Distanze dal nodo "+source.getElement() + " [");
		MinHeap<Node<V>> heap = new MinHeap<>();
		HashMap<HeapEntry<Node<V>>, Node<V>> hashMap = new HashMap<>();
		hashMap.put(heap.insert(source.timestamp, source), source);
		for(Node<V> n : g.getNodes())
			hashMap.put(heap.insert(n.timestamp, n), n);

		while(!heap.isEmpty()){
			Node<V> u = hashMap.get(heap.removeMin());
			for(Edge<V> e : g.getOutEdges(u)){
				Node<V> v = e.getTarget();
				if(v.timestamp > u.timestamp + e.getWeight()){
					v.timestamp = u.timestamp + e.getWeight();
					for(HeapEntry<Node<V>> he : hashMap.keySet())
						if(he.getValue() == v) heap.replaceKey(he, v.timestamp);					
				}
			}
		}

		for(Node<V> n : g.getNodes())
			sb.append(n.getElement()+":"+n.timestamp + " ");

		sb.append("]");
		return sb.toString();
	}

	public static <V> void apsp(Graph<V> g) {
		for(Node<V> n : g.getNodes()){
			resetAll(g);
			System.out.println(sssp(g, n));
		}
			


	}
}
