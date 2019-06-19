package com.tlkj.cod.file;

import com.tlkj.cod.file.facade.CodFileFacade;
import com.tlkj.cod.file.service.CodFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Desc cod-file 工厂
 *
 * @author sourcod
 * @version 1.0
 * @className CodFileFactory
 * @date 2019/6/19 9:47 AM
 */
@Service
public class CodFileFactory {

    @Autowired
    CodFileFacade codFileFacade;


    @Autowired
    CodFileService codFileService;
}
