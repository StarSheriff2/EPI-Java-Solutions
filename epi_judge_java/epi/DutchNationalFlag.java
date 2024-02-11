package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class DutchNationalFlag {
  public enum Color { RED, WHITE, BLUE }

  public static void dutchFlagPartition(int pivotIndex, List<Color> A) {
    Color pivotColor = A.get(pivotIndex);
    int colorOrdinal = pivotColor.ordinal();
    System.out.println("\nColorPivot: " + pivotColor);
    System.out.println("Before Partition: " + Arrays.toString(A.toArray()));

    int smaller = 0, equal = 0, larger = A.size();

    while (equal < larger) {
      if (A.get(equal).ordinal() < 1) {
        Collections.swap(A, smaller++, equal++);
      } else if (A.get(equal).ordinal() == 1) {
        ++equal;
      } else {
        Collections.swap(A, equal, --larger);
      }
    }

    System.out.println("Partioned: " + Arrays.toString(A.toArray()));

//    int i = 0, j = A.size() - 1, k = 0;
//
//    while(i <= j) {
//        if (A.get(i).ordinal() < colorOrdinal) {
//            Collections.swap(A, i++, k++);
//        } else if (A.get(i).ordinal() > colorOrdinal) {
//            Collections.swap(A, i, j--);
//        } else {
//            i++;
//        }
//    }
//    if (k < i) {
//        while (k < i) {
//            A.set(k++, pivotColor);
//        }
//    }
  }

  @EpiTest(testDataFile = "dutch_national_flag.tsv")
  public static void dutchFlagPartitionWrapper(TimedExecutor executor,
                                               List<Integer> A, int pivotIdx)
      throws Exception {
    List<Color> colors = new ArrayList<>();
    int[] count = new int[3];

    Color[] C = Color.values();
    for (int i = 0; i < A.size(); i++) {
      count[A.get(i)]++;
      colors.add(C[A.get(i)]);
    }

    Color pivot = colors.get(pivotIdx);
    executor.run(() -> dutchFlagPartition(pivotIdx, colors));

    int i = 0;
    while (i < colors.size() && colors.get(i).ordinal() < pivot.ordinal()) {
      count[colors.get(i).ordinal()]--;
      ++i;
    }

    while (i < colors.size() && colors.get(i).ordinal() == pivot.ordinal()) {
      count[colors.get(i).ordinal()]--;
      ++i;
    }

    while (i < colors.size() && colors.get(i).ordinal() > pivot.ordinal()) {
      count[colors.get(i).ordinal()]--;
      ++i;
    }

    if (i != colors.size()) {
      throw new TestFailure("Not partitioned after " + Integer.toString(i) +
                            "th element");
    } else if (count[0] != 0 || count[1] != 0 || count[2] != 0) {
      throw new TestFailure("Some elements are missing from original array");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DutchNationalFlag.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
