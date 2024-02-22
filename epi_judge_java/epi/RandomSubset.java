package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.RandomSequenceChecker;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.sql.Array;
import java.util.*;

public class RandomSubset {
// This approach uses O(K) size and uses a hashMap for addtitiona space
//Average running time:  157 ms
//  Median running time:   143 ms
  // Returns a random k-sized subset of {0, 1, ..., n - 1}.
  public static List<Integer> randomSubset(int n, int k) {

    if (k == 0) {
      return Collections.emptyList();
    }

    Random random = new Random();


    List<Integer> result = new ArrayList<>();
    Map<Integer,Integer> indexMap = new HashMap<>();


    for (int i = 0; i < k; i++) {
      int randIdx = i + random.nextInt(n - i);
      int selectedIdx = indexMap.getOrDefault(randIdx, randIdx);

      // Swap elements at selectedIdx and i
      indexMap.put(randIdx, indexMap.getOrDefault(i, i));
      indexMap.put(i, selectedIdx);

      result.add(selectedIdx);
    }

    return result;
  }

  //    naive implementation with O(n) space
//    Average running time:  121 ms
//Median running time:   134 ms
//      List<Integer> baseArr = new ArrayList<>(Collections.nCopies(n, -1));
//    List<Integer> result = new ArrayList<>();
//
//
//
//    for (int i = 0; i < k; i++) {
//      if (baseArr.get(i) < 0) {
//        baseArr.set(i, i);
//      }
//
//      int randIdx = random.nextInt(i, n);
//
//      if (baseArr.get(randIdx) < 0) {
//        baseArr.set(randIdx, randIdx);
//      }
//
//      Collections.swap(baseArr, i, randIdx);
////      result.add(baseArr.get(i));
//    }
//
////    System.out.println(Arrays.toString(baseArr.subList(0,k).toArray()));
//    return baseArr.subList(0,k);
//  }
  private static boolean randomSubsetRunner(TimedExecutor executor, int n,
                                            int k) throws Exception {
    List<List<Integer>> results = new ArrayList<>();

    executor.run(() -> {
      for (int i = 0; i < 1000000; ++i) {
        results.add(randomSubset(n, k));
      }
    });

    int totalPossibleOutcomes = RandomSequenceChecker.binomialCoefficient(n, k);
    List<Integer> A = new ArrayList<>(n);
    for (int i = 0; i < n; ++i) {
      A.add(i);
    }
    List<List<Integer>> combinations = new ArrayList<>();
    for (int i = 0; i < RandomSequenceChecker.binomialCoefficient(n, k); ++i) {
      combinations.add(RandomSequenceChecker.computeCombinationIdx(A, n, k, i));
    }
    List<Integer> sequence = new ArrayList<>();
    for (List<Integer> result : results) {
      Collections.sort(result);
      sequence.add(combinations.indexOf(result));
    }
    return RandomSequenceChecker.checkSequenceIsUniformlyRandom(
        sequence, totalPossibleOutcomes, 0.01);
  }

  @EpiTest(testDataFile = "random_subset.tsv")
  public static void randomSubsetWrapper(TimedExecutor executor, int n, int k)
      throws Exception {
    RandomSequenceChecker.runFuncWithRetries(
        () -> randomSubsetRunner(executor, n, k));
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RandomSubset.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
