package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;
import java.util.function.ToIntBiFunction;

public class EvaluateRpn {
  @EpiTest(testDataFile = "evaluate_rpn.tsv")

  public static int eval(String expression) {
    StringBuilder numString = new StringBuilder();
    Deque<Integer> operands = new ArrayDeque<>();
    boolean isNegative = false;

    int i = 0;
    while (i < expression.length()) {
      char nextChar = expression.charAt(i);
      if (nextChar == '-' && (i + 1) < expression.length() && expression.charAt(i + 1) != ',') {
          isNegative = true;
      } else if (Character.isDigit(nextChar)) {
        numString.append(expression.charAt(i));
      } else if (nextChar == ',' && !numString.isEmpty()) {
          int num = Integer.parseInt(numString.toString());
        operands.addFirst(isNegative ? -1 * num : num);
        isNegative = false;
        numString.setLength(0);
      } else if (nextChar != ',') {
        int b = operands.removeFirst();
        int a = operands.removeFirst();
        operands.addFirst(performOperation(a, b, nextChar));
      }
      i++;
    }


//    stringToNum(expression, i);
//    for (int i = 0; i < expression.length(); ++i) {
//      String nextString = String.valueOf(expression.charAt(i));
//
//      if (operators.contains(nextString) || nextString.equals(",")) {
//        if (operands.isEmpty()) {
//          operands.addFirst(Integer.parseInt(numString.toString()));
//        }
//
//      }
//    }

    return operands.isEmpty() ? Integer.parseInt(numString.toString()) : operands.peekFirst();
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

//  public static Integer stringToNum(String str, int i) {
//    StringBuilder numString = new StringBuilder();
//
//    while (Character.isDigit(str.charAt(i))) {
//        numString.append(str.charAt(i));
//        i++;
//    }
//
//    return Integer.parseInt(numString.toString());
//  }



  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "EvaluateRpn.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
