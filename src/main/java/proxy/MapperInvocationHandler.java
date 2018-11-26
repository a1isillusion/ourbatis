package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


public class MapperInvocationHandler implements InvocationHandler{
	
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        return new Object();

    }
}
