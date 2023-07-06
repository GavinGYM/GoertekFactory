package com.ruoyi.mes.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.mes.domain.Report;
import com.ruoyi.mes.service.IReportService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 报工Controller
 * 
 * @author Yiming Guo
 * @date 2023-07-06
 */
@RestController
@RequestMapping("/mes/report")
public class ReportController extends BaseController
{
    @Autowired
    private IReportService reportService;

    /**
     * 查询报工列表
     */
    @PreAuthorize("@ss.hasPermi('mes:report:list')")
    @GetMapping("/list")
    public TableDataInfo list(Report report)
    {
        startPage();
        List<Report> list = reportService.selectReportList(report);
        return getDataTable(list);
    }

    /**
     * 导出报工列表
     */
    @PreAuthorize("@ss.hasPermi('mes:report:export')")
    @Log(title = "报工", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Report report)
    {
        List<Report> list = reportService.selectReportList(report);
        ExcelUtil<Report> util = new ExcelUtil<Report>(Report.class);
        util.exportExcel(response, list, "报工数据");
    }

    /**
     * 获取报工详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:report:query')")
    @GetMapping(value = "/{reportId}")
    public AjaxResult getInfo(@PathVariable("reportId") Long reportId)
    {
        return success(reportService.selectReportByReportId(reportId));
    }

    /**
     * 新增报工
     */
    @PreAuthorize("@ss.hasPermi('mes:report:add')")
    @Log(title = "报工", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Report report)
    {
        return toAjax(reportService.insertReport(report));
    }

    /**
     * 修改报工
     */
    @PreAuthorize("@ss.hasPermi('mes:report:edit')")
    @Log(title = "报工", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Report report)
    {
        return toAjax(reportService.updateReport(report));
    }

    /**
     * 删除报工
     */
    @PreAuthorize("@ss.hasPermi('mes:report:remove')")
    @Log(title = "报工", businessType = BusinessType.DELETE)
	@DeleteMapping("/{reportIds}")
    public AjaxResult remove(@PathVariable Long[] reportIds)
    {
        return toAjax(reportService.deleteReportByReportIds(reportIds));
    }
}
