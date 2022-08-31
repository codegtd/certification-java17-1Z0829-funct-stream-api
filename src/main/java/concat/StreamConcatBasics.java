package concat;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

public class StreamConcatBasics {
  public static void main(String[] args) {
    Set tree1 = new TreeSet(List.of("Ralph", "Larry", "Carol"));
    Set tree2 = new TreeSet(List.of("Mark", "Mary", "Maggie"));

    Stream<String> stream1 = tree1.stream();
    Stream<String> stream2 = tree2.stream();

    stream1 = stream1.sorted(Comparator.reverseOrder());
    stream2 = stream2.filter(s-> s.charAt(s.length()-1) != 'y');

    System.out.println("---Merged---");
    Stream<String> merged = Stream.concat(stream1, stream2);
    merged.forEach(System.out::println);
  }
}