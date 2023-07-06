public	class Node<V> {

	public static enum Stato{
		UNEXPLORED, EXPLORING, EXPLORED;
	};
	
	private V element;
	public Stato stato;
	
	int timestamp; // Utile per associare valori interi ai vertici
	
	public Node(V e) {
		element = e;
		stato = Stato.UNEXPLORED;
		timestamp = 0;
	}

	public V getElement() { return element; } 

	public String toString() {
		return element.toString();
	}
}

