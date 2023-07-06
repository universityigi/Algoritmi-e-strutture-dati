/* Entry a chiave intera e valore generico */
public class HeapEntry<V> {
    /* indice in cui e' memorizzato il valore */
    private int index;

    /* chiave (su cui si fa l'ordinamento) */
    private int key;

    /* valore */
    private V value;

    /* Costruttore */
    public HeapEntry(int index, int key, V value) {
        this.index = index;
        this.key = key;
        this.value = value;
    }

    /* GET methods */
    public int getIndex() {
        return index;
    }

    public int getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    /* SET methods */
    public void setIndex(int index) {
        this.index = index;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setValue(V value) {
        this.value = value;
    }
    
    public String toString() {
        return "[chiave: "+key+", valore: "+value+"]"; 
    }
}
