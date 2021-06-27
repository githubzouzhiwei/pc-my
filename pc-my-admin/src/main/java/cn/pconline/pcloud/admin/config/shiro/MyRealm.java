package cn.pconline.pcloud.admin.config.shiro;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.pconline.pcloud.base.entity.system.Resource;
import cn.pconline.pcloud.base.entity.system.Role;
import cn.pconline.pcloud.base.entity.system.User;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.AllowAllCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.pconline.framework.util.MD5Utils;
import cn.pconline.pcloud.admin.service.system.RoleService;
import cn.pconline.pcloud.admin.service.system.UserService;

/**
 * 
 * @author SPPan
 *
 */
@Component
public class MyRealm extends AuthorizingRealm {

	public MyRealm() {
		super(new AllowAllCredentialsMatcher());
		setAuthenticationTokenClass(UsernamePasswordToken.class);

		// 暂时禁用Cache
		 setCachingEnabled(false);
	}

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;

	/**
	 * 授权认证
	 * 引用shiro:hasPermission="key"|subject.isPermitted(key)|perms[key]时触发（前两者直接触发，后者必须在过滤器链里有配置）
	 * 角色认证同理
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		User user = (User) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		Set<String> shiroPermissions = new HashSet<>();
		Set<String> roleSet = new HashSet<String>();
		
		// 加载你的角色
		List<Role> roles = roleService.list4Login(user);
		
		if(roles != null){
			for (Role role : roles) {
				// 添加角色
				roleSet.add(role.getRoleKey());
				
				// 添加角色关联的资源
				if(role.getRelResourceList() != null){
					for (Resource resource : role.getRelResourceList()) {
						shiroPermissions.add(resource.getSourceKey());
					}
				}
			}
		}
		
		authorizationInfo.setRoles(roleSet);
		authorizationInfo.setStringPermissions(shiroPermissions);
		return authorizationInfo;
	}

	/**
	 * 登录认证
	 * 调用subject.login(token)时触发
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();

		User user = userService.findByAccount(username);

		String password = new String((char[]) token.getCredentials());

		// 账号不存在
		if (user == null) {
			throw new UnknownAccountException("账号不存在！");
		}
		// 密码错误
		if (!MD5Utils.md5(password).equals(user.getPwd())) {
			throw new IncorrectCredentialsException("密码错误！");
		}
		// 账号锁定
		if (user.getIsLock() == 1 && user.getIsAdmin() != 1) {
			throw new LockedAccountException("账号已被锁定！");
		}

		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
		return info;
	}

}
