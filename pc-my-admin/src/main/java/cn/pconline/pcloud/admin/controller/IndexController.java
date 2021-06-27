package cn.pconline.pcloud.admin.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.pconline.pcloud.admin.service.system.ResourceService;
import cn.pconline.pcloud.base.entity.system.Resource;
import cn.pconline.pcloud.base.entity.system.User;


@Controller
public class IndexController{
	
	@Autowired
	private ResourceService resourceService;

	@RequestMapping(value ={"/admin/","/admin/index"})
	public String index(Model model){
		// 获取登录账号信息
		Subject subject = SecurityUtils.getSubject();
		// 判断是否已经登录
		if (!subject.isAuthenticated()) {
			return "redirect:/admin/login";
		}	
		User user = (User) subject.getPrincipal();
		if(user == null) {
			subject.logout();
			return "redirect:/admin/login";
		}
		model.addAttribute("user", user);
		
		// 加载菜单
		List<Resource> allResource = resourceService.getAllResource(true);
		model.addAttribute("allResource", allResource);
		
		return "admin/index";
	}

	@RequestMapping(value = {"/admin/welcome"})
	public String welcome(Model model){
		
		return "admin/welcome";
	}
	
	@RequestMapping(value = {"/admin/permission/died"})
	public String noPrivilege(Model model){
		
		return "admin/error403";
	}
}
