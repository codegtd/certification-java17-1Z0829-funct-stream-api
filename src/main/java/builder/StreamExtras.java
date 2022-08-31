package builder;

import java.util.stream.Stream;

public class StreamExtras {
  public static void main(String[] args) {

    // 1) IMPERATIVE-STYLE:
    // 1.1) 'Intermediate Op's' must 'assign back' to 'Origin-Stream'
    Stream<Integer> stream = Stream.of(10, 20, 30);
    stream = stream.limit(2);
//    stream.limit(2);  // RUNTIME-ERROR: IntermOp's not computed
    stream.forEach(System.out::println);


    // 2) CHAINED-STYLE
    // 2.1) 'Intermediate Op's' are automatically computed
    Stream.of(10, 20, 30)
          .limit(2)
          .forEach(System.out::println);
  }
}