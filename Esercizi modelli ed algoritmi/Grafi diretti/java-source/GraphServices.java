import java.util.*;

public class GraphServices<V> {

    public static <V> void sweep(Graph<V> g) {
        g.resetStatus();
        g.getNodes().get(0).timestamp = 0;
        
        for (Graph.Node<V> e : g.getNodes()){
            sweep(e);
        }
        
    }

    public static <V> void sweep(Graph.Node<V> n){
        if(n.state != Graph.Node.Status.UNEXPLORED) return;
        n.state = Graph.Node.Status.EXPLORING;
        int i = n.timestamp;
        for(Graph.Node<V> e : n.outEdges){
            if(e.state == Graph.Node.Status.UNEXPLORED){
                e.timestamp = i++;
                System.out.println(n.value + " -> " + e.value + " : " + "DISCOVERY");
                sweep(e);
            } else if(e.state == Graph.Node.Status.EXPLORING){
                System.out.println(n.value + " -> " + e.value + " : " + "BACK");
            } else{
                if(e.timestamp > n.timestamp)
                    System.out.println(n.value + " -> " + e.value + " : " + "FORWARD");
                else System.out.println(n.value + " -> " + e.value + " : " + "CROSS");
            }
        }
        n.state = Graph.Node.Status.EXPLORED;
    }
    
    public static <V> void topologicalSort(Graph<V> g) {
        if(hasLoop(g)){
            return;
        }
        int n = g.getNodes().size();
        System.out.println("Size is = "+n);
        for(Graph.Node<V> node : g.getNodes()){
            if(node.state != Graph.Node.Status.UNEXPLORED)
                continue; 
            LinkedList<Graph.Node<V>> ret = new LinkedList<>();
            dfs(g, node, ret);
            while(!ret.isEmpty())
                ret.removeLast().timestamp = n--;
                
        }
        for(Graph.Node<V> node : g.getNodes()){
            System.out.println(node.value + " has timestamp of " + node.timestamp);
        }

    }

    private static  <V> boolean hasLoop(Graph<V> g) {
        for(Graph.Node<V> node : g.getNodes()){
            g.resetStatus();
            if(node.state != Graph.Node.Status.UNEXPLORED)
                continue;
            if(hasLoop(g, node))
                return true;
                
        }
        g.resetStatus();
        return false;
    }

    private static  <V> boolean hasLoop(Graph<V> g, Graph.Node<V> u) {
        u.state = Graph.Node.Status.EXPLORING;
        for(Graph.Node<V> v : u.outEdges)
            if(v.state == Graph.Node.Status.UNEXPLORED)
                return hasLoop(g, v);
            else if(v.state == Graph.Node.Status.EXPLORING){
                System.out.println("FOUND LOOP starting from node "+ u.value + "while analysing node "+ v.value);
                return true;
            }
                

        u.state = Graph.Node.Status.EXPLORED;
        return false;
    }

    public static <V> void strongConnectedComponents(Graph<V> g) {
        for(Graph.Node<V> node : g.getNodes()){
            if(node.state != Graph.Node.Status.UNEXPLORED)
                continue;
            
            StringBuilder sb = new StringBuilder();
            List<Graph.Node<V>> dir = new LinkedList<>();
            dfs(g, node, dir);
            for(Graph.Node<V> u : dir)              //resetta solamente i potenziali nodi connessi
                u.state = Graph.Node.Status.UNEXPLORED;
            List<Graph.Node<V>> inv = new LinkedList<>();
            invertedDfs(g, node, inv);
            if (dir.size() == 0)
                continue;

            for(Graph.Node<V> u : inv){             //perchè si inizia da inv e funziona?
                if(dir.contains(u)) sb.append(" " + u.value );
                else u.state = Graph.Node.Status.UNEXPLORED;
            }

            sb.append("\n");
            System.out.println("Lista nodi:\t" + sb);

        }
    }

    public static <V> void dfs(Graph<V> g, Graph.Node<V> u, List<Graph.Node<V>> list){
        if(u.state != Graph.Node.Status.UNEXPLORED)
            return;
        list.add(u);
        u.state = Graph.Node.Status.EXPLORING;
        for(Graph.Node<V> v : u.outEdges){
                dfs(g, v, list);
        }
        u.state = Graph.Node.Status.EXPLORED;
    }

    public static <V> void invertedDfs(Graph<V> g, Graph.Node<V> u, List<Graph.Node<V>> list){
    if(u.state != Graph.Node.Status.UNEXPLORED)
        return;
    list.add(u);
    u.state = Graph.Node.Status.EXPLORING;
    for(Graph.Node<V> v : u.inEdges){
        invertedDfs(g, v, list);
    }
    u.state = Graph.Node.Status.EXPLORED;
    }
}
