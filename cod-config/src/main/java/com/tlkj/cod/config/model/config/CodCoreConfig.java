package com.tlkj.cod.config.model.config;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

/**
 * Desc codFrame 核心配置
 * 所有配置继承它
 *
 * @author sourcod
 * @version 1.0
 * @className CodCoreConfig
 * @date 2019/5/31 5:12 PM
 */
@Getter
@Setter
@Component
@Primary
public class CodCoreConfig {

    /**
     * 框架临时目录
     */
    @Value("${cod.core.temp.dir:.cod-temp}")
    private String codCoreTempDir;

    /**
     * 试用时间
     * 30天
     * 30*24*60*60
     */
    @Value("${cod.core.trial.time:2592000}")
    private String codCoreTrialTime;


    /**
     * 环境
     * 0: 开发环境
     * 1: 测试环境
     * 2: 正式环境
     *
     */
    @Value("${cod.server.config.env:0}")
    private String env;

    /**
     * 服务名
     */
    @Value("${cod.core.server.name:codframe}")
    private String serverName;

    /**
     * 项目版本
     */
    @Value("${cod.core.server.version:unknown}")
    private String version;

    /**
     * 框架版本
     */
    @Value("${cod.core.frame.version:#{codCoreConfig.getVersion()}}")
    private String frameVersion;

    /**
     * 项目名称
     */
    @Value("${cod.core.project.name:codframe}")
    private String projectName;

    /**
     * 临时框架版本
     */
    private String tempVersion;

    /**
     * 获取项目版本号
     * 1. 获取启动类
     * 2. 获取路径
     * 3. 获取jar包
     * 4. 读取 manifest 信息
     * 5. 读取版本号
     * @return
     * @throws IOException
     */
    public String getVersion() throws IOException, ClassNotFoundException {
        if (StringUtils.isNotBlank(tempVersion)){
            return this.tempVersion;
        }
        // 1. 获取启动类
        StackTraceElement[] elements = new Throwable().getStackTrace();
        String className = elements[elements.length - 1].getClassName();
        if ("java.lang.Thread".equals(className)){
            return "";
        }
        // 2. 获取路径
        String path = Class.forName(className).getProtectionDomain().getCodeSource().getLocation().getPath();

        // 3. 判断是否是开发环境
        if ("0".equals(env)){
            int i = path.lastIndexOf("/",path.lastIndexOf("/")-1)+1;
            if (i != -1){
                path = path.substring(0, i);
            }
        }

        // 4. 获取启动jar包
        JarFile jarFile = new JarFile(path + "/codframe.jar");

        Manifest manifest = jarFile.getManifest();
        Attributes attributes = manifest.getMainAttributes();
        // 5. 获取版本信息
        String version = "";
        if (attributes != null){
            version = attributes.getValue("project-version");
        }
        this.tempVersion = version;
        return version;
    }



}
