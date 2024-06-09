package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.List;

public class IsListCyclic {

  public static ListNode<Integer> hasCycle(ListNode<Integer> head) {
    ListNode<Integer> fast = head, slow = head;

    while (fast.next != null && fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;

      if (slow == fast) {
//        int cycleLen = 0;
//        do {
//          fast = fast.next;
//          cycleLen++;
//        } while (slow != fast);

//        ListNode<Integer> advancedCycleIter = head;
//        while (cycleLen-- > 0) {
//          advancedCycleIter = advancedCycleIter.next;
//        }

//        ListNode<Integer> iter = head;
//        while (iter != advancedCycleIter) {
//          advancedCycleIter = advancedCycleIter.next;
//          iter = iter.next;
//        }

        slow = head;
        while (slow != fast) {
          slow = slow.next;
          fast = fast.next;
        }

        return slow;

//        return iter;
      }
    }

    return null;

//    TC is O(n) wcs; SC is O(n) because I make a copy of the original
//    list

//    ListNode<Integer> paintedList = new ListNode<>(0, head);
//    ListNode<Integer> originalCurrent = head;
//    ListNode<Integer> paintedListCurr = paintedList.next;
//
//    while (paintedListCurr != null && paintedListCurr.data != null) {
//      originalCurrent = originalCurrent.next;
//      paintedListCurr.data = null;
//      paintedListCurr = paintedListCurr.next;
//    }
//
//    if (paintedListCurr != null) {
//      return originalCurrent;
//    }
//
//    return null;
  }
  @EpiTest(testDataFile = "is_list_cyclic.tsv")
  public static void HasCycleWrapper(TimedExecutor executor,
                                     ListNode<Integer> head, int cycleIdx)
      throws Exception {
    int cycleLength = 0;
    if (cycleIdx != -1) {
      if (head == null) {
        throw new RuntimeException("Can't cycle empty list");
      }
      ListNode<Integer> cycleStart = null, cursor = head;
      while (cursor.next != null) {
        if (cursor.data == cycleIdx) {
          cycleStart = cursor;
        }
        cursor = cursor.next;
        if (cycleStart != null) {
          cycleLength++;
        }
      }
      if (cursor.data == cycleIdx) {
        cycleStart = cursor;
      }
      if (cycleStart == null) {
        throw new RuntimeException("Can't find a cycle start");
      }
      cursor.next = cycleStart;
      cycleLength++;
    }

    ListNode<Integer> result = executor.run(() -> hasCycle(head));

    if (cycleIdx == -1) {
      if (result != null) {
        throw new TestFailure("Found a non-existing cycle");
      }
    } else {
      if (result == null) {
        throw new TestFailure("Existing cycle was not found");
      }

      ListNode<Integer> cursor = result;
      do {
        cursor = cursor.next;
        cycleLength--;
        if (cursor == null || cycleLength < 0) {
          throw new TestFailure(
              "Returned node does not belong to the cycle or is not the closest node to the head");
        }
      } while (cursor != result);

      if (cycleLength != 0) {
        throw new TestFailure(
            "Returned node does not belong to the cycle or is not the closest node to the head");
      }
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsListCyclic.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
