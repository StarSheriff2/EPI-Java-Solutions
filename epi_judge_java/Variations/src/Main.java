package Variations.src;

import epi.*;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    ///*** 5.18. Variations MatrixInSpiralOrder ***///
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

    // d. Variant: Compute the spiral order for an m x n 2D array A
    public static List<Integer> rectangleSpiralOrder(List<List<Integer>> rectangleMatrix) {
        if (rectangleMatrix.isEmpty()) {
            return Collections.emptyList();
        }

        int m = rectangleMatrix.size(), n = rectangleMatrix.get(0).size();

        List<Integer> spiralOrder = new ArrayList<>();

        final int[][] SHIFT = {{0, 1}, { 1, 0}, { 0, -1 }, { -1, 0}};
        int dir = 0, x = 0, y = 0;

        for (int i = 0; i < (m * n); i++) {
            spiralOrder.add(rectangleMatrix.get(x).get(y));
            rectangleMatrix.get(x).set(y, 0);
            int nextX = x + SHIFT[dir][0], nextY = y + SHIFT[dir][1];

            if (nextX >= m || nextX < 0 || nextY >= n || nextY < 0
                || rectangleMatrix.get(nextX).get(nextY) == 0) {
                dir = (dir + 1) % 4;
                nextX = x + SHIFT[dir][0];
                nextY = y + SHIFT[dir][1];
            }

            x =nextX;
            y = nextY;
        }

        return spiralOrder;
    }

    ///*    <================== Chapter 7: LinkedLists ==================>   *//

    ///*** 7.1. Merge Two Sorted Lists (pg. 92) ***///

    //    a. Variant 2: Doubly Linked List
    public static DoublyListNodeImpl<Integer> mergeTwoSortedDoublyLists(DoublyListNodeImpl<Integer> L1,
                                                                         DoublyListNodeImpl<Integer> L2) {

        DoublyListNodeImpl<Integer> mergedLists = new DoublyListNodeImpl<>(null,null, null);
        DoublyListNodeImpl<Integer> currNode = mergedLists;

        while(L1 != null && L2 != null) {
            if (L1.data <= L2.data) {
                currNode.next = L1;
                L1 = L1.next;
            } else {
                currNode.next = L2;
                L2 = L2.next;
            }

            currNode.next.prev = currNode;
            currNode = currNode.next;
        }

        if (L1 != null) {
            currNode.next = L1;
        } else if (L2 != null) {
            currNode.next = L2;
        }

        currNode.next.prev = currNode;
        return mergedLists.next;
    }

    ///*** 7.2. Reverse a Single Sublist (pg. 93) ***///

    //    a. Variant 1: Reverse a singly-list
    public static ListNode<Integer> reverseSinglyList(ListNode<Integer> L) {
//        ListNode<Integer> dummyHead = new ListNode<>(0, L);
//        ListNode<Integer> nodeIter = dummyHead.next;
//        int len = L.size();
//
//        int k = 1;
//        while (k++ < len) {
//            ListNode<Integer> temp = nodeIter.next;
//            nodeIter.next = temp.next;
//            temp.next = dummyHead.next;
//            dummyHead.next = temp;
//        }
//
//        return dummyHead.next;
//    ListNode<Integer> dummyHead = new ListNode<>(null, L);
        ListNode<Integer> current = L;
        ListNode<Integer> prev = null;

        while (current != null) {
            ListNode<Integer> next_node = current.next;
            current.next = prev;
            prev = current;
            current = next_node;
        }

        return prev;
    }

    ///*    <================== Chapter 8: LinkedLists ==================>   *//

    ///*** 8.2. Variations Evaluate RPN ***///

    //    a. Variant 1: Do same problem with Polish notation

    public static int evaluatePn(String expression) {
        StringBuilder numString = new StringBuilder();
        Deque<Object> inputs = new ArrayDeque<>();
        boolean isNegative = false;

        int i = 0;
        while (i < expression.length()) {
            char nextChar = expression.charAt(i);
            if (nextChar == '-' && (i + 1) < expression.length() && expression.charAt(i + 1) != ',') {
                isNegative = true;
            } else if (Character.isDigit(nextChar) && (i + 1) != expression.length()) {
                numString.append(expression.charAt(i));
            } else if (!Character.isDigit(nextChar) && nextChar != ',') {
                inputs.addFirst(nextChar);
            } else if ((nextChar == ',') && !numString.isEmpty()) {
                if (inputs.peekFirst() instanceof Character) {
                    int num = Integer.parseInt(numString.toString());
                    inputs.addFirst(isNegative ? -1 * num : num);
                    isNegative = false;
                    numString.setLength(0);
                } else {
                    int b = Integer.parseInt(numString.toString());
                    b = isNegative ? -1 * b : b;
                    isNegative = false;
                    numString.setLength(0);
                    int a = (int) inputs.removeFirst();
                    char operator = (char) inputs.removeFirst();
                    inputs.addFirst(performOperation(a, b, operator));
                }
            } else if (Character.isDigit(nextChar)) {
                int b = nextChar - '0';
                int a = (int) inputs.removeFirst();
                char operator = (char) inputs.removeFirst();
                boolean isEmpty = inputs.isEmpty();
                inputs.addFirst(performOperation(a, b, operator));

                if (!isEmpty && (i + 1) == expression.length()) {
                    b = (int) inputs.removeFirst();
                    a = (int) inputs.removeFirst();
                    operator = (char) inputs.removeFirst();
                    inputs.addFirst(performOperation(a, b, operator));
                }
            }

            i++;
        }

        return inputs.isEmpty() ? Integer.parseInt(numString.toString()) : (int) inputs.peekFirst();
    }

    public static Integer performOperation(int a, int b, char o) {
        switch (o) {
            case '+' -> {
                return a + b;
            }
            case '-' -> {
                return a - b;
            }
            case '*' -> {
                return a * b;
            }
            default -> {
                return a / b;
            }
        }
    }

    ///*** 8.6. Variations Compute Binary Tree
    // Nodes in Order of Increasing Depth ***///

    // a. Variant 1:
    //    Variant: Write a program which takes
    //    as input a binary tree and returns the keys in top down,
    //    alternating left-to-right and right-to-left order,
    //    starting from left-to-right. pg. 118

//    Solution:

//    Bascially the same as solution for normal order, except, here we
//    alternate between left to right iteration and right to left iteration
//    for the nodeArr array.
//    We can simply create a boolean variable to alternate betweem both directions
//    Something like this:
public static List<List<Integer>>
binaryTreeDepthFirstWithAlternateOrder(BinaryTreeNode<Integer> tree) {
    List<List<Integer>> result = new ArrayList<>();

    if (tree == null) {
        return result;
    }

    boolean leftToRight = true;

    List<BinaryTreeNode<Integer>> nodeArr = List.of(tree);
    while (!nodeArr.isEmpty()) {
        result.add(nodeArr.stream()
                .map(node -> node.data)
                .toList());
        nodeArr = new ArrayList<>(nodeArr.stream()
                .map(node -> Arrays.asList(node.left, node.right))
                .flatMap(List::stream)
                .filter(Objects::nonNull)
                .toList());

        if (!leftToRight) {
            Collections.reverse(nodeArr);
        }

        leftToRight = !leftToRight;
    }

    return result;
}

// b. Variant 2
//
//    Variant: Write a program which takes as input a binary tree and returns
//    the keys in a bottom up, left-to-right order. For example, if the input
//    is the tree in Figure 10.1 on Page 148, your program should return
//    ((641), (17,401, 257), (28,0,3, 1, 28), (271, 561, 2, 271), (6, 6), (314)).

//    Solution
//  For this solution, we'll use a Stack instead of an array to store the
//    sequence of values.
//    So, once we're done traversing the BT, we deque values from result into our final
//    array answer

    public static List<List<Integer>>
    binaryTreeDepthOrderBottomUp(BinaryTreeNode<Integer> tree) {
        Deque<List<Integer>> result = new ArrayDeque<>();

        if (tree == null) {
            return new ArrayList<>();
        }

        List<BinaryTreeNode<Integer>> nodeArr = List.of(tree);
        while (!nodeArr.isEmpty()) {
            result.addFirst(nodeArr.stream()
                    .map(node -> node.data)
                    .toList());
            nodeArr = new ArrayList<>(nodeArr.stream()
                    .map(node -> Arrays.asList(node.left, node.right))
                    .flatMap(List::stream)
                    .filter(Objects::nonNull)
                    .toList());
        }

        List<List<Integer>> bottomUpOrder = new ArrayList<>();

        while(!result.isEmpty()) {
            bottomUpOrder.add(result.removeFirst());
        }

        return bottomUpOrder;
    }

//    Variant 3
//
//    Variant: Variant: Write a program which takes as input a
//    binary tree with integer keys, and returns the average
//    of the keys at each level. For example, if the input is
//    the tree in Figure 10.1 on Page 148, your program should
//    return (314,6, 276.25, 12, 225, 641).

//    Solution
//  For this solution, I would keep a counter for the number of nodes per depth,
//  by multuplying depth times 2, and then reducing each row and diving by this
//    value to get the avergae

    ///*    <================== Chapter 7: Binary Trees ==================>   *//

    ///*** 9.1. Variations Test if a Binary Tree is Height-balanced pg. 125  ***///

    // a. Variant 1: Write a program that returns the size of the
    // largest subtree that is complete pg. 127

    public static int largestCompleteTree(BinaryTreeNode<Integer> tree) {
        return findLargestCompleteSubtree(tree).size;
    }

    private static CompletenessStatusWithHeight findLargestCompleteSubtree(BinaryTreeNode<Integer> tree) {
        if (tree == null) {
            return new CompletenessStatusWithHeight(true, -1, 0, 0);
        }

        CompletenessStatusWithHeight leftSubtree = findLargestCompleteSubtree(tree.left);
        CompletenessStatusWithHeight rightSubtree = findLargestCompleteSubtree(tree.right);

        boolean complete = false;
        int size = (leftSubtree.size + rightSubtree.size) + 1;

        // Check if the current subtree is complete

        int height = Math.max(leftSubtree.height, rightSubtree.height) + 1;
        if ((leftSubtree.complete && rightSubtree.complete &&
                leftSubtree.height == rightSubtree.height) ||
                (leftSubtree.complete && rightSubtree.complete &&
                        leftSubtree.height == rightSubtree.height + 1)) {
            complete = true;
        }
        int largestCompleteSize = complete ?
                size :
                Math.max(leftSubtree.largestCompleteSize, rightSubtree.largestCompleteSize);

        return new CompletenessStatusWithHeight(complete, height, size, largestCompleteSize);
    }

    private static class CompletenessStatusWithHeight {
        public boolean complete;
        public int height;
        public int size;
        public int largestCompleteSize;

        public CompletenessStatusWithHeight(boolean complete, int height, int size, int largestCompleteSize){
            this.complete = complete;
            this.height = height;
            this.size = size;
            this.largestCompleteSize = largestCompleteSize;
        }
    }

    // b. Variant 2:
    //      Define a node in a binary tree to be k-balanced if the difference in the number
    // of nodes in its left and right subtrees is no more than k. Design an algorithm that
    // takes as input a binary tree and positive integer k, and returns a node in the binary
    // tree such that the node is not k-balanced, but all of its descendants are k-balanced.

    public static BinaryTreeNode<Integer> notKBalanced(BinaryTreeNode<Integer> tree, int k) {
        return checkKBalanced(tree, k).node;
    }

    private static KBalancedWithSize checkKBalanced(BinaryTreeNode<Integer> tree, int k) {
        if (tree == null) {
            return new KBalancedWithSize(true, 0, null);
        }

        KBalancedWithSize leftSubtree = checkKBalanced(tree.left, k);
        if (leftSubtree.node != null) {
            return leftSubtree; // Return early if a non-k-balanced node is found
        }

        KBalancedWithSize rightSubtree = checkKBalanced(tree.right, k);
        if (rightSubtree.node != null) {
            return rightSubtree; // Return early if a non-k-balanced node is found
        }

//        There might be scenarios where the left subtree has more nodes than
//        the right one; in which case, we need the abs difference
        boolean isKBalanced = Math.abs(leftSubtree.size - rightSubtree.size) <= k;
        int size = leftSubtree.size + rightSubtree.size + 1;

        if (!isKBalanced) {
            return new KBalancedWithSize(false, size, tree);
        } else {
//            We don't need to return the node if the tree is k-Balanced
            return new KBalancedWithSize(true, size, null);
        }
    }

    private static class KBalancedWithSize {
        public boolean isKBalanced;
        public int size;
        public BinaryTreeNode<Integer> node;

        public KBalancedWithSize(boolean isKBalanced, int size, BinaryTreeNode<Integer> node){
            this.isKBalanced = isKBalanced;
            this.size = size;
            this.node = node;
        }
    }

    ///*    <================== Chapter 10: Heaps ==================>   *//

    ///*** 10.4. Variations pg. 153  ***///

    // a. Variant 1: Prin kth element read up to that point p. 153

    public static void printKthLargest(Iterator<Integer> input, int k) {
        // Space complexity is K and Time is N log k
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(
                k,
                Integer::compare
        );

        while (input.hasNext()) {
            minHeap.add(input.next());

            if (minHeap.size() > k) {
                minHeap.remove();
            }

            if (minHeap.size() == k) {
                System.out.println(minHeap.peek());
            }
        }
    }

    ///*    <================== Chapter 11: Searching ==================>   *//

    ///*** 11.1. Variations pg. 161  ***///

    // a. Variant 1: Design an efficient algorithm that takes a sorted array and a key, and finds
    // the index of the first occurrence of an element greater than that key. p. 161

    public static int searchFirstOfGreaterThanK(List<Integer> A, int k) {
        int result = -1;
        int l = 0, u = A.size() - 1;

        while (l <= u) {
            int m = l + ((u - l)/2);

            if (A.get(m) <= k) {
                l = m + 1;
            } else {
                result = m;
                u = m - 1;
            }
        }

        return result;
    }

    ///*    <================== Chapter 12: HashMaps ==================>   *//

    ///*** Bootcamp Variation pg. 179  ***///
    // Variation:
    // Design a program that takes as input a set of words and returns groups of anagrams
    // for those words. Each group must contain at least two words.
    //  Requirement: time complexity should be O(nm)

    public static List<List<String>> findAnagrams(List<String> dictionary) {
        Map<String, List<String>> anagramsHash = new HashMap<>();

        dictionary.forEach((s) -> {
                String key = stringHash(s);
                anagramsHash.putIfAbsent(key, new ArrayList<>());
                anagramsHash.get(key).add(s);
            }
        );

        return anagramsHash.values().stream()
                .filter((a) -> a.size() >= 2)
                .collect(Collectors.toList());
    }

    public static String stringHash(String word) {
        int[] charCounts = new int[26];
        for (char c : word.toCharArray()) {
            charCounts[c - 'a']++;
        }

        return Arrays.toString(charCounts);
//        List<Integer> alphabetMatches = findInAlphabet(s);
//
//        String stringKey = "";
//        for (int i = 0; i < 26; i++) {
//            int charCount = alphabetMatches.get(i);
//            if (charCount >= 0) {
//                int j = 0;
//                while (j++ < charCount) {
//                    stringKey = stringKey.concat(Character.toString(i + 'a'));
//                }
//
//            }
//        }
//        return stringKey;
    }

    public static List<ContactList> mergeContactLists(
    List<ContactList> contacts) {
        return new ArrayList<>(new HashSet(contacts));

    }

    public static class ContactList {
        public List<String> names;

        ContactList(List<String> names) { this.names = names; }

        @Override
        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof ContactList)) {
                return false;
            }
            return this == obj ||
                    new HashSet(names).equals(new HashSet<>(((ContactList) obj).names));
        }

        @Override
        public int hashCode() {
            return new HashSet(names).hashCode();
        }
    }

//    public static List<Integer> findInAlphabet(String s) {
//        List<Integer> alphabetArray = new ArrayList<>(26);
//
//        for (int i = 0; i < 26; i++) {
//            alphabetArray.add(i, -1);
//        }
//
//        for (int i = 0; i < s.length(); i++) {
//            int idx = s.charAt(i) - 'a';
//            int count = Math.max(0, alphabetArray.get(idx)) + 1;
//            alphabetArray.set(idx, count);
//        }
//
//        return alphabetArray;
//    }

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

// Auxiliary classes

class DoublyListNodeImpl<T> {
    public T data;
    DoublyListNodeImpl<T> prev, next;

    DoublyListNodeImpl(T data, DoublyListNodeImpl<T> prev, DoublyListNodeImpl<T> next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }

    public void populateFromArrayList(List<T> arrayList) {
        DoublyListNodeImpl<T> current = this;

        if (arrayList.size() == 0) {
            return;
        } else {
            current.data = arrayList.get(0);
        }

        for (int i = 1; i < arrayList.size(); i++) {
            DoublyListNodeImpl<T> temp = current;
            current.next = new DoublyListNodeImpl(arrayList.get(i), temp, null);
            current = current.next;
        }
    }

    public String toString() {
        String result = "{ prev: " + (prev != null ? prev.data : null) +
                        ", data: " + data +
                        ", next: " + (next != null ? next.data : null) + "}, ";
//        result += "data: " + data + ", ";
        if (next != null) {
            result += next.toString();
        }
        return result;
    }
}

