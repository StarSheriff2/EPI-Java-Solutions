package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class SearchFirstKey {
  @EpiTest(testDataFile = "search_first_key.tsv")

  public static int searchFirstOfK(List<Integer> A, int k) {
//    int firstOccurrence = -1;
//    int l = 0, u = A.size() - 1;
//
//    while (l <= u) {
//      int m = l + ((u - l)/2);
//
//      if (A.get(m) < k) {
//        l = m + 1;
//      } else if (A.get(m) > k) {
//        u = m - 1;
//      } else{
//        firstOccurrence = m;
//
//        u = m - 1;
//      }
//    }
//
//    return firstOccurrence;

//    Variant 1
//    A = Arrays.asList(-14, -10, 2, 108, 108, 243, 285, 285, 285, 401);
//    k = 285;
//    int result = -1;
//    int l = 0, u = A.size() - 1;
//
//    while (l <= u) {
//      int m = l + ((u - l)/2);
//
//      if (A.get(m) <= k) {
//        l = m + 1;
//      } else {
//        result = m;
//        u = m - 1;
//      }
//    }
//
//    return result;

//    variant 3
//    The way to solve this is do 2 passes. One looking for the first occurrence of k; and a second one looking for the
//    last occurrence; however, the search for the last occurrence can be shortened by
//    starting at the max index occurrence of the first pass
//    If k is not found, just return (-1, -1)

//    Variant 4
    List<String> B = Arrays.asList("apple", "banana", "carrot", "date", "fig", "grape");
    String p = "cat";
    boolean result = Collections.binarySearch(B, p, prefixComparator) >= 0;

    return 0;
  }

  private static final Comparator<String> prefixComparator = (str, prefixToCompare) -> {
    int len = prefixToCompare.length();
    String substring = str.length() < len ? str : str.substring(0, len);
    return substring.compareTo(prefixToCompare);
  };

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchFirstKey.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
