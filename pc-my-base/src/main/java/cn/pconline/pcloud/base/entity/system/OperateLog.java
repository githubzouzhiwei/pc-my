package cn.pconline.pcloud.base.entity.system;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class OperateLog {
	@Id
    private Long operateLogId;

    private String module;

    private String updateTarget;

    private String updateContent;

    private Date createAt;

    private String createBy;

    public OperateLog(Long operateLogId, String module, String updateTarget, String updateContent, Date createAt, String createBy) {
        this.operateLogId = operateLogId;
        this.module = module;
        this.updateTarget = updateTarget;
        this.updateContent = updateContent;
        this.createAt = createAt;
        this.createBy = createBy;
    }

    public OperateLog() {
        super();
    }

    public Long getOperateLogId() {
        return operateLogId;
    }

    public void setOperateLogId(Long operateLogId) {
        this.operateLogId = operateLogId;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module == null ? null : module.trim();
    }

    public String getUpdateTarget() {
        return updateTarget;
    }

    public void setUpdateTarget(String updateTarget) {
        this.updateTarget = updateTarget == null ? null : updateTarget.trim();
    }

    public String getUpdateContent() {
        return updateContent;
    }

    public void setUpdateContent(String updateContent) {
        this.updateContent = updateContent == null ? null : updateContent.trim();
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
}