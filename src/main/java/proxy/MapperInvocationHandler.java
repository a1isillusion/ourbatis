package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import handler.StatementHandler;
import util.AnnotationUtil;


public class MapperInvocationHandler implements InvocationHandler{
	
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String key=method.getDeclaringClass().getName()+"."+method.getName();
        return StatementHandler.handle(AnnotationUtil.getStatementList().get(key), args);
    }
}
