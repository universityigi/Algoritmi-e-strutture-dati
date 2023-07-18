public class BST<V> {

    private Node<V> root;

    /* Classe interna che descrive il generico nodo del BST */
    private class Node<V> {
        private int key;
        private V value;
        private Node<V> left;
        private Node<V> right;

        public Node(int key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    /* Costruttore */
    public BST(int key, V value) {
        this.root = new Node<V>(key, value);
    }

    /* Metodo ausiliario per insert */
    private void insert(Node<V> t, int k, V v) {
        if (t.key == k) {

            t.value = v;

        } else if (k < t.key) {

            if (t.left == null)
                t.left = new Node<V>(k, v);
            else
                insert(t.left, k, v);

        } else {

            if (t.right == null)
                t.right = new Node<V>(k, v);
            else
                insert(t.right, k, v);

        }
    }

    /* Inserisce la coppia (k, v) nel BST */
    public void insert(int k, V v) {
        if (this.root == null)
            this.root = new Node<V>(k, v);

        else
            insert(this.root, k, v);
    }

    /* Metodo ausiliario per find */
    private V find(Node<V> t, int k) {
        if (t == null)
            return null;

        else if (t.key == k)
            return t.value;

        else if (k < t.key)
            return find(t.left, k);

        else
            return find(t.right, k);
    }

    /* Restituisce l’unico valore associato ad una chiave */
    public V find(int k) {
        if (this.root == null)
            return null;
        else
            return find(this.root, k);
    }

    /* Metodo ausiliario per findMin */
    private Node<V> findMin(Node<V> t) {
        if (t.left == null)
            return t;

        return findMin(t.left);
    }

    /* Restituisce la chiave minima contenuta nel BST */
    public int findMin() {
        if (this.root == null)
            return -1;

        return findMin(this.root).key;
    }

    /* Metodo ausiliario per removeMin */
    private Node<V> removeMin(Node<V> t) {
        // we are on the min key
        if (t.left == null) {
            return t.right;
        }

        t.left = removeMin(t.left);
        return t;
    }

    /* Elimina il nodo con la chiave minima contenuta nel BST */
    public void removeMin() {
        if (this.root == null)
            return;

        this.root = removeMin(this.root);
    }

    /* Metodo ausiliario per remove */
    private Node<V> remove(Node<V> n, int k) {
        if (n == null)
            return null;

        else if (k < n.key)
            n.left = remove(n.left, k);

        else if (k > n.key)
            n.right = remove(n.right, k);

        else {
            if (n.right == null)
                return n.left;

            if (n.left == null)
                return n.right;

            Node<V> to_remove = n;

            n = findMin(to_remove.right);
            Node<V> nn = new Node<V>(n.key, n.value);
            nn.right = removeMin(to_remove.right);
            nn.left = to_remove.left;
            n = nn;
        }

        return n;
    }

    /* Elimina dal BST il nodo con chiave k (se esiste) */
    public void remove(int k) {
        if (this.root == null)
            return;

        this.root = remove(this.root, k);
    }

    /* Metodo ausiliario per print */
    private void print(Node<V> t, int level) {
        if (t == null)
            return;

        for (int i = 0; i < level - 1; i++) {
            System.out.print("   ");
        }

        if (level > 0) {
            System.out.print(" |--");
        }

        System.out.println(t.key);

        print(t.left, level + 1);
        print(t.right, level + 1);
    }

    /* Stampa una rappresentazione dell’albero */
    void print() {
        print(this.root, 0);
    }

    /* Metodo ausiliario per predecessor */
    private Node<V> predecessor(Node<V> n, int k) {
        if (n == null)
            return null;

        else if (k <= n.key)
            return predecessor(n.left, k);

        Node<V> t = predecessor(n.right, k);
        if (t == null)
            return n;

        return t;
    }

    /* Restituisce il nodo del BST avente la chiave maggiore tra quelle minori di k */
    int predecessor(int k) {
        Node<V> predecessor = predecessor(this.root, k);
        
        if (predecessor == null)
            return -1;
        else
            return predecessor.key;
    }
}
