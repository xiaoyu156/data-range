package cn.ac.iie.zuul.bean;

import cn.ac.iie.zuul.bean.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @ClassName User
 * @Author tjh
 * @Date 18/7/26 下午3:52
 * @Version 1.0
 **/
@Entity
@Data
public class User extends BaseEntity {
    @Id
    @Column(name = "id", length = 128)
    @GenericGenerator(name = "my-uuid", strategy = "uuid")
    @GeneratedValue(generator = "my-uuid")
    private String id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "realname")
    private String realname;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "status")
    private Integer status;

    @Column(name = "last_login_time")
    private Instant lastLoginTime;

    @Column(name = "last_login_ip")
    private String lastLoginIp;

    @Column(name = "id_card")
    private String idCard;

    @Column(name = "email")
    private String email;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private Set<Role> roles = new HashSet<>();

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", realname='" + realname + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", status=" + status +
                ", lastLoginTime=" + lastLoginTime +
                ", lastLoginIp='" + lastLoginIp + '\'' +
                ", idCard='" + idCard + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(realname, user.realname) &&
                Objects.equals(phone, user.phone) &&
                Objects.equals(address, user.address) &&
                Objects.equals(status, user.status) &&
                Objects.equals(lastLoginTime, user.lastLoginTime) &&
                Objects.equals(lastLoginIp, user.lastLoginIp) &&
                Objects.equals(idCard, user.idCard) &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), id, username, password, realname, phone, address, status, lastLoginTime, lastLoginIp, idCard, email);
    }
}
