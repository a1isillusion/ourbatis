package util;



import java.util.List;

import annotation.Delete;
import annotation.Insert;
import annotation.Mapper;
import annotation.Param;
import annotation.Select;
import nullguo.ourbatis.Customer;
import nullguo.ourbatis.Test;

@Mapper
public interface TestMapper {
@Delete(sql = "delete from customer where id=#{test.num};")	
public void deleteCustomer( @Param(value = "test") Test test,Test test1,@Param(value="test2") Test test2);
@Insert(sql="insert customer values(#{c.id},#{c.name},#{c.sex},#{c.papertype},#{c.papernumber},#{c.phonenumber});")
public void insertCustomer(@Param(value="c")Customer customer);
@Select(sql="select * from customer;")
public List<Customer> selectCustomer();
}
