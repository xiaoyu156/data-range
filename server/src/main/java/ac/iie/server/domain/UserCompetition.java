package ac.iie.server.domain;

import java.io.Serializable;
import java.util.Date;

public class UserCompetition implements Serializable {
    private String id;

    private String competitionId;

    private String userId;

    private Date createTime;

    private Integer status;

    private Integer type;

    public UserCompetition() {
    }

    private Competition competition;


    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(String competitionId) {
        this.competitionId = competitionId == null ? null : competitionId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    @Override
    public String toString() {
        return "UserCompetition{" +
                "id='" + id + '\'' +
                ", competitionId='" + competitionId + '\'' +
                ", userId='" + userId + '\'' +
                ", createTime=" + createTime +
                ", status=" + status +
                ", type=" + type +
                ", competition=" + competition +
                '}';
    }
}