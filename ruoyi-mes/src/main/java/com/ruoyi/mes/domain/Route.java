package com.ruoyi.mes.domain;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工序路线对象 mes_route
 * 
 * @author Yiming Guo
 * @date 2023-06-28
 */
public class Route extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 工序编号 */
    private Long routeId;

    /** 工序路线名称 */
    @Excel(name = "工序路线名称")
    private String routeName;

    /** 工序路线说明 */
    @Excel(name = "工序路线说明")
    private String routeComment;

    /** 状态 */
    @Excel(name = "状态")
    private Long routeStatus;

    /** 添加时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date routeAddTime;

    /** 工序与路线关系信息 */
    private List<RouteProcess> routeProcessList;

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
    public void setRouteComment(String routeComment) 
    {
        this.routeComment = routeComment;
    }

    public String getRouteComment() 
    {
        return routeComment;
    }
    public void setRouteStatus(Long routeStatus) 
    {
        this.routeStatus = routeStatus;
    }

    public Long getRouteStatus() 
    {
        return routeStatus;
    }
    public void setRouteAddTime(Date routeAddTime) 
    {
        this.routeAddTime = routeAddTime;
    }

    public Date getRouteAddTime() 
    {
        return routeAddTime;
    }

    public List<RouteProcess> getRouteProcessList()
    {
        return routeProcessList;
    }

    public void setRouteProcessList(List<RouteProcess> routeProcessList)
    {
        this.routeProcessList = routeProcessList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("routeId", getRouteId())
            .append("routeName", getRouteName())
            .append("routeComment", getRouteComment())
            .append("routeStatus", getRouteStatus())
            .append("routeAddTime", getRouteAddTime())
            .append("routeProcessList", getRouteProcessList())
            .toString();
    }
}
