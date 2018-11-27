package handler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.Database;
import map.MappedStatement;
import util.OgnlParser;

public class StatementHandler {
public static Object handle(MappedStatement mappedStatement,Object[] args) {
	Connection connection=Database.getConnection();
	Object result=null;
	try {
		Statement statement=connection.createStatement();
        String sql=getSql(mappedStatement, args);
        System.out.println(sql);
		if(mappedStatement.getSqlType().equals(MappedStatement.SELECT_TYPE)) {
			ResultSet resultSet=statement.executeQuery(sql);
			result=ResultSetHandler.handle(resultSet, mappedStatement.getRetrunType());
		}
        else {
            result=statement.execute(sql);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return result;
}
public static String getSql(MappedStatement mappedStatement,Object[] args) {
	if(args==null) {
		return mappedStatement.getExpression();
	}
	OgnlParser parser=new OgnlParser();
	for(int point=0;point<args.length;point++) {
		if(mappedStatement.getParamsMap().containsKey(point)) {
			parser.put(mappedStatement.getParamsMap().get(point),args[point]);
		}
	}
	return parser.sqlparse(mappedStatement.getExpression());
}
}
