package cn.pconline.pcloud.admin.service.system;

import java.util.List;

import cn.pconline.framework.util.StringUtils;
import cn.pconline.pcloud.base.dao.system.ResourceMapper;
import cn.pconline.pcloud.base.dao.system.RoleMapper;
import cn.pconline.pcloud.base.entity.system.Resource;
import cn.pconline.pcloud.base.entity.system.Role;
import cn.pconline.pcloud.base.entity.system.RoleExample;
import cn.pconline.pcloud.base.entity.system.User;
import cn.pconline.pcloud.base.form.RolePageForm;
import cn.pconline.pcloud.base.service.AbstractService;
import cn.pconline.pcloud.base.util.HelperUtil;
import cn.pconline.pcloud.base.util.RedisKey;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends AbstractService<Role,RoleMapper>{

	@Autowired
	RoleMapper roleMapper;
	
	@Autowired
	ResourceMapper resourceMapper;
	
	public RoleService() {
		super(Role.class,RoleMapper.class);
	}

	public List<Role> list(long userId) {
		return roleMapper.selectByUserId(userId);
	}
	
	public List<Role> list() {
		RoleExample roleExample = new RoleExample();
		RoleExample.Criteria criteria = roleExample.createCriteria();
		criteria.andStatusEqualTo(1);
		return roleMapper.selectByExample(roleExample);
	}

	/**
	 * 获取关联角色
	 * @param user
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Role> list4Login(User user){
		String key = baseProperties.getApplicationName() + RedisKey.REDIS_MY_ROLES + user.getUserId();

		Object rdObj = redisTemplateUtil.get(key);
		if(rdObj != null){
			return (List<Role>) rdObj;
		}

		// 获取账号关联角色
		List<Role> list;
		
		if(user.getIsAdmin() == 1){
			 // 超级管理员默认拥有所有角色和资源权限
			list = this.list();
			if(list != null && !list.isEmpty()){
				Role role = list.get(0);
				role.setRelResourceList( resourceMapper.selectByExample(null) );
			} else {
				Role role = new Role();
				role.setRoleId(1L);
				role.setRoleKey("Admin");
				role.setRelResourceList( resourceMapper.selectByExample(null) );
				list.add(role);
			}
		} else {
			list = roleMapper.selectByUserId(user.getUserId());
			// 遍历加载关联资源
			if(list != null){
				for (Role role : list) {
					role.setRelResourceList( selectByRoleId(role.getRoleId()) );
				}
			}
		}
		
		// 设置缓存一小时
		redisTemplateUtil.set(key, list, 3600);
		
		return list;
	}
	
	/**
	 * 查询角色关联的资源
	 * @param roleId
	 * @return
	 */
	public List<Resource> selectByRoleId(long roleId){
		return resourceMapper.selectByRoleId(roleId);
	}

	public PageInfo<Role> pager(RolePageForm rolePageForm) {

		//使用扩展类查询
		RoleExample roleExample = new RoleExample();
		RoleExample.Criteria criteria = roleExample.createCriteria();
		if(StringUtils.isNotBlank(rolePageForm.getName())){
			criteria.andNameLike("%" + rolePageForm.getName() + "%");
		}
		
		// 排序条件
		if(StringUtils.isNotBlank(rolePageForm.getField())){
			roleExample.setOrderByClause(HelperUtil.toUnderline(rolePageForm.getField()) + " " + rolePageForm.getOrder());
		} else {
			roleExample.setOrderByClause("update_at desc");
		}
		
		PageHelper.startPage(rolePageForm.getPage(), rolePageForm.getLimit());
		return new PageInfo<>(roleMapper.selectByExample(roleExample));
	}
	
	/**
	 * 名称是否已存在
	 * @param role
	 * @return
	 */
	public boolean existName(Role role){
		RoleExample roleExample = new RoleExample();
		RoleExample.Criteria criteria = roleExample.createCriteria();
		criteria.andStatusEqualTo(1);
		criteria.andNameEqualTo(role.getName());
		if(role.getRoleId() != null && role.getRoleId() > 0){
			criteria.andRoleIdNotEqualTo(role.getRoleId());
		}
		int count = roleMapper.countByExample(roleExample);
		return count > 0;
	}
	
	/**
	 * 账号是否已存在
	 * @param role
	 * @return
	 */
	public boolean existRoleKey(Role role){
		RoleExample roleExample = new RoleExample();
		RoleExample.Criteria criteria = roleExample.createCriteria();
		criteria.andStatusEqualTo(1);
		criteria.andRoleKeyEqualTo(role.getRoleKey());
		if(role.getRoleId() != null && role.getRoleId() > 0){
			criteria.andRoleIdNotEqualTo(role.getRoleId());
		}
		int count = roleMapper.countByExample(roleExample);
		return count > 0;
	}

}
