package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class IntAsArrayMultiply {
  @EpiTest(testDataFile = "int_as_array_multiply.tsv")
  public static List<Integer> multiply(List<Integer> num1, List<Integer> num2) {
    // total = 0
    // product = multiply_digit(array1[i], array2)
      //     total += product
      // repeat until i > array1.length
      List<Integer> total = new ArrayList<>();

      if (num1.get(0) == 0 || num2.get(0) == 0) {
          total.add(0);
          return total;
      }

      boolean isNegative = false;

      if (num1.get(0) < 0) {
          num1.set(0, num1.get(0) * -1);
          isNegative = true;
      }
      if (num2.get(0) < 0) {
          num2.set(0, num2.get(0) * -1);
          isNegative = !isNegative;
      }

      int decimalPlace = 1;
      for (int i = num1.size() - 1; i >= 0; i--) {
        List<Integer> product = multiply_digit(num1.get(i), num2);

        if (total.isEmpty()) {
            total = product;
        } else {
//            We add a zero to the end of list, just as we do in school multiplication
            int j = 1;
            while (j <= decimalPlace) {
                j++;
                product.add(0);
            }
            decimalPlace++;
            total = add_arrays(product, total);
        }
      }

      if (isNegative) {
          total.set(0, total.get(0) * -1);
      }

      System.out.println(Arrays.toString(total.toArray()));

      return total;
    }
    private static List<Integer> multiply_digit(int multiplier, List<Integer> array) {
      int carry = 0;

      List<Integer> subTotal = new ArrayList<>();

      for (int i = array.size() - 1; i >= 0; i--) {
        int digitsProduct = (array.get(i) * multiplier) + carry;

        carry = digitsProduct/10;
        if (i == 0) {
            subTotal.add(0, digitsProduct%10);
            if (digitsProduct/10 != 0) {
              subTotal.add(0, digitsProduct/10);
            }
        } else {
          subTotal.add(0, digitsProduct%10);
        }
      }

      return subTotal;
    }

    private static List<Integer> add_arrays(List<Integer> biggerArray, List<Integer> smallerArray) {
        int carry = 0;

        List<Integer> total = new ArrayList<>();

        int j = smallerArray.size() - 1;
        for (int i = biggerArray.size() - 1; i >= 0; i--) {
            int digitsSum;
            if (j < 0) {
                digitsSum = biggerArray.get(i) + carry;
            } else {
                digitsSum = biggerArray.get(i) + smallerArray.get(j) + carry;
            }

            carry = digitsSum/10;

            total.add(0, digitsSum%10);

            if (i == 0) {
                if (digitsSum/10 != 0) {
                    total.add(0, digitsSum/10);
                }
            }
            j--;
        }

        return total;
    }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntAsArrayMultiply.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
