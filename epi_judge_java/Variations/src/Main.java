package Variations.src;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    // 1. Variations Parity
    // a. Propagate RightMost Setbit
//    x - 1 effectively sets all bits to the right
//    of the LSB to 1. By bitwaise ORing it with x,
//    we can get the desired result
    static long propagateRightMostSetbit(long x) {
        return x | (x - 1);
    }

    // b. x mod power of 2
    static int modPowerOfTwo(long x, int powerOfTwo) {
//        since all powers of two have one single set bit,
//        we can assume that by performing a bitwise AND of
//        powerOfTwo - 1 with any number, will show any reminder bits.
//        If the powerOfTwo fits evenly x times in x, then there should
//        be no reminder after this operation
        return (int) (x & (powerOfTwo - 1));
    }

    static boolean isPowerOfTwo(long x) {
        if (x == 0 || x == 1) return (boolean) false;

//        the logic here is, if we do x&(x - 1), we
//        effectively remove the lowest set bit.
//        Since all powers of two in binary consist of
//        just one set bit, by performing the LSB removal of x
//        and checking its value, we can know if its a power of two
        return (boolean) ((x&(x - 1)) == 0);
    }

    /*** 1. Variations Dutch National Flag Partitioning ***///

    // a. Reorder same keys together
    public enum Color { RED, WHITE, BLUE }
    static void dutchFlagPartitionSameKeysTogether(List<Color> A) {
        System.out.println("Before Partition: " + Arrays.toString(A.toArray()));
//        Color pivotColor = A.get(pivotIndex);
//        int colorOrdinal = pivotColor.ordinal();

        int bottom = 0, middle = 0, top = A.size();

        while (middle < top) {
            if (A.get(middle).ordinal() == Color.RED.ordinal()) {
                Collections.swap(A, bottom++, middle++);
            } else if (A.get(middle).ordinal() == Color.WHITE.ordinal()) {
                ++middle;
            } else {
                Collections.swap(A, middle, --top);
            }
        }

        System.out.println("Partioned: " + Arrays.toString(A.toArray()));
    }
}