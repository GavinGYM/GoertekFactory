package com.ruoyi.mes.mapper;

import java.util.List;
import com.ruoyi.mes.domain.MaterialItem;

/**
 * 物料信息Mapper接口
 * 
 * @author Yiming Guo
 * @date 2023-06-30
 */
public interface MaterialItemMapper 
{
    /**
     * 查询物料信息
     * 
     * @param materialItemId 物料信息主键
     * @return 物料信息
     */
    public MaterialItem selectMaterialItemByMaterialItemId(Long materialItemId);

    /**
     * 查询物料信息列表
     * 
     * @param materialItem 物料信息
     * @return 物料信息集合
     */
    public List<MaterialItem> selectMaterialItemList(MaterialItem materialItem);

    /**
     * 新增物料信息
     * 
     * @param materialItem 物料信息
     * @return 结果
     */
    public int insertMaterialItem(MaterialItem materialItem);

    /**
     * 修改物料信息
     * 
     * @param materialItem 物料信息
     * @return 结果
     */
    public int updateMaterialItem(MaterialItem materialItem);

    /**
     * 删除物料信息
     * 
     * @param materialItemId 物料信息主键
     * @return 结果
     */
    public int deleteMaterialItemByMaterialItemId(Long materialItemId);

    /**
     * 批量删除物料信息
     * 
     * @param materialItemIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMaterialItemByMaterialItemIds(Long[] materialItemIds);
}
