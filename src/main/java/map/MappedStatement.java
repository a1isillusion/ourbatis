package map;

import java.util.HashMap;

public class MappedStatement {
public static final String SELECT_TYPE="select";
public static final String UPDATE_TYPE="update";
public static final String INSERT_TYPE="insert";
public static final String DELETE_TYPE="delete";
public String sqlType;
public String expression;
public HashMap<Integer, String> paramsMap;
public Class<?> retrunType;
public MappedStatement() {
	paramsMap=new HashMap<Integer, String>();
}
public String getSqlType() {
	return sqlType;
}
public void setSqlType(String sqlType) {
	this.sqlType = sqlType;
}
public String getExpression() {
	return expression;
}
public void setExpression(String expression) {
	this.expression = expression;
}
public HashMap<Integer, String> getParamsMap() {
	return paramsMap;
}
public void setParamsMap(HashMap<Integer, String> paramsMap) {
	this.paramsMap = paramsMap;
}
public Class<?> getRetrunType() {
	return retrunType;
}
public void setRetrunType(Class<?> retrunType) {
	this.retrunType = retrunType;
}

}
