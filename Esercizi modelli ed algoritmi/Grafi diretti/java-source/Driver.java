public class Driver {

    public static void print() {
        System.out.println("Richiesto argomento: {graph, sweep, top_sort, strong_cc}");
    }

    public static void main(String[] argv) {
        if (argv.length < 1) {
            print();
            return;
        }

        if (argv[0].equals("graph")) {
            Graph<String> graph = new Graph<String>();

            Graph.Node<String> a = graph.addNode("a");
            Graph.Node<String> b = graph.addNode("b");
            Graph.Node<String> c = graph.addNode("c");
            Graph.Node<String> d = graph.addNode("d");
            Graph.Node<String> e = graph.addNode("e");
            Graph.Node<String> f = graph.addNode("f");
            Graph.Node<String> g = graph.addNode("g");
            Graph.Node<String> h = graph.addNode("h");

            graph.addEdge(a, b);
            graph.addEdge(a, f);
            graph.addEdge(b, c);
            graph.addEdge(b, f);
            graph.addEdge(c, d);
            graph.addEdge(d, b);
            graph.addEdge(e, f);
            graph.addEdge(f, c);
            graph.addEdge(g, h);

            System.out.println("Grafo iniziale");
            //System.out.println(graph.getNodes());
            System.out.println(graph);

            System.out.println("Rimozione arco (" + f + ", " + c + ")");
            graph.removeEdge(f,c);
            System.out.println(graph);

            System.out.println("Rimozione arco (" + c + ", " + b + ")");
            graph.removeEdge(c,b);
            System.out.println(graph);

            System.out.println("Rimozione arco (" + c + ", " + d + ")");
            graph.removeEdge(c,d);
            System.out.println(graph);

            System.out.println("Inserimento arco (" + c + ", " + f + ")");
            graph.addEdge(c,f);
            System.out.println(graph);

            System.out.println("Inserimento arco (" + d + ", " + c + ")");
            graph.addEdge(d,c);
            System.out.println(graph);

            System.out.println("Rimozione nodo (" + d + ")");
            graph.removeNode(d);
            System.out.println(graph);

            System.out.println("Rimozione nodo (" + f + ")");
            graph.removeNode(f);
            System.out.println(graph);

            System.out.println("Rimozione nodo (" + c + ")");
            graph.removeNode(c);
            System.out.println(graph);
        }
        else if (argv[0].equals("sweep")) {
            Graph<String> graph = new Graph<String>();

            Graph.Node<String> a = graph.addNode("a");
            Graph.Node<String> b = graph.addNode("b");
            Graph.Node<String> c = graph.addNode("c");
            Graph.Node<String> d = graph.addNode("d");
            Graph.Node<String> e = graph.addNode("e");
            Graph.Node<String> f = graph.addNode("f");
            Graph.Node<String> g = graph.addNode("g");
            Graph.Node<String> h = graph.addNode("h");

            graph.addEdge(a, b);
            graph.addEdge(a, f);
            graph.addEdge(b, c);
            graph.addEdge(b, f);
            graph.addEdge(c, d);
            graph.addEdge(d, b);
            graph.addEdge(e, f);
            graph.addEdge(f, c);
            graph.addEdge(g, h);

            System.out.println("Grafo:");
            System.out.println(graph);
            GraphServices.sweep(graph);
        }
        else if (argv[0].equals("top_sort")) {
            Graph<String> graph = new Graph<String>();

            Graph.Node<String> a = graph.addNode("a");
            Graph.Node<String> b = graph.addNode("b");
            Graph.Node<String> c = graph.addNode("c");
            Graph.Node<String> d = graph.addNode("d");
            Graph.Node<String> e = graph.addNode("e");
            Graph.Node<String> f = graph.addNode("f");
            Graph.Node<String> g = graph.addNode("g");
            Graph.Node<String> h = graph.addNode("h");

            graph.addEdge(a, b);
            graph.addEdge(a, f);
            graph.addEdge(b, c);
            graph.addEdge(b, f);
            graph.addEdge(c, d);
            graph.addEdge(d, b);
            graph.addEdge(e, f);
            graph.addEdge(f, c);
            graph.addEdge(g, h);

            System.out.println("Grafo:");
            System.out.println(graph);

            System.out.println("Primo tentativo: (dovrebbe fallire)");
            GraphServices.topologicalSort(graph);

            System.out.println("Rimozione arco (d,b)");
            graph.removeEdge(d, b);

            System.out.println("Nuovo Grafo:");
            System.out.println(graph);

            System.out.println("Secondo tentativo: (dovrebbe riuscire)");
            GraphServices.topologicalSort(graph);
        }
        else if (argv[0].equals("strong_cc")) {
            Graph<String> graph = new Graph<String>();

            Graph.Node<String> a = graph.addNode("a");
            Graph.Node<String> b = graph.addNode("b");
            Graph.Node<String> c = graph.addNode("c");
            Graph.Node<String> d = graph.addNode("d");
            Graph.Node<String> e = graph.addNode("e");
            Graph.Node<String> f = graph.addNode("f");
            Graph.Node<String> g = graph.addNode("g");
            Graph.Node<String> h = graph.addNode("h");

            graph.addEdge(a, b);
            graph.addEdge(a, f);
            graph.addEdge(b, c);
            graph.addEdge(b, f);
            graph.addEdge(c, d);
            graph.addEdge(d, b);
            graph.addEdge(e, f);
            graph.addEdge(f, c);
            graph.addEdge(g, h);

            System.out.println("Grafo 1:");
            System.out.println(graph);

            GraphServices.strongConnectedComponents(graph);
            System.out.println("\n");

            Graph<String> graph2 = new Graph<String>();

            Graph.Node<String> a2 = graph2.addNode("a");
            Graph.Node<String> b2 = graph2.addNode("b");
            Graph.Node<String> c2 = graph2.addNode("c");
            Graph.Node<String> d2 = graph2.addNode("d");
            Graph.Node<String> e2 = graph2.addNode("e");

            graph2.addEdge(a2, b2);
            graph2.addEdge(b2, c2);
            graph2.addEdge(c2, a2);
            graph2.addEdge(a2, d2);
            graph2.addEdge(d2, e2);

            System.out.println("Grafo 2:");
            System.out.println(graph2);

            GraphServices.strongConnectedComponents(graph2);
            System.out.println("\n");

            Graph<String> graph3 = new Graph<String>();

            Graph.Node<String> a3 = graph3.addNode("0");
            Graph.Node<String> b3 = graph3.addNode("1");
            Graph.Node<String> c3 = graph3.addNode("2");
            Graph.Node<String> d3 = graph3.addNode("3");
            Graph.Node<String> e3 = graph3.addNode("4");
            Graph.Node<String> f3 = graph3.addNode("5");
            Graph.Node<String> g3 = graph3.addNode("6");
            Graph.Node<String> h3 = graph3.addNode("7");
            Graph.Node<String> i3 = graph3.addNode("8");

            // 0-1-2-3
            graph3.addEdge(a3, b3);
            graph3.addEdge(b3, c3);
            graph3.addEdge(c3, d3);
            graph3.addEdge(d3, a3);

            graph3.addEdge(c3, e3);

            // 4-5-6
            graph3.addEdge(e3, f3);
            graph3.addEdge(f3, g3);
            graph3.addEdge(g3, e3);

            graph3.addEdge(h3, g3);
            graph3.addEdge(h3, i3);

            System.out.println("Grafo 3:");
            System.out.println(graph3);

            GraphServices.strongConnectedComponents(graph3);
            System.out.println("\n");
        }
    }
}
