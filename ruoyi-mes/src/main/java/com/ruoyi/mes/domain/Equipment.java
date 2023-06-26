package com.ruoyi.mes.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 设备信息对象 mes_equipment
 * 
 * @author Yiming Guo
 * @date 2023-06-26
 */
public class Equipment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 设备编号 */
    private Long equipmentId;

    /** 设备名称 */
    @Excel(name = "设备名称")
    private String equipmentName;

    /** 品牌 */
    @Excel(name = "品牌")
    private String equipmentBrand;

    /** 型号 */
    @Excel(name = "型号")
    private String equipmentModel;

    /** 设备类型 */
    @Excel(name = "设备类型")
    private String equipmentType;

    /** 设备序列号 */
    @Excel(name = "设备序列号")
    private String equipmentCode;

    /** 设备状态 */
    @Excel(name = "设备状态")
    private Long equipmentRunningStatus;

    /** 启用状态 */
    @Excel(name = "启用状态")
    private Long equipmentActiveStatus;

    /** 添加时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date equipmentAddTime;

    public void setEquipmentId(Long equipmentId) 
    {
        this.equipmentId = equipmentId;
    }

    public Long getEquipmentId() 
    {
        return equipmentId;
    }
    public void setEquipmentName(String equipmentName) 
    {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentName() 
    {
        return equipmentName;
    }
    public void setEquipmentBrand(String equipmentBrand) 
    {
        this.equipmentBrand = equipmentBrand;
    }

    public String getEquipmentBrand() 
    {
        return equipmentBrand;
    }
    public void setEquipmentModel(String equipmentModel) 
    {
        this.equipmentModel = equipmentModel;
    }

    public String getEquipmentModel() 
    {
        return equipmentModel;
    }
    public void setEquipmentType(String equipmentType) 
    {
        this.equipmentType = equipmentType;
    }

    public String getEquipmentType() 
    {
        return equipmentType;
    }
    public void setEquipmentCode(String equipmentCode) 
    {
        this.equipmentCode = equipmentCode;
    }

    public String getEquipmentCode() 
    {
        return equipmentCode;
    }
    public void setEquipmentRunningStatus(Long equipmentRunningStatus) 
    {
        this.equipmentRunningStatus = equipmentRunningStatus;
    }

    public Long getEquipmentRunningStatus() 
    {
        return equipmentRunningStatus;
    }
    public void setEquipmentActiveStatus(Long equipmentActiveStatus) 
    {
        this.equipmentActiveStatus = equipmentActiveStatus;
    }

    public Long getEquipmentActiveStatus() 
    {
        return equipmentActiveStatus;
    }
    public void setEquipmentAddTime(Date equipmentAddTime) 
    {
        this.equipmentAddTime = equipmentAddTime;
    }

    public Date getEquipmentAddTime() 
    {
        return equipmentAddTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("equipmentId", getEquipmentId())
            .append("equipmentName", getEquipmentName())
            .append("equipmentBrand", getEquipmentBrand())
            .append("equipmentModel", getEquipmentModel())
            .append("equipmentType", getEquipmentType())
            .append("equipmentCode", getEquipmentCode())
            .append("equipmentRunningStatus", getEquipmentRunningStatus())
            .append("equipmentActiveStatus", getEquipmentActiveStatus())
            .append("equipmentAddTime", getEquipmentAddTime())
            .toString();
    }
}
