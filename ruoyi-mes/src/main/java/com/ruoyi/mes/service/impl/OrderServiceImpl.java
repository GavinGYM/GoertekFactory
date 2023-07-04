package com.ruoyi.mes.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.mes.domain.OrderRoute;
import com.ruoyi.mes.mapper.OrderMapper;
import com.ruoyi.mes.domain.Order;
import com.ruoyi.mes.service.IOrderService;

/**
 * 生产工单Service业务层处理
 * 
 * @author Yiming Guo
 * @date 2023-07-04
 */
@Service
public class OrderServiceImpl implements IOrderService 
{
    @Autowired
    private OrderMapper orderMapper;

    /**
     * 查询生产工单
     * 
     * @param orderId 生产工单主键
     * @return 生产工单
     */
    @Override
    public Order selectOrderByOrderId(Long orderId)
    {
        return orderMapper.selectOrderByOrderId(orderId);
    }

    /**
     * 查询生产工单列表
     * 
     * @param order 生产工单
     * @return 生产工单
     */
    @Override
    public List<Order> selectOrderList(Order order)
    {
        return orderMapper.selectOrderList(order);
    }

    /**
     * 新增生产工单
     * 
     * @param order 生产工单
     * @return 结果
     */
    @Transactional
    @Override
    public int insertOrder(Order order)
    {
        int rows = orderMapper.insertOrder(order);
        insertOrderRoute(order);
        return rows;
    }

    /**
     * 修改生产工单
     * 
     * @param order 生产工单
     * @return 结果
     */
    @Transactional
    @Override
    public int updateOrder(Order order)
    {
        orderMapper.deleteOrderRouteByOrderId(order.getOrderId());
        insertOrderRoute(order);
        return orderMapper.updateOrder(order);
    }

    /**
     * 批量删除生产工单
     * 
     * @param orderIds 需要删除的生产工单主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteOrderByOrderIds(Long[] orderIds)
    {
        orderMapper.deleteOrderRouteByOrderIds(orderIds);
        return orderMapper.deleteOrderByOrderIds(orderIds);
    }

    /**
     * 删除生产工单信息
     * 
     * @param orderId 生产工单主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteOrderByOrderId(Long orderId)
    {
        orderMapper.deleteOrderRouteByOrderId(orderId);
        return orderMapper.deleteOrderByOrderId(orderId);
    }

    /**
     * 新增工单工序信息
     * 
     * @param order 生产工单对象
     */
    public void insertOrderRoute(Order order)
    {
        List<OrderRoute> orderRouteList = order.getOrderRouteList();
        Long orderId = order.getOrderId();
        if (StringUtils.isNotNull(orderRouteList))
        {
            List<OrderRoute> list = new ArrayList<OrderRoute>();
            for (OrderRoute orderRoute : orderRouteList)
            {
                orderRoute.setOrderId(orderId);
                list.add(orderRoute);
            }
            if (list.size() > 0)
            {
                orderMapper.batchOrderRoute(list);
            }
        }
    }
}
