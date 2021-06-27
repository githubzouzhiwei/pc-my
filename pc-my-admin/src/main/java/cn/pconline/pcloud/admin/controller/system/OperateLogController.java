package cn.pconline.pcloud.admin.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import cn.pconline.framework.enums.XAdminCodeEnum;
import cn.pconline.framework.util.XAdmindResult;
import cn.pconline.pcloud.admin.service.system.OperateLogService;
import cn.pconline.pcloud.base.entity.system.OperateLog;
import cn.pconline.pcloud.base.form.OperateLogPageForm;

@Controller
@RequestMapping("/admin/operateLog")
public class OperateLogController {
	
	@Autowired
	OperateLogService operateLogService;
	
	@RequestMapping(value = "/index")
	public String index() {
		return "admin/operateLog/index";
	}
	
	@RequestMapping(value = { "/list" })
	@ResponseBody
	public XAdmindResult list(OperateLogPageForm operateLogPageForm) {
		PageInfo<OperateLog> pageInfo = operateLogService.pager(operateLogPageForm);
		return XAdmindResult.build(XAdminCodeEnum.SUCCESS, pageInfo.getList(), pageInfo.getTotal());
	}
}
