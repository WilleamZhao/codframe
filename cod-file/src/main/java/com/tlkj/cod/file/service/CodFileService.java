/*
 * Copyright (c) 2019.
 * sourcod.com
 * All rights reserved
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.file.service;

import com.tlkj.cod.file.model.CodFileModel;
import org.springframework.web.multipart.MultipartFile;

public interface CodFileService {

    /**
     * 系统上传文件
     * @param prefix 前缀; 统一放到attachment路径下的前缀,可传多个自动建立文件夹
     * @param file   文件
     * @param type   指定上传类型; 2019-03-07 add
     * @return
     */
    CodFileModel upload(MultipartFile file, String type, String... prefix);

    /**
     * 删除文件
     * @param id 主键
     * @return
     */
    boolean delete(String id);
}
