package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

//import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
//import java.util.stream.Collectors;

import static java.util.HashMap.newHashMap;

public class IsStringPermutableToPalindrome {
  @EpiTest(testDataFile = "is_string_permutable_to_palindrome.tsv")

  public static boolean canFormPalindrome(String s) {
    int len = s.length();
    Map<Character, Integer> mappedString = newHashMap(len);

    s.chars().forEach(c -> {
      char k = (char) (c + 97 - 'a');
      mappedString.merge(k, 1, (oldValue, newValue) -> oldValue ^ 1);
    });

    int result = mappedString.values().stream().reduce(0, Integer::sum);

    return ((len % 2 == 0 && result == 0) || (len % 2 != 0 && result == 1));
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsStringPermutableToPalindrome.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
