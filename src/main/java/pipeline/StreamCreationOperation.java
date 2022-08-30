package pipeline;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class StreamCreationOperation {
  public static void main(String[] args) {

    String argss = (args != null && args.length > 0) ? "First" : null;
    List<String> arrayList = new ArrayList<>(List.of("First", "Second", "Third"));

    Stream<String> empty = Stream.empty();
    Stream<String> stream = Stream.of("First", "Second");
    Stream<String> orEmpty = Stream.ofNullable(argss);
    Stream<Double> infinite = Stream.generate(Math::random);
    Stream<String> lstream = arrayList.stream();

    infinite
         .limit(10)
         .forEach((t) -> System.out.print(String.format("%.4f  ", t)));

    lstream.forEach(System.out::println);


    /*╔═══════════════════════╗
      ║ TRAVERSING ITERATIONS ║
      ╚═══════════════════════╝*/
    // (1) Create Stream
    Stream<Integer> inf = Stream.iterate(5, (t) -> t + 5);
    Stream<Integer> fin = Stream.iterate(5, (t) -> t <= 100, (t) -> t + 5);

    for (String arg : args) {
      Stream<String> is = Stream.of(arg.split("\\s"));
                 stream = Stream.concat(stream, is);
    }

    // (2) Operation
    inf.limit(10).forEach((t) -> System.out.print(t + " "));

    // (3) Terminal Operation
    fin.forEach((t) -> System.out.print(t + " "));
    stream.forEach(System.out::println);

    Stream.Builder<String> w = Stream.<String>builder();
    for (String arg : args) {
      for (String s : arg.split("\\s")) {
        w.accept(s);
      }
    }
    Stream<String> wordStream = w.build();
    System.out.println("\n---- Stream of words using Stream.Builder");
    wordStream.forEach(System.out::println);

  }
}