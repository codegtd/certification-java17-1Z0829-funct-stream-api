interface MyInterface {  String doSomething(String s);  }

class MyClass implements MyInterface {
  public String doSomething(String s) {return "3. Class " + s;  }
}

public class SamArgs {
  public static void main(String[] args) {
    MyInterface lambdaVar = (s) -> "1.  variable " + s;
    runSam(lambdaVar);
    runSam((s) -> "2.  unnamed " + s);
    runSam(new MyClass()::doSomething);
    runSam(SamArgs.staticReturnInterface());
//    runSam(SamArgs::staticReturnInterface);
  }

  private static void runSam(MyInterface i) {
    System.out.println(i.doSomething("hello"));  }

  private static MyInterface staticReturnInterface() {
    return (s) -> "4.  method " + s;  }
}