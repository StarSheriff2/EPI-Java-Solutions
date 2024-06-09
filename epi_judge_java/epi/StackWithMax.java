package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.NoSuchElementException;

public class StackWithMax {

//  private static class ElementWithCachedMax {
//    public Integer element;
//    public Integer max;
//
//    public ElementWithCachedMax(Integer element, Integer max) {
//      this.element = element;
//      this.max = max;
//    }
//  }
  public static class Stack {

    private final Deque<Integer> maxStack = new ArrayDeque<>();
    private final Deque<Integer> stack = new ArrayDeque<>();

    public boolean empty() {
      return stack.isEmpty();
    }

    public Integer max() {
      return maxStack.peek() != null ? maxStack.peekFirst() : null;
    }

    public Integer pop() {
      Integer popped = stack.removeFirst();
      if (maxStack.peek().equals(popped)) {
        maxStack.removeFirst();
      }
      return popped;
    }

    public void push(Integer x) {
      stack.addFirst(x);
      if (maxStack.isEmpty() || maxStack.peekFirst() <= x) {
        maxStack.addFirst(x);
      }
    }
  }

  @EpiUserType(ctorParams = {String.class, int.class})
  public static class StackOp {
    public String op;
    public int arg;

    public StackOp(String op, int arg) {
      this.op = op;
      this.arg = arg;
    }
  }

  @EpiTest(testDataFile = "stack_with_max.tsv")
  public static void stackTester(List<StackOp> ops) throws TestFailure {
    try {
      Stack s = new Stack();
      int result;
      for (StackOp op : ops) {
        switch (op.op) {
        case "Stack":
          s = new Stack();
          break;
        case "push":
          s.push(op.arg);
          break;
        case "pop":
          result = s.pop();
          if (result != op.arg) {
            throw new TestFailure("Pop: expected " + op.arg +
                                  ", got " + result);
          }
          break;
        case "max":
          result = s.max();
          if (result != op.arg) {
            throw new TestFailure("Max: expected " + op.arg +
                                  ", got " + result);
          }
          break;
        case "empty":
          result = s.empty() ? 1 : 0;
          if (result != op.arg) {
            throw new TestFailure("Empty: expected " + op.arg +
                                  ", got " + s);
          }
          break;
        default:
          throw new RuntimeException("Unsupported stack operation: " + op.op);
        }
      }
    } catch (NoSuchElementException e) {
      throw new TestFailure("Unexpected NoSuchElement exception");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "StackWithMax.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
