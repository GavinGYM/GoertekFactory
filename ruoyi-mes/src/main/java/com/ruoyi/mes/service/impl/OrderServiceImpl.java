package com.ruoyi.mes.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.mes.domain.Material;
import com.ruoyi.mes.domain.MaterialItem;
import com.ruoyi.mes.mapper.MaterialItemMapper;
import com.ruoyi.mes.mapper.ProcessMapper;
import com.ruoyi.mes.mapper.WorkshopMapper;
import org.aspectj.weaver.ast.Or;
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

    @Autowired
    private MaterialItemMapper materialItemMapper;

    @Autowired
    private ProcessMapper processMapper;

    @Autowired
    private WorkshopMapper workshopMapper;

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
        List<Order> orderList = orderMapper.selectOrderList(order);
        for(Order order1 : orderList){
            MaterialItem materialItem = materialItemMapper.selectMaterialItemByMaterialItemId(order1.getOrderMaterialId());
            order1.setOrderMaterialName(materialItem.getMaterialItemName());
            order1.setOrderMaterialUnit(materialItem.getMaterialItemUnit());
        }
        // 对OrderList根据工单状态进行排序
        orderList.sort((o1, o2) -> {
            if(o1.getOrderStatus() > o2.getOrderStatus()){
                return 1;
            }else if(o1.getOrderStatus() < o2.getOrderStatus()){
                return -1;
            }else{
                return 0;
            }
        });
        return orderList;
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
        LoginUser loginUser = SecurityUtils.getLoginUser();
        order.setOrderCreator(loginUser.getUser().getUserId());
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
     * 批量修改工单状态
     *
     * @param orderIds 需要修改状态的生产工单主键
     * @return 结果
     */
    @Transactional
    @Override
    public int changeStatusByOrderIds(Long[] orderIds, Long orderStatus)
    {
        int result = 0;
        for (Long orderId : orderIds) {
            Order order = orderMapper.selectOrderByOrderId(orderId);
            order.setOrderStatus(orderStatus);
            if(orderStatus == 1L && order.getOrderStartDate() == null){
                order.setOrderStartDate(new java.util.Date());
            }
            result += orderMapper.updateOrder(order);
        }
        return result;
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

    /**
     * 更新工单工序信息
     *
     * @param orderRoute 工单工序对象
     * @return 结果
     */
    public int updateOrderRoute(OrderRoute orderRoute)
    {
        return orderMapper.updateOrderRoute(orderRoute);
    }
}
