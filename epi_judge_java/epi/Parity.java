package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Parity {
  @EpiTest(testDataFile = "parity.tsv")

//  Brute force approach O(N)
//  public static short parity(long x) {
//    short result = 0;
//    while (x != 0) {
//      result ^= (x & 1);
//      x >>>= 1;
//    }
//    return result;
//  }

//  Remove LSB approach O(K)
//  public static short parity(long x) {
//    short result = 0;
//    while (x != 0) {
//      result ^= 1;
//      x = x&(x - 1);
//    }
//    return result;
//  }

// Lookup table with caching approach
// Investigate what final means
// Average running time:   <1 us
// Median running time:    <1 us
  public static short parity(long x) {
    final int MASK_SIZE = 16;
    final int BIT_MASK = 0xFFFF;

    return (short) (


            findParity((BIT_MASK & (x >>> (MASK_SIZE * 3)))) ^
                  findParity((BIT_MASK & (x >>> (MASK_SIZE * 2)))) ^
                    findParity((BIT_MASK & (x >>> MASK_SIZE))) ^
                    findParity((BIT_MASK & x))

// Unsigned Right Shift (>>>):
// The >>> operator is used for unsigned right shift.
// It also shifts the bits to the right,
// but it fills the leftmost positions with zeros,
// regardless of the sign of the original value.
//      computeOrGetParity((int) (BIT_MASK & (x >>> (MASK_SIZE * 3)))) ^
//      computeOrGetParity((int) (BIT_MASK & (x >>> (MASK_SIZE * 2)))) ^
//      computeOrGetParity((int) (BIT_MASK & (x >>> MASK_SIZE))) ^
//      computeOrGetParity((int) (BIT_MASK & x))
    );
  }

  private static final int PRECOMPUTED_PARITY_SIZE = 65536;

  static Map<Long, Short> parityLookupT = new HashMap<>(PRECOMPUTED_PARITY_SIZE);

  private static short findParity(long l) {
    if (parityLookupT.containsKey(l)) {
      return parityLookupT.get(l);
    } else {
      short parity = computeParity(l);
      parityLookupT.put(l, parity);
      return parity;
    }
  }

//  private static final short[] precomputedParity = new short[PRECOMPUTED_PARITY_SIZE];
//
//  static {
//    Arrays.fill(precomputedParity, (short) -1);
//  }
//
//  private static short computeOrGetParity(int index) {
//    if (precomputedParity[index] != -1) {
//      return (precomputedParity[index]);
//    } else {
//      short parity = computeParity(index);
//      precomputedParity[index] = parity;
//      return parity;
//    }
//  }
//

//  /  Naive approach of computing the parity of a number of any size: O(N)
  //  private static short computeParity(long l) {
//    short result = 0;
//    while (l != 0) {
//      result ^= 1;
//      l &= (l - 1);
//    }
//
//    return result;
//  }

//  A 16 bit number is computed in basically 4 steps
  private static short computeParity(long x) {
    x ^= x >>> 8;
    x ^= x >>> 4;
    x ^= x >>> 2;
    x ^= x >>> 1;
    return (short)(x & 0x1);
  }

//  //  variations
//  static long propagateRightMostSetbit(long x) {
//      return x | (x - 1);
//  }
//
//  propagateRightMostSetbit(2) // 01010000

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "Parity.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
