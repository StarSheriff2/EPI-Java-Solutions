package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class IntersectSortedArrays {
  @EpiTest(testDataFile = "intersect_sorted_arrays.tsv")

  public static List<Integer> intersectTwoSortedArrays(List<Integer> A,
                                                       List<Integer> B) {
    List<Integer> result = new ArrayList<>();
    for (int i = 0, j = 0; i < A.size() && j < B.size(); i++) {
      int value = A.get(i);
      if (i == 0 || value != A.get(i - 1)) {
        int newPivot = Collections.binarySearch(B.subList(j, B.size()), value);
        if (newPivot >= 0) {
          result.add(value);
          j = newPivot + 1;
        } else {
          j = (newPivot + 1) * -1;
        }
      }
    }

    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntersectSortedArrays.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
