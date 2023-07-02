package com.ruoyi.mes.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.mapper.MaterialItemMapper;
import com.ruoyi.mes.domain.MaterialItem;
import com.ruoyi.mes.service.IMaterialItemService;

/**
 * 物料信息Service业务层处理
 * 
 * @author Yiming Guo
 * @date 2023-06-30
 */
@Service
public class MaterialItemServiceImpl implements IMaterialItemService 
{
    @Autowired
    private MaterialItemMapper materialItemMapper;

    /**
     * 查询物料信息
     * 
     * @param materialItemId 物料信息主键
     * @return 物料信息
     */
    @Override
    public MaterialItem selectMaterialItemByMaterialItemId(Long materialItemId)
    {
        return materialItemMapper.selectMaterialItemByMaterialItemId(materialItemId);
    }

    /**
     * 查询物料信息列表
     * 
     * @param materialItem 物料信息
     * @return 物料信息
     */
    @Override
    public List<MaterialItem> selectMaterialItemList(MaterialItem materialItem)
    {
        return materialItemMapper.selectMaterialItemList(materialItem);
    }

    /**
     * 新增物料信息
     * 
     * @param materialItem 物料信息
     * @return 结果
     */
    @Override
    public int insertMaterialItem(MaterialItem materialItem)
    {
        return materialItemMapper.insertMaterialItem(materialItem);
    }

    /**
     * 修改物料信息
     * 
     * @param materialItem 物料信息
     * @return 结果
     */
    @Override
    public int updateMaterialItem(MaterialItem materialItem)
    {
        return materialItemMapper.updateMaterialItem(materialItem);
    }

    /**
     * 批量删除物料信息
     * 
     * @param materialItemIds 需要删除的物料信息主键
     * @return 结果
     */
    @Override
    public int deleteMaterialItemByMaterialItemIds(Long[] materialItemIds)
    {
        return materialItemMapper.deleteMaterialItemByMaterialItemIds(materialItemIds);
    }

    /**
     * 删除物料信息信息
     * 
     * @param materialItemId 物料信息主键
     * @return 结果
     */
    @Override
    public int deleteMaterialItemByMaterialItemId(Long materialItemId)
    {
        return materialItemMapper.deleteMaterialItemByMaterialItemId(materialItemId);
    }
}
