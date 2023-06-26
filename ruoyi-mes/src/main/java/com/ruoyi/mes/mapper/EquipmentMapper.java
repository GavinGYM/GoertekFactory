package com.ruoyi.mes.mapper;

import java.util.List;
import com.ruoyi.mes.domain.Equipment;

/**
 * 设备信息Mapper接口
 * 
 * @author Yiming Guo
 * @date 2023-06-26
 */
public interface EquipmentMapper 
{
    /**
     * 查询设备信息
     * 
     * @param equipmentId 设备信息主键
     * @return 设备信息
     */
    public Equipment selectEquipmentByEquipmentId(Long equipmentId);

    /**
     * 查询设备信息列表
     * 
     * @param equipment 设备信息
     * @return 设备信息集合
     */
    public List<Equipment> selectEquipmentList(Equipment equipment);

    /**
     * 新增设备信息
     * 
     * @param equipment 设备信息
     * @return 结果
     */
    public int insertEquipment(Equipment equipment);

    /**
     * 修改设备信息
     * 
     * @param equipment 设备信息
     * @return 结果
     */
    public int updateEquipment(Equipment equipment);

    /**
     * 删除设备信息
     * 
     * @param equipmentId 设备信息主键
     * @return 结果
     */
    public int deleteEquipmentByEquipmentId(Long equipmentId);

    /**
     * 批量删除设备信息
     * 
     * @param equipmentIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEquipmentByEquipmentIds(Long[] equipmentIds);
}
