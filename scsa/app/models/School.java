package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class School extends Model {
	@Required(message="学校编码不能为空。")
	public String schoolCode;
	@Required(message="学校名称不能为空。")
	public String schoolName;
	@MaxSize(500)
	public String schoolDesc;
	
	@Required(message="必须选择学校所在城市。")
	@ManyToOne
	public City city;

}
