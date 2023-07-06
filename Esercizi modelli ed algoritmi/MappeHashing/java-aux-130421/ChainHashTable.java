import java.util.LinkedList;
import java.security.DrbgParameters.Capability;
import java.util.ArrayList;

public class ChainHashTable extends AbstractHashTable {
	private LinkedList<Entry> [] table;
	
	// Costruttori
	public ChainHashTable(int cap, int p, double lambda) {
		super(cap, p, lambda);
	}
	public ChainHashTable(int cap, int p) {
		super(cap, p); // massimo fattore di carico predefinito
	}
	public ChainHashTable(int cap) {
		super(cap); // primo predefinito
	}
	public ChainHashTable() {
		super(); // capacità predefinita
	}
	
	// Metodi non implementati in AbstractHashTable

	// Inizializza una tabella hash vuota secondo i parametri passati al costruttore
	
	protected void createTable() {
		this.table = (LinkedList<Entry>[]) new LinkedList[getCapacity()];
	}

	// Restituisce il valore (intero) associato alla chiave k
	// Restituisce -1 se la chiave è assente
	public int get(String k) {
		int hash = this.hashFunction(k);
		LinkedList<Entry> l = table[hash];
		if (l == null) return -1;
		for (Entry e : l){
			if (e.getKey().equals(k))
				return e.getValue();
		}
		return -1;
	}
	
	// Aggiorna il valore associato alla chiave k
	// Restituisce il vecchio valore o -1 se la chiave non è presente
	public int put(String k, int value) {
		int hash = this.hashFunction(k);
		if(table[hash] == null) table[hash] = new LinkedList<>();

		LinkedList<Entry> l = table[hash];
		for (Entry e : l){
			if (e.getKey().equals(k)){
				int oldVal = e.getValue();
				e.setValue(value);
				return oldVal;
			}
		}
		if((size() / getCapacity()) >= getMaxLambda()) resize(getCapacity()*2);
		hash = this.hashFunction(k);
		incSize();
		l.addLast(new Entry(k, value));
		return -1;
	}
	
	
	// Rimuove la coppia con chiave k
	// Restituisce il vecchio valore o -1 se la chiave non è presente
	public int remove(String k) {
		int hash = this.hashFunction(k);
		LinkedList<Entry> l = table[hash];
		if (l == null) return -1;
		for (Entry e : l){
			if (e.getKey().equals(k)){
				int val = e.getValue();
				l.remove(e);
				decSize();
				return val;
			}
		}
		return -1;
	}
	
	// Restituisce un oggetto Iterable contenente tutte le coppie presenti
	// nella tabella hash
	public Iterable<Entry> entrySet() {
		LinkedList<Entry> ret = new LinkedList<>();
		for(int i = 0; i < getCapacity(); i++){
				if(table[i] != null){
					for (Entry e : table[i]) ret.addLast(e);
				}
		} 
		return ret;
	}

}


