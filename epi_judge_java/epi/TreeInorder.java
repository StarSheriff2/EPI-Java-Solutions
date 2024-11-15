package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
public class TreeInorder {

  private static class NodeAndState {
    public BinaryTreeNode<Integer> node;
    public Boolean leftSubtreeTraversed;

    public NodeAndState(BinaryTreeNode<Integer> node,
                        Boolean leftSubtreeTraversed) {
      this.node = node;
      this.leftSubtreeTraversed = leftSubtreeTraversed;
    }
  }

  @EpiTest(testDataFile = "tree_inorder.tsv")
  public static List<Integer> inorderTraversal(BinaryTreeNode<Integer> tree) {
    Deque<BinaryTreeNode<Integer>> nodeStack = new ArrayDeque<>();
    List<Integer> inorderTraversalList = new ArrayList<>();

    BinaryTreeNode<Integer> current = tree;
    if (current != null) {
      nodeStack.add(current);
    }

    while (!nodeStack.isEmpty()) {
      if (current.left == null || current != nodeStack.peekLast()) {
        current = nodeStack.removeLast();
        inorderTraversalList.add(current.data);

        if (current.right != null) {
          current = current.right;
          nodeStack.add(current);
        }
      } else {
          current = current.left;
          nodeStack.add(current);
      }
    }

    return inorderTraversalList;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeInorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
