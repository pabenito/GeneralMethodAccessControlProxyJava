package TestClients;

import TestProxy.MyInterface;

public class AClient{
    private final MyInterface myClass;

    public AClient(MyInterface myClass) {
        this.myClass = myClass;
    }

    public void tryMethod1() throws ClassNotFoundException, NoSuchMethodException {
        myClass.method1();
    }

    public void  tryMethod2() throws ClassNotFoundException, NoSuchMethodException {
        myClass.method2(0);
    }

    public boolean tryMethod3() throws ClassNotFoundException, NoSuchMethodException {
        return myClass.method3();
    }
}
