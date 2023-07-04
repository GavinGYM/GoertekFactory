package com.ruoyi.mes.domain;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 生产工单对象 mes_order
 * 
 * @author Yiming Guo
 * @date 2023-07-04
 */
public class Order extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 工单号 */
    @Excel(name = "工单号")
    private Long orderId;

    /** 批次号 */
    @Excel(name = "批次号")
    private Long orderBatchId;

    /** 创建人 */
    @Excel(name = "创建人")
    private Long orderCreator;

    /** 状态 */
    @Excel(name = "状态")
    private Long orderStatus;

    /** 物料 */
    @Excel(name = "物料")
    private Long orderMaterialId;

    /** 计划完工日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "计划完工日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date orderDeadline;

    /** 开始生产日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始生产日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date orderStartDate;

    /** 实际完工日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "实际完工日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date orderEndDate;

    /** 生产数量 */
    @Excel(name = "生产数量")
    private Long orderNumber;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date orderCreateTime;

    /** 工单工序信息 */
    private List<OrderRoute> orderRouteList;

    public void setOrderId(Long orderId) 
    {
        this.orderId = orderId;
    }

    public Long getOrderId() 
    {
        return orderId;
    }
    public void setOrderBatchId(Long orderBatchId) 
    {
        this.orderBatchId = orderBatchId;
    }

    public Long getOrderBatchId() 
    {
        return orderBatchId;
    }
    public void setOrderCreator(Long orderCreator) 
    {
        this.orderCreator = orderCreator;
    }

    public Long getOrderCreator() 
    {
        return orderCreator;
    }
    public void setOrderStatus(Long orderStatus) 
    {
        this.orderStatus = orderStatus;
    }

    public Long getOrderStatus() 
    {
        return orderStatus;
    }
    public void setOrderMaterialId(Long orderMaterialId) 
    {
        this.orderMaterialId = orderMaterialId;
    }

    public Long getOrderMaterialId() 
    {
        return orderMaterialId;
    }
    public void setOrderDeadline(Date orderDeadline) 
    {
        this.orderDeadline = orderDeadline;
    }

    public Date getOrderDeadline() 
    {
        return orderDeadline;
    }
    public void setOrderStartDate(Date orderStartDate) 
    {
        this.orderStartDate = orderStartDate;
    }

    public Date getOrderStartDate() 
    {
        return orderStartDate;
    }
    public void setOrderEndDate(Date orderEndDate) 
    {
        this.orderEndDate = orderEndDate;
    }

    public Date getOrderEndDate() 
    {
        return orderEndDate;
    }
    public void setOrderNumber(Long orderNumber) 
    {
        this.orderNumber = orderNumber;
    }

    public Long getOrderNumber() 
    {
        return orderNumber;
    }
    public void setOrderCreateTime(Date orderCreateTime) 
    {
        this.orderCreateTime = orderCreateTime;
    }

    public Date getOrderCreateTime() 
    {
        return orderCreateTime;
    }

    public List<OrderRoute> getOrderRouteList()
    {
        return orderRouteList;
    }

    public void setOrderRouteList(List<OrderRoute> orderRouteList)
    {
        this.orderRouteList = orderRouteList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("orderId", getOrderId())
            .append("orderBatchId", getOrderBatchId())
            .append("orderCreator", getOrderCreator())
            .append("orderStatus", getOrderStatus())
            .append("orderMaterialId", getOrderMaterialId())
            .append("orderDeadline", getOrderDeadline())
            .append("orderStartDate", getOrderStartDate())
            .append("orderEndDate", getOrderEndDate())
            .append("orderNumber", getOrderNumber())
            .append("orderCreateTime", getOrderCreateTime())
            .append("orderRouteList", getOrderRouteList())
            .toString();
    }
}
