package cn.pconline.pcloud.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.pconline.framework.util.MD5Utils;
import cn.pconline.framework.util.StringUtils;
import cn.pconline.pcloud.admin.service.system.UserService;
import cn.pconline.pcloud.base.config.BaseProperties;
import cn.pconline.pcloud.base.entity.system.User;
import cn.pconline.pcloud.base.util.RedisKey;
import cn.pconline.pcloud.base.util.RedisTemplateUtil;

@Controller
public class LoginController {

	public static Log log = LogFactory.getLog(LoginController.class);

	@Autowired
	UserService userService;

	@Autowired
	RedisTemplateUtil redisTemplateUtil;
	
	@Autowired
    BaseProperties baseProperties;

	@RequestMapping(value = { "/admin/login" }, method = RequestMethod.GET)
	public String login(HttpServletRequest request) {
		
		Subject subject = SecurityUtils.getSubject();

		// 判断是否已经登录
		if (subject.isAuthenticated()) {
			return "redirect:/admin/index";
		}

		return "admin/login";
	}
	
	@RequestMapping(value = { "/admin/login" }, method = RequestMethod.POST)
	public String loginCheck(HttpServletRequest request) {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if(StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
			request.setAttribute("msgTxt", "请输入账号和密码！");
			return "admin/login";
		}
		
		// 查询账号
		User user = userService.findByAccount(username);
		if(user == null) {
			request.setAttribute("msgTxt", "账号不存在！");
			return "admin/login";
		}
		
		// 密码错误
		if (!MD5Utils.md5(password).equals(user.getPwd())) {
			request.setAttribute("msgTxt", "密码错误！");
			return "admin/login";
		}
		// 账号锁定
		if (user.getIsLock() == 1 && user.getIsAdmin() != 1) {
			request.setAttribute("msgTxt", "账号已被锁定！");
			return "admin/login";
		}
		
		// Shiro鉴权
		try {
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			Subject subject = SecurityUtils.getSubject();
			subject.login(token);
			subject.getSession().setTimeout(3600 * 1000); // 过期时间

			// 登录成功跳转到首页
			return "redirect:/admin/index";
		} catch (AuthenticationException e) {
			request.setAttribute("msgTxt", e.getMessage());
			e.printStackTrace();
			return "admin/login";
		}
	}

	@RequestMapping(value = { "/admin/logout" }, method = RequestMethod.GET)
	public String logout() {
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		if (user != null) {
			redisTemplateUtil.del(baseProperties.getApplicationName() + RedisKey.REDIS_MY_ROLES + user.getUserId()); // 退出时清理缓存
		}
		subject.logout();

		return "redirect:/admin/login";
	}

}
