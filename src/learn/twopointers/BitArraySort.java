package learn.twopointers;

public class BitArraySort {
  public static void main(String[] args){
    byte[] array = new byte[]{0, 0, 0, 1,  0, 1, 0, 0, 0, 1, 1, 0, 0, 0};

    int i = 0;
    int j = array.length - 1;

    while(i < j){
      while(array[i] == 0) i++;
      while(array[j] == 1) j--;

      if( i > j){
        break;
      }
      byte temp = array[i];
      array[i] = array[j];
      array[j] = temp;

      i++;
      j--;
    }

    System.out.println(" sorted array:" + array);
    for(i=0; i< array.length; i++){
      System.out.print(array[i] + ", ");
    }
  }
}
