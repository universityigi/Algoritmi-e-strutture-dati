import java.util.*;

public class Driver {
	
	private Driver() {}
	
	public static void main(String[] args) {
		Graph<String> gra = new Graph<String>();
			
            	Graph.GraphNode<String> a = gra.addNode("a");
            	Graph.GraphNode<String> b = gra.addNode("b");
            	Graph.GraphNode<String> c = gra.addNode("c");
            	Graph.GraphNode<String> d = gra.addNode("d");
            	Graph.GraphNode<String> e = gra.addNode("e");
            	Graph.GraphNode<String> f = gra.addNode("f");
            	Graph.GraphNode<String> g = gra.addNode("g");
	
		gra.addEdge(a, b);
		gra.addEdge(a, c);
		gra.addEdge(a, e);		
		gra.addEdge(b, c);
		gra.addEdge(b, d);
		gra.addEdge(c, e);		
		gra.addEdge(c, d);		
		gra.addEdge(d, f);
		gra.addEdge(d, g);
		gra.addEdge(e, d);
		gra.addEdge(e, f);
		gra.addEdge(f, g);										
		
		System.out.println("Grafo:");
		System.out.println(gra);
		
				
		
		// Test per BFS
		System.out.println("Nodo sorgente: a, k = 2");
		System.out.println();
		System.out.println("Il tuo programma dovrebbe stampare: a b c d e f");
		System.out.println();
		System.out.print("Il tuo programma stampa: ");
		GraphServices.kdist(gra, a, 2); 
		System.out.println("");
		System.out.println("Nodo sorgente: e, k = 1");
		System.out.println();
		System.out.println("Il tuo programma dovrebbe stampare: e d f");
		System.out.println();
		System.out.print("Il tuo programma stampa: ");
		GraphServices.kdist(gra, e, 1); 
		System.out.println();
		System.out.println("Nodo sorgente: g, k = 3");
		System.out.println();
		System.out.println("Il tuo programma dovrebbe stampare: g");
		System.out.println();
		System.out.print("Il tuo programma stampa: ");
		GraphServices.kdist(gra, g, 3); 
		System.out.println();

		
	}
}
