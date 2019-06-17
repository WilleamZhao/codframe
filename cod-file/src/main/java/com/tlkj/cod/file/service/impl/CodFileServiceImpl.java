/*
 * Copyright (c) 2019.
 * sourcod.com
 * All rights reserved
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.file.service.impl;

import com.tlkj.cod.file.model.CodFileModel;
import com.tlkj.cod.file.service.CodFileService;
import org.springframework.web.multipart.MultipartFile;

/**
 * cod-file service
 */
public class CodFileServiceImpl implements CodFileService {

    @Override
    public CodFileModel upload(MultipartFile file, String type, String... prefix) {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}
