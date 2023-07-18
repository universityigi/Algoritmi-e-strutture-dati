import java.util.*;

public class GraphServices {

    public static <V> void bfs(Graph<V> g) {
        resetStatus(g);
        for (Node<V> n : g.getNodes()){
            if(n.state == Node.Status.UNEXPLORED)
                bfs(g, n);
        }
    }

    public static <V> void bfs (Graph<V> g, Node<V> src){
        Queue<Node<V>> q = new LinkedList<>();
        q.add(src);
        MinHeap<Node<V>> heap = new MinHeap<>();
        while(!q.isEmpty()){
            Node<V> u = q.remove();
            if(u.state == Node.Status.UNEXPLORED){
                System.out.println(u.getValue());
            u.state = Node.Status.EXPLORING;
            for(Edge<V> e : g.getOutEdges(u)){
                Node<V> v = e.getTarget();
                if(v.state == Node.Status.UNEXPLORED)
                    heap.insert(e.getWeight(),v);
            }
            while(!heap.isEmpty())
                q.add(heap.removeMin().getValue());
            u.state = Node.Status.EXPLORED;
            }
        }

    }


    public static <V> void sssp(Graph<V> g, Node<V> source) {
        int maxDist = 0;
        for(Edge<V> i : g.getEdges())
            maxDist += i.getWeight();
        source.dist = 0;
        MinHeap<Node<V>> heap = new MinHeap<>();
        ArrayList<HeapEntry<Node<V>>> entries = new ArrayList<>();
        for(Node<V> i : g.getNodes()){
            if (i != source)
                i.dist = maxDist++;
            entries.add(heap.insert(i.dist, i));
        }
        System.out.println("INIZIO\n");
        while(!heap.isEmpty()){
            Node<V> n = heap.removeMin().getValue();
            System.out.println(n + " " + n.dist);
            for(Edge<V> e : g.getOutEdges(n)){
                Node<V> v = e.getTarget();
                if(v.dist > n.dist + e.getWeight()){
                    v.dist = n.dist + e.getWeight();
                    for(HeapEntry<Node<V>> i : entries)
                        if(i.getValue() == v)
                            heap.replaceKey(i, v.dist);
                }
            }
        }
        System.out.println("FINE\n");
    }
    
    public static <V> void mst(Graph<V> g) {
        resetStatus(g);
        MinHeap<Edge<V>> heap = new MinHeap<>();
        for (Edge<V> e : g.getEdges()){
            heap.insert(e.getWeight(), e);
        }
        LinkedList<Node<V>> nodes = new LinkedList<>();
        int i = 0;
        for (Node<V> n: g.getNodes()){
            n.map = i++;
            nodes.addLast(n);
        }
        Partition<V> part = new Partition<>(nodes);
        
        while(heap.size() > g.getNodes().size()){
            Edge<V> e = heap.removeMin().getValue();
            Node<V> u = e.getSource();
            Node<V> v = e.getTarget();
            if(u.state == Node.Status.UNEXPLORED || v.state == Node.Status.UNEXPLORED){
                u.state = Node.Status.EXPLORED;
                v.state = Node.Status.EXPLORED;
                System.out.println(u + " -> "+ v);
                if(part.find(u.map).contains(part.find(v.map))){
                    part.union(u.map, v.map);
                }
            }
        }
    }


    public static <V> void resetStatus(Graph<V> g){
        for(Node<V> i: g.getNodes())
            i.state = Node.Status.UNEXPLORED;
    }
}
