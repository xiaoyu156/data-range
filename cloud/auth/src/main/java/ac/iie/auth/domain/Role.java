package ac.iie.auth.domain;

import ac.iie.auth.domain.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName Role
 * @Author tjh
 * @Date 18/7/26 下午3:52
 * @Version 1.0
 **/
@Entity
@Data
public class Role extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String value;

    @JsonIgnore
    @ManyToMany(targetEntity =Authority.class,fetch = FetchType.EAGER)
    @BatchSize(size = 20)
    private Set<Authority> authorities = new HashSet<>();
}
