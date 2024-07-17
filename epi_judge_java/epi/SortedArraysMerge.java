package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.lang.reflect.Array;
import java.util.*;

public class SortedArraysMerge {
  @EpiTest(testDataFile = "sorted_arrays_merge.tsv")

  public static List<Integer>
  mergeSortedArrays(List<List<Integer>> sortedArrays) {
    List<Iterator<Integer>> iters = new ArrayList<>(sortedArrays.size());
    for (List<Integer> sortedArray : sortedArrays) {
        iters.add(sortedArray.iterator());
    }

   PriorityQueue<ArrayValue> minHeap = new PriorityQueue<>(
            sortedArrays.size(),
            (x, y) -> Integer.compare(x.value, y.value)
    );

    for (int i = 0; i < iters.size(); i++) {
      minHeap.add(new ArrayValue(iters.get(i).next(), i));
    }

    List<Integer> result = new ArrayList<>();
    while(!minHeap.isEmpty()) {
      ArrayValue headValue = minHeap.poll();
      result.add(headValue.value);

      if (iters.get(headValue.arrayId).hasNext()) {
        minHeap.add(new ArrayValue(iters.get(headValue.arrayId).next(), headValue.arrayId));
      }
    }

    return result;
  }

  private static class ArrayValue{
    public int value;
    public int arrayId;

    public ArrayValue(int value, int arrayId) {
      this.value = value;
      this.arrayId = arrayId;
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortedArraysMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
