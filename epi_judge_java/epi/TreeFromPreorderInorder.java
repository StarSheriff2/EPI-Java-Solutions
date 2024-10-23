package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class TreeFromPreorderInorder {
  @EpiTest(testDataFile = "tree_from_preorder_inorder.tsv")

  public static BinaryTreeNode<Integer>
  binaryTreeFromPreorderInorder(List<Integer> preorder, List<Integer> inorder) {
//    Time is O(n) and Space is (n)
    return buildBt(preorder, inorder);
  }

  private static BinaryTreeNode<Integer> buildBt(List<Integer> preorder, List<Integer> inorder) {
    if (preorder.isEmpty()) {
      return null;
    }

    BinaryTreeNode<Integer> root = new BinaryTreeNode<>();

    int i = 0;
    while (!inorder.get(i).equals(preorder.get(0))) {
      i++;
    }

    root.data = preorder.get(0);
    root.left = buildBt(preorder.subList(1, i + 1), inorder.subList(0, i));
    root.right = buildBt(preorder.subList(i + 1, preorder.size()), inorder.subList(i + 1, preorder.size()));

    return root;
  }


  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeFromPreorderInorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
