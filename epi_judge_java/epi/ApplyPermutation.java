package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class ApplyPermutation {
  public static void applyPermutation(List<Integer> perm, List<Integer> A) {
//    List<Integer> B = new ArrayList<>(A);
//    perm: 2 0 1 3
//    A: 0 1 2 3
//    Final: 1 2 0 3

//    Iteration 0
//    perm[i] = 2
//    temp = A[2] = 2
//    A[i] = 0
//    i = 2
//    writes = 1;
//    A = 0103
//    Iteration 1
//    perm[i] = 1
//    temp = 1
//    A[i] = 2
//    i  = 1
//    writes 2
//    A = 0203
//    temp = 0
//    A = 1203

    List<Integer> perm_t = new ArrayList<>(Arrays.asList(5, 0, 6, 3, 4, 1, 2));
    List<Integer> B = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6));
    //    perm_t: 5, 0, 6, 3, 4, 1, 2
    //    B:      0, 1, 2, 3, 4, 5, 6
    //    Final:  1, 5, 6, 3, 4, 0, 2
//                1, 5, 2, 3, 4, 0, 6

    System.out.println(Arrays.toString(B.toArray()));
    int i = 0;
    int writes = 0;
    int temp = B.get(0);
    while (writes < B.size()) {
      if (perm_t.get(i) == i) {
        i = ++writes;
        temp = B.get(i);
      } else {
        int subTemp = B.get(perm_t.get(i));
        B.set(perm_t.get(i), temp);
        temp = subTemp;
        i = temp;
        writes++;
      }

      System.out.println(Arrays.toString(B.toArray()));
    }
//    Brute force approach
//    List<Integer> C = new ArrayList<>(B);
//
//    for (int i = 0; i < B.size(); i++) {
//      B.set(perm_t.get(i), C.get(i));
//    }
//
//    System.out.println(Arrays.toString(B.toArray()));
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
