public class Driver {
	
	private Driver() {}
	
	public static void main(String[] args) {
		Graph<String> gra = new Graph<String>();
			
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
		System.out.println("BFS da a: ");
		System.out.println();
		System.out.println("Il tuo programma dovrebbe stampare:");
		System.out.println("Livello A: 0\nLivello B: 1\nLivello C: 2\nLivello D: 4\nLivello E: 3\nLivello F: 4");
		System.out.println();
		System.out.println("Il tuo programma stampa:");
		GraphServices.bfs(gra, a); 
		System.out.println("");
		System.out.println("BFS da F: ");
		System.out.println();
		System.out.println("Il tuo programma dovrebbe stampare:");
		System.out.println("Livello F: 0");
		System.out.println();
		System.out.println("Il tuo programma stampa:");
		GraphServices.bfs(gra, f); 
		System.out.println();

		
	}
}
