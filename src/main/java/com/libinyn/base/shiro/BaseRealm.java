package com.libinyn.base.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.libinyn.base.been.UserInfo;

/**
 * @fileName: CrsRealm.java
 * @author: WeiHui.Zhang
 * @date: 2016-09-08  21:21
 * @version: v1.0.0
 */
public class BaseRealm extends AuthorizingRealm {


	/**
	 * 登录授权
	 *
	 * @param token 登录信息
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		String password = new String((char[]) token.getCredentials());
		UserInfo userInfo = null;
		if("libin".equals(username) && "123456".equals(password)){
			userInfo = new UserInfo();
			userInfo.setUsername("libin");
			userInfo.setName("李斌");
			return new SimpleAuthenticationInfo(userInfo, password, getName());
		}else{
			throw new IncorrectCredentialsException("账户或密码错误");
		}
		
	}

	/**
	 * 认证
	 *
	 * @param principals
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//获得用户，根据用户的查询相关的权限
//		UserInfo userInfo = (UserInfo) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		//赋予角色
		info.addRole("admin");
		//赋予权限
		info.addStringPermission("user");
		
		return info;
	}

	@Override
	public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
		super.clearCachedAuthorizationInfo(principals);
	}

	@Override
	public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
		super.clearCachedAuthenticationInfo(principals);
	}

	@Override
	public void clearCache(PrincipalCollection principals) {
		super.clearCache(principals);
	}

	public void clearAllCachedAuthorizationInfo() {
		getAuthorizationCache().clear();
	}

	public void clearAllCachedAuthenticationInfo() {
		getAuthenticationCache().clear();
	}

	public void clearAllCache() {
		clearAllCachedAuthenticationInfo();
		clearAllCachedAuthorizationInfo();
	}

	/**
	 * 清空该角色用户的权限缓存
	 *
	 * @param pc 权限
	 */
	public void clearUserPermCache(PrincipalCollection pc) {
		RealmSecurityManager securityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
		BaseRealm crsRealm = (BaseRealm) securityManager.getRealms().iterator().next();
		crsRealm.clearCachedAuthorizationInfo(pc);
	}
}