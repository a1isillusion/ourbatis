package nullguo.ourbatis;

public class Customer {
public int id;
public String name;
public String sex;
public String papertype;
public String papernumber;
public String phonenumber;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getSex() {
	return sex;
}
public void setSex(String sex) {
	this.sex = sex;
}
public String getPapertype() {
	return papertype;
}
public void setPapertype(String papertype) {
	this.papertype = papertype;
}
public String getPapernumber() {
	return papernumber;
}
public void setPapernumber(String papernumber) {
	this.papernumber = papernumber;
}
public String getPhonenumber() {
	return phonenumber;
}
public void setPhonenumber(String phonenumber) {
	this.phonenumber = phonenumber;
}
@Override
public String toString() {
	return "Customer [id=" + id + ", name=" + name + ", sex=" + sex + ", papertype=" + papertype + ", papernumber="
			+ papernumber + ", phonenumber=" + phonenumber + "]";
}

}
