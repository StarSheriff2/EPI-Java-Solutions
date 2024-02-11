package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class PrimeSieve {
  @EpiTest(testDataFile = "prime_sieve.tsv")
  // Given n, return all primes up to and including n.
  public static List<Integer> generatePrimes(int n) {
//    approach 3: Book solution using "seiving"
//    In this approach we take advantage of the array's
//    index as a way to store the prime numbers
//      Average running time:    1 ms
//      Median running time:    12 us
      List<Integer> primes = new ArrayList<>();
      List<Boolean> isPrime = new ArrayList<>(Collections.nCopies(n + 1, true));
      isPrime.set(0, false);
      isPrime.set(1, false);

      for (int i = 2; i <= n; i++) {
        if (isPrime.get(i)) {
            primes.add(i);

            int j = 2;
            while ((i * j) <= n) {
                isPrime.set((i * j), false);
                j++;
            }
        }
      }

      return primes;
//    approach 2
//    This approach is faster than approach 1, as it divides num by range of i to √i
//    Average running time:    3 ms
//    Median running time:     9 us
//    List<Integer> primeList = new ArrayList<>();
//    int num = 2;
//
//    while (num <= n) {
//      if (num%2 > 0 || num == 2) {
//        boolean isPrime = true;
//
//        for (Integer integer : primeList) {
//          if ((integer * integer) > num ){
//            break;
//          }
//
//          if ((num % integer) == 0) {
//              isPrime = false;
//              break;
//          }
//        }
//
//        if (isPrime) {
//          primeList.add(num);
//        }
//      }
//
//      num++;
//    }
//    return primeList;

//    approach 1
//    List<Integer> primeList = new ArrayList<>();
//    int num = 2;
//
//    while (num <= n) {
//      if (num%2 > 0 || num == 2) {
//        boolean isPrime = true;
//
//        for (Integer integer : primeList) {
//            if ((num % integer) == 0) {
//                isPrime = false;
//                break;
//            }
//        }
//
//        if (isPrime) {
//          primeList.add(num);
//        }
//      }
//
//      num++;
//    }
//    return primeList;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PrimeSieve.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
