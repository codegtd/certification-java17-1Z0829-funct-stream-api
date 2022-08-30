package method_refer;

import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

@ToString
class Person {

  public static String[] staticNames = {"Allen", "Bob"};

  @Getter
  private String name;

  {
    int i = new Random().nextInt(2);
    this.name = staticNames[i];
  }

  public static void printStatic(String s) {

    System.out.print("printStatic-" + s + "\n");
  }

  public void printInstance(String s) {

    System.out.print("printInstance-" + s + "\n");
  }
}

public class MethodReference {
  public static void main(String[] args) {

    Arrays.stream(Person.staticNames)
          .forEach(Person::printStatic);

    Arrays.stream(Person.staticNames)
          .forEach(new Person()::printInstance);


    System.out.println("\nInstance Method on an arbitrary object: ");
    Arrays.sort(Person.staticNames, (x, y) -> x.compareToIgnoreCase(y));
    Arrays.sort(Person.staticNames, (String::compareToIgnoreCase));

    System.out.println("\nReference to Constructor: ");
    Stream.generate(Person::new)
          .limit(3)
          .forEach((x) -> System.out.println(x ));
  }
}