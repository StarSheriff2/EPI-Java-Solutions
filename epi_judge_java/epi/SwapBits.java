package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.Arrays;

public class SwapBits {
  @EpiTest(testDataFile = "swap_bits.tsv")
//  Array approach
//  public static long swapBits(long x, int i, int j) {
//    String binaryString = Long.toBinaryString(x);
//    char[] partialBinaryArr = binaryString.toCharArray();
//    char[] leadingZeros = new char[64 - partialBinaryArr.length];
//    Arrays.fill(leadingZeros, '0');
//    char[] binaryArr = new char[64];
//
//    System.arraycopy(leadingZeros, 0, binaryArr, 0, leadingZeros.length);
//    System.arraycopy(partialBinaryArr, 0, binaryArr, leadingZeros.length, partialBinaryArr.length);
//
//    int binaryLastIndex = binaryArr.length - 1;
//
//    if (binaryArr[binaryLastIndex - i] ==  binaryArr[binaryLastIndex - j]) {
//      return x;
//    }
//
//    char tempI = binaryArr[binaryLastIndex - i];
//    binaryArr[binaryLastIndex - i] =  binaryArr[binaryLastIndex - j];
//    binaryArr[binaryLastIndex - j] = tempI;
//
//    String swappedBinaryString = String.copyValueOf(binaryArr);
//    long swappedBinary = Long.parseLong(swappedBinaryString, 2);
//
//    return swappedBinary;
//  }

//  My best approach using bitwise operations
//  public static long swapBits(long x, int i, int j) {
//    long iBit = (x >>> i) & 1;
//    long jBit = (x >>> j) & 1;
//    long swappedBits = x;
//
//    if (iBit != jBit && jBit == 1) {
//      long swappedI = (jBit << i) ^ x;
//      swappedBits = (jBit << j) ^ swappedI;
//    }
//    if (iBit != jBit && iBit == 1) {
//      long swappedJ = (iBit << j) ^ x;
//      swappedBits = (iBit << i) ^ swappedJ;
//    }
//
//    return swappedBits;
//  }

//  textbook solution: So elegant!!!!!!! 🫶
  public static long swapBits(long x, int i, int j) {
//    First we extract the bits in the ith and jth positions
//    and check if they are different. If they are we proceed to
//    flip them.
    if (((x >>> i) & 1) != ((x >>> j) & 1)) {
//      First we build a mask by moving both bits to their original position
//      with all the bits on both sides set to zeros;
//      then, by performing an OR bitwise on both, we effectovely
//      create a bitmask with these two bits
      long bitMask = (1L << i) | (1L << j);
//      We finally XOR the bitmask and x
//      Since x ^ 1 = 0 when x = 1, and 1 when x = 0,
//     we effectively flip their values
      x ^= bitMask;
    }

    return x;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SwapBits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
