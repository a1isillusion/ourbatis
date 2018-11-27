package util;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import annotation.Delete;
import annotation.Insert;
import annotation.Mapper;
import annotation.Param;
import annotation.Select;
import annotation.Update;
import map.MappedStatement;

public class AnnotationUtil {
public static Map<String,MappedStatement> statementList=new HashMap<String,MappedStatement>();	
public void scanAnootation() {
	 statementList.clear();
	 File f = new File(this.getClass().getResource("/").getPath());
	 File [] fileList=f.listFiles();
	 for(File file:fileList) {
		 if(file.isDirectory()&&!file.getName().equals("META-INF")) {
			  validAnnotation(file.getName());
		 }
	 }
    
}
public static void validAnnotation(String packageName){
	List<Class<?>> clsList = ClassUtil.getAllClassByPackageName(packageName);
	for(Class<?> c:clsList) {
		if(c.isAnnotationPresent(Mapper.class)) {
			try {
				Method[] methods=c.getMethods();
				for(Method method:methods) {
					String key=method.getDeclaringClass().getName()+"."+method.getName();
					MappedStatement statement=new MappedStatement();
					statement.setRetrunType(method.getReturnType());
					if(method.isAnnotationPresent(Select.class)) {
					    String returnClass=((ParameterizedType)method.getGenericReturnType()).getActualTypeArguments()[0].getTypeName();
					    statement.setRetrunType(Class.forName(returnClass));
						statement.setSqlType(MappedStatement.SELECT_TYPE);
						statement.setExpression(method.getAnnotation(Select.class).sql());
					}
					if(method.isAnnotationPresent(Update.class)) {
						statement.setSqlType(MappedStatement.UPDATE_TYPE);
						statement.setExpression(method.getAnnotation(Update.class).sql());	
					}
					if(method.isAnnotationPresent(Insert.class)) {
						statement.setSqlType(MappedStatement.INSERT_TYPE);
						statement.setExpression(method.getAnnotation(Insert.class).sql());
					}
					if(method.isAnnotationPresent(Delete.class)) {
						statement.setSqlType(MappedStatement.DELETE_TYPE);
						statement.setExpression(method.getAnnotation(Delete.class).sql());
					}
					Parameter[] parameters=method.getParameters();
					int point=0;
					for(Parameter parameter:parameters) {
						if(parameter.getAnnotation(Param.class)!=null) {
							statement.getParamsMap().put(point,parameter.getAnnotation(Param.class).value());
						}
						point+=1;
					}
					statementList.put(key, statement);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}
	
}
public static void handleMethodAnnotation(Method method,String sqlType) {
	String key=method.getDeclaringClass().getName()+"."+method.getName();
	MappedStatement statement=new MappedStatement();
	statement.setSqlType(sqlType);
	statement.setExpression(method.getAnnotation(Delete.class).sql());
	Parameter[] parameters=method.getParameters();
	int point=0;
	for(Parameter parameter:parameters) {
		if(parameter.getAnnotation(Param.class)!=null) {
			statement.getParamsMap().put(point,parameter.getAnnotation(Param.class).value());
		}
		point+=1;
	}
	statementList.put(key, statement);
}
public static Map<String, MappedStatement> getStatementList() {
	return statementList;
}
public static void setStatementList(Map<String, MappedStatement> statementList) {
	AnnotationUtil.statementList = statementList;
}


}
