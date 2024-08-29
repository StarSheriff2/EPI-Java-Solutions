package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class KthLargestInArray {
  // The numbering starts from one, i.e., if A = [3,1,-1,2] then
  // findKthLargest(1, A) returns 3, findKthLargest(2, A) returns 2,
  // findKthLargest(3, A) returns 1, and findKthLargest(4, A) returns -1.
  @EpiTest(testDataFile = "kth_largest_in_array.tsv")
  public static int findKthLargest(int k, List<Integer> A) {
//    <<<< ------------ Simple approach ------------ >>>>
//    O(N Log n) Time Complexity
//    Collections.sort(A, (a, b) -> b - a );
//    return A.get(k - 1);
//    A = Arrays.asList(3, 8, 9, 6, 1);
//    k = 2;

//    <<<< ------------ Partitioning Approach ------------ >>>>
//    Sorting the list is wasteful as it's not necessary to sort the whole list all the time
//    We can iteratively reduce the size of the search interval through partitioning
//    O(N) Time Complexity
//    int l = 0, r = A.size() - 1;
//    int pivotIdx = -1;
//    Random rand = new Random(0);
////    k = (r / 2) + 2;
//
//    while (pivotIdx != (k - 1)) {
//      pivotIdx = rand.nextInt((r - l) + 1) + l;
//      int partitionIdx = partitionInterval(l, r, pivotIdx, A);
//
//      if (partitionIdx > (k - 1)) {
//        r = partitionIdx - 1;
//      }
//      if (partitionIdx < (k - 1)) {
//        l = partitionIdx + 1;
//      }
//      pivotIdx = partitionIdx;
//    }
//
//    return A.get(pivotIdx);


//    Variant 3 SOlution
    int l = 0, r = A.size() - 1;
    int pivotIdx = -1;
    Random rand = new Random(0);

    while (pivotIdx != (k - 1)) {
      pivotIdx = rand.nextInt((r - l) + 1) + l;
      int partitionIdx = partitionInterval(l, r, pivotIdx, A);

      if (partitionIdx > (k - 1)) {
        r = partitionIdx - 1;
      }
      if (partitionIdx < (k - 1)) {
        l = partitionIdx + 1;
      }
      pivotIdx = partitionIdx;
    }

    return A.get(pivotIdx);

//    Variant 1:
//    pg. 172
//    FInd the Median of an array
//    SOlution: It's essentially the same imlementation for findKthLargest, with the
//    difference that we now look for k = n/2 if n is odd
//    If the list is even, we need to look for k = (n-1)/2 too, and
//    then the average of n/2 and (n - 1)/2 should be the final answer

//    Variant 2:
//    Find the kth largest element of A in the presence of duplicates
//    Solution: Use the same approach of the base solution, since my partitioning approach
//    places all duplicates together

//    Variant 3:
//    Devise an algorithm that computes where to placethe mailbox so as to minimize the total distance
//    that residents travel to get to the mailbox.
//    Insight: The accumulated sum of all the residents from 0 to k determines the optimal position for the mailbox.
//    Solution: Find K such that the of all building residents to the left of k is greater than the right sum
//    PseudoCode:

//    - calcualte the total sum of all residents in every building
//    - create a new class that returns the leftSum of a partition up to the new pivot, after partitioning
//    - partition as always, but additionally keep track of the leftSum
//    - if leftSum is > (totalSum - leftSum)
//        then you have found k
//      else
//        left = k + 1
//
//    - repeat until leftSum is > (totalSum - leftSum)
  }



  private static int partitionInterval(int l, int r, int pivotIdx, List<Integer> A) {
    int m = l;
    int pivotValue = A.get(pivotIdx);

    while (m <= r) {
      if (A.get(m).equals(pivotValue)) {
        m++;
      } else if (A.get(m) > pivotValue) {
        Collections.swap(A, l++, m++);
      } else {
        Collections.swap(A, m, r--);
      }
    }

    return l;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "KthLargestInArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
