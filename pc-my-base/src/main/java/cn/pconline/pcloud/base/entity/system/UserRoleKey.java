package cn.pconline.pcloud.base.entity.system;

public class UserRoleKey {
    private Long userId;

    private Long roleId;

    public UserRoleKey(Long userId, Long roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    public UserRoleKey() {
        super();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}