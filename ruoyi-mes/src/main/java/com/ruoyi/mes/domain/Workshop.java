package com.ruoyi.mes.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 车间信息对象 mes_workshop
 * 
 * @author Yiming Guo
 * @date 2023-06-26
 */
public class Workshop extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 车间编号 */
    private Long workshopId;

    /** 车间名称 */
    @Excel(name = "车间名称")
    private String workshopName;

    /** 负责人 */
    @Excel(name = "负责人")
    private String workshopLeader;

    /** 状态 */
    @Excel(name = "状态")
    private Long workshopStatus;

    /** 添加时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date workshopAddTime;

    /** 备注 */
    @Excel(name = "备注")
    private String workshopComment;

    public void setWorkshopId(Long workshopId) 
    {
        this.workshopId = workshopId;
    }

    public Long getWorkshopId() 
    {
        return workshopId;
    }
    public void setWorkshopName(String workshopName) 
    {
        this.workshopName = workshopName;
    }

    public String getWorkshopName() 
    {
        return workshopName;
    }
    public void setWorkshopLeader(String workshopLeader) 
    {
        this.workshopLeader = workshopLeader;
    }

    public String getWorkshopLeader() 
    {
        return workshopLeader;
    }
    public void setWorkshopStatus(Long workshopStatus) 
    {
        this.workshopStatus = workshopStatus;
    }

    public Long getWorkshopStatus() 
    {
        return workshopStatus;
    }
    public void setWorkshopAddTime(Date workshopAddTime) 
    {
        this.workshopAddTime = workshopAddTime;
    }

    public Date getWorkshopAddTime() 
    {
        return workshopAddTime;
    }
    public void setWorkshopComment(String workshopComment) 
    {
        this.workshopComment = workshopComment;
    }

    public String getWorkshopComment() 
    {
        return workshopComment;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("workshopId", getWorkshopId())
            .append("workshopName", getWorkshopName())
            .append("workshopLeader", getWorkshopLeader())
            .append("workshopStatus", getWorkshopStatus())
            .append("workshopAddTime", getWorkshopAddTime())
            .append("workshopComment", getWorkshopComment())
            .toString();
    }
}
