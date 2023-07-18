import java.util.LinkedList;

public class OpenHashTable extends AbstractHashTable {
	// Un array di Entry per la tabella
	private Entry[] table;
	// marcatore di cella vuota ma da esaminare durante probing
	private final Entry DEFUNCT = new Entry(null, 0); 
	
	// Costruttori
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
		
	// Metodi non implementati in AbstractHashTable

	// Inizializza una tabella hash vuota secondo i parametri passati al costruttore
	protected void createTable() {
		table = new Entry[getCapacity()];
	}

	// Restituisce il valore (intero) associato alla chiave k
	// Restituisce -1 se la chiave è assente
	public int get(String k) {
		int hash = this.hashFunction(k);
		System.out.println("get() : Sto cercando "+ hash);
		for (int i = 0; i < getCapacity(); i++){
			int pos = (hash+(i*i)) % getCapacity();
			System.out.println("\t\tget() : tento "+ pos);
			if (table[pos] == null ){
				return -1;
			}
			else if (table[pos].getKey().equals(k)){
					return table[pos].getValue();
			}
		}
		System.out.println("get() : Valore non trovato");
		return -1;
	}
	
	// Aggiorna il valore associato alla chiave k
	// Restituisce il vecchio valore o -1 se la chiave non è presente
	public int put(String k, int value) {
		if(( (double) size() / getCapacity()) > getMaxLambda()) resize(getCapacity()*2);
		int hash = this.hashFunction(k);
		System.out.println("put() : Sto cercando "+ hash);
		for (int i = 0; i < getCapacity(); i++){
			int pos = (int) (hash+(i*i)) %getCapacity();
			if (table[pos] == null || table[pos].equals(DEFUNCT)){
				incSize();
				table[pos] = new Entry(k, value);
				return -1;
			}
			if (table[pos].getKey().equals(k)){
				int oldVal = table[pos].getValue();
				table[pos] = new Entry(k, value);
				System.out.println("put() : SOVRASCRITTO");
				return oldVal;
			}
		}
		return -1;
	}
	
	
	// Rimuove la coppia con chiave k
	// Restituisce il vecchio valore o -1 se la chiave non è presente
	public int remove(String k) {
		int hash = this.hashFunction(k);
		System.out.println("remove() : Sto cercando "+ hash);
		for (int i = 0; i < getCapacity(); i++){
			int pos = (int) (hash+(i*i)) %getCapacity();
			System.out.println("\t\tremove(): tento "+pos);
			if (table[pos] == null || table[pos].equals(DEFUNCT)){
				continue;
			}
			if (table[pos].getKey().equals(k)){
				System.out.println("\t\tremove(): Trovato, elimino "+pos);
				decSize();
				int oldVal = table[pos].getValue();
				table[pos] = DEFUNCT;
				return oldVal;
			}
		}
		System.out.println("remove(): Valore non trovato");
		return -1;
	}
	

	public void print() {
		for (int i = 0; i < getCapacity(); i++){
			String s = String.valueOf(i) + " = ";
			if (table[i] == null)
				s += "NULL";
			else if (table[i].equals(DEFUNCT))
				s += "DEFUNCT";
			else
				s += table[i].getKey() + " " +this.hashFunction(table[i].getKey());;
			System.out.println(s);
		}
	}
	// Restituisce un oggetto Iterable contenente tutte le coppie presenti
	// nella tabella hash
	public Iterable<Entry> entrySet() {
		LinkedList<Entry> res = new LinkedList<>();
		for(int i = 0; i < getCapacity(); i++){
			if (table[i] != null && !table[i].equals(DEFUNCT))
				res.addFirst(table[i]);
		}
		return res;
	}
	
}


