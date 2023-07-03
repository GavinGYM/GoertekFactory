package com.ruoyi.mes.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.mes.mapper.StationMapper;
import com.ruoyi.mes.mapper.WorkshopMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.mapper.StationUserMapper;
import com.ruoyi.mes.domain.StationUser;
import com.ruoyi.mes.service.IStationUserService;
import com.ruoyi.system.mapper.SysUserMapper;

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

    @Autowired
    private StationMapper stationMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private WorkshopMapper workshopMapper;

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
        List<StationUser> stationUserList = stationUserMapper.selectStationUserList(stationUser);
        for (StationUser stationUser1 : stationUserList) {
            stationUser1.setStationName(stationMapper.selectStationByStationId(stationUser1.getStationId()).getStationName());
            stationUser1.setUserName(sysUserMapper.selectUserById(stationUser1.getUserId()).getUserName());
            stationUser1.setUserNickName(sysUserMapper.selectUserById(stationUser1.getUserId()).getNickName());
            stationUser1.setWorkshopName(workshopMapper.selectWorkshopByWorkshopId(
                    stationMapper.selectStationByStationId(stationUser1.getStationId()).getWorkshopId()).getWorkshopName());
            stationUser1.setAddUserName(sysUserMapper.selectUserById(stationUser1.getAddUserId()).getUserName());
        }
        return stationUserList;
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
        LoginUser loginUser = SecurityUtils.getLoginUser();
        stationUser.setAddUserId(loginUser.getUser().getUserId());
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
