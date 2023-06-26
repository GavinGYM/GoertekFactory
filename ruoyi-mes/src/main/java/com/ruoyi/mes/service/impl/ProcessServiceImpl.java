package com.ruoyi.mes.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.mapper.ProcessMapper;
import com.ruoyi.mes.domain.Process;
import com.ruoyi.mes.service.IProcessService;

/**
 * 工序设置Service业务层处理
 * 
 * @author Yiming Guo
 * @date 2023-06-26
 */
@Service
public class ProcessServiceImpl implements IProcessService 
{
    @Autowired
    private ProcessMapper processMapper;

    /**
     * 查询工序设置
     * 
     * @param processId 工序设置主键
     * @return 工序设置
     */
    @Override
    public Process selectProcessByProcessId(Long processId)
    {
        return processMapper.selectProcessByProcessId(processId);
    }

    /**
     * 查询工序设置列表
     * 
     * @param process 工序设置
     * @return 工序设置
     */
    @Override
    public List<Process> selectProcessList(Process process)
    {
        return processMapper.selectProcessList(process);
    }

    /**
     * 新增工序设置
     * 
     * @param process 工序设置
     * @return 结果
     */
    @Override
    public int insertProcess(Process process)
    {
        return processMapper.insertProcess(process);
    }

    /**
     * 修改工序设置
     * 
     * @param process 工序设置
     * @return 结果
     */
    @Override
    public int updateProcess(Process process)
    {
        return processMapper.updateProcess(process);
    }

    /**
     * 批量删除工序设置
     * 
     * @param processIds 需要删除的工序设置主键
     * @return 结果
     */
    @Override
    public int deleteProcessByProcessIds(Long[] processIds)
    {
        return processMapper.deleteProcessByProcessIds(processIds);
    }

    /**
     * 删除工序设置信息
     * 
     * @param processId 工序设置主键
     * @return 结果
     */
    @Override
    public int deleteProcessByProcessId(Long processId)
    {
        return processMapper.deleteProcessByProcessId(processId);
    }
}
