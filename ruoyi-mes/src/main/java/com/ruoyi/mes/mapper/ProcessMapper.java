package com.ruoyi.mes.mapper;

import java.util.List;
import com.ruoyi.mes.domain.Process;

/**
 * 工序设置Mapper接口
 * 
 * @author Yiming Guo
 * @date 2023-06-26
 */
public interface ProcessMapper 
{
    /**
     * 查询工序设置
     * 
     * @param processId 工序设置主键
     * @return 工序设置
     */
    public Process selectProcessByProcessId(Long processId);

    /**
     * 查询工序设置列表
     * 
     * @param process 工序设置
     * @return 工序设置集合
     */
    public List<Process> selectProcessList(Process process);

    /**
     * 新增工序设置
     * 
     * @param process 工序设置
     * @return 结果
     */
    public int insertProcess(Process process);

    /**
     * 修改工序设置
     * 
     * @param process 工序设置
     * @return 结果
     */
    public int updateProcess(Process process);

    /**
     * 删除工序设置
     * 
     * @param processId 工序设置主键
     * @return 结果
     */
    public int deleteProcessByProcessId(Long processId);

    /**
     * 批量删除工序设置
     * 
     * @param processIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProcessByProcessIds(Long[] processIds);
}
