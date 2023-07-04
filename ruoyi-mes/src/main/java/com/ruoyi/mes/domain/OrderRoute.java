package com.ruoyi.mes.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工单工序对象 mes_order_route
 * 
 * @author Yiming Guo
 * @date 2023-07-04
 */
public class OrderRoute extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 工单编号 */
    private Long orderId;

    /** 工序路线编号 */
    @Excel(name = "工序路线编号")
    private Long routeId;

    /** 工序编号 */
    @Excel(name = "工序编号")
    private Long processId;

    /** 工序顺序 */
    @Excel(name = "工序顺序")
    private Long processIndex;

    /** 车间编号 */
    @Excel(name = "车间编号")
    private Long workshopId;

    /** 工位编号 */
    @Excel(name = "工位编号")
    private Long stationId;

    /** 完成数量 */
    @Excel(name = "完成数量")
    private Long orderFinishNumber;

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
    public void setWorkshopId(Long workshopId) 
    {
        this.workshopId = workshopId;
    }

    public Long getWorkshopId() 
    {
        return workshopId;
    }
    public void setStationId(Long stationId) 
    {
        this.stationId = stationId;
    }

    public Long getStationId() 
    {
        return stationId;
    }
    public void setOrderFinishNumber(Long orderFinishNumber) 
    {
        this.orderFinishNumber = orderFinishNumber;
    }

    public Long getOrderFinishNumber() 
    {
        return orderFinishNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("orderId", getOrderId())
            .append("routeId", getRouteId())
            .append("processId", getProcessId())
            .append("processIndex", getProcessIndex())
            .append("workshopId", getWorkshopId())
            .append("stationId", getStationId())
            .append("orderFinishNumber", getOrderFinishNumber())
            .toString();
    }
}
