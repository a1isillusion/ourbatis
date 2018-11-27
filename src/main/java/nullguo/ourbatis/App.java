package nullguo.ourbatis;


import java.io.File;
import java.sql.Connection;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import database.Database;
import map.MappedStatement;
import ognl.Ognl;
import ognl.OgnlContext;
import proxy.MapperProxy;
import util.AnnotationUtil;
import util.OgnlParser;
import util.TestMapper;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        Test test=new Test();
        test.id="myid";
        test.num=5;
        test.b=false;
        AnnotationUtil annotationUtil=new AnnotationUtil();
        annotationUtil.scanAnootation();
        TestMapper mapper=MapperProxy.getInstance(TestMapper.class);
        Customer customer=new Customer();
        customer.id=7;
        customer.name="our";
        customer.sex="男";
        customer.papertype="身份证";
        customer.papernumber="123225";
        customer.phonenumber="321234";
        mapper.insertCustomer(customer);
        List<Customer> list=mapper.selectCustomer();
        for(Customer customer2:list) {
        	System.out.println(customer2);
        }
    }
}
