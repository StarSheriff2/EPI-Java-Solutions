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
//    List<Integer> result = new ArrayList<>();
//     O(M + N) in both time and space complexity

//    int i = 0;
//    int b = 0;
//    while (i < m && b < n) {
//      if (i != 0 && A.get(i).equals(A.get(i - 1))) {
//        result.add(A.get(i));
//        i++;
//      } else if (b != 0 && B.get(b).equals(B.get(b - 1))) {
//        result.add(B.get(b));
//        b++;
//      } else {
//        if (A.get(i) <= B.get(b)) {
//          result.add(A.get(i));
//          i++;
//        } else { // b is less than a
//          result.add(B.get(b));
//          b++;
//        }
//      }
//    }
//
//    while (b < n) {
//      result.add(B.get(b));
//      b++;
//    }
//
//    while (i < m) {
//      result.add(A.get(i));
//      i++;
//    }
//
//    Collections.copy(A, result);

    int a = m - 1, b = n - 1, writeIdx = A.size() - 1; //    <<-- Second approach -->>
//    O(M + N) in time and O(1) in space complexity
    while (a >= 0 && b >= 0) {
//      We start from the end because in this way we know we will have
//      enough empty slots without worrying about overwriting index values
//      There are only two possiblities, either add A<a> or B<b>
//      If none of the conditions where A<a> is the next value to write,
//      then by default everything else (any other possiblities) necessarily
//      fall under the B<b> option
      A.set(writeIdx--, A.get(a) >= B.get(b) ?  A.get(a--) : B.get(b--));
    }

    while (b >= 0) {
      A.set(writeIdx--, B.get(b--));
    }
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
