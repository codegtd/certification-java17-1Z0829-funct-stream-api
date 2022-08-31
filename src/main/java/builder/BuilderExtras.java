package builder;

import java.util.stream.Stream;

public class BuilderExtras {
  public static void main(String[] args) {

    //
    Stream.Builder<String> builder = Stream.builder();
    builder = builder.add("a");  // ok but not necessary
    builder.add("b");
    builder.add("c");
    //    builder.add(10); // Compile-Error <-- 10 is 'int'

    Stream<String> stream = builder.build();
    stream.forEach(System.out::println);
    //    builder.add("d"); // Runtime-Error: 'Add' after 'build()' the Stream

    //-----------------------------------------------------------------------------

    // 1) STREAM-RAW-BUILD: IMPERATIVE-STYLE
    Stream.Builder rawBuilder = Stream.builder();
    rawBuilder.add("a");
    rawBuilder.add(new Object());
    Stream<String> str = rawBuilder.build();// Compile OK
    str.forEach(System.out::println);       // Compile OK | Runtime-Error


    // 2) STREAM-RAW-BUILD: CHAINED-STYLE
    Stream.builder()
          .add("a")                         // Compile OK
          .add(new Object())                // Compile OK
          .build()
          .forEach(System.out::println);    // Compile OK | Runtime OK

    Stream.<String>builder()
          .add("a")
          // .add(10)
          .build()
          .forEach(System.out::println);

    // TYPE IN RIGHT-SIDE-STREAM OF VARIABLE WILL NOT TYPING
//    Stream.Builder rawBuilder = Stream.<String>builder();
//    rawBuilder.add("a");
//    rawBuilder.add(new Object());
//
//    Stream<String> str2 = rawBuilder.build();
//    str2.forEach(System.out::println);
  }
}