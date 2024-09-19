package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TwoSortedArraysMerge {

  public static void mergeTwoSortedArrays(List<Integer> A, int m,
                                          List<Integer> B, int n) {
    List<Integer> result = new ArrayList<>();
//     O(M + N) in both time and space complexity

    int i = 0;
    int j = 0;
    while (i < m && j < n) {
      if (i != 0 && A.get(i).equals(A.get(i - 1))) {
        result.add(A.get(i));
        i++;
      } else if (j != 0 && B.get(j).equals(B.get(j - 1))) {
        result.add(B.get(j));
        j++;
      } else {
        if (A.get(i) <= B.get(j)) {
          result.add(A.get(i));
          i++;
        } else { // b is less than a
          result.add(B.get(j));
          j++;
        }
      }
    }

    while (j < n) {
      result.add(B.get(j));
      j++;
    }

    while (i < m) {
      result.add(A.get(i));
      i++;
    }

    Collections.copy(A, result);
  }
  @EpiTest(testDataFile = "two_sorted_arrays_merge.tsv")
  public static List<Integer>
  mergeTwoSortedArraysWrapper(List<Integer> A, int m, List<Integer> B, int n) {
    mergeTwoSortedArrays(A, m, B, n);
    return A;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TwoSortedArraysMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
