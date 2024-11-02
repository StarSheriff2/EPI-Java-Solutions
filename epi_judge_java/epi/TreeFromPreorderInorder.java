package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TreeFromPreorderInorder {
  @EpiTest(testDataFile = "tree_from_preorder_inorder.tsv")

  public static BinaryTreeNode<Integer>
  binaryTreeFromPreorderInorder(List<Integer> preorder, List<Integer> inorder) {
//    Time is O(n) and Space is (n + h) h for the max call stack

//    Build a hash to store all elements mapped to their index to
//    speed up root lookup in inorder list
    return buildBt(preorder, inorder,
            IntStream.range(0, inorder.size())
                    .boxed()
                    .collect(Collectors.toMap(inorder::get, i -> i))
    );

    // variant 2
//    List<Integer> arr = Arrays.asList(4, 8, 11, 14, 2, 7, 6, 12, 10, 13);
//    BinaryTreeNode<Integer> tree = new BinaryTreeNode<>(arr.get(0));
//    buildMaxTreeBt(arr, 1, tree);
//
//    while (tree.parent != null) {
//      tree = tree.parent;
//    }
//
//    return tree;
  }

  private static BinaryTreeNode<Integer> buildBt(List<Integer> preorderSub,
                                                 List<Integer> inorderSub,
                                                 Map<Integer, Integer> inorderKeyToIndex) {
    if (preorderSub.isEmpty()) {
      return null;
    }

    BinaryTreeNode<Integer> root = new BinaryTreeNode<>(preorderSub.get(0));

//    Get the root index in the inorder sublist using an offset
    int inorderOffset = inorderKeyToIndex.get(inorderSub.get(0)); // the first element in the inorder sublist
    int realInorderRootIdx = inorderKeyToIndex.get(preorderSub.get(0));
    int relativeInorderRootIdx = realInorderRootIdx - inorderOffset;

    root.left = buildBt(
            preorderSub.subList(1, relativeInorderRootIdx + 1),
            inorderSub.subList(0, relativeInorderRootIdx),
            inorderKeyToIndex
    );
    root.right = buildBt(
            preorderSub.subList(relativeInorderRootIdx + 1, preorderSub.size()),
            inorderSub.subList(relativeInorderRootIdx + 1, preorderSub.size()),
            inorderKeyToIndex
    );

    return root;
  }


  // Variant 1:
  // generate tree from inorder and postorder traversal data
  // The idea is the same, hoever, we now stat from the last item in the postorder index, which should be the root of
  // of the tree. The k nodes to the left of the postorder array, correspond to the
  // left subtree, and next k nodes to the right subtree

  // Variant 2:
  // MaxTree
  // each node has to be compared to the prev node
  //    1) if the current node is greater than the last node,
  //      a) if the last node has a parent
  //          i) recursively check the parent, until the current node is less than the parent
  //               - replace the right subtree of the ancestor with the current node
  //               - the replaced tree becomes the left subtree of the current node
  //      a) if the last node has no parent, the last one becomes the left t of the current
  //    2) if the c node is less than the last one, the c node becomes the right t of the prev node
  //
//  private static void buildMaxTreeBt(List<Integer> arr, int currIdx,
//                                                        BinaryTreeNode<Integer> prevNode) {
//    if (currIdx >= arr.size()) {
//      return;
//    }
//
//    BinaryTreeNode<Integer> node = new BinaryTreeNode<>(arr.get(currIdx));
//
//    if (node.data > prevNode.data) {
//      while (prevNode.parent != null && prevNode.parent.data < node.data) {
//        prevNode = prevNode.parent;
//      }
//
//      if (prevNode.parent == null) {
//        node.left = prevNode;
//        prevNode.parent = node;
//      } else {
//        BinaryTreeNode<Integer> temp = prevNode.parent.right;
//        prevNode.parent.right = node;
//        node.parent = prevNode.parent;
//        node.left = temp;
//        temp.parent = node;
//      }
//    } else {
//      prevNode.right = node;
//      node.parent = prevNode;
//    }
//
//    buildMaxTreeBt(arr, ++currIdx, node);
//  }



  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeFromPreorderInorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
