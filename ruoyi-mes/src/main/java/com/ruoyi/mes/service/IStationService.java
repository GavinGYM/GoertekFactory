package com.ruoyi.mes.service;

import java.util.List;
import com.ruoyi.mes.domain.Station;

/**
 * 工位信息Service接口
 * 
 * @author Yiming Guo
 * @date 2023-06-29
 */
public interface IStationService 
{
    /**
     * 查询工位信息
     * 
     * @param stationId 工位信息主键
     * @return 工位信息
     */
    public Station selectStationByStationId(Long stationId);

    /**
     * 查询工位信息列表
     * 
     * @param station 工位信息
     * @return 工位信息集合
     */
    public List<Station> selectStationList(Station station);

    /**
     * 新增工位信息
     * 
     * @param station 工位信息
     * @return 结果
     */
    public int insertStation(Station station);

    /**
     * 修改工位信息
     * 
     * @param station 工位信息
     * @return 结果
     */
    public int updateStation(Station station);

    /**
     * 批量删除工位信息
     * 
     * @param stationIds 需要删除的工位信息主键集合
     * @return 结果
     */
    public int deleteStationByStationIds(Long[] stationIds);

    /**
     * 删除工位信息信息
     * 
     * @param stationId 工位信息主键
     * @return 结果
     */
    public int deleteStationByStationId(Long stationId);
}
