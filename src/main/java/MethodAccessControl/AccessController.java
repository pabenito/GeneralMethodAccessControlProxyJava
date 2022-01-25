package MethodAccessControl;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.Thread.currentThread;

public class AccessController {
    private final Class<?> proxy;
    private Class<?> caller;
    private Method method;
    private List<StackTraceElement> trace;
    private List<Class> clientWithAccess;

    // Public
    public AccessController() throws ClassNotFoundException {
        setTrace();
        this.proxy = getClassFromTrace(3);
    }

    public boolean canAccess() throws ClassNotFoundException, NoSuchMethodException {
        setTrace();
        setCaller();
        setMethod();
        setClientsWithAccess();
        return callerHasAccess();
    }

    public String getMessageError(){
        return  String.format("Error. The class %s has no access to %s", caller.getName(), method.getName());
    }

    // Private

        // Setters

    private void setTrace() {
        this.trace = List.of(currentThread().getStackTrace());
    }

    private void setCaller() throws ClassNotFoundException {
        this.caller = getClassFromTrace(5);
    }

    private void setMethod() throws NoSuchMethodException {
        String methodName = trace.get(4).getMethodName();
        List<Method> proxyMethods = List.of(proxy.getMethods());
        List<Method> withSameName = proxyMethods.stream().filter(m -> m.getName().equals(methodName)).toList();
        this.method = withSameName.get(0);
    }

    private void setClientsWithAccess() {
        Feature feature = method.getAnnotation(Feature.class);
        this.clientWithAccess = feature == null ? null : List.of(feature.classes());
    }

        // Trace

    private Class<?> getClassFromTrace(int index) throws ClassNotFoundException {
        return Class.forName(getClassName(index));
    }

    private String getClassName(int index) {
        return trace.get(index).getClassName();
    }

        // Check if caller has access

    private boolean callerHasAccess() {
        return !hasFeatureAnnotation() || clientWithAccess.contains(caller);
    }

    private boolean hasFeatureAnnotation() {
        return clientWithAccess != null;
    }
}
