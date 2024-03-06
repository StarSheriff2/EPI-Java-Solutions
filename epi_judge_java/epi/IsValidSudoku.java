package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class IsValidSudoku {
  @EpiTest(testDataFile = "is_valid_sudoku.tsv")

  // Check if a partially filled matrix has any conflicts.
  public static boolean isValidSudoku(List<List<Integer>> partialAssignment) {
//    [[0, 0, 0, 0, 0, 0, 0, 0, 0],
//     [0, 0, 0, 0, 0, 0, 0, 0, 0],
//     [0, 0, 0, 0, 0, 1, 2, 0, 0],
//     [0, 0, 0, 0, 6, 0, 4, 0, 0],
//     [0, 0, 0, 2, 0, 0, 0, 0, 5],
//     [7, 0, 0, 0, 0, 0, 0, 0, 0],
//     [0, 8, 3, 0, 0, 0, 0, 0, 0],
//     [0, 0, 0, 0, 0, 0, 3, 3, 0],
//     [0, 0, 4, 0, 0, 0, 0, 0, 0]]

        List<List<Integer>> testList = new ArrayList<>(Arrays.asList(
                new ArrayList<>(Collections.nCopies(9, 0)),
                new ArrayList<>(Collections.nCopies(9, 0)),
                new ArrayList<>(Collections.nCopies(9, 0))
        ));

        List<List<Integer>> testingList = new ArrayList<>(Arrays.asList(
                new ArrayList<>(Collections.nCopies(9, 0)),
                new ArrayList<>(Collections.nCopies(9, 0)),
                new ArrayList<>(Collections.nCopies(9, 0))
        ));

        boolean valid = true;
//        int column = 0;

//        Check rows
        for (int i = 0; i < 9; i++) {
          for (int j = 0; j < 9; j++) {
            int currValue = partialAssignment.get(i).get(j) - 1;

            if (currValue < 0) {
              continue;
            }

            if (testList.get(0).get(currValue) == 0) {
              testList.get(0).set(currValue, currValue);
            } else {
              valid = false;
              break;
            }
          }

            testingList = testList.stream()
                    .map(ArrayList::new)
                    .collect(Collectors.toList());

          for (List<Integer> innerList : testList) {
            Collections.fill(innerList, 0);
          }

          if (!valid) {
            break;
          }
        }

//        Check columns
      for (int j = 0; j < 9; j++) {
          for (int i = 0; i < 9; i++) {
              int currValue = partialAssignment.get(i).get(j) - 1;

              if (currValue < 0) {
                  continue;
              }

              if (testList.get(1).get(currValue) == 0) {
                  testList.get(1).set(currValue, currValue);
              } else {
                  valid = false;
                  break;
              }
          }

          testingList = testList.stream()
                  .map(ArrayList::new)
                  .collect(Collectors.toList());

          for (List<Integer> innerList : testList) {
              Collections.fill(innerList, 0);
          }

          if (!valid) {
              break;
          }
      }

    System.out.println("\ntestList: " + Arrays.toString(testList.toArray()));
//    System.out.println(Arrays.toString(partialAssignment.toArray()));
    System.out.println("\ntestingList: " + Arrays.toString(testingList.toArray()));
    return valid;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsValidSudoku.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
