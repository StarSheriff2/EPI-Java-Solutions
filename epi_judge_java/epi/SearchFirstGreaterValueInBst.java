package epi;
import com.sun.source.tree.Tree;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;

public class SearchFirstGreaterValueInBst {

  public static BstNode<Integer> findFirstGreaterThanK(BstNode<Integer> tree,
                                                       Integer k) {
//    O(H) time complexity since we levarage the binary search strucure of the tree
//    to ignore the left subtree for each level if its root is < k
//    Space complexity is O(H) too; using a while loop could help us get rid of
//    additional space complexity
    if (tree == null) {
      return null;
    }

    if (tree.left != null && k < tree.data) {
      BstNode<Integer> t = findFirstGreaterThanK(tree.left, k);

      if (t != null && k < t.data) {
        return t;
      }
    }

    if (tree.data > k) {
      return tree;
    }

    if (tree.right != null) {
      BstNode<Integer> t = findFirstGreaterThanK(tree.right, k);

      if (t != null && k < t.data) {
        return t;
      }
    }

    return null;
  }

//  Variation 1:
//  Find the first node whos value equals a given value, in an in order traversal,
//  given a BST with duplicates
//  Solution:
//  Perform an inorder traversal leveragin binary search, just as in the problem here,
//  with the addition that
//  1) we check if a node is >= to the given value,
//  2) we store the node and its relative position to the main root
//  3) and explore the left subtree if the main root is >= the key
//  4) we explore the right subtree only if the subtree's root is < the key AND there
//  is no candidate so far
  @EpiTest(testDataFile = "search_first_greater_value_in_bst.tsv")
  public static int findFirstGreaterThanKWrapper(BstNode<Integer> tree,
                                                 Integer k) {
    BstNode<Integer> result = findFirstGreaterThanK(tree, k);
    return result != null ? result.data : -1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchFirstGreaterValueInBst.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
