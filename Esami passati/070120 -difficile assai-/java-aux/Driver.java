public class Driver {
	
	private Driver() {}
	
	public static void main(String[] args) {
		Graph<String> gra = new Graph<String>();
	
		Node<String> a = new Node<String>(new String("a"));
		Node<String> b = new Node<String>(new String("b"));		
		Node<String> c = new Node<String>(new String("c"));		
		Node<String> d = new Node<String>(new String("d"));
		Node<String> e = new Node<String>(new String("e"));
		Node<String> f = new Node<String>(new String("f"));
		
		gra.insertNode(a); 		
		gra.insertNode(b); 		
		gra.insertNode(c); 		
		gra.insertNode(d); 		
		gra.insertNode(e); 		
		gra.insertNode(f); 		
	
		gra.insertEdge(a, b, 2);
		gra.insertEdge(a, c, 1);
		gra.insertEdge(a, d, 5);
		gra.insertEdge(b, c, 2);
		gra.insertEdge(b, d, 3);
		gra.insertEdge(c, d, 3);
		gra.insertEdge(c, e, 1);		
		gra.insertEdge(e, d, 1);
		gra.insertEdge(d, f, 5);		
		gra.insertEdge(e, f, 2);
		
		System.out.println("Grafo:");
		System.out.println(gra);
		
				
		
		// Test per BFS
		System.out.println("BFS da a: ");
		System.out.println();
		System.out.println("Il tuo programma dovrebbe stampare:");
		System.out.println("Livello a: 0\nLivello b: 1\nLivello c: 1\nLivello d: 1\nLivello e: 2\nLivello f: 2");
		System.out.println();
		System.out.println("Il tuo programma stampa:");
		GraphServices.bfs(gra, a); 
		System.out.println("");
		System.out.println("BFS da f: ");
		System.out.println();
		System.out.println("Il tuo programma dovrebbe stampare:");
		System.out.println("Livello f: 0");
		System.out.println();
		System.out.println("Il tuo programma stampa:");
		GraphServices.bfs(gra, f); 
		System.out.println("");

		
		// Test per SSSP
		System.out.println("SSSP dal nodo 'a':");
		System.out.println();
		System.out.println("Il tuo programma dovrebbe stampare:");
		System.out.println("Distanze dal nodo a [a:0, b:2, d:3, c:1, e:2, f:4]");
		System.out.println();
		System.out.println("Il tuo programma stampa:");
		System.out.println(GraphServices.sssp(gra, a));
		System.out.println("");

		System.out.println("SSSP dal nodo 'c':");
		System.out.println();
		System.out.println("Il tuo programma dovrebbe stampare:");
		System.out.println("Distanze dal nodo c [a:100000, b:100000, d:2, c:0, e:1, f:3]");
		System.out.println();
		System.out.println("Il tuo programma stampa:");
		System.out.println(GraphServices.sssp(gra, c));
		System.out.println("");
		
		// Test per APSP
		System.out.println("APSP:");
		System.out.println();
		System.out.println("Il tuo programma dovrebbe stampare:");
		System.out.println("Distanze dal nodo a [a:0, b:2, d:3, c:1, e:2, f:4]\n" +
							"Distanze dal nodo b [a:100000, b:0, d:3, c:2, e:3, f:5]\n" +
							"Distanze dal nodo d [a:100000, b:100000, d:0, c:100000, e:100000, f:5]\n" +
							"Distanze dal nodo c [a:100000, b:100000, d:2, c:0, e:1, f:3]\n" +
							"Distanze dal nodo e [a:100000, b:100000, d:1, c:100000, e:0, f:2]\n" +
							"Distanze dal nodo f [a:100000, b:100000, d:100000, c:100000, e:100000, f:0]");
		System.out.println();
		System.out.println("Il tuo programma stampa:");
		GraphServices.apsp(gra);
		System.out.println();
	}
}
