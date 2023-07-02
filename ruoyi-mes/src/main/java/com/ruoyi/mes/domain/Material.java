package com.ruoyi.mes.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.TreeEntity;

/**
 * 物料分类对象 mes_material
 * 
 * @author Yiming Guo
 * @date 2023-06-29
 */
public class Material extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 物料ID */
    private Long materialId;

    /** 父物料ID */
    private Long materialParentId;

    /** 物料名称 */
    @Excel(name = "物料名称")
    private String materialName;

    /** 显示顺序 */
    private Long materialOrder;

    /** 物料说明 */
    @Excel(name = "物料说明")
    private String materialComment;

    public void setMaterialId(Long materialId) 
    {
        this.materialId = materialId;
    }

    public Long getMaterialId() 
    {
        return materialId;
    }
    public void setMaterialParentId(Long materialParentId) 
    {
        this.materialParentId = materialParentId;
    }

    public Long getMaterialParentId() 
    {
        return materialParentId;
    }
    public void setMaterialName(String materialName) 
    {
        this.materialName = materialName;
    }

    public String getMaterialName() 
    {
        return materialName;
    }
    public void setMaterialOrder(Long materialOrder) 
    {
        this.materialOrder = materialOrder;
    }

    public Long getMaterialOrder() 
    {
        return materialOrder;
    }
    public void setMaterialComment(String materialComment) 
    {
        this.materialComment = materialComment;
    }

    public String getMaterialComment() 
    {
        return materialComment;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("materialId", getMaterialId())
            .append("materialParentId", getMaterialParentId())
            .append("materialName", getMaterialName())
            .append("materialOrder", getMaterialOrder())
            .append("materialComment", getMaterialComment())
            .toString();
    }
}
