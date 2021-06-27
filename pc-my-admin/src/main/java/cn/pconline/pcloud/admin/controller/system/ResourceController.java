package cn.pconline.pcloud.admin.controller.system;

import java.util.*;

import cn.pconline.framework.enums.XAdminCodeEnum;
import cn.pconline.framework.util.XAdmindResult;
import cn.pconline.pcloud.admin.service.system.ResourceService;
import cn.pconline.pcloud.admin.service.system.ShiroFilterService;
import cn.pconline.pcloud.base.entity.system.Resource;
import cn.pconline.pcloud.base.entity.system.ResourceExample;
import cn.pconline.pcloud.base.entity.system.User;
import cn.pconline.pcloud.base.util.ZTreeObjectUtil;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/resource")
public class ResourceController {
	@Autowired
	private ResourceService resourceService;
	
	@Autowired
	private ShiroFilterService shiroFilterService;

    @RequestMapping("/index")
    public String index(Model model) {
    	// 父级菜单
    	List<Resource> pResourceList = resourceService.getAllResource(true);
		model.addAttribute("pResourceList", pResourceList);
    	
        return "admin/resource/index";
    }

    @RequestMapping("/list")
    @ResponseBody
    public XAdmindResult list() {
    	List<Resource> resources = resourceService.getAllResource();
        return XAdmindResult.build(XAdminCodeEnum.SUCCESS, resources, resources.size());
    }
   
    @RequestMapping("/form/{resourceId}")
    public String form(@PathVariable Long resourceId, Model model){
        if(resourceId != null && resourceId > 0){
            model.addAttribute("formData", resourceService.find(resourceId));
        }
        return "admin/resource/form";
    }
    
    @RequestMapping(value= {"/save"}, method = RequestMethod.POST)
	@ResponseBody
	public XAdmindResult save(Resource resource, ModelMap map){
    	int result = 0;
    	
    	resource.setUpdateAt(new Date());
		// 根据父ID推算出类型
		if(resource.getParentId() > 0){
			Resource pr = resourceService.find(resource.getParentId());
			resource.setType(pr.getType() + 1);
		} else {
			resource.setType(0);
		}
		
		if(resource.getResourceId() != null && resource.getResourceId() > 0){
			result = resourceService.update(resource);
		} else {
			resource.setCreateAt(new Date());
			result = resourceService.create(resource);
		}
		shiroFilterService.resetFilterChain(); // 重新设置授权
	
		return XAdmindResult.build(result > 0 ? XAdminCodeEnum.SUCCESS : XAdminCodeEnum.ERROR);
	}
    
    @RequestMapping("treeResources")
    @ResponseBody
    public ZTreeObjectUtil.Ztree treeResources(){
    	ResourceExample example = new ResourceExample();
		ResourceExample.Criteria criteria = example.createCriteria();
		criteria.andTypeLessThan(2);
		criteria.andIsHideEqualTo(0);
		example.setOrderByClause("sort");
		List<Resource> resources = resourceService.list(example);
    	
        ZTreeObjectUtil zTreeObjectUtil = ZTreeObjectUtil.getInstance();
        List<ZTreeObjectUtil.SimpleData> datas = new ArrayList<>();
        
        for(Resource r : resources){
            datas.add( zTreeObjectUtil.new SimpleData(String.valueOf(r.getResourceId()), r.getName(),
            		false, true, String.valueOf(r.getParentId())) );
        }
        return zTreeObjectUtil.getByRoot(zTreeObjectUtil.new Ztree("0", "无", false, true), datas);
    }
   
    @RequestMapping(value = "/delete/{resourceId}", method = RequestMethod.POST)
    @ResponseBody
    public XAdmindResult delete(@PathVariable Long resourceId, ModelMap map) {
    	Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		if(user.getIsAdmin() != 1){
			return XAdmindResult.build(XAdminCodeEnum.NOT_PERMIT, "您不是超级管理员，禁止该操作！");
		}
    	
    	int result = resourceService.delete(resourceId);
    	shiroFilterService.resetFilterChain(); // 重新设置授权
        return XAdmindResult.build(result > 0 ? XAdminCodeEnum.SUCCESS : XAdminCodeEnum.ERROR);
    }
    
    @RequestMapping("deleteBatch")
    @ResponseBody
    public XAdmindResult deleteBatch(@RequestParam(value = "ids[]") Long[] ids){
    	Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		if(user.getIsAdmin() != 1){
			return XAdmindResult.build(XAdminCodeEnum.NOT_PERMIT, "您不是超级管理员，禁止该操作！");
		}
		for (Long id : ids) {
			resourceService.delete(id);
		}
    	shiroFilterService.resetFilterChain(); // 重新设置授权
        return XAdmindResult.build(XAdminCodeEnum.SUCCESS);
    }
    
    @RequestMapping("updateIsHide")
    @ResponseBody
    public XAdmindResult updateIsHide(long resourceId, int isHide){
    	Resource resource = new Resource();
		resource.setResourceId(resourceId);
		resource.setIsHide(isHide);
		resource.setUpdateAt(new Date());
		int result = resourceService.update(resource);
    	
    	shiroFilterService.resetFilterChain(); // 重新设置授权
        return XAdmindResult.build(result > 0 ? XAdminCodeEnum.SUCCESS : XAdminCodeEnum.ERROR);
    }
}
