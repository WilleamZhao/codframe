/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.action.system;

import com.tlkj.cod.facade.system.FileFacade;
import com.tlkj.cod.model.common.Response;
import com.tlkj.cod.model.system.dto.CodFrameFileDto;
import com.tlkj.cod.model.system.dto.CodFrameFileUrlDto;
import com.tlkj.cod.model.common.GeneralResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Desc 文件Action
 *
 * @author sourcod
 * @version 1.0
 * @className FileAction
 * @date 2018/11/30 10:12 PM
 */
@RestController
@RequestMapping("system/file")
public class FileAction extends GeneralResponse {

    @Autowired
    FileFacade fileFacade;

    /**
     * 上传文件
     */
    @RequestMapping(value = "upload", method = {RequestMethod.PUT, RequestMethod.POST})
    public Response upload(@RequestParam("file") MultipartFile file, String type){
        CodFrameFileDto filePath = fileFacade.upload(file, type);
        return super.output(filePath);
    }

    /**
     * 获取url前缀
     */
    @RequestMapping(value = "getUrl", method = {RequestMethod.GET})
    public Response getUrl(HttpServletRequest request){
        String type = request.getParameter("type");
        CodFrameFileUrlDto dto = fileFacade.getFileUrl(type);
        return dto == null ? super.fail() : super.success(dto);
    }
}
