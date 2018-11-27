package handler;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;




public class ResultSetHandler {
public static List<Object> handle(ResultSet resultSet,Class<?> returnType) {
	ArrayList<Object> resultList=new ArrayList<Object>();
	try {
		ResultSetMetaData metaData=resultSet.getMetaData();
		while (resultSet.next()) {
			Object returnData=returnType.newInstance();
			for(int i=1;i<=metaData.getColumnCount();i++) {
				String fieldName=metaData.getColumnName(i);
				Field field=returnType.getField(fieldName);
				setValue(resultSet,metaData.getColumnType(i),fieldName,field,returnData);
			}
			resultList.add(returnData);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return resultList;
}
public static void setValue(ResultSet resultSet,int columnType,String fieldName,Field field,Object target) throws Exception {
	if(columnType==Types.VARCHAR||columnType==Types.CHAR||columnType==Types.LONGNVARCHAR) {
		String value=resultSet.getString(fieldName);
		field.set(target, value);
	}
	else if(columnType==Types.INTEGER||columnType==Types.SMALLINT) {
		int value=resultSet.getInt(fieldName);
		field.set(target, value);
	}
	else if(columnType==Types.FLOAT||columnType==Types.DOUBLE) {
		double value=resultSet.getDouble(fieldName);
		field.set(target, value);
	}
	else if(columnType==Types.BOOLEAN||columnType==Types.BIT) {
		boolean value=resultSet.getBoolean(fieldName);
		field.set(target,value);
	}
	else if(columnType==Types.DECIMAL||columnType==Types.NUMERIC) {
		BigDecimal value=resultSet.getBigDecimal(fieldName);
		field.set(target,value);
	}
	else if(columnType==Types.DATE) {
		Date value=resultSet.getDate(fieldName);
		field.set(target,value);
	}
	else if(columnType==Types.TIME) {
		Time value=resultSet.getTime(fieldName);
		field.set(target,value);
	}
	else if(columnType==Types.TIMESTAMP) {
		Timestamp value=resultSet.getTimestamp(fieldName);
		field.set(target,value);
	}
	else if(columnType==Types.BINARY||columnType==Types.VARBINARY||columnType==Types.LONGVARBINARY) {
		byte[] value=resultSet.getBytes(fieldName);
		field.set(target,value);
	}
}
}
