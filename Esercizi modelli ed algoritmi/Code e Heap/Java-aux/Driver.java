import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Driver {

    public static void print() {
        System.out.println("Richiesto argomento: {min_heap, array, sort, change, k}");
    }

    public static void main(String[] argv) {

        if (argv.length < 1) {
            print();
            return;
        }

        if (argv[0].equals("min_heap")) {

            System.out.print("Building a min heap with 10 items\n");
            Heap h = new Heap(Heap.HEAP_TYPE.MIN_HEAP, 10);

            h.add(5);
            System.out.print("------------------\n");
            System.out.print("Heap is:\n\t");
            h.print();
            System.out.print("Should be:\n\t 5 \n");
            System.out.print("------------------\n");

            h.add(10);
            System.out.print("------------------\n");
            System.out.print("Heap is:\n\t");
            h.print();
            System.out.print("Should be:\n\t 5 10 \n");
            System.out.print("------------------\n");

            h.add(15);
            System.out.print("------------------\n");
            System.out.print("Heap is:\n\t");
            h.print();
            System.out.print("Should be:\n\t 5 10 15 \n");
            System.out.print("------------------\n");

            h.add(9);
            System.out.print("------------------\n");
            System.out.print("Heap is:\n\t");
            h.print();
            System.out.print("Should be:\n\t 5 9 15 10\n");
            System.out.print("------------------\n");

            h.add(4);
            System.out.print("------------------\n");
            System.out.print("Heap is:\n\t");
            h.print();
            System.out.print("Should be:\n\t 4 5 15 10 9\n");
            System.out.print("------------------\n");

            h.add(13);
            System.out.print("------------------\n");
            System.out.print("Heap is:\n\t");
            h.print();
            System.out.print("Should be:\n\t 4 5 13 10 9 15\n");
            System.out.print("------------------\n");

            h.add(3);
            System.out.print("------------------\n");
            System.out.print("Heap is:\n\t");
            h.print();
            System.out.print("Should be:\n\t 3 5 4 10 9 15 13\n");
            System.out.print("------------------\n");

            h.add(2);
            System.out.print("------------------\n");
            System.out.print("Heap is:\n\t");
            h.print();
            System.out.print("Should be:\n\t 2 3 4 5 9 15 13 10\n");
            System.out.print("------------------\n");

            h.poll();
            System.out.print("------------------\n");
            System.out.print("Heap is:\n\t");
            h.print();
            System.out.print("Should be:\n\t 3 5 4 10 9 15 13\n");
            System.out.print("------------------\n");

        } else if (argv[0].equals("array")) {

            int[] a = new int[]{17, 3, 100, 25, 1, 2, 7, 19, 36};
            Heap h = Heap.array2heap(a, Heap.HEAP_TYPE.MAX_HEAP);
            System.out.print("Heap is:\n\t");
            if (h != null)
            	h.print();
            System.out.print("Should be:\n\t 100 36 17 25 1 2 7 19 3\n");

        } else if (argv[0].equals("sort")) {

            int[] a = new int[]{17, 3, 100, 25, 1, 2, 7, 19, 36};
            Heap.heapSort(a);
            System.out.print("Array is:\n\t");
            printArray(a);
            System.out.print("Should be:\n\t 1 2 3 7 17 19 25 36 100\n");

        } else if (argv[0].equals("change")) {

            System.out.print("Building a min heap with 10 items\n");
            Heap h = new Heap(Heap.HEAP_TYPE.MIN_HEAP, 10);

            h.add(5);
            h.add(10);
            h.add(15);
            h.add(9);
            h.add(4);
            h.add(13);
            h.add(3);
            Heap.HeapEntry e = h.add(2);
            System.out.print("------------------\n");
            System.out.print("Heap is:\n\t");
            h.print();
            System.out.print("------------------\n");

            System.out.print("Updating last inserted node from 2 to 11...\n");

            h.updateEntryKey(e, 11);
            System.out.print("------------------\n");
            System.out.print("Heap is:\n\t");
            h.print();
            System.out.print("Should be:\n\t 3 5 4 10 9 15 13 11\n");
            System.out.print("------------------\n");

        } else if (argv[0].equals("k")) {

            int[] a = new int[]{17, 3, 100, 25, 1, 2, 7, 19, 36};
            Kth d = new Kth(4);

            d.insert(a[0]);
            d.insert(a[1]);
            d.insert(a[2]);
            d.insert(a[3]);
            System.out.print("k-th is " + d.get() + " should be 100\n");
            d.insert(a[4]);
            System.out.print("k-th is " + d.get() + " should be 25\n");
            d.remove();
            System.out.print("k-th is " + d.get() + " should be 100\n");
            d.insert(a[3]);
            d.insert(a[5]);
            d.insert(a[6]);
            d.insert(a[7]);
            d.insert(a[8]);
            System.out.print("k-th is " + d.get() + " should be 7\n");
            d.remove();
            d.remove();
            d.remove();
            d.remove();
            d.remove();
            System.out.print("k-th is " + d.get() + " should be 100\n");
            d.remove();
            System.out.print("k-th is " + d.get() + " should be -1\n");
        } else {
            print();
        }

    }

    public static void printArray(int[] array) {
        for (int k = 0; k < array.length; k++)
            System.out.print(" " + array[k]);
        System.out.print("\n");
    }
}
