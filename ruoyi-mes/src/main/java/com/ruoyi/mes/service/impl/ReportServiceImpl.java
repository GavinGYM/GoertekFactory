package com.ruoyi.mes.service.impl;

import java.util.List;

import com.ruoyi.mes.domain.Order;
import com.ruoyi.mes.domain.OrderRoute;
import com.ruoyi.mes.mapper.OrderMapper;
import com.ruoyi.mes.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.mapper.ReportMapper;
import com.ruoyi.mes.domain.Report;
import com.ruoyi.mes.service.IReportService;

/**
 * 报工Service业务层处理
 * 
 * @author Yiming Guo
 * @date 2023-07-06
 */
@Service
public class ReportServiceImpl implements IReportService 
{
    @Autowired
    private ReportMapper reportMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private IOrderService orderService;

    /**
     * 查询报工
     * 
     * @param reportId 报工主键
     * @return 报工
     */
    @Override
    public Report selectReportByReportId(Long reportId)
    {
        return reportMapper.selectReportByReportId(reportId);
    }

    /**
     * 查询报工列表
     * 
     * @param report 报工
     * @return 报工
     */
    @Override
    public List<Report> selectReportList(Report report)
    {
        return reportMapper.selectReportList(report);
    }

    /**
     * 新增报工
     * 
     * @param report 报工
     * @return 结果
     */
    @Override
    public int insertReport(Report report)
    {
        int result = reportMapper.insertReport(report);
        if(result > 0){
            // 查询当前工序的所有报工
            Report searchReport = new Report();
            searchReport.setOrderId(report.getOrderId());
            searchReport.setProcessIndex(report.getProcessIndex());
            List<Report> reportList = reportMapper.selectReportList(searchReport);
            // 计算总报工数
            Long totalNumber = 0L;
            for(Report r : reportList){
                totalNumber += r.getReportNumber();
            }
            // 更新当前工序的总报工数
            Order order = orderMapper.selectOrderByOrderId(report.getOrderId());
            List<OrderRoute> orderRouteList = order.getOrderRouteList();
            for(OrderRoute orderRoute : orderRouteList){
                if(orderRoute.getRouteId().equals(report.getRouteId()) && orderRoute.getProcessIndex().equals(report.getProcessIndex())){
                    orderRoute.setOrderFinishNumber(totalNumber);
                    break;
                }
            }
            order.setOrderRouteList(orderRouteList);

            // 查询订单的目标产量
            Long targetNumber = order.getOrderNumber();
            // 查询订单所有工序的已报工数量
            boolean flag = true;
            for(OrderRoute orderRoute1 : orderRouteList){
                if (orderRoute1.getOrderFinishNumber() == null || orderRoute1.getOrderFinishNumber() < targetNumber){
                    flag = false;
                    break;
                }
            }
            // 如果所有工序的已报工数量都大于等于目标产量，则订单状态改为已完成
            if(flag){
                order.setOrderStatus(3L);
            }

            // 最后更新订单
            orderService.updateOrder(order);
        }
        return result;
    }

    /**
     * 修改报工
     * 
     * @param report 报工
     * @return 结果
     */
    @Override
    public int updateReport(Report report)
    {
        return reportMapper.updateReport(report);
    }

    /**
     * 批量删除报工
     * 
     * @param reportIds 需要删除的报工主键
     * @return 结果
     */
    @Override
    public int deleteReportByReportIds(Long[] reportIds)
    {
        return reportMapper.deleteReportByReportIds(reportIds);
    }

    /**
     * 删除报工信息
     * 
     * @param reportId 报工主键
     * @return 结果
     */
    @Override
    public int deleteReportByReportId(Long reportId)
    {
        return reportMapper.deleteReportByReportId(reportId);
    }
}