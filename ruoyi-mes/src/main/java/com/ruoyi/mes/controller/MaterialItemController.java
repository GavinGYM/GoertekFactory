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
import com.ruoyi.mes.domain.MaterialItem;
import com.ruoyi.mes.service.IMaterialItemService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 物料信息Controller
 * 
 * @author Yiming Guo
 * @date 2023-06-30
 */
@RestController
@RequestMapping("/mes/item")
public class MaterialItemController extends BaseController
{
    @Autowired
    private IMaterialItemService materialItemService;

    /**
     * 查询物料信息列表
     */
    @PreAuthorize("@ss.hasPermi('mes:item:list')")
    @GetMapping("/list")
    public TableDataInfo list(MaterialItem materialItem)
    {
        startPage();
        List<MaterialItem> list = materialItemService.selectMaterialItemList(materialItem);
        return getDataTable(list);
    }

    /**
     * 导出物料信息列表
     */
    @PreAuthorize("@ss.hasPermi('mes:item:export')")
    @Log(title = "物料信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MaterialItem materialItem)
    {
        List<MaterialItem> list = materialItemService.selectMaterialItemList(materialItem);
        ExcelUtil<MaterialItem> util = new ExcelUtil<MaterialItem>(MaterialItem.class);
        util.exportExcel(response, list, "物料信息数据");
    }

    /**
     * 获取物料信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:item:query')")
    @GetMapping(value = "/{materialItemId}")
    public AjaxResult getInfo(@PathVariable("materialItemId") Long materialItemId)
    {
        return success(materialItemService.selectMaterialItemByMaterialItemId(materialItemId));
    }

    /**
     * 新增物料信息
     */
    @PreAuthorize("@ss.hasPermi('mes:item:add')")
    @Log(title = "物料信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MaterialItem materialItem)
    {
        return toAjax(materialItemService.insertMaterialItem(materialItem));
    }

    /**
     * 修改物料信息
     */
    @PreAuthorize("@ss.hasPermi('mes:item:edit')")
    @Log(title = "物料信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MaterialItem materialItem)
    {
        return toAjax(materialItemService.updateMaterialItem(materialItem));
    }

    /**
     * 删除物料信息
     */
    @PreAuthorize("@ss.hasPermi('mes:item:remove')")
    @Log(title = "物料信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{materialItemIds}")
    public AjaxResult remove(@PathVariable Long[] materialItemIds)
    {
        return toAjax(materialItemService.deleteMaterialItemByMaterialItemIds(materialItemIds));
    }
}
