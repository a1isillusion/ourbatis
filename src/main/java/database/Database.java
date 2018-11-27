package database;

import java.io.File;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.LinkedList;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Database {
public static LinkedList<Connection> connectionPool=new LinkedList<Connection>();
static {
	try {
		 String driver=null,url=null,user=null,password=null;
		 //连接池默认大小为15
		 int  InitSize = 15;
		 File f = new File(Class.forName("database.Database").getResource("/").getPath());
		 File [] fileList=f.listFiles();
		 for(File file:fileList) {
			 if(file.getName().equals("oubatis.xml")) {
				 SAXReader reader=new SAXReader();
			        Document document=reader.read(file);
			        Element root=document.getRootElement();
			        driver=root.element("driver").getText();
			        url=root.element("url").getText();
			        user=root.element("user").getText();
			        password=root.element("password").getText();
			        InitSize=Integer.parseInt(root.element("initSize").getText());
			 }
		 }
        //加载驱动
        Class.forName(driver);
        for(int i = 0; i < InitSize; i++){
            Connection conn = DriverManager.getConnection(url, user, password);
            connectionPool.add(conn);
        }
	} catch (Exception e) {
		e.printStackTrace();
	}
}
public static Connection getConnection() {
    if(connectionPool.size() > 0){
        //从集合中获取一个连接
        final Connection conn = connectionPool.removeFirst();
        //返回Connection的代理对象
        return (Connection) Proxy.newProxyInstance(conn.getClass().getClassLoader(), conn.getClass().getInterfaces(), new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args)
                    throws Throwable {
                if(!"close".equals(method.getName())){
                    return method.invoke(conn, args);
                }else{
                    connectionPool.add(conn);
                    return null;
                }
            }
        });
    }else{
        throw new RuntimeException("数据库繁忙，稍后再试");
    }
}

}
