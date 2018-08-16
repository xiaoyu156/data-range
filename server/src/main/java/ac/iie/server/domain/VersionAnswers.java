package ac.iie.server.domain;

import java.io.Serializable;
import java.util.Date;

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

    private Double score1;

    private Double score2;

    private Double score3;

    private Double score4;

    private Double score5;

    private Integer type;

    private String imageUrl;

    private String userId;

    private String userName;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserCompId() {
        return userCompId;
    }

    public void setUserCompId(String userCompId) {
        this.userCompId = userCompId == null ? null : userCompId.trim();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public String getResultUrl() {
        return resultUrl;
    }

    public void setResultUrl(String resultUrl) {
        this.resultUrl = resultUrl == null ? null : resultUrl.trim();
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public Integer getResource() {
        return resource;
    }

    public void setResource(Integer resource) {
        this.resource = resource;
    }

    public String getRunCommand() {
        return runCommand;
    }

    public void setRunCommand(String runCommand) {
        this.runCommand = runCommand == null ? null : runCommand.trim();
    }

    public Double getScore1() {
        return score1;
    }

    public void setScore1(Double score1) {
        this.score1 = score1;
    }

    public Double getScore2() {
        return score2;
    }

    public void setScore2(Double score2) {
        this.score2 = score2;
    }

    public Double getScore3() {
        return score3;
    }

    public void setScore3(Double score3) {
        this.score3 = score3;
    }

    public Double getScore4() {
        return score4;
    }

    public void setScore4(Double score4) {
        this.score4 = score4;
    }

    public Double getScore5() {
        return score5;
    }

    public void setScore5(Double score5) {
        this.score5 = score5;
    }

    @Override
    public String toString() {
        return "VersionAnswers{" +
                "id='" + id + '\'' +
                ", userCompId='" + userCompId + '\'' +
                ", version='" + version + '\'' +
                ", resultUrl='" + resultUrl + '\'' +
                ", uploadTime=" + uploadTime +
                ", status=" + status +
                ", detail='" + detail + '\'' +
                ", resource=" + resource +
                ", runCommand='" + runCommand + '\'' +
                ", score1=" + score1 +
                ", score2=" + score2 +
                ", score3=" + score3 +
                ", score4=" + score4 +
                ", score5=" + score5 +
                ", type=" + type +
                ", imageUrl='" + imageUrl + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}