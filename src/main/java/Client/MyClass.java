package Client;

public class MyClass {
    public MyClass(){

    }

    // Simple method
    public void printHelloWorld(){
        printHelloTo("World");
    }

    // Method with argument
    public void printHelloTo(String to){
        System.out.println(giveHelloTo(to));
    }

    // Method with argument return
    public String giveHelloTo(String to){
        return "Hello " + to;
    }
}
