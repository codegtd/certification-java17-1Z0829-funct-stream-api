package concat;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

public class StreamConcatV2 {
  public static void main(String[] args) {
    Set tree1 = new TreeSet(List.of("Ralph", "Larry"));
    Set tree2 = new TreeSet(List.of("Mark", "Larry"));

    Stream<String> str1 = tree1.stream();
    Stream<String> str2 = tree2.stream();

    str1 = str1.peek(s -> System.out.print("1: "));
    str2 = str2.peek(s -> System.out.print("2: "));

    Stream<String> concatStream = Stream.concat(str1, str2);

    concatStream.forEach(System.out::println);

    /*╔════════════════╗
      ║ three options  ║
      ╠════════════════╣
      ║ RUN-TIME-ERROR ║
      ╚════════════════╝*/
    Stream<String> newMerge = Stream.concat(str1, Stream.of("Zoe","Pete"));
    str1 = str1.sorted(Comparator.reverseOrder());
    tree1.add("Zoe");
  }
}