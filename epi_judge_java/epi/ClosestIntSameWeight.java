package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
//import  problem_helpers.Ls1bIndexLookUpTable;

public class ClosestIntSameWeight {
  static final Ls1bIndexLookUpTable lookupTable = new Ls1bIndexLookUpTable();
  @EpiTest(testDataFile = "closest_int_same_weight.tsv")
  public static long closestIntSameBitCount(long x) {
    // Constants
    final int BIT_MASK = 0xFFFF;
    final short MASK_SIZE = 16;

    // Initialize variables
    short index = 0;
    long shifted = x;

    // Extract masked segment
    long maskedBits = shifted & BIT_MASK;

    // Find the segments that are not all 0s or all 1s
    while (maskedBits == 0 || maskedBits == BIT_MASK) {
        index += MASK_SIZE;
        shifted >>= MASK_SIZE;
        maskedBits = shifted & BIT_MASK;
    }

    if ((shifted & 1) == 1) {

      // Since we need to find the rightmost non-equal 2 consecutive bits,
      // we flip all bits to find the last occurrence of 0
      long flipped = shifted ^ BIT_MASK;

      // Extract masked segment
      flipped = flipped & BIT_MASK;
      index += lookupTable.getValue((int) flipped);

      // If index == 0 OR if index is > 0 AND the bit at index - 1
      // position is 0 we use the lookup table
    } else if (index == 0 || ((x >> (index - 1)) & 1) == 0) {
      shifted &= BIT_MASK;
      index += lookupTable.getValue((int) shifted);
    }

    return swapBits(x, index, (short) (index - 1));
  }

  //  O(N) approach
//  public static long closestIntSameBitCount(long x) {
//
////    In order to find the lowest possible distance between y and x
////    we opt to swap places between the lowest unset bit with a set bit next to
////    it, to swap places.
////    This way the weight is kept the same
//    short i;
//    if ((x & 1) == 0) {
//      i = getLs1bIndex(x);
//    } else {
//      i = getLowestUnsetBit(x);
//    }
//
//    return swapBits(x, i, (short) (i - 1));
//  }

  //  O(N) approach
//  private static short getLs1bIndex(long x) {
//    short ls1bIndex = 0;
//
//    while ((x & 1) == 0) {
//      x >>= 1;
//      ls1bIndex++;
//    }
//
//    return ls1bIndex;
//  }

//  O(N) approach
//  private static short getLowestUnsetBit(long x) {
//    short lubIndex = 0;
//
//    while ((x & 1) == 1) {
//      x >>= 1;
//      lubIndex++;
//    }
//
//    return lubIndex;
//  }

  private static long swapBits(long x, short i, short j) {
    long bitMask = (1L << i) | (1L << j);
    return x ^ bitMask;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ClosestIntSameWeight.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

class Ls1bIndexLookUpTable {
  private final short[] table;
  private final int TABLE_SIZE = 0xFFFF;

  public Ls1bIndexLookUpTable() {
    // Initialize the table with 2^16 entries
    table = new short[TABLE_SIZE];

    initializeTable();
  }

  private void initializeTable() {
    for (int i = 1; i < TABLE_SIZE; i++) {
      short index;
      if ((i & 1) == 0) {
        index = getLs1bIndex(i);
      } else {
        index = getLowestUnsetBit(i);
      }

      table[i] = index;
    }
  }

  // Getter
  public short getValue(int index) {
    // Retrieve a value from the table based on the provided index
    if (index > 0 && index < TABLE_SIZE) {
      return table[index];
    } else {
      throw new IllegalArgumentException("Index out of bounds");
    }
  }

  private static short getLs1bIndex(long x) {
    if (x == 0) {
      return 0;
    }

    short ls1bIndex = 0;

    while ((x & 1) == 0) {
      x >>= 1;
      ls1bIndex++;
    }

    return ls1bIndex;
  }

  private static short getLowestUnsetBit(long x) {
    short lubIndex = 0;

    while ((x & 1) == 1) {
      x >>= 1;
      lubIndex++;
    }

    return lubIndex;
  }
//    public static final short[] lookupTable =  new short[] {0, 1, 1, 2, 2, 1, 1, 3, 3, 1, 1, 2, 2, 1, 1, 4, 4, 1, 1, 2, 2, 1, 1, 3, 3, 1, 1, 2, 2, 1, 1, 5, 5, 1, 1, 2, 2, 1, 1, 3, 3, 1, 1, 2, 2, 1, 1, 4, 4, 1, 1, 2, 2, 1, 1, 3, 3, 1, 1, 2, 2, 1, 1, 6, 6, 1, 1, 2, 2, 1, 1, 3, 3, 1, 1, 2, 2, 1};
}
