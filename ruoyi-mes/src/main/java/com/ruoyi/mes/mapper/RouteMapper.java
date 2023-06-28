package com.ruoyi.mes.mapper;

import java.util.List;
import com.ruoyi.mes.domain.Route;
import com.ruoyi.mes.domain.RouteProcess;

/**
 * 工序路线Mapper接口
 * 
 * @author Yiming Guo
 * @date 2023-06-28
 */
public interface RouteMapper 
{
    /**
     * 查询工序路线
     * 
     * @param routeId 工序路线主键
     * @return 工序路线
     */
    public Route selectRouteByRouteId(Long routeId);

    /**
     * 查询工序路线列表
     * 
     * @param route 工序路线
     * @return 工序路线集合
     */
    public List<Route> selectRouteList(Route route);

    /**
     * 新增工序路线
     * 
     * @param route 工序路线
     * @return 结果
     */
    public int insertRoute(Route route);

    /**
     * 修改工序路线
     * 
     * @param route 工序路线
     * @return 结果
     */
    public int updateRoute(Route route);

    /**
     * 删除工序路线
     * 
     * @param routeId 工序路线主键
     * @return 结果
     */
    public int deleteRouteByRouteId(Long routeId);

    /**
     * 批量删除工序路线
     * 
     * @param routeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteRouteByRouteIds(Long[] routeIds);

    /**
     * 批量删除工序与路线关系
     * 
     * @param routeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteRouteProcessByRouteIds(Long[] routeIds);
    
    /**
     * 批量新增工序与路线关系
     * 
     * @param routeProcessList 工序与路线关系列表
     * @return 结果
     */
    public int batchRouteProcess(List<RouteProcess> routeProcessList);
    

    /**
     * 通过工序路线主键删除工序与路线关系信息
     * 
     * @param routeId 工序路线ID
     * @return 结果
     */
    public int deleteRouteProcessByRouteId(Long routeId);
}
