package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
public class TreeWithParentInorder {
  @EpiTest(testDataFile = "tree_with_parent_inorder.tsv")

//  public static List<Integer> inorderTraversal(BinaryTree<Integer> tree) {
//    // Time O(n) and space O(1)
//    // 1 if current has left, current equals left
//    // 2 if current has no left
//    //     then add current to list
//    //     then current euqals parent
//    // 3  if current.left equals prev current
//    //       then add current to list
//    //    if current has right repeat steps 1 - 3
//    //    if current has no right
//    //       then current equals parent
//    //       if current.right equals prev current
//    //          then current equals current.parent
//    //  4 repeat until parent is NULL
//
//    BinaryTree<Integer> current = tree;
//    BinaryTree<Integer> prevTree = null;
//    List<Integer> inorderTraversal = new ArrayList<>();
//
//    while (current != null) {
//      if (current.left == prevTree || (current.right != prevTree && current.left == null)) {
//        inorderTraversal.add(current.data);
//
//        if (current.right != null) {
//          current = current.right;
//        } else {
//          prevTree = current;
//          current = current.parent;
//        }
//      } else if (prevTree != null && current.right == prevTree) {
//        prevTree = current;
//        current = current.parent;
//      } else {
//        current = current.left;
//      }
//    }
//
//    return inorderTraversal;
//  }

  // Variants:
  // Variant 1: Preorder traversal
//  public static List<Integer> prerderTraversal(BinaryTree<Integer> tree) {
//    //    Time O(n) and space O(1)
//
//    BinaryTree<Integer> curr = tree;
//    BinaryTree<Integer> prev = null;
//    List<Integer> preorderTraversal = new ArrayList<>();
//
//    while (curr != null) {
//      BinaryTree<Integer> next;
//
//      if (curr.left == prev) {
//        next = (curr.right != null) ? curr.right : curr.parent;
//      } else if (curr.right == prev) {
//        next = curr.parent;
//      } else {
//        preorderTraversal.add(curr.data);
//
//        next = (curr.left != null) ? curr.left : (curr.right != null) ? curr.right : curr.parent;
//      }
//
//      prev = curr;
//      curr = next;
//    }
//
//    return preorderTraversal;
//  }

  // Note: Variant 1: Postorder traversal
  public static List<Integer> postrderTraversal(BinaryTree<Integer> tree) {
    //    Time O(n) and space O(1)

    BinaryTree<Integer> curr = tree;
    BinaryTree<Integer> prev = null;
    List<Integer> postorderTraversal = new ArrayList<>();

    while (curr != null) {
      BinaryTree<Integer> next;

      if (curr.right == prev
              || curr.left == prev && curr.right == null
              || curr.left == null && curr.right == null) {
        postorderTraversal.add(curr.data);
        next = curr.parent;
      } else {
        next = (curr.left == prev || curr.left == null) ? curr.right : curr.left;
      }

      prev = curr;
      curr = next;
    }

    return postorderTraversal;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeWithParentInorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
