package cn.pconline.pcloud.admin.service.system;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.pconline.framework.util.StringUtils;
import cn.pconline.pcloud.base.dao.system.UserMapper;
import cn.pconline.pcloud.base.dao.system.UserRoleMapper;
import cn.pconline.pcloud.base.entity.system.User;
import cn.pconline.pcloud.base.entity.system.UserExample;
import cn.pconline.pcloud.base.entity.system.UserRoleExample;
import cn.pconline.pcloud.base.entity.system.UserRoleKey;
import cn.pconline.pcloud.base.form.UserPageForm;
import cn.pconline.pcloud.base.service.AbstractService;
import cn.pconline.pcloud.base.util.HelperUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Transactional(propagation = Propagation.REQUIRED)
@Service
public class UserService extends AbstractService<User, UserMapper> {

	protected UserService() {
		super(User.class, UserMapper.class);
	}

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;

	public User findByAccount(String account) {
		UserExample example = new UserExample();
		UserExample.Criteria criteria = example.createCriteria();
		criteria.andAccountEqualTo(account);
		
		List<User> list = userMapper.selectByExample(example);
		
		return list != null && !list.isEmpty() ? list.get(0) : null;
	}
	
	/**
	 * 超级管理员首次登录时保存账号
	 * @param map
	 */
	public void saveAdmin4Login(Map<String, Object> map){
		Long userId = (Long) map.get("userId");
		User user = userMapper.selectByPrimaryKey(userId);
		
		if(user == null){
			user = new User();
			user.setUserId(userId);
			user.setAccount(map.get("account").toString());
			user.setName(map.get("name").toString());
			user.setDepartment(map.get("department") == null ? "" : map.get("department").toString());
			user.setIsAdmin(1);
			user.setIsLock(0);
			user.setCreateAt(new Date());
			user.setUpdateAt(new Date());
			
			userMapper.insert(user);
		} else if(user.getIsAdmin() != 1){
			user.setIsAdmin(1);
			user.setUpdateAt(new Date());
			
			userMapper.updateByPrimaryKey(user);
		}
	}
	
	/**
	 * 分页查询
	 * @param resourcePageForm
	 * @return
	 */
	public PageInfo<User> pager(UserPageForm userPageForm) {
		UserExample userExample = new UserExample();
		UserExample.Criteria criteria = userExample.createCriteria();
		// 查询条件
		if(StringUtils.isNotBlank(userPageForm.getAccount())){
			criteria.andAccountLike("%" + userPageForm.getAccount() + "%");
		}
		if(StringUtils.isNotBlank(userPageForm.getDepartment())){
			criteria.andDepartmentLike("%" + userPageForm.getDepartment() + "%");
		}
		
		// 排序条件
		if(StringUtils.isNotBlank(userPageForm.getField())){
			userExample.setOrderByClause(HelperUtil.toUnderline(userPageForm.getField()) + " " + userPageForm.getOrder());
		} else {
			userExample.setOrderByClause("update_at desc");
		}
		
		// 分页查询(拦截器)
		PageHelper.startPage(userPageForm.getPage(), userPageForm.getLimit());
		return new PageInfo<>(userMapper.selectByExample(userExample));
	}

	/**
	 * 批量保存关联角色
	 * @param userId
	 * @param roleIds
	 */
	public void saveUserRole(long userId, Long[] roleIds){
		UserRoleExample userRoleExample = new UserRoleExample();
		UserRoleExample.Criteria criteria = userRoleExample.createCriteria();
		criteria.andUserIdEqualTo(userId);
		userRoleMapper.deleteByExample(userRoleExample);
		
		if(roleIds != null){
			for (Long roleId : roleIds) {
				userRoleMapper.insert(new UserRoleKey(userId, roleId));
			}
		}
	}
	
}
