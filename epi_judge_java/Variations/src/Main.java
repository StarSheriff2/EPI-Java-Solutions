package Variations.src;

import epi.DutchNationalFlag;

import java.awt.*;
import java.util.ArrayList;
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

//        Naive apprach of O(n) time complexity
//        int writeIndex = 0;
//
//        for (int i = 0; i < A.size(); i++) {
//            if (!A.get(i).equals(key)) {
//                A.set(writeIndex++, A.get(i));
//            }
//        }
//
//        System.out.println("A no Dups: " + Arrays.toString(A.toArray()));
//        return writeIndex;

        int writeIndex = 0;
        int lower = 0;
        int upper = A.size() - 1;
        List<Integer> searchResult = binarySearch(lower, upper, A, key);
        int found = searchResult.get(1);
        int lowerBound;
        int upperBound;

        if (found >= 0) {
            lowerBound = found - 1;
            if (A.get(lowerBound) == key) {
                lower = searchResult.get(0) + 1;

                while (lowerBound >= 0 && A.get(lowerBound) == key) {
                    lowerBound = binarySearch(lower, lowerBound, A, key).get(1);
                    lowerBound--;
                }
            }

            upperBound = found + 1;
            if (A.get(upperBound) == key) {
                upper = searchResult.get(2) - 1;

                while (upperBound < A.size() && A.get(upperBound) == key) {
                    upperBound = binarySearch(upperBound, upper, A, key).get(2);
                    upperBound++;
                }
            }

            writeIndex = lowerBound;
            for (int i = upperBound; i < A.size(); i++) {
                A.set(++writeIndex, A.get(i));
            }
        }

        System.out.println("A no Dups: " + Arrays.toString(A.toArray()));
        System.out.println("Found: " + found);
        return ++writeIndex;
    }

    // b. X should appear min(2, m) times if X appears m times in A
    public static int deleteDuplicatesToMinTimes(List<Integer> A, int m) {
        System.out.println("\nCurrent A: " + Arrays.toString(A.toArray()));
        if (A.isEmpty()) {
            return 0;
        }

//      Initiate index pointers for currently written index, first index position of a duplicate
//        and counter for dupicate appearances
        int writeIndex = 0, duplicateIndex = 0, xCount = 1;

//        2, 4, 8, 10, 10, 10, 10, 10, 15, 16, 16, 16, 16, 16, 16, 20, 21, 22, 22, 22, 23, 30, 40, 41, 41, 50, 64
        for (int i = 1; i < A.size(); i++) {
//            Check if A[i] is differente than prev element
            if (!A.get(i).equals(A.get(i - 1))) {
//                If not equal check number of appearances of element
//                If element appears m times, move writeIndex back to the index
//                after the initial position of the first appearance of the duplicate + 2
                if (xCount == m && m != 1) {
                    writeIndex = duplicateIndex+=2;
                } else {
//                  If not euqal to m, update index of duplicateIndex to that of ++writeIndex
                    duplicateIndex = ++writeIndex;
                }
//                Write incoming integer into the next writeIndex position
//                and restart counter
                A.set(writeIndex, A.get(i));
                xCount = 1;
            } else {
                xCount++;
                A.set(++writeIndex, A.get(i));
            }
        }

        System.out.println("A with min(2, m) if x appears m times: " + Arrays.toString(A.toArray()));
        return ++writeIndex;
    }

    ///*** 5.18. Variations MatricInSpiralOrder ***///
    // a. Given d, write a program to generatea d x d 2D array
    public static List<List<Integer>> generateSpiralingSquareMatrix(int d) {
        List<List<Integer>> squareMatrix = new ArrayList<>();
        for (int i = 0; i < d; i++) {
            squareMatrix.add(new ArrayList<>(Collections.nCopies(d, 0)));
        }

        int nextInt = 1;

        for (int offset = 0; offset < Math.ceil(d * 0.5); ++offset) {
            nextInt = clockwiseArrayPass(offset, nextInt, squareMatrix);
        }

        return squareMatrix;
    }

    private static int clockwiseArrayPass(int offset, int nextInt, List<List<Integer>> squareMatrix) {
        int len = squareMatrix.size() - 1 - offset;

//        Here we are at the center of the squareMatrix
        if (offset == len) {
            squareMatrix.get(offset).set(offset, nextInt);
            return nextInt;
        }

        // Right move
        for (int y = offset; y < len; ++y) {
            squareMatrix.get(offset).set(y, nextInt++);
        }

        // Down move
        for (int x = offset; x < len; ++x) {
            squareMatrix.get(x).set(len, nextInt++);
        }

        // Back move
        for (int y = len; y > offset; --y) {
            squareMatrix.get(len).set(y, nextInt++);
        }

        // Up move
        for (int x = len; x > offset; --x) {
            squareMatrix.get(x).set(offset, nextInt++);
        }

        return nextInt;
    }

    // b. Given a sequence of P, write a program to generate
    //    a 2d array A whose spiral order is P
    public static List<List<Integer>> generateSquareMatrixFromSequence(List<Integer> P) {
        int sideLen = (int) Math.sqrt(P.size());

        List<List<Integer>> squareMatrix = new ArrayList<>();
        for (int i = 0; i < sideLen; i++) {
            squareMatrix.add(new ArrayList<>(Collections.nCopies(sideLen, 0)));
        }

        final int[][] SHIFT = { {0, 1}, { 1, 0 }, { 0, -1 }, { -1, 0 }};
        int dir = 0, x = 0, y = 0;

        for (Integer integer : P) {
            squareMatrix.get(x).set(y, integer);
            int nextX = x + (SHIFT[dir][0]), nextY = y + (SHIFT[dir][1]);

            if (nextX == sideLen || nextY == sideLen || nextX < 0 || nextY < 0 ||
                    squareMatrix.get(nextX).get(nextY) != 0) {
                dir = (dir + 1) % 4;
                nextX = x + (SHIFT[dir][0]);
                nextY = y + (SHIFT[dir][1]);
            }

            x = nextX;
            y = nextY;
        }

        return squareMatrix;
    }

    // c. Variant: Enumerate the first n pairs of integers (a, b) in spiral order,
    //    starting from (0,0) followed by (1,0). For example, if n = 10, your output should be
    //    (0,0), (1, 0), (1, - 1), (0, - 1), (-1, - 1), (-1, 0), (- 1, 1), (0,1), (1, 1), (2, 1).
    public static Point[] spiralPairs(int n) {
        Point[] pairs = new Point[n];
        final Integer[][] SHIFT = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int dir = 0;
//        The initial boundary is 1
        int boundary = 1;
        int x = 0;
        int y = 0;

        for(int i = 0; i < n; i++) {
//            we compute a and b by subtracting the conversionOffset from x and y, and
//            in the case of y, the y axis is the inversion of the value of y, so we multiply
//            it by -1
            pairs[i] = new Point(x, y);
            int nextX = x + SHIFT[dir][0], nextY = y + (-1 * SHIFT[dir][1]);
            if (nextX > boundary || nextY > boundary || nextX < (-boundary) || nextY < (-boundary)) {
                dir = (dir + 1) % 4;
//                Everytime we do a full circle, we need to expand our
//                boundary
                if (dir == 0) {
                    ++boundary;
                }

                nextX = x + SHIFT[dir][0];
                nextY = y + (-1 * SHIFT[dir][1]);
            }

            x = nextX;
            y = nextY;
        }

        return pairs;
    }

    //    Auxiliary methods
    private static List<Integer> binarySearch(int lower, int upper, List<Integer> arr, int find) {
        int mid;
        int found = -1;

        while (lower <= upper) {
            mid = (lower + upper)/2;

            if (arr.get(mid) == find) {
                found = mid;
                break;
            }
            if (arr.get(mid) < find) {
                upper = mid - 1;
            } else {
                lower = mid + 1;
            }
        }

        return new ArrayList<>(Arrays.asList(lower, found, upper));
    }
}

