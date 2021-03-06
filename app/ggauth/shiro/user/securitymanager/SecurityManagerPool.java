
package ggauth.shiro.user.securitymanager;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.util.Factory;

/**
 * @Description:安全管理器管理
 * @version 1.0
 * @since JDK1.7
 * @author yaomy
 * @company xxxxxxxxxxxxxx
 * @copyright (c) 2017 yaomy Co'Ltd Inc. All rights reserved.
 * @date 2017年9月21日 下午8:14:40
 */
public enum SecurityManagerPool {

	pool;
	
	private SecurityManager securityManager = null;
	/**
	 * 
	 * @Description:初始化
	 * @author yaomy
	 * @date 2017年9月21日 下午8:19:51
	 */
	public void initSecurityManager(){
		if(securityManager == null){
			//获取SecurityManager安全管理器工厂类，此处使用shiro.ini文件进行初始化
			Factory<SecurityManager> factory = new IniSecurityManagerFactory("conf/shiro-permission.ini");
			//获取SecurityManager安全管理器实例,并绑定给SecurityUtils
			securityManager = factory.getInstance();
			SecurityUtils.setSecurityManager(securityManager);
		}
	}

}
