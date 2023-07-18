
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;


import java.io.File;
import java.io.FileNotFoundException;

public class Graph<V> {

    private LinkedList<Node<V>> nodes;

    public Graph () {
        nodes = new LinkedList<>();
    }

    public List<Node<V>> getNodes() {
        return nodes;
    }

    public List<Node<V>> getNeighbors(Node<V> n) {
        return n.outEdges;
    }

    public Node<V> addNode(V value) {
        for(Node<V> i : nodes){
            if (i.value.equals(value)) return i;
        }
        Node<V> node = new Node<>(value);
        nodes.add(node);
        return node;
    }

    public void addEdge(Node<V> s, Node<V> t) {
        if(!s.outEdges.contains(t)) s.outEdges.add(t);
        if(!t.outEdges.contains(s)) t.outEdges.add(s);
    }

    public V getNodeValue(Node<V> n) {
        for(Node<V> i : nodes){
            if(i.equals(n)) return i.value;
        }
        return null;
    }

    public void removeEdge(Node<V> v1, Node<V> v2) {
        v1.outEdges.remove(v2);
        v2.outEdges.remove(v1);

    }

    public void removeNode(Node<V> v) {
        for(Node<V> i : nodes){
            i.outEdges.remove(v);
        }
        nodes.remove(v);
    }

    public static Graph<String> readFF(File input) {
        Graph<String> graph = new Graph<>();
        try{
            Scanner scanner;
            scanner = new Scanner(input);
            String data = scanner.nextLine();
            while (scanner.hasNextLine()){
                data = scanner.nextLine();
                data.strip();
                String[] sData = data.split(" ");
                Node<String> n1 = graph.addNode(sData[0]);
                Node<String> n2 = graph.addNode(sData[1]);
                graph.addEdge(n1, n2);
                System.out.println(graph.printAdj());
            }
            
        scanner.close();
        return graph;
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        } 
        return null;
    }

    public String printAdj() {
        StringBuilder sb = new StringBuilder();
        for(Node<V> i : nodes){
            sb.append(i.value + " = { ");
            for(Node<V> adj : i.outEdges){
                sb.append(adj.value + " ");
            }
            sb.append("} \n");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Node<V> i : nodes){
            for(Node<V> adj : i.outEdges){
                sb.append(i.value + " " + adj.value + "\n");
            }
        }
        return sb.toString();
    }


    public int nConComp() {
        int counter  = getConComp().size();
        
        return counter;
    }

    public List<Graph<V>> getConComp() {
        List<Graph<V>> retList = new LinkedList<>();
        for (Node<V> i : nodes) {
            if (i.state == Node.Status.UNEXPLORED) {
                Graph<V> g = new Graph<>();
                Queue<Node<V>> q = new LinkedList<>();
                q.add(i);
                i.state = Node.Status.EXPLORED;
                while (!q.isEmpty()) {
                    Node<V> node = q.remove();
                    List<Node<V>> unexploredEdges = new LinkedList<>(node.outEdges);
                    Node<V> n1 = g.addNode(node.value);
                    for (Node<V> edge : unexploredEdges) {
                        if (edge.state == Node.Status.UNEXPLORED) {
                            edge.state = Node.Status.EXPLORED;
                            Node<V> n2 = g.addNode(edge.value);
                            g.addEdge(n1, n2);
                            q.add(edge);
                        }
                    }
                }
                retList.add(g);
            }
            else continue;
        }
        return retList;
    }
    

    /* Classe interna che descrive il generico nodo del grafo */
    public static class Node<V> implements Cloneable {
        public enum Status {UNEXPLORED, EXPLORED, EXPLORING}

        protected V value;
        protected LinkedList<Node<V>> outEdges;

        protected Status state; // tiene traccia dello stato di esplorazione

        public Node (V val){
            this.value = val;
            outEdges = new LinkedList<>();
            state = Status.UNEXPLORED;
        }
        @Override
        public String toString() {
            return "Node [value=" + value + ", state=" + state + "]";
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }
}
