package com.ruoyi.mes.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.mapper.WorkshopMapper;
import com.ruoyi.mes.domain.Workshop;
import com.ruoyi.mes.service.IWorkshopService;

/**
 * 车间信息Service业务层处理
 * 
 * @author Yiming Guo
 * @date 2023-06-26
 */
@Service
public class WorkshopServiceImpl implements IWorkshopService 
{
    @Autowired
    private WorkshopMapper workshopMapper;

    /**
     * 查询车间信息
     * 
     * @param workshopId 车间信息主键
     * @return 车间信息
     */
    @Override
    public Workshop selectWorkshopByWorkshopId(Long workshopId)
    {
        return workshopMapper.selectWorkshopByWorkshopId(workshopId);
    }

    /**
     * 查询车间信息列表
     * 
     * @param workshop 车间信息
     * @return 车间信息
     */
    @Override
    public List<Workshop> selectWorkshopList(Workshop workshop)
    {
        return workshopMapper.selectWorkshopList(workshop);
    }

    /**
     * 新增车间信息
     * 
     * @param workshop 车间信息
     * @return 结果
     */
    @Override
    public int insertWorkshop(Workshop workshop)
    {
        return workshopMapper.insertWorkshop(workshop);
    }

    /**
     * 修改车间信息
     * 
     * @param workshop 车间信息
     * @return 结果
     */
    @Override
    public int updateWorkshop(Workshop workshop)
    {
        return workshopMapper.updateWorkshop(workshop);
    }

    /**
     * 批量删除车间信息
     * 
     * @param workshopIds 需要删除的车间信息主键
     * @return 结果
     */
    @Override
    public int deleteWorkshopByWorkshopIds(Long[] workshopIds)
    {
        return workshopMapper.deleteWorkshopByWorkshopIds(workshopIds);
    }

    /**
     * 删除车间信息信息
     * 
     * @param workshopId 车间信息主键
     * @return 结果
     */
    @Override
    public int deleteWorkshopByWorkshopId(Long workshopId)
    {
        return workshopMapper.deleteWorkshopByWorkshopId(workshopId);
    }
}
