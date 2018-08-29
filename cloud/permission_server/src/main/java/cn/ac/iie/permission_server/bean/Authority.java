package cn.ac.iie.permission_server.bean;

import cn.ac.iie.permission_server.bean.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @ClassName Authority
 * @Author tjh
 * @Date 18/7/26 下午3:52
 * @Version 1.0
 **/

@Entity
@Data
public class Authority extends BaseEntity implements Serializable {
    @Id
    @Column(name = "id", length = 128)
    @GenericGenerator(name = "my-uuid", strategy = "uuid")
    @GeneratedValue(generator = "my-uuid")
    private String id;
    private String name;
    private String permissionUrl;
    private String method;
    private String params;

    @JsonIgnore
    @ManyToMany(mappedBy = "authorities")
    private Set<Role> roles = new HashSet<>();

    @Override
    public String toString() {
        return "Authority{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", permissionUrl='" + permissionUrl + '\'' +
                ", method='" + method + '\'' +
                ", params='" + params + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Authority authority = (Authority) o;
        return Objects.equals(id, authority.id) &&
                Objects.equals(name, authority.name) &&
                Objects.equals(permissionUrl, authority.permissionUrl) &&
                Objects.equals(method, authority.method) &&
                Objects.equals(params, authority.params);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), id, name, permissionUrl, method, params);
    }
}
