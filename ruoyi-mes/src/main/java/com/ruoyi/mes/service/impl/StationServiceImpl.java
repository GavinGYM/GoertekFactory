package com.ruoyi.mes.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.mapper.StationMapper;
import com.ruoyi.mes.domain.Station;
import com.ruoyi.mes.service.IStationService;

/**
 * 工位信息Service业务层处理
 * 
 * @author Yiming Guo
 * @date 2023-06-29
 */
@Service
public class StationServiceImpl implements IStationService 
{
    @Autowired
    private StationMapper stationMapper;

    /**
     * 查询工位信息
     * 
     * @param stationId 工位信息主键
     * @return 工位信息
     */
    @Override
    public Station selectStationByStationId(Long stationId)
    {
        return stationMapper.selectStationByStationId(stationId);
    }

    /**
     * 查询工位信息列表
     * 
     * @param station 工位信息
     * @return 工位信息
     */
    @Override
    public List<Station> selectStationList(Station station)
    {
        return stationMapper.selectStationList(station);
    }

    /**
     * 新增工位信息
     * 
     * @param station 工位信息
     * @return 结果
     */
    @Override
    public int insertStation(Station station)
    {
        return stationMapper.insertStation(station);
    }

    /**
     * 修改工位信息
     * 
     * @param station 工位信息
     * @return 结果
     */
    @Override
    public int updateStation(Station station)
    {
        return stationMapper.updateStation(station);
    }

    /**
     * 批量删除工位信息
     * 
     * @param stationIds 需要删除的工位信息主键
     * @return 结果
     */
    @Override
    public int deleteStationByStationIds(Long[] stationIds)
    {
        return stationMapper.deleteStationByStationIds(stationIds);
    }

    /**
     * 删除工位信息信息
     * 
     * @param stationId 工位信息主键
     * @return 结果
     */
    @Override
    public int deleteStationByStationId(Long stationId)
    {
        return stationMapper.deleteStationByStationId(stationId);
    }
}
