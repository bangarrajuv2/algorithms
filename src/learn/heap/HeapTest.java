package learn.heap;

import java.util.ArrayList;
import java.util.List;

public class HeapTest {

  public static void main(String[] args){
      int[] arr = new int[]{4, 5, 3, 2, 1};
      List<Integer> list = new ArrayList<>();

    costOfStrings(arr);

//      for(int i=0; i< arr.length; i++){
//        list.add(arr[i]);
//      }
//
//      for(int i=0; i< list.size(); i++){
//        System.out.println(i + "=" + list.get(i));
//      }

  }

  private static void costOfStrings(int[] arr){

    List<Integer> heap= new ArrayList<>();
    for(int i=0; i<arr.length; i++){
      insertElement(heap, arr[i]);
    }

//    for(int i=0; i<heap.size(); i++){
//      System.out.print(heap.get(i) + ",");
//    }

    System.out.println();
    int totalCost = 0;
    while(heap.size() > 1){
      int minEle1 = deleteElement(heap);
      int minEle2 = deleteElement(heap);

      int additionCost = minEle2 != -1 ? (minEle1 + minEle2) : minEle1;
      totalCost += additionCost;
      System.out.println(" totalCost = " + totalCost + "(" + minEle1 + ", " + minEle2 + ")");

      if(minEle2 == -1 ) break;
      insertElement(heap, additionCost);
    }

    System.out.println(" Total Cost=" + totalCost);
  }

  private static void buildHeap(int[] arr){

    List<Integer> heap= new ArrayList<>();
    for(int i=0; i<arr.length; i++){
      insertElement(heap, arr[i]);
    }

    for(int i=0; i<heap.size(); i++){
      System.out.print(heap.get(i) + ",");
    }

    System.out.println();
    while(heap.size() > 0){
      System.out.print(heap.size() + "=" + deleteElement(heap) + ",");
    }

  }

  private static void insertElement(List<Integer> heap , int input){
    // store the element in heap
    heap.add(input);

    int childIdx = heap.size() - 1;
    int parentIdx = getParentIdx(childIdx);
    //balance heap
    int parent = heap.get(parentIdx);
    int child = input;

    while(child < parent){
      //swap parent and child
      heap.set(parentIdx, child);
      heap.set(childIdx, parent);

      if(parentIdx == 0){
        break;
      }
      childIdx = parentIdx;
      parentIdx = getParentIdx(childIdx);

      parent = heap.get(parentIdx);
      child = heap.get(childIdx);
    }

  }

  private static int deleteElement(List<Integer> heap) {

    if(heap.size() == 0){
      return -1;
    }else {

      // remove the root
      int root = heap.get(0);

      // balance the remaining heap

      /*choose child to promote*/
      int parentIdx = 0;
      int childIdx = getChildToPromote(heap, parentIdx);

      while (childIdx <= heap.size() - 1 && childIdx != -1) {

        int child = heap.get(childIdx);
        int parent = heap.get(parentIdx);

        //swap parent and child
        heap.set(parentIdx, child);
        heap.set(childIdx, parent);

        parentIdx = childIdx;
        childIdx = getChildToPromote(heap, parentIdx);
      }

      if (parentIdx == heap.size() - 1) {
        // remove the last element in the heap.
        heap.remove(heap.size() - 1);
      } else {
        while (parentIdx < heap.size() - 1) {
          heap.set(parentIdx, heap.get(parentIdx + 1));
          parentIdx++;
        }
        heap.remove(parentIdx);
      }

      //return the root
      return root;
    }
  }

    private static int getParentIdx(int childIdx){
    return (childIdx - 1)/2;
  }

  private static int getChildToPromote(List<Integer> heap, int parentIdx) {
    int childIdx1 = 2 * parentIdx + 1;
    int childIdx2 = 2 * parentIdx + 2;

    int childIdx = -1;

    if (childIdx1 < heap.size() && childIdx2 < heap.size()) {
      childIdx = heap.get(childIdx1) < heap.get(childIdx2) ? (childIdx1) : (childIdx2);
    }else{
      childIdx = childIdx1 < heap.size() ? childIdx1 : -1;
    }
    return childIdx;
  }
}

//[4, 5, 3, 2, 1], = 3
//[4, 5, 3, 3], = 6
//[4, 5, 6], = 9
//[6, 9], 6 = 15
