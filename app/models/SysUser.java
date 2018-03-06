package models;

import javax.persistence.Entity;
import javax.validation.constraints.Max;

import play.data.validation.Email;
import play.data.validation.Match;
import play.data.validation.Min;
import play.data.validation.Phone;
import play.data.validation.Required;
import play.db.jpa.Model;
@Entity
public class SysUser extends Model implements java.io.Serializable{
	@Required(message="姓名必须填写。")
	public String userName ;
	@Required 
	//@Match(value="[0-9]{4}",message="密码必须为4位数字") //0-9 的4位数字
	public String password;
	
	@Required(message="手机号必须填写。")
	@Phone
	public String telPhone;
	
	@Required(message="邮箱必须填写。")
	@Email
	public String email ; 
	
	@Required(message="性别必须填写。")
	public String sex;
	@Required(message="年龄不能为空。")
	@Min(value=5,message="学生年龄不能小于5岁。")
	@Max(value=70,message="学生年龄不能大于70岁。")
	public int age;
	
	public String role ; //系统用户角色
	
	
	public String address ; 
	
	public String toString(){
		return userName ;
	}
	
}
