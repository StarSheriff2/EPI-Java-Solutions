package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Arrays;

public class ReverseBits {
  @EpiTest(testDataFile = "reverse_bits.tsv")
//  Brute force O(n)
//  public static long reverseBits(long x) {
//    long reversedBits = 0;
//
//    short count = 63;
//    while (count != 0) {
//      long lsb = x & 1;
//
//      if (lsb == 1) {
//        reversedBits |= 1;
//      }
//      reversedBits <<= 1;
//      x >>>= 1;
//      count -= 1;
//    }
//
//    return reversedBits;
//  }

//  Lookup table with cache
  public static long reverseBits(long x) {
    long MASK_SIZE = 16;
    long BIT_MASK = 0xFFFF;

//    Split binary into 4 16bit parts and compute reverse value or
//    fetch it from lookup table
    long firstQuarter = (x >>> MASK_SIZE * 3) & BIT_MASK;
    long secondQuarter = (x >>> MASK_SIZE * 2) & BIT_MASK;
    long thirdQuarter = (x >>> MASK_SIZE) & BIT_MASK;
    long fourthQuarter = x & BIT_MASK;
    return (
            (computeOrGetReversedBits((int) (fourthQuarter)) << (MASK_SIZE * 3)) |
            (computeOrGetReversedBits((int) (thirdQuarter)) << (MASK_SIZE * 2)) |
            (computeOrGetReversedBits((int) (secondQuarter)) << (MASK_SIZE)) |
            (computeOrGetReversedBits((int) firstQuarter))
            );
  }

  static final int TABLE_SIZE = 0xFFFF;
  static long[] lookUpTable = new long[TABLE_SIZE];

  static {
    Arrays.fill(lookUpTable, -1);
  }

  public static long computeOrGetReversedBits(int index) {
    if (lookUpTable[index] == -1) {
      lookUpTable[index] = computeReversedBit(index);
    }

    return lookUpTable[index];
  }

  private static long computeReversedBit(long x) {
    short i = 15;
    short j = 0;
    while (i > 7) {
      if (((x >>> i) & 1) != ((x >>> j) & 1)) {
        long bitMask = (1L << i) | (1L << j);
        x ^= bitMask;
      }
        i -= 1;
        j += 1;
      }

    return x;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseBits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
