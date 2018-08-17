package ac.iie.server.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Competition implements Serializable {
    private String id;

    private String title;

    private String description;

    private String dataDesc;

    private Boolean isIncludeMedia;

    private String dataUrl;

    private String programUrl;

    private Integer type;

    private Date publishTime;

    private Date updateTime;

    private Integer joinNum;

    private Integer status;

    private String logoUrl;

    private Date endTime;

    private String typeId;

    private Integer bonus;

    private String scoreItems;

    private String keyDataMap;

    private String content;

    private String runCommand;

    private String statusMsg;

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    public Boolean getIncludeMedia() {
        return isIncludeMedia;
    }

    public void setIncludeMedia(Boolean includeMedia) {
        isIncludeMedia = includeMedia;
    }

    public String getRunCommand() {
        return runCommand;
    }

    public void setRunCommand(String runCommand) {
        this.runCommand = runCommand;
    }

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getDataDesc() {
        return dataDesc;
    }

    public void setDataDesc(String dataDesc) {
        this.dataDesc = dataDesc == null ? null : dataDesc.trim();
    }

    public Boolean getIsIncludeMedia() {
        return isIncludeMedia;
    }

    public void setIsIncludeMedia(Boolean isIncludeMedia) {
        this.isIncludeMedia = isIncludeMedia;
    }

    public String getDataUrl() {
        return dataUrl;
    }

    public void setDataUrl(String dataUrl) {
        this.dataUrl = dataUrl == null ? null : dataUrl.trim();
    }

    public String getProgramUrl() {
        return programUrl;
    }

    public void setProgramUrl(String programUrl) {
        this.programUrl = programUrl == null ? null : programUrl.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getJoinNum() {
        return joinNum;
    }

    public void setJoinNum(Integer joinNum) {
        this.joinNum = joinNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl == null ? null : logoUrl.trim();
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId == null ? null : typeId.trim();
    }

    public Integer getBonus() {
        return bonus;
    }

    public void setBonus(Integer bonus) {
        this.bonus = bonus;
    }

    public String getScoreItems() {
        return scoreItems;
    }

    public void setScoreItems(String scoreItems) {
        this.scoreItems = scoreItems == null ? null : scoreItems.trim();
    }

    public String getKeyDataMap() {
        return keyDataMap;
    }

    public void setKeyDataMap(String keyDataMap) {
        this.keyDataMap = keyDataMap == null ? null : keyDataMap.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    @Override
    public String toString() {
        return "Competition{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dataDesc='" + dataDesc + '\'' +
                ", isIncludeMedia=" + isIncludeMedia +
                ", dataUrl='" + dataUrl + '\'' +
                ", programUrl='" + programUrl + '\'' +
                ", type=" + type +
                ", publishTime=" + publishTime +
                ", updateTime=" + updateTime +
                ", joinNum=" + joinNum +
                ", status=" + status +
                ", logoUrl='" + logoUrl + '\'' +
                ", endTime=" + endTime +
                ", typeId='" + typeId + '\'' +
                ", bonus=" + bonus +
                ", scoreItems='" + scoreItems + '\'' +
                ", keyDataMap='" + keyDataMap + '\'' +
                ", content='" + content + '\'' +
                ", runCommand='" + runCommand + '\'' +
                ", statusMsg='" + statusMsg +
                '}';
    }
}