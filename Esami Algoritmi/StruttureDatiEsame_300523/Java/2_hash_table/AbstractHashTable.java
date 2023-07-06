import java.util.Random;
import java.util.ArrayList;

public abstract class AbstractHashTable {
    private int capacity; // dim. tabella
    private int n = 0; // numero di entry nella tabella
    private int prime; // numero primo
    private long a, b; // coefficienti per MAD
    private double maxLambda; // fattore di carico massimo
    
    /* La classe Entry --> coppie (chiave, valore) */
    class Entry {	
        private String key;
        private int value;
        
        public Entry(String k, int v) {
            key = k;
            value = v;
        }
        
        public String getKey() {
            return key;
        }
        
        public int getValue() {
            return value;
        }
        
        public void setValue(int v) {
            value = v;
            return;
        }
        
        public String toString() {
            return "(" + getKey() + ", " + Integer.toString(getValue()) + ")";
        }
    }
    
    /* Costruttori di AbstractHashTable */
    public AbstractHashTable(int cap, int p, double lambda) {
        capacity = cap;
        prime = p;
        maxLambda = lambda;
        Random gen = new Random();
        a = gen.nextInt(prime) + 1;
        b = gen.nextInt(prime);
        createTable();
    }
    
    public AbstractHashTable(int cap, int p) {
        this(cap, p, 0.5); // massimo fattore di carico predefinito
    }
    
    public AbstractHashTable(int cap) {
        this(cap, 109345121); // primo predefinito
    }
    
    public AbstractHashTable() {
        this(5); // capacità predefinita
    }
    
    /* Metodi ausiliari comuni a tutte le classi */
    
    // metodo che implementa la funzione hash (hash code + compressione)
    protected int hashFunction(String k) {
        return 	(int) (Math.abs(k.hashCode()*a + b) % prime) % capacity;
    }

    // metodo che aggiorna la dimensione della tabella hash
    protected void resize(int newCap) {
        ArrayList<Entry> buffer = new ArrayList<> (n);

        for (Entry e: entrySet())
            buffer.add(e);
        
        capacity = newCap;
        createTable(); // la capacità è stata modificata
        
        n = 0;
        for (Entry e: buffer)
            put(e.key, e.value);
    }
        
    /* Metodi pubblici comuni a tutte le classi */
    
    // restituisce true se la tabella è vuota
    public boolean isEmpty() {
        return n == 0;
    }
    
    // restituisce il numero di chiavi presenti nella tabella
    public int size() {
        return n;
    }
    
    // restituisce la capacità della tabella
    public int getCapacity() {
        return capacity;
    }
    
    // incrementa il numero n di chiavi presenti
    public void incSize() {
        n++;
    }
    
    // decrementa il numero n di chiavi presenti
    public void decSize() {
        if (n > 0)
            n--;
    }
    
    // restituisce valore max. per il fattore di carico (si effettua resize se superato)
    public double getMaxLambda() {
        return maxLambda;
    }
    
    // stampa una rappresentazione delle coppie presenti
    public void print() {
        if (isEmpty()) {
            System.out.println("[]");
            return;
        }

        String s = "[";
        for (Entry e: entrySet()) 
            s = s + e.toString() + ",";
        s = s.substring(0, s.length() - 1) + "]";
        System.out.println(s);
    }
    
    /* Metodi astratti da implementare nelle sottoclassi */

    protected abstract void createTable(); // inizializza la tabella hash
    public abstract int get(String k); // restituisce il valore associato alla chiave k
    public abstract int put(String k, int value); // inserisce un coppia
    public abstract int remove(String k); // rimuove la coppia con chiave k
    public abstract Iterable<Entry> entrySet(); // restituisce un Iterable contentente tutte le coppie presenti
    
}
