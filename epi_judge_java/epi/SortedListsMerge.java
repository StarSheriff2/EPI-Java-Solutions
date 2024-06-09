package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class SortedListsMerge {
  @EpiTest(testDataFile = "sorted_lists_merge.tsv")
  //@include
  public static ListNode<Integer> mergeTwoSortedLists(ListNode<Integer> L1,
                                                      ListNode<Integer> L2) {

    ListNode<Integer> mergedLists = new ListNode<>(0, null);
    ListNode<Integer> currNode = mergedLists;

    while(L1 != null && L2 != null) {
      if (L1.data <= L2.data) {
        currNode.next = L1;
        L1 = L1.next;
      } else {
        currNode.next = L2;
        L2 = L2.next;
      }

      currNode = currNode.next;
    }

    if (L1 != null) {
      currNode.next = L1;
    } else if (L2 != null) {
      currNode.next = L2;
    }

    return mergedLists.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortedListsMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
