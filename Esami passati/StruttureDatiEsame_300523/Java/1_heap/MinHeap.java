import java.util.*; 

public class MinHeap<V> {
    
    private ArrayList<HeapEntry<V>> a; 
    
    /* Costruttore */
    public MinHeap() {
        a = new ArrayList<HeapEntry<V>>(); 
        a.add(null); // per un indirizzamento più naturale (radice -> a.get(1))
    }
    
    /* Restituisce la radice dell'heap */
    public HeapEntry<V> getMin() { 
        if(isEmpty()) return null; 
        return a.get(1); 
    }
    
    /* Metodi ausiliari per eseguire upheap e downheap e ripristinare la proprietà di heap */
    private void upheap(HeapEntry<V> e) {
        while(e.getIndex() != 1) {
            int eIdx = e.getIndex(); 
            int pIdx = eIdx / 2; 
            HeapEntry<V> p = a.get( pIdx ); 
            if(e.getKey() >= p.getKey()) break;  
            a.set( pIdx, e ); 
            a.set( eIdx, p ); 
            e.setIndex( pIdx ); 
            p.setIndex( eIdx ); 
        }
    }
    
    private boolean hasChildren(HeapEntry<V> e) {
        return 2*e.getIndex() <= size(); 
    }

    private boolean hasBothChildren(HeapEntry<V> e) {
        return 2*e.getIndex()+1 <= size(); 
    }
    
    private void downheap(HeapEntry<V> e) {
        while(hasChildren(e)) {
            int eIdx = e.getIndex(); 
            int lIdx = 2*eIdx; 
            int rIdx = 2*eIdx + 1; 
            HeapEntry<V> l = a.get( lIdx ); 
            if(hasBothChildren(e)) {
                HeapEntry<V> r = a.get( rIdx ); 
                int minIdx = ( r.getKey() < l.getKey() ? rIdx : lIdx ); 
                HeapEntry<V> min = a.get( minIdx ); 
                if(e.getKey() <= min.getKey()) break; 
                a.set( minIdx, e ); 
                a.set( eIdx, min );  
                e.setIndex( minIdx ); 
                min.setIndex( eIdx ); 
            }
            else { // e ha solo il figlio sinistro
                if(e.getKey() <= l.getKey()) break; 
                a.set( lIdx, e ); 
                a.set( eIdx, l ); 
                e.setIndex( lIdx ); 
                l.setIndex( eIdx ); 
            }
        }
    }
    
    /* Inserisce una nuova entry nell'heap con chiave p e valore value */
    public HeapEntry<V> insert(int p, V value) {
        HeapEntry<V> e = new HeapEntry<V>(size()+1, p, value); 
        a.add(e); 
        upheap(e); 
        return e; 
    }
    
    /* Restituisce true se l'heap è vuoto, false altrimenti */
    public boolean isEmpty() { 
        return size()==0; 
    }
    
    /* Rimuove la radice dell'heap */
    public HeapEntry<V> removeMin() {
        HeapEntry<V> e = getMin(); 
        HeapEntry<V> last = a.get( size() ); // ultimo elemento
        a.set( 1, last ); 
        last.setIndex(1); 
        a.remove( size() ); 
        downheap(last); 
        return e; 
    }
    
    /* Restituisce la dimensione */
    public int size() { 
        return a.size()-1; 
    }
    
    /* Esegue heapsort sull'array in input */
    public static void heapsort(int[] v) {
        MinHeap<Object> t = new MinHeap<Object>(); 
        for(int i=0; i<v.length; i++)
            t.insert(v[i], null); 
        int c = 0; 
        while(!t.isEmpty()) {
            v[c++] = t.removeMin().getKey(); 
        }
    }
    
    /* Cambia il valore di una entry */
    public HeapEntry<V> replaceValue(HeapEntry<V> e, V value) {
        e.setValue(value); 
        return e; 
    }
    
    /* Cambia la chiave di una entry */
    public HeapEntry<V> replaceKey(HeapEntry<V> e, int key) {
        int oldKey = e.getKey(); 
        e.setKey(key); 
        if(key < oldKey) upheap(e); 
        else downheap(e); 
        return e; 
    }
    
    /* Rimuove la entry dall'heap */
    public HeapEntry<V> remove(HeapEntry<V> e) {
        HeapEntry<V> last = a.get( size() ); // ultimo elemento
        a.set( e.getIndex(), last ); 
        last.setIndex( e.getIndex() ); 
        a.remove( size() ); 
        if(last.getKey() < e.getKey()) 
            upheap(last); 
        else
            downheap(last); 
        return e; 
    }
}
