package pipeline;

import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class StreamTerminalOperation {

  private static Person getNewPerson() {

    String[] names = {"Mary", "Jim", "Joe", "Aaron"};
    return new Person(names[new Random().nextInt(4)]);
  }

  private static Person getNewPersonNoAaron() {
    String[] names = {"Mary", "Jim", "Joe"};
    return new Person(names[new Random().nextInt(3)]);
  }

  public static void main(String[] args) {
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