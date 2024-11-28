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
//  public static List<Integer> inorderTraversal(BinaryTreeNode<Integer> tree) {
////    Time O(n) Space O(N + H)
//    Deque<BinaryTreeNode<Integer>> nodeStack = new ArrayDeque<>();
//    List<Integer> inorderTraversalList = new ArrayList<>();
//
//    BinaryTreeNode<Integer> current = tree;
//    if (current != null) {
//      nodeStack.add(current);
//    }
//
//    while (!nodeStack.isEmpty()) {
//      if (current.left == null || current != nodeStack.peekLast()) {
//        current = nodeStack.removeLast();
//        inorderTraversalList.add(current.data);
//
//        if (current.right != null) {
//          current = current.right;
//          nodeStack.add(current);
//        }
//      } else {
//          current = current.left;
//          nodeStack.add(current);
//      }
//    }
//
//    return inorderTraversalList;
//  }


  // Variant 1: Preorder traversal
//  public static List<Integer> preorderTraversal(BinaryTreeNode<Integer> tree) {
//    // Time O(n) Space O(N + H)
//    // Same as before, but with the difference that:
//    // - we pop the stack on every iteration
//    // - quee first the right tree; then the left
//    // - if left != null, then current = left; otherwise, right
//    // - if a tree has no left or right subtrees, current becomes the next element
//    // in the stack
//    Deque<BinaryTreeNode<Integer>> nodeStack = new ArrayDeque<>();
//    List<Integer> preorderTraversalList = new ArrayList<>();
//
//    BinaryTreeNode<Integer> current;
//    if (tree != null) {
//      nodeStack.add(tree);
//    }
//
//    while (!nodeStack.isEmpty()) {
//      current = nodeStack.removeLast();
//      preorderTraversalList.add(current.data);
//
//      if (current.right != null) {
//        nodeStack.add(current.right);
//      }
//      if (current.left != null) {
//        nodeStack.add(current.left);
//      }
//    }
//
//    return preorderTraversalList;
//  }

  // Variant 2: Postorder traversal
  public static List<Integer> postorderTraversal(BinaryTreeNode<Integer> tree) {
    // Time O(n) Space O(N + H)
    // store each node with its visited status
    // initially all nodes are added to the stack with visited set to false
    // Add root to stack
    // on each iteration do this:
    // set current to the last element in the stack (don't pop yet!) and do this:
    // if current node's status == true OR current has no children
    //    then pop the last element in stack
    //    and set current to next element in stack
    // else
    //    add right tree to stack, and then the left tree
    //    set current to last element without popping
    Deque<NodeAndVisitedState> nodeStack = new ArrayDeque<>();
    List<Integer> postorderTraversalList = new ArrayList<>();

    NodeAndVisitedState current;
    if (tree != null) {
      nodeStack.add(new NodeAndVisitedState(tree, false));
    }

    while (!nodeStack.isEmpty()) {
      current = nodeStack.peekLast();

      if (current.visited || (current.node.left == null && current.node.right == null)) {
        postorderTraversalList.add(nodeStack.removeLast().node.data);
      } else {
        if (current.node.right != null) {
          nodeStack.add(new NodeAndVisitedState(current.node.right, false));
        }
        if (current.node.left != null) {
          nodeStack.add(new NodeAndVisitedState(current.node.left, false));
        }

        // we mark it as visited after adding current's children to the stack
        current.visited = true;
      }
    }

    return postorderTraversalList;
  }

  private static class NodeAndVisitedState {
    public BinaryTreeNode<Integer> node;
    public Boolean visited;
    public NodeAndVisitedState(BinaryTreeNode<Integer> node, Boolean visited) {
      this.node = node;
      this.visited = visited;
    }
  }


  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeInorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
