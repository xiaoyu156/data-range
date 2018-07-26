package ac.iie.auth.domain.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.Instant;
import java.util.Date;

/**
 * @ClassName BaseEntity
 * @Author tjh
 * @Date 18/7/26 下午3:52
 * @Version 1.0
 **/
@MappedSuperclass
@Data
public class BaseEntity {
    @Column(name = "create_time", updatable = false, nullable = false)
    protected Instant createTime;

    @Column(name = "update_time", nullable = false)
    @JsonIgnore
    protected Instant updateTime;

    @PrePersist
    public void prePersist() {
        createTime = updateTime = Instant.now();
    }
    @PreUpdate
    public void preUpdate() {
        updateTime = Instant.now();
    }
}
