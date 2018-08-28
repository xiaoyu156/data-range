package ac.iie.server.domain;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class Competition implements Serializable {
    private String id;

    private String title;

    private String description;

    private String dataDesc;

    private Boolean isIncludeMedia;

    private String dataUrl;

    private String programUrl;

    private Integer type;

    private LocalDateTime publishTime;

    private LocalDateTime updateTime;

    private Integer joinNum;

    private Integer status;

    private String logoUrl;

    private LocalDateTime endTime;

    private String typeId;

    private Integer bonus;

    private String scoreItems;

    private String keyDataMap;

    private String content;

    private String runCommand;

    private String statusMsg;

    private int programStatus;

    private String sponsor;

    private User user;

}