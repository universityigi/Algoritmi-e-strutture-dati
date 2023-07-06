public	class Node<V> {
    public static enum Status {
        UNEXPLORED, EXPLORING, EXPLORED;
    };
    
    private V value;

    public Status state; // tiene traccia dello stato di esplorazione
    int map; // utile in partition union e find
    int timestamp; // utile per associare valori interi ai vertici
    int dist; // utile per memorizzare distanze in algoritmi per cammini minimi

    public Node(V e) {
        value = e;
        state = Status.UNEXPLORED;
        timestamp = 0;
    }

    public V getValue() {
        return value;
    }

    public String toString() {
        return value.toString();
    }
}
