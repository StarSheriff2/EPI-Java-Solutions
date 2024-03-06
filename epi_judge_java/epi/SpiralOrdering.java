package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class SpiralOrdering {
  @EpiTest(testDataFile = "spiral_ordering.tsv")

  public static List<Integer>
  matrixInSpiralOrder(List<List<Integer>> squareMatrix) {
    if (squareMatrix.isEmpty()) {
      return Collections.emptyList();
    }

//    O(N^2) space complexity due to recursive stack; O(N^2) time complexity

    List<Integer> result = new ArrayList<>();
    computeSpiralOrder(squareMatrix, 0, squareMatrix.size() - 1,
            0, squareMatrix.size() - 1, 0, 0, result);

    return result;
  }

  private static void
  computeSpiralOrder(List<List<Integer>> squareMatrix,
                     int startRow, int endRow, int startCol,
                     int endCol, int currRow, int currCol,
                     List<Integer> result)
  {
    result.add(squareMatrix.get(currRow).get(currCol));

    if (currCol < endCol && currRow == startRow) {
      computeSpiralOrder(squareMatrix, startRow, endRow,
              startCol, endCol, currRow, (currCol + 1), result);
    } else if (currRow < endRow && currCol == endRow) {
      computeSpiralOrder(squareMatrix, startRow, endRow,
              startCol, endCol, (currRow + 1), currCol, result);
    } else if (currRow == endRow && currCol > startCol) {
      computeSpiralOrder(squareMatrix, startRow, endRow,
              startCol, endCol, currRow, (currCol - 1), result);
    } else if (currCol == startCol && currRow > startRow && (currRow - 1) != startRow) {
      computeSpiralOrder(squareMatrix, startRow, endRow,
              startCol, endCol, (currRow - 1), currCol, result);
    } else if (currRow != endRow) {
      computeSpiralOrder(squareMatrix, (startRow + 1), (endRow - 1),
              (startCol + 1), (endCol - 1), currRow, (currCol + 1), result);
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SpiralOrdering.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
