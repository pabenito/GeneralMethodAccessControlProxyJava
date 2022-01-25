package TestProxy;

import MethodAccessControl.AccessController;
import MethodAccessControl.Feature;
import TestClients.AClient;
import TestClients.BClient;
import TestClients.CClient;

public class MyProxy implements MyInterface {
    private MyInterface myClass;
    private AccessController accessController;
    public MyProxy(MyInterface myClass) throws ClassNotFoundException {
       this.myClass = myClass;
       accessController = new AccessController();
    }

    @Override
    public void method1() throws ClassNotFoundException, NoSuchMethodException {
        checkAccess();
        myClass.method1();
    }

    @Override
    @Feature(classes = {AClient.class, BClient.class})
    public void method2(int value) throws ClassNotFoundException, NoSuchMethodException {
        checkAccess();
        myClass.method2(value);
    }

    @Override
    @Feature(classes = {AClient.class, CClient.class})
    public boolean method3() throws ClassNotFoundException, NoSuchMethodException {
        checkAccess();
        return myClass.method3();
    }

    private void checkAccess() throws ClassNotFoundException, NoSuchMethodException {
        assert (accessController.canAccess()) : accessController.getMessageError();
    }
}
