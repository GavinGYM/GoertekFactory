package com.ruoyi.mes.service;

import java.util.List;
import com.ruoyi.mes.domain.Report;

/**
 * 报工Service接口
 * 
 * @author Yiming Guo
 * @date 2023-07-06
 */
public interface IReportService 
{
    /**
     * 查询报工
     * 
     * @param reportId 报工主键
     * @return 报工
     */
    public Report selectReportByReportId(Long reportId);

    /**
     * 查询报工列表
     * 
     * @param report 报工
     * @return 报工集合
     */
    public List<Report> selectReportList(Report report);

    /**
     * 新增报工
     * 
     * @param report 报工
     * @return 结果
     */
    public int insertReport(Report report);

    /**
     * 修改报工
     * 
     * @param report 报工
     * @return 结果
     */
    public int updateReport(Report report);

    /**
     * 批量删除报工
     * 
     * @param reportIds 需要删除的报工主键集合
     * @return 结果
     */
    public int deleteReportByReportIds(Long[] reportIds);

    /**
     * 删除报工信息
     * 
     * @param reportId 报工主键
     * @return 结果
     */
    public int deleteReportByReportId(Long reportId);
}
