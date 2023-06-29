package com.ruoyi.mes.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工位信息对象 mes_station
 * 
 * @author Yiming Guo
 * @date 2023-06-29
 */
public class Station extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 工位编号 */
    @Excel(name = "工位编号")
    private Long stationId;

    /** 工位名称 */
    @Excel(name = "工位名称")
    private String stationName;

    /** 所属车间 */
    @Excel(name = "所属车间")
    private Long workshopId;

    /** 绑定工序 */
    @Excel(name = "绑定工序")
    private Long processId;

    /** 绑定设备 */
    @Excel(name = "绑定设备")
    private Long equipmentId;

    /** 状态 */
    @Excel(name = "状态")
    private Long stationStatus;

    /** 备注 */
    @Excel(name = "备注")
    private String stationComment;

    public void setStationId(Long stationId) 
    {
        this.stationId = stationId;
    }

    public Long getStationId() 
    {
        return stationId;
    }
    public void setStationName(String stationName) 
    {
        this.stationName = stationName;
    }

    public String getStationName() 
    {
        return stationName;
    }
    public void setWorkshopId(Long workshopId) 
    {
        this.workshopId = workshopId;
    }

    public Long getWorkshopId() 
    {
        return workshopId;
    }
    public void setProcessId(Long processId) 
    {
        this.processId = processId;
    }

    public Long getProcessId() 
    {
        return processId;
    }
    public void setEquipmentId(Long equipmentId) 
    {
        this.equipmentId = equipmentId;
    }

    public Long getEquipmentId() 
    {
        return equipmentId;
    }
    public void setStationStatus(Long stationStatus) 
    {
        this.stationStatus = stationStatus;
    }

    public Long getStationStatus() 
    {
        return stationStatus;
    }
    public void setStationComment(String stationComment) 
    {
        this.stationComment = stationComment;
    }

    public String getStationComment() 
    {
        return stationComment;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("stationId", getStationId())
            .append("stationName", getStationName())
            .append("workshopId", getWorkshopId())
            .append("processId", getProcessId())
            .append("equipmentId", getEquipmentId())
            .append("stationStatus", getStationStatus())
            .append("stationComment", getStationComment())
            .toString();
    }
}
