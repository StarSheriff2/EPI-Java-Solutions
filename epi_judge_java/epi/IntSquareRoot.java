package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IntSquareRoot {
  @EpiTest(testDataFile = "int_square_root.tsv")

  public static int squareRoot(int k) {
    long l = 0, u = k;

    while (l <= u) {
      long m = l + ((u - l) / 2);

      if ((m * m) <= k) {
        l = m + 1;
      } else {
        u = m - 1;
      }
    }

    return (int) l - 1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntSquareRoot.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
