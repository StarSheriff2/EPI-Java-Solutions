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

    while (current != null) {
      nodeStack.add(current);

      if (current.left != null) {
        current = current.left;
      } else {
        inorderTraversalList.add(nodeStack.removeLast().data);

        if (current.right != null) {
          current = current.right;
        } else {
          if (current.right == null && !nodeStack.isEmpty()) {
            current = nodeStack.removeLast();
            inorderTraversalList.add(current.data);

            current = current.right;
            while (current == null && !nodeStack.isEmpty()) {
              current = nodeStack.removeLast();
              inorderTraversalList.add(current.data);

              current = current.right;
            }
          } else {
            current = current.right;
          }
        }
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
