package cn.pconline.pcloud.admin.service.system;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.pconline.framework.util.DateUtils;
import cn.pconline.framework.util.StringUtils;
import cn.pconline.pcloud.base.dao.system.OperateLogMapper;
import cn.pconline.pcloud.base.entity.system.OperateLog;
import cn.pconline.pcloud.base.entity.system.OperateLogExample;
import cn.pconline.pcloud.base.entity.system.Resource;
import cn.pconline.pcloud.base.form.OperateLogPageForm;
import cn.pconline.pcloud.base.service.AbstractService;
import cn.pconline.pcloud.base.util.HelperUtil;

/**
 * 操作日志服务类
 * @author jzc
 *
 */
@Service
public class OperateLogService extends AbstractService<OperateLog, OperateLogMapper> {

	protected OperateLogService() {
		super(OperateLog.class, OperateLogMapper.class);
	}
	
	@Autowired
	private ResourceService resourceService;
	
	@Autowired
	private OperateLogMapper operateLogMapper;

	/**
	 * 记录POST操作日志
	 * @param value (editor|uri|data)
	 */
	public void loggedPostOperate(String value){
		JSONObject json = JSONObject.parseObject(value);
		String account = json.get("account").toString();
		String uri = json.get("uri").toString();
		String params = json.get("params").toString();
		
		OperateLog operateLog = new OperateLog();
		operateLog.setUpdateTarget(uri);
		operateLog.setUpdateContent(params);
		operateLog.setCreateAt(new Date());
		operateLog.setCreateBy(account);
		
		if(uri.startsWith("/admin/login")) {
			operateLog.setModule("登录");
		} else {
			String[] dirs = uri.split("/");
			if(dirs.length < 4) {
				return;
			}
			String path = "/" + dirs[1] + "/" + dirs[2] + "/" + dirs[3];
			// 从资源表筛选数据
			Resource resource = resourceService.listResourceByUri(path);
			if(resource == null){
				path = "/" + dirs[1] + "/" + dirs[2] + "/";
				resource = resourceService.listResourceByUri(path);
				if(resource == null){
					System.out.println("## POST请求未能匹配资源：" + uri);
					operateLog.setModule("--");
				} else {
					String module = resource.getName().substring(0, resource.getName().length() - 2);
					String operate = dirs[3].toLowerCase();
					
					if(operate.contains("save")){
						module += "保存";
					} else if(operate.contains("create") || operate.contains("add") || operate.contains("insert")){
						module += "添加";
					} else if(operate.contains("update") || operate.contains("edit")){
						module += "编辑";
					} else if(operate.contains("status") || operate.contains("lock") || operate.contains("hide")){
						module += "审核";
					} else if(operate.contains("delete") || operate.contains("remove")){
						module += "删除";
					} else {
						module += operate;
					}
					operateLog.setModule(module);
				}
			} else {
				operateLog.setModule(resource.getName());
			}
		}
		
		// 记录日志
		this.create(operateLog);
	}
	
	/**
	 * 分页查询
	 * @param operateLogForm
	 * @return
	 */
	public PageInfo<OperateLog> pager(OperateLogPageForm operateLogForm) {
		OperateLogExample operateLogExample = new OperateLogExample();
		OperateLogExample.Criteria criteria = operateLogExample.createCriteria();
		if(StringUtils.isNotBlank(operateLogForm.getModule())){
			criteria.andModuleLike("%" + operateLogForm.getModule() + "%");
		}
		if(StringUtils.isNotBlank(operateLogForm.getCreateAts()) && operateLogForm.getCreateAts().length() > 3){
			String[] dates = operateLogForm.getCreateAts().split(" \\- ");
			criteria.andCreateAtGreaterThanOrEqualTo(DateUtils.parseDateDetail(dates[0] + " 00:00:00"));
			criteria.andCreateAtLessThanOrEqualTo(DateUtils.parseDateDetail(dates[1] + " 23:59:59"));
		}
		
		// 排序条件
		if(StringUtils.isNotBlank(operateLogForm.getField())){
			operateLogExample.setOrderByClause(HelperUtil.toUnderline(operateLogForm.getField()) + " " + operateLogForm.getOrder());
		} else {
			operateLogExample.setOrderByClause("create_at desc");
		}
		
		PageHelper.startPage(operateLogForm.getPage(), operateLogForm.getLimit());
		return new PageInfo<>(operateLogMapper.selectByExample(operateLogExample));
	}
	
}
