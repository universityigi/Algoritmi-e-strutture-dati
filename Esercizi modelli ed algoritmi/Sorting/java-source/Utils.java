import java.util.*;
import java.io.*;

public class Utils{
    private static int MIN_SIZE = 4;
    private static int MAX_SIZE = 100000000;

    private static String RANDOM = "R";
    private static String ORDINATO_CRESCENTE = "C";
    private static String ORDINATO_DECRESCENTE = "D";
    private static String QUASI_CRESCENTE = "c";
    private static String QUASI_DECRESCENTE = "d";

    /* 
     * Stampa info su come usare il Driver
     */
    public void printHelp() {
        System.out.println("\nCome usare:\njava Driver test <sort_alg> <tipo_input> <size>\njava Driver run <sort_alg> <tipo_input> <size>\njava Driver gen <tipo_input> <size> <file_name>\njava Driver cmp <nome-alg1> <nome-alg2> <tipoInput> <size>\njava Driver file <sort_alg> <file_name>\n\nVedere il testo dell'esercitazione per info più specifiche\n\n");
    }

    /* 
     * Stampa quali algoritmi di ordinamento possono essere dati in input al Driver
     */
    public void printHelpSort(){
        System.out.println("\nAlgoritmi da implementare e testare:\nmergeSort\nheapSort\nselectionSort\ninsertionSort\nquickSort\nradixSort\nbucketSort\n\n\nVedere il testo dell'esercitazione per info più specifiche\n\n");
    }

    /* 
     * Controlla che la input size rispetti MAX_SIZE e MIN_SIZE 
     */
    public int checkSize(int size){
        if(size < MIN_SIZE || size > MAX_SIZE){
            System.out.println("Size non valida. La size deve essere compresa tra "+MIN_SIZE+" e "+MAX_SIZE+" inclusi");
            System.exit(1);
        }
        return size;
    }

    /* 
     * Controlla che l'algortimo di input sia uno di quelli da implementare nel file Sort.java 
     */
    public boolean checkSortAlg(String s) {
        if(s.equals("mergeSort")) 
            return true;
        else if(s.equals("heapSort"))
            return true;
        else if(s.equals("selectionSort"))
            return true;
        else if(s.equals("insertionSort"))
            return true;
        else if(s.equals("quickSort"))
            return true;
        else if(s.equals("radixSort"))
            return true;
        else if(s.equals("bucketSort"))
            return true;
        else if(s.equals("bubbleSort"))
            return true;
        else if(s.equals("quickSortDefault"))
            return true;
        // Qui possono essere aggiunti altri algoritmi
        else
            System.out.println("\n" + s + " non è stato incluso nei sorgenti; puoi aggiungerlo se vuoi");
        return false;
    }

    /*
     * Esegue num inversioni random nell'array
     * un'inversione è uno swap di due elementi
     */
    public void genInversioni(int[] arr, int num_inversioni){
        int i = 0;
        Random r = new Random();
        while(i < num_inversioni) {
            int p = r.nextInt(arr.length-1);
            int q = r.nextInt(arr.length-1);
            //swap
            int tmp = arr[p];
            arr[p] = arr[q];
            arr[q] = tmp;
            i++;
        }
    }

    /* 
     * Generatore di array 
     */
    public int[] generator(int size, String tipo) {
        int[] ris = new int[size];
        Random r = new Random(System.currentTimeMillis());
        int num_inversioni = (int) Math.round(.1*size+.5);

        if (tipo.equals(RANDOM)) {
            for (int i = 0; i < size; i++)
                ris[i] = r.nextInt(size);
        } else if(tipo.equals(ORDINATO_CRESCENTE)){
            for (int i = 0; i < size; i++) 
                ris[i] = i;
        } else if(tipo.equals(ORDINATO_DECRESCENTE)){
            for (int i = 0; i < size; i++) 
                ris[i] = size - i;
        } else if(tipo.equals(QUASI_CRESCENTE)){ 
            for (int i = 0; i < size; i++) 
                ris[i] = i;
            genInversioni(ris, num_inversioni);
        } else if(tipo.equals(QUASI_DECRESCENTE)){
            for (int i = 0; i < size; i++) 
                ris[i] = size - i;
            genInversioni(ris, num_inversioni);
        } else{
            printHelp();
            System.exit(1);
        }
        //System.out.println("Array generato: " + Arrays.toString(ris));
        return ris;
    }

    /* 
     * Test di correttezza: 
     * esegue <sort_alg> (uno di quelli su elencati) su un input di dimensione <size_input> , 
     * della tipologia <tipo_input>, stampando info sulla correttezza;
     * Il test viene effettuato confrontando il risultato con l'algoritmo di libreria
     */
    public void test(String alg, int[] arr) {
        System.out.println("\n  > Test " + alg);
        int[] clone = arr.clone();
        int[] clone2 = arr.clone();

        System.out.println("    >> Ordinamento array...\n");
        sort(alg, clone);
        Arrays.sort(clone2);
        if(Arrays.equals(clone, clone2)){
            System.out.println("\nEsito test algoritmo OK\n");
        }
        else{
            System.out.println("Array ordinato con "+alg+": \n" + Arrays.toString(clone));
            System.out.println("Should be: \n" + Arrays.toString(clone2));
            System.out.println("\nEsito test algoritmo ERRORE (sorry)\n");
        }
    }
    
    /*
     * - Esegue ordinamento con <sort_alg> su un input di tipo <tipo_input> e dimensione <size_input>; 
     * - Stampa il tempo di elaborazione;
     */
    public void run(String alg, int[] arr) {
        System.out.println("\n  > Run " + alg);
        int[] clone = arr.clone();
        
        long start = System.currentTimeMillis();
        System.out.println("    >> Ordinamento array...");
        sort(alg, clone);
        System.out.printf(" TIME: %.3f sec.%n\n", ((1.0 * System.currentTimeMillis() - start) / 1000));
    }

    /*
     * - Prende l'array in input da file
     * - Esegue ordinamento con <sort_alg> su un input di tipo <tipo_input> e dimensione <size_input>; 
     * - Stampa il tempo di elaborazione;
     * (come run ma prende input da file)
     */
    public void file(String alg, String file_name) {
        try{
            System.out.println("Leggo array dal file " + file_name + "...");
            Scanner s = new Scanner(new File(file_name));
            int[] array = new int[s.nextInt()];
            for (int i = 0; i < array.length; i++)
                array[i] = s.nextInt();
            System.out.println(Arrays.toString(array));

            test(alg, array);
            run(alg, array);
        } catch(FileNotFoundException ex){
            System.out.println("File not found");
            System.exit(1);
        }
    }

    /*
     * Genera un possibile input (tipologia <tipo_input>, dimensione <size_input>), scrivendolo nel file <file_name>;
     */
    public void arrayToFile(String tipo, int size, String filename){
        try{
            int[] arr = generator(size, tipo);
            System.out.println("Scrivo array sul file " + filename + "...");
            
            BufferedWriter outputWriter = null;
            outputWriter = new BufferedWriter(new FileWriter(filename));
            outputWriter.write(Integer.toString(arr.length));
            outputWriter.newLine();
            for (int i = 0; i < arr.length; i++) {
                outputWriter.write(Integer.toString(arr[i]));
                outputWriter.newLine();
            }
            outputWriter.flush();  
            outputWriter.close();

            //System.out.println(Arrays.toString(arr));
            System.out.println("Finito, OK");
        }catch(IOException e){
            System.out.println("IOException");
            System.exit(1);
        }
    }

    /* 
     * Ordina con bubbleSort 
     */
    public void bubbleSort(int[] arr) {
        int i, j;
        for (i = 0; i < arr.length-1; i++) {
            boolean anySwap = false;
            for (j = 0; j < arr.length-i-1; j++)
                if (arr[j] > arr[j+1]) {
                    anySwap = true;
                    //swap
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                }
            if(!anySwap) return; // no swap --> già ordinato 
        }
    }

    /* 
     * Esegue l'algoritmo di ordinamento dato in input 
     */
    public void sort(String s, int[] arr) {
        Sort alg = new Sort(); 
        if(s.equals("mergeSort")) 
            alg.mergeSort(arr);
        else if(s.equals("heapSort"))
            alg.heapSort(arr);
        else if(s.equals("selectionSort"))
            alg.selectionSort(arr);
        else if(s.equals("insertionSort"))
            alg.insertionSort(arr);
        else if(s.equals("quickSort"))
            alg.quickSort(arr);
        else if(s.equals("radixSort"))
            alg.radixSort(arr);
        else if(s.equals("bucketSort"))
            alg.bucketSort(arr);
        else if(s.equals("bubbleSort"))
            bubbleSort(arr);
        else if(s.equals("quickSortDefault"))
            alg.quickSortDefault(arr);
        // Qui possono essere aggiunti altri algoritmi
    }
}