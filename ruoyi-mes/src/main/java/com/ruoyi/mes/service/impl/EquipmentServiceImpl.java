package com.ruoyi.mes.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.mapper.EquipmentMapper;
import com.ruoyi.mes.domain.Equipment;
import com.ruoyi.mes.service.IEquipmentService;

/**
 * 设备信息Service业务层处理
 * 
 * @author Yiming Guo
 * @date 2023-06-26
 */
@Service
public class EquipmentServiceImpl implements IEquipmentService 
{
    @Autowired
    private EquipmentMapper equipmentMapper;

    /**
     * 查询设备信息
     * 
     * @param equipmentId 设备信息主键
     * @return 设备信息
     */
    @Override
    public Equipment selectEquipmentByEquipmentId(Long equipmentId)
    {
        return equipmentMapper.selectEquipmentByEquipmentId(equipmentId);
    }

    /**
     * 查询设备信息列表
     * 
     * @param equipment 设备信息
     * @return 设备信息
     */
    @Override
    public List<Equipment> selectEquipmentList(Equipment equipment)
    {
        return equipmentMapper.selectEquipmentList(equipment);
    }

    /**
     * 新增设备信息
     * 
     * @param equipment 设备信息
     * @return 结果
     */
    @Override
    public int insertEquipment(Equipment equipment)
    {
        return equipmentMapper.insertEquipment(equipment);
    }

    /**
     * 修改设备信息
     * 
     * @param equipment 设备信息
     * @return 结果
     */
    @Override
    public int updateEquipment(Equipment equipment)
    {
        return equipmentMapper.updateEquipment(equipment);
    }

    /**
     * 批量删除设备信息
     * 
     * @param equipmentIds 需要删除的设备信息主键
     * @return 结果
     */
    @Override
    public int deleteEquipmentByEquipmentIds(Long[] equipmentIds)
    {
        return equipmentMapper.deleteEquipmentByEquipmentIds(equipmentIds);
    }

    /**
     * 删除设备信息信息
     * 
     * @param equipmentId 设备信息主键
     * @return 结果
     */
    @Override
    public int deleteEquipmentByEquipmentId(Long equipmentId)
    {
        return equipmentMapper.deleteEquipmentByEquipmentId(equipmentId);
    }
}
