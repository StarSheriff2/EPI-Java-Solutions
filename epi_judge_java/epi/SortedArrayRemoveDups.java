package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class SortedArrayRemoveDups {
  public static int deleteDuplicates(List<Integer> A) {
    System.out.println("\nCurrent A: " + Arrays.toString(A.toArray()));
    if (A.isEmpty()) {
      return 0;
    }

    int j = 0;

    for (int i = 1; i < A.size(); i++) {
      if (!A.get(i).equals(A.get(j))) {
        A.set(++j, A.get(i));
      }
    }

    System.out.println("A no Dups: " + Arrays.toString(A.toArray()));
    return j + 1;
  }
  @EpiTest(testDataFile = "sorted_array_remove_dups.tsv")
  public static List<Integer> deleteDuplicatesWrapper(TimedExecutor executor,
                                                      List<Integer> A)
      throws Exception {
    int end = executor.run(() -> deleteDuplicates(A));
    return A.subList(0, end);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortedArrayRemoveDups.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
