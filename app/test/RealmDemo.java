
package test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import framework.store.config.GGConfigurer;
import framework.store.log.GGLogger;
import framework.store.mongo.pool.GGMongoClientPool;
import framework.store.mongo.pool.GGMongoClients;

/**
 * http://jinnianshilongnian.iteye.com/blog/2019547
 * @Description:TODO
 * @version 1.0
 * @since JDK1.7
 * @author yaomy
 * @company xxxxxxxxxxxxxx
 * @copyright (c) 2017 yaomy Co'Ltd Inc. All rights reserved.
 * @date 2017年9月13日 下午4:37:09
 */
public class RealmDemo {
	private static final transient Logger log = LoggerFactory.getLogger(RealmDemo.class);
	public static void main(String[] args) {
		 GGConfigurer.load("conf/application.conf");
		 GGMongoClientPool.pool.initPool(GGMongoClients.getClients());
		 GGLogger.info("数据库初始化成功...");
		//获取SecurityManager安全管理器工厂类，此处使用shiro.ini文件进行初始化
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("conf/shiro-permission.ini");
		//获取SecurityManager安全管理器实例,并绑定给SecurityUtils
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		
		//创建用户名密码身份验证token(即：用户身份/凭证)
		UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123", true);
		//获取主题
		Subject subject = SecurityUtils.getSubject();
		
		try{
			//登录，即身份验证
			subject.login(token);
			GGLogger.info("是否登录成功---"+subject.isAuthenticated());
			GGLogger.info("是否记住我----"+subject.isRemembered());
			
			Session session = subject.getSession();
			if(subject.isPermitted("system+edit1+10")){
				System.out.println("----------------------拥有打印权限----------------");
			} else {
				System.out.println("----------------------无打印权限---------------------");
				
			}
			if(subject.hasRole("admin")){
				System.out.println("拥有权限");
			}else {
				GGLogger.info("无权限");
			}
		} catch(UnknownAccountException e){
			log.info("身份验证失败"+e);
		} catch (IncorrectCredentialsException  e) {
			log.info("身份验证失败"+e);
		} catch (LockedAccountException e) {
			log.info("身份验证失败"+e);
		} catch (ExcessiveAttemptsException e) {
			log.info("身份验证失败"+e);
			// TODO: handle exception
		} catch (AuthenticationException e) {
			log.info("身份验证失败"+e);
			// TODO: handle exception
		}
		
		subject.logout();
	}
}
