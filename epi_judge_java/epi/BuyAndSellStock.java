package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class BuyAndSellStock {
  @EpiTest(testDataFile = "buy_and_sell_stock.tsv")
  public static double computeMaxProfit(List<Double> prices) {
//    (310, 315, 275, 295, 260, 270, 290, 230, 255,250)
//    brute -force approach O(n^2)
//    double maxProfit = 0;

//    for (int i = 0; i < prices.size(); i++) {
//      for (int j = (i + 1); j < prices.size(); j++) {
//        double profit = prices.get(j) - prices.get(i);
//
//        if (profit > maxProfit) {
////          System.out.println("buy: " + prices.get(i) + ", sell: " + prices.get(j));
//          maxProfit = profit;
//        }
//      }
//    }

//    return maxProfit;

    //    (310, 315, 275, 295, 260, 270, 290, 230, 255,250)
//    optimized O(n) space and time complexity
    if (prices.isEmpty()) {
      return 0;
    }

    double maxProfit = 0;
    double profit = 0;
    double min = prices.get(0);

    for (int i = 0; i < prices.size(); i++) {
      if (prices.get(i) < min) {
        min = prices.get(i);
      }

      profit = prices.get(i) - min;

      if (profit > maxProfit) {
        maxProfit = profit;
      }
    }

    return maxProfit;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BuyAndSellStock.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
