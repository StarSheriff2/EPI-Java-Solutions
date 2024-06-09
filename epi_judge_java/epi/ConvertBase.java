package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.Map;

public class ConvertBase {
  @EpiTest(testDataFile = "convert_base.tsv")

  public static String convertBase(String numAsString, int b1, int b2) {
//    Map<String, String> digitMapping =  Map.of(
//            "10","A", "11", "B", "12", "C", "13", "D", "14", "E", "15", "F");

    boolean isNegative = numAsString.charAt(0) == ('-');

    int baseTenNum = numAsString.substring((isNegative) ? 1: 0)
            .chars()
            .reduce(0, (x, c) -> x * b1 +
                                (Character.isDigit(c) ? c - '0' : c - 'A' + 10));

    return (isNegative ? "-" : "") + (baseTenNum == 0 ? "0" : convertFromBase(baseTenNum, b2));

//    Prev solution:
//    StringBuilder targetBaseString = new StringBuilder();
//    String nextDigit = String.valueOf(Math.abs(baseTenNum) % b2);
//    nextDigit = digitMapping.getOrDefault(nextDigit, nextDigit);
//    targetBaseString.append(nextDigit);
//    int quotient = Math.abs(baseTenNum) / b2;
//
//    while (quotient != 0) {
//      nextDigit = String.valueOf(quotient % b2);
//      nextDigit = digitMapping.getOrDefault(nextDigit, nextDigit);
//      targetBaseString.append(nextDigit);
//      quotient = quotient / b2;
//    }
//
//    return targetBaseString.append((isNegative) ? "-" : "").reverse().toString();
  }

  private static String convertFromBase(int numAsInt, int base) {
    return numAsInt == 0 ?
            "":
            convertFromBase(numAsInt / base, base) +
            (char) (numAsInt % base >= 10 ? 'A' + numAsInt % base - 10
                                          : '0' + numAsInt % base);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ConvertBase.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
