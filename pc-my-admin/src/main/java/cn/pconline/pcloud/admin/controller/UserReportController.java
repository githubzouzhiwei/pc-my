package cn.pconline.pcloud.admin.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import cn.pconline.framework.enums.XAdminCodeEnum;
import cn.pconline.framework.util.StringUtils;
import cn.pconline.framework.util.XAdmindResult;
import cn.pconline.pcloud.admin.service.UserReportService;
import cn.pconline.pcloud.base.config.BaseProperties;
import cn.pconline.pcloud.base.entity.UserReport;
import cn.pconline.pcloud.base.entity.system.User;
import cn.pconline.pcloud.base.form.UserReportPageForm;
import cn.pconline.pcloud.base.util.DateUtil;
import cn.pconline.pcloud.base.util.FileUtil;

@Controller
@RequestMapping("/admin/report")
public class UserReportController {
	
	@Autowired
	UserReportService userReportService;
	@Autowired
	BaseProperties baseProperties;
	
	@RequestMapping(value = "/index")
	public String index() {
		return "admin/report/index";
	}
	
	@RequestMapping(value = { "/list" })
	@ResponseBody
	public XAdmindResult list(UserReportPageForm pageForm) {
		PageInfo<UserReport> pageInfo = userReportService.pager(pageForm);
		return XAdmindResult.build(XAdminCodeEnum.SUCCESS, pageInfo.getList(), pageInfo.getTotal());
	}
	
	/**
	 * 新增用户报告
	 * @return
	 */
	@RequestMapping(value = "/add")
	public String add() {
		return "admin/report/detail";
	}
	
	/**
	 * 编辑用户报告
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/edit/{id}")
	public String edit(@PathVariable Long id, HttpServletRequest req) {
		UserReport userReport = userReportService.find(id);
		req.setAttribute("formData", userReport);
		
		return "admin/report/detail";
	}
	
	/**
	 * 图片管理
	 * @param id
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/image")
	public String image(long id, HttpServletRequest req) {
		UserReport userReport = userReportService.find(id);
		
		req.setAttribute("id", id);
		req.setAttribute("pdf", userReport.getReportFile());
		req.setAttribute("pageSize", userReport.getPageSize());
		return "admin/report/image";
	}
	
	@ResponseBody
	@RequestMapping(value = "/imageList")
	public XAdmindResult imageList(String pdf, int pageSize, HttpServletRequest req) {
		List<JSONObject> list = new ArrayList<JSONObject>();
		if(!StringUtils.isBlank(pdf) && pageSize > 0) {
			pdf = pdf.replace(".pdf", "");
			String pre;
			JSONObject jo;
			for(int i=1; i<=pageSize; i++) {
				jo = new JSONObject();
				pre = i <= 9 ? "0" : "";
				jo.put("filename", pdf + "_" + pre + i);
				list.add(jo);
			}
		}
		
		return XAdmindResult.build(XAdminCodeEnum.SUCCESS, list, pageSize);
	}
	
	/**
	 * 保存
	 * @param userReport
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public XAdmindResult doSave(UserReport userReport) {
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		userReport.setUpdateAt(new Date());
		userReport.setUpdateBy(user.getName());
			
		if(userReport.getId() > 0) {
			UserReport userReportOld = userReportService.find(userReport.getId());
			int pageSize = userReportOld.getPageSize();
			if(!userReport.getReportFile().equals(userReportOld.getReportFile())) {
				pageSize = this.buildImage(userReport); // 需要重新生成图片
			}
			
			userReport.setPageSize(pageSize);
			userReportService.update(userReport);
		} else {
			int pageSize = this.buildImage(userReport); // 生成图片
			
			userReport.setStatus(1);
			userReport.setPageSize(pageSize);
			userReport.setCreateAt(new Date());
			userReport.setCreateBy(user.getName());
			userReportService.create(userReport);
		}
		
		return XAdmindResult.build(XAdminCodeEnum.SUCCESS);
	}
	
	/**
	 * 逻辑删除
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public XAdmindResult doDel(Long id) {
		UserReport userReport = userReportService.find(id);
		
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		userReport.setStatus(0);
		userReport.setUpdateAt(new Date());
		userReport.setUpdateBy(user.getName());
		userReportService.update(userReport);
		
		return XAdmindResult.build(XAdminCodeEnum.SUCCESS);
	}
	
	/**
	 * 上传文件[PDF]
	 * @param file
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public XAdmindResult doUpload(@RequestParam("file") MultipartFile file) {
		
		if (file.isEmpty()) {
			return XAdmindResult.build(XAdminCodeEnum.ERROR, "File is empty.");
        }

		String fileName = file.getOriginalFilename();
		String newFileName = DateUtil.format(new Date(), "yyMMddHHmmssSSS") + ".pdf";
		System.out.println(fileName + " --> " + newFileName);
		
		File dest = new File(baseProperties.getFilePath() + newFileName); // 物理路径
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
			file.transferTo(dest); // 文件写入
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			return XAdmindResult.build(XAdminCodeEnum.ERROR, "File writer exception!");
		}
		
		return XAdmindResult.build(XAdminCodeEnum.SUCCESS, newFileName); // 返回文件名
	}
	
	/**
	 * 生成图片[已停用]
	 * @param filename
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/toImage", method = RequestMethod.POST)
	public XAdmindResult toImage(long id) {
		UserReport userReport = userReportService.find(id);
		if(userReport == null || StringUtils.isBlank(userReport.getReportFile()) || userReport.getPageSize() > 0) {
			return XAdmindResult.build(XAdminCodeEnum.ERROR, "报告不存在或已生成过图片！");
		}
		
		// 生成多张jpg
		int pageSize = FileUtil.pdfToImg(baseProperties.getFilePath() + userReport.getReportFile(), "jpg");
		
		if(pageSize > 0) {
			// 转换生成webp
			String pre, inputFile, outputFile;
			boolean webpOk;
			for (int i = 1; i <= pageSize; i++) {
				pre = i <= 9 ? "_0" : "_";
				inputFile = baseProperties.getFilePath().replace("/pdf/", "/jpg/") + userReport.getReportFile().replace(".pdf", pre + i + ".jpg");
		        outputFile = baseProperties.getFilePath().replace("/pdf/", "/webp/") + userReport.getReportFile().replace(".pdf", pre + i + ".webp");
		        webpOk = FileUtil.imgToWebp(inputFile, outputFile, baseProperties.getFileQuality(), baseProperties.getFileCwebp());
		        
		        if(!webpOk) {
		        	return XAdmindResult.build(XAdminCodeEnum.ERROR, "生成webp图片失败！");
		        }
			}
			
			// 更新报告
			userReport.setPageSize(pageSize);
			userReportService.update(userReport);
			
			XAdmindResult xAdmindResult = XAdmindResult.build(XAdminCodeEnum.SUCCESS);
			xAdmindResult.setCount(pageSize);
			return xAdmindResult;
		}
		
		return XAdmindResult.build(XAdminCodeEnum.ERROR, "生成jpg图片失败！");
	}
	
	private int buildImage(UserReport userReport) {
		// 生成多张jpg
		int pageSize = FileUtil.pdfToImg(baseProperties.getFilePath() + userReport.getReportFile(), "jpg");
		
		if(pageSize > 0) {
			// 转换生成webp
			String pre, inputFile, outputFile;
			boolean webpOk;
			for (int i = 1; i <= pageSize; i++) {
				pre = i <= 9 ? "_0" : "_";
				inputFile = baseProperties.getFilePath().replace("/pdf/", "/jpg/") + userReport.getReportFile().replace(".pdf", pre + i + ".jpg");
		        outputFile = baseProperties.getFilePath().replace("/pdf/", "/webp/") + userReport.getReportFile().replace(".pdf", pre + i + ".webp");
		        webpOk = FileUtil.imgToWebp(inputFile, outputFile, baseProperties.getFileQuality(), baseProperties.getFileCwebp());
		        
		        if(!webpOk) {
		        	System.out.println("# 生成webp图片失败!");
		        	return 0;
		        }
			}
		}
		
		return pageSize;
	}
	
	/**
	 * 查询报告[测试]
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/get-{id}")
	public String getOpgReport(@PathVariable Long id) {
		UserReport userReport = userReportService.find(id);
		
		JSONObject resultJson = new JSONObject();
		resultJson.put("status", 0);
		resultJson.put("msg", "");
		resultJson.put("data", userReport);
		return resultJson.toJSONString();
	}
	
}
