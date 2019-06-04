/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.admin.facade;

import com.tlkj.cod.model.system.dto.CodFrameFileDto;
import com.tlkj.cod.model.system.dto.CodFrameFileUrlDto;
import org.springframework.web.multipart.MultipartFile;

/**
 * Desc 上传文件Facade
 *
 * @author sourcod
 * @version 1.0
 * @className FileFacade
 * @date 2018/11/30 10:18 PM
 */
public interface FileFacade {

    /**
     * 系统上传文件
     * @param prefix 前缀; 统一放到attachment路径下的前缀,可传多个自动建立文件夹
     * @param file   文件
     * @param type   指定上传类型; 2019-03-07 add
     * @return
     */
    CodFrameFileDto upload(MultipartFile file, String type, String... prefix);

    /**
     * 删除文件
     * @param id 主键
     * @return
     */
    boolean delete(String id);

    /**
     * 获取文件头url
     * @return 文件url
     */
    CodFrameFileUrlDto getFileUrl(String type);
}
