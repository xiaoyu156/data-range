package ac.iie.auth.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @ClassName Authority
 * @Author tjh
 * @Date 18/7/26 下午3:52
 * @Version 1.0
 **/

@Entity
@Data
public class Authority {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String value;
}
