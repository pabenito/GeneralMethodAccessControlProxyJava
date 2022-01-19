import AccessControl.Admin;
import Client.KeyAdmin;
import AccessControl.MethodAccessProxy;
import Client.MyClass;
import Client.MyClient;

public class Program {
    public static void main(String[] args) {
        MyClass myclass = new MyClass();
        MethodAccessProxy<MyClass> proxy = new MethodAccessProxy<>(myclass);
        Admin admin = new KeyAdmin<MyClass>("AdminPassword", proxy);
        MyClient myClient = new MyClient(proxy);

        // Must fail
        myClient.tryPrintHelloWorld();
        myClient.tryPrintHelloTo("Software Developer");
        System.out.println(myClient.giveHelloTo("Software Developer"));
        System.out.print("\n");

        // Grant access
        admin.grantAccess(myClient.getClass(), proxy.getPublicMethods().stream().filter(m -> m.getName().equals("printHelloWorld")).toList().get(0));

        // Must work
        myClient.tryPrintHelloWorld();

        // Must fail
        myClient.tryPrintHelloTo("Software Developer");
        System.out.println(myClient.giveHelloTo("Software Developer"));
        System.out.print("\n");

        // Remove access
        admin.removeAccess(myClient.getClass(), proxy.getPublicMethods().stream().filter(m -> m.getName().equals("printHelloWorld")).toList().get(0));

        // Must fail
        myClient.tryPrintHelloWorld();
        myClient.tryPrintHelloTo("Software Developer");
        System.out.println(myClient.giveHelloTo("Software Developer"));
        System.out.print("\n");

        admin.grantAccess(myClient.getClass(), proxy.getPublicMethods());

        // Must work
        myClient.tryPrintHelloWorld();
        myClient.tryPrintHelloTo("Software Developer");
        System.out.println(myClient.giveHelloTo("Software Developer"));
        System.out.print("\n");

        admin.removeAccess(myClient.getClass(), proxy.getPublicMethods());

        // Must fail
        myClient.tryPrintHelloWorld();
        myClient.tryPrintHelloTo("Software Developer");
        System.out.println(myClient.giveHelloTo("Software Developer"));
        System.out.print("\n");
    }
}
