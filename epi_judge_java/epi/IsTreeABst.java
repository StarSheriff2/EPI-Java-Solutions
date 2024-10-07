package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.Deque;

public class IsTreeABst {
  @EpiTest(testDataFile = "is_tree_a_bst.tsv")

  public static boolean isBinaryTreeBST(BinaryTreeNode<Integer> tree) {
  //    <----- Range Approach with DFT (Depth First traversal) with a pre-order traversal ------>
  //    return areKeysWithinInterval(tree, Integer.MIN_VALUE, Integer.MAX_VALUE);

    //    <----- Range Approach with DFT (Breadth First traversal) ------>
    if (tree == null) return true;  // Edge case: empty tree is a valid BST

    Deque<NodeWithBounds<Integer>> queue = new ArrayDeque<>();
    queue.addLast(new NodeWithBounds<>(tree, Integer.MIN_VALUE, Integer.MAX_VALUE));

    return areNodesInRange(queue);
  }

//  private static boolean areKeysWithinInterval(BinaryTreeNode<Integer> node, int min, int max) {
//    <----- Range Approach with DFT (Depth First traversal) with a pre-order traversal ------>
//                        (This can be furthered improved through a in-order traversal, and verifying if prev node
//    isn't greater than the current node)
    // The time complexity is O(N) in worst case, and space complexity is O(2N) is worst case

    // This is a pre-order traversal, which checks the node's data first, before
    // checking its subtrees

    // This is the base case: once it reaches a node with no subtrees, bothe return true
    // therefore the node itself becomes true when its subtrees are evaluated in line 26
//    if (node == null) {
//      return true;
//    }

    // Early return avoids moving deeper into stack
//    if (node.data < min || node.data > max) {
//      return false;
//    }
//
//    return areKeysWithinInterval(node.left, min, node.data) && areKeysWithinInterval(node.right, node.data, max);

    //    <----- END ------>
//  }

  private static boolean areNodesInRange(Deque<NodeWithBounds<Integer>> queue) {
//    <----- Range Approach with DFT (Breadth First traversal) ------>
//    By using BFT we can avoid going deeper into tree if any node at an upper level is not a BST
    while (!queue.isEmpty()) {
      NodeWithBounds<Integer> obj = queue.removeFirst();

      int data = obj.node.data;
      if (data < obj.lower || data > obj.upper) {
        return false;  // Invalid BST if the node's data is out of bounds
      }

      if (obj.node.left != null) {
        queue.addLast(new NodeWithBounds<>(obj.node.left, obj.lower, obj.node.data));
      }
      if (obj.node.right != null ) {
        queue.addLast(new NodeWithBounds<>(obj.node.right, obj.node.data, obj.upper));
      }
    }

    return true;
  }

  public static class NodeWithBounds<T> {
    public BinaryTreeNode<T> node;
    public int lower, upper;

    public NodeWithBounds(BinaryTreeNode<T> node, int lower, int upper) {
      this.node = node;
      this.lower = lower;
      this.upper = upper;
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeABst.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
