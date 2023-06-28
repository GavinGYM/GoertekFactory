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
import com.ruoyi.mes.domain.Route;
import com.ruoyi.mes.service.IRouteService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 工序路线Controller
 * 
 * @author Yiming Guo
 * @date 2023-06-28
 */
@RestController
@RequestMapping("/mes/route")
public class RouteController extends BaseController
{
    @Autowired
    private IRouteService routeService;

    /**
     * 查询工序路线列表
     */
    @PreAuthorize("@ss.hasPermi('mes:route:list')")
    @GetMapping("/list")
    public TableDataInfo list(Route route)
    {
        startPage();
        List<Route> list = routeService.selectRouteList(route);
        return getDataTable(list);
    }

    /**
     * 导出工序路线列表
     */
    @PreAuthorize("@ss.hasPermi('mes:route:export')")
    @Log(title = "工序路线", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Route route)
    {
        List<Route> list = routeService.selectRouteList(route);
        ExcelUtil<Route> util = new ExcelUtil<Route>(Route.class);
        util.exportExcel(response, list, "工序路线数据");
    }

    /**
     * 获取工序路线详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:route:query')")
    @GetMapping(value = "/{routeId}")
    public AjaxResult getInfo(@PathVariable("routeId") Long routeId)
    {
        return success(routeService.selectRouteByRouteId(routeId));
    }

    /**
     * 新增工序路线
     */
    @PreAuthorize("@ss.hasPermi('mes:route:add')")
    @Log(title = "工序路线", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Route route)
    {
        return toAjax(routeService.insertRoute(route));
    }

    /**
     * 修改工序路线
     */
    @PreAuthorize("@ss.hasPermi('mes:route:edit')")
    @Log(title = "工序路线", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Route route)
    {
        return toAjax(routeService.updateRoute(route));
    }

    /**
     * 删除工序路线
     */
    @PreAuthorize("@ss.hasPermi('mes:route:remove')")
    @Log(title = "工序路线", businessType = BusinessType.DELETE)
	@DeleteMapping("/{routeIds}")
    public AjaxResult remove(@PathVariable Long[] routeIds)
    {
        return toAjax(routeService.deleteRouteByRouteIds(routeIds));
    }
}
