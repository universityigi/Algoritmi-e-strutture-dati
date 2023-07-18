import java.util.*; 

public class Partition<V> {
    // Manteniamo una ArrayList *non necessariamente distinta* per ogni chiave
    HashMap<Integer, ArrayList<Node<V>>> A;

    // Crea una partizione in cui ogni elemento di V forma un insieme a se stante
    // Costo: O(n)
    public Partition(Collection<Node<V>> S) {
        A = new HashMap<>(); 
        int i = 0;
        for(Node<V> n : S) {
            A.put(i, new ArrayList<Node<V>>());
            A.get(i).add(n);
            i++;
        }
    }
    
    // Recupera la lista contenente una data chiave
    // Costo: O(1)
    public List<Node<V>> find(int key) {
        return A.get(key);
    }
    
    // Fonde le liste contenenti due date chiavi, aggiornando i riferimenti in A
    // Costo: O(min(n_u, n_v))
    public void union(int u, int v) {
        if(A.get(u) == A.get(v))
            return;
        
        int n_u = A.get(u).size(), n_v = A.get(v).size();
        ArrayList<Node<V>> A_u = A.get(u), A_v = A.get(v);
        
        if(n_u < n_v) {
            for(Node<V> x : A_u) {
                A.put(x.map, A_v);
                A_v.add(x);
            }
        }
        else {
            for(Node<V> x : A_v) {
                A.put(x.map, A_u);
                A_u.add(x);
            }
        }
    }
}