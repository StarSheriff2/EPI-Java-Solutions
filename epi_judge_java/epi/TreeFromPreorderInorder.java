package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
  }

  private static BinaryTreeNode<Integer> buildBt(List<Integer> preorderSub, List<Integer> inorderSub, Map<Integer, Integer> inorderKeyToIndex) {
    if (preorderSub.isEmpty()) {
      return null;
    }

    BinaryTreeNode<Integer> root = new BinaryTreeNode<>(preorderSub.get(0));

//    Get the root index in the inorder sublist using an offset
    int inorderOffset = inorderKeyToIndex.get(inorderSub.get(0)); // the first element in the inorder sublist
    int realInorderRootIdx = inorderKeyToIndex.get(preorderSub.get(0));
    int relativeInorderRootIdx = realInorderRootIdx - inorderOffset;

    root.left = buildBt(preorderSub.subList(1, relativeInorderRootIdx + 1), inorderSub.subList(0, relativeInorderRootIdx), inorderKeyToIndex);
    root.right = buildBt(preorderSub.subList(relativeInorderRootIdx + 1, preorderSub.size()), inorderSub.subList(relativeInorderRootIdx + 1, preorderSub.size()), inorderKeyToIndex);

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
