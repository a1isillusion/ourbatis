Ourbatis：一个模仿Mybatis的持久层框架
使用方法：
在源文件目录新建ourbatis.xml，进行数据库配置，如下所示：
````markdown
<configuration>
<driver>com.mysql.jdbc.Driver</driver>
<url>jdbc:mysql://localhost:3306/hotel</url>
<user>root</user>
<password>1234</password>
<initSize>1</initSize>
</configuration>
````
新建Mapper接口，如下所示：
````markdown
@Mapper
public interface TestMapper {
@Delete(sql = "delete from customer where id=#{test.num};")	
public void deleteCustomer( @Param(value = "test") Test test,Test test1,@Param(value="test2") Test test2);
@Insert(sql="insert customer values(#{c.id},#{c.name},#{c.sex},#{c.papertype},#{c.papernumber},#{c.phonenumber});")
public void insertCustomer(@Param(value="c")Customer customer);
@Select(sql="select * from customer where id=#{id};")
public List<Customer> selectCustomer(@Param(value="id")int id);
}
````
启动Ourbatis，获取Mapper代理对象，如下所示：
````markdown
        InitOurbatis.init();
        TestMapper mapper=MapperProxy.getInstance(TestMapper.class);
````
