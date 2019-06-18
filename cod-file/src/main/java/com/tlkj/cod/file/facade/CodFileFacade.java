package com.tlkj.cod.file.facade;

import com.tlkj.cod.dao.bean.Page;
import com.tlkj.cod.file.model.CodFileInfo;
import com.tlkj.cod.file.model.CodFileModel;
import com.tlkj.cod.file.model.enums.CodFileTypeEnum;

import java.io.InputStream;
import java.util.List;

/**
 * Desc cod-file facade
 *
 * @author sourcod
 * @version 1.0
 * @className CodFileFacade
 * @date 2019/6/18 2:17 PM
 */
public interface CodFileFacade {

    /**
     * 上传文件
     * @param inputStream 文件
     * @param prefix      前缀; 统一放到attachment路径下的前缀,可传多个自动建立文件夹
     * @param type        指定上传类型
     * @param fileName    文件名称
     * @return
     */
    CodFileModel uploadFile(InputStream inputStream, CodFileTypeEnum type, String fileName, String... prefix);

    /**
     * 删除文件
     * @param id 主键
     * @return
     */
    boolean delete(String id);

    /**
     * 读取文件
     * @param fileName
     * @return
     */
    InputStream readFile(String url, String fileName);

    /**
     * 文件列表
     * @return
     */
    Page<List<CodFileInfo>> listFile(CodFileTypeEnum typeEnum);
}
