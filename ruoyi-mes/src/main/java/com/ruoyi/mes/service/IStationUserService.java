package com.ruoyi.mes.service;

import java.util.List;
import com.ruoyi.mes.domain.StationUser;

/**
 * 工位绑定Service接口
 * 
 * @author Yiming Guo
 * @date 2023-07-03
 */
public interface IStationUserService 
{
    /**
     * 查询工位绑定
     * 
     * @param stationUserId 工位绑定主键
     * @return 工位绑定
     */
    public StationUser selectStationUserByStationUserId(Long stationUserId);

    /**
     * 查询工位绑定列表
     * 
     * @param stationUser 工位绑定
     * @return 工位绑定集合
     */
    public List<StationUser> selectStationUserList(StationUser stationUser);

    /**
     * 新增工位绑定
     * 
     * @param stationUser 工位绑定
     * @return 结果
     */
    public int insertStationUser(StationUser stationUser);

    /**
     * 修改工位绑定
     * 
     * @param stationUser 工位绑定
     * @return 结果
     */
    public int updateStationUser(StationUser stationUser);

    /**
     * 批量删除工位绑定
     * 
     * @param stationUserIds 需要删除的工位绑定主键集合
     * @return 结果
     */
    public int deleteStationUserByStationUserIds(Long[] stationUserIds);

    /**
     * 删除工位绑定信息
     * 
     * @param stationUserId 工位绑定主键
     * @return 结果
     */
    public int deleteStationUserByStationUserId(Long stationUserId);
}
