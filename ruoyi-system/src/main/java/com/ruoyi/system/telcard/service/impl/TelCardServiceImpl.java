package com.ruoyi.system.telcard.service.impl;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.telcard.mapper.TelCardMapper;
import com.ruoyi.system.telcard.domain.TelCard;
import com.ruoyi.system.telcard.service.ITelCardService;

/**
 * 通讯录转换记录Service业务层处理
 * 
 * @author wjh
 * @date 2023-05-22
 */
@Service
public class TelCardServiceImpl implements ITelCardService 
{
    @Autowired
    private TelCardMapper telCardMapper;

    /**
     * 查询通讯录转换记录
     * 
     * @param id 通讯录转换记录主键
     * @return 通讯录转换记录
     */
    @Override
    public TelCard selectTelCardById(String id)
    {
        return telCardMapper.selectTelCardById(id);
    }

    /**
     * 查询通讯录转换记录列表
     * 
     * @param telCard 通讯录转换记录
     * @return 通讯录转换记录
     */
    @Override
    public List<TelCard> selectTelCardList(TelCard telCard)
    {
        return telCardMapper.selectTelCardList(telCard);
    }

    /**
     * 新增通讯录转换记录
     * 
     * @param telCard 通讯录转换记录
     * @return 结果
     */
    @Override
    public int insertTelCard(TelCard telCard)
    {
        telCard.setCreateTime(DateUtils.getNowDate());
        return telCardMapper.insertTelCard(telCard);
    }

    /**
     * 修改通讯录转换记录
     * 
     * @param telCard 通讯录转换记录
     * @return 结果
     */
    @Override
    public int updateTelCard(TelCard telCard)
    {
        telCard.setUpdateTime(DateUtils.getNowDate());
        return telCardMapper.updateTelCard(telCard);
    }

    /**
     * 批量删除通讯录转换记录
     * 
     * @param ids 需要删除的通讯录转换记录主键
     * @return 结果
     */
    @Override
    public int deleteTelCardByIds(String[] ids)
    {
        return telCardMapper.deleteTelCardByIds(ids);
    }

    /**
     * 删除通讯录转换记录信息
     * 
     * @param id 通讯录转换记录主键
     * @return 结果
     */
    @Override
    public int deleteTelCardById(String id)
    {
        return telCardMapper.deleteTelCardById(id);
    }

//
//    public static void main(String[] args) {
//        String inFile = null;
//        String outFile = null;
//        for (int i = 0; i < args.length; i++) {
//            if (i == 0 && inFile == null) {
//                inFile = args[0];
//            }
//            if (i == 2 && outFile == null) {
//                outFile = args[1];
//            }
//        }
//
//        if (inFile == null) {
//            inFile = "Tel.txt";
//        }
//        if (outFile == null) {
//            outFile = "Tel.vcf";
//        }
//
//        System.out.println("start>>>");
//        System.out.println("inFile:" + inFile);
//        System.out.println("outFile:" + outFile);
//        createTelCard(inFile, outFile);
//        System.out.println("over<<<");
//    }

    @Override
    public void createTelCard(String uploadPath, String downloadPath) {
        System.out.println("start>>>");
        System.out.println("inFile:" + uploadPath);
        System.out.println("outFile:" + downloadPath);
        writeFile(getContent(readFile(uploadPath)), downloadPath);
        System.out.println("over<<<");
    }

    private void writeFile(StringBuilder content, String outFile) {
        try {
            // 创建文件 并写入
            File file = new File(outFile);
            file.createNewFile();
            FileWriter writer = new FileWriter(outFile);
            writer.write(content.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Map<String, String> readFile(String filePath) {
        Map<String, String> cards = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            String[] tmp = new String[0];
            String[] splits = new String[]{" ", "\t"};
            int i = 0;
            while ((line = reader.readLine()) != null) {
                for (String split : splits) {
                    if (line.contains(split)) {
                        System.out.print(i);
                        System.out.println(">>>");
                        addCard(cards, line, split);
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cards;
    }

    private void addCard(Map<String, String> cards, String line, String splitChar) {
        String[] tmp;
        tmp = line.split(splitChar);
        if (tmp.length == 2) {
            cards.put(tmp[0].trim(), tmp[1].trim());
            System.out.println(tmp[0].trim());
            System.out.println(tmp[1].trim());
        }
        return;
    }

    private StringBuilder getContent(Map<String, String> cards) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : cards.entrySet()) {
            sb.append(getContent(entry.getKey(), entry.getValue()));
        }
        return sb;
    }

    private StringBuilder getContent(String userName, String telNumber) {
        StringBuilder sb = new StringBuilder();
        sb.append("BEGIN:VCARD").append("\r\n")
                .append("VERSION:3.0").append("\r\n")
                .append("FN:").append(userName).append("\r\n")
                .append("N:").append(";").append(userName).append(";;;").append("\r\n")
                .append("TEL;TYPE=VOICE,CELL,pref;").append("VALUE=text:").append(telNumber).append("\r\n")
                .append("END:VCARD");
        return sb;
    }
}
