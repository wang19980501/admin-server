package com.ruoyi.system.telcard.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 通讯录转换记录对象 tel_card
 * 
 * @author wjh
 * @date 2023-05-22
 */
public class TelCard extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private String id;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 上传文件地址 */
    private String uploadPath;

    /** 下载文件地址 */
    private String downloadPath;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }
    public void setUploadPath(String uploadPath) 
    {
        this.uploadPath = uploadPath;
    }

    public String getUploadPath() 
    {
        return uploadPath;
    }
    public void setDownloadPath(String downloadPath) 
    {
        this.downloadPath = downloadPath;
    }

    public String getDownloadPath() 
    {
        return downloadPath;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("uploadPath", getUploadPath())
            .append("downloadPath", getDownloadPath())
            .toString();
    }
}
