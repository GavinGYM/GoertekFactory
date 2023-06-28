package com.ruoyi.mes.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import com.ruoyi.mes.domain.Process;
import com.ruoyi.mes.service.IProcessService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 工序设置Controller
 * 
 * @author Yiming Guo
 * @date 2023-06-26
 */
@Api("查询工序设置列表")
@Controller
@RestController
@RequestMapping("/mes/process")
public class ProcessController extends BaseController
{
    @Autowired
    private IProcessService processService;

    /**
     * 查询工序设置列表
     */
    @ApiOperation("查询工序设置列表")
    @PreAuthorize("@ss.hasPermi('mes:process:list')")
    @GetMapping("/list")
    public TableDataInfo list(Process process)
    {
        startPage();
        List<Process> list = processService.selectProcessList(process);
        return getDataTable(list);
    }

    /**
     * 导出工序设置列表
     */
    @PreAuthorize("@ss.hasPermi('mes:process:export')")
    @Log(title = "工序设置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Process process)
    {
        List<Process> list = processService.selectProcessList(process);
        ExcelUtil<Process> util = new ExcelUtil<Process>(Process.class);
        util.exportExcel(response, list, "工序设置数据");
    }

    /**
     * 获取工序设置详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:process:query')")
    @GetMapping(value = "/{processId}")
    public AjaxResult getInfo(@PathVariable("processId") Long processId)
    {
        return success(processService.selectProcessByProcessId(processId));
    }

    /**
     * 新增工序设置
     */
    @PreAuthorize("@ss.hasPermi('mes:process:add')")
    @Log(title = "工序设置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Process process)
    {
        return toAjax(processService.insertProcess(process));
    }

    /**
     * 修改工序设置
     */
    @PreAuthorize("@ss.hasPermi('mes:process:edit')")
    @Log(title = "工序设置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Process process)
    {
        return toAjax(processService.updateProcess(process));
    }

    /**
     * 删除工序设置
     */
    @PreAuthorize("@ss.hasPermi('mes:process:remove')")
    @Log(title = "工序设置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{processIds}")
    public AjaxResult remove(@PathVariable Long[] processIds)
    {
        return toAjax(processService.deleteProcessByProcessIds(processIds));
    }
}
