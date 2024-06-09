package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class ReverseSublist {
  @EpiTest(testDataFile = "reverse_sublist.tsv")

  public static ListNode<Integer> reverseSublist(ListNode<Integer> L, int start,
                                                 int finish) {
   ListNode<Integer> dummyHead = new ListNode<>(0, L);
   ListNode<Integer> sublistHead = dummyHead;

   int k = 1;
   while (k++ < start) {
     sublistHead = sublistHead.next;
   }

   ListNode<Integer> sublistIter = sublistHead.next;
   while (start++ < finish) {
     ListNode<Integer> temp = sublistIter.next;
     sublistIter.next = temp.next;
     temp.next = sublistHead.next;
     sublistHead.next = temp;
   }

   return dummyHead.next;

//    THis approach is slightly faster than the second approach, as it reverts nodes in one single pass, but it fails the
//    requirements as I allocate new nodes in line 28
//    ListNode<Integer> left = L;
//    ListNode<Integer> current = L;
//    ListNode<Integer> reversed = new ListNode<>(null, null);
//    ListNode<Integer> right = reversed;
//
//    int i = 1;
//    while (i <= finish) {
//      if (i == start) {
//        left = current;
//      }
//
//      if (i >= start) {
//        reversed.data = current.data;
//        reversed = new ListNode<>(null, reversed);
//      }
//
//      if (i == finish) {
//        left.data = reversed.next.data;
//        right.next = current.next;
//        left.next = reversed.next.next;
//      }
//
//      current = current.next;
//      i++;
//    }
//
//    return L;

//  This approach requires two passes (O(N) space and time complexities)
//    ListNode<Integer> left = L;
//    ListNode<Integer> current = L;
//    Stack<Integer> stack = new Stack<>();
//
//    int i = 1;
//    while (i <= finish) {
//      if (i == start) {
//        left = current;
//      }
//
//      if (i >= start) {
//        stack.add(current.data);
//      }
//
//      if (i == finish) {
//        while (!stack.empty()) {
//          left.data = stack.pop();
//          left = left.next;
//        }
//      }
//
//      current = current.next;
//      i++;
//    }
//
//    return L;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseSublist.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
