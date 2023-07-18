import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Driver {

    public static void print() {
        System.out.println("Richiesto argomento: {chaining, open}");
    }

    public static void main(String[] argv) {

        if (argv.length < 1) {
            print();
            return;
        }

        if (argv[0].equals("chaining")) {

            System.out.print("Initializing hash table with capacity 5");
            ChainHashTable table = new ChainHashTable();
            System.out.print("------------------\n");
            System.out.println("Test inserimento 2 coppie\n");
            table.put("Gianni", 1);
            table.put("Giorgio", 1);
            System.out.print("Table size: " + table.size() + "\n");
            System.out.print("Table is:\n\n");
            table.print();
            System.out.print("La tabella dovrebbe contenere le coppie\n");
            System.out.print("[(Gianni, 1) (Giorgio, 1)]\n");
            System.out.print("------------------\n");
            System.out.println("Test inserimento 4 coppie di cui una con chiave già presente\n");
            table.put("Marianna", 6);
            table.put("Stefania", 9);
            table.put("Massimo", 3);
            table.put("Gianni", 4);
            System.out.print("------------------\n");
            System.out.print("Dimensione tabella: " + table.size() + "\n");
            System.out.print("La tabella contiene le coppie:\n\n");
            table.print();
            System.out.print("La tabella dovrebbe contenere le coppie\n");
            System.out.print("[(Gianni, 4),(Giorgio, 1),(Marianna, 6),(Stefania, 9),(Massimo, 3)]\n");
            System.out.print("------------------\n");
            System.out.println("Rimuoviamo la coppia con chiave Giorgio\n");            
            table.remove("Giorgio");
            System.out.print("------------------\n");
            System.out.println("Rimuoviamo la coppia con chiave Gino, non presente\n");            
            table.remove("Gino");
            System.out.print("------------------\n");
            System.out.print("Dimensione tabella: " + table.size() + "\n");
            System.out.print("La tabella contiene le coppie:\n\n");
            table.print();
            System.out.print("La tabella dovrebbe contenere le coppie\n");
            System.out.print("[(Gianni, 4),(Marianna, 6),(Stefania, 9),(Massimo, 3)]\n");
            System.out.print("------------------\n");
            System.out.println("Reinseriamo la coppia (Giorgio, 7)\n");            
            table.put("Giorgio", 7);
            System.out.print("Dimensione tabella: " + table.size() + "\n");
            System.out.print("La tabella contiene le coppie:\n\n");
            table.print();
            System.out.print("La tabella dovrebbe contenere le coppie\n");
            System.out.print("[(Gianni, 4),(Giorgio, 7),(Marianna, 6),(Stefania, 9),(Massimo, 3)]\n");
            System.out.print("------------------\n");
            System.out.println("Richiediamo il valore associato alla chiave Gino che non esiste\n");
            System.out.println("Valore associato a Gino: " + table.get("Gino"));
            System.out.print("------------------\n");
            System.out.println("Richiediamo il valore associato alla chiave Giorgio\n");
            System.out.println("Valore associato a Giorgio: " + table.get("Giorgio"));
            System.out.print("------------------\n");

            


        } else if (argv[0].equals("open")) {

            System.out.print("Initializing hash table with capacity 5");
            OpenHashTable table = new OpenHashTable();
            System.out.print("------------------\n");
            System.out.println("Test inserimento 2 coppie\n");
            table.put("Gianni", 1);
            table.put("Giorgio", 1);
            System.out.print("Table size: " + table.size() + "\n");
            System.out.print("Table is:\n\n");
            table.print();
            System.out.print("La tabella dovrebbe contenere le coppie\n");
            System.out.print("[(Gianni, 1) (Giorgio, 1)]\n");
            System.out.print("------------------\n");
            System.out.println("Test inserimento 4 coppie di cui una con chiave già presente\n");
            table.put("Marianna", 6);
            table.put("Stefania", 9);
            table.put("Massimo", 3);
            table.put("Gianni", 4);
            System.out.print("------------------\n");
            System.out.print("Dimensione tabella: " + table.size() + "\n");
            System.out.print("La tabella contiene le coppie:\n\n");
            table.print();
            System.out.print("La tabella dovrebbe contenere le coppie\n");
            System.out.print("[(Gianni, 4),(Giorgio, 1),(Marianna, 6),(Stefania, 9),(Massimo, 3)]\n");
            System.out.print("------------------\n");
            System.out.println("Rimuoviamo la coppia con chiave Giorgio\n");            
            table.remove("Giorgio");
            System.out.print("------------------\n");
            System.out.println("Rimuoviamo la coppia con chiave Gino, non presente\n");            
            table.remove("Gino");
            System.out.print("------------------\n");
            System.out.print("Dimensione tabella: " + table.size() + "\n");
            System.out.print("La tabella contiene le coppie:\n\n");
            table.print();
            System.out.print("La tabella dovrebbe contenere le coppie\n");
            System.out.print("[(Gianni, 4),(Marianna, 6),(Stefania, 9),(Massimo, 3)]\n");
            System.out.print("------------------\n");
            System.out.println("Reinseriamo la coppia (Giorgio, 7)\n");            
            table.put("Giorgio", 7);
            System.out.print("Dimensione tabella: " + table.size() + "\n");
            System.out.print("La tabella contiene le coppie:\n\n");
            table.print();
            System.out.print("La tabella dovrebbe contenere le coppie\n");
            System.out.print("[(Gianni, 4),(Giorgio, 7),(Marianna, 6),(Stefania, 9),(Massimo, 3)]\n");
            System.out.print("------------------\n");
            System.out.println("Richiediamo il valore associato alla chiave Gino che non esiste\n");
            System.out.println("Valore associato a Gino: " + table.get("Gino"));
            System.out.print("------------------\n");
            System.out.println("Richiediamo il valore associato alla chiave Giorgio\n");
            System.out.println("Valore associato a Giorgio: " + table.get("Giorgio"));
            System.out.print("------------------\n");


        } else {
            print();
        }

    }

}
