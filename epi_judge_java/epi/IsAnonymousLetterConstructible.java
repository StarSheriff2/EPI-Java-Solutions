package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class IsAnonymousLetterConstructible {
  @EpiTest(testDataFile = "is_anonymous_letter_constructible.tsv")

  public static boolean isLetterConstructibleFromMagazine(String letterText,
                                                          String magazineText) {
    // Time O(N) where N is the longest string
    // Space O(N) where N equals letterText
//    int letterLen = letterText.length();
    int magazineTextLen = magazineText.length();
    Map<Character, Long> charOccurrences = letterText.chars()
            .mapToObj((c) -> (char) c)
            .collect(Collectors.groupingBy(
                    Function.identity(),
                    Collectors.counting()));
//    Map<Character, Integer> charOccurrences = new HashMap<>(letterLen);
//
//    for (int i = 0; i < letterLen; i++) {
//      Character k = letterText.charAt(i);
//      charOccurrences.merge(k, 1, (oldValue, newValue) -> ++oldValue);
//    }

    for (int i = 0; i < magazineTextLen; i++) {
      Character k = magazineText.charAt(i);
      if (charOccurrences.containsKey(k)) {
        charOccurrences.put(k, charOccurrences.get(k) - 1);
        if (charOccurrences.remove(k, 0L)) {
          charOccurrences.remove(k);
        }
        if (charOccurrences.isEmpty()) {
          break;
        }
      }
    }

    return charOccurrences.isEmpty();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsAnonymousLetterConstructible.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
