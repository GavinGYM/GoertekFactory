package com.ruoyi.mes.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 物料信息对象 mes_material_item
 * 
 * @author Yiming Guo
 * @date 2023-06-30
 */
public class MaterialItem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @Excel(name = "编号")
    private Long materialItemId;

    /** 物料名称 */
    @Excel(name = "物料名称")
    private String materialItemName;

    /** 规格型号 */
    @Excel(name = "规格型号")
    private String materialItemModel;

    /** 计量单位 */
    @Excel(name = "计量单位")
    private Long materialItemUnit;

    /** 物料分类 */
    @Excel(name = "物料分类")
    private Long materialItemClass;

    /** 物料类型 */
    @Excel(name = "物料类型")
    private Long materialItemType;

    /** 绑定工序路线 */
    private Long materialItemRoute;

    /** 状态 */
    @Excel(name = "状态")
    private Long materialItemStatus;

    /** 添加时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date materialItemAddTime;

    /** 备注 */
    private String materialItemComment;

    public void setMaterialItemId(Long materialItemId) 
    {
        this.materialItemId = materialItemId;
    }

    public Long getMaterialItemId() 
    {
        return materialItemId;
    }
    public void setMaterialItemName(String materialItemName) 
    {
        this.materialItemName = materialItemName;
    }

    public String getMaterialItemName() 
    {
        return materialItemName;
    }
    public void setMaterialItemModel(String materialItemModel) 
    {
        this.materialItemModel = materialItemModel;
    }

    public String getMaterialItemModel() 
    {
        return materialItemModel;
    }
    public void setMaterialItemUnit(Long materialItemUnit) 
    {
        this.materialItemUnit = materialItemUnit;
    }

    public Long getMaterialItemUnit() 
    {
        return materialItemUnit;
    }
    public void setMaterialItemClass(Long materialItemClass) 
    {
        this.materialItemClass = materialItemClass;
    }

    public Long getMaterialItemClass() 
    {
        return materialItemClass;
    }
    public void setMaterialItemType(Long materialItemType) 
    {
        this.materialItemType = materialItemType;
    }

    public Long getMaterialItemType() 
    {
        return materialItemType;
    }
    public void setMaterialItemRoute(Long materialItemRoute) 
    {
        this.materialItemRoute = materialItemRoute;
    }

    public Long getMaterialItemRoute() 
    {
        return materialItemRoute;
    }
    public void setMaterialItemStatus(Long materialItemStatus) 
    {
        this.materialItemStatus = materialItemStatus;
    }

    public Long getMaterialItemStatus() 
    {
        return materialItemStatus;
    }
    public void setMaterialItemAddTime(Date materialItemAddTime) 
    {
        this.materialItemAddTime = materialItemAddTime;
    }

    public Date getMaterialItemAddTime() 
    {
        return materialItemAddTime;
    }
    public void setMaterialItemComment(String materialItemComment) 
    {
        this.materialItemComment = materialItemComment;
    }

    public String getMaterialItemComment() 
    {
        return materialItemComment;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("materialItemId", getMaterialItemId())
            .append("materialItemName", getMaterialItemName())
            .append("materialItemModel", getMaterialItemModel())
            .append("materialItemUnit", getMaterialItemUnit())
            .append("materialItemClass", getMaterialItemClass())
            .append("materialItemType", getMaterialItemType())
            .append("materialItemRoute", getMaterialItemRoute())
            .append("materialItemStatus", getMaterialItemStatus())
            .append("materialItemAddTime", getMaterialItemAddTime())
            .append("materialItemComment", getMaterialItemComment())
            .toString();
    }
}
