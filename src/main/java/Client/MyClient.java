package Client;

import AccessControl.MethodAccessProxy;

import java.lang.reflect.InvocationTargetException;

public class MyClient {
    MethodAccessProxy<MyClass> proxy;

    public MyClient(MethodAccessProxy<MyClass> proxy){
        this.proxy = proxy;
    }

    public void tryPrintHelloWorld(){
        try {
            proxy.execute(this.getClass(), proxy.getPublicMethods().stream().filter(m -> m.getName().equals("printHelloWorld")).toList().get(0), null);
        } catch (Exception e) {
            System.out.println("There was an error trying to execute printHello:\n" + e.getMessage());
        } catch (AssertionError e){
            System.out.println("There was an error trying to execute printHello:\n" + e.getMessage());
        }
    }

    public void tryPrintHelloTo(String to){
        try {
            proxy.execute(this.getClass(), proxy.getPublicMethods().stream().filter(m -> m.getName().equals("printHelloTo")).toList().get(0), to);
        } catch (Exception e) {
            System.out.println("There was an error trying to execute printHelloTo:\n" + e.getMessage());
        } catch (AssertionError e){
            System.out.println("There was an error trying to execute printHelloTo:\n" + e.getMessage());
        }
    }

    public String giveHelloTo(String to){
        try {
            return (String) proxy.execute(this.getClass(), proxy.getPublicMethods().stream().filter(m -> m.getName().equals("giveHelloTo")).toList().get(0), to);
        } catch (Exception e) {
            System.out.println("There was an error trying to execute giveHelloTo:\n" + e.getMessage());
        } catch (AssertionError e){
            System.out.println("There was an error trying to execute giveHelloTo:\n" + e.getMessage());
        }
        return "Error";
    }
}
