package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class ReplaceAndRemove {

  public static int replaceAndRemove(int size, char[] s) {
    if (s.length == 0 || s[0] == 0) {
      return 0;
    }

    int i = 0;
    while (i < s.length) {
      if (s[i] == 0) {
        break;
      }

      if (s[i] == 'b') {
        shiftArr(i, false, s);
      } else if (s[i] == 'a') {
        s[i++] = 'd';
        shiftArr(i, true, s);
        s[i] = 'd';
        i++;
      } else {
        i++;
      }
    }

    return (i);
  }

  private static void shiftArr(int start, boolean increase, char[] arr) {
    if (increase) {
      char temp = arr[start];
      for (int i = (start + 1); i < arr.length; i++) {
        if (temp == 0 ) {
          break;
        }

        char temp2 = arr[i];
        arr[i] = temp;
        temp = temp2;
      }
    } else {
      char temp = arr[arr.length - 1];
      for (int i = arr.length - 1; i > start; i--) {
        if ((i - 1) < 0) {
          break;
        }
        char temp2 = arr[i - 1];
        arr[i - 1] = temp;
        temp = temp2;
      }
    }
  }

  @EpiTest(testDataFile = "replace_and_remove.tsv")
  public static List<String>
  replaceAndRemoveWrapper(TimedExecutor executor, Integer size, List<String> s)
      throws Exception {
    char[] sCopy = new char[s.size()];
    for (int i = 0; i < size; ++i) {
      if (!s.get(i).isEmpty()) {
        sCopy[i] = s.get(i).charAt(0);
      }
    }

    Integer resSize = executor.run(() -> replaceAndRemove(size, sCopy));

    List<String> result = new ArrayList<>();
    for (int i = 0; i < resSize; ++i) {
      result.add(Character.toString(sCopy[i]));
    }
    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReplaceAndRemove.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
