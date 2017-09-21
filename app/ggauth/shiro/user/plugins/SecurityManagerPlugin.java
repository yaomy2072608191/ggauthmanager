
package ggauth.shiro.user.plugins;

import play.PlayPlugin;
import framework.yaomy.config.GGConfigurer;
import framework.yaomy.log.GGLogger;
import framework.yaomy.mongo.pool.GGMongoClientPool;
import framework.yaomy.mongo.pool.GGMongoClients;
import ggauth.shiro.user.securitymanager.SecurityManagerPool;

/**
 * @Description:shiro安全管理器初始化
 * @version 1.0
 * @since JDK1.7
 * @author yaomy
 * @company xxxxxxxxxxxxxx
 * @copyright (c) 2017 yaomy Co'Ltd Inc. All rights reserved.
 * @date 2017年9月21日 下午8:12:04
 */
public class SecurityManagerPlugin extends PlayPlugin{

	 public void onApplicationStart() {	
		 GGLogger.info("SecurityManager初始化成功...");
		 SecurityManagerPool.initSecurityManager();
	 }
	 public void onApplicationStop() {
	 }
}