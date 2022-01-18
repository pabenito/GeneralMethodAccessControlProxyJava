package AccessControl;

public class Administrador<T> implements Admin {
    private final MethodAccessProxy<T> proxy;
    private static String key = "AdminPassword";

    public Administrador(String key, MethodAccessProxy<T> proxy){
        if(Administrador.key.equals(key)){
            throw new RuntimeException("The key is not correct");
        }
        this.proxy = proxy;
    }

    @Override
    public MethodAccessProxy<T> getProxy() {
        return this.proxy;
    }
}
