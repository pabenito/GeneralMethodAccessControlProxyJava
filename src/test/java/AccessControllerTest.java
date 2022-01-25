import TestClients.*;
import TestProxy.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccessControllerTest {
    static MyInterface proxy;
    static AClient a;
    static BClient b;
    static CClient c;

    @BeforeAll
    static void init() throws ClassNotFoundException {
        proxy = new MyProxy(new MyClass());
        a = new AClient(proxy);
        b = new BClient(proxy);
        c = new CClient(proxy);
    }

    // Test private methods

    // Test method1 access

    @Test
    void AHasAccessToMethod1() throws ClassNotFoundException, NoSuchMethodException {
        a.tryMethod1();
    }

    @Test
    void BHasAccessToMethod1() throws ClassNotFoundException, NoSuchMethodException {
        b.tryMethod1();
    }

    @Test
    void CAccessToMethod1() throws ClassNotFoundException, NoSuchMethodException {
        c.tryMethod1();
    }

    // Test method2 access

    @Test
    void AHasAccessToMethod2() throws ClassNotFoundException, NoSuchMethodException {
        a.tryMethod2();
    }

    @Test
    void BHasAccessToMethod2() throws ClassNotFoundException, NoSuchMethodException {
        b.tryMethod2();
    }

    @Test
    void CDoesntHaveAccessToMethod2() throws ClassNotFoundException, NoSuchMethodException {
        assertThrows(AssertionError.class, () -> c.tryMethod2(), String.format("Error. The class %s has no access to %s", c.getClass().getName(), "method1"));
    }

    // Test method3 access

    @Test
    void AHasAccessToMethod3() throws ClassNotFoundException, NoSuchMethodException {
        a.tryMethod3();
    }

    @Test
    void BDoesntHaveAccessToMethod3() throws ClassNotFoundException, NoSuchMethodException {
        assertThrows(AssertionError.class, () -> b.tryMethod3(), String.format("Error. The class %s has no access to %s", b.getClass().getName(), "method3"));
    }

    @Test
    void CHasAccessToMethod3() throws ClassNotFoundException, NoSuchMethodException {
        c.tryMethod3();
    }
}
