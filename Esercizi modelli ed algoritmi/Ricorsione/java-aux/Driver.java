import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Driver {

    public static void main(String[] argv) {
        if (argv.length < 1) {
            System.out.println("Specificare: {inverti, maxGap, labirinto}");
            return;
        }

        if (argv[0].equals("inverti")) {

            LinkedList<Integer> l = new LinkedList<Integer>();
            l.add(10);
            l.add(20);
            l.add(5);
            l.add(6);
            l.add(8);
            System.out.println("lista originale");
            System.out.println(l);
            InvertiLista.invertiLista(l);
            System.out.println("* * *");
            System.out.println("lista invertita");
            System.out.println(l);
            System.out.println();

        } else if (argv[0].equals("maxGap")) {

            int[] array = {0, 5, 8, 10, 15, 27, 32, 43, 45};

            System.out.print("[");
            for (int i = 0; i < array.length; i++) {
                System.out.print("" + (i == 0 ? " " : ", ") + array[i]);
            }
            System.out.print(" ]\n");

            System.out.println("massimo gap: " + MaxGap.maxGap(array, 0, array.length-1));

        } else if (argv[0].equals("labirinto")) {

            try {

                BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Inserisci n (dimensione del labirinto): ");

                String s = r.readLine();

                if(s == null) return;
                StringTokenizer st = new StringTokenizer(s);
                int n = Integer.parseInt(st.nextToken());

                Labirinto lab = new Labirinto(n);

                System.out.println("Inserisci " + n + " righe di " + n + " caratteri, '.'=vuoto '#'=pieno :");
                for(int i=0; i<n; i++) {
                    s = r.readLine();
                    for(int j=0; j<n; j++) {
                        if(s.charAt(j) == '#')
                            lab.setPiena(i,j);
                    }
                }

                boolean found = lab.risolvibile();

                System.out.println("Percorso " + (found ? "" : "NON ") + "trovato. ");
                System.out.println(lab);

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("Specificare: {inverti, maxGap, labirinto}");
        }

    }

}
