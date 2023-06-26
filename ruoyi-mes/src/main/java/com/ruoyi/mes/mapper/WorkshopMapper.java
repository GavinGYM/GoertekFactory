package com.ruoyi.mes.mapper;

import java.util.List;
import com.ruoyi.mes.domain.Workshop;

/**
 * 车间信息Mapper接口
 * 
 * @author Yiming Guo
 * @date 2023-06-26
 */
public interface WorkshopMapper 
{
    /**
     * 查询车间信息
     * 
     * @param workshopId 车间信息主键
     * @return 车间信息
     */
    public Workshop selectWorkshopByWorkshopId(Long workshopId);

    /**
     * 查询车间信息列表
     * 
     * @param workshop 车间信息
     * @return 车间信息集合
     */
    public List<Workshop> selectWorkshopList(Workshop workshop);

    /**
     * 新增车间信息
     * 
     * @param workshop 车间信息
     * @return 结果
     */
    public int insertWorkshop(Workshop workshop);

    /**
     * 修改车间信息
     * 
     * @param workshop 车间信息
     * @return 结果
     */
    public int updateWorkshop(Workshop workshop);

    /**
     * 删除车间信息
     * 
     * @param workshopId 车间信息主键
     * @return 结果
     */
    public int deleteWorkshopByWorkshopId(Long workshopId);

    /**
     * 批量删除车间信息
     * 
     * @param workshopIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWorkshopByWorkshopIds(Long[] workshopIds);
}
