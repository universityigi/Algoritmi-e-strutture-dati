
public	class Edge<V> {

	private Node<V> source;
	private Node<V> target; 
	private Integer weight; 
	
	public Edge(Node<V> source, Node<V> target, Integer weight) {
		this.source = source;
		this.target = target; 
		this.weight = weight; 
	}

	// Restituisce il nodo da cui parte l'arco
	public Node<V> getSource() { return source; }
	
	// Restituisce il nodo puntato dall'arco
	public Node<V> getTarget() { return target; } 

	// Restituisce il peso dell'arco
	public Integer getWeight() {
		return weight;
	}

	public String toString() {
		return target.toString() + "[peso=" + weight + "]";
	}

}

