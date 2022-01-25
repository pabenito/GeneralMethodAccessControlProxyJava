package TestProxy;

public interface MyInterface {
    void method1() throws ClassNotFoundException, NoSuchMethodException;

    void method2(int value) throws ClassNotFoundException, NoSuchMethodException;

    boolean method3() throws ClassNotFoundException, NoSuchMethodException;
}

