package nullguo.ourbatis;

import java.util.HashMap;

import ognl.Ognl;
import ognl.OgnlContext;
import util.OgnlParser;

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
        test.num=12;
        test.b=false;
        OgnlParser parser=new OgnlParser("test", test);
        System.out.println(parser.sqlparse("select * from user where id=#{test.id} and num=#{test.num};"));
        
    }
}
