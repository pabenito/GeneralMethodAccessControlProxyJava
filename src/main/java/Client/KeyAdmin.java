package Client;

import AccessControl.Admin;
import AccessControl.MethodAccessProxy;

public class KeyAdmin<T> implements Admin {
    private final MethodAccessProxy<T> proxy;
    private static String key = "AdminPassword";

    public KeyAdmin(String key, MethodAccessProxy<T> proxy){
        if(!KeyAdmin.key.equals(key)){
            throw new RuntimeException("The key is not correct");
        }
        this.proxy = proxy;
    }

    @Override
    public MethodAccessProxy<T> getProxy() {
        return this.proxy;
    }
}
