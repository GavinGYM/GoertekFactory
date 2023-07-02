package com.ruoyi.mes.mapper;

import java.util.List;
import com.ruoyi.mes.domain.Material;

/**
 * 物料分类Mapper接口
 * 
 * @author Yiming Guo
 * @date 2023-06-29
 */
public interface MaterialMapper 
{
    /**
     * 查询物料分类
     * 
     * @param materialId 物料分类主键
     * @return 物料分类
     */
    public Material selectMaterialByMaterialId(Long materialId);

    /**
     * 查询物料分类列表
     * 
     * @param material 物料分类
     * @return 物料分类集合
     */
    public List<Material> selectMaterialList(Material material);

    /**
     * 新增物料分类
     * 
     * @param material 物料分类
     * @return 结果
     */
    public int insertMaterial(Material material);

    /**
     * 修改物料分类
     * 
     * @param material 物料分类
     * @return 结果
     */
    public int updateMaterial(Material material);

    /**
     * 删除物料分类
     * 
     * @param materialId 物料分类主键
     * @return 结果
     */
    public int deleteMaterialByMaterialId(Long materialId);

    /**
     * 批量删除物料分类
     * 
     * @param materialIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMaterialByMaterialIds(Long[] materialIds);
}
