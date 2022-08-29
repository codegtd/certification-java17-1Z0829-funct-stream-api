import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Getter
@ToString
class Person {
  private String name;

  Person(String name) {
    System.out.println("Instantiating " + name);
    this.name = name;
  }
}

public class StreamPipeline {

  private static Person getNewPerson() {

    String[] names = {"Mary", "Jim", "Joe", "Aaron"};
    return new Person(names[new Random().nextInt(4)]);
  }

  private static Person getNewPersonNoAaron() {
    String[] names = {"Mary", "Jim", "Joe"};
    return new Person(names[new Random().nextInt(3)]);
  }

  public static void main(String[] args) {

    show("--> Process pipeline with '(3) Terminal Operation'");
    Stream.of("First", "Second")
          .forEach((s) -> System.out.println(s));

    show("(1) Created a Stream/Pipeline");
    Stream<Person> personStream = Stream.generate(() -> getNewPerson());

    for (int i = 0; i < 3; i++) System.out.println("\tJust looping");

    show("(2) Intermediate operation in Pipeline (Lazy)");
    personStream = personStream.limit(4);

    for (int i = 0; i < 3; i++) System.out.println("\tLooping again");

    show("(3) Terminal Operation in the Pipeline");
    personStream.forEach((s) -> System.out.println(s));

    /*╔═══════════════════════════════╗
      ║ OPERATIONS - CHAINED PIPELINE ║
      ╚═══════════════════════════════╝*/
    List<Person> personList = new ArrayList<>();
    for (int i = 0; i < 20; i++) personList.add(getNewPerson());

    long count =
         personList
              .stream()
              .filter((s) -> s.getName().equals("Jim"))
              .count();
    show("Random generation Jim Persons: " + count);

    /*╔═══════════════════════════════════╗
      ║ OPERATIONS - NOT CHAINED PIPELINE ║
      ╚═══════════════════════════════════╝*/
    Stream<Person> stream3 = personList.stream();
    stream3 = stream3.filter((s) -> s.getName().equals("Mary"));
    long maryCount = stream3.count();
    show("Random generation Mary Persons: " + maryCount);
    /*╔═══════════════════════════════════════════════╗
      ║           .count() END THE PIPELINE           ║
      ╠═══════════════════════════════════════════════╣
      ║  IF WE TRY FOREACH - WILL THROWS A EXCEPTION  ║
      ║ stream3.forEach((s) -> System.out.println(s));║
      ╚═══════════════════════════════════════════════╝*/

    /*╔══════════════════════════════════╗
      ║        TERMINAL OPERATION        ║
      ╠══════════════════════════════════╣
      ║ SHORT-CIRCUIT - OK - Aaron found ║
      ╚══════════════════════════════════╝*/
    Stream<Person> infinit = Stream.generate(() -> getNewPerson());
    show("Found Aaron: " + infinit.anyMatch((s) -> s.getName().equals("Aaron")));

    /*╔══════════════════════════════════════════╗
      ║            TERMINAL OPERATION            ║
      ╠══════════════════════════════════════════╣
      ║ SHORT-CIRCUIT - NOT-OK - Aaron not found ║
      ║     INFINITY EXECUTION WILL HAPPENS      ║
      ╚══════════════════════════════════════════╝*/
    Stream<Person> infinit2 = Stream.generate(() -> getNewPersonNoAaron());
    show("Not Found Aaron: " + infinit2.anyMatch((s) -> s.getName().equals("Aaron")));
  }

  private static void show(String txt) {

    System.out.println(txt);
  }

}