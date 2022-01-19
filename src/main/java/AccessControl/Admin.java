package AccessControl;

import java.lang.reflect.Method;
import java.util.Collection;

public interface Admin{
    <T> MethodAccessProxy<T> getProxy();

    default void grantAccess(Class client, Method method){
        getProxy().grantAccess(client, method);
    }

    default void grantAccess(Collection<Class> clients, Method method){
        getProxy().grantAccess(clients, method);
    }

    default void grantAccess(Class client, Collection<Method> methods){
        getProxy().grantAccess(client, methods);
    }

    default void removeAccess(Class client, Method method){
        getProxy().removeAccess(client, method);
    }

    default void removeAccess(Collection<Class> clients, Method method){
        getProxy().removeAccess(clients, method);
    }

    default void removeAccess(Class client, Collection<Method> methods){
        getProxy().removeAccess(client, methods);
    }
}
