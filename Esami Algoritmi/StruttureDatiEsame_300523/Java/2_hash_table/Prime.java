import java.util.*;

public class Prime {
    /* Primality Test: metodi base */

    // Naive Primality Test
    // Costo: O(n)
    private static boolean naivePrimalityTest(int n) {
        if (n <= 1)
            return false;

        // controlla se esiste un divisore di n in [2, n-1]
        for (int i = 2; i < n; i++)
            if (n % i == 0)
                return false;

        return true;
    }

    // Simple Primality Test
    // Costo: O(sqrt(n))
    private static boolean simplePrimalityTest(int n) {
        if (n <= 1)
            return false;

        // controlla se esiste un divisore di n in [2, sqrt(n)]
        for (int i = 2; i * i <= n; i++)
            if (n % i == 0)
                return false;

        return true;
    }

    // Optimized Primality Test
    // Costo: O(sqrt(n))
    private static boolean optimizedSimplePrimalityTest(int n) {
        if (n <= 1)
            return false;
        if (n <= 3)
            return true;

        if (n % 2 == 0 || n % 3 == 0)
            return false;

        // controlla se esiste un divisore di n in [2, sqrt(n)]
        // considera solo i numeri del tipo 6k ± 1
        for (int i = 5; i * i <= n; i = i + 6)
            if (n % i == 0 || n % (i + 2) == 0)
                return false;

        return true;
    }

    /* Primality Test: metodi probabilistici */

    // Fermat Primality Test
    // Costo: O(k * log(n))
    private static boolean fermatPrimalityTest(int n) {
        if (n <= 1)
            return false;
        if (n <= 3)
            return true;

        Random ran = new Random();
        int minValue = 2;
        int maxValue = n-2;

        // se n è un numero primo, allora per ogni a in [2, n-2], a^(n-1) % n = 1
        // scegli k numeri in [2, n-2] e controlla se uno di questi viola la proprietà precedente
        int k = 1 + n/10;
        while (k > 0) {
            int a = minValue + ran.nextInt(maxValue - minValue + 1);

            if (power(a, n-1, n) != 1)
                return false;

            k--;
        }

        return true;
    }

    // Funzione ausiliaria per calcolare a ^ n % p in O(log(n))
    private static int power(int a, int n, int p) {
        int res = 1;

        a = a % p;

        while (n > 0) {
            if (n % 2 != 0)
                res = (res * a) % p;

            n = n / 2;
            a = (a * a) % p;
        }

        return res;
    }

    /* Genera un nuovo numero primo */

    // Trova il più piccolo numero primo maggiore di n
    static int nextPrime(int n) {
        if (n <= 1)
            return 2;

        int prime;
        if (n % 2 == 0)
            prime = n + 1;
        else
            prime = n + 2;

        boolean found = false;

        while (!found) {
            // è possibile usare uno dei test di primalità implementati in precedenza
            if (optimizedSimplePrimalityTest(prime))
                found = true;
            else
                prime += 2;
        }

        return prime;
    }
}
