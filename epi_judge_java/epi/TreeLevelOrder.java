package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TreeLevelOrder {
  @EpiTest(testDataFile = "tree_level_order.tsv")

  public static List<List<Integer>>
  binaryTreeDepthOrder(BinaryTreeNode<Integer> tree) {
    List<List<Integer>> result = new ArrayList<>();

    if (tree == null) {
      return result;
    }

    List<BinaryTreeNode<Integer>> nodeArr = List.of(tree);
    while (!nodeArr.isEmpty()) {
      result.add(nodeArr.stream()
              .map(node -> node.data)
              .toList());
      nodeArr = nodeArr.stream()
              .map(node -> Arrays.asList(node.left, node.right))
              .flatMap(List::stream)
              .filter(Objects::nonNull)
              .toList();
    }

    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeLevelOrder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
