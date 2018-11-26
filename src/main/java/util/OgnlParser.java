package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

public class OgnlParser {
	
public OgnlContext context;

public OgnlParser() {
	context=new OgnlContext();
}
public void put(String key,Object value) {
	context.put(key, value);
}
public Object get(String expression) {
	Object value=null;
	try {
		value= Ognl.getValue(Ognl.parseExpression(expression), context, context.getRoot());
	} catch (OgnlException e) {
		e.printStackTrace();
	}
	return value;
}
public String sqlparse(String sql) {
	StringBuilder finalSql=new StringBuilder();
	int point=0;
	Pattern pattern=Pattern.compile("#\\{.+?\\}");
	Matcher matcher=pattern.matcher(sql);
	while (matcher.find()) {
		String expression=matcher.group();
		String ognlExpression=expression.replaceAll("\\{|\\}", "");
		finalSql.append(sql.substring(point,matcher.start())).append(get(ognlExpression).toString());
		point=matcher.end();
	}
	if(point<sql.length()) {
		finalSql.append(sql.substring(point, sql.length()));
	}
	return finalSql.toString();
}
}
