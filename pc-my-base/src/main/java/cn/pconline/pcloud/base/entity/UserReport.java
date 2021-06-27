package cn.pconline.pcloud.base.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.pconline.framework.util.StringUtils;

public class UserReport {
	
    private Long id;

    private String name;

    private Integer sex;

    private String mobile;

    private String testDate;

    private String reportFile;
    
    private Integer pageSize;

    private Integer score;

    private String title;

    private String intro;

    private Integer status;

    private Date createAt;

    private String createBy;

    private Date updateAt;

    private String updateBy;

    public UserReport(Long id, String name, Integer sex, String mobile, String testDate, String reportFile, Integer pageSize, Integer score,
    		String title, String intro, Integer status, Date createAt, String createBy, Date updateAt, String updateBy) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.mobile = mobile;
        this.testDate = testDate;
        this.reportFile = reportFile;
        this.pageSize = pageSize;
        this.score = score;
        this.title = title;
        this.intro = intro;
        this.status = status;
        this.createAt = createAt;
        this.createBy = createBy;
        this.updateAt = updateAt;
        this.updateBy = updateBy;
    }

    public UserReport() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getTestDate() {
        return testDate;
    }

    public void setTestDate(String testDate) {
        this.testDate = testDate;
    }

    public String getReportFile() {
        return reportFile;
    }

    public void setReportFile(String reportFile) {
        this.reportFile = reportFile == null ? null : reportFile.trim();
    }

    public Integer getPageSize() {
    	if(pageSize == null) {
    		pageSize = 0; 
    	}
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }
    
    public List<String> getWebpList(){
    	List<String> list = new ArrayList<String>();
    	if(!StringUtils.isBlank(reportFile) && pageSize != null && pageSize > 0) {
    		String pre, webp;
			for (int i = 1; i <= pageSize; i++) {
				pre = i <= 9 ? "_0" : "_";
				webp = "https://ex.5cy.cn/data/webp/" + reportFile.replace(".pdf", pre + i + ".webp");
				list.add(webp);
			}
    	}
    	return list;
    }
}