public class Driver {
	
	private Driver() {}
	
	public static void main(String[] args) {
		Graph<String> gra = new Graph<String>();
		int distanza;
			
            	Graph.GraphNode<String> a = gra.addNode("A");
            	Graph.GraphNode<String> b = gra.addNode("B");
            	Graph.GraphNode<String> c = gra.addNode("C");
            	Graph.GraphNode<String> d = gra.addNode("D");
            	Graph.GraphNode<String> e = gra.addNode("E");
            	Graph.GraphNode<String> f = gra.addNode("F");
	
		gra.addEdge(a, b);
		gra.addEdge(b, c);
		gra.addEdge(c, e);		
		gra.addEdge(e, d);
		gra.addEdge(e, f);
		gra.addEdge(d, b);		
		
		System.out.println("Grafo:");
		System.out.println(gra);
		
				
		
		// Test per BFS
		System.out.println("Nodo sorgente: A");
		System.out.println();
		System.out.println("Il tuo programma dovrebbe stampare: 4");
		System.out.println();
		distanza = GraphServices.max_dist(gra, a); 
		System.out.println("Il tuo programma stampa: " + distanza);
		System.out.println("");
		System.out.println("Nodo sorgente: E");
		System.out.println();
		System.out.println("Il tuo programma dovrebbe stampare: 3");
		System.out.println();
		distanza = GraphServices.max_dist(gra, e); 
		System.out.println("Il tuo programma stampa: " + distanza);
		System.out.println();

		
	}
}
