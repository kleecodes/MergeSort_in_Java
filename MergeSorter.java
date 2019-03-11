import java.util.Scanner;
public class MergeSorter extends Sorter{

  @Override
  @SuppressWarnings("unchecked")
  public <E extends Comparable<E>> void sort(E[] array){
    // Object[] spare = (E[]) new Object[array.length/2];
    E[] spare = (E[])new Comparable[array.length];
    this.sort(array, 0, array.length, spare);
  }
/** Private sorter method that does the dirty work.
  * @param E array to sort
  * @param E spare array that is half the size
  * @param int to start
  * @param int to end
  */
  private <E extends Comparable<E>> void sort(E[] array, int start, int end, E[] spare){
    mergesort(array, spare, end);
    // for(int i = 0; i < array.length; i++){
    //   if(i == array.length-1){
    //     System.out.print(array[i] + "\n");
    //   }
    //   else{
    //     System.out.print(array[i] + ", ");
    //   }
    // }
  }
  private <E extends Comparable<E>> void mergesort(E[] array, E[] spare, int n){
    copyArray(array, 0, n, spare);
    split(spare, 0, n, array);
  }
  private <E extends Comparable<E>> void merge(E[] array, int start, int mid, int end, E[] spare){
    int i = start;
    int j = mid;
    for(int k = start; k < end; k++){
      if(i < mid && (j >= end || (array[i].compareTo(array[j]) < 1))){
        spare[k] = array[i];
        i++;
      }
      else{
        spare[k] = array[j];
        j++;
      }
    }
  }
  private <E extends Comparable<E>> void split(E[] spare, int start, int end, E[] array){
    if((end - start) < 2){
      return;
    }
    int mid = (end + start) / 2;
    split(array, start, mid, spare);
    split(array, mid, end, spare);
    merge(spare, start, mid, end, array);
  }


  private <E extends Comparable<E>> void copyArray(E[] one, int start, int end, E[] two){
    for(int i = start; i < end; i++){
      two[i] = one[i];
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
      System.out.print("Please enter a positive integer to selections sort: ");
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
