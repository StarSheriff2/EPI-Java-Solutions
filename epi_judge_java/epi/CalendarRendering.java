package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CalendarRendering {
  @EpiUserType(ctorParams = {int.class, int.class})

  public static class Event {
    public int start, finish;

    public Event(int start, int finish) {
      this.start = start;
      this.finish = finish;
    }
  }

  private static class Endpoint {
    public int time;
    public boolean isStart;

    Endpoint(int time, boolean isStart) {
      this.time = time;
      this.isStart = isStart;
    }
  }

  @EpiTest(testDataFile = "calendar_rendering.tsv")

  public static int findMaxSimultaneousEvents(List<Event> A) {
    List<Endpoint> endpoints = A.stream().map(
            (event) -> List.of(
                      new Endpoint(event.start, true),
                      new Endpoint(event.finish, false)
              ))
                    .flatMap(List::stream)
                    .collect(Collectors.toList());

    endpoints.sort((a, b) -> {
      if (a.time != b.time) {
//        return a.time - b.time;
        return Integer.compare(a.time, b.time);
      } else {
        return Boolean.compare(b.isStart, a.isStart);
//        return a.isStart && !b.isStart ? -1 : !a.isStart && b.isStart ? 1 : 0;
      }
    });

    int maxConcurrentEvents = 0, numConcurrentEvents = 0;
    for (Endpoint endpoint : endpoints) {
      if (endpoint.isStart) {
        maxConcurrentEvents = Math.max(++numConcurrentEvents, maxConcurrentEvents);
      } else {
        numConcurrentEvents--;
      }
    }

    return maxConcurrentEvents;
  }

//  Variant
//  Peak Bandwith:
//  pg. 210
//  Problem: Variant: Users 1, 2,..., n share an Internet connection.
//  User i uses b; bandwidth from times; to fi, inclusive.
//  What is the peak bandwidth
//  Insight:
//  Instead of adding the number of concurrent events (connections), we add the
//  bandwith of each connection
//  Our Endpoint class should include now bandwith
//  Whenever a we reach an end time endpoint, we subtract from the
//  currentTotalBandwith

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "CalendarRendering.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
