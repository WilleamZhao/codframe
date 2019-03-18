/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.facade.system;

import com.tlkj.cod.model.enums.StatusCode;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;

/**
 * Desc 附件Facade
 *
 * @author sourcod
 * @version 1.0
 * @className AttachmentFacade
 * @date 2018/11/20 6:59 PM
 */
public interface AttachmentFacade {



    /**
     * 保存附件
     * @param userId      上传用户Id
     * @return
     */
    StatusCode save(String id, String userId, String fileName, String fileType, String extName, String fileSize, String fileUnit, String url, HttpServletRequest request);

    /**
     * 上传附件
     * @param id
     * @param userId
     * @param fileType
     * @param request
     * @return
     */
    StatusCode uploadFile(String id, MultipartFile file, String userId, String fileName, String fileType, HttpServletRequest request);


}
