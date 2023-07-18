import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Graph<V> {

    private LinkedList<Node<V>> nodes;
    private int n_nodes;
    private int n_edges;

    public Graph () {
        this.nodes = new LinkedList<Node<V>>();
    }

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

    public void addEdge(Node<V> s, Node<V> t) {
        s.outEdges.add(t);
        t.inEdges.add(s);
        this.n_edges++;
    }

    public V getNodeValue(Node<V> n) {
        return n.value;
    }
    
    public void removeEdge(Node<V> v1, Node<V> v2) {
        if(getOutNeighbors(v1).contains(v2)) {
            v1.outEdges.remove(v2);
            v2.inEdges.remove(v1);
            this.n_edges--;
        }
    }

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

    public void resetStatus() {
        List<Graph.Node<V>> nodes = this.getNodes();
        
        for(Graph.Node<V> node : nodes) {
            node.state = Graph.Node.Status.UNEXPLORED;
            node.timestamp = 0;
        }
    }
    
    @Override
    public String toString() {
        HashMap<Node<V>, Graph.Node.Status> savedStatus = new HashMap<>();
        for(Node<V> node : this.nodes) {
            savedStatus.put(node, node.state);
            node.state = Graph.Node.Status.UNEXPLORED;
        }
        
        StringBuffer toRet = new StringBuffer();
        toRet.append(this.n_nodes + " " + this.n_edges + "\n");
        for(Node<V> node : this.nodes) {
            if(node.state == Graph.Node.Status.UNEXPLORED)
                DFSprintEdges(node, toRet);
        }
        
        for(Node<V> node : this.nodes) {
            node.state = savedStatus.get(node);
        }
        return toRet.toString();
    }

    private void DFSprintEdges(Node<V> node, StringBuffer str) {
        if(node.state != Node.Status.UNEXPLORED)
            return;
        
        node.state = Node.Status.EXPLORING;
        for(Node<V> e : node.outEdges)
            str.append(node.value + " -> " + e.value + "\n");
        
        for(Node<V> e : node.outEdges) {
            if(e.state == Node.Status.UNEXPLORED)
                DFSprintEdges(e, str);
        }
        node.state = Node.Status.EXPLORED;
    }

    /* Classe interna che descrive il generico nodo del grafo */
    public static class Node<V> implements Cloneable {
        public enum Status {UNEXPLORED, EXPLORED, EXPLORING}

        protected V value;
        protected LinkedList<Node<V>> outEdges;
        protected LinkedList<Node<V>> inEdges;

        protected Status state; // tiene traccia dello stato di esplorazione
        protected int timestamp; // utile per associare valori interi ai vertici

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
