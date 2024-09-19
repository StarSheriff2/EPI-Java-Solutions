package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class IntersectSortedArrays {
  @EpiTest(testDataFile = "intersect_sorted_arrays.tsv")

  public static List<Integer> intersectTwoSortedArrays(List<Integer> A,
                                                       List<Integer> B) {
//    Early return if there is no overlapping between the lists
    if (A.isEmpty() || B.isEmpty() ||
            ((A.get(A.size() - 1)) < B.get(0)) ||
            ((B.get(B.size() - 1)) < A.get(0))) {
      return new ArrayList<>();
    }

//    Both arrays should begin from the nearest value in common
//    to avoid unecessary searching
    int i = 0;
    while (A.get(i) < B.get(0) && (i + 1) < A.size()) {
      i++;
    }

    int j = 0;
    while (A.get(i) > B.get(j) && (j + 1) < B.size()) {
      j++;
    }


    List<Integer> result = new ArrayList<>();
    for (; i < A.size() && j < B.size(); i++) {
      int value = A.get(i);
      if (i == 0 || value != A.get(i - 1)) {
        int newPivot = Collections.binarySearch(B.subList(j, B.size()), value);
        if (newPivot >= 0) {
          result.add(value);
          j = newPivot + 1;
        } else {
          j = newPivot * -1;
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
