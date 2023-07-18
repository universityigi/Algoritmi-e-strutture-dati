import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Graph<V> {

    private LinkedList<GraphNode<V>> nodes;
    private int n_nodes;
    private int n_edges;

    /**
     * Costruttore
     *
     */	
    public Graph () {
        this.nodes = new LinkedList<GraphNode<V>>();
    }

    /**
     * Restituisce una collezione contenente i nodi del grafo
     *
     */	
    @SuppressWarnings("unchecked")
	public List<GraphNode<V>> getNodes() {
    	List<GraphNode<V>> ret = new LinkedList<>();
    	for(GraphNode<V> n : this.nodes) {
    		try {
				ret.add((GraphNode<V>) n.clone());
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
    	}
        return (List<GraphNode<V>>) ret;
    }
    
	/**
     * Restituisce una lista contenente i vicini uscenti del nodo dato
     *
     */	
    @SuppressWarnings("unchecked")
	public List<GraphNode<V>> getOutNeighbors(GraphNode<V> n){
    	List<GraphNode<V>> ret = new LinkedList<>();
    	for(GraphNode<V> edge : n.outEdges) {
    		try {
				ret.add((GraphNode<V>) edge.clone());
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
    	}
        return (List<GraphNode<V>>) ret;
    }

	/**
     * Restituisce una lista contenente i vicini entranti del nodo dato
     *
     */	
	@SuppressWarnings("unchecked")
	public List<GraphNode<V>> getInNeighbors(GraphNode<V> n){
    	List<GraphNode<V>> ret = new LinkedList<>();
    	for(GraphNode<V> edge : n.inEdges) {
    		try {
				ret.add((GraphNode<V>) edge.clone());
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
    	}
        return (List<GraphNode<V>>) ret;
    }

    /**
     * Aggiunge un nuovo nodo al grafo
     *
     */
    public GraphNode<V> addNode(V value) {
        GraphNode<V> n = new GraphNode<V>();
        n.value = value;

        n.outEdges = new LinkedList<GraphNode<V>>();
		n.inEdges = new LinkedList<GraphNode<V>>();

        n.state = GraphNode.Status.UNEXPLORED;
        n.timestamp = 0;
        for(GraphNode<V> node : this.nodes) {
        	if(node.value.equals(value))
        		return node;
        }
        this.nodes.add(n);
        this.n_nodes++;
        return n;
    }

	 /**
     * Aggiunge un nuovo arco diretto al grafo dal nodo nodo s al nodo t
     *
     */
    public void addEdge(GraphNode<V> s, GraphNode<V> t) {
        s.outEdges.add(t);
		t.inEdges.add(s);
        this.n_edges++;
    }

	 /**
     * Restituisce il valore associato al nodo
     *
     */
    public V getNodeValue(GraphNode<V> n) {
        return n.value;
    }
	
	 /**
     * Rimuove l'arco tra i nodi v1 e v2
     *
     */
	public void removeEdge(GraphNode<V> v1, GraphNode<V> v2){
		if(getOutNeighbors(v1).contains(v2)){
			v1.outEdges.remove(v2);
			v2.inEdges.remove(v1);
			this.n_edges--;
		}
    }

	 /**
     * Rimuove il nodo v e tutti gli archi incidenti
     *
     */
    public void removeNode(GraphNode<V> v){
    	if(this.nodes.contains(v)) {
			LinkedList<GraphNode<V>> out_aux = new LinkedList<GraphNode<V>>(v.outEdges);
			LinkedList<GraphNode<V>> in_aux = new LinkedList<GraphNode<V>>(v.inEdges);
	    	for(GraphNode<V> o : out_aux) {
	    		this.removeEdge(v, o);
	    	}
			for(GraphNode<V> i : in_aux) {
	    		this.removeEdge(i, v);
	    	}
    	}
    	this.nodes.remove(v);
		this.n_nodes--;
    }

	 /**
     * Riporta a UNEXPLORED lo stato di tutti i nodi
     *
     */
	public void resetStatus(){
		List<Graph.GraphNode<V>> nodes = this.getNodes();
		for(Graph.GraphNode<V> node : nodes){
			node.state = Graph.GraphNode.Status.UNEXPLORED;
			node.timestamp = 0;
		}
	}
    
	 /**
     * Restituisce una stringa che rappresenta il grafo
     *
     */
    @Override
    public String toString(){
    	HashMap<GraphNode<V>, Graph.GraphNode.Status> savedStatus = new HashMap<>();
    	for(GraphNode<V> node : this.nodes) {
    		savedStatus.put(node, node.state);
    		node.state = Graph.GraphNode.Status.UNEXPLORED;
    	}
    	
    	StringBuffer toRet = new StringBuffer();
    	toRet.append(this.n_nodes + " " + this.n_edges + "\n");
    	for(GraphNode<V> node : this.nodes) {
    		if(node.state == Graph.GraphNode.Status.UNEXPLORED)
    			DFSprintEdges(node, toRet);
    	}
    	
    	for(GraphNode<V> node : this.nodes) {
    		node.state = savedStatus.get(node);
    	}
    	return toRet.toString();
    }

	 /**
     * Modifica una variabile StringBuffer che contiene nodi e archi del grafo
     * NEl farlo effettua una DFS
     *
     */
    private void DFSprintEdges(GraphNode<V> node, StringBuffer str) {
    	if(node.state != GraphNode.Status.UNEXPLORED)
    		return;
    	node.state = GraphNode.Status.EXPLORING;
    	for(GraphNode<V> e : node.outEdges) {
    		str.append(node.value + " -> " + e.value + "\n");
    	}
    	for(GraphNode<V> e : node.outEdges) {
    		if(e.state == GraphNode.Status.UNEXPLORED)
    			DFSprintEdges(e, str);
    	}
    	node.state = GraphNode.Status.EXPLORED;
	}

	 /**
     * Classe interna che descrive il generico nodo del grafo, con liste dei vicini uscenti ed entranti
     *
     */
	public static class GraphNode<V> implements Cloneable{
        public enum Status {UNEXPLORED, EXPLORED, EXPLORING}

        protected V value;
        protected LinkedList<GraphNode<V>> outEdges;
		protected LinkedList<GraphNode<V>> inEdges;

        // keep track status
        protected Status state;
        protected int timestamp;

		@Override
		public String toString() {
			return "GraphNode [value=" + value + ", state=" + state + "]";
		}

		@Override
		protected Object clone() throws CloneNotSupportedException {
			return (GraphNode<V>) this;
		}
    }
}
