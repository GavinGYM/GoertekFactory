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
import com.ruoyi.mes.domain.Workshop;
import com.ruoyi.mes.service.IWorkshopService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 车间信息Controller
 * 
 * @author Yiming Guo
 * @date 2023-06-26
 */
@RestController
@RequestMapping("/mes/workshop")
public class WorkshopController extends BaseController
{
    @Autowired
    private IWorkshopService workshopService;

    /**
     * 查询车间信息列表
     */
    @PreAuthorize("@ss.hasPermi('mes:workshop:list')")
    @GetMapping("/list")
    public TableDataInfo list(Workshop workshop)
    {
        startPage();
        List<Workshop> list = workshopService.selectWorkshopList(workshop);
        return getDataTable(list);
    }

    /**
     * 导出车间信息列表
     */
    @PreAuthorize("@ss.hasPermi('mes:workshop:export')")
    @Log(title = "车间信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Workshop workshop)
    {
        List<Workshop> list = workshopService.selectWorkshopList(workshop);
        ExcelUtil<Workshop> util = new ExcelUtil<Workshop>(Workshop.class);
        util.exportExcel(response, list, "车间信息数据");
    }

    /**
     * 获取车间信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:workshop:query')")
    @GetMapping(value = "/{workshopId}")
    public AjaxResult getInfo(@PathVariable("workshopId") Long workshopId)
    {
        return success(workshopService.selectWorkshopByWorkshopId(workshopId));
    }

    /**
     * 新增车间信息
     */
    @PreAuthorize("@ss.hasPermi('mes:workshop:add')")
    @Log(title = "车间信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Workshop workshop)
    {
        return toAjax(workshopService.insertWorkshop(workshop));
    }

    /**
     * 修改车间信息
     */
    @PreAuthorize("@ss.hasPermi('mes:workshop:edit')")
    @Log(title = "车间信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Workshop workshop)
    {
        return toAjax(workshopService.updateWorkshop(workshop));
    }

    /**
     * 删除车间信息
     */
    @PreAuthorize("@ss.hasPermi('mes:workshop:remove')")
    @Log(title = "车间信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{workshopIds}")
    public AjaxResult remove(@PathVariable Long[] workshopIds)
    {
        return toAjax(workshopService.deleteWorkshopByWorkshopIds(workshopIds));
    }
}
