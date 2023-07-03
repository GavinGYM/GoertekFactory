package com.ruoyi.mes.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.mapper.StationUserMapper;
import com.ruoyi.mes.domain.StationUser;
import com.ruoyi.mes.service.IStationUserService;

/**
 * 工位绑定Service业务层处理
 * 
 * @author Yiming Guo
 * @date 2023-07-03
 */
@Service
public class StationUserServiceImpl implements IStationUserService 
{
    @Autowired
    private StationUserMapper stationUserMapper;

    /**
     * 查询工位绑定
     * 
     * @param stationUserId 工位绑定主键
     * @return 工位绑定
     */
    @Override
    public StationUser selectStationUserByStationUserId(Long stationUserId)
    {
        return stationUserMapper.selectStationUserByStationUserId(stationUserId);
    }

    /**
     * 查询工位绑定列表
     * 
     * @param stationUser 工位绑定
     * @return 工位绑定
     */
    @Override
    public List<StationUser> selectStationUserList(StationUser stationUser)
    {
        return stationUserMapper.selectStationUserList(stationUser);
    }

    /**
     * 新增工位绑定
     * 
     * @param stationUser 工位绑定
     * @return 结果
     */
    @Override
    public int insertStationUser(StationUser stationUser)
    {
        return stationUserMapper.insertStationUser(stationUser);
    }

    /**
     * 修改工位绑定
     * 
     * @param stationUser 工位绑定
     * @return 结果
     */
    @Override
    public int updateStationUser(StationUser stationUser)
    {
        return stationUserMapper.updateStationUser(stationUser);
    }

    /**
     * 批量删除工位绑定
     * 
     * @param stationUserIds 需要删除的工位绑定主键
     * @return 结果
     */
    @Override
    public int deleteStationUserByStationUserIds(Long[] stationUserIds)
    {
        return stationUserMapper.deleteStationUserByStationUserIds(stationUserIds);
    }

    /**
     * 删除工位绑定信息
     * 
     * @param stationUserId 工位绑定主键
     * @return 结果
     */
    @Override
    public int deleteStationUserByStationUserId(Long stationUserId)
    {
        return stationUserMapper.deleteStationUserByStationUserId(stationUserId);
    }
}
