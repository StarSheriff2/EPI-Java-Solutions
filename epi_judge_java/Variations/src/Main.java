package Variations.src;

import epi.DutchNationalFlag;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    ///*** 1. Variations Parity ***///
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
        if (x == 0 || x == 1) return false;

//        the logic here is, if we do x&(x - 1), we
//        effectively remove the lowest set bit.
//        Since all powers of two in binary consist of
//        just one set bit, by performing the LSB removal of x
//        and checking its value, we can know if its a power of two
        return (x&(x - 1)) == 0;
    }

    ///*    <================== Chapter 6: Arrays ==================>   *//

    ///*** 5.1. Variations Dutch National Flag Partitioning ***///

    // a. Reorder same keys together
    public enum Color { RED, WHITE, BLUE }
    static void dutchFlagPartitionSameKeysTogether(int pivotIndex, List<Color> A) {
        int sortOrdinal = 1;
        System.out.println("\nsortOrdinal: " + sortOrdinal);
        System.out.println("Before Partition: " + Arrays.toString(A.toArray()));

        int bottom = 0, middle = 0, top = A.size();

        while (middle < top) {
            int currentOrdinal = A.get(middle).ordinal();

            if (currentOrdinal < sortOrdinal) {
                Collections.swap(A, bottom++, middle++);
            } else if (currentOrdinal == sortOrdinal) {
                ++middle;
            } else {
                Collections.swap(A, middle, --top);
            }

        }

        System.out.println("Partioned: " + Arrays.toString(A.toArray()));
    }

    // b. Reorder same 4 keys together
    public enum ColorV2 { BROWN, BLACK, YELLOW, GREEN }
    static void dutchFlagPartitionSameKeysTogether4Values(List<ColorV2> A) {
        System.out.println("Before Partition: " + Arrays.toString(A.toArray()));

        int bottom = 0, bottomMiddle = 0, middle = 0, top = A.size();

        while (middle < top) {
            if (A.get(middle).ordinal() == 0) {
                Collections.swap(A, bottom++, middle++);
            } else if (A.get(middle).ordinal() == 1) {
                Collections.swap(A, bottomMiddle++, middle++);
            } else if (A.get(middle).ordinal() == 2) {
                middle++;
            } else {
                Collections.swap(A, middle, --top);
            }
        }

        System.out.println("Partioned: " + Arrays.toString(A.toArray()));
    }

    ///*** 5.5. Variations Delete Duplicate ***///
    // a. Delete key
    public static int deleteDuplicatesForKey(List<Integer> A, int key) {
        System.out.println("\nCurrent A: " + Arrays.toString(A.toArray()));
        if (A.isEmpty()) {
            return 0;
        }

        int j = 0;

        for (int i = 0; i < A.size(); i++) {
            if (!A.get(i).equals(key)) {
                A.set(j++, A.get(i));
            }
        }

        System.out.println("A no Dups: " + Arrays.toString(A.toArray()));
        return j;
    }

    // b. X should appear min(2, m) times if X appears m times in A
    public static int deleteDuplicatesToMinTimes(List<Integer> A, int m) {
        System.out.println("\nCurrent A: " + Arrays.toString(A.toArray()));
        if (A.isEmpty()) {
            return 0;
        }

        int writeIndex = 0;
        int evaluatedIndex = 0;
        int xCount = 1;

//        2, 4, 8, 10, 10, 10, 10, 10, 15, 16, 16, 16, 16, 16, 16, 20, 21, 22, 22, 22, 23, 30, 40, 41, 41, 50, 64
        for (int i = 1; i < A.size(); i++) {
            if (!A.get(i).equals(A.get(evaluatedIndex))) {
                if (xCount == m && m != 1) {
                    int temp = A.get(writeIndex);
                    A.set(++writeIndex, temp);
                    A.set(++writeIndex, A.get(i));
                    evaluatedIndex++;
                    xCount = 1;
                } else {
                    int writes = 1;
                    while (writes < xCount) {
                        A.set((writeIndex + 1), A.get(writeIndex));
                        writes++;
                        writeIndex = writeIndex + 1;
                    }
                    if ((writeIndex + 1) < A.size()) {
                        A.set(++writeIndex, A.get(i));
                        xCount = 1;
                        evaluatedIndex++;
                    }
                }
            } else {
                xCount++;
                evaluatedIndex++;
            }
        }

        System.out.println("A with min(2, m) if x appears m times: " + Arrays.toString(A.toArray()));
        return ++writeIndex;
    }
}

