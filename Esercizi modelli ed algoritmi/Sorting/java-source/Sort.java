import java.util.*;

public class Sort {

    /* Disponibile in libreria java */
    public void quickSortDefault(int[] array) {
        Arrays.sort(array);
    }

    public void mergeSort(int[] array) {
        if(array.length <= 1)
            return;
        else{
            int half = array.length / 2;
            int[] leftArr = Arrays.copyOfRange(array, 0, half);
            int[] rightArr = Arrays.copyOfRange(array, half, array.length);
            mergeSort(leftArr);
            mergeSort(rightArr);
            merge(leftArr, rightArr, array);
        }
    }

    public void merge(int[] left, int[] right, int[] orig){
        int i = 0;
        int j = 0;
        int x = 0;

        while(i<left.length && j < right.length){
            if(left[i]<right[j]){
                orig[x++] = left[i++];
            }
            else orig[x++] = right[j++];
        }

        while(i<left.length)    orig[x++] = left[i++];
        while(j<right.length)   orig[x++] = right[j++];
    }

    public void heapSort(int[] array) {
        Heap.heapSort(array);
        return;
    }

    public void insertionSort(int[] array) {            //in-place
        for(int i = 1; i < array.length; i++){
            for (int j = i; j > 0; j--){
                if(array[j]<array[j-1]){
                    int temp = array[j];
                    array[j] = array[j-1];
                    array[j-1] = temp;
                }
                else break;
            }
        }
        return;
    }

    public void selectionSort(int[] array) {
        System.out.println("selectionSort non è ancora implementato");
        return;
    }

    public void quickSort(int[] array) {
        int size = array.length;
        for (int i = 1; i < size-1; i++){
            
        }
    }

    public void radixSort(int[] array) {
        System.out.println("radixSort non è ancora implementato");
        return;
    }

    public void bucketSort(int[] array) {
        System.out.println("bucketSort non è ancora implementato");
        return;
    }
}
