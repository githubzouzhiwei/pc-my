package cn.pconline.pcloud.admin.controller.system;

import cn.pconline.framework.enums.XAdminCodeEnum;
import cn.pconline.framework.util.MD5Utils;
import cn.pconline.framework.util.StringUtils;
import cn.pconline.framework.util.XAdmindResult;
import cn.pconline.pcloud.admin.service.system.RoleService;
import cn.pconline.pcloud.admin.service.system.UserService;
import cn.pconline.pcloud.base.entity.system.Role;
import cn.pconline.pcloud.base.entity.system.User;
import cn.pconline.pcloud.base.form.UserPageForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/index")
	public String index() {
		return "admin/user/index";
	}

	@RequestMapping(value = { "/list" })
	@ResponseBody
	public XAdmindResult list(UserPageForm userPageForm) {
		PageInfo<User> pageInfo = userService.pager(userPageForm);
		return XAdmindResult.build(XAdminCodeEnum.SUCCESS, pageInfo.getList(), pageInfo.getTotal());
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		return "admin/user/add";
	}
	
	@RequestMapping(value = { "/create" }, method = RequestMethod.POST)
	@ResponseBody
	public XAdmindResult create(User user, ModelMap map) {
		User userOld = userService.find(user.getUserId());
		 // 先校验是否已存在
		if(userOld != null && userOld.getUserId() != null){
			return XAdmindResult.build(XAdminCodeEnum.ERROR, "账号("+ user.getAccount() + ")已存在！");
		}
		user.setIsAdmin(0);
		user.setIsLock(0);
		user.setCreateAt(new Date());
		user.setUpdateAt(new Date());
		
		int result = userService.create(user);
		return XAdmindResult.build(result > 0 ? XAdminCodeEnum.SUCCESS : XAdminCodeEnum.ERROR);
	}
	
	@RequestMapping(value = { "/account/list" })
	@ResponseBody
	public XAdmindResult accountList(UserPageForm userPageForm) {
		if(StringUtils.isBlank(userPageForm.getAccount()) || userPageForm.getAccount().length() < 2){
			return XAdmindResult.build(XAdminCodeEnum.SUCCESS, new ArrayList<User>(), 0);
		}
		
		// 从太平洋账号系统查询数据
		List<User> list = new ArrayList<>();
		/*if(list == null){
			return XAdmindResult.build(XAdminCodeEnum.ERROR);
		}*/
		
		return XAdmindResult.build(XAdminCodeEnum.SUCCESS, list, 0);
	}

	@RequestMapping(value = "/userRole/{userId}", method = RequestMethod.GET)
	public String userRole(@PathVariable Long userId, ModelMap map) {
		map.put("userId", userId);
		return "admin/user/userRole";
	}
	
	@ResponseBody
	@RequestMapping(value = "/getUserRole/{userId}", method = RequestMethod.GET)
	public JSONObject getUserRole(@PathVariable Long userId, ModelMap map) {
		List<Role> roles = roleService.list(); // 全部角色
		List<Role> myRoles = roleService.list(userId); // 关联角色
		
		JSONObject json = new JSONObject(), leftJson;
		JSONArray left = new JSONArray(), right = new JSONArray();
		json.put("left", left);
		json.put("right", right);
		
		for (Role role : roles) {
			if(!myRoles.stream().filter(o -> o.getRoleId() == role.getRoleId()).isParallel()){
				leftJson = new JSONObject();
				left.add(leftJson);
				leftJson.put("value", role.getRoleId());
				leftJson.put("title", role.getName());
			}
		}
		
		for (Role role : myRoles) {
			right.add(role.getRoleId());
		}
		
		return json;
	}
	
	@ResponseBody
	@RequestMapping(value = "/saveUserRole", method = RequestMethod.POST)
	public XAdmindResult saveUserRole(Long userId, @RequestParam(value = "roleIds[]", required = false) Long[] roleIds) {
		userService.saveUserRole(userId, roleIds);
		return XAdmindResult.build(XAdminCodeEnum.SUCCESS);
	}
	
	@ResponseBody
	@RequestMapping(value = "/locked", method = RequestMethod.POST)
	public XAdmindResult locked(long userId, int isLock) throws Exception{
		User user = new User();
		user.setUserId(userId);
		user.setIsLock(isLock);
		user.setUpdateAt(new Date());
		int reuslt = userService.update(user);
		return XAdmindResult.build(reuslt > 0 ? XAdminCodeEnum.SUCCESS : XAdminCodeEnum.ERROR);
	}
	
	@RequestMapping(value = "/reset/{account}-{userId}", method = RequestMethod.GET)
	public String showReset(@PathVariable String account, @PathVariable Long userId, ModelMap map) {
		map.put("userId", userId);
		map.put("account", account);
		return "admin/user/reset";
	}
	
	/**
	 * 修改密码
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/reset", method = RequestMethod.POST)
	public XAdmindResult reset(User user) throws Exception{
		User userNew = userService.find(user.getUserId());
		userNew.setPwd(MD5Utils.md5(user.getPwd()));
		userNew.setUpdateAt(new Date());
		int reuslt = userService.update(userNew);
		return XAdmindResult.build(reuslt > 0 ? XAdminCodeEnum.SUCCESS : XAdminCodeEnum.ERROR);
	}
	
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public XAdmindResult add(User user) throws Exception{
		user.setPwd(MD5Utils.md5(user.getPwd()));
		user.setIsAdmin(0);
		user.setIsLock(0);
		user.setCreateAt(new Date());
		user.setUpdateAt(new Date());
		int reuslt = userService.create(user);
		return XAdmindResult.build(reuslt > 0 ? XAdminCodeEnum.SUCCESS : XAdminCodeEnum.ERROR);
	}
	
	@ResponseBody
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public XAdmindResult del(long userId) throws Exception{
		int reuslt = userService.delete(userId);
		return XAdmindResult.build(reuslt > 0 ? XAdminCodeEnum.SUCCESS : XAdminCodeEnum.ERROR);
	}
	
}
