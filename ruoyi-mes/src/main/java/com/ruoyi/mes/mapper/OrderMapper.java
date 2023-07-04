package com.ruoyi.mes.mapper;

import java.util.List;
import com.ruoyi.mes.domain.Order;
import com.ruoyi.mes.domain.OrderRoute;

/**
 * 生产工单Mapper接口
 * 
 * @author Yiming Guo
 * @date 2023-07-04
 */
public interface OrderMapper 
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
     * 删除生产工单
     * 
     * @param orderId 生产工单主键
     * @return 结果
     */
    public int deleteOrderByOrderId(Long orderId);

    /**
     * 批量删除生产工单
     * 
     * @param orderIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOrderByOrderIds(Long[] orderIds);

    /**
     * 批量删除工单工序
     * 
     * @param orderIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOrderRouteByOrderIds(Long[] orderIds);
    
    /**
     * 批量新增工单工序
     * 
     * @param orderRouteList 工单工序列表
     * @return 结果
     */
    public int batchOrderRoute(List<OrderRoute> orderRouteList);
    

    /**
     * 通过生产工单主键删除工单工序信息
     * 
     * @param orderId 生产工单ID
     * @return 结果
     */
    public int deleteOrderRouteByOrderId(Long orderId);
}
