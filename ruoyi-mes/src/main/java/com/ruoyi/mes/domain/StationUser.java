package com.ruoyi.mes.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工位绑定对象 mes_station_user
 * 
 * @author Yiming Guo
 * @date 2023-07-03
 */
public class StationUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 工位绑定编号 */
    private Long stationUserId;

    /** 工位ID */
    @Excel(name = "工位ID")
    private Long stationId;

    /** 工位名称 */
    @Excel(name = "工位名称")
    private String stationName;

    /** 车间名称 */
    @Excel(name = "车间名称")
    private String workshopName;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 用户昵称 */
    @Excel(name = "用户昵称")
    private String userNickName;

    /** 用户名称 */
    @Excel(name = "用户名称")
    private String userName;

    /** 添加人 */
    @Excel(name = "添加人")
    private Long addUserId;

    /** 添加人名称 */
    @Excel(name = "添加人名称")
    private String addUserName;

    /** 添加时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date addTime;

    public void setStationName(String stationName) { this.stationName = stationName; }

    public String getStationName() { return stationName; }

    public void setWorkshopName(String workshopName) { this.workshopName = workshopName; }

    public String getWorkshopName() { return workshopName; }

    public void setUserName(String userName) { this.userName = userName; }

    public String getUserName() { return userName;}

    public void setUserNickName(String userNickName) { this.userNickName = userNickName; }

    public String getUserNickName() { return userNickName; }

    public void setAddUserName(String addUserName) { this.addUserName = addUserName; }

    public String getAddUserName() { return addUserName; }

    public void setStationUserId(Long stationUserId) 
    {
        this.stationUserId = stationUserId;
    }

    public Long getStationUserId() 
    {
        return stationUserId;
    }
    public void setStationId(Long stationId) 
    {
        this.stationId = stationId;
    }

    public Long getStationId() 
    {
        return stationId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setAddUserId(Long addUserId) 
    {
        this.addUserId = addUserId;
    }

    public Long getAddUserId() 
    {
        return addUserId;
    }
    public void setAddTime(Date addTime) 
    {
        this.addTime = addTime;
    }

    public Date getAddTime() 
    {
        return addTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("stationUserId", getStationUserId())
            .append("stationId", getStationId())
            .append("userId", getUserId())
            .append("addUserId", getAddUserId())
            .append("addTime", getAddTime())
            .append("stationName", getStationName())
            .append("userName", getUserName())
            .append("addUserName", getAddUserName())
            .append("workshopName", getWorkshopName())
            .append("userNickName", getUserNickName())
            .toString();
    }
}
