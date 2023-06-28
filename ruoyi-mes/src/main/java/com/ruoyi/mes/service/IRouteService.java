package com.ruoyi.mes.service;

import java.util.List;
import com.ruoyi.mes.domain.Route;

/**
 * 工序路线Service接口
 * 
 * @author Yiming Guo
 * @date 2023-06-28
 */
public interface IRouteService 
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
     * 批量删除工序路线
     * 
     * @param routeIds 需要删除的工序路线主键集合
     * @return 结果
     */
    public int deleteRouteByRouteIds(Long[] routeIds);

    /**
     * 删除工序路线信息
     * 
     * @param routeId 工序路线主键
     * @return 结果
     */
    public int deleteRouteByRouteId(Long routeId);
}
