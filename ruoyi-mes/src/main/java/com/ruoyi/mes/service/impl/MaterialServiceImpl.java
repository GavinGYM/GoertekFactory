package com.ruoyi.mes.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.mapper.MaterialMapper;
import com.ruoyi.mes.domain.Material;
import com.ruoyi.mes.service.IMaterialService;

/**
 * 物料分类Service业务层处理
 * 
 * @author Yiming Guo
 * @date 2023-06-29
 */
@Service
public class MaterialServiceImpl implements IMaterialService 
{
    @Autowired
    private MaterialMapper materialMapper;

    /**
     * 查询物料分类
     * 
     * @param materialId 物料分类主键
     * @return 物料分类
     */
    @Override
    public Material selectMaterialByMaterialId(Long materialId)
    {
        return materialMapper.selectMaterialByMaterialId(materialId);
    }

    /**
     * 查询物料分类列表
     * 
     * @param material 物料分类
     * @return 物料分类
     */
    @Override
    public List<Material> selectMaterialList(Material material)
    {
        return materialMapper.selectMaterialList(material);
    }

    /**
     * 新增物料分类
     * 
     * @param material 物料分类
     * @return 结果
     */
    @Override
    public int insertMaterial(Material material)
    {
        return materialMapper.insertMaterial(material);
    }

    /**
     * 修改物料分类
     * 
     * @param material 物料分类
     * @return 结果
     */
    @Override
    public int updateMaterial(Material material)
    {
        return materialMapper.updateMaterial(material);
    }

    /**
     * 批量删除物料分类
     * 
     * @param materialIds 需要删除的物料分类主键
     * @return 结果
     */
    @Override
    public int deleteMaterialByMaterialIds(Long[] materialIds)
    {
        return materialMapper.deleteMaterialByMaterialIds(materialIds);
    }

    /**
     * 删除物料分类信息
     * 
     * @param materialId 物料分类主键
     * @return 结果
     */
    @Override
    public int deleteMaterialByMaterialId(Long materialId)
    {
        return materialMapper.deleteMaterialByMaterialId(materialId);
    }
}
