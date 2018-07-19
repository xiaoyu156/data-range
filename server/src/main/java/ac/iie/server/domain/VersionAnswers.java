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
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userCompId=").append(userCompId);
        sb.append(", version=").append(version);
        sb.append(", resultUrl=").append(resultUrl);
        sb.append(", uploadTime=").append(uploadTime);
        sb.append(", status=").append(status);
        sb.append(", detail=").append(detail);
        sb.append(", resource=").append(resource);
        sb.append(", runCommand=").append(runCommand);
        sb.append(", score1=").append(score1);
        sb.append(", score2=").append(score2);
        sb.append(", score3=").append(score3);
        sb.append(", score4=").append(score4);
        sb.append(", score5=").append(score5);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}