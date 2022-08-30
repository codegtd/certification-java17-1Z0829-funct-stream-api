package pipeline;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
class Person {
  private String name;

  Person(String name) {
    System.out.println("Instantiating " + name);
    this.name = name;
  }
}