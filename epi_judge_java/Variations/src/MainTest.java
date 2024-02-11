package Variations.src;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static Variations.src.Main.Color.*;
import static Variations.src.Main.ColorV2.*;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @org.junit.jupiter.api.Test
    void main() {
    }

// Parity Variations
    // 1)
    @Test
    public void testPropagateRightMostSetbit() {
        // Test case 1: Testing with a number that has multiple zeros after the last set bit
        assertEquals(0b01011111L, Main.propagateRightMostSetbit(0b01010000L));

        // Test case 2: Another example
        assertEquals(0b01111111L, Main.propagateRightMostSetbit(0b01000000L));

        // Test case 3: Testing with a number that has only one set bit
        assertEquals(0b00000001L, Main.propagateRightMostSetbit(0b00000001L));

        // Add more test cases as needed
    }

    // 2)
    @Test
    public void modPowerOfTwo() {
        //  power fits < 1 time in x
        assertEquals(1, Main.modPowerOfTwo(183, 16));
        assertEquals(1, Main.modPowerOfTwo(5, 2));

        //  power fits > 1 time in x
    }

    @Test
    public void isPowerOfTwo() {
        assertFalse(Main.isPowerOfTwo(183));
        assertFalse(Main.isPowerOfTwo(48));
        assertTrue(Main.isPowerOfTwo(2));
        assertTrue(Main.isPowerOfTwo(4));
        assertTrue(Main.isPowerOfTwo(256));
        assertTrue(Main.isPowerOfTwo(16));
        assertTrue(Main.isPowerOfTwo(32));
        assertTrue(Main.isPowerOfTwo(128));

        // edge cases
        assertFalse(Main.isPowerOfTwo(0));
        assertFalse(Main.isPowerOfTwo(1));
        assertFalse(Main.isPowerOfTwo(-1));
        assertFalse(Main.isPowerOfTwo(-4));
    }

    ///*    <================== Chapter 6: Arrays ==================>   *//

    ///*** 5.1. Variations Dutch National Flag Partitioning ***///

    // a. Reorder same keys together

    @Test
    public void dutchFlagPartitionSameKeysTogether() {
        // Case 1
        List<Main.Color> arraySource1 = Arrays.asList(RED, BLUE, BLUE, WHITE, RED, BLUE, WHITE);
        Main.dutchFlagPartitionSameKeysTogether(1, arraySource1);
        List<Main.Color> expectedPartition1 = Arrays.asList(RED, RED, WHITE, WHITE, BLUE, BLUE, BLUE);
        assertIterableEquals(expectedPartition1, arraySource1);
        // Case 2
        List<Main.Color> arraySource2 = Arrays.asList(RED, BLUE, BLUE, WHITE, RED, BLUE, WHITE, RED, RED, RED, WHITE);
        Main.dutchFlagPartitionSameKeysTogether(0, arraySource2);
        List<Main.Color> expectedPartition2 = Arrays.asList(RED, RED, RED, RED, RED, WHITE, WHITE, WHITE, BLUE, BLUE, BLUE);
        assertIterableEquals(expectedPartition2, arraySource2);

        // Case 3
        List<Main.Color> arraySource3 = Arrays.asList(BLUE, BLUE, WHITE, RED, BLUE, WHITE, RED, RED, RED, WHITE, RED);
        Main.dutchFlagPartitionSameKeysTogether(2, arraySource3);
        List<Main.Color> expectedPartition3 = Arrays.asList(RED, RED, RED, RED, RED, WHITE, WHITE, WHITE, BLUE, BLUE, BLUE);
        assertIterableEquals(expectedPartition3, arraySource3);
    }

    // b. Reorder same 4 keys together
    @Test
    public void dutchFlagPartitionSameKeysTogether4Values() {
        // Case 1
        List<Main.ColorV2> arraySource1 = Arrays.asList(YELLOW, GREEN, GREEN, BLACK, BROWN, BROWN, BROWN, YELLOW, YELLOW, BROWN, BLACK, GREEN, GREEN);
        Main.dutchFlagPartitionSameKeysTogether4Values(arraySource1);
        List<Main.ColorV2> expectedPartition1 = Arrays.asList(BROWN, BROWN, BROWN, BROWN, BLACK, BLACK, YELLOW, YELLOW, YELLOW, GREEN, GREEN, GREEN, GREEN);
        assertIterableEquals(expectedPartition1, arraySource1);
    }

    ///*** 5.5. Variations Delete Duplicate ***///
    // a. Delete key
    @Test
    public void deleteDuplicatesForKey() {
        // Case 1.1
        List<Integer> arraySource1 = Arrays.asList(2, 10, 54, 3, 5, 10, 15, 10, 1, 453);
        List<Integer> expectedResult1 = Arrays.asList(2, 54, 3, 5, 15, 1, 453, 10, 1, 453);
        assertEquals(7, Main.deleteDuplicatesForKey(arraySource1, 10));
        assertIterableEquals(expectedResult1, arraySource1);

        // Case 1.2
        List<Integer> arraySource2 = Arrays.asList(4, 4, 4, 4, 4, 4);
        List<Integer> expectedResult2 = List.of(4, 4, 4, 4, 4, 4);
        assertEquals(0, Main.deleteDuplicatesForKey(arraySource2, 4));
        assertIterableEquals(expectedResult2, arraySource2);
    }

    // b. X should appear min(2, m) times if X appears m times in A
    @Test
    public void deleteDuplicatesToMinTimes() {
        // Case 1.1
        List<Integer> arraySource1 =    Arrays.asList(2, 4, 8, 10, 10, 10, 10, 10, 15, 20, 21, 22, 22, 22, 23, 30, 40, 41, 41, 50, 64);
        List<Integer> expectedResult1 = Arrays.asList(2, 4, 8, 10, 10, 15, 20, 21, 22, 22, 22, 23, 30, 40, 41, 41, 50, 64, 41, 50, 64);
        assertEquals(18, Main.deleteDuplicatesToMinTimes(arraySource1, 5));
        assertIterableEquals(expectedResult1, arraySource1);

        // Case 1.2
        List<Integer> arraySource2 =    Arrays.asList(2, 4, 8, 10, 10, 10, 10, 10, 15, 16, 16, 16, 16, 16, 20, 21, 22, 22, 22, 23, 30, 40, 41, 41, 50, 64);
        List<Integer> expectedResult2 = Arrays.asList(2, 4, 8, 10, 10, 15, 16, 16, 20, 21, 22, 22, 22, 23, 30, 40, 41, 41, 50, 64, 30, 40, 41, 41, 50, 64);
        assertEquals(20, Main.deleteDuplicatesToMinTimes(arraySource2, 5));
        assertIterableEquals(expectedResult2, arraySource2);

        // Case 1.3
        List<Integer> arraySource3 =    Arrays.asList(2, 4, 8, 10, 10, 10, 10, 10, 15, 16, 16, 16, 16, 16, 20, 21, 22, 22, 22, 23, 30, 40, 41, 41, 50, 64);
        List<Integer> expectedResult3 = Arrays.asList(2, 4, 8, 10, 10, 10, 10, 10, 15, 16, 16, 16, 16, 16, 20, 21, 22, 22, 22, 23, 30, 40, 41, 41, 50, 64);
        assertEquals(26, Main.deleteDuplicatesToMinTimes(arraySource3, 1));
        assertIterableEquals(expectedResult3, arraySource3);
    }
}