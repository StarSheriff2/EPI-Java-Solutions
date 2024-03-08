package Variations.src;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
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
//        List<Integer> arraySource1 = Arrays.asList(1, 2, 3, 5, 10, 10, 10, 15, 54, 453);
//        List<Integer> expectedResult1 = Arrays.asList(1, 2, 3, 5, 15, 54, 453, 15, 54, 453);
//        assertEquals(7, Main.deleteDuplicatesForKey(arraySource1, 10));
//        assertIterableEquals(expectedResult1, arraySource1);

//        // Case 1.2
        List<Integer> arraySource2 = Arrays.asList(4, 4, 4, 4, 4, 4);
        List<Integer> expectedResult2 = List.of(4, 4, 4, 4, 4, 4);
        assertEquals(0, Main.deleteDuplicatesForKey(arraySource2, 4));
        assertIterableEquals(expectedResult2, arraySource2);
//
//        // Case 1.3
//        List<Integer> arraySource3 = Arrays.asList(1, 2, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255, 256, 257, 258, 259, 260, 261, 262, 263, 264, 265, 266, 267, 268, 269, 270, 271, 272, 273, 274, 275, 276, 277, 278, 279, 280, 281, 282, 283, 284, 285, 286, 287, 288, 289, 290, 291, 292, 293, 294, 295, 296, 297, 298, 299, 300, 301, 302, 303, 304, 305, 306, 307, 308, 309, 310, 311, 312, 313, 314, 315, 316, 317, 318, 319, 320, 321, 322, 323, 324, 325, 326, 327, 328, 329, 330, 331, 332, 333, 334, 335, 336, 337, 338, 339, 340, 341, 342, 343, 344, 345, 346, 347, 348, 349, 350, 351, 352, 353, 354, 355, 356, 357, 358, 359, 360, 361, 362, 363, 364, 365, 366, 367, 368, 369, 370, 371, 372, 373, 374, 375, 376, 377, 378, 379, 380, 381, 382, 383, 384, 385, 386, 387, 388, 389, 390, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 404, 405, 406, 407, 408, 409, 410, 411, 412, 413, 414, 415, 416, 417, 418, 419, 420, 421, 422, 423, 424, 425, 426, 427, 428, 429, 430, 431, 432, 433, 434, 435, 436, 437, 438, 439, 440, 441, 442, 443, 444, 445, 446, 447);
//        List<Integer> expectedResult3 = List.of(1, 2, 3, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255, 256, 257, 258, 259, 260, 261, 262, 263, 264, 265, 266, 267, 268, 269, 270, 271, 272, 273, 274, 275, 276, 277, 278, 279, 280, 281, 282, 283, 284, 285, 286, 287, 288, 289, 290, 291, 292, 293, 294, 295, 296, 297, 298, 299, 300, 301, 302, 303, 304, 305, 306, 307, 308, 309, 310, 311, 312, 313, 314, 315, 316, 317, 318, 319, 320, 321, 322, 323, 324, 325, 326, 327, 328, 329, 330, 331, 332, 333, 334, 335, 336, 337, 338, 339, 340, 341, 342, 343, 344, 345, 346, 347, 348, 349, 350, 351, 352, 353, 354, 355, 356, 357, 358, 359, 360, 361, 362, 363, 364, 365, 366, 367, 368, 369, 370, 371, 372, 373, 374, 375, 376, 377, 378, 379, 380, 381, 382, 383, 384, 385, 386, 387, 388, 389, 390, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 404, 405, 406, 407, 408, 409, 410, 411, 412, 413, 414, 415, 416, 417, 418, 419, 420, 421, 422, 423, 424, 425, 426, 427, 428, 429, 430, 431, 432, 433, 434, 435, 436, 437, 438, 439, 440, 441, 442, 443, 444, 445, 446, 447);
//        assertEquals(0, Main.deleteDuplicatesForKey(arraySource3, 4));
//        assertIterableEquals(expectedResult3, arraySource3);
    }

    // b. X should appear min(2, m) times if X appears m times in A
    @Test
    public void deleteDuplicatesToMinTimes() {
        // Case 1.1
        List<Integer> arraySource1 = Arrays.asList(2, 4, 8, 10, 10, 10, 10, 10, 15, 20, 21, 22, 22, 22, 23, 30, 40, 41, 41, 50, 64);
        List<Integer> expectedResult1 = Arrays.asList(2, 4, 8, 10, 10, 15, 20, 21, 22, 22, 22, 23, 30, 40, 41, 41, 50, 64, 41, 50, 64);
        assertEquals(18, Main.deleteDuplicatesToMinTimes(arraySource1, 5));
        assertIterableEquals(expectedResult1, arraySource1);

        // Case 1.2
        List<Integer> arraySource2 = Arrays.asList(2, 4, 8, 10, 10, 10, 10, 10, 15, 16, 16, 16, 16, 16, 20, 21, 22, 22, 22, 23, 30, 40, 41, 41, 50, 64);
        List<Integer> expectedResult2 = Arrays.asList(2, 4, 8, 10, 10, 15, 16, 16, 20, 21, 22, 22, 22, 23, 30, 40, 41, 41, 50, 64, 30, 40, 41, 41, 50, 64);
        assertEquals(20, Main.deleteDuplicatesToMinTimes(arraySource2, 5));
        assertIterableEquals(expectedResult2, arraySource2);

        // Case 1.3
        List<Integer> arraySource3 = Arrays.asList(2, 4, 8, 10, 10, 10, 10, 10, 15, 16, 16, 16, 16, 16, 20, 21, 22, 22, 22, 23, 30, 40, 41, 41, 50, 64);
        List<Integer> expectedResult3 = Arrays.asList(2, 4, 8, 10, 10, 10, 10, 10, 15, 16, 16, 16, 16, 16, 20, 21, 22, 22, 22, 23, 30, 40, 41, 41, 50, 64);
        assertEquals(26, Main.deleteDuplicatesToMinTimes(arraySource3, 1));
        assertIterableEquals(expectedResult3, arraySource3);
    }

    ///*** 5.18. Variations MatricInSpiralOrder ***///
    // a. Given d, write a program to generatea d x d 2D array
    @Test
    public void generateSpiralingSquareMatrix() {
        // Case 1.1
        List<List<Integer>> expectedResult = new ArrayList<>(
                Arrays.asList(
                        Arrays.asList(1, 2, 3, 4),
                        Arrays.asList(12,13, 14, 5),
                        Arrays.asList(11, 16, 15, 6),
                        Arrays.asList(10, 9, 8, 7)
                )
        );

        assertEquals(expectedResult, Main.generateSpiralingSquareMatrix(4));

        // Case 1.2
        List<List<Integer>> expectedResult2 = new ArrayList<>(
                Arrays.asList(
                        Arrays.asList(1, 2, 3),
                        Arrays.asList(8,9, 4),
                        Arrays.asList(7, 6, 5)
                )
        );

        assertEquals(expectedResult2, Main.generateSpiralingSquareMatrix(3));

        // Case 1.3
        List<List<Integer>> expectedResult3 = new ArrayList<>(
                List.of(
                        List.of(1)
                )
        );

        assertEquals(expectedResult3, Main.generateSpiralingSquareMatrix(1));

        // Case 1.4
        List<List<Integer>> expectedResult4 = new ArrayList<>(
                Arrays.asList(
                        Arrays.asList(1, 2, 3, 4, 5, 6),
                        Arrays.asList(20, 21, 22, 23, 24, 7),
                        Arrays.asList(19, 32, 33, 34, 25, 8),
                        Arrays.asList(18, 31, 36, 35, 26, 9),
                        Arrays.asList(17, 30, 29, 28, 27, 10),
                        Arrays.asList(16, 15, 14, 13, 12, 11)
                )
        );

        assertEquals(expectedResult4, Main.generateSpiralingSquareMatrix(6));

        // Case 1.5
        List<List<Integer>> expectedResult5 = new ArrayList<>(
                Arrays.asList(
                        Arrays.asList(1, 2, 3, 4, 5, 6, 7),
                        Arrays.asList(24, 25, 26, 27, 28, 29, 8),
                        Arrays.asList(23, 40, 41, 42, 43, 30, 9),
                        Arrays.asList(22, 39, 48, 49, 44, 31, 10),
                        Arrays.asList(21, 38, 47, 46, 45, 32, 11),
                        Arrays.asList(20, 37, 36, 35, 34, 33, 12),
                        Arrays.asList(19, 18, 17, 16, 15, 14, 13)
                )
        );

        assertEquals(expectedResult5, Main.generateSpiralingSquareMatrix(7));
    }

    // b. Given a sequence of P, write a program to generate
    //    a 2d array A whose spiral order is P
    @Test
    public void generateSquareMatrixFromSequence() {
        // Case 2.1
        List<List<Integer>> expectedResult = new ArrayList<>(
                Arrays.asList(
                        Arrays.asList(1, 2, 3),
                        Arrays.asList(8, 9, 4),
                        Arrays.asList(7, 6, 5)
                )
        );

        assertEquals(expectedResult, Main.generateSquareMatrixFromSequence(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)));

        // Case 2.2
        List<List<Integer>> expectedResult2 = new ArrayList<>(
                Arrays.asList(
                        Arrays.asList(1, 2),
                        Arrays.asList(4, 3)
                )
        );

        assertEquals(expectedResult2, Main.generateSquareMatrixFromSequence(Arrays.asList(1, 2, 3, 4)));

        // Case 2.3
        List<List<Integer>> expectedResult3 = new ArrayList<>(
                List.of(
                        List.of(1)
                )
        );

        assertEquals(expectedResult3, Main.generateSquareMatrixFromSequence(List.of(1)));

        // Case 2.4
        List<List<Integer>> expectedResult4 = new ArrayList<>(
                Arrays.asList(
                        Arrays.asList(9 , 5, 3, 4),
                        Arrays.asList(30, 13, 54, 8),
                        Arrays.asList(10, 33, 21, 1),
                        Arrays.asList(6, 7, 34, 23)
                )
        );

        assertEquals(expectedResult4, Main.generateSquareMatrixFromSequence(Arrays.asList(9, 5, 3, 4, 8, 1, 23, 34, 7, 6, 10, 30, 13, 54, 21, 33)));
    }

    // c. Variant: Enumerate the first n pairs of integers (a, b) in spiral order,
    //    starting from (0,0) followed by (1,0). For example, if n = 10, your output should be
    //    (0,0), (1, 0), (1, - 1), (0, - 1), (-1, - 1), (-1, 0), (- 1, 1), (0,1), (1, 1), (2, 1).
    @Test
    public void spiralPairs() {
        // Case 3.1
        Point[] expectedResult = {new Point(0, 0), new Point(1, 0),
                new Point(1, -1), new Point(0, -1),
                new Point(-1, -1), new Point(-1, 0),
                new Point(-1, 1), new Point(0, 1),
                new Point(1, 1), new Point(2, 1)};

        assertIterableEquals(Arrays.asList(expectedResult), Arrays.asList(Main.spiralPairs(10)));

        // Case 3.2
        Point[] expectedResult2 = {new Point(0, 0), new Point(1, 0),
                new Point(1, -1), new Point(0, -1),
                new Point(-1, -1), new Point(-1, 0),
                new Point(-1, 1), new Point(0, 1),
                new Point(1, 1), new Point(2, 1),
                new Point(2, 0), new Point(2, -1),
                new Point(2, -2), new Point(1, -2),
                new Point(0, -2), new Point(-1, -2),
                new Point(-2, -2), new Point(-2, -1),
                new Point(-2, 0), new Point(-2, 1)};

        assertIterableEquals(Arrays.asList(expectedResult2), Arrays.asList(Main.spiralPairs(20)));
    }

    // d. Variant: Compute the spiral order for an m x n 2D array A
    @Test
    public void rectangleSpiralOrder() {
        // Case 4.1
        List<List<Integer>> inputMatrix = new ArrayList<>(
                Arrays.asList(
                        Arrays.asList(1, 2, 3, 4),
                        Arrays.asList(10, 11, 12, 5),
                        Arrays.asList(9, 8, 7, 6)
                )
        );

        List<Integer> expectedResult = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);

        assertEquals(expectedResult, Main.rectangleSpiralOrder(inputMatrix));

        // Case 4.2
        List<List<Integer>> inputMatrix2 = new ArrayList<>(
                Arrays.asList(
                        Arrays.asList(1, 4, 9, 10, 8),
                        Arrays.asList(14, 11, 12, 3, 10),
                        Arrays.asList(23, 18, 2, 21, 26)
                )
        );

        List<Integer> expectedResult2 = Arrays.asList(1, 4, 9, 10, 8, 10, 26, 21, 2, 18, 23, 14, 11, 12, 3);

        assertEquals(expectedResult2, Main.rectangleSpiralOrder(inputMatrix2));

    }
}