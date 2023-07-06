public class MaxGap {

    public static int maxGap(int[] array, int start, int end) {
      	if(end-start < 2){
          return Math.abs(array[end]-array[start]);
        }
        else{
          int mid = (end+start) / 2;
          int a = maxGap(array, start, mid);
          int b = maxGap(array, mid, end);
          return Integer.max(a,b);
        }
    }
}
