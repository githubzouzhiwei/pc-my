package cn.pconline.pcloud.admin.service.system;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.pconline.framework.util.StringUtils;
import cn.pconline.pcloud.base.dao.system.ResourceMapper;
import cn.pconline.pcloud.base.dao.system.RoleResourceMapper;
import cn.pconline.pcloud.base.entity.system.Resource;
import cn.pconline.pcloud.base.entity.system.ResourceExample;
import cn.pconline.pcloud.base.entity.system.Role;
import cn.pconline.pcloud.base.entity.system.RoleResourceExample;
import cn.pconline.pcloud.base.entity.system.RoleResourceKey;
import cn.pconline.pcloud.base.entity.system.User;
import cn.pconline.pcloud.base.form.ResourcePageForm;
import cn.pconline.pcloud.base.service.AbstractService;
import cn.pconline.pcloud.base.util.HelperUtil;

@Service
public class ResourceService extends AbstractService<Resource, ResourceMapper> {

	protected ResourceService() {
		super(Resource.class, ResourceMapper.class);
	}

	@Autowired
	private RoleResourceMapper roleResourceMapper;

	@Autowired
	private ResourceMapper resourceMapper;
	
	@Autowired
	private RoleService roleService;
	
	/**
	 * 分页查询
	 * @param resourcePageForm
	 * @return
	 */
	public PageInfo<Resource> pager(ResourcePageForm resourcePageForm) {
		ResourceExample resourceExample = new ResourceExample();
		ResourceExample.Criteria criteria = resourceExample.createCriteria();
		// 查询条件
		if(StringUtils.isNotBlank(resourcePageForm.getName())){
			criteria.andNameLike("%" + resourcePageForm.getName() + "%");
		}
		if(resourcePageForm.getParentId() != null && resourcePageForm.getParentId() > 0){
			criteria.andParentIdEqualTo(resourcePageForm.getParentId());
		}
		// 排序条件
		if(StringUtils.isNotBlank(resourcePageForm.getField())){
			resourceExample.setOrderByClause(HelperUtil.toUnderline(resourcePageForm.getField()) + " " + resourcePageForm.getOrder());
		} else {
			resourceExample.setOrderByClause("type asc, sort asc");
		}
		
		// 分页查询(拦截器)
		PageHelper.startPage(resourcePageForm.getPage(), resourcePageForm.getLimit());
		return new PageInfo<>(resourceMapper.selectByExample(resourceExample));
	}

	/**
	 * 获取所有资源
	 * @param isMenu 是否菜单项
	 * @return
	 */
	public List<Resource> getAllResource(boolean isMenu){
		ResourceExample resourceExample = new ResourceExample();
		ResourceExample.Criteria criteria = resourceExample.createCriteria();

		criteria.andTypeEqualTo(0);
		criteria.andIsHideEqualTo(0);
		resourceExample.setOrderByClause("sort");

		List<Resource> resources = resourceMapper.selectByExample(resourceExample); // 一级
		List<Resource> childResources;

		// 遍历加载子集菜单
		for (Resource resource : resources) {
			if(isMenu) {
				childResources = getResourceByParentId(resource.getResourceId(), 0, isMenu); // 根据账号角色权限来获取二级菜单
			} else {
				childResources = getResourceByParentId(resource.getResourceId()); // 二级
			}
			
			if(childResources != null && !childResources.isEmpty()){
				resource.setChildResourceList(childResources);
				
				// 遍历加载操作权限细项
				if(!isMenu){
					for (Resource childResource : childResources) {
						childResource.setChildResourceList( getResourceByParentId(childResource.getResourceId()) ); // 三级
					}
				}
			}
		}
		return resources;
	}
	
	/**
	 * 获取完整菜单列表
	 * @return
	 */
	public List<Resource> getAllResource(){
		List<Resource> list = new ArrayList<>();
		List<Resource> resources = getResourceByParentId(0, -1); // resourceMapper.selectByExample(resourceExample); // 一级
		List<Resource> childResources, functionResources;

		// 遍历一级
		for (Resource resource : resources) {
			resource.setName("<b>" + resource.getName() + "</b>");
			list.add(resource);
			
			childResources = getResourceByParentId(resource.getResourceId(), -1); // 二级
			
			if(childResources != null && !childResources.isEmpty()){
				// 遍历二级菜单
				for (Resource childResource : childResources) {
					childResource.setName("<font color='#0C61DA'>└ " + childResource.getName() + "</font>");
					list.add(childResource);
					
					functionResources = getResourceByParentId(childResource.getResourceId(), -1); // 三级
					// 遍历三级功能项
					for (Resource function : functionResources) {
						function.setName("<font color='#1E9FFF'>└─ " + function.getName() + "</font>");
						list.add(function);
					}
				}
			}
		}
		return list;
	}
	
	/**
	 * 获取子集资源
	 * @return
	 */
	public List<Resource> getResourceByParentId(long parentId){
		return getResourceByParentId(parentId, 0);
	}
	
	public List<Resource> getResourceByParentId(long parentId, int isHide){
		ResourceExample resourceExample = new ResourceExample();
		ResourceExample.Criteria criteria = resourceExample.createCriteria();

		criteria.andParentIdEqualTo(parentId);
		if(isHide >= 0) {
			criteria.andIsHideEqualTo(isHide);
		}
		resourceExample.setOrderByClause("sort");

		return resourceMapper.selectByExample(resourceExample);
	}
	
	/**
	 * 获取框架页菜单
	 * @param parentId
	 * @param isHide
	 * @param isMenu
	 * @return
	 */
	public List<Resource> getResourceByParentId(long parentId, int isHide, boolean isMenu){
		List<Role> roleList = null;
		User user = null;
		if(isMenu) {
			Subject subject = SecurityUtils.getSubject();
			user = (User) subject.getPrincipal();
			if(user == null) {
				return null;
			}
			if(user.getIsAdmin() != 1) {
				roleList = roleService.list4Login(user);
			}
		}
		
		ResourceExample resourceExample = new ResourceExample();
		ResourceExample.Criteria criteria = resourceExample.createCriteria();

		criteria.andParentIdEqualTo(parentId);
		if(isHide >= 0) {
			criteria.andIsHideEqualTo(isHide);
		}
		resourceExample.setOrderByClause("sort");

		List<Resource> resourceList = resourceMapper.selectByExample(resourceExample);
		List<Resource> resourceList2 = null;
		
		// 过滤掉没有权限的资源菜单
		if(isMenu && user != null && user.getIsAdmin() != 1 && roleList != null && !roleList.isEmpty()) {
			resourceList2 = new ArrayList<>();
			
			for (Resource resource : resourceList) {
				for (Role role : roleList) {
					boolean isBreak = false;
					for (Resource roleResouce : role.getRelResourceList()) {
						if(resource.getResourceId().equals(roleResouce.getResourceId())) {
							resourceList2.add(resource); // 添加有权限的菜单
							isBreak = true;
							break;
						}
					}
					if(isBreak) {
						break;
					}
				}
			}
			
			return resourceList2;
		}
		
		return resourceList;
	}

	public void modifyRoleResource(Long roleId, String resourceIdsStr) {
		// 先删除角色下的资源
		RoleResourceExample rre = new RoleResourceExample();
		RoleResourceExample.Criteria criteria = rre.createCriteria();
		criteria.andRoleIdEqualTo(roleId);
		roleResourceMapper.deleteByExample(rre);
		
		String[] resourceIds = resourceIdsStr.split(",");
		RoleResourceKey rr;
		long resourceId;
		Resource resource;
		for (String rid : resourceIds) {
			resourceId = Long.parseLong(rid);
			resource = this.find(resourceId);
			if(resource.getType() == 0){
				continue; // 不存储一级资源（因没有实际Url对应关系）
			}
			
			rr = new RoleResourceKey();
			rr.setRoleId(roleId);
			rr.setResourceId(resourceId);
			
			roleResourceMapper.insert(rr);
		}
	}

	public List<JSONObject> treeResource(List<Resource> resources,List<Long> resourceIds){
		List<JSONObject> result = new ArrayList<>();
		if(resources!=null && !resources.isEmpty()){
			
			for (Resource resource : resources) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", resource.getResourceId());
				jsonObject.put("title", resource.getName());
				
				if(resourceIds!=null && !resourceIds.isEmpty()){
					if((resource.getChildResourceList() == null || resource.getChildResourceList().isEmpty()) && resourceIds.contains(resource.getResourceId())){
						jsonObject.put("checked", true);
					}
				}
				result.add(jsonObject);
				if(resource.getChildResourceList()!=null && !resource.getChildResourceList().isEmpty()){
					if(resource.getType() == 0){
						jsonObject.put("spread", true);
					}
					jsonObject.put("children", treeResource(resource.getChildResourceList(), resourceIds));
				}
			}
		}
		return result;
	}

	public List<Resource> list(ResourceExample example){
		return resourceMapper.selectByExample(example);
	}
	
	public List<Resource> list(){
		ResourceExample example = new ResourceExample();
		ResourceExample.Criteria criteria = example.createCriteria();
		criteria.andIsHideEqualTo(0);
		
		return resourceMapper.selectByExample(example);
	}
	
	public Resource listResourceByUri(String path){
		ResourceExample resourceExample = new ResourceExample();
		ResourceExample.Criteria criteria = resourceExample.createCriteria();
		criteria.andSourceUrlLike(path + "%");
		
		List<Resource> list = resourceMapper.selectByExample(resourceExample);
		
		if(list != null && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}
	
}
