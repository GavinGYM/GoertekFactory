package com.ruoyi.mes.service;

import java.util.List;
import com.ruoyi.mes.domain.Order;

/**
 * 生产工单Service接口
 * 
 * @author Yiming Guo
 * @date 2023-07-04
 */
public interface IOrderService 
{
    /**
     * 查询生产工单
     * 
     * @param orderId 生产工单主键
     * @return 生产工单
     */
    public Order selectOrderByOrderId(Long orderId);

    /**
     * 查询生产工单列表
     * 
     * @param order 生产工单
     * @return 生产工单集合
     */
    public List<Order> selectOrderList(Order order);

    /**
     * 新增生产工单
     * 
     * @param order 生产工单
     * @return 结果
     */
    public int insertOrder(Order order);

    /**
     * 修改生产工单
     * 
     * @param order 生产工单
     * @return 结果
     */
    public int updateOrder(Order order);

    /**
     * 批量删除生产工单
     * 
     * @param orderIds 需要删除的生产工单主键集合
     * @return 结果
     */
    public int deleteOrderByOrderIds(Long[] orderIds);

    /**
     * 删除生产工单信息
     * 
     * @param orderId 生产工单主键
     * @return 结果
     */
    public int deleteOrderByOrderId(Long orderId);
}
