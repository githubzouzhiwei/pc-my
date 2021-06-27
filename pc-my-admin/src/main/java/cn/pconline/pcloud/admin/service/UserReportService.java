package cn.pconline.pcloud.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.pconline.framework.util.StringUtils;
import cn.pconline.pcloud.base.dao.UserReportMapper;
import cn.pconline.pcloud.base.entity.UserReport;
import cn.pconline.pcloud.base.entity.UserReportExample;
import cn.pconline.pcloud.base.form.UserReportPageForm;
import cn.pconline.pcloud.base.service.AbstractService;
import cn.pconline.pcloud.base.util.DateUtil;
import cn.pconline.pcloud.base.util.HelperUtil;

@Service
public class UserReportService extends AbstractService<UserReport, UserReportMapper> {

	@Autowired
	UserReportMapper userReportMapper;

	public UserReportService() {
		super(UserReport.class, UserReportMapper.class);
	}
	
	/**
	 * 分页查询
	 * @param pageForm
	 * @return
	 */
	public PageInfo<UserReport> pager(UserReportPageForm pageForm) {

		UserReportExample example = new UserReportExample();
		UserReportExample.Criteria criteria = example.createCriteria();
		
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
		return new PageInfo<>(userReportMapper.selectByExample(example));
	}
	
	/**
	 * 查询用户报告
	 * @param name
	 * @param mobile
	 * @param testDate
	 * @return
	 */
	public List<UserReport> list(String name, String mobile, String testDate) {
		UserReportExample example = new UserReportExample();
		UserReportExample.Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(1);
		criteria.andNameEqualTo(name);
		criteria.andMobileEqualTo(mobile);
		if(!StringUtils.isBlank(testDate)) {
			criteria.andTestDateEqualTo(testDate);
		}
		// 排序+分页
		example.setOrderByClause("test_date desc, create_at desc LIMIT 100");
		
		return userReportMapper.selectByExample(example);
	}
	
	public List<UserReport> list(String name, String mobile) {
		return this.list(name, mobile, null);
	}
	
}
