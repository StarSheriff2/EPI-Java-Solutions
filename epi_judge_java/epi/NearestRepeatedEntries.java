package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NearestRepeatedEntries {
  @EpiTest(testDataFile = "nearest_repeated_entries.tsv")

  public static int findNearestRepetition(List<String> paragraph) {
//    Time O(N); Space O(d) -> number of distinct entries in the array
    int closestPairDistance = -1;
    Map<String, Integer> stringIndexOccurrence = new HashMap<>();

    for (int i = 0; i < paragraph.size(); i++) {
      String key = paragraph.get(i);
      if (stringIndexOccurrence.containsKey(key)) {
        int distance = i - stringIndexOccurrence.get(key);

        if (distance < closestPairDistance || closestPairDistance < 0) {
          closestPairDistance = distance;
        }
      }

      stringIndexOccurrence.put(key, i);
    }

    return closestPairDistance;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NearestRepeatedEntries.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
