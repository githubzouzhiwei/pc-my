package cn.pconline.pcloud.admin.controller.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.pconline.pcloud.admin.service.system.ResourceService;
import cn.pconline.pcloud.admin.service.system.RoleService;
import cn.pconline.pcloud.base.entity.system.Resource;
import cn.pconline.pcloud.base.entity.system.Role;
import cn.pconline.pcloud.base.form.RolePageForm;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import cn.pconline.framework.enums.XAdminCodeEnum;
import cn.pconline.framework.util.XAdmindResult;

@Controller
@RequestMapping("/admin/role")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@Autowired
	private ResourceService resourceService;

	@RequestMapping(value = { "/list" })
	@ResponseBody
	public XAdmindResult list(RolePageForm rolePageForm) {
		PageInfo<Role> pageInfo = roleService.pager(rolePageForm);
		return XAdmindResult.build(XAdminCodeEnum.SUCCESS, pageInfo.getList(), pageInfo.getTotal());
	}

	@RequestMapping(value = "/index")
	public String index() {
		return "admin/role/index";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap map) {
		return "admin/role/add";
	}

	@RequestMapping(value = "/edit/{roleId}", method = RequestMethod.GET)
	public String edit(@PathVariable Long roleId, Model model) {
		Role role = roleService.find(roleId);
		model.addAttribute("role",role);
		return "admin/role/edit";
	}
	
	@RequestMapping(value = "/doAdd", method = RequestMethod.POST)
	@ResponseBody
	public XAdmindResult doAdd(Role role) {
		if(roleService.existName(role)){
			return XAdmindResult.build(XAdminCodeEnum.ERROR, "角色名称已存在！");
		}
		if(roleService.existRoleKey(role)){
			return XAdmindResult.build(XAdminCodeEnum.ERROR, "角色KEY已存在！");
		}
		
		role.setStatus(1);
		role.setCreateAt(new Date());
		role.setUpdateAt(new Date());
		int result = roleService.create(role);
		return XAdmindResult.build(result > 0 ? XAdminCodeEnum.SUCCESS : XAdminCodeEnum.ERROR, String.valueOf(result));
	}
	
	@RequestMapping(value = "/doEdit", method = RequestMethod.POST)
	@ResponseBody
	public XAdmindResult doEdit(Role role) {
		if(roleService.existName(role)){
			return XAdmindResult.build(XAdminCodeEnum.ERROR, "角色名称已存在！");
		}
		if(roleService.existRoleKey(role)){
			return XAdmindResult.build(XAdminCodeEnum.ERROR, "角色KEY已存在！");
		}
		
		role.setUpdateAt(new Date());
		int reuslt = roleService.update(role);
		return XAdmindResult.build(reuslt > 0 ? XAdminCodeEnum.SUCCESS : XAdminCodeEnum.ERROR);
	}
	
	@RequestMapping(value = "/roleResource/{roleId}", method = RequestMethod.GET)
	public String resourceByRole(@PathVariable Long roleId, Model model){
		List<Resource> allResource = resourceService.getAllResource(false);
		List<Resource> relResourceList = roleService.selectByRoleId(roleId);
		List<Long> resourceIds = new ArrayList<>();
		if(relResourceList != null){
			for (Resource resource : relResourceList) {
				resourceIds.add(resource.getResourceId());
			}
		}
		List<JSONObject> jsonObjects = resourceService.treeResource(allResource, resourceIds);
		model.addAttribute("data", JSONObject.toJSONString(jsonObjects));
		model.addAttribute("roleId", roleId);
		return "admin/role/roleResource";
	}
	
	@RequestMapping(value = "/roleResouceEdit", method = RequestMethod.POST)
	@ResponseBody
	public XAdmindResult roleResouceEdit(@RequestParam(value = "roleId", defaultValue = "0") Long roleId,
			@RequestParam(value = "resourceIds", defaultValue = "0") String resourceIds){
		resourceService.modifyRoleResource(roleId, resourceIds);
		
		return XAdmindResult.build(XAdminCodeEnum.SUCCESS);
	}

	@RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
	@ResponseBody
	public XAdmindResult updateStatus(long roleId, int status){
		Role role = new Role();
		role.setRoleId(roleId);
		role.setStatus(status);
		role.setUpdateAt(new Date());
		int reuslt = roleService.update(role);
		return XAdmindResult.build(reuslt > 0 ? XAdminCodeEnum.SUCCESS : XAdminCodeEnum.ERROR);
	}
	
}
