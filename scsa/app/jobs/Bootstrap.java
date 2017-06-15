package jobs;

import models.SysUser;
import play.Logger;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;
/**
 * 应用启动时 执行的job
 * 开发模式下，当地一个请求被发起时才执行
 * 生产模式下，当应用启动时执行
 * */
@OnApplicationStart(async=true)
public class Bootstrap extends Job{
	public void doJob(){
		java.util.List sysUserList=SysUser.find("email = ?", "admin").fetch();
		if(sysUserList.size()<1){//未初始化数据时，则加载初始化数据
			Logger.info("start init System basic data !", "");
			Fixtures.deleteAllModels();
			Fixtures.loadModels("data.yml");
			Logger.info("end init System basic data !", "");
		}else{
			Logger.info("basic data is exist ,init complete !", "");
		}
	
	}
}
