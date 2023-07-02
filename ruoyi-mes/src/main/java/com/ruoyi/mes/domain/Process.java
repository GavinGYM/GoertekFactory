package com.ruoyi.mes.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工序设置对象 mes_process
 * 
 * @author Yiming Guo
 * @date 2023-06-26
 */
public class Process extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long processId;

    /** 工序名 */
    @Excel(name = "工序名")
    private String processName;

    /** 工序描述 */
    @Excel(name = "工序描述")
    private String processDescription;

    /** 状态 */
    @Excel(name = "状态")
    private Long processStatus;

    /** 添加时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date processAddTime;

    public void setProcessId(Long processId) 
    {
        this.processId = processId;
    }

    public Long getProcessId() 
    {
        return processId;
    }
    public void setProcessName(String processName) 
    {
        this.processName = processName;
    }

    public String getProcessName() 
    {
        return processName;
    }
    public void setProcessDescription(String processDescription) 
    {
        this.processDescription = processDescription;
    }

    public String getProcessDescription() 
    {
        return processDescription;
    }
    public void setProcessStatus(Long processStatus) 
    {
        this.processStatus = processStatus;
    }

    public Long getProcessStatus() 
    {
        return processStatus;
    }
    public void setProcessAddTime(Date processAddTime) 
    {
        this.processAddTime = processAddTime;
    }

    public Date getProcessAddTime() 
    {
        return processAddTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("processId", getProcessId())
            .append("processName", getProcessName())
            .append("processDescription", getProcessDescription())
            .append("processStatus", getProcessStatus())
            .append("processAddTime", getProcessAddTime())
            .toString();
    }
}
