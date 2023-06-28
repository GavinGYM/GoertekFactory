package com.ruoyi.mes.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.mes.domain.RouteProcess;
import com.ruoyi.mes.mapper.RouteMapper;
import com.ruoyi.mes.domain.Route;
import com.ruoyi.mes.service.IRouteService;

/**
 * 工序路线Service业务层处理
 * 
 * @author Yiming Guo
 * @date 2023-06-28
 */
@Service
public class RouteServiceImpl implements IRouteService 
{
    @Autowired
    private RouteMapper routeMapper;

    /**
     * 查询工序路线
     * 
     * @param routeId 工序路线主键
     * @return 工序路线
     */
    @Override
    public Route selectRouteByRouteId(Long routeId)
    {
        return routeMapper.selectRouteByRouteId(routeId);
    }

    /**
     * 查询工序路线列表
     * 
     * @param route 工序路线
     * @return 工序路线
     */
    @Override
    public List<Route> selectRouteList(Route route)
    {
        return routeMapper.selectRouteList(route);
    }

    /**
     * 新增工序路线
     * 
     * @param route 工序路线
     * @return 结果
     */
    @Transactional
    @Override
    public int insertRoute(Route route)
    {
        int rows = routeMapper.insertRoute(route);
        insertRouteProcess(route);
        return rows;
    }

    /**
     * 修改工序路线
     * 
     * @param route 工序路线
     * @return 结果
     */
    @Transactional
    @Override
    public int updateRoute(Route route)
    {
        routeMapper.deleteRouteProcessByRouteId(route.getRouteId());
        insertRouteProcess(route);
        return routeMapper.updateRoute(route);
    }

    /**
     * 批量删除工序路线
     * 
     * @param routeIds 需要删除的工序路线主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteRouteByRouteIds(Long[] routeIds)
    {
        routeMapper.deleteRouteProcessByRouteIds(routeIds);
        return routeMapper.deleteRouteByRouteIds(routeIds);
    }

    /**
     * 删除工序路线信息
     * 
     * @param routeId 工序路线主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteRouteByRouteId(Long routeId)
    {
        routeMapper.deleteRouteProcessByRouteId(routeId);
        return routeMapper.deleteRouteByRouteId(routeId);
    }

    /**
     * 新增工序与路线关系信息
     * 
     * @param route 工序路线对象
     */
    public void insertRouteProcess(Route route)
    {
        List<RouteProcess> routeProcessList = route.getRouteProcessList();
        Long routeId = route.getRouteId();
        if (StringUtils.isNotNull(routeProcessList))
        {
            List<RouteProcess> list = new ArrayList<RouteProcess>();
            for (RouteProcess routeProcess : routeProcessList)
            {
                routeProcess.setRouteId(routeId);
                list.add(routeProcess);
            }
            if (list.size() > 0)
            {
                routeMapper.batchRouteProcess(list);
            }
        }
    }
}
