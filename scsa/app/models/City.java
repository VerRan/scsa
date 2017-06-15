package models;

import javax.persistence.Entity;

import play.data.validation.MaxSize;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class City extends Model {

	@Required(message="城市编码不能为空。")
	@MinSize(value=3,message="城市编码小于3，太短了。")
	@MaxSize(value=10,message="城市编码大于10，太长了。")
	public String cityCode;
	
	@Required(message="城市名称不能为空。")
	@MinSize(value=2,message="城市名称小于2，太短了。")
	@MaxSize(value=10,message="城市名称大于10，太长了。")
	public String cityName;
	public City(String cityCode, String cityName) {
		super();
		this.cityCode = cityCode;
		this.cityName = cityName;
	}
	
	public String toString() {
	    return cityName;
	}

}
