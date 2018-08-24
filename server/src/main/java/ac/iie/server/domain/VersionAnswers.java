package ac.iie.server.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class VersionAnswers implements Serializable {
    private String id;

    private String userCompId;

    private String version;

    private String resultUrl;

    private Date uploadTime;

    private Integer status;

    private String detail;

    private Integer resource;

    private String runCommand;

    private String compId;

    private String score;

    private Integer type;

    private String imageUrl;

    private String userId;

    private String userName;

    private String statusMsg;

    private String compName;

}