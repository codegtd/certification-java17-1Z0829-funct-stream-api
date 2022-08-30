package method_refer;

import java.util.stream.Stream;

public class StreamMethodRefer {
  public static void main(String[] args) {

    String[] namesArray = {"Allen", "Fred", "Howard"};

    show("Reduce Lambda: " +
              Stream.iterate(5, (t) -> t <= 100, (t) -> t + 5)
                    .reduce((a, b) -> (a + b))// BinaryOperator-SAM
                    .get()); // Supplier-SAM

    show("Reduce MyMethod Refer.: " +
              Stream.iterate(5, (t) -> t <= 100, (t) -> t + 5)
                    .reduce(StreamMethodRefer::myMethod)// MyMethod
                    .get()); // Supplier-SAM

    show("Reduce MyMethodV2 Refer.: " +
              Stream.iterate(5, (t) -> t <= 100, (t) -> t + 5)
                    .reduce(StreamMethodRefer::myMethodV2)// MyMethodV2
                    .get()); // Supplier-SAM

    show("Reduce Integer Method Refer.: " +
              Stream.iterate(5, (t) -> t <= 100, (t) -> t + 5)
                    .reduce(Integer::sum)// Integer Class Method
                    .get()); // Supplier-SAM

//        BinaryOperator bi = (int a, Integer b) -> a + b;
  }

  private static int myMethod(int a, int b) {  return a + b;  }
  private static int myMethodV2(int a, Integer b) {  return a + b;  }

  private static void show(String txt) {

    System.out.println(txt);
  }
}