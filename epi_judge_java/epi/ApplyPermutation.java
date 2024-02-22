package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class ApplyPermutation {
  public static void applyPermutation(List<Integer> perm, List<Integer> A) {
    //    Optimized for memory: O(N) Space and Time complexity,
    //    with no additional storage and no modifying of P
//    In order to not modify P we have to mark A once it's visited
//    This forces us to make a second pass to decode A
//    Average running time:  123 us
  //  Median running time:    15 us
    for (int i = 0; i < A.size(); i++) {
      Integer targetPerm = perm.get(i);

      while (!(targetPerm.equals(i)) && A.get(i) >= 0) {
        Collections.swap(A, i, targetPerm);

        int encoded = A.get(targetPerm) + 1;
        A.set(targetPerm, (-1 * encoded));
        targetPerm = perm.get(targetPerm);
      }

      if (targetPerm.equals(i)) {
        int encoded = A.get(targetPerm) + 1;
        A.set(targetPerm, (-1 * encoded));
      }
    }

      A.replaceAll(integer -> -1 * integer - 1);


//    OPtimized for memory: O(N) Space complexity with no additional storage
//    Average running time:   81 us
//    Median running time:    10 us
//    System.out.println(Arrays.toString(A.toArray()));

//    for (int i = 0; i < A.size(); i++) {
//      while (!(perm.get(i).equals(i))) {
//        Collections.swap(A, i, perm.get(i));
//
//        Collections.swap(perm, i, perm.get(i));
//      }
//    }
//    System.out.println(Arrays.toString(A.toArray()));

//    Memory heavy approach: O(n) space complexity because of additional array
//    List<Integer> B = new ArrayList<>(A);
//
//    for (int i = 0; i < A.size(); i++) {
//      A.set(perm.get(i), B.get(i));
//    }
//
//    System.out.println(Arrays.toString(A.toArray()));
  }
  @EpiTest(testDataFile = "apply_permutation.tsv")
  public static List<Integer> applyPermutationWrapper(List<Integer> perm,
                                                      List<Integer> A) {
    applyPermutation(perm, A);
    return A;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ApplyPermutation.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
