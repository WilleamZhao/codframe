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

import com.tlkj.cod.dao.bean.Page;
import com.tlkj.cod.facade.system.AttachmentFacade;
import com.tlkj.cod.model.common.Response;
import com.tlkj.cod.model.system.dto.CodFrameAttachmentListDto;
import com.tlkj.cod.model.enums.StatusCode;
import com.tlkj.cod.model.system.dto.CodFrameAttachmentTypeDto;
import com.tlkj.cod.service.system.AttachmentService;
import com.tlkj.cod.service.system.UserService;
import com.tlkj.cod.model.common.GeneralResponse;
import com.tlkj.cod.model.enums.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Desc 附件Action
 *
 * @author sourcod
 * @version 1.0
 * @className AttachmentAction
 * @date 2018/11/7 下午10:35
 */
@RestController
@RequestMapping("system/attachment")
public class AttachmentAction extends GeneralResponse {

    @Autowired
    AttachmentService attachmentService;

    @Autowired
    AttachmentFacade attachmentFacade;

    @Autowired
    UserService userService;

    /**
     * 附件列表
     */
    @RequestMapping(value = "listAttachment", method = {RequestMethod.GET})
    public Response listAttachment(HttpServletRequest request){
        String fileName = request.getParameter("fileName");
        String userName = request.getParameter("userName");
        String extName = request.getParameter("extName");
        String size = request.getParameter("size");
        String ip = request.getParameter("ip");
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        Page<List<CodFrameAttachmentListDto>> listPage = attachmentService.listAttachment(fileName, userName, extName, size, ip, page, limit);
        return listPage == null ? super.fail() : super.success(listPage);
    }

    public Response getAttachment(HttpServletRequest request){

        return success();
    }

    /**
     * 上传附件
     */
    @RequestMapping(value = "save", method = {RequestMethod.POST})
    public Response uploadAttachment(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        String id = request.getParameter("id");
        String token = request.getParameter("access_token");
        String userId = userService.getUserByCache(token).getId();
        String status = request.getParameter("status");
        String fileType = request.getParameter("fileType");
        String fileName = request.getParameter("fileName");
        StatusCode statusCode = attachmentFacade.uploadFile(id, file, userId, fileName, fileType, request);
        return super.output(statusCode);
    }

    /**
     * 删除附件
     */
    @RequestMapping(value = "del", method = {RequestMethod.POST})
    public Response deleteAttachment(HttpServletRequest request){
        String id = request.getParameter("id");
        String token = request.getParameter("access_token");
        String userId = userService.getUserByCache(token).getId();
        String status = request.getParameter("status");

        StatusCode statusCode = attachmentService.deleteFile(id, userId, status);
        return StatusCode.verifyStatusCode(statusCode) ? super.success(statusCode) : super.fail();
    }

    /**
     * 获取附件分类
     * @return
     */
    @RequestMapping(value = "gettype", method = {RequestMethod.GET})
    public Response getType(){
        List<CodFrameAttachmentTypeDto> list = attachmentService.getType();
        return list == null ? super.fail() : super.success(list);
    }
}
