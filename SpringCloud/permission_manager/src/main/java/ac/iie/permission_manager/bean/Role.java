package ac.iie.permission_manager.bean;

public class Role {
    private String id;

    private String roleName;

    public Role(String id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public Role() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }
}