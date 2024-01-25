package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
//import java.util.function.Supplier;

public class PrimitiveMultiply {
  @EpiTest(testDataFile = "primitive_multiply.tsv")
  public static long multiply(long x, long y) {
  // Brute-force approach

    // These args break the program. Stack overflow
//      final long xa = 57536L;
//      final long ya = 2187L;
//    expected: 125831232
//    expected: 125831232
//    result:   125831232
//    final long xa = 2L;
//    final long ya = 3L;
    //    expected: 252
    //              2068819225
//    result:   65878

    if (x == 1 && y == 1) {
      return x;
    } else if (x == 1) {
      return y;
    } else if (y == 1) {
      return x;
    }

    product = 0;

//    There's a stack overflow issue caused by the recursive calls

//    customLoop(0, (int i) -> ((x >> i) == 0), (int i) -> shift_and_add(i, x, y));

    forLoop(1, 0, (int i) -> ((x >> i) != 0), (int i) -> shift_and_add(i, x, y));

//    long result = product;
//    product = 0;

    return product;
  }

  public static boolean carry = false;
  static long sum = 0;
  static long product = 0;

  @FunctionalInterface
  interface LoopActionInterface {
    void get(int index);
  }

  @FunctionalInterface
  interface LoopConditionInterface {
    boolean get(int index);
  }

  private static void shift_and_add(int index, long x, long y) {
    // reset sum and carry for current operation of x and y
    sum = 0;
    carry = false;

    long multiplier = (x >> index) & 1;

    if (multiplier == 1) {
      long subproduct = y << index;

      if (product != 0 || index > 0) {
        // add all bits in both integers through a loop
//        customLoop(0,
//                    (int i) -> ((product >> i) == 0 && (subproduct >> i) == 0 && !carry),
//                    (int i) -> bitSum(i, product, subproduct));
        forLoop(1,
                0,
                (int i) -> (((product >> i) != 0 || (subproduct >> i) != 0) || carry),
                (int i) -> bitSum(i, product, subproduct));

        // Update product to reflect last sum operation
        product = sum;
      } else {
        // We equal the current product to y if there is no product yet
        // and we are multiplying by the LSB of the multiplier
        product = y;
      }
    }
  }

  private static void bitSum(int index, long x, long y) {
    x >>= index;
    y >>= index;

    long maskedX = x & 1;
    long maskedY = y & 1;
    long bitSum = maskedX ^ maskedY;

    //  Let's check if addition creates a carry
    if (bitSum == 0) {
      // Let's check if bitSum generates a carry
      boolean isSetBit = maskedX == 1;

      // If it's a set bit, and we have a carry,
      // we use the carry and create a new one.
      if (isSetBit && carry) {
        bitSum = 1;
      // If it's a set bit, and there's no carry, we create a carry
      }  else if (isSetBit) {
        carry = true;
      // If it's not a set bit, and there's a carry,
      // we use it in the current bit place
      } else if (carry) {
        carry = false;
        bitSum = 1;
      }
    } else {
      // If there is a carry, we add it
      // to the 1 set bit, and effectively
      // generating a new carry
      if (carry) {
        bitSum = 0;
      }
    }

    sum = (sum | (bitSum << index));
  }

  public static void incrementer(int increment, int i, LoopConditionInterface condition, LoopActionInterface action) {
    i += increment;

    forLoop(increment, i, condition, action);
  }

  public static void forLoop(int increment, int i, LoopConditionInterface condition, LoopActionInterface action) {
    if (condition.get(i)) {
      action.get(i);
      incrementer(increment, i, condition, action);
    }
  }

//  public static void customLoop(int index, CustomLoopConditionInterface condition, CustomLoopActionInterface action) {
//    if (condition.get(index)) {
//      return;
//    }
//
//    action.get(index);
//
//    index++;
//
//    customLoop(index, condition, action);
//  }

  public static void main(String[] args) {
    System.exit(
            GenericTest
                    .runFromAnnotations(args, "PrimitiveMultiply.java",
                            new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
