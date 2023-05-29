package com.ruoyi.system.telcard.mapper;

import java.util.List;
import com.ruoyi.system.telcard.domain.TelCard;

/**
 * 通讯录转换记录Mapper接口
 * 
 * @author wjh
 * @date 2023-05-22
 */
public interface TelCardMapper 
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
     * 删除通讯录转换记录
     * 
     * @param id 通讯录转换记录主键
     * @return 结果
     */
    public int deleteTelCardById(String id);

    /**
     * 批量删除通讯录转换记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTelCardByIds(String[] ids);
}
