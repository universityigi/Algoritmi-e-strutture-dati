public class Driver {

    public static void print() {
        System.out.println("Richiesto argomento: {bst, predecessor, tree, balanced, avl}");
    }

    public static void main(String[] argv) {

        if (argv.length < 1) {
            print();
            return;
        }

        if (argv[0].equals("bst")) {

            BST<String> b = new BST<String>(6,"Pisa");

            b.insert(3,"Roma");
            b.insert(12,"Milano");
            b.insert(7,"Bologna");
            b.insert(5,"Firenze");
            b.insert(1,"Torino");
            b.insert(15,"Siracusa");
            b.insert(8,"Bari");

            b.print();

            System.out.println("Valore associato a 5: " + b.find(5));

            System.out.print("remove(5)\n");
            b.remove(5);

            System.out.println("Valore associato a 5: " + b.find(5));

            b.print();

            System.out.print("remove(6)\n");
            b.remove(6);

            b.print();

            System.out.print("remove(3)\n");
            b.remove(3);

            b.print();

        } else if (argv[0].equals("predecessor")) {

            BST<String> b = new BST<String>(6,"Pisa");

            b.insert(3,"Roma");
            b.insert(12,"Milano");
            b.insert(7,"Bologna");
            b.insert(5,"Firenze");
            b.insert(1,"Torino");
            b.insert(15,"Siracusa");
            b.insert(8,"Bari");

            b.print();

            System.out.print("Nodo associato a predecessor(8): ");
            System.out.print("[chiave: " + b.predecessor(8) + ", valore: " + b.find(b.predecessor(8)) + "]\n");

            System.out.print("remove(7)\n");
            b.remove(7);

            System.out.print("Nodo associato a predecessor(8): ");
            System.out.print("[chiave: " + b.predecessor(8) + ", valore: " + b.find(b.predecessor(8)) + "]\n");

        } else if (argv[0].equals("tree")) {

            Tree t = Tree.build_1();
            boolean is_bst = t.isBST();
            if (is_bst)
                System.out.print("L'albero 1 è effettivamente un BST. OK.\n");
            else
                System.out.print("L'albero 1 è un BST ma non viene riconosciuto come tale. KO.\n");

            t = Tree.build_2();
            is_bst = t.isBST();
            if (!is_bst)
                System.out.print("L'albero 2 non è effettivamente un BST. OK.\n");
            else
                System.out.print("L'albero 2 NON è un BST ma viene riconosciuto come tale. KO.\n");

        } else if (argv[0].equals("balanced")) {

            Tree t = Tree.build_1();
            boolean is_bst = t.isBalanced();
            if (is_bst)
                System.out.print("L'albero 1 è effettivamente bilanciato. OK.\n");
            else
                System.out.print("L'albero 1 è bilanciato ma non viene riconosciuto come tale. KO.\n");

            t = Tree.build_3();
            is_bst = t.isBalanced();
            if (!is_bst)
                System.out.print("L'albero 3 non è effettivamente bilanciato. OK\n");
            else
                System.out.print("L'albero 3 non è bilanciato ma non viene riconosciuto come tale. KO.\n");

        } else if (argv[0].equals("avl")) {

            Tree t = Tree.build_1();
            boolean is_bst = t.isAVL();
            if (is_bst)
                System.out.print("L'albero 1 è effettivamente un AVL. OK.\n");
            else
                System.out.print("L'albero 1 è un AVL ma non viene riconosciuto come tale. KO.\n");

            t = Tree.build_2();
            is_bst = t.isAVL();
            if (!is_bst)
                System.out.print("L'albero 2 non è effettivamente un AVL. OK.\n");
            else
                System.out.print("L'albero 2 NON è un AVL ma viene riconosciuto come tale. KO.\n");

            t = Tree.build_3();
            is_bst = t.isAVL();
            if (!is_bst)
                System.out.print("L'albero 3 non è effettivamente un AVL. OK\n");
            else
                System.out.print("L'albero 3 non è AVL ma non viene riconosciuto come tale. KO.\n");

        } else {
            print();
        }
    }
}
