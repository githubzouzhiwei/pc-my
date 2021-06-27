package cn.pconline.pcloud.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import cn.pconline.framework.enums.XAdminCodeEnum;
import cn.pconline.framework.util.XAdmindResult;
import cn.pconline.pcloud.admin.service.UserOpenService;
import cn.pconline.pcloud.base.config.BaseProperties;
import cn.pconline.pcloud.base.entity.UserOpen;
import cn.pconline.pcloud.base.form.UserOpenPageForm;

@Controller
@RequestMapping("/admin/open")
public class UserOpenController {

	@Autowired
	UserOpenService userOpenService;
	@Autowired
	BaseProperties baseProperties;
	
	@RequestMapping(value = "/index")
	public String index() {
		return "admin/open/index";
	}
	
	@RequestMapping(value = { "/list" })
	@ResponseBody
	public XAdmindResult list(UserOpenPageForm pageForm) {
		PageInfo<UserOpen> pageInfo = userOpenService.pager(pageForm);
		return XAdmindResult.build(XAdminCodeEnum.SUCCESS, pageInfo.getList(), pageInfo.getTotal());
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public XAdmindResult doDel(Long id) {
		userOpenService.delete(id);
		return XAdmindResult.build(XAdminCodeEnum.SUCCESS);
	}
	
}
