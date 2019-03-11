import java.util.Scanner;
public class mergesorter extends Sorter{

  @Override
  @SuppressWarnings("unchecked")
  public <E extends Comparable<E>> void sort(E[] array){
    // Object[] spare = (E[]) new Object[array.length/2];
    E[] spare = (E[])new Comparable[array.length];
    sort(array, spare, array.length);
  }
/** Private sorter method that does the dirty work.
  * @param E array to sort
  * @param E spare array that is half the size
  * @param int to start
  * @param int to end
  */
  private <E extends Comparable<E>> void sort(E[] array, E[] spare, int size){
    copyArray(array, 0, size, spare);
    mergeSort(array, spare, 0, array.length - 1);
  }

  private <E extends Comparable<E>> void merge(E[] array, E[] spare, int left, int right, int rightEnd){
    int leftEnd = right - 1;
    int k = left;
    int num = rightEnd -left + 1;
    while(left <= leftEnd && right <= rightEnd){
      if(array[left].compareTo(array[right]) <= 0){
        spare[k+1] = array[left+1];
      }
      else{
        spare[k++] = array[right++];
      }
    }
    while(left <= leftEnd){
      spare[k++] = array[left++];
    }
    while(right <= rightEnd){
      spare[k++] = array[right++];
    }
    for(int i = 0; i < num; i++, rightEnd--){
      array[rightEnd] = spare[rightEnd];
    }
  }
  private <E extends Comparable<E>> void copyArray(E[] array, int start, int end, E[] spare){
    for(int i = start; i < end; i++){
      spare[i] = array[i];
    }
  }
  private <E extends Comparable<E>> void mergeSort(E[] array, E[] spare, int left, int right){
    if(left < right){
      int center = (left + right)/2;
      mergeSort(array, spare, left, center);
      mergeSort(array, spare, center + 1, right);
      merge(array, spare, left, center + 1, right);
    }
  }



  public static void main(String[] args){
    Scanner calledScanner = new Scanner(System.in);
    System.out.println("Welcome!");
    String input;
    boolean try_again;
    int size = 0;
    do{
      try_again = false;
      System.out.println("Please enter a positive integer to selections sort: ");
      input = calledScanner.nextLine();
      try{
        size = Integer.parseInt(input);
      }
      catch(NumberFormatException e){
        System.out.println("that was not a positive number");
        try_again = true;
      }
      if(size <= 0) try_again = true;
    } while(try_again);

    MergeSorter ss = new MergeSorter();

    System.out.println("time took to sort selection: " + ss.timeSort(size) + "ms");
  }


}
