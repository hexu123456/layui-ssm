package com.hx.shiro;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.hx.dao.FlatRightDao;
import com.hx.entity.TblManagerInfo;
import com.hx.service.FlatRightService;


public class ShiroRealms extends AuthorizingRealm {
	
	@Resource private FlatRightService flatAdminService;
	@Resource private FlatRightDao flatAdminDao;

	//授权shiro 回调方法,配置之后前端jsp可以使用shiro标签判断用户权限
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		//1.从PrincipalCollection中获取登录用户的信息
		Object principal = principals.getPrimaryPrincipal();
		//2.利用登录的用户的信息来用当前用户的角色或权限(可操作数据库)
		Set<String> roles=new HashSet<>();
		if(principal.equals("gzjojo"))
		  roles.add("gzjojo");
		//3.创建SimpleAuthorizationInfo,并设置其roles属性
		SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo(roles);
		//4.返回SimpleAuthorizationInfo
		return simpleAuthorInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {

		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		TblManagerInfo manager=null;
		try {
			manager = flatAdminDao.queryManager(token.getUsername().trim());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if (manager == null) {
			throw new UnknownAccountException();
		}
		if (manager != null) {
			// 6,构建对象并返回，AuthenticationInfo，使用实现类：SimpleAuthenticationInfo
			// principal：确认印证信息可以是username,也可以是对应的用户实体对象
			Object principal = manager.getAccount();
			// credentials:密码
			Object credentials = manager.getPassword();
			// realmName:当前对象的name，调用父类的getName()方法即可
			String realmName = getName();
			// 盐值
			ByteSource credentialsSalt = ByteSource.Util.bytes(manager
					.getAccount());
			SimpleAuthenticationInfo info = null;
			info = new SimpleAuthenticationInfo(principal, credentials,
					credentialsSalt, realmName);
			this.setSession("account", manager.getAccount()); 
            this.setSession("logintime", manager.getLogintime()); 
            this.setSession("headimg", manager.getHeadimg()); 
            this.setSession("provincename", manager.getProvincename()); 
            this.setSession("cityname", manager.getCityname()); 
            this.setSession("companyname", manager.getCompanyname()); 
            this.setSession("mparentid", manager.getMparentid());//父级管理员ID
            this.setSession("managerid", manager.getManagerid());//当前管理员ID
            this.setSession("type", manager.getType());//类型(type:x.平台超管  0.平台管理员  1.酒店管理员)
            this.setSession("linkman",manager.getLinkman());//联系人
            String client_token;
			try {
				//client_token = flatAdminService.get16Token(manager.getAccount(),manager.getPassword());
				//this.setSession("token",client_token);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return info;
		}
		return null;
	}
	  /** 
     * 保存登录名 
     */  
    private void setSession(Object key, Object value){  
        Session session = getSession();  
        //System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");  
        if(null != session){  
            session.setAttribute(key, value);  
        }  
    }  
      
    private Session getSession(){  
        try{  
            Subject subject = SecurityUtils.getSubject();  
            Session session = subject.getSession(false);  
            if (session == null){  
                session = subject.getSession();  
            }  
            if (session != null){  
                return session;  
            }  
        }catch (InvalidSessionException e){  
              
        }  
        return null;  
    }  
	public static void main(String[] args) {
		String algorithmName = "MD5";
		Object source = "123456";
		Object salt = ByteSource.Util.bytes("gzjojo");
		int hashIterations = 1024;
		SimpleHash simpleHash = new SimpleHash(algorithmName, source, salt,
				hashIterations);
		System.out.println(simpleHash);
	}

}
