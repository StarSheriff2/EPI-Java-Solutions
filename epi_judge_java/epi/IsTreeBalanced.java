package epi;
import Variations.src.Main;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeBalanced {

  @EpiTest(testDataFile = "is_tree_balanced.tsv")

  public static boolean isBalanced(BinaryTreeNode<Integer> tree) {
    return checkBalanced(tree).balanced;
  }

  private static BalancedStatusWithHeight checkBalanced(BinaryTreeNode<Integer> tree) {
    if (tree == null) {
      return new BalancedStatusWithHeight(true, -1);
    }

    BalancedStatusWithHeight leftSubtree = checkBalanced(tree.left);
    if (!leftSubtree.balanced) {
      return leftSubtree;
    }

    BalancedStatusWithHeight rightSubtree = checkBalanced(tree.right);
    if (!rightSubtree.balanced) {
      return rightSubtree;
    }

    boolean balanced = Math.abs(leftSubtree.height - rightSubtree.height) <= 1;
    int height = Math.max(leftSubtree.height, rightSubtree.height) + 1;
    return new BalancedStatusWithHeight(balanced, height);
  }

  private static class BalancedStatusWithHeight {
    public boolean balanced;
    public int height;

    public BalancedStatusWithHeight(boolean balanced, int height){
      this.balanced = balanced;
      this.height = height;
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeBalanced.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
