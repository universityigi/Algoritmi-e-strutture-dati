import java.util.LinkedList;

public class OpenHashTable extends AbstractHashTable {
    // Un array di Entry per la tabella
    private Entry[] table;
    // Marcatore di cella vuota ma da esaminare durante probing
    private final Entry DEFUNCT = new Entry(null, 0); 
    
    /* Costruttori */
    public OpenHashTable(int cap, int p, double lambda) {
        super(cap, p, lambda);
    }

    public OpenHashTable(int cap, int p) {
        super(cap, p); // massimo fattore di carico predefinito
    }
    
    public OpenHashTable(int cap) {
        super(cap); // primo predefinito
    }
    
    public OpenHashTable() {
        super(); // capacità predefinita
    }
    
    /* Metodi privati ausiliari */
    
    // Trova la posizione in cui si trova la chiave k o restituisce -1
    private int findQP(String k) { 
        int init = hashFunction(k);
        int c = getCapacity();
        int index;
        
        for (int i = 0; i < c; i++) {
            index = (init + i*i) % c;
            if (table[index] == null)
                return -1;	
            if ((!table[index].equals(DEFUNCT)) && table[index].getKey().equals(k))
                return index;
        }
        return -1; // Esplorate almeno N/2 posizioni senza successo
    }

    // Trova la posizione della chiave k o il prossimo slot se disponbile
    // Usa Quadratic Probing
    private int findSlotQP(String k) { 
        int init = hashFunction(k);
        int c = getCapacity();
        int index;
        
        for (int i = 0; i < c; i++) {
            index = (init + i*i) % c;
            if ((table[index] == null) || (table[index].equals(DEFUNCT)))
                return index;
        }
        return -1; // Esplorate N posizioni senza successo
    }
    
    /* Metodi non implementati in AbstractHashTable */

    // Inizializza una tabella hash vuota secondo i parametri passati al costruttore
    protected void createTable() {
        table = new Entry[getCapacity()];
        return;
    }
    
    // Restituisce il valore (intero) associato alla chiave k
    // Restituisce -1 se la chiave è assente
    public int get(String k) {
        int index = findQP(k);
        if (index == -1)
            return -1;
        return table[index].getValue();
    }
    
    // Aggiorna il valore associato alla chiave k
    // Restituisce il vecchio valore o -1 se la chiave non è presente
    public int put(String k, int value) {
        int old = -1;
        int c = getCapacity();
        if (size() + 1 > c*getMaxLambda()) {
            // resize(2*c + 1);
            resize(Prime.nextPrime(2*c));
        }
        
        int index = findQP(k);
        if (index != -1) {
            old = table[index].getValue();
            table[index].setValue(value); 
        }
        else {
            index = findSlotQP(k);
            table[index] = new Entry(k, value);
            incSize();
        }

        return old;
    }
    
    // Rimuove la coppia con chiave k
    // Restituisce il vecchio valore o -1 se la chiave non è presente
    public int remove(String k) {
        if (isEmpty())
            return -1;
        
        int index = findQP(k);
        if (index != -1) {
            int old = table[index].getValue();
            table[index] = DEFUNCT;
            decSize();
            return old;
        }
        
        return -1;
    }
    
    // Restituisce un oggetto Iterable contenente tutte le coppie presenti nella tabella hash
    public Iterable<Entry> entrySet() {
        LinkedList<Entry> pairs;
        if (isEmpty())
            return null;
        
        pairs = new LinkedList<Entry>();
        for (int i = 0; i < table.length; i++)
            if ((table[i] != null) && (!table[i].equals(DEFUNCT)))
                pairs.addLast(table[i]);
        
        return pairs;
    }
}
