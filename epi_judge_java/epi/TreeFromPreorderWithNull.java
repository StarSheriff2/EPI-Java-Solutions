package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.List;
public class TreeFromPreorderWithNull {
  public static int currIdx;

  public static BinaryTreeNode<Integer>
  reconstructPreorder(List<Integer> preorder) {
    currIdx = 0;
    return buildBinaryTree(preorder);

    // Variant 1:
    // It't the same process with the difference that
    // we start from the last node and move backwards as we iterate
  }

  private static BinaryTreeNode<Integer>
  buildBinaryTree(List<Integer> preorder) {
    Integer subtreeKey = preorder.get(currIdx);
    ++currIdx;

    if (subtreeKey == null) {
      return null;
    }

    BinaryTreeNode<Integer> tree = new BinaryTreeNode<>(subtreeKey);

    tree.left = buildBinaryTree(preorder);
    tree.right = buildBinaryTree(preorder);

    return tree;
  }

  @EpiTest(testDataFile = "tree_from_preorder_with_null.tsv")
  public static BinaryTreeNode<Integer>
  reconstructPreorderWrapper(TimedExecutor executor, List<String> strings)
      throws Exception {
    List<Integer> ints = new ArrayList<>();
    for (String s : strings) {
      if (s.equals("null")) {
        ints.add(null);
      } else {
        ints.add(Integer.parseInt(s));
      }
    }

    return executor.run(() -> reconstructPreorder(ints));
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeFromPreorderWithNull.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
