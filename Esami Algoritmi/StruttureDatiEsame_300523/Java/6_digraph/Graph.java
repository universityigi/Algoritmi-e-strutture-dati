import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Graph<V> {

    private LinkedList<Node<V>> nodes;
    private int n_nodes;
    private int n_edges;

    /* Costruttore */
    public Graph () {
        this.nodes = new LinkedList<Node<V>>();
    }

    /* Restituisce una collezione contenente i nodi del grafo */
    @SuppressWarnings("unchecked")
    public List<Node<V>> getNodes() {
        List<Node<V>> ret = new LinkedList<>();
        
        for(Node<V> n : this.nodes) {
            try {
                ret.add((Node<V>) n.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        
        return (List<Node<V>>) ret;
    }
    
    /* Restituisce una lista contenente i vicini uscenti del nodo dato */
    @SuppressWarnings("unchecked")
    public List<Node<V>> getOutNeighbors(Node<V> n) {
        List<Node<V>> ret = new LinkedList<>();
        
        for(Node<V> edge : n.outEdges) {
            try {
                ret.add((Node<V>) edge.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        
        return (List<Node<V>>) ret;
    }

    /* Restituisce una lista contenente i vicini entranti del nodo dato */
    @SuppressWarnings("unchecked")
    public List<Node<V>> getInNeighbors(Node<V> n) {
        List<Node<V>> ret = new LinkedList<>();
        
        for(Node<V> edge : n.inEdges) {
            try {
                ret.add((Node<V>) edge.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        
        return (List<Node<V>>) ret;
    }

    /* Aggiunge un nuovo nodo al grafo */
    public Node<V> addNode(V value) {
        Node<V> n = new Node<V>();
        n.value = value;

        n.outEdges = new LinkedList<Node<V>>();
        n.inEdges = new LinkedList<Node<V>>();

        n.state = Node.Status.UNEXPLORED;
        n.timestamp = 0;
        for(Node<V> node : this.nodes) {
            if(node.value.equals(value))
                return node;
        }
        
        this.nodes.add(n);
        this.n_nodes++;
        return n;
    }

    /* Aggiunge un nuovo arco diretto al grafo dal nodo nodo s al nodo t */
    public void addEdge(Node<V> s, Node<V> t) {
        s.outEdges.add(t);
        t.inEdges.add(s);
        this.n_edges++;
    }

    /* Restituisce il valore associato al nodo */
    public V getNodeValue(Node<V> n) {
        return n.value;
    }
    
    /* Rimuove l'arco tra i nodi v1 e v2 */
    public void removeEdge(Node<V> v1, Node<V> v2) {
        if(getOutNeighbors(v1).contains(v2)){
            v1.outEdges.remove(v2);
            v2.inEdges.remove(v1);
            this.n_edges--;
        }
    }

    /* Rimuove il nodo v e tutti gli archi incidenti */
    public void removeNode(Node<V> v) {
        if(this.nodes.contains(v)) {
            LinkedList<Node<V>> out_aux = new LinkedList<Node<V>>(v.outEdges);
            LinkedList<Node<V>> in_aux = new LinkedList<Node<V>>(v.inEdges);
            
            for(Node<V> o : out_aux)
                this.removeEdge(v, o);

            for(Node<V> i : in_aux)
                this.removeEdge(i, v);
        }
        
        this.nodes.remove(v);
        this.n_nodes--;
    }

    /* Riporta a UNEXPLORED lo stato di tutti i nodi */
    public void resetStatus() {
        List<Graph.Node<V>> nodes = this.getNodes();
        
        for(Graph.Node<V> node : nodes) {
            node.state = Graph.Node.Status.UNEXPLORED;
            node.timestamp = 0;
        }
    }
    
    /* Restituisce una stringa che rappresenta il grafo */
    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
        for(Node<V> node : this.nodes) {
            for (Node<V> edge: getOutNeighbors(node))
                str.append("(" + node.value + ", " + edge.value + ") ");
            str.append("\n");
        }
        return str.toString();
    }

    /* Classe interna che descrive il generico nodo del grafo, con liste dei vicini uscenti ed entranti */
    public static class Node<V> implements Cloneable {
        public enum Status {UNEXPLORED, EXPLORED, EXPLORING}

        protected V value;
        protected LinkedList<Node<V>> outEdges;
        protected LinkedList<Node<V>> inEdges;

        protected Status state; // tiene traccia dello stato di esplorazione
        protected int map; // utile in partition union e find
        protected int timestamp; // utile per associare valori interi ai vertici
        protected int dist; // utile per memorizzare distanze in algoritmi per cammini minimi

        @Override
        public String toString() {
            return "Node [value=" + value + ", state=" + state + "]";
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return (Node<V>) this;
        }
    }
}
