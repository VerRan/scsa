package models;

import javax.persistence.Entity;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Major extends Model {

	@Required(message="专业编码不能为空。")
	public String majorCode;
	@Required(message="专业名称不能为空。")
	public String majorName;
	@MaxSize(500)
	public String majorDesc;

}
