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
        System.out.println("BFS: ");
        GraphServices.bfs(gra); 
        System.out.println("");
        
        // Test per SSSP
        System.out.println("SSSP dal nodo 'a':");
        GraphServices.sssp(gra, a);
        System.out.println("");

        // MST e' generalmente definito per grafi non diretti
        // Modifichiamo il grafo rendendolo indiretto e pesato
        gra.insertEdge(a, b, 2);
        gra.insertEdge(b, a, 2);
        gra.insertEdge(a, c, 1);
        gra.insertEdge(c, a, 1);
        gra.insertEdge(a, d, 5);
        gra.insertEdge(d, a, 5);
        gra.insertEdge(b, c, 2);
        gra.insertEdge(c, b, 2);
        gra.insertEdge(b, d, 3);
        gra.insertEdge(d, b, 3);
        gra.insertEdge(c, d, 3);
        gra.insertEdge(d, c, 3);
        gra.insertEdge(c, e, 1);		
        gra.insertEdge(e, c, 1);
        gra.insertEdge(e, d, 1);
        gra.insertEdge(d, e, 1);
        gra.insertEdge(d, f, 5);
        gra.insertEdge(f, d, 5);	
        gra.insertEdge(e, f, 2);
        gra.insertEdge(f, e, 2);

        // Test per MST
        System.out.println("MST:");
        GraphServices.mst(gra);
    }
}
