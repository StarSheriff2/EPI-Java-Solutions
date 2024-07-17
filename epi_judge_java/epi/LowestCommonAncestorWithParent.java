package epi;
import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
public class LowestCommonAncestorWithParent {

  public static BinaryTree<Integer> lca(BinaryTree<Integer> node0,
                                        BinaryTree<Integer> node1) {
//    Get each nodes depth
    int depthN0 = getDepth(node0), depthN1 = getDepth(node1);

//   Get both nodes to the place where both are at the same depth
    while (depthN0 < depthN1) {
      node1 = node1.parent;
      depthN1--;
    }
    while (depthN1 < depthN0) {
      node0 = node0.parent;
      depthN0--;
    }

    // Move up the search path for both nodes in tandem
    while (node0 != node1) {
      node1 = node1.parent;
      node0 = node0.parent;
    }

    return node0;
  }

  public static int getDepth(BinaryTree<Integer> node) {
    if (node == null) {
      return 0;
    }

    int depth = 0;
    BinaryTree<Integer> current = node;
    while (current.parent != null) {
      current = current.parent;
      depth++;
    }

    return depth;
  }
  @EpiTest(testDataFile = "lowest_common_ancestor.tsv")
  public static int lcaWrapper(TimedExecutor executor, BinaryTree<Integer> tree,
                               Integer key0, Integer key1) throws Exception {
    BinaryTree<Integer> node0 = BinaryTreeUtils.mustFindNode(tree, key0);
    BinaryTree<Integer> node1 = BinaryTreeUtils.mustFindNode(tree, key1);

    BinaryTree<Integer> result = executor.run(() -> lca(node0, node1));

    if (result == null) {
      throw new TestFailure("Result can not be null");
    }
    return result.data;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LowestCommonAncestorWithParent.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
