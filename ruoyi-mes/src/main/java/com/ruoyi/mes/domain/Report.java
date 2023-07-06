package com.ruoyi.mes.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 报工对象 mes_report
 * 
 * @author Yiming Guo
 * @date 2023-07-06
 */
public class Report extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 报工号 */
    private Long reportId;

    /** 生产工单号 */
    @Excel(name = "生产工单号")
    private Long orderId;

    /** 工序路线号 */
    @Excel(name = "工序路线号")
    private Long routeId;

    /** 工序路线名 */
    @Excel(name = "工序路线名")
    private String routeName;

    /** 工序号 */
    @Excel(name = "工序号")
    private Long processId;

    /** 工序名 */
    @Excel(name = "工序名")
    private String processName;

    /** 工序排序 */
    @Excel(name = "工序排序")
    private Long processIndex;

    /** 报工人id */
    @Excel(name = "报工人id")
    private Long userId;

    /** 报工人名称 */
    @Excel(name = "报工人名称")
    private String userName;

    /** 报工时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "报工时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date reportTime;

    /** 报工数量 */
    @Excel(name = "报工数量")
    private Long reportNumber;

    /** 车间id */
    @Excel(name = "车间id")
    private Long workshopId;

    /** 车间名称 */
    @Excel(name = "车间名称")
    private String workshopName;

    /** 工位id */
    @Excel(name = "工位id")
    private Long stationId;

    /** 工位名称 */
    @Excel(name = "工位名称")
    private String stationName;

    public void setReportId(Long reportId) 
    {
        this.reportId = reportId;
    }

    public Long getReportId() 
    {
        return reportId;
    }
    public void setOrderId(Long orderId) 
    {
        this.orderId = orderId;
    }

    public Long getOrderId() 
    {
        return orderId;
    }
    public void setRouteId(Long routeId) 
    {
        this.routeId = routeId;
    }

    public Long getRouteId() 
    {
        return routeId;
    }

    public void setRouteName(String routeName)
    {
        this.routeName = routeName;
    }

    public String getRouteName()
    {
        return routeName;
    }
    public void setProcessId(Long processId) 
    {
        this.processId = processId;
    }

    public Long getProcessId() 
    {
        return processId;
    }

    public void setProcessName(String processName)
    {
        this.processName = processName;
    }

    public String getProcessName()
    {
        return processName;
    }
    public void setProcessIndex(Long processIndex) 
    {
        this.processIndex = processIndex;
    }

    public Long getProcessIndex() 
    {
        return processIndex;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserName()
    {
        return userName;
    }
    public void setReportTime(Date reportTime) 
    {
        this.reportTime = reportTime;
    }

    public Date getReportTime() 
    {
        return reportTime;
    }
    public void setReportNumber(Long reportNumber) 
    {
        this.reportNumber = reportNumber;
    }

    public Long getReportNumber() 
    {
        return reportNumber;
    }
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("reportId", getReportId())
            .append("orderId", getOrderId())
            .append("routeId", getRouteId())
            .append("processId", getProcessId())
            .append("processIndex", getProcessIndex())
            .append("userId", getUserId())
            .append("reportTime", getReportTime())
            .append("reportNumber", getReportNumber())
            .append("workshopId", getWorkshopId())
            .append("stationId", getStationId())
            .append("routeName", getRouteName())
            .append("processName", getProcessName())
            .append("userName", getUserName())
            .append("workshopName", getWorkshopName())
            .append("stationName", getStationName())
            .toString();
    }
}
