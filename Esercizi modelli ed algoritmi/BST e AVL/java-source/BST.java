public class BST<V> {

    Node<V> root;

    private class Node<T>{
        int key;
        T value;
        Node<T> left;
        Node<T> right;

        Node(int k, T v){
            key = k;
            value = v;
            left = null;
            right = null;
        }
    }

    public BST(int key, V value) {
        root = new Node<V>(key, value);
    }

    public void insert(int k, V v) {
        insert(root, k, v);
    }

    private void insert(Node<V> n, int k, V v){     //metodo ausiliario
        if(n.key == k)
            n.value = v;
        else if(n.key > k){
            if(n.left == null) n.left = new Node<>(k, v);
            insert(n.left, k, v);
        } else {
            if(n.right == null) n.right = new Node<>(k, v);
            insert(n.right, k, v);
        }
    }

    public V find(int k) {
        return find(root, k).value;
    }

    public Node<V> find(Node<V> n, int k){                //metodo ausiliario
        if(n.key == k)
            return n;
        else if(n.key > k){
            if(n.left == null) return null;
            return find(n.left, k);
        } else {
            if(n.right == null) return null;
            return find(n.right, k);
        }
    }

    public int findMin() {
        return findMin(root);
    }

    public int findMin(Node<V> n){                  //metodo ausiliario
        if(n.left != null)
            return findMin(n.left);
        else
            return n.key;
    }

    public void removeMin() {
        removeMin(root);
    }

    public void removeMin(Node<V> n) {              //metodo ausiliario
        
    }

    public void remove(int k) {
        Node<V> node = find(root, k);
        Node<V> succ = successor(root, node.key);
        node.key = succ.key;
        node.value = succ.value;

    }

    

    void print(){
        StringBuilder sb = new StringBuilder();
        print(root, sb);
        System.out.println(sb.toString());
    }

    void print(Node<V> n, StringBuilder sb){
        sb.append(" ("+n.key+ ",");
        if(n.left != null)
            print(n.left,sb);
        else sb.append("()");
        sb.append(",");
        if(n.right != null)
            print(n.right, sb);
        else sb.append("()");
        sb.append(")");
    }

    int predecessor(int k) {
        return predecessor(root, k).key;
    }
    Node<V> predecessor(Node<V> n, int k){
        if(n == null)
            return null;
        else if (n.key>=k)
            return predecessor(n.left, k);
        Node<V> t = predecessor(n.right, k);
        if(t == null)
            return n;
        else
            return t;
    }

    Node<V> successor(Node<V> n, int k){
        if(n == null)
            return null;
        else if (n.key<=k)
            return successor(n.right, k);
        Node<V> t = successor(n.left, k);
        if(t == null)
            return n;
        else
            return t;
    }
}
