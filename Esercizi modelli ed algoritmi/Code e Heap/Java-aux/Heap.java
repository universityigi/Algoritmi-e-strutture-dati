public class Heap {

    public enum HEAP_TYPE {MAX_HEAP, MIN_HEAP};

    HEAP_TYPE type;
    int size;
    int capacity;
    HeapEntry heap[];

    public static class HeapEntry {
        int key;
        int value;

        protected HeapEntry(int key, int value){
            this.key = key;
            this.value = value;
        }
    }

    public Heap(HEAP_TYPE type, int capacity) {
        this.type = type;
        this.capacity = capacity;
        heap = new HeapEntry[capacity];
    }

    public HEAP_TYPE getType() {
        return type;
    }

    public int peek() {
        if(size == 0)
            return 0;
        else
            return heap[0].key;
    }

    public HeapEntry add(int key) {
        if(size+1 >= capacity){

        }
        HeapEntry newEntry = new HeapEntry(key, 0);
        heap[size] = newEntry;
        upheap(size);
        size++;
        return newEntry;

    }

    public int getEntryKey(HeapEntry e) {
        return e.key;
    }

    public int size() {
        return size;
    }

    public int poll() {
        int res = heap[0].key;
        heap[0] = heap[size-1];
        heap[size--] = null;
        downheap(0);
        return res;
    }

    public static Heap array2heap(int[] array, HEAP_TYPE type) {
        int arrSize = array.length;
        Heap heap = new Heap(HEAP_TYPE.MIN_HEAP, arrSize*2);
        for (int i = 0; i < arrSize; i++){
            heap.heap[i] = new HeapEntry(array[i], 0);
            heap.size++;
        }
        heap.heapify();
        return heap;
    }

    public void print() {
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < size; i++){
            res.append(" "+heap[i].key);
        }
        System.out.println(res);
    }

    public static void heapSort(int[] array) {
        Heap heapSorted = array2heap(array, HEAP_TYPE.MIN_HEAP);
        for(int i = 0; i < array.length; i++){
            array[i] = heapSorted.poll();
        }
    }

    public void updateEntryKey(HeapEntry e, int key) {
        int entryIndex = entryIndex(e);
        if(entryIndex == -1)    return;        
        if(key > e.key){
            e.key = key;
            downheap(entryIndex);
        }
        else{
            e.key = key;
            upheap(entryIndex);
        }
    }

    public int entryIndex(HeapEntry e){
        for(int i = 0; i < size; i++){
            if (heap[i].equals(e))
                return i;
        }
        return -1;
    } 

    public int parent(int pos){
        return (pos-1)/2;
    }

    public int leftIndex(int pos){
        int newPos = 2*pos+1;
        if(newPos >= size){
            return -1;
        }
        return newPos;
    }

    public int rightIndex(int pos){
        int newPos = 2*pos+2;
        if(newPos >= size){
            return -1;
        }
        return newPos;
    }
    
    public void upheap(int pos){
        if(pos == 0)
            return;
        else{
            if(heap[pos].key <= heap[parent(pos)].key){
                HeapEntry temp = heap[pos];
                heap[pos] = heap[parent(pos)];
                heap[parent(pos)] = temp;
                upheap(parent(pos));
            }
            else return;
        }
    }

    public boolean isLeaf(int pos){
        return (leftIndex(pos) == -1) && (rightIndex(pos) == -1);
    }

    public void downheap(int pos){
        if(isLeaf(pos)) return;
        else{
            int toChangeWith = leftIndex(pos);
            if (rightIndex(pos) != -1 && heap[rightIndex(pos)].key < heap[leftIndex(pos)].key){
                toChangeWith = rightIndex(pos);
            }
            if(heap[pos].key > heap[toChangeWith].key){
                HeapEntry temp = heap[pos];
                heap[pos] = heap[toChangeWith];
                heap[toChangeWith] = temp;
                downheap(toChangeWith);
            }
            else return;
        }
    }

    public void heapify(){
        int startIndex = parent(size-1);
        for (int i = startIndex; i >= 0; i--){
            downheap(i);
        }
    }

}
