package com.ruoyi.system.telcard.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.IdGen;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.common.utils.file.MimeTypeUtils;
import com.ruoyi.system.telcard.mapper.TelCardMapper;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.telcard.domain.TelCard;
import com.ruoyi.system.telcard.service.ITelCardService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 通讯录转换记录Controller
 * 
 * @author wjh
 * @date 2023-05-22
 */
@RestController
@RequestMapping("/system/telcard")
public class TelCardController extends BaseController
{
    @Autowired
    private ITelCardService telCardService;

    /**
     * 查询通讯录转换记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:telcard:list')")
    @GetMapping("/list")
    public TableDataInfo list(TelCard telCard)
    {
        startPage();
        List<TelCard> list = telCardService.selectTelCardList(telCard);
        return getDataTable(list);
    }

    /**
     * 导出通讯录转换记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:telcard:export')")
    @Log(title = "通讯录转换记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TelCard telCard)
    {
        List<TelCard> list = telCardService.selectTelCardList(telCard);
        ExcelUtil<TelCard> util = new ExcelUtil<TelCard>(TelCard.class);
        util.exportExcel(response, list, "通讯录转换记录数据");
    }

    /**
     * 获取通讯录转换记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:telcard:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(telCardService.selectTelCardById(id));
    }

    /**
     * 新增通讯录转换记录
     */
    @PreAuthorize("@ss.hasPermi('system:telcard:add')")
    @Log(title = "通讯录转换记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TelCard telCard)
    {
        return toAjax(telCardService.insertTelCard(telCard));
    }

    /**
     * 修改通讯录转换记录
     */
    @PreAuthorize("@ss.hasPermi('system:telcard:edit')")
    @Log(title = "通讯录转换记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TelCard telCard)
    {
        return toAjax(telCardService.updateTelCard(telCard));
    }

    /**
     * 删除通讯录转换记录
     */
    @PreAuthorize("@ss.hasPermi('system:telcard:remove')")
    @Log(title = "通讯录转换记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(telCardService.deleteTelCardByIds(ids));
    }

    /**
     *
     * @param file 上传的文件
     * @param response
     * @param request
     */
    @Log(title = "文件转换", businessType = BusinessType.UPDATE)
    @PostMapping("/fileConversion")
    public void fileDownload(@RequestParam("file") MultipartFile file, HttpServletResponse response, HttpServletRequest request)
    {
        try
        {
            String originalFilename = file.getOriginalFilename();
            String uploadPath = FileUploadUtils.upload(RuoYiConfig.getAvatarPath(), file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
            // 数据库资源地址
            uploadPath = RuoYiConfig.getProfile() + StringUtils.substringAfter(uploadPath, Constants.RESOURCE_PREFIX);
            String downloadPath = uploadPath.replace(".txt", ".vcf");
            telCardService.createTelCard(uploadPath, downloadPath);
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, originalFilename);
            FileUtils.writeBytes(downloadPath, response.getOutputStream());
            TelCard telCard = new TelCard();
            telCard.setId(IdGen.uuid());
            telCard.setUploadPath(uploadPath);
            telCard.setDownloadPath(downloadPath);
            telCard.setCreateBy(getUsername());
            telCardService.insertTelCard(telCard);
        }
        catch (Exception e) {
            System.out.println("下载文件失败:" +  e);
        }

    }
}
