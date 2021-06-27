package cn.pconline.pcloud.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.pconline.framework.util.StringUtils;
import cn.pconline.pcloud.base.dao.UserOpenMapper;
import cn.pconline.pcloud.base.entity.UserOpen;
import cn.pconline.pcloud.base.entity.UserOpenExample;
import cn.pconline.pcloud.base.form.UserOpenPageForm;
import cn.pconline.pcloud.base.service.AbstractService;
import cn.pconline.pcloud.base.util.DateUtil;
import cn.pconline.pcloud.base.util.HelperUtil;

@Service
public class UserOpenService extends AbstractService<UserOpen, UserOpenMapper> {
	
	@Autowired
	UserOpenMapper userOpenMapper;

	public UserOpenService() {
		super(UserOpen.class, UserOpenMapper.class);
	}
	
	/**
	 * 分页查询
	 * @param pageForm
	 * @return
	 */
	public PageInfo<UserOpen> pager(UserOpenPageForm pageForm) {

		UserOpenExample example = new UserOpenExample();
		UserOpenExample.Criteria criteria = example.createCriteria();
		
		// 查询条件
		criteria.andStatusEqualTo(1);
		if(StringUtils.isNotBlank(pageForm.getName())){
			criteria.andNameLike("%" + pageForm.getName() + "%");
		}
		if(StringUtils.isNotBlank(pageForm.getCreateAts())){
			String[] dates = pageForm.getCreateAts().split(" - ");
			if(dates.length == 2) {
				criteria.andCreateAtBetween(DateUtil.parse(dates[0] + " 00:00:00"), DateUtil.parse(dates[1] + " 23:59:59"));
			}
		}
		
		// 排序条件
		if(StringUtils.isNotBlank(pageForm.getField())){
			example.setOrderByClause(HelperUtil.toUnderline(pageForm.getField()) + " " + pageForm.getOrder());
		} else {
			example.setOrderByClause("create_at desc");
		}
		
		// 分页查询
		PageHelper.startPage(pageForm.getPage(), pageForm.getLimit());
		return new PageInfo<>(userOpenMapper.selectByExample(example));
	}
	
	/**
	 * 查询授权绑定用户信息
	 * @param openid
	 * @return
	 */
	public UserOpen findByOpenid(String openid) {
		UserOpenExample example = new UserOpenExample();
		UserOpenExample.Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(1);
		criteria.andOpenidEqualTo(openid);
		
		List<UserOpen> list = userOpenMapper.selectByExample(example);
		if(list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
	
	public UserOpen findByMiniOpenid(String miniOpenid) {
		UserOpenExample example = new UserOpenExample();
		UserOpenExample.Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(1);
		criteria.andMiniOpenidEqualTo(miniOpenid);
		
		List<UserOpen> list = userOpenMapper.selectByExample(example);
		if(list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
	
	public UserOpen findByUnionid(String unionid) {
		UserOpenExample example = new UserOpenExample();
		UserOpenExample.Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(1);
		criteria.andUnionidEqualTo(unionid);
		
		List<UserOpen> list = userOpenMapper.selectByExample(example);
		if(list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 查询授权绑定用户信息
	 * @param name
	 * @param mobile
	 * @return
	 */
	public UserOpen find(String name, String mobile) {
		UserOpenExample example = new UserOpenExample();
		UserOpenExample.Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(1);
		criteria.andNameEqualTo(name);
		criteria.andMobileEqualTo(mobile);
		
		List<UserOpen> list = userOpenMapper.selectByExample(example);
		if(list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
	
}
