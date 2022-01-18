package AccessControl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

public class MethodAccessProxy<T> {
    private T myClass;
    private Map<Method, List<Class>> access;

    // Constructor
    MethodAccessProxy(T myClass) {
        setClass(myClass);
    }

    // Admin package API.
    void grantAccess(Class client, Method method) {
        checkValidMethod(method);
        access.get(method).add(client);
    }

    public void grantAccess(Collection<Class> clients, Method method){
        checkValidMethod(method);
        access.get(method).addAll(clients);
    }

    void removeAccess(Class client, Method method) {
        checkValidMethod(method);
        checkHasAccess(client, method);
        access.get(method).remove(client);
    }

    public void removeAccess(Collection<Class> clients, Method method){
        checkValidMethod(method);
        checkAllHasAccess(clients, method);
    }

    // Client public API
    public boolean hasAccess(Class client, Method method) {
        checkValidMethod(method);
        return access.get(method).contains(client);
    }

    public Object execute(Class client, Method method, Object... args) throws InvocationTargetException, IllegalAccessException {
        checkValidMethod(method);
        checkHasAccess(client, method);
        return method.invoke(myClass, args);
    }

    // Getters & Setters
    public void setClass(T myClass){
        this.myClass = myClass;
        initAccessMap();
    }

    public List<Method> getMethods(){
        return Arrays.asList(myClass.getClass().getMethods());
    }

    // Private methods
    private void initAccessMap() {
        access = new HashMap<>();
        for(Method method : myClass.getClass().getMethods()){
            if(methodIsPublic(method)){
                access.put(method, new LinkedList<>());
            }
        }
    }

    private boolean methodExist(Method method) {
        return getMethods().contains(method);
    }

    private boolean methodIsPublic(Method method) {
        return method.getModifiers() == Modifier.PUBLIC;
    }

    // Preconditions
    private void checkValidMethod(Method method){
        assert(methodExist(method)) : String.format("Error. The method '%s' dont exist in class '%s'.", method.getName(), myClass.getClass().getName());
        assert(methodIsPublic(method)) : String.format("Error. The method '%s' is not public.", method.getName());
    }

    private void checkHasAccess(Class client, Method method) {
        assert(hasAccess(client,method)) : String.format("Error. %s cant access to this method. Send request to the Admin.", client.getName());
    }

    private void checkAllHasAccess(Collection<Class> clients, Method method) {
        List<Class> whoCantAccess = clients.stream().filter(c -> hasAccess(c, method)).toList();
        assert(whoCantAccess.size() == 0) : String.format("Error. These classes has no access to the method '%s': %s", method.getName(), whoCantAccess);
    }
}
