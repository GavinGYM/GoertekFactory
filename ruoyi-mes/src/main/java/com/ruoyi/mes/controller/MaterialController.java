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
import com.ruoyi.mes.domain.Material;
import com.ruoyi.mes.service.IMaterialService;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 物料分类Controller
 * 
 * @author Yiming Guo
 * @date 2023-06-29
 */
@RestController
@RequestMapping("/mes/material")
public class MaterialController extends BaseController
{
    @Autowired
    private IMaterialService materialService;

    /**
     * 查询物料分类列表
     */
    @PreAuthorize("@ss.hasPermi('mes:material:list')")
    @GetMapping("/list")
    public AjaxResult list(Material material)
    {
        List<Material> list = materialService.selectMaterialList(material);
        return success(list);
    }

    /**
     * 导出物料分类列表
     */
    @PreAuthorize("@ss.hasPermi('mes:material:export')")
    @Log(title = "物料分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Material material)
    {
        List<Material> list = materialService.selectMaterialList(material);
        ExcelUtil<Material> util = new ExcelUtil<Material>(Material.class);
        util.exportExcel(response, list, "物料分类数据");
    }

    /**
     * 获取物料分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:material:query')")
    @GetMapping(value = "/{materialId}")
    public AjaxResult getInfo(@PathVariable("materialId") Long materialId)
    {
        return success(materialService.selectMaterialByMaterialId(materialId));
    }

    /**
     * 新增物料分类
     */
    @PreAuthorize("@ss.hasPermi('mes:material:add')")
    @Log(title = "物料分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Material material)
    {
        return toAjax(materialService.insertMaterial(material));
    }

    /**
     * 修改物料分类
     */
    @PreAuthorize("@ss.hasPermi('mes:material:edit')")
    @Log(title = "物料分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Material material)
    {
        return toAjax(materialService.updateMaterial(material));
    }

    /**
     * 删除物料分类
     */
    @PreAuthorize("@ss.hasPermi('mes:material:remove')")
    @Log(title = "物料分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{materialIds}")
    public AjaxResult remove(@PathVariable Long[] materialIds)
    {
        return toAjax(materialService.deleteMaterialByMaterialIds(materialIds));
    }
}
