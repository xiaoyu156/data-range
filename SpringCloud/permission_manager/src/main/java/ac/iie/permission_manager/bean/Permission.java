package ac.iie.permission_manager.bean;

public class Permission {
    private String id;

    private String permissionName;

    private String url;

    private String params;

    public Permission(String id, String permissionName, String url, String params) {
        this.id = id;
        this.permissionName = permissionName;
        this.url = url;
        this.params = params;
    }

    public Permission() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName == null ? null : permissionName.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params == null ? null : params.trim();
    }
}