package com.ruoyi.mes.service.impl;

import java.util.List;
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
        return reportMapper.insertReport(report);
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
