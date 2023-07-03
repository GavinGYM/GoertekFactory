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
import com.ruoyi.mes.domain.StationUser;
import com.ruoyi.mes.service.IStationUserService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 工位绑定Controller
 * 
 * @author Yiming Guo
 * @date 2023-07-03
 */
@RestController
@RequestMapping("/mes/stationUser")
public class StationUserController extends BaseController
{
    @Autowired
    private IStationUserService stationUserService;

    /**
     * 查询工位绑定列表
     */
    @PreAuthorize("@ss.hasPermi('mes:stationUser:list')")
    @GetMapping("/list")
    public TableDataInfo list(StationUser stationUser)
    {
        startPage();
        List<StationUser> list = stationUserService.selectStationUserList(stationUser);
        return getDataTable(list);
    }

    /**
     * 导出工位绑定列表
     */
    @PreAuthorize("@ss.hasPermi('mes:stationUser:export')")
    @Log(title = "工位绑定", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StationUser stationUser)
    {
        List<StationUser> list = stationUserService.selectStationUserList(stationUser);
        ExcelUtil<StationUser> util = new ExcelUtil<StationUser>(StationUser.class);
        util.exportExcel(response, list, "工位绑定数据");
    }

    /**
     * 获取工位绑定详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:stationUser:query')")
    @GetMapping(value = "/{stationUserId}")
    public AjaxResult getInfo(@PathVariable("stationUserId") Long stationUserId)
    {
        return success(stationUserService.selectStationUserByStationUserId(stationUserId));
    }

    /**
     * 新增工位绑定
     */
    @PreAuthorize("@ss.hasPermi('mes:stationUser:add')")
    @Log(title = "工位绑定", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StationUser stationUser)
    {
        return toAjax(stationUserService.insertStationUser(stationUser));
    }

    /**
     * 修改工位绑定
     */
    @PreAuthorize("@ss.hasPermi('mes:stationUser:edit')")
    @Log(title = "工位绑定", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StationUser stationUser)
    {
        return toAjax(stationUserService.updateStationUser(stationUser));
    }

    /**
     * 删除工位绑定
     */
    @PreAuthorize("@ss.hasPermi('mes:stationUser:remove')")
    @Log(title = "工位绑定", businessType = BusinessType.DELETE)
	@DeleteMapping("/{stationUserIds}")
    public AjaxResult remove(@PathVariable Long[] stationUserIds)
    {
        return toAjax(stationUserService.deleteStationUserByStationUserIds(stationUserIds));
    }
}
