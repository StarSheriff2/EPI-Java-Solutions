package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
public class StringIntegerInterconversion {

  public static String intToString(int x) {
//    O(N)
//Average running time:    1 us
//Median running time:     1 us
    long power = 10;
    int polarize = 1;

    if (x < 0) {
      polarize = -1;
    }

    int nextDigit = (int) (polarize * (x % power));

    StringBuilder slug = new StringBuilder();
    slug.append((char) ('0' + nextDigit));
    nextDigit = (int) (polarize * ((x / power) % power));

    while ((x / power) != 0) {
      slug.append((char) ('0' + nextDigit));
      power *= 10;
      nextDigit = (int) (polarize * ((x / power) % 10));
    }

    if (polarize < 0) {
      return slug.append('-').reverse().toString();
    }

    return slug.reverse().toString();
  }
  public static int stringToInt(String s) {
    if (s.equals("0")) {
      return 0;
    }

    int power = 1;
    int integer = 0;

    for (int i = s.length() - 1; i >= 0; --i) {
      String nextString = String.valueOf(s.charAt(i));
      if (nextString.equals("+")) {
        break;
      } else if (nextString.equals("-")) {
        integer *= -1;
        break;
      } else {
        int nextInt = s.charAt(i) - '0';
        int nextDigit = nextInt * power;
        integer += nextDigit;
      }

      power *= 10;
    }

    return integer;
  }
  @EpiTest(testDataFile = "string_integer_interconversion.tsv")
  public static void wrapper(int x, String s) throws TestFailure {
    if (Integer.parseInt(intToString(x)) != x) {
      throw new TestFailure("Int to string conversion failed");
    }
    if (stringToInt(s) != x) {
      throw new TestFailure("String to int conversion failed");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "StringIntegerInterconversion.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
