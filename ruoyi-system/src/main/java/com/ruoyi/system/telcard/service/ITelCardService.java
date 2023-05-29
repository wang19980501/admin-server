package com.ruoyi.system.telcard.service;

import java.util.List;
import com.ruoyi.system.telcard.domain.TelCard;

/**
 * 通讯录转换记录Service接口
 * 
 * @author wjh
 * @date 2023-05-22
 */
public interface ITelCardService 
{
    /**
     * 查询通讯录转换记录
     * 
     * @param id 通讯录转换记录主键
     * @return 通讯录转换记录
     */
    public TelCard selectTelCardById(String id);

    /**
     * 查询通讯录转换记录列表
     * 
     * @param telCard 通讯录转换记录
     * @return 通讯录转换记录集合
     */
    public List<TelCard> selectTelCardList(TelCard telCard);

    /**
     * 新增通讯录转换记录
     * 
     * @param telCard 通讯录转换记录
     * @return 结果
     */
    public int insertTelCard(TelCard telCard);

    /**
     * 修改通讯录转换记录
     * 
     * @param telCard 通讯录转换记录
     * @return 结果
     */
    public int updateTelCard(TelCard telCard);

    /**
     * 文件转换
     * @param uploadPath 上传文件
     * @param downloadPath 下载文件
     */
    public void createTelCard(String uploadPath, String downloadPath);

    /**
     * 批量删除通讯录转换记录
     * 
     * @param ids 需要删除的通讯录转换记录主键集合
     * @return 结果
     */
    public int deleteTelCardByIds(String[] ids);

    /**
     * 删除通讯录转换记录信息
     * 
     * @param id 通讯录转换记录主键
     * @return 结果
     */
    public int deleteTelCardById(String id);
}
