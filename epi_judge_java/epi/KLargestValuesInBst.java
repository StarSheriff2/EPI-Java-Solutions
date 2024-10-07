package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiPredicate;
public class KLargestValuesInBst {
  @EpiTest(testDataFile = "k_largest_values_in_bst.tsv")

  public static List<Integer> findKLargestInBst(BstNode<Integer> tree, int k) {
    List<Integer> kLargest = new ArrayList<>(k);
    postOrderTraversal(tree, k, kLargest);
    return new ArrayList<>(kLargest);
  }

  private static void postOrderTraversal(BstNode<Integer> tree, int k, List<Integer> kLargest) {
    if (tree != null) {
      postOrderTraversal(tree.right, k, kLargest);

      if (kLargest.size() < k) {
        kLargest.add(tree.data);
      }

      if (kLargest.size() < k) {
        postOrderTraversal(tree.left, k, kLargest);
      }
    }
  }


  @EpiTestComparator
  public static boolean comp(List<Integer> expected, List<Integer> result) {
    if (result == null) {
      return false;
    }
    Collections.sort(expected);
    Collections.sort(result);
    return expected.equals(result);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "KLargestValuesInBst.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
