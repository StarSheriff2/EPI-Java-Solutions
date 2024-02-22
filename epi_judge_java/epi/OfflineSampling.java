package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.RandomSequenceChecker;
import epi.test_framework.TimedExecutor;

import java.util.*;

public class OfflineSampling {
  public static void randomSampling(int k, List<Integer> A) {
  //    O(k) time, O(1) space
  //    Average running time:   81 ms
  //    Median running time:    85 ms
    Random rand = new Random();

    for (int i = 0; i < k; i++) {
      Collections.swap(A, i, (rand.nextInt(i, A.size())));
    }

//    or
//    for (int i = 0; i < k; i++) {
//      Collections.swap(A, i, rand.nextInt(A.size() - i) + i);
//    }

//    System.out.println(Arrays.toString(A.toArray()));
//    Naive approach which doesn't totally fulfill the requirements of the question
//    O(N) time complexity in worst case scenario when randNum equals
//    0 and k == A.size(), effectivelu requiring a complete traversal of the array
//    to build the sublist
//    Average running time:  111 ms
//    Median running time:   104 ms
//    Random rand = new Random();
//    int randNum = rand.nextInt(A.size() - (k - 1));
//
//    A.subList(randNum, randNum + k);

//    List<Integer> sublist = new ArrayList<>(A.subList(randNum, randNum + k));
//
//    A.clear();
//    A.addAll(sublist);
  }

  private static boolean randomSamplingRunner(TimedExecutor executor, int k,
                                              List<Integer> A)
      throws Exception {
    List<List<Integer>> results = new ArrayList<>();

    executor.run(() -> {
      for (int i = 0; i < 1000000; ++i) {
        randomSampling(k, A);
        results.add(new ArrayList<>(A.subList(0, k)));
      }
    });

    int totalPossibleOutcomes =
        RandomSequenceChecker.binomialCoefficient(A.size(), k);
    Collections.sort(A);
    List<List<Integer>> combinations = new ArrayList<>();
    for (int i = 0; i < RandomSequenceChecker.binomialCoefficient(A.size(), k);
         ++i) {
      combinations.add(
          RandomSequenceChecker.computeCombinationIdx(A, A.size(), k, i));
    }
    List<Integer> sequence = new ArrayList<>();
    for (List<Integer> result : results) {
      Collections.sort(result);
      sequence.add(combinations.indexOf(result));
    }
    return RandomSequenceChecker.checkSequenceIsUniformlyRandom(
        sequence, totalPossibleOutcomes, 0.01);
  }

  @EpiTest(testDataFile = "offline_sampling.tsv")
  public static void randomSamplingWrapper(TimedExecutor executor, int k,
                                           List<Integer> A) throws Exception {
    RandomSequenceChecker.runFuncWithRetries(
        () -> randomSamplingRunner(executor, k, A));
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "OfflineSampling.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
