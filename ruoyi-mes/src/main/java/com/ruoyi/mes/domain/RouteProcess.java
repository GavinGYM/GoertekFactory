package com.ruoyi.mes.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工序与路线关系对象 mes_route_process
 * 
 * @author Yiming Guo
 * @date 2023-06-28
 */
public class RouteProcess extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 路线ID */
    private Long routeId;

    /** 工序ID */
    private Long processId;

    /** 工序顺序 */
    @Excel(name = "工序顺序")
    private Long processIndex;

    public void setRouteId(Long routeId) 
    {
        this.routeId = routeId;
    }

    public Long getRouteId() 
    {
        return routeId;
    }
    public void setProcessId(Long processId) 
    {
        this.processId = processId;
    }

    public Long getProcessId() 
    {
        return processId;
    }
    public void setProcessIndex(Long processIndex)
    {
        this.processIndex = processIndex;
    }

    public Long getProcessIndex()
    {
        return processIndex;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("routeId", getRouteId())
            .append("processId", getProcessId())
            .append("processIndex", getProcessIndex())
            .toString();
    }
}
